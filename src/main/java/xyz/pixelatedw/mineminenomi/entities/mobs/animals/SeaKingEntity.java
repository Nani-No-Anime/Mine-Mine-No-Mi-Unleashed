/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*    */ 
/*    */ import net.minecraft.entity.AgeableEntity;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.goal.BreatheAirGoal;
/*    */ import net.minecraft.entity.ai.goal.FindWaterGoal;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*    */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*    */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*    */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*    */ import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
/*    */ import net.minecraft.entity.passive.AnimalEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class SeaKingEntity extends AnimalEntity {
/*    */   public SeaKingEntity(World world) {
/* 22 */     super(ModEntities.SEA_KING, world);
/* 23 */     this.goalSelector.addGoal(0, (Goal)new FindWaterGoal(this));
/* 24 */     this.goalSelector.addGoal(1, (Goal)new RandomSwimmingGoal(this, 1.0D, 10));
/* 25 */     this.goalSelector.addGoal(3, (Goal)new MeleeAttackGoal(this, 1.0D, false));
/* 26 */     this.goalSelector.addGoal(4, (Goal)new BreatheAirGoal(this));
/* 27 */     this.goalSelector.addGoal(6, (Goal)new LookRandomlyGoal((MobEntity)this));
/*    */     
/* 29 */     this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/* 30 */     this.targetSelector.addGoal(0, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, false));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 36 */     super.registerAttributes();
/* 37 */     getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16.0D);
/* 38 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(80.0D);
/* 39 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
/* 40 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBreatheUnderwater() {
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSwim() {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AgeableEntity createChild(AgeableEntity ageable) {
/* 57 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\SeaKingEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */