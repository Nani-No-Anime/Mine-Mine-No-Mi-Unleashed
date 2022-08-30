/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OpenDoorGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.GroundPathNavigator;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public abstract class AbstractPirateEntity extends OPEntity {
/*  39 */   protected static final Item[] PIRATE_SWORDS = new Item[] { (Item)ModWeapons.PIRATE_CUTLASS, Items.IRON_SWORD, Items.STONE_SWORD, Items.STONE_AXE, Items.IRON_AXE };
/*  40 */   private static final DataParameter<String> CREW = EntityDataManager.createKey(AbstractPirateEntity.class, DataSerializers.STRING);
/*     */ 
/*     */   
/*     */   protected AbstractPirateEntity(EntityType<? extends MobEntity> type, World world) {
/*  44 */     this(type, world, (String[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected AbstractPirateEntity(EntityType<? extends MobEntity> type, World world, String[] textures) {
/*  49 */     super(type, world, textures);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  55 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  56 */     props.setFaction("pirate");
/*  57 */     if (this.world.getRandom().nextInt(10) < 3) {
/*     */       
/*  59 */       props.setRace("fishman");
/*  60 */       queueEntityDataUpdate();
/*     */     } 
/*     */     
/*  63 */     ((GroundPathNavigator)getNavigator()).setBreakDoors(true);
/*     */     
/*  65 */     this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this));
/*  66 */     this.goalSelector.addGoal(0, (Goal)new OpenDoorGoal((MobEntity)this, true));
/*  67 */     this.goalSelector.addGoal(2, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  68 */     this.goalSelector.addGoal(3, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  69 */     this.goalSelector.addGoal(3, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  71 */     Predicate<Entity> factionScope = FactionHelper.getOutsideGroupPredicate((LivingEntity)this);
/*  72 */     Predicate<Entity> invisibleEntity = entity -> (entity instanceof LivingEntity) ? (!((LivingEntity)entity).isPotionActive(Effects.INVISIBILITY)) : false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.targetSelector.addGoal(1, (Goal)new ImprovedHurtByTargetGoal(this, factionScope, new Class[0]));
/*  78 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, OPEntity.class, 10, true, false, factionScope.and(invisibleEntity)));
/*  79 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, 10, true, false, factionScope.and(invisibleEntity)));
/*  80 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  86 */     super.registerData();
/*  87 */     getDataManager().register(CREW, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT nbt) {
/*  93 */     super.writeAdditional(nbt);
/*  94 */     nbt.putString("crew", getCrew());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT nbt) {
/* 100 */     super.readAdditional(nbt);
/* 101 */     setCrew(nbt.getString("crew"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDespawnPeaceful() {
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDespawn(double distance) {
/* 113 */     if (distance > 1024.0D) {
/* 114 */       return true;
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCrew() {
/* 121 */     return (String)getDataManager().get(CREW);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCrew(String crew) {
/* 126 */     getDataManager().set(CREW, crew);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropInventory() {
/* 132 */     super.dropInventory();
/* 133 */     if (getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModArmors.MH5_GAS_MASK && WyHelper.randomDouble() < 0.2D)
/* 134 */       entityDropItem((IItemProvider)ModArmors.MH5_GAS_MASK); 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\AbstractPirateEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */