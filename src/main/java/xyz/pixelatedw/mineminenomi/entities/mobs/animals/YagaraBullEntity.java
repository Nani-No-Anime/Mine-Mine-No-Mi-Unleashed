package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class YagaraBullEntity extends AnimalEntity implements IDynamicRenderer {
  private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
  private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.VARINT);
  
  private static final Item[] SADDLES = new Item[] { Items.SADDLE };
  private static final Item[] TAMING_FOOD = new Item[] { Items.TROPICAL_FISH };
  private static final Item[] FOOD = new Item[] { Items.COOKED_COD, Items.COOKED_SALMON, Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH };
  
  private String[] textures = new String[] { "yagara_bull1", "yagara_bull2", "yagara_bull3" };
  
  private double waterLevel;
  
  public YagaraBullEntity(World world) {
    super(ModEntities.YAGARA_BULL, world);
    this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this));
    this.goalSelector.addGoal(1, (Goal)new PanicGoal(this, 1.25D));
    this.goalSelector.addGoal(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(2, (Goal)new LookRandomlyGoal((MobEntity)this));
    this.goalSelector.addGoal(3, (Goal)new TemptGoal(this, 1.25D, Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.TROPICAL_FISH } ), true));
    this.goalSelector.addGoal(4, (Goal)new RandomSwimmingGoal(this, 1.5D, 80));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.05000000074505806D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(8.0D);
  }


  
  protected void registerData() {
    super.registerData();
    getDataManager().register(OWNER, Optional.empty());
    getDataManager().register(IS_SADDLED, Boolean.valueOf(false));
    getDataManager().register(TEXTURE_ID, Integer.valueOf(0));
  }


  
  public void tick() {
    if (checkInWater()) {
      
      double d2 = (this.waterLevel - (getBoundingBox()).minY) / getHeight();
      setAir(30);
      
      if (areEyesInFluid(FluidTags.WATER) || this.world.getBlockState(getPosition().up()).getBlock() == Blocks.WATER)
      {
        d2 = 0.5D;
      }
      
      if (d2 > 0.0D) {
        
        Vec3d vec3d1 = getMotion();
        setMotion(vec3d1.x, (vec3d1.y + d2 * 0.02D) * 0.75D, vec3d1.z);
      } 
    } 
    
    super.tick();
  }
  
  private boolean checkInWater() {
    boolean m = false;
    AxisAlignedBB axisalignedbb = getBoundingBox();
    int i = MathHelper.floor(axisalignedbb.minX);
    int j = MathHelper.ceil(axisalignedbb.maxX);
    int k = MathHelper.floor(axisalignedbb.minY);
    int l = MathHelper.ceil(axisalignedbb.minY + 0.001D);
    int i1 = MathHelper.floor(axisalignedbb.minZ);
    int j1 = MathHelper.ceil(axisalignedbb.maxZ);
    boolean flag = false;
    this.waterLevel = Double.MIN_VALUE;
    
    BlockPos.PooledMutable blockpos$pooledmutableblockpos = BlockPos.PooledMutable.retain();
    
    try { for (int k1 = i; k1 < j; k1++) {
        
        for (int l1 = k; l1 < l; l1++) {
          
          for (int i2 = i1; i2 < j1; i2++) {
            
            blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
            IFluidState ifluidstate = this.world.getFluidState((BlockPos)blockpos$pooledmutableblockpos);
            if (ifluidstate.isTagged(FluidTags.WATER)) {
              
              float f = l1 + ifluidstate.getActualHeight((IBlockReader)this.world, (BlockPos)blockpos$pooledmutableblockpos);
              this.waterLevel = Math.max(f, this.waterLevel);
              m = flag | axisalignedbb.minY < f;
            } 
          } 
        } 
      } 
      if (blockpos$pooledmutableblockpos != null) blockpos$pooledmutableblockpos.close();  } catch (Throwable throwable) { if (blockpos$pooledmutableblockpos != null)
        try { blockpos$pooledmutableblockpos.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
     return m;
  }


  
  public boolean canBePushed() {
    return true;
  }


  
  public double getMountedYOffset() {
    return 0.5D;
  }


  
  public boolean canBeSteered() {
    return getControllingPassenger() instanceof LivingEntity;
  }


  
  protected boolean canTriggerWalking() {
    return false;
  }


  
  protected boolean canFitPassenger(Entity passenger) {
    return (getPassengers().size() < 2 && !areEyesInFluid(FluidTags.WATER));
  }


  
  public float getBlockPathWeight(BlockPos pos, IWorldReader world) {
    boolean flag1 = false;
    
    for (int i = 1; i < 3; i++) {
      
      BlockState state = world.getBlockState(pos.down(i));
      if (state.getMaterial() == Material.WATER) {
        
        flag1 = true;
        
        break;
      } 
    } 
    boolean flag2 = (pos.getY() < 150);
    float weight = (64 / pos.getY() * 10);
    return (flag1 && flag2) ? weight : 0.0F;
  }


  
  public boolean canSpawn(IWorld world, SpawnReason reason) {
    BlockPos pos = new BlockPos((Entity)this);
    float weight = getBlockPathWeight(pos, (IWorldReader)world);
    return (weight > 0.0F);
  }

  
  protected void mountTo(PlayerEntity player) {
    if (!this.world.isRemote) {
      
      player.rotationYaw = this.rotationYaw;
      player.rotationPitch = this.rotationPitch;
      player.startRiding((Entity)this);
    } 
  }


  
  public void updatePassenger(Entity passenger) {
    super.updatePassenger(passenger);
    if (passenger instanceof MobEntity) {
      
      MobEntity mobentity = (MobEntity)passenger;
      this.renderYawOffset = mobentity.renderYawOffset;
    } 
  }


  
  @Nullable
  public Entity getControllingPassenger() {
    return getPassengers().isEmpty() ? null : getPassengers().get(0);
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    if (this.textures != null && this.textures.length > 0) {
      setTexture(this.rand.nextInt(this.textures.length));
    }
    return spawnData;
  }


  
  public void travel(Vec3d vec) {
    if (isAlive())
    {
      if (isBeingRidden() && canBeSteered() && isSaddled()) {
        
        LivingEntity livingentity = (LivingEntity)getControllingPassenger();
        this.rotationYaw = livingentity.rotationYaw;
        this.prevRotationYaw = this.rotationYaw;
        this.rotationPitch = livingentity.rotationPitch * 0.5F;
        setRotation(this.rotationYaw, this.rotationPitch);
        this.renderYawOffset = this.rotationYaw;
        this.rotationYawHead = this.renderYawOffset;
        float f = livingentity.moveStrafing * 0.2F;
        float f1 = livingentity.moveForward;
        
        if (canPassengerSteer()) {
          
          setAIMoveSpeed((float)getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
          super.travel(new Vec3d(f, vec.y, f1));
        }
        else if (livingentity instanceof PlayerEntity) {
          
          setMotion(Vec3d.ZERO);
        } 
      } else {
        
        super.travel(vec);
      } 
    }
  }


  
  public boolean processInteract(PlayerEntity player, Hand hand) {
    if (player.world.isRemote) {
      return false;
    }
    
    if (isTamed() && player == getOwner() && hand == Hand.MAIN_HAND) {
      
      ItemStack stack = player.getHeldItemMainhand();
      
      if (!stack.isEmpty()) {
        
        Optional<Item> food = Arrays.<Item>stream(FOOD).filter(x -> (stack.getItem() == x)).findFirst();
        Optional<Item> saddle = Arrays.<Item>stream(SADDLES).filter(x -> (stack.getItem() == x)).findFirst();
        
        if (food.isPresent() && getHealth() < getMaxHealth()) {
          
          stack.shrink(1);
          heal(4.0F);
          spawnHeartParticles();
          return true;
        } 
        if (saddle.isPresent() && !isSaddled())
        {
          setSaddled(true);
          stack.shrink(1);
        }
      
      } else {
        
        mountTo(player);
      }
    
    } else if (!isTamed() && hand == Hand.MAIN_HAND) {

      
      ItemStack stack = player.getHeldItemMainhand();
      
      if (!stack.isEmpty()) {
        
        Optional<Item> tropicalFish = Arrays.<Item>stream(TAMING_FOOD).filter(x -> (stack.getItem() == x)).findFirst();
        
        if (tropicalFish.isPresent()) {
          
          stack.shrink(1);
          double chance = this.rand.nextDouble();
          
          if (chance < 0.35D) {
            
            spawnHeartParticles();
            setOwner(player.getUniqueID());
          } 
        } 
      } 
    } 
    
    return false;
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    if (((Optional)this.dataManager.get(OWNER)).isPresent())
      compound.putString("owner", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString()); 
    compound.putBoolean("isSaddled", ((Boolean)this.dataManager.get(IS_SADDLED)).booleanValue());
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    if (!WyHelper.isNullOrEmpty(compound.getString("owner")))
      this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("owner")))); 
    this.dataManager.set(IS_SADDLED, Boolean.valueOf(compound.getBoolean("isSaddled")));
  }


  
  public boolean canDespawn(double distance) {
    return !isTamed();
  }

  
  public void spawnHeartParticles() {
    for (int i = 0; i < 5; i++) {
      
      double offsetX = WyHelper.randomDouble() / 2.0D;
      double offsetY = WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble() / 2.0D;
      
      ((ServerWorld)this.world).spawnParticle(ParticleTypes.HEART, getPosX() + offsetX, getPosY() + 1.0D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
    } 
  }

  
  public void setSaddled(boolean flag) {
    this.dataManager.set(IS_SADDLED, Boolean.valueOf(flag));
  }

  
  public boolean isSaddled() {
    return ((Boolean)this.dataManager.get(IS_SADDLED)).booleanValue();
  }

  
  public void setOwner(UUID uuid) {
    this.dataManager.set(OWNER, Optional.of(uuid));
  }

  
  public PlayerEntity getOwner() {
    return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
  }

  
  public boolean isTamed() {
    return (getOwner() != null);
  }

  
  public int getTextureId() {
    return ((Integer)getDataManager().get(TEXTURE_ID)).intValue();
  }

  
  protected void setTexture(int texture) {
    getDataManager().set(TEXTURE_ID, Integer.valueOf(texture));
  }


  
  public String getMobTexture() {
    return this.textures[getTextureId()];
  }


  
  public AgeableEntity createChild(AgeableEntity ageable) {
    return null;
  }


  
  public String getDefaultTexture() {
    return this.textures[0];
  }
}


