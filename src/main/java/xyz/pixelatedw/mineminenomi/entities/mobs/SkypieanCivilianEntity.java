/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*    */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*    */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*    */ import net.minecraft.entity.ai.goal.OpenDoorGoal;
/*    */ import net.minecraft.entity.ai.goal.SwimGoal;
/*    */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class SkypieanCivilianEntity extends OPEntity {
/* 19 */   private static final String[] DEFAULT_TEXTURES = new String[] { "skypiean_civilian1", "skypiean_civilian2", "skypiean_civilian3" };
/*    */ 
/*    */   
/*    */   public SkypieanCivilianEntity(World world) {
/* 23 */     super(ModEntities.SKYPIEAN_CIVILIAN, world, DEFAULT_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 29 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 30 */     props.setFaction("civilian");
/*    */     
/* 32 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/* 33 */     this.goalSelector.addGoal(2, (Goal)new OpenDoorGoal((MobEntity)this, false));
/* 34 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/* 35 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 36 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*    */     
/* 38 */     this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 44 */     super.registerAttributes();
/* 45 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
/* 46 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/* 47 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/* 48 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/* 49 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\SkypieanCivilianEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */