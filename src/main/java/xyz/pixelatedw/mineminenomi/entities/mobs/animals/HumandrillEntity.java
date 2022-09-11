package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
import javax.annotation.Nullable;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.ai.ISwordsman;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GapCloserGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;

public class HumandrillEntity extends OPEntity implements ISwordsman {
  private static final DataParameter<Float> SIZE = EntityDataManager.createKey(HumandrillEntity.class, DataSerializers.FLOAT);
  private static final DataParameter<Boolean> ARMOR = EntityDataManager.createKey(HumandrillEntity.class, DataSerializers.BOOLEAN);
  
  protected static final Item[] SWORDS = new Item[] { (Item)ModWeapons.MARINE_SWORD, Items.IRON_SWORD, Items.STONE_SWORD, (Item)ModWeapons.SANDAI_KITETSU, (Item)ModWeapons.NIDAI_KITETSU, (Item)ModWeapons.WADO_ICHIMONJI, Items.DIAMOND_SWORD };
  AxisAlignedBB finalBox; protected void registerGoals() { super.registerGoals(); this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this)); this.goalSelector.addGoal(0, (Goal)new ImprovedMeleeAttackGoal(this, 1.25D, true)); this.goalSelector.addGoal(2, (Goal)new GapCloserGoal(this)); this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D)); this.goalSelector.addGoal(4, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F)); this.goalSelector.addGoal(4, (Goal)new LookRandomlyGoal((MobEntity)this)); this.targetSelector.addGoal(1, (Goal)new HurtByTargetGoal(this, new Class[0])); this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, true)); this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractPirateEntity.class, true)); this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractBanditEntity.class, true)); this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractMarineEntity.class, true)); this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, LapahnEntity.class, true));
    addSwordsmanAbilities(this, 2);
    setDoriki(20.0D + WyHelper.randomWithRange(0, 10) + getThreat());
    setBelly(30.0D + WyHelper.randomWithRange(0, 20)); } public HumandrillEntity(World world) { super(ModEntities.HUMANDRILL, world);



























































    
    this.finalBox = null; }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    if (this.rand.nextDouble() < 0.7D) {
      ItemStack randomSword;
      
      if (this.rand.nextDouble() < 0.9D) {
        randomSword = new ItemStack((IItemProvider)SWORDS[this.rand.nextInt(SWORDS.length)]);
      }
      else {
        
        getDataManager().set(SIZE, Float.valueOf(2.5F));
        getDataManager().set(ARMOR, Boolean.valueOf(true));
        randomSword = new ItemStack((IItemProvider)ModWeapons.YORU);
      } 
      randomSword.getOrCreateTag().putBoolean("isClone", true);
      setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
    } 
    
    return spawnData; } protected void registerAttributes() { super.registerAttributes(); getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(80.0D); getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.255D); getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((3.0F + ((getSize() > 1.0F) ? (2.5F * getSize()) : 0.0F))); getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Math.round(40.0F + ((getSize() > 1.0F) ? (8.0F * getSize()) : 0.0F))); getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue((1.2F + getSize() * 0.5F)); getAttribute(ModAttributes.FALL_RESISTANCE).setBaseValue(10.0D); if (((Boolean)getDataManager().get(ARMOR)).booleanValue()) {
      getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(WyHelper.randomWithRange(8, 12)); getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0D);
    } 
    setDoriki(15.0D + WyHelper.randomWithRange(3, (int)(6.0F + getSize() * 3.0F)));
    setBelly(0.0D); }
  public void tick() { super.tick(); }

  
  protected void registerData() {
    super.registerData();
    float size = 0.8F + this.rand.nextFloat();
    boolean armor = (this.rand.nextDouble() > 0.5D);
    getDataManager().register(SIZE, Float.valueOf(Math.min(size, 1.8F)));
    getDataManager().register(ARMOR, Boolean.valueOf(armor));
  }
  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putFloat("size", ((Float)this.dataManager.get(SIZE)).floatValue());
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.dataManager.set(SIZE, Float.valueOf(compound.getFloat("size")));
  }

  
  public float getSize() {
    return 1.0F;
  }
  
  protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {}
}


