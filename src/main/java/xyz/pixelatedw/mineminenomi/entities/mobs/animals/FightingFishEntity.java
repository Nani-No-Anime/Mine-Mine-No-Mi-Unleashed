package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.BreakBoatGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

import javax.annotation.Nullable;

public class FightingFishEntity extends WaterMobEntity {
  private static final DataParameter<Float> SIZE = EntityDataManager.createKey(FightingFishEntity.class, DataSerializers.FLOAT);
  public static final EntityPredicate TARGET_PREDICATE = (new EntityPredicate()).setDistance(20.0D).allowFriendlyFire().allowInvulnerable().setLineOfSiteRequired();

  
  public FightingFishEntity(World world) {
    super(ModEntities.FIGHTING_FISH, world);
    
    this.moveController = new MoveHelperController(this);
    this.lookController = (LookController)new DolphinLookController(this, 10);
  }


  
  protected void registerGoals() {
    this.goalSelector.addGoal(0, (Goal)new FindWaterGoal(this));
    this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.7999999523162842D, true));
    this.goalSelector.addGoal(2, (Goal)new RandomSwimmingGoal(this, 1.0D, 10));
    this.goalSelector.addGoal(2, (Goal)new LookRandomlyGoal(this));
    this.goalSelector.addGoal(2, (Goal)new LookAtGoal(this, PlayerEntity.class, 12.0F));
    this.goalSelector.addGoal(4, (Goal)new FollowBoatGoal(this));
    this.goalSelector.addGoal(5, (Goal)new BreakBoatGoal(this));
    
    this.targetSelector.addGoal(1, (Goal)new HurtByTargetGoal(this, new Class[0]));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
    this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal(this, YagaraBullEntity.class, 10, true, true, living -> !(living instanceof FightingFishEntity)));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(55.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2000000476837158D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Math.ceil((7.0F * getSize())));
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Math.ceil(60.0D * getSize()));
    getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue((-8.0F * getSize()));
  }


  
  protected void registerData() {
    super.registerData();
    float size = 0.2F + this.rand.nextFloat();
    getDataManager().register(SIZE, Float.valueOf(Math.min(size, 1.0F)));
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
    setAir(getMaxAir());
    this.rotationPitch = 0.0F;
    ILivingEntityData data = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    return data;
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putFloat("size", ((Float)this.dataManager.get(SIZE)).floatValue());
  }


  
  public EntitySize getSize(Pose pose) {
    float fishScale = getSize();
    EntitySize newSize = getType().getSize().scale(fishScale);
    return newSize;
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.dataManager.set(SIZE, Float.valueOf(compound.getFloat("size")));
  }


  
  public void notifyDataManagerChange(DataParameter<?> key) {
    if (SIZE.equals(key)) {
      recalculateSize();
    }
    super.notifyDataManagerChange(key);
  }

  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
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
    float weight = (64 / Math.max(pos.getY(), 1) * 10);
    return (flag1 && flag2) ? weight : 0.0F;
  }


  
  public boolean canSpawn(IWorld world, SpawnReason reason) {
    BlockPos pos = new BlockPos((Entity)this);
    float weight = getBlockPathWeight(pos, (IWorldReader)world);
    return (weight > 0.0F);
  }


  
  public int getVerticalFaceSpeed() {
    return 1;
  }


  
  public int getHorizontalFaceSpeed() {
    return 1;
  }


  
  public boolean canBreatheUnderwater() {
    return true;
  }


  
  public CreatureAttribute getCreatureAttribute() {
    return CreatureAttribute.WATER;
  }


  
  public void baseTick() {
    int i = getAir();
    super.baseTick();
    updateAir(i);
  }


  
  public boolean canBeLeashedTo(PlayerEntity player) {
    return true;
  }


  
  protected PathNavigator createNavigator(World worldIn) {
    return (PathNavigator)new SwimmerPathNavigator(this, worldIn);
  }


  
  public void travel(Vec3d positionIn) {
    if (isServerWorld() && isInWater()) {
      
      moveRelative(getAIMoveSpeed(), positionIn);
      move(MoverType.SELF, getMotion());
      setMotion(getMotion().scale(0.9D));
      if (getAttackTarget() == null) {
        setMotion(getMotion().add(0.0D, -0.005D, 0.0D));
      } else {
        setMotion(getMotion().add(0.0D, -0.01D, 0.0D));
      } 
    } else {
      
      super.travel(positionIn);
    } 
  }
  
  static class MoveHelperController
    extends MovementController
  {
    private final FightingFishEntity fish;
    
    public MoveHelperController(FightingFishEntity fish) {
      super(fish);
      this.fish = fish;
    }


    
    public void tick() {
      if (this.fish.isInWater())
      {
        this.fish.setMotion(this.fish.getMotion().add(0.0D, 0.005D, 0.0D));
      }
      
      if (this.action == MovementController.Action.MOVE_TO && !this.fish.getNavigator().noPath()) {
        
        double d0 = this.posX - this.fish.getPosX();
        double d1 = this.posY - this.fish.getPosY();
        double d2 = this.posZ - this.fish.getPosZ();
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        if (d3 < 2.500000277905201E-7D) {
          
          this.mob.setMoveForward(0.0F);
        }
        else {
          
          float f = (float)(MathHelper.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
          this.fish.rotationYaw = limitAngle(this.fish.rotationYaw, f, 10.0F);
          this.fish.renderYawOffset = this.fish.rotationYaw;
          this.fish.rotationYawHead = this.fish.rotationYaw;
          float f1 = (float)(this.speed * this.fish.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
          if (this.fish.isInWater())
          {
            this.fish.setAIMoveSpeed(f1 * 0.02F);
            float f2 = -((float)(MathHelper.atan2(d1, MathHelper.sqrt(d0 * d0 + d2 * d2)) * 57.2957763671875D));
            f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
            this.fish.rotationPitch = limitAngle(this.fish.rotationPitch, f2, 5.0F);
            float f3 = MathHelper.cos(this.fish.rotationPitch * 0.017453292F);
            float f4 = MathHelper.sin(this.fish.rotationPitch * 0.017453292F);
            this.fish.moveForward = f3 * f1;
            this.fish.moveVertical = -f4 * f1;
          }
          else
          {
            this.fish.setAIMoveSpeed(f1 * 0.1F);
          }
        
        } 
      } else {
        
        this.fish.setAIMoveSpeed(0.0F);
        this.fish.setMoveStrafing(0.0F);
        this.fish.setMoveVertical(0.0F);
        this.fish.setMoveForward(0.0F);
      } 
    }
  }
}


