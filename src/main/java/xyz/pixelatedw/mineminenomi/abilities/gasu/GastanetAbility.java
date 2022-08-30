/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.gasu.GastanetParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class GastanetAbility extends Ability {
/* 22 */   public static final GastanetAbility INSTANCE = new GastanetAbility();
/*    */   
/* 24 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GastanetParticleEffect();
/*    */ 
/*    */   
/*    */   public GastanetAbility() {
/* 28 */     super("Gastanet", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("The user creates an explosion by detonating gas with their castanets");
/* 30 */     setMaxCooldown(8.0D);
/*    */     
/* 32 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 37 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
/* 38 */     ExplosionAbility explosion = AbilityHelper.newExplosion(null, player.world, 
/* 39 */         (mop.getHitVec()).x, 
/* 40 */         (mop.getHitVec()).y, 
/* 41 */         (mop.getHitVec()).z, 5.0F);
/* 42 */     explosion.setStaticDamage(40.0F);
/* 43 */     explosion.setDestroyBlocks(false);
/* 44 */     explosion.doExplosion();
/*    */     
/* 46 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(new BlockPos(mop.getHitVec()), player.world, 
/* 47 */         ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player) ? 12.0D : 8.0D, new Class[] { LivingEntity.class });
/* 48 */     targets.remove(player);
/*    */     
/* 50 */     if (ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/* 51 */       targets.forEach(target -> ((ShinokuniAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)ShinokuniAbility.INSTANCE)).applyEffects(player, target));
/*    */     } else {
/* 53 */       targets.forEach(target -> target.addPotionEffect(new EffectInstance(Effects.POISON, 200, 5)));
/*    */     } 
/* 55 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GastanetAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */