package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;

import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DirectionalPlaceContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.*;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;
import java.util.Optional;

public class TaktBlockEntity
  extends Entity {
  public int fallTime;
  public boolean shouldDropItem = true;
  private boolean dontSetBlock;
  private boolean hurtEntities;
  private int fallHurtMax = 40;
  private float fallHurtAmount = 2.0F;
  public CompoundNBT tileEntityData;
  protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(TaktBlockEntity.class, DataSerializers.BLOCK_POS);
  protected static final DataParameter<Optional<BlockState>> BLOCK_STATE = EntityDataManager.createKey(TaktBlockEntity.class, DataSerializers.OPTIONAL_BLOCK_STATE);

  
  public TaktBlockEntity(World world) {
    super(OpeProjectiles.TAKT_BLOCK, world);
  }

  
  public TaktBlockEntity(World world, double x, double y, double z, BlockState fallingBlockState) {
    super(OpeProjectiles.TAKT_BLOCK, world);
    setBlockState(fallingBlockState);
    this.preventEntitySpawning = true;
    setPosition(x, y + ((1.0F - getHeight()) / 2.0F), z);
    setMotion(Vec3d.ZERO);
    this.prevPosX = x;
    this.prevPosY = y;
    this.prevPosZ = z;
    setOrigin(new BlockPos(this));
  }





  
  public boolean canBeAttackedWithItem() {
    return false;
  }

  
  public void setOrigin(BlockPos originPos) {
    this.dataManager.set(ORIGIN, originPos);
  }

  
  @OnlyIn(Dist.CLIENT)
  public BlockPos getOrigin() {
    return (BlockPos)this.dataManager.get(ORIGIN);
  }


  
  protected boolean canTriggerWalking() {
    return false;
  }


  
  protected void registerData() {
    this.dataManager.register(ORIGIN, BlockPos.ZERO);
    this.dataManager.register(BLOCK_STATE, Optional.of(Blocks.SAND.getDefaultState()));
  }





  
  public boolean canBeCollidedWith() {
    return !this.removed;
  }





  
  public void tick() {
    if (getBlockState().isAir()) {
      
      remove();
    }
    else {
      
      Block block = getBlockState().getBlock();
      if (this.fallTime++ == 0) {
        
        BlockPos blockpos = new BlockPos(this);
        if (this.world.getBlockState(blockpos).getBlock() == block) {
          
          this.world.removeBlock(blockpos, false);
        }
        else if (!this.world.isRemote) {
          
          remove();
          
          return;
        } 
      } 
      if (!hasNoGravity())
      {
        setMotion(getMotion().add(0.0D, -0.04D, 0.0D));
      }
      
      move(MoverType.SELF, getMotion());
      if (!this.world.isRemote) {
        
        BlockPos blockpos1 = new BlockPos(this);
        boolean flag = getBlockState().getBlock() instanceof net.minecraft.block.ConcretePowderBlock;
        boolean flag1 = (flag && this.world.getFluidState(blockpos1).isTagged(FluidTags.WATER));
        double d0 = getMotion().lengthSquared();
        if (flag && d0 > 1.0D) {
          
          BlockRayTraceResult blockraytraceresult = this.world.rayTraceBlocks(new RayTraceContext(new Vec3d(this.prevPosX, this.prevPosY, this.prevPosZ), getPositionVec(), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.SOURCE_ONLY, this));
          if (blockraytraceresult.getType() != RayTraceResult.Type.MISS && this.world.getFluidState(blockraytraceresult.getPos()).isTagged(FluidTags.WATER)) {
            
            blockpos1 = blockraytraceresult.getPos();
            flag1 = true;
          } 
        } 
        
        if (!this.onGround && !flag1) {
          
          if (!this.world.isRemote && ((this.fallTime > 100 && (blockpos1.getY() < 1 || blockpos1.getY() > 256)) || this.fallTime > 600))
          {
            if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS))
            {
              entityDropItem((IItemProvider)block);
            }
            
            remove();
          }
        
        } else {
          
          BlockState blockstate = this.world.getBlockState(blockpos1);
          setMotion(getMotion().mul(0.7D, -0.5D, 0.7D));
          if (blockstate.getBlock() != Blocks.MOVING_PISTON) {
            
            remove();
            if (!this.dontSetBlock) {
              
              boolean flag2 = blockstate.isReplaceable((BlockItemUseContext)new DirectionalPlaceContext(this.world, blockpos1, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
              boolean flag3 = (FallingBlock.canFallThrough(this.world.getBlockState(blockpos1.down())) && (!flag || !flag1));
              boolean flag4 = (getBlockState().isValidPosition((IWorldReader)this.world, blockpos1) && !flag3);
              if (flag2 && flag4) {
                
                if (getBlockState().has((IProperty)BlockStateProperties.WATERLOGGED) && this.world.getFluidState(blockpos1).getFluid() == Fluids.WATER)
                {
                  setBlockState((BlockState)getBlockState().with((IProperty)BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)));
                }
                
                if (this.world.setBlockState(blockpos1, getBlockState(), 3)) {
                  
                  if (block instanceof FallingBlock)
                  {
                    ((FallingBlock)block).onEndFalling(this.world, blockpos1, getBlockState(), blockstate);
                  }
                  
                  if (this.tileEntityData != null && getBlockState().hasTileEntity()) {
                    
                    TileEntity tileentity = this.world.getTileEntity(blockpos1);
                    if (tileentity != null)
                    {
                      CompoundNBT compoundnbt = tileentity.write(new CompoundNBT());
                      
                      for (String s : this.tileEntityData.keySet()) {
                        
                        INBT inbt = this.tileEntityData.get(s);
                        if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s))
                        {
                          compoundnbt.put(s, inbt.copy());
                        }
                      } 
                      
                      tileentity.read(compoundnbt);
                      tileentity.markDirty();
                    }
                  
                  } 
                } else if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                  
                  entityDropItem((IItemProvider)block);
                }
              
              } else if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                
                entityDropItem((IItemProvider)block);
              }
            
            } else if (block instanceof FallingBlock) {
              
              ((FallingBlock)block).onBroken(this.world, blockpos1);
            } 
          } 
        } 
      } 
      
      setMotion(getMotion().scale(0.98D));
    } 
  }


  
  public boolean onLivingFall(float distance, float damageMultiplier) {
    if (this.hurtEntities) {
      
      int i = MathHelper.ceil(distance - 1.0F);
      if (i > 0) {
        
        List<Entity> list = Lists.newArrayList(this.world.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox()));
        boolean flag = getBlockState().isIn(BlockTags.ANVIL);
        DamageSource damagesource = flag ? DamageSource.ANVIL : DamageSource.FALLING_BLOCK;
        
        for (Entity entity : list)
        {
          entity.attackEntityFrom(damagesource, Math.min(MathHelper.floor(i * this.fallHurtAmount), this.fallHurtMax));
        }
        
        if (flag && this.rand.nextFloat() < 0.05000000074505806D + i * 0.05D) {
          
          BlockState blockstate = AnvilBlock.damage(getBlockState());
          if (blockstate == null) {
            
            this.dontSetBlock = true;
          }
          else {
            
            setBlockState(blockstate);
          } 
        } 
      } 
    } 
    
    return false;
  }


  
  protected void writeAdditional(CompoundNBT compound) {
    compound.put("BlockState", (INBT)NBTUtil.writeBlockState(getBlockState()));
    compound.putInt("Time", this.fallTime);
    compound.putBoolean("DropItem", this.shouldDropItem);
    compound.putBoolean("HurtEntities", this.hurtEntities);
    compound.putFloat("FallHurtAmount", this.fallHurtAmount);
    compound.putInt("FallHurtMax", this.fallHurtMax);
    if (this.tileEntityData != null)
    {
      compound.put("TileEntityData", (INBT)this.tileEntityData);
    }
  }






  
  protected void readAdditional(CompoundNBT compound) {
    setBlockState(NBTUtil.readBlockState(compound.getCompound("BlockState")));
    this.fallTime = compound.getInt("Time");
    if (compound.contains("HurtEntities", 99)) {
      
      this.hurtEntities = compound.getBoolean("HurtEntities");
      this.fallHurtAmount = compound.getFloat("FallHurtAmount");
      this.fallHurtMax = compound.getInt("FallHurtMax");
    }
    else if (getBlockState().isIn(BlockTags.ANVIL)) {
      
      this.hurtEntities = true;
    } 
    
    if (compound.contains("DropItem", 99))
    {
      this.shouldDropItem = compound.getBoolean("DropItem");
    }
    
    if (compound.contains("TileEntityData", 10))
    {
      this.tileEntityData = compound.getCompound("TileEntityData");
    }
    
    if (getBlockState().isAir())
    {
      setBlockState(Blocks.SAND.getDefaultState());
    }
  }

  
  @OnlyIn(Dist.CLIENT)
  public World getWorldObj() {
    return this.world;
  }

  
  public void setHurtEntities(boolean hurtEntitiesIn) {
    this.hurtEntities = hurtEntitiesIn;
  }





  
  @OnlyIn(Dist.CLIENT)
  public boolean canRenderOnFire() {
    return false;
  }


  
  public void fillCrashReport(CrashReportCategory category) {
    super.fillCrashReport(category);
    category.addDetail("Immitating BlockState", getBlockState().toString());
  }

  
  public void setBlockState(BlockState state) {
    getDataManager().set(BLOCK_STATE, Optional.of(state));
  }

  
  public BlockState getBlockState() {
    if (((Optional)getDataManager().get(BLOCK_STATE)).isPresent()) {
      return ((Optional<BlockState>)getDataManager().get(BLOCK_STATE)).get();
    }
    return Blocks.AIR.getDefaultState();
  }















  
  public boolean ignoreItemEntityData() {
    return true;
  }


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}


