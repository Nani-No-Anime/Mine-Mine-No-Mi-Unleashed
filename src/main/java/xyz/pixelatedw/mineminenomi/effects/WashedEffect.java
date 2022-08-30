/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WashedEffect
/*    */   extends Effect {
/*    */   public WashedEffect() {
/* 17 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 39 */     if (entity.world.isRemote) {
/*    */       return;
/*    */     }
/* 42 */     for (int i = 0; i < 15; i++) {
/*    */       
/* 44 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 45 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 46 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 48 */       ParticleType<GenericParticleData> particle = ModParticleTypes.AWA;
/* 49 */       if (i % 3 == 0) {
/* 50 */         particle = ModParticleTypes.AWA3;
/*    */       }
/* 52 */       GenericParticleData data = new GenericParticleData(particle);
/* 53 */       data.setLife(7);
/* 54 */       data.setSize(1.3F);
/* 55 */       WyHelper.spawnParticles(data, (ServerWorld)entity.world, entity.getPosX() + offsetX, entity.getPosY() + 1.0D + offsetY, entity.getPosZ() + offsetZ);
/*    */     } 
/*    */     
/* 58 */     if (entity.isWet())
/*    */     {
/* 60 */       entity.removePotionEffect(this);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\WashedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */