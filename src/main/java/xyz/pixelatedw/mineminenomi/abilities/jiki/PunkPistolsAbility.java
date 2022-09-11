package xyz.pixelatedw.mineminenomi.abilities.jiki;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.PunkPistolProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class PunkPistolsAbility extends RepeaterAbility implements IAnimatedAbility {
  public static final PunkPistolsAbility INSTANCE = new PunkPistolsAbility();
  
  private static final int ITEMS_PER_SPEAR = 10;
  private static final int SPEARS = 6;
  private List<ItemStack> magneticItems = new ArrayList<>();
  
  private int prevContTime;
  private boolean shoot;
  
  public PunkPistolsAbility() {
    super("Punk Pistols", AbilityHelper.getDevilFruitCategory());
    setDescription("Uses §260§r magnetic objects from the user's inventory to form §26§r spears and shoots them at enemies.");
    setMaxCooldown(12.0D);
    setMaxRepeaterCount(6, 4);
    
    this.onStartContinuityEvent = this::onStartContinuity;
    this.onEndContinuityEvent = this::onEndContinuity;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (getThreshold() != 0 && i % 5 == 0) {
      
      List<ItemStack> originals = JikiHelper.getMagneticItems(player, 10);
      int count = JikiHelper.countMagneticItems(originals);
      if (count < 10) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
        endContinuity(player);
        
        return;
      } 
      List<ItemStack> components = JikiHelper.getMagneticItemsStack(player, originals, 10);
      PunkPistolProjectile proj = new PunkPistolProjectile(player.world, (LivingEntity)player, components);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 2.0F);
      
      AttractAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
  }












  
  private boolean onEndContinuity(PlayerEntity player) {
    return true;
  }

  
  public boolean onStartContinuity(PlayerEntity player) {
    List<ItemStack> originals = JikiHelper.getMagneticItems(player, 10);
    int count = JikiHelper.countMagneticItems(originals);
    
    if (count < 10) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
      return false;
    } 
    
    this.magneticItems = JikiHelper.getMagneticItemsStack(player, originals, 10);
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (player.world.isRemote || this.shoot) {
      return false;
    }
    List<ItemStack> originals = JikiHelper.getMagneticItems(player, 10);
    int count = JikiHelper.countMagneticItems(originals);
    if (count < 10) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
      endContinuity(player);
      return false;
    } 
    
    List<ItemStack> components = JikiHelper.getMagneticItemsStack(player, originals, 10);
    
    PunkPistolProjectile proj = new PunkPistolProjectile(player.world, (LivingEntity)player, components);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 2.0F);
    
    this.shoot = true;
    this.prevContTime = getContinueTime();
    this.continueTime = 0;
    setThreshold(2.0D);
    WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


