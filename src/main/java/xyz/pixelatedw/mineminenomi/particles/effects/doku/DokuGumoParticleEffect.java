/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doku;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class DokuGumoParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public boolean venomDemon = false;
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 18 */     int width = this.venomDemon ? 5 : 3;
/* 19 */     int height = this.venomDemon ? 6 : 3;
/* 20 */     for (int i = 0; i < (this.venomDemon ? 50 : 30); i++) {
/*    */       
/* 22 */       double offsetX = WyHelper.randomWithRange(-width, width) + WyHelper.randomDouble();
/* 23 */       double offsetY = WyHelper.randomWithRange(-height, height) + WyHelper.randomDouble();
/* 24 */       double offsetZ = WyHelper.randomWithRange(-width, width) + WyHelper.randomDouble();
/*    */       
/* 26 */       int age = (int)(1.0D + WyHelper.randomWithRange(0, 2));
/*    */       
/* 28 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
/* 29 */       if (this.venomDemon) {
/* 30 */         data.setColor(1.0F, 0.0F, 0.0F);
/*    */       }
/* 32 */       data.setLife(age);
/* 33 */       data.setSize(1.0F);
/* 34 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\DokuGumoParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */