/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.common;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GroundParticlesEffect
/*    */   extends ParticleEffect {
/*    */   private int offset;
/*    */   private int amount;
/*    */   
/*    */   public GroundParticlesEffect(int offset, int amount) {
/* 19 */     this.offset = offset;
/* 20 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 26 */     for (int i = 0; i < this.amount; i++) {
/*    */       
/* 28 */       double offsetX = WyHelper.randomWithRange(-this.offset, this.offset) + WyHelper.randomDouble();
/* 29 */       double offsetZ = WyHelper.randomWithRange(-this.offset, this.offset) + WyHelper.randomDouble();
/*    */       
/* 31 */       for (int j = 0; j < 2; j++) {
/*    */         
/* 33 */         BlockState blockState = world.getBlockState((new BlockPos(posX + offsetX, posY - j, posZ + offsetZ)).down());
/* 34 */         ((ServerWorld)world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + offsetX, posY, posZ + offsetZ, 1, 0.0D, 0.5D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\common\GroundParticlesEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */