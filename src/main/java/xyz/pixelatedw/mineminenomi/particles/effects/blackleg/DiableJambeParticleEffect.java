/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DiableJambeParticleEffect
/*    */   extends ParticleEffect {
/*    */   public void spawn(PlayerEntity player, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     double rotX = 0.0D, rotZ = 0.0D;
/*    */     
/* 17 */     float rotation = Math.abs(player.rotationYaw);
/* 18 */     if (rotation >= 280.0F || rotation < 30.0F) {
/*    */       
/* 20 */       rotX = -0.1D;
/* 21 */       rotZ = 0.2D;
/*    */     }
/* 23 */     else if (rotation >= 30.0F && rotation < 120.0F) {
/*    */       
/* 25 */       rotX = 0.2D;
/* 26 */       rotZ = 0.1D;
/*    */     }
/* 28 */     else if (rotation >= 120.0F && rotation < 200.0F) {
/*    */       
/* 30 */       rotX = 0.1D;
/* 31 */       rotZ = -0.2D;
/*    */     }
/* 33 */     else if (rotation >= 200.0F && rotation < 280.0F) {
/*    */       
/* 35 */       rotX = -0.2D;
/* 36 */       rotZ = -0.1D;
/*    */     } 
/*    */     
/* 39 */     for (int i = 0; i < 2; i++) {
/*    */       
/* 41 */       double offsetX = rotX + WyHelper.randomDouble() / 5.0D;
/* 42 */       double offsetY = 0.4D + WyHelper.randomDouble() / 2.5D;
/* 43 */       double offsetZ = rotZ + WyHelper.randomDouble() / 5.0D;
/*    */       
/* 45 */       int age = (int)(1.0D + WyHelper.randomWithRange(0, 2));
/*    */       
/* 47 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/* 48 */       data.setLife(age);
/* 49 */       data.setSize(age / 2.5F);
/* 50 */       data.setMotion(0.0D, 0.02D, 0.0D);
/* 51 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\DiableJambeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */