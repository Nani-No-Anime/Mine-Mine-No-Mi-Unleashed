/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*    */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*    */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*    */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*    */ import net.minecraft.entity.ai.goal.OpenDoorGoal;
/*    */ import net.minecraft.entity.ai.goal.SwimGoal;
/*    */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.pathfinding.GroundPathNavigator;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
/*    */ 
/*    */ public abstract class AbstractKriegPirateEntity
/*    */   extends AbstractPirateEntity {
/*    */   static {
/* 24 */     NON_KRIEG_PIRATE = (target -> (target instanceof AbstractPirateEntity) ? (!((AbstractPirateEntity)target).getCrew().equalsIgnoreCase("Krieg Pirates")) : ((target instanceof PlayerEntity) ? true : ((target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static final Predicate<LivingEntity> NON_KRIEG_PIRATE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractKriegPirateEntity(EntityType<? extends MobEntity> type, World world) {
/* 38 */     super(type, world, null);
/*    */     
/* 40 */     setCrew("Krieg Pirates");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 46 */     ((GroundPathNavigator)getNavigator()).setBreakDoors(true);
/*    */     
/* 48 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/* 49 */     this.goalSelector.addGoal(2, (Goal)new OpenDoorGoal((MobEntity)this, false));
/* 50 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/* 51 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 52 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*    */     
/* 54 */     this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/* 55 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, LivingEntity.class, 10, true, true, NON_KRIEG_PIRATE));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\kriegpirates\AbstractKriegPirateEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */