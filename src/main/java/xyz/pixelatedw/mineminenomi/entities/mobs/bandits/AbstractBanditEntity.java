/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*    */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*    */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*    */ import net.minecraft.entity.ai.goal.OpenDoorGoal;
/*    */ import net.minecraft.entity.ai.goal.SwimGoal;
/*    */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*    */ import net.minecraft.entity.monster.MonsterEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.pathfinding.GroundPathNavigator;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedHurtByTargetGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public abstract class AbstractBanditEntity
/*    */   extends OPEntity {
/* 32 */   protected static final Item[] BANDIT_SWORDS = new Item[] { (Item)ModWeapons.BANDIT_KNIFE, (Item)ModWeapons.PIRATE_CUTLASS, Items.IRON_SWORD, Items.STONE_SWORD, Items.STONE_AXE, Items.IRON_AXE };
/*    */ 
/*    */   
/*    */   protected AbstractBanditEntity(EntityType<? extends MobEntity> type, World world) {
/* 36 */     this(type, world, (String[])null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected AbstractBanditEntity(EntityType<? extends MobEntity> type, World world, String[] textures) {
/* 41 */     super(type, world, textures);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 47 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 48 */     props.setFaction("bandit");
/*    */     
/* 50 */     ((GroundPathNavigator)getNavigator()).setBreakDoors(true);
/*    */     
/* 52 */     this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this));
/* 53 */     this.goalSelector.addGoal(0, (Goal)new OpenDoorGoal((MobEntity)this, true));
/* 54 */     this.goalSelector.addGoal(2, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/* 55 */     this.goalSelector.addGoal(3, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 56 */     this.goalSelector.addGoal(3, (Goal)new LookRandomlyGoal((MobEntity)this));
/*    */     
/* 58 */     Predicate<Entity> factionScope = FactionHelper.getOutsideGroupPredicate((LivingEntity)this);
/* 59 */     Predicate<Entity> invisibleEntity = entity -> (entity instanceof LivingEntity) ? (!((LivingEntity)entity).isPotionActive(Effects.INVISIBILITY)) : false;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 64 */     this.targetSelector.addGoal(1, (Goal)new ImprovedHurtByTargetGoal(this, factionScope, new Class[0]));
/* 65 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, OPEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
/* 66 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
/* 67 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerData() {
/* 73 */     super.registerData();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isDespawnPeaceful() {
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canDespawn(double distance) {
/* 85 */     if (distance > 1024.0D) {
/* 86 */       return true;
/*    */     }
/* 88 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\bandits\AbstractBanditEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */