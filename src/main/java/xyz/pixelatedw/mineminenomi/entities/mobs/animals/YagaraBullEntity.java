/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.TemptGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.IFluidState;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class YagaraBullEntity extends AnimalEntity implements IDynamicRenderer {
/*  54 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  55 */   private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.BOOLEAN);
/*  56 */   private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(YagaraBullEntity.class, DataSerializers.VARINT);
/*     */   
/*  58 */   private static final Item[] SADDLES = new Item[] { Items.SADDLE };
/*  59 */   private static final Item[] TAMING_FOOD = new Item[] { Items.TROPICAL_FISH };
/*  60 */   private static final Item[] FOOD = new Item[] { Items.COOKED_COD, Items.COOKED_SALMON, Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH };
/*     */   
/*  62 */   private String[] textures = new String[] { "yagara_bull1", "yagara_bull2", "yagara_bull3" };
/*     */   
/*     */   private double waterLevel;
/*     */   
/*     */   public YagaraBullEntity(World world) {
/*  67 */     super(ModEntities.YAGARA_BULL, world);
/*  68 */     this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this));
/*  69 */     this.goalSelector.addGoal(1, (Goal)new PanicGoal(this, 1.25D));
/*  70 */     this.goalSelector.addGoal(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  71 */     this.goalSelector.addGoal(2, (Goal)new LookRandomlyGoal((MobEntity)this));
/*  72 */     this.goalSelector.addGoal(3, (Goal)new TemptGoal(this, 1.25D, Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.TROPICAL_FISH } ), true));
/*  73 */     this.goalSelector.addGoal(4, (Goal)new RandomSwimmingGoal(this, 1.5D, 80));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  79 */     super.registerAttributes();
/*  80 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
/*  81 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.05000000074505806D);
/*  82 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
/*  83 */     getAttribute(LivingEntity.SWIM_SPEED).setBaseValue(8.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  89 */     super.registerData();
/*  90 */     getDataManager().register(OWNER, Optional.empty());
/*  91 */     getDataManager().register(IS_SADDLED, Boolean.valueOf(false));
/*  92 */     getDataManager().register(TEXTURE_ID, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  98 */     if (checkInWater()) {
/*     */       
/* 100 */       double d2 = (this.waterLevel - (getBoundingBox()).minY) / getHeight();
/* 101 */       setAir(30);
/*     */       
/* 103 */       if (areEyesInFluid(FluidTags.WATER) || this.world.getBlockState(getPosition().up()).getBlock() == Blocks.WATER)
/*     */       {
/* 105 */         d2 = 0.5D;
/*     */       }
/*     */       
/* 108 */       if (d2 > 0.0D) {
/*     */         
/* 110 */         Vec3d vec3d1 = getMotion();
/* 111 */         setMotion(vec3d1.x, (vec3d1.y + d2 * 0.02D) * 0.75D, vec3d1.z);
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     super.tick();
/*     */   }
/*     */   
/*     */   private boolean checkInWater() {
/*     */     boolean m = false;
/* 120 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 121 */     int i = MathHelper.floor(axisalignedbb.minX);
/* 122 */     int j = MathHelper.ceil(axisalignedbb.maxX);
/* 123 */     int k = MathHelper.floor(axisalignedbb.minY);
/* 124 */     int l = MathHelper.ceil(axisalignedbb.minY + 0.001D);
/* 125 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/* 126 */     int j1 = MathHelper.ceil(axisalignedbb.maxZ);
/* 127 */     boolean flag = false;
/* 128 */     this.waterLevel = Double.MIN_VALUE;
/*     */     
/* 130 */     BlockPos.PooledMutable blockpos$pooledmutableblockpos = BlockPos.PooledMutable.retain();
/*     */     
/* 132 */     try { for (int k1 = i; k1 < j; k1++) {
/*     */         
/* 134 */         for (int l1 = k; l1 < l; l1++) {
/*     */           
/* 136 */           for (int i2 = i1; i2 < j1; i2++) {
/*     */             
/* 138 */             blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
/* 139 */             IFluidState ifluidstate = this.world.getFluidState((BlockPos)blockpos$pooledmutableblockpos);
/* 140 */             if (ifluidstate.isTagged(FluidTags.WATER)) {
/*     */               
/* 142 */               float f = l1 + ifluidstate.getActualHeight((IBlockReader)this.world, (BlockPos)blockpos$pooledmutableblockpos);
/* 143 */               this.waterLevel = Math.max(f, this.waterLevel);
/* 144 */               m = flag | axisalignedbb.minY < f;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 149 */       if (blockpos$pooledmutableblockpos != null) blockpos$pooledmutableblockpos.close();  } catch (Throwable throwable) { if (blockpos$pooledmutableblockpos != null)
/*     */         try { blockpos$pooledmutableblockpos.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/* 151 */      return m;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 163 */     return 0.5D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeSteered() {
/* 169 */     return getControllingPassenger() instanceof LivingEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canFitPassenger(Entity passenger) {
/* 181 */     return (getPassengers().size() < 2 && !areEyesInFluid(FluidTags.WATER));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(BlockPos pos, IWorldReader world) {
/* 187 */     boolean flag1 = false;
/*     */     
/* 189 */     for (int i = 1; i < 3; i++) {
/*     */       
/* 191 */       BlockState state = world.getBlockState(pos.down(i));
/* 192 */       if (state.getMaterial() == Material.WATER) {
/*     */         
/* 194 */         flag1 = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 199 */     boolean flag2 = (pos.getY() < 150);
/* 200 */     float weight = (64 / pos.getY() * 10);
/* 201 */     return (flag1 && flag2) ? weight : 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawn(IWorld world, SpawnReason reason) {
/* 207 */     BlockPos pos = new BlockPos((Entity)this);
/* 208 */     float weight = getBlockPathWeight(pos, (IWorldReader)world);
/* 209 */     return (weight > 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mountTo(PlayerEntity player) {
/* 214 */     if (!this.world.isRemote) {
/*     */       
/* 216 */       player.rotationYaw = this.rotationYaw;
/* 217 */       player.rotationPitch = this.rotationPitch;
/* 218 */       player.startRiding((Entity)this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updatePassenger(Entity passenger) {
/* 225 */     super.updatePassenger(passenger);
/* 226 */     if (passenger instanceof MobEntity) {
/*     */       
/* 228 */       MobEntity mobentity = (MobEntity)passenger;
/* 229 */       this.renderYawOffset = mobentity.renderYawOffset;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getControllingPassenger() {
/* 237 */     return getPassengers().isEmpty() ? null : getPassengers().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 244 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/* 246 */     if (this.textures != null && this.textures.length > 0) {
/* 247 */       setTexture(this.rand.nextInt(this.textures.length));
/*     */     }
/* 249 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void travel(Vec3d vec) {
/* 255 */     if (isAlive())
/*     */     {
/* 257 */       if (isBeingRidden() && canBeSteered() && isSaddled()) {
/*     */         
/* 259 */         LivingEntity livingentity = (LivingEntity)getControllingPassenger();
/* 260 */         this.rotationYaw = livingentity.rotationYaw;
/* 261 */         this.prevRotationYaw = this.rotationYaw;
/* 262 */         this.rotationPitch = livingentity.rotationPitch * 0.5F;
/* 263 */         setRotation(this.rotationYaw, this.rotationPitch);
/* 264 */         this.renderYawOffset = this.rotationYaw;
/* 265 */         this.rotationYawHead = this.renderYawOffset;
/* 266 */         float f = livingentity.moveStrafing * 0.2F;
/* 267 */         float f1 = livingentity.moveForward;
/*     */         
/* 269 */         if (canPassengerSteer()) {
/*     */           
/* 271 */           setAIMoveSpeed((float)getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
/* 272 */           super.travel(new Vec3d(f, vec.y, f1));
/*     */         }
/* 274 */         else if (livingentity instanceof PlayerEntity) {
/*     */           
/* 276 */           setMotion(Vec3d.ZERO);
/*     */         } 
/*     */       } else {
/*     */         
/* 280 */         super.travel(vec);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean processInteract(PlayerEntity player, Hand hand) {
/* 288 */     if (player.world.isRemote) {
/* 289 */       return false;
/*     */     }
/*     */     
/* 292 */     if (isTamed() && player == getOwner() && hand == Hand.MAIN_HAND) {
/*     */       
/* 294 */       ItemStack stack = player.getHeldItemMainhand();
/*     */       
/* 296 */       if (!stack.isEmpty()) {
/*     */         
/* 298 */         Optional<Item> food = Arrays.<Item>stream(FOOD).filter(x -> (stack.getItem() == x)).findFirst();
/* 299 */         Optional<Item> saddle = Arrays.<Item>stream(SADDLES).filter(x -> (stack.getItem() == x)).findFirst();
/*     */         
/* 301 */         if (food.isPresent() && getHealth() < getMaxHealth()) {
/*     */           
/* 303 */           stack.shrink(1);
/* 304 */           heal(4.0F);
/* 305 */           spawnHeartParticles();
/* 306 */           return true;
/*     */         } 
/* 308 */         if (saddle.isPresent() && !isSaddled())
/*     */         {
/* 310 */           setSaddled(true);
/* 311 */           stack.shrink(1);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 316 */         mountTo(player);
/*     */       }
/*     */     
/* 319 */     } else if (!isTamed() && hand == Hand.MAIN_HAND) {
/*     */ 
/*     */       
/* 322 */       ItemStack stack = player.getHeldItemMainhand();
/*     */       
/* 324 */       if (!stack.isEmpty()) {
/*     */         
/* 326 */         Optional<Item> tropicalFish = Arrays.<Item>stream(TAMING_FOOD).filter(x -> (stack.getItem() == x)).findFirst();
/*     */         
/* 328 */         if (tropicalFish.isPresent()) {
/*     */           
/* 330 */           stack.shrink(1);
/* 331 */           double chance = this.rand.nextDouble();
/*     */           
/* 333 */           if (chance < 0.35D) {
/*     */             
/* 335 */             spawnHeartParticles();
/* 336 */             setOwner(player.getUniqueID());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 342 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 348 */     super.writeAdditional(compound);
/* 349 */     if (((Optional)this.dataManager.get(OWNER)).isPresent())
/* 350 */       compound.putString("owner", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString()); 
/* 351 */     compound.putBoolean("isSaddled", ((Boolean)this.dataManager.get(IS_SADDLED)).booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 357 */     super.readAdditional(compound);
/* 358 */     if (!WyHelper.isNullOrEmpty(compound.getString("owner")))
/* 359 */       this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("owner")))); 
/* 360 */     this.dataManager.set(IS_SADDLED, Boolean.valueOf(compound.getBoolean("isSaddled")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDespawn(double distance) {
/* 366 */     return !isTamed();
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawnHeartParticles() {
/* 371 */     for (int i = 0; i < 5; i++) {
/*     */       
/* 373 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 374 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 375 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */       
/* 377 */       ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.HEART, getPosX() + offsetX, getPosY() + 1.0D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSaddled(boolean flag) {
/* 383 */     this.dataManager.set(IS_SADDLED, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSaddled() {
/* 388 */     return ((Boolean)this.dataManager.get(IS_SADDLED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(UUID uuid) {
/* 393 */     this.dataManager.set(OWNER, Optional.of(uuid));
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getOwner() {
/* 398 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/* 403 */     return (getOwner() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextureId() {
/* 408 */     return ((Integer)getDataManager().get(TEXTURE_ID)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTexture(int texture) {
/* 413 */     getDataManager().set(TEXTURE_ID, Integer.valueOf(texture));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMobTexture() {
/* 419 */     return this.textures[getTextureId()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AgeableEntity createChild(AgeableEntity ageable) {
/* 425 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultTexture() {
/* 431 */     return this.textures[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\YagaraBullEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */