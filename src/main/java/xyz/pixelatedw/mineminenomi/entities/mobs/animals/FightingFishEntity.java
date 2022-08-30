/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.CreatureAttribute;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityPredicate;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.controller.DolphinLookController;
/*     */ import net.minecraft.entity.ai.controller.LookController;
/*     */ import net.minecraft.entity.ai.controller.MovementController;
/*     */ import net.minecraft.entity.ai.goal.FindWaterGoal;
/*     */ import net.minecraft.entity.ai.goal.FollowBoatGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
/*     */ import net.minecraft.entity.passive.WaterMobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.BreakBoatGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ 
/*     */ public class FightingFishEntity extends WaterMobEntity {
/*  46 */   private static final DataParameter<Float> SIZE = EntityDataManager.createKey(FightingFishEntity.class, DataSerializers.FLOAT);
/*  47 */   public static final EntityPredicate TARGET_PREDICATE = (new EntityPredicate()).setDistance(20.0D).allowFriendlyFire().allowInvulnerable().setLineOfSiteRequired();
/*     */ 
/*     */   
/*     */   public FightingFishEntity(World world) {
/*  51 */     super(ModEntities.FIGHTING_FISH, world);
/*     */     
/*  53 */     this.moveController = new MoveHelperController(this);
/*  54 */     this.lookController = (LookController)new DolphinLookController((MobEntity)this, 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  60 */     this.goalSelector.addGoal(0, (Goal)new FindWaterGoal(this));
/*  61 */     this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.7999999523162842D, true));
/*  62 */     this.goalSelector.addGoal(2, (Goal)new RandomSwimmingGoal(this, 1.0D, 10));
/*  63 */     this.goalSelector.addGoal(2, (Goal)new LookRandomlyGoal((MobEntity)this));
/*  64 */     this.goalSelector.addGoal(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 12.0F));
/*  65 */     this.goalSelector.addGoal(4, (Goal)new FollowBoatGoal(this));
/*  66 */     this.goalSelector.addGoal(5, (Goal)new BreakBoatGoal(this));
/*     */     
/*  68 */     this.targetSelector.addGoal(1, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*  69 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, true));
/*  70 */     this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, YagaraBullEntity.class, 10, true, true, living -> !(living instanceof FightingFishEntity)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  76 */     super.registerAttributes();
/*  77 */     getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  78 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(55.0D);
/*  79 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2000000476837158D);
/*  80 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Math.ceil((7.0F * getSize())));
/*  81 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Math.ceil(60.0D * getSize()));
/*  82 */     getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue((-8.0F * getSize()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  88 */     super.registerData();
/*  89 */     float size = 0.2F + this.rand.nextFloat();
/*  90 */     getDataManager().register(SIZE, Float.valueOf(Math.min(size, 1.0F)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
/*  97 */     setAir(getMaxAir());
/*  98 */     this.rotationPitch = 0.0F;
/*  99 */     ILivingEntityData data = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
/* 100 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 106 */     super.writeAdditional(compound);
/* 107 */     compound.putFloat("size", ((Float)this.dataManager.get(SIZE)).floatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntitySize getSize(Pose pose) {
/* 113 */     float fishScale = getSize();
/* 114 */     EntitySize newSize = getType().getSize().scale(fishScale);
/* 115 */     return newSize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 121 */     super.readAdditional(compound);
/* 122 */     this.dataManager.set(SIZE, Float.valueOf(compound.getFloat("size")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyDataManagerChange(DataParameter<?> key) {
/* 128 */     if (SIZE.equals(key)) {
/* 129 */       recalculateSize();
/*     */     }
/* 131 */     super.notifyDataManagerChange(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 136 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(BlockPos pos, IWorldReader world) {
/* 142 */     boolean flag1 = false;
/*     */     
/* 144 */     for (int i = 1; i < 3; i++) {
/*     */       
/* 146 */       BlockState state = world.getBlockState(pos.down(i));
/* 147 */       if (state.getMaterial() == Material.WATER) {
/*     */         
/* 149 */         flag1 = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 154 */     boolean flag2 = (pos.getY() < 150);
/* 155 */     float weight = (64 / Math.max(pos.getY(), 1) * 10);
/* 156 */     return (flag1 && flag2) ? weight : 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawn(IWorld world, SpawnReason reason) {
/* 162 */     BlockPos pos = new BlockPos((Entity)this);
/* 163 */     float weight = getBlockPathWeight(pos, (IWorldReader)world);
/* 164 */     return (weight > 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVerticalFaceSpeed() {
/* 170 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHorizontalFaceSpeed() {
/* 176 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CreatureAttribute getCreatureAttribute() {
/* 188 */     return CreatureAttribute.WATER;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void baseTick() {
/* 194 */     int i = getAir();
/* 195 */     super.baseTick();
/* 196 */     updateAir(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeLeashedTo(PlayerEntity player) {
/* 202 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected PathNavigator createNavigator(World worldIn) {
/* 208 */     return (PathNavigator)new SwimmerPathNavigator((MobEntity)this, worldIn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void travel(Vec3d positionIn) {
/* 214 */     if (isServerWorld() && isInWater()) {
/*     */       
/* 216 */       moveRelative(getAIMoveSpeed(), positionIn);
/* 217 */       move(MoverType.SELF, getMotion());
/* 218 */       setMotion(getMotion().scale(0.9D));
/* 219 */       if (getAttackTarget() == null) {
/* 220 */         setMotion(getMotion().add(0.0D, -0.005D, 0.0D));
/*     */       } else {
/* 222 */         setMotion(getMotion().add(0.0D, -0.01D, 0.0D));
/*     */       } 
/*     */     } else {
/*     */       
/* 226 */       super.travel(positionIn);
/*     */     } 
/*     */   }
/*     */   
/*     */   static class MoveHelperController
/*     */     extends MovementController
/*     */   {
/*     */     private final FightingFishEntity fish;
/*     */     
/*     */     public MoveHelperController(FightingFishEntity fish) {
/* 236 */       super((MobEntity)fish);
/* 237 */       this.fish = fish;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void tick() {
/* 243 */       if (this.fish.isInWater())
/*     */       {
/* 245 */         this.fish.setMotion(this.fish.getMotion().add(0.0D, 0.005D, 0.0D));
/*     */       }
/*     */       
/* 248 */       if (this.action == MovementController.Action.MOVE_TO && !this.fish.getNavigator().noPath()) {
/*     */         
/* 250 */         double d0 = this.posX - this.fish.getPosX();
/* 251 */         double d1 = this.posY - this.fish.getPosY();
/* 252 */         double d2 = this.posZ - this.fish.getPosZ();
/* 253 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 254 */         if (d3 < 2.500000277905201E-7D) {
/*     */           
/* 256 */           this.mob.setMoveForward(0.0F);
/*     */         }
/*     */         else {
/*     */           
/* 260 */           float f = (float)(MathHelper.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
/* 261 */           this.fish.rotationYaw = limitAngle(this.fish.rotationYaw, f, 10.0F);
/* 262 */           this.fish.renderYawOffset = this.fish.rotationYaw;
/* 263 */           this.fish.rotationYawHead = this.fish.rotationYaw;
/* 264 */           float f1 = (float)(this.speed * this.fish.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
/* 265 */           if (this.fish.isInWater())
/*     */           {
/* 267 */             this.fish.setAIMoveSpeed(f1 * 0.02F);
/* 268 */             float f2 = -((float)(MathHelper.atan2(d1, MathHelper.sqrt(d0 * d0 + d2 * d2)) * 57.2957763671875D));
/* 269 */             f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
/* 270 */             this.fish.rotationPitch = limitAngle(this.fish.rotationPitch, f2, 5.0F);
/* 271 */             float f3 = MathHelper.cos(this.fish.rotationPitch * 0.017453292F);
/* 272 */             float f4 = MathHelper.sin(this.fish.rotationPitch * 0.017453292F);
/* 273 */             this.fish.moveForward = f3 * f1;
/* 274 */             this.fish.moveVertical = -f4 * f1;
/*     */           }
/*     */           else
/*     */           {
/* 278 */             this.fish.setAIMoveSpeed(f1 * 0.1F);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 284 */         this.fish.setAIMoveSpeed(0.0F);
/* 285 */         this.fish.setMoveStrafing(0.0F);
/* 286 */         this.fish.setMoveVertical(0.0F);
/* 287 */         this.fish.setMoveForward(0.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\FightingFishEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */