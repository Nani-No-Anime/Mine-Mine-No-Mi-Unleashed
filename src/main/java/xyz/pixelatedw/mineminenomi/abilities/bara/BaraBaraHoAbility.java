package xyz.pixelatedw.mineminenomi.abilities.bara;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraBaraHoProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.BaraHoZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class BaraBaraHoAbility extends Ability implements IMorphAbility {
  public static final BaraBaraHoAbility INSTANCE = new BaraBaraHoAbility();
  
  private BaraBaraHoProjectile projectile;

  
  public BaraBaraHoAbility() {
    super("Bara Bara Ho", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches the user's fist towards the enemy\nIf the user holds a weapon in hand this will increase the fist's damage as well");
    setMaxCooldown(4.0D);
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
    this.onEndCooldownEvent = this::onEndCooldownEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BaraBaraFestivalAbility ability = (BaraBaraFestivalAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BaraBaraFestivalAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_BARA_FESTIVAL, new Object[] { getDisplayName() }));
      return false;
    } 
    
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    if (props.getZoanPoint().isEmpty()) {
      props.setZoanPoint("");
    }
    BaraBaraHoProjectile proj = new BaraBaraHoProjectile(player.world, (LivingEntity)player);
    
    ItemStack stack = player.getHeldItemMainhand();
    
    float extraDamage = 0.0F;
    if (ItemsHelper.isSword(stack)) {
      extraDamage = ItemsHelper.getItemDamage(stack);
    }
    proj.setDamage(proj.getDamage() + extraDamage);
    
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    this.projectile = proj;
    
    props.setZoanPoint(getTransformation().getForm());
    WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
    
    player.addPotionEffect(new EffectInstance(ModEffects.NO_HANDS, 12, 0));
    
    return true;
  }

  
  private void duringCooldownEvent(PlayerEntity player, int cd) {
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    if (this.projectile == null || !this.projectile.isAlive()) {
      
      props.setZoanPoint("");
      WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
    } 
  }

  
  private void onEndCooldownEvent(PlayerEntity player) {
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    props.setZoanPoint("");
    WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)BaraHoZoanInfo.INSTANCE;
  }


  
  public boolean isTransformationActive(LivingEntity entity) {
    return DevilFruitCapability.get(entity).getZoanPoint().equals(getTransformation().getForm());
  }
}


