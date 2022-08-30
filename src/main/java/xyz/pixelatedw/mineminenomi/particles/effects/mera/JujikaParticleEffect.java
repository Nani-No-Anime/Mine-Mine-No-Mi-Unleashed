/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class JujikaParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double dirId) {
/* 18 */     Direction dir = Direction.values()[(int)dirId];
/* 19 */     Random rand = world.rand; float i;
/* 20 */     for (i = 0.0F; i <= 5.0F; i += 0.5F) {
/*    */       GenericParticleData data;
/*    */ 
/*    */       
/* 24 */       int x = 0;
/* 25 */       int z = 0;
/*    */       
/* 27 */       z = (int)(z + i * dir.getXOffset());
/* 28 */       x = (int)(x + i * dir.getZOffset());
/*    */       
/* 30 */       if (rand.nextInt(10) % 2 == 0) {
/* 31 */         data = new GenericParticleData(ModParticleTypes.MERA);
/*    */       } else {
/* 33 */         data = new GenericParticleData(ModParticleTypes.MERA2);
/* 34 */       }  data.setLife((int)WyHelper.randomWithRange(1, 2));
/* 35 */       data.setSize((float)WyHelper.randomWithRange(0, 2));
/* 36 */       data.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 37 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX - x, posY, posZ - z);
/* 38 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY, posZ + z);
/* 39 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + i, posZ);
/* 40 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY - i, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\JujikaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */