package xyz.pixelatedw.mineminenomi.abilities.jiki;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.DamnedPunkProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.DamnedPunkZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PunkGibsonZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class DamnedPunkAbility extends PunchTriggerAbility implements IFormRequiredAbility, IMorphAbility {
  public static final DamnedPunkAbility INSTANCE = new DamnedPunkAbility();
  
  private static final int MAX_ITEMS = 32;
  private List<ItemStack> magneticItems = new ArrayList<>();

  
  public DamnedPunkAbility() {
    super("Damned Punk", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the users arm into a railgun that shoots a projectile with every swing using §232§r magnetic objects from the users inventory per shoot, dealing massive damage on impact.");
    setThreshold(15.0D);
    setMaxCooldown(25.0D);
    
    this.onStartContinuityEvent = this::onStartChargingEvent;
    this.onEndContinuityEvent = this::onEndChargingEvent;
    this.onSwingEvent = this::onSwingEvent;
  }

  
  private boolean onSwingEvent(PlayerEntity player) {
    if (player.world.isRemote) {
      return false;
    }
    List<ItemStack> originals = JikiHelper.getMagneticItems(player, 32);
    int count = JikiHelper.countMagneticItems(originals);
    if (count < 32) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
      return false;
    } 
    
    this.magneticItems = JikiHelper.getMagneticItemsStack(player, originals, 32);
    
    DamnedPunkProjectile proj = new DamnedPunkProjectile(player.world, (LivingEntity)player, this.magneticItems);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.5F);
    
    AttractAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }

  
  public boolean onStartChargingEvent(PlayerEntity player) {
    List<ItemStack> originals = JikiHelper.getMagneticItems(player, 32);
    int count = JikiHelper.countMagneticItems(originals);
    
    if (count < 32) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
      return false;
    } 
    
    return true;
  }

  
  public boolean onEndChargingEvent(PlayerEntity player) {
    return true;
  }


  
  public boolean canUse(PlayerEntity player) {
    if (!super.canUse(player)) {
      return false;
    }
    Ability abl = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)PunkGibsonAbility.INSTANCE);
    
    return (abl != null && abl.isContinuous());
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PunkGibsonZoanInfo.INSTANCE };
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)DamnedPunkZoanInfo.INSTANCE;
  }


  
  public boolean isTransformationActive(LivingEntity entity) {
    return isContinuous();
  }
}


