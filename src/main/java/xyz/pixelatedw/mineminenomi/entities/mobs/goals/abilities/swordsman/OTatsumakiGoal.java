/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class OTatsumakiGoal
/*    */   extends CooldownGoal {
/*    */   private OPEntity entity;
/*    */   private double damage;
/*    */   
/*    */   public OTatsumakiGoal(OPEntity entity) {
/* 23 */     super(entity, 80, entity.getRNG().nextInt(20));
/* 24 */     this.entity = entity;
/* 25 */     this.entity.addThreat(5);
/* 26 */     this.damage = this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 32 */     if (!super.shouldExecute()) {
/* 33 */       return false;
/*    */     }
/* 35 */     ItemStack itemStack = this.entity.getHeldItemMainhand();
/*    */     
/* 37 */     if (itemStack == null || itemStack.isEmpty() || this.entity.getAttackTarget() == null) {
/* 38 */       return false;
/*    */     }
/* 40 */     if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
/* 41 */       return false;
/*    */     }
/* 43 */     if (this.entity.getDistance((Entity)this.entity.getAttackTarget()) > 3.0F) {
/* 44 */       return false;
/*    */     }
/* 46 */     execute();
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 53 */     super.endCooldown();
/* 54 */     this.entity.setCurrentGoal(null);
/* 55 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 60 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 4.0D);
/* 61 */     targets.remove(this.entity);
/* 62 */     for (LivingEntity target : targets) {
/*    */       
/* 64 */       target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), (float)this.damage);
/* 65 */       target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0, false, true));
/*    */     } 
/*    */     
/* 68 */     OTatsumakiAbility.PARTICLES.spawn(this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 70 */     this.entity.setCurrentGoal((Goal)this);
/* 71 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\swordsman\OTatsumakiGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */