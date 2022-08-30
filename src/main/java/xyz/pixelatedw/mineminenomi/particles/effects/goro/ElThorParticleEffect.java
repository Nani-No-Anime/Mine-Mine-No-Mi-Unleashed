/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ElThorParticleEffect extends ParticleEffect {
/*    */   public boolean aiming = false;
/* 13 */   public int ownerID = 0;
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/*    */     int i;
/* 18 */     for (i = 0; i < 30; i++) {
/*    */       
/* 20 */       double offsetX = WyHelper.randomWithRange(-32, 32) + WyHelper.randomDouble();
/* 21 */       double offsetY = 72.0D + WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 22 */       double offsetZ = WyHelper.randomWithRange(-32, 32) + WyHelper.randomDouble();
/*    */       
/* 24 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO3);
/* 25 */       data.setLife(140);
/* 26 */       data.setSize(50.0F);
/*    */       
/* 28 */       if (i % 2 == 0) {
/* 29 */         data.setColor(0.4F, 0.4F, 0.4F);
/*    */       } else {
/* 31 */         data.setColor(0.3F, 0.3F, 0.3F);
/*    */       } 
/* 33 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + 0.5D + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */     
/* 36 */     for (i = 0; i < 16; i++) {
/*    */       
/* 38 */       double offsetX = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
/* 39 */       double offsetY = 72.0D + WyHelper.randomDouble();
/* 40 */       double offsetZ = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
/*    */       
/* 42 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO2);
/* 43 */       data.setLife(140);
/* 44 */       data.setSize(15.0F);
/* 45 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + 0.5D + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */     
/* 48 */     if (this.aiming)
/*    */     {
/* 50 */       for (i = 0; i < 3; i++) {
/*    */         
/* 52 */         double offsetX = WyHelper.randomDouble();
/* 53 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 55 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO);
/* 56 */         data.setLife(1);
/* 57 */         data.setEntityID(this.ownerID);
/* 58 */         data.hideFromOthers();
/* 59 */         data.setSize(10.0F);
/* 60 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + 0.5D + offsetX, posY, posZ + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\ElThorParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */