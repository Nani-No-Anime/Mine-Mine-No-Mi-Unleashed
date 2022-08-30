/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.math.RayTraceContext;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ReducedFallEffect
/*    */   extends Effect {
/*    */   public ReducedFallEffect() {
/* 16 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 21 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 27 */     Vec3d startVec = entity.getPositionVec();
/*    */     
/* 29 */     boolean blockUnder = entity.world.rayTraceBlocks(new RayTraceContext(startVec, startVec.add(0.0D, -3.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)entity)).getType().equals(RayTraceResult.Type.BLOCK);
/*    */     
/* 31 */     if ((entity.getMotion()).y < -1.0E-5D && !blockUnder) {
/*    */       
/* 33 */       entity.setMotion(entity.getMotion().mul(1.0D, 0.1D, 1.0D));
/* 34 */       entity.velocityChanged = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 47 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\ReducedFallEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */