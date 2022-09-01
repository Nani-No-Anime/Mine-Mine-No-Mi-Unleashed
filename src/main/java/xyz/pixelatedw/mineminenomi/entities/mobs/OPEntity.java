package xyz.pixelatedw.mineminenomi.entities.mobs;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtensibleEnum;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public abstract class OPEntity
  extends CreatureEntity implements IDynamicRenderer {
  protected String[] textures;
  protected boolean needsEntityDataUpdate;
  private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(OPEntity.class, DataSerializers.STRING);
  private static final DataParameter<Boolean> HAS_BUSOSHOKU_HAKI_ACTIVE = EntityDataManager.createKey(OPEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> HAS_FULLBODY_HAKI_ACTIVE = EntityDataManager.createKey(OPEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Integer> ANIMATION = EntityDataManager.createKey(OPEntity.class, DataSerializers.VARINT);
  private static final AttributeModifier GENERIC_BUSO_ATTACK_MULTIPLIER = (new AttributeModifier(UUID.fromString("41120ad1-c457-44d5-ac4c-896e83e1333f"), "Generic Buso Haki Strength Multiplier", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
  private static final AttributeModifier FULLBODY_BUSO_ATTACK_MULTIPLIER = (new AttributeModifier(UUID.fromString("5d4e0e3d-2580-47c0-bbcf-19c4ee04eb48"), "Fullbody Buso Haki Strength Multiplier", 0.4D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
  private static final AttributeModifier FULLBODY_BUSO_DEFENSE_MULTIPLIER = (new AttributeModifier(UUID.fromString("49252c18-dcd6-4b3f-9fbc-df1962f999ab"), "Fullbody Buso Haki Defense Multiplier", 0.4D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
  private int doriki;
  protected int threat = 2;
  private int belly;
  private Goal currentGoal;
  private Goal previousGoal;
  private boolean isFishman;
  
  public OPEntity(EntityType type, World world) {
    this(type, world, (String[])null);
  }

  
  public OPEntity(EntityType type, World world, String[] textures) {
    super(type, world);
    this.experienceValue = this.threat;
    this.textures = textures;
    chooseTexture();
    this.isFishman = EntityStatsCapability.get((LivingEntity)this).isFishman();
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
  }


  
  protected void registerData() {
    super.registerData();
    getDataManager().register(TEXTURE, "");
    getDataManager().register(ANIMATION, Integer.valueOf(0));
    getDataManager().register(HAS_BUSOSHOKU_HAKI_ACTIVE, Boolean.valueOf(false));
    getDataManager().register(HAS_FULLBODY_HAKI_ACTIVE, Boolean.valueOf(false));
  }


  
  public void writeAdditional(CompoundNBT nbt) {
    super.writeAdditional(nbt);
    nbt.putInt("doriki", this.doriki);
    nbt.putInt("belly", this.belly);
    nbt.putInt("threat", this.threat);
    nbt.putInt("animation", getAnimation());
    
    nbt.putString("texture", getMobTexture());
    
    nbt.putBoolean("hasBusoHaki", hasBusoHaki());
    nbt.putBoolean("hasFullbodyHaki", hasFullbodyHaki());
  }


  
  public void readAdditional(CompoundNBT nbt) {
    super.readAdditional(nbt);
    this.doriki = nbt.getInt("doriki");
    this.belly = nbt.getInt("belly");
    this.threat = nbt.getInt("threat");
    setAnimation(nbt.getInt("animation"));
    
    setTexture(nbt.getString("texture"));
    
    setBusoHaki(nbt.getBoolean("hasBusoHaki"));
    setFullbodyHaki(nbt.getBoolean("hasFullbodyHaki"));
  }


  
  public String getDefaultTexture() {
    if (this.textures != null)
      return this.textures[0]; 
    return "null";
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    setLeftHanded(false);
    
    if (WyHelper.isNullOrEmpty(getMobTexture())) {
      setTexture(getDefaultTexture());
    }
    return spawnData;
  }


  
  protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {}

  
  public float getBlockPathWeight(BlockPos pos, IWorldReader world) {
    Block aboveBlock = world.getBlockState(pos.up()).getBlock();
    Block belowBlock = world.getBlockState(pos.down()).getBlock();
    boolean flag1 = !world.getBlockState(pos).isAir((IBlockReader)world, pos);
    boolean flag2 = (pos.getY() < 150);
    boolean isOutside = (aboveBlock.getBlock() == Blocks.AIR);
    boolean flag3 = (belowBlock.getBlock() == Blocks.GRASS_BLOCK || belowBlock.getBlock() == Blocks.SAND || belowBlock.getBlock() == Blocks.STONE);
    float weight = (64 / pos.getY() * 10);
    return (flag1 && flag2 && flag3 && isOutside) ? weight : 0.0F;
  }


  
  public boolean canSpawn(IWorld world, SpawnReason reason) {
    return (getBlockPathWeight(new BlockPos((Entity)this), (IWorldReader)world) > 0.0F);
  }


  
  public boolean canDespawn(double distance) {
    if (hasCustomName() && !CommonConfig.INSTANCE.getDespawnWithNametag()) {
      return false;
    }
    return true;
  }

  
  protected boolean shouldDrown() {
    return !this.isFishman;
  }


  
  public String getMobTexture() {
    return (String)getDataManager().get(TEXTURE);
  }

  
  protected void setTexture(String id) {
    getDataManager().set(TEXTURE, id);
  }

  
  protected void chooseTexture() {
    if (this.textures != null && this.textures.length > 0) {
      
      int id = this.rand.nextInt(this.textures.length);
      setTexture(this.textures[id]);
    } 
  }

  
  public int getDoriki() {
    return this.doriki;
  }

  
  public void setDoriki(double value) {
    this.doriki = (int)Math.floor(value);
  }

  
  public int getBelly() {
    return this.belly;
  }

  
  public void setBelly(double value) {
    this.belly = (int)Math.floor(value);
  }

  
  public int getAnimation() {
    return ((Integer)this.dataManager.get(ANIMATION)).intValue();
  }

  
  public void setAnimation(int value) {
    this.dataManager.set(ANIMATION, Integer.valueOf(value));
  }

  
  public boolean hasBusoHaki() {
    return ((Boolean)this.dataManager.get(HAS_BUSOSHOKU_HAKI_ACTIVE)).booleanValue();
  }

  
  public void setBusoHaki(boolean value) {
    this.dataManager.set(HAS_BUSOSHOKU_HAKI_ACTIVE, Boolean.valueOf(value));
  }

  
  public boolean hasFullbodyHaki() {
    return ((Boolean)this.dataManager.get(HAS_FULLBODY_HAKI_ACTIVE)).booleanValue();
  }

  
  public void setFullbodyHaki(boolean value) {
    this.dataManager.set(HAS_FULLBODY_HAKI_ACTIVE, Boolean.valueOf(value));
  }

  
  public Goal getCurrentGoal() {
    return this.currentGoal;
  }

  
  public Goal getPreviousGoal() {
    return this.previousGoal;
  }

  
  public void setCurrentGoal(Goal goal) {
    this.currentGoal = goal;
  }

  
  public void setPreviousGoal(Goal goal) {
    this.previousGoal = goal;
  }

  
  public void addThreat(int threat) {
    this.threat += threat;
  }

  
  public int getThreat() {
    return this.threat;
  }

  
  public void setThreat(int threat) {
    this.threat = threat;
  }

  
  public void queueEntityDataUpdate() {
    this.needsEntityDataUpdate = true;
  }


  
  public void tick() {
    super.tick();
    
    if (!this.world.isRemote) {
      
      if (hasBusoHaki()) {
        
        if (!getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(GENERIC_BUSO_ATTACK_MULTIPLIER)) {
          getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(GENERIC_BUSO_ATTACK_MULTIPLIER);
        
        }
      }
      else if (getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(GENERIC_BUSO_ATTACK_MULTIPLIER)) {
        getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(GENERIC_BUSO_ATTACK_MULTIPLIER);
      } 
      
      if (hasFullbodyHaki()) {
        
        if (!getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(FULLBODY_BUSO_ATTACK_MULTIPLIER))
          getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(FULLBODY_BUSO_ATTACK_MULTIPLIER); 
        if (!getAttribute(SharedMonsterAttributes.ARMOR).hasModifier(FULLBODY_BUSO_DEFENSE_MULTIPLIER)) {
          getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(FULLBODY_BUSO_DEFENSE_MULTIPLIER);
        }
      } else {
        
        if (getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(FULLBODY_BUSO_ATTACK_MULTIPLIER))
          getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(FULLBODY_BUSO_ATTACK_MULTIPLIER); 
        if (getAttribute(SharedMonsterAttributes.ARMOR).hasModifier(FULLBODY_BUSO_DEFENSE_MULTIPLIER)) {
          getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(FULLBODY_BUSO_DEFENSE_MULTIPLIER);
        }
      } 
      if (getRidingEntity() instanceof net.minecraft.entity.item.BoatEntity && getAttackTarget() != null)
      {
        stopRiding();
      }
      
      if (this.needsEntityDataUpdate) {
        
        IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
        WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(getEntityId(), props), (LivingEntity)this);
        this.needsEntityDataUpdate = false;
      } 
    } 
  }
  
  public enum Animation
    implements IExtensibleEnum {
    NONE(0),
    FLINTLOCK_POINTING(1),
    CLEAVE_ATTACK(2),
    SHOCKWAVE(3),
    BLOCK(4);
    
    private int id;

    
    Animation(int id) {
      this.id = id;
    }

    
    public int getId() {
      return this.id;
    }

    
    public static Animation create(String name, int id) {
      throw new IllegalStateException("Enum not extended");
    }
  }
}
