/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class GapCloserGoal extends Goal {
/*    */   private OPEntity entity;
/*    */   private int hitCount;
/*    */   private int maxCount;
/*    */   private float prevHealth;
/*    */   private double speed;
/*    */   
/*    */   public GapCloserGoal(OPEntity entity) {
/* 18 */     this(entity, 1.7D, 3);
/*    */   }
/*    */ 
/*    */   
/*    */   public GapCloserGoal(OPEntity entity, double speed, int hitCount) {
/* 23 */     this.entity = entity;
/* 24 */     this.speed = speed;
/* 25 */     this.maxCount = hitCount;
/* 26 */     this.prevHealth = this.entity.getHealth();
/* 27 */     this.entity.addThreat(1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 33 */     if (this.entity.getAttackTarget() == null) {
/* 34 */       return false;
/*    */     }
/* 36 */     if (this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED)) {
/* 37 */       return false;
/*    */     }
/* 39 */     if (this.entity.getHealth() < this.prevHealth) {
/*    */       
/* 41 */       this.hitCount++;
/* 42 */       this.prevHealth = this.entity.getHealth();
/*    */     } 
/*    */     
/* 45 */     float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
/* 46 */     if (distance > 14.0F && distance < 2.0F) {
/* 47 */       return false;
/*    */     }
/* 49 */     if (this.hitCount < this.maxCount) {
/* 50 */       return false;
/*    */     }
/* 52 */     execute();
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 58 */     double mX = (-MathHelper.sin(this.entity.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.entity.rotationPitch / 180.0F * 3.1415927F)) * 0.4D;
/* 59 */     double mZ = (MathHelper.cos(this.entity.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.entity.rotationPitch / 180.0F * 3.1415927F)) * 0.4D;
/*    */     
/* 61 */     double f2 = MathHelper.sqrt(mX * mX + (this.entity.getMotion()).y * (this.entity.getMotion()).y + mZ * mZ);
/* 62 */     mX /= f2;
/* 63 */     mZ /= f2;
/* 64 */     mX += this.entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0D;
/* 65 */     mZ += this.entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0D;
/* 66 */     mX *= this.speed;
/* 67 */     mZ *= this.speed;
/*    */     
/* 69 */     this.entity.setMotion(new Vec3d(mX, 0.3D, mZ));
/*    */     
/* 71 */     this.hitCount = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\GapCloserGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */