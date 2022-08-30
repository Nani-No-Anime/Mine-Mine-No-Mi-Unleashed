/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.block.AnvilBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.FallingBlock;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.item.DirectionalPlaceContext;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ 
/*     */ public class TaktBlockEntity
/*     */   extends Entity {
/*     */   public int fallTime;
/*     */   public boolean shouldDropItem = true;
/*     */   private boolean dontSetBlock;
/*     */   private boolean hurtEntities;
/*  52 */   private int fallHurtMax = 40;
/*  53 */   private float fallHurtAmount = 2.0F;
/*     */   public CompoundNBT tileEntityData;
/*  55 */   protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(TaktBlockEntity.class, DataSerializers.BLOCK_POS);
/*  56 */   protected static final DataParameter<Optional<BlockState>> BLOCK_STATE = EntityDataManager.createKey(TaktBlockEntity.class, DataSerializers.OPTIONAL_BLOCK_STATE);
/*     */ 
/*     */   
/*     */   public TaktBlockEntity(World world) {
/*  60 */     super(OpeProjectiles.TAKT_BLOCK, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public TaktBlockEntity(World world, double x, double y, double z, BlockState fallingBlockState) {
/*  65 */     super(OpeProjectiles.TAKT_BLOCK, world);
/*  66 */     setBlockState(fallingBlockState);
/*  67 */     this.preventEntitySpawning = true;
/*  68 */     setPosition(x, y + ((1.0F - getHeight()) / 2.0F), z);
/*  69 */     setMotion(Vec3d.ZERO);
/*  70 */     this.prevPosX = x;
/*  71 */     this.prevPosY = y;
/*  72 */     this.prevPosZ = z;
/*  73 */     setOrigin(new BlockPos(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeAttackedWithItem() {
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrigin(BlockPos originPos) {
/*  87 */     this.dataManager.set(ORIGIN, originPos);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public BlockPos getOrigin() {
/*  93 */     return (BlockPos)this.dataManager.get(ORIGIN);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/* 105 */     this.dataManager.register(ORIGIN, BlockPos.ZERO);
/* 106 */     this.dataManager.register(BLOCK_STATE, Optional.of(Blocks.SAND.getDefaultState()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 115 */     return !this.removed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 124 */     if (getBlockState().isAir()) {
/*     */       
/* 126 */       remove();
/*     */     }
/*     */     else {
/*     */       
/* 130 */       Block block = getBlockState().getBlock();
/* 131 */       if (this.fallTime++ == 0) {
/*     */         
/* 133 */         BlockPos blockpos = new BlockPos(this);
/* 134 */         if (this.world.getBlockState(blockpos).getBlock() == block) {
/*     */           
/* 136 */           this.world.removeBlock(blockpos, false);
/*     */         }
/* 138 */         else if (!this.world.isRemote) {
/*     */           
/* 140 */           remove();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 145 */       if (!hasNoGravity())
/*     */       {
/* 147 */         setMotion(getMotion().add(0.0D, -0.04D, 0.0D));
/*     */       }
/*     */       
/* 150 */       move(MoverType.SELF, getMotion());
/* 151 */       if (!this.world.isRemote) {
/*     */         
/* 153 */         BlockPos blockpos1 = new BlockPos(this);
/* 154 */         boolean flag = getBlockState().getBlock() instanceof net.minecraft.block.ConcretePowderBlock;
/* 155 */         boolean flag1 = (flag && this.world.getFluidState(blockpos1).isTagged(FluidTags.WATER));
/* 156 */         double d0 = getMotion().lengthSquared();
/* 157 */         if (flag && d0 > 1.0D) {
/*     */           
/* 159 */           BlockRayTraceResult blockraytraceresult = this.world.rayTraceBlocks(new RayTraceContext(new Vec3d(this.prevPosX, this.prevPosY, this.prevPosZ), getPositionVec(), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.SOURCE_ONLY, this));
/* 160 */           if (blockraytraceresult.getType() != RayTraceResult.Type.MISS && this.world.getFluidState(blockraytraceresult.getPos()).isTagged(FluidTags.WATER)) {
/*     */             
/* 162 */             blockpos1 = blockraytraceresult.getPos();
/* 163 */             flag1 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 167 */         if (!this.onGround && !flag1) {
/*     */           
/* 169 */           if (!this.world.isRemote && ((this.fallTime > 100 && (blockpos1.getY() < 1 || blockpos1.getY() > 256)) || this.fallTime > 600))
/*     */           {
/* 171 */             if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS))
/*     */             {
/* 173 */               entityDropItem((IItemProvider)block);
/*     */             }
/*     */             
/* 176 */             remove();
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 181 */           BlockState blockstate = this.world.getBlockState(blockpos1);
/* 182 */           setMotion(getMotion().mul(0.7D, -0.5D, 0.7D));
/* 183 */           if (blockstate.getBlock() != Blocks.MOVING_PISTON) {
/*     */             
/* 185 */             remove();
/* 186 */             if (!this.dontSetBlock) {
/*     */               
/* 188 */               boolean flag2 = blockstate.isReplaceable((BlockItemUseContext)new DirectionalPlaceContext(this.world, blockpos1, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
/* 189 */               boolean flag3 = (FallingBlock.canFallThrough(this.world.getBlockState(blockpos1.down())) && (!flag || !flag1));
/* 190 */               boolean flag4 = (getBlockState().isValidPosition((IWorldReader)this.world, blockpos1) && !flag3);
/* 191 */               if (flag2 && flag4) {
/*     */                 
/* 193 */                 if (getBlockState().has((IProperty)BlockStateProperties.WATERLOGGED) && this.world.getFluidState(blockpos1).getFluid() == Fluids.WATER)
/*     */                 {
/* 195 */                   setBlockState((BlockState)getBlockState().with((IProperty)BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)));
/*     */                 }
/*     */                 
/* 198 */                 if (this.world.setBlockState(blockpos1, getBlockState(), 3)) {
/*     */                   
/* 200 */                   if (block instanceof FallingBlock)
/*     */                   {
/* 202 */                     ((FallingBlock)block).onEndFalling(this.world, blockpos1, getBlockState(), blockstate);
/*     */                   }
/*     */                   
/* 205 */                   if (this.tileEntityData != null && getBlockState().hasTileEntity()) {
/*     */                     
/* 207 */                     TileEntity tileentity = this.world.getTileEntity(blockpos1);
/* 208 */                     if (tileentity != null)
/*     */                     {
/* 210 */                       CompoundNBT compoundnbt = tileentity.write(new CompoundNBT());
/*     */                       
/* 212 */                       for (String s : this.tileEntityData.keySet()) {
/*     */                         
/* 214 */                         INBT inbt = this.tileEntityData.get(s);
/* 215 */                         if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s))
/*     */                         {
/* 217 */                           compoundnbt.put(s, inbt.copy());
/*     */                         }
/*     */                       } 
/*     */                       
/* 221 */                       tileentity.read(compoundnbt);
/* 222 */                       tileentity.markDirty();
/*     */                     }
/*     */                   
/*     */                   } 
/* 226 */                 } else if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/*     */                   
/* 228 */                   entityDropItem((IItemProvider)block);
/*     */                 }
/*     */               
/* 231 */               } else if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/*     */                 
/* 233 */                 entityDropItem((IItemProvider)block);
/*     */               }
/*     */             
/* 236 */             } else if (block instanceof FallingBlock) {
/*     */               
/* 238 */               ((FallingBlock)block).onBroken(this.world, blockpos1);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 244 */       setMotion(getMotion().scale(0.98D));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onLivingFall(float distance, float damageMultiplier) {
/* 251 */     if (this.hurtEntities) {
/*     */       
/* 253 */       int i = MathHelper.ceil(distance - 1.0F);
/* 254 */       if (i > 0) {
/*     */         
/* 256 */         List<Entity> list = Lists.newArrayList(this.world.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox()));
/* 257 */         boolean flag = getBlockState().isIn(BlockTags.ANVIL);
/* 258 */         DamageSource damagesource = flag ? DamageSource.ANVIL : DamageSource.FALLING_BLOCK;
/*     */         
/* 260 */         for (Entity entity : list)
/*     */         {
/* 262 */           entity.attackEntityFrom(damagesource, Math.min(MathHelper.floor(i * this.fallHurtAmount), this.fallHurtMax));
/*     */         }
/*     */         
/* 265 */         if (flag && this.rand.nextFloat() < 0.05000000074505806D + i * 0.05D) {
/*     */           
/* 267 */           BlockState blockstate = AnvilBlock.damage(getBlockState());
/* 268 */           if (blockstate == null) {
/*     */             
/* 270 */             this.dontSetBlock = true;
/*     */           }
/*     */           else {
/*     */             
/* 274 */             setBlockState(blockstate);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 280 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeAdditional(CompoundNBT compound) {
/* 286 */     compound.put("BlockState", (INBT)NBTUtil.writeBlockState(getBlockState()));
/* 287 */     compound.putInt("Time", this.fallTime);
/* 288 */     compound.putBoolean("DropItem", this.shouldDropItem);
/* 289 */     compound.putBoolean("HurtEntities", this.hurtEntities);
/* 290 */     compound.putFloat("FallHurtAmount", this.fallHurtAmount);
/* 291 */     compound.putInt("FallHurtMax", this.fallHurtMax);
/* 292 */     if (this.tileEntityData != null)
/*     */     {
/* 294 */       compound.put("TileEntityData", (INBT)this.tileEntityData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditional(CompoundNBT compound) {
/* 305 */     setBlockState(NBTUtil.readBlockState(compound.getCompound("BlockState")));
/* 306 */     this.fallTime = compound.getInt("Time");
/* 307 */     if (compound.contains("HurtEntities", 99)) {
/*     */       
/* 309 */       this.hurtEntities = compound.getBoolean("HurtEntities");
/* 310 */       this.fallHurtAmount = compound.getFloat("FallHurtAmount");
/* 311 */       this.fallHurtMax = compound.getInt("FallHurtMax");
/*     */     }
/* 313 */     else if (getBlockState().isIn(BlockTags.ANVIL)) {
/*     */       
/* 315 */       this.hurtEntities = true;
/*     */     } 
/*     */     
/* 318 */     if (compound.contains("DropItem", 99))
/*     */     {
/* 320 */       this.shouldDropItem = compound.getBoolean("DropItem");
/*     */     }
/*     */     
/* 323 */     if (compound.contains("TileEntityData", 10))
/*     */     {
/* 325 */       this.tileEntityData = compound.getCompound("TileEntityData");
/*     */     }
/*     */     
/* 328 */     if (getBlockState().isAir())
/*     */     {
/* 330 */       setBlockState(Blocks.SAND.getDefaultState());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public World getWorldObj() {
/* 337 */     return this.world;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHurtEntities(boolean hurtEntitiesIn) {
/* 342 */     this.hurtEntities = hurtEntitiesIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean canRenderOnFire() {
/* 352 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillCrashReport(CrashReportCategory category) {
/* 358 */     super.fillCrashReport(category);
/* 359 */     category.addDetail("Immitating BlockState", getBlockState().toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockState(BlockState state) {
/* 364 */     getDataManager().set(BLOCK_STATE, Optional.of(state));
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState getBlockState() {
/* 369 */     if (((Optional)getDataManager().get(BLOCK_STATE)).isPresent()) {
/* 370 */       return ((Optional<BlockState>)getDataManager().get(BLOCK_STATE)).get();
/*     */     }
/* 372 */     return Blocks.AIR.getDefaultState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreItemEntityData() {
/* 391 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/* 397 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ope\TaktBlockEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */