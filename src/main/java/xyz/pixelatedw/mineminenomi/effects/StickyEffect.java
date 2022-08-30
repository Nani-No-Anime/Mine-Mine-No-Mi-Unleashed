/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class StickyEffect extends OverlayEffect {
/*    */   public StickyEffect() {
/* 19 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 30 */     if (entity.isBurning() && entity.ticksExisted > 0) {
/*    */       
/* 32 */       entity.extinguish();
/* 33 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 6.0F);
/* 34 */       explosion.setExplosionSound(true);
/* 35 */       explosion.setDamageOwner(true);
/* 36 */       explosion.setDestroyBlocks(true);
/* 37 */       explosion.setFireAfterExplosion(true);
/* 38 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
/* 39 */       explosion.setDamageEntities(true);
/* 40 */       explosion.setStaticDamage(100.0F);
/* 41 */       explosion.doExplosion();
/* 42 */       entity.removePotionEffect(ModEffects.STICKY);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 61 */     return new float[] { 0.62F, 0.78F, 0.0F, 0.95F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasBodyOverlayColor() {
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 73 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getResourceLocation(int duration) {
/* 78 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay() {
/* 84 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\StickyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */