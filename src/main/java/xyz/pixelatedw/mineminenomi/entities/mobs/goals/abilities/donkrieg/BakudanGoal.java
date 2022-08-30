/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.entities.BombEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BakudanGoal
/*    */   extends CooldownGoal {
/*    */   public BakudanGoal(DonKriegEntity entity, int timer, int random) {
/* 17 */     super((OPEntity)entity, timer, random);
/* 18 */     this.entity = entity;
/*    */   }
/*    */   
/*    */   private DonKriegEntity entity;
/*    */   
/*    */   public boolean shouldExecute() {
/* 24 */     boolean shouldExecute = super.shouldExecute();
/* 25 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 26 */     boolean hasLowHP = (this.entity.getHealth() <= this.entity.getMaxHealth() / 1.25D);
/* 27 */     boolean hasDifferentPhase = (this.entity.getPreviousGoal() != this);
/* 28 */     boolean hasChance = (Math.abs(WyHelper.randomDouble()) < 0.3D);
/* 29 */     boolean hasMovementUnblocked = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
/*    */     
/* 31 */     if (shouldExecute && hasTarget && hasLowHP && hasDifferentPhase && hasChance && hasMovementUnblocked) {
/* 32 */       return true;
/*    */     }
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 40 */     super.endCooldown();
/* 41 */     this.entity.setCurrentGoal(null);
/* 42 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 48 */     if (this.entity.getAttackTarget() == null) {
/*    */       
/* 50 */       this.entity.setCurrentGoal((Goal)this);
/* 51 */       setOnCooldown(true);
/* 52 */       this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
/*    */       
/*    */       return;
/*    */     } 
/* 56 */     for (int i = 0; i < 6; i++) {
/*    */       
/* 58 */       BombEntity bomb = new BombEntity(this.entity.world);
/* 59 */       BlockPos pos = WyHelper.findOnGroundSpawnLocation(this.entity.world, ModEntities.BOMB, this.entity.getAttackTarget().getPosition(), 15, 10);
/* 60 */       if (pos != null) {
/*    */         
/* 62 */         bomb.setPosition(pos.getX(), pos.getY(), pos.getZ());
/* 63 */         this.entity.world.addEntity((Entity)bomb);
/* 64 */         if (i > 4)
/* 65 */           this.entity.setAnimation(OPEntity.Animation.NONE.ordinal()); 
/*    */       } 
/*    */     } 
/* 68 */     this.entity.setCurrentGoal((Goal)this);
/* 69 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\donkrieg\BakudanGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */