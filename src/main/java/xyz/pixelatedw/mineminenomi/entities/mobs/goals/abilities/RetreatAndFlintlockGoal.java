/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RetreatAndFlintlockGoal extends CooldownGoal {
/* 15 */   private float animationTimer = 0.0F;
/*    */   
/*    */   private OPEntity entity;
/*    */   
/*    */   public RetreatAndFlintlockGoal(OPEntity entity, int timer, int random) {
/* 20 */     super(entity, timer, random);
/* 21 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 27 */     if (!super.shouldExecute()) {
/* 28 */       return false;
/*    */     }
/* 30 */     if (this.entity.getAttackTarget() == null) {
/* 31 */       return false;
/*    */     }
/* 33 */     if (this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED)) {
/* 34 */       return false;
/*    */     }
/* 36 */     float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
/* 37 */     if (distance < 3.0F || distance > 35.0F) {
/* 38 */       return false;
/*    */     }
/* 40 */     if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
/* 41 */       return false;
/*    */     }
/* 43 */     if (isOnCooldown()) {
/*    */       
/* 45 */       cooldownTick();
/* 46 */       return false;
/*    */     } 
/*    */     
/* 49 */     execute();
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 56 */     super.endCooldown();
/* 57 */     this.entity.setCurrentGoal(null);
/* 58 */     this.entity.setPreviousGoal((Goal)this);
/* 59 */     this.animationTimer = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 64 */     this.entity.setAnimation(OPEntity.Animation.FLINTLOCK_POINTING.ordinal());
/* 65 */     this.animationTimer += 0.05F;
/*    */     
/* 67 */     if (this.entity.getAttackTarget() == null) {
/*    */       
/* 69 */       this.entity.setCurrentGoal((Goal)this);
/* 70 */       setOnCooldown(true);
/* 71 */       this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
/*    */       
/*    */       return;
/*    */     } 
/* 75 */     if (this.animationTimer < 0.2F) {
/*    */       
/* 77 */       Vec3d speed = WyHelper.propulsion(this.entity.getAttackTarget(), 1.25D, 1.25D);
/* 78 */       this.entity.setMotion(speed.x, 0.1D, speed.z);
/*    */     }
/* 80 */     else if (this.animationTimer >= 1.4F) {
/*    */       
/* 82 */       NormalBulletProjectile proj = new NormalBulletProjectile(this.entity.world, (LivingEntity)this.entity);
/* 83 */       proj.setDamage(4.0F);
/*    */       
/* 85 */       LivingEntity target = this.entity.getAttackTarget();
/*    */       
/* 87 */       double velX = target.getPosX() - this.entity.getPosX();
/* 88 */       double velY = (target.getBoundingBox()).minY + (target.getHeight() / 3.0F) - proj.getPosY();
/* 89 */       double velZ = target.getPosZ() - this.entity.getPosZ();
/* 90 */       double x = MathHelper.sqrt(velX * velX + velZ * velZ);
/*    */       
/* 92 */       proj.shoot(velX, velY + x * 0.10000000149011612D, velZ, 1.6F, (15 - this.entity.world.getDifficulty().getId() * 4));
/* 93 */       this.entity.world.addEntity((Entity)proj);
/*    */       
/* 95 */       this.entity.setCurrentGoal((Goal)this);
/* 96 */       setOnCooldown(true);
/* 97 */       this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\RetreatAndFlintlockGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */