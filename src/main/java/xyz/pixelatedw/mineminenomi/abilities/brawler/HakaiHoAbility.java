/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;

import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class HakaiHoAbility extends PunchAbility implements IMultiTargetAbility {
/* 22 */   public static final HakaiHoAbility INSTANCE = new HakaiHoAbility();
/*    */ 
/*    */   
/*    */   public HakaiHoAbility() {
/* 26 */     super("Hakai Ho", AbilityHelper.getStyleCategory());
/* 27 */     setDescription("The user punches with enough force to create an explosion");
/* 28 */     setMaxCooldown(10.0D);
/*    */     
/* 30 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 31 */     this.onHitEntityEvent = this::onHitEntity;
/* 32 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 37 */     if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
/*    */       
/* 39 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
/* 40 */       return false;
/*    */     } 
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 48 */     clearTargets();
/*    */     
/* 50 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(target.getPosition(), target.world, 2.0D);
/* 51 */     targets.remove(player);
/*    */     
/* 53 */     for (LivingEntity aoeTarget : targets) {
/*    */       
/* 55 */       if (isTarget(aoeTarget)) {
/*    */         
/* 57 */         aoeTarget.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 10.0F);
/* 58 */         aoeTarget.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 100, 0, false, false));
/* 59 */         Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 60 */         aoeTarget.setMotion(speed.x, 0.5D, speed.z);
/* 61 */         aoeTarget.velocityChanged = true;
/*    */       } 
/*    */     } 
/*    */     
/* 65 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)target, target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 2.0F);
/* 66 */     explosion.setStaticDamage(15.0F);
/* 67 */     explosion.setDestroyBlocks(false);
/* 68 */     explosion.setDamageEntities(false);
/* 69 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 70 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 76 */     return 15.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\HakaiHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */