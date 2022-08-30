/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChargedWeatherBallParticleEffect extends ParticleEffect {
/*    */   private float red;
/*    */   private float green;
/*    */   private float blue;
/*    */   private ParticleType<GenericParticleData> particle;
/*    */   
/*    */   public ChargedWeatherBallParticleEffect(Color color, ParticleType<GenericParticleData> particle) {
/* 19 */     this.red = color.getRed() / 255.0F;
/* 20 */     this.green = color.getGreen() / 255.0F;
/* 21 */     this.blue = color.getBlue() / 255.0F;
/* 22 */     this.particle = particle;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 28 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 30 */       double offsetX = WyHelper.randomDouble() / 1.5D;
/* 31 */       double offsetY = WyHelper.randomDouble() / 1.5D;
/* 32 */       double offsetZ = WyHelper.randomDouble() / 1.5D;
/*    */       
/* 34 */       GenericParticleData data = new GenericParticleData(this.particle);
/* 35 */       data.setLife(4);
/* 36 */       data.setSize(2.0F);
/* 37 */       data.setMotion(0.0D, 0.02D, 0.0D);
/* 38 */       data.setColor(this.red, this.green, this.blue);
/* 39 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\artofweather\ChargedWeatherBallParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */