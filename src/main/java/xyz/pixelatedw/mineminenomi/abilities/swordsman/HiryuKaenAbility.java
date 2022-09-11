package xyz.pixelatedw.mineminenomi.abilities.swordsman;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.swordsman.HiryuKaenParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class HiryuKaenAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility {
  public static final HiryuKaenAbility INSTANCE = new HiryuKaenAbility();
  public static final HiryuKaenParticleEffect PARTICLES = new HiryuKaenParticleEffect();
  
  private boolean wasActivated = false;
  
  private boolean canHit = false;
  private boolean hasFallDamage = true;
  
  public HiryuKaenAbility() {
    super("Hiryu: Kaen", AbilityHelper.getStyleCategory());
    setDescription("The user leaps into the air and releases a big flaming shockwave slash when landing");
    setMaxCooldown(15.0D);
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
    this.onEndCooldownEvent = this::onEndCooldown;
  }

  
  private void onEndCooldown(PlayerEntity player) {
    this.wasActivated = false;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldown) {
    if (cooldown < getMaxCooldown() && !this.canHit && !player.onGround && !this.wasActivated) {
      
      this.canHit = true;
      this.wasActivated = true;
    } 
    
    if (player.onGround && this.canHit) {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 4.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      
      for (LivingEntity entity : targets) {
        
        if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
          
          entity.attackEntityFrom((DamageSource)(new ModEntityDamageSource("player", (Entity)player)).markDamageAsSlash(), 18.0F);
          entity.setFire(4);
        } 
      } 
      
      if (targets.size() > 0) {
        ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
      }
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
      this.canHit = false;
    } 
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseSwordsmanAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
      return false;
    } 
    
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
    player.setMotion(speed.x, 1.3D, speed.z);
    player.velocityChanged = true;
    this.canHit = false;
    this.hasFallDamage = false;
    
    return true;
  }


  
  public void resetFallDamage(LivingEntity player) {
    this.hasFallDamage = true;
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }
}


