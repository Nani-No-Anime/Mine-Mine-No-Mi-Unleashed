/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GunArrayGoal extends CooldownGoal {
/* 13 */   private float animationTimer = 0.0F;
/*    */   
/*    */   private DonKriegEntity entity;
/*    */   
/*    */   public GunArrayGoal(DonKriegEntity entity, int timer, int random) {
/* 18 */     super((OPEntity)entity, timer, random);
/* 19 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 25 */     boolean shouldExecute = super.shouldExecute();
/* 26 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 27 */     boolean hasHighHP = (this.entity.getHealth() > this.entity.getMaxHealth() / 2.0F);
/* 28 */     boolean canSeeTarget = (hasTarget && this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget()));
/* 29 */     boolean hasDifferentPhase = (this.entity.getPreviousGoal() != this);
/* 30 */     boolean hasChance = (Math.abs(WyHelper.randomDouble()) < 0.5D);
/* 31 */     boolean hasMovementUnblocked = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
/*    */     
/* 33 */     if (shouldExecute && hasHighHP && canSeeTarget && hasDifferentPhase && hasChance && hasMovementUnblocked) {
/*    */       
/* 35 */       execute();
/* 36 */       return true;
/*    */     } 
/*    */     
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 45 */     super.endCooldown();
/* 46 */     this.entity.setCurrentGoal(null);
/* 47 */     this.entity.setPreviousGoal((Goal)this);
/* 48 */     this.animationTimer = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 53 */     this.entity.setAnimation(120);
/* 54 */     this.animationTimer += 0.05F;
/*    */     
/* 56 */     if (this.entity.getAttackTarget() == null) {
/*    */       
/* 58 */       this.entity.setCurrentGoal((Goal)this);
/* 59 */       setOnCooldown(true);
/* 60 */       this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
/*    */       
/*    */       return;
/*    */     } 
/* 64 */     if (this.animationTimer >= 1.4F) {
/*    */       
/* 66 */       for (int i = 0; i < 6; i++) {
/*    */         
/* 68 */         NormalBulletProjectile proj = new NormalBulletProjectile(this.entity.world, (LivingEntity)this.entity);
/* 69 */         proj.setDamage(4.0F);
/*    */         
/* 71 */         LivingEntity target = this.entity.getAttackTarget();
/*    */         
/* 73 */         double velX = target.getPosX() - this.entity.getPosX();
/* 74 */         double velY = (target.getBoundingBox()).minY + (target.getHeight() / 3.0F) - proj.getPosY();
/* 75 */         double velZ = target.getPosZ() - this.entity.getPosZ();
/* 76 */         double x = MathHelper.sqrt(velX * velX + velZ * velZ);
/*    */         
/* 78 */         proj.shoot(velX, velY + x * 0.10000000149011612D, velZ, 1.6F, (15 - this.entity.world.getDifficulty().getId() * 4));
/* 79 */         this.entity.world.addEntity((Entity)proj);
/*    */       } 
/*    */       
/* 82 */       this.entity.setCurrentGoal((Goal)this);
/* 83 */       setOnCooldown(true);
/* 84 */       this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\donkrieg\GunArrayGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */