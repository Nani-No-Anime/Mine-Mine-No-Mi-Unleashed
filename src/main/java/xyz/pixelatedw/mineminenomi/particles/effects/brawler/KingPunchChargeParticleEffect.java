/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.brawler;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KingPunchChargeParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 22 */     int i = 0;
/* 23 */     double t = 0.0D;
/*    */     
/* 25 */     Random rand = world.rand;
/*    */     
/* 27 */     while (t < 1.0D) {
/*    */       
/* 29 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 31 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 33 */         double x = t * Math.cos(theta);
/* 34 */         double y = rand.nextInt(1);
/* 35 */         double z = t * Math.sin(theta);
/*    */         
/* 37 */         motionX = -x / 20.0D;
/* 38 */         motionY = 0.05D + rand.nextDouble() / 10.0D;
/* 39 */         motionZ = -z / 20.0D;
/*    */         
/* 41 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/*    */         
/* 43 */         if (i % 2 == 0) {
/* 44 */           data = new GenericParticleData(ModParticleTypes.MOKU2);
/*    */         }
/* 46 */         double offsetX = world.rand.nextDouble() * WyHelper.randomWithRange(7, 9);
/* 47 */         double offsetY = 1.0D;
/* 48 */         double offsetZ = world.rand.nextDouble() * WyHelper.randomWithRange(7, 9);
/*    */         
/* 50 */         BlockState blockState = world.getBlockState((new BlockPos(posX + offsetX, posY, posZ + offsetZ)).down());
/*    */         
/* 52 */         ((ServerWorld)world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), posX - 4.0D + offsetX, posY - 1.0D + offsetY, posZ - 4.0D + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 58 */         data.setLife(1);
/* 59 */         data.setSize(1.0F);
/* 60 */         data.setMotion(motionX, motionY, motionZ);
/* 61 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.25D + WyHelper.randomDouble() * 2.0D);
/*    */         
/* 63 */         data.setLife(3);
/* 64 */         data.setSize(1.0F);
/* 65 */         data.setMotion(motionX, motionY + 0.001D, motionZ);
/* 66 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.5D + WyHelper.randomDouble(), posY + y, posZ + z * 1.5D + WyHelper.randomDouble());
/*    */         
/* 68 */         data.setLife(5);
/* 69 */         data.setSize(1.0F);
/* 70 */         data.setMotion(motionX, motionY + 0.005D, motionZ);
/* 71 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.75D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.75D + WyHelper.randomDouble() * 2.0D);
/*    */         
/* 73 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\brawler\KingPunchChargeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */