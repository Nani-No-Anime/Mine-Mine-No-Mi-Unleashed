/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SmokeEffect extends Effect {
/* 16 */   private int smokeTime = 0;
/*    */ 
/*    */   
/*    */   public SmokeEffect() {
/* 20 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 25 */     if (duration == 1) {
/*    */       
/* 27 */       this.smokeTime = 0;
/* 28 */       return false;
/*    */     } 
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 37 */     if (entity.world.isRemote) {
/*    */       return;
/*    */     }
/* 40 */     for (int i = 0; i < 80; i++) {
/*    */       
/* 42 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 43 */       double offsetY = 1.0D + WyHelper.randomDouble() / 2.0D;
/* 44 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 46 */       ParticleType<GenericParticleData> particle = ModParticleTypes.MOKU2;
/* 47 */       if (i % 3 == 0) {
/* 48 */         particle = ModParticleTypes.MOKU;
/*    */       }
/* 50 */       GenericParticleData data = new GenericParticleData(particle);
/* 51 */       data.setLife((int)WyHelper.randomWithRange(1, 10));
/* 52 */       data.setSize((float)WyHelper.randomWithRange(0, 4));
/* 53 */       WyHelper.spawnParticles(data, (ServerWorld)entity.world, entity.getPosX() + offsetX, entity.getPosY() + offsetY, entity.getPosZ() + offsetZ);
/*    */     } 
/*    */     
/* 56 */     if (entity.isWet())
/*    */     {
/* 58 */       entity.removePotionEffect(this);
/*    */     }
/* 60 */     this.smokeTime++;
/*    */     
/* 62 */     if (this.smokeTime > 100) {
/*    */       
/* 64 */       entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, this.smokeTime / 100, false, false));
/* 65 */       if (this.smokeTime > 200)
/*    */       {
/* 67 */         entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, this.smokeTime / 200, false, false));
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\SmokeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */