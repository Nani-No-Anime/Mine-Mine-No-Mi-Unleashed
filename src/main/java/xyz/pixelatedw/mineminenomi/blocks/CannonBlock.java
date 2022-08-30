/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.HorizontalBlock;
/*     */ import net.minecraft.block.IWaterLoggable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.fluid.IFluidState;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.state.BooleanProperty;
/*     */ import net.minecraft.state.DirectionProperty;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CannonTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class CannonBlock extends Block implements IWaterLoggable {
/*  44 */   public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
/*  45 */   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
/*     */ 
/*     */   
/*     */   public CannonBlock() {
/*  49 */     super(Block.Properties.create(Material.IRON).hardnessAndResistance(1.5F).notSolid());
/*  50 */     setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)FACING, (Comparable)Direction.NORTH)).with((IProperty)WATERLOGGED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState getStateForPlacement(BlockItemUseContext context) {
/*  57 */     BlockState blockstate = getDefaultState();
/*  58 */     BlockPos blockpos = context.getPos();
/*  59 */     Direction[] adirection = context.getNearestLookingDirections();
/*  60 */     IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
/*     */     
/*  62 */     for (Direction direction : adirection) {
/*     */       
/*  64 */       if (direction.getAxis().isHorizontal()) {
/*     */         
/*  66 */         blockstate = (BlockState)blockstate.with((IProperty)FACING, (Comparable)direction);
/*  67 */         blockstate = (BlockState)blockstate.with((IProperty)WATERLOGGED, Boolean.valueOf((ifluidstate.getFluid() == Fluids.WATER)));
/*  68 */         if (blockstate.isValidPosition((IWorldReader)context.getWorld(), blockpos))
/*     */         {
/*  70 */           return blockstate;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
/*  81 */     CannonTileEntity tileEntity = (CannonTileEntity)world.getTileEntity(pos);
/*     */     
/*  83 */     if (hand == Hand.OFF_HAND) {
/*  84 */       return ActionResultType.PASS;
/*     */     }
/*  86 */     if (!player.getHeldItemMainhand().isEmpty()) {
/*     */       
/*  88 */       if (tileEntity.getGunpowederLoaded() < 5 && player.getHeldItemMainhand().getItem() == Items.GUNPOWDER) {
/*     */         
/*  90 */         player.getHeldItemMainhand().shrink(1);
/*  91 */         tileEntity.addGunpoweder();
/*  92 */         return ActionResultType.SUCCESS;
/*     */       } 
/*     */       
/*  95 */       if (!tileEntity.hasCannonBall() && player.getHeldItemMainhand().getItem() == ModItems.CANNON_BALL) {
/*     */         
/*  97 */         player.getHeldItemMainhand().shrink(1);
/*  98 */         tileEntity.setHasCannonBall(true);
/*  99 */         return ActionResultType.SUCCESS;
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     if (!world.isRemote && tileEntity.getGunpowederLoaded() > 0 && tileEntity.hasCannonBall()) {
/*     */       
/* 105 */       int damage = 30 + tileEntity.getGunpowederLoaded() * 2;
/* 106 */       int life = 100 + tileEntity.getGunpowederLoaded() * 20;
/* 107 */       float gravMod = life / 10000.0F;
/* 108 */       float speed = gravMod * 100.0F;
/* 109 */       if (speed < 1.5F) {
/* 110 */         speed = 0.0F;
/*     */       }
/* 112 */       CannonBallProjectile proj = new CannonBallProjectile(player.world, (LivingEntity)player);
/* 113 */       proj.setPosition(pos.getX(), pos.getY() + 1.25D, pos.getZ());
/* 114 */       proj.setDamage(damage);
/* 115 */       proj.setMaxLife(life);
/* 116 */       proj.setGravity(0.04F - gravMod);
/* 117 */       world.addEntity((Entity)proj);
/* 118 */       proj.shoot((Entity)player, 0.0F, ((Direction)state.get((IProperty)FACING)).getHorizontalAngle(), 0.0F, 3.0F + speed, 0.0F);
/*     */       
/* 120 */       for (int i = 0; i < 10; i++) {
/*     */         
/* 122 */         double offsetX = WyHelper.randomDouble();
/* 123 */         double offsetY = WyHelper.randomDouble();
/* 124 */         double offsetZ = WyHelper.randomDouble();
/*     */         
/* 126 */         ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.POOF, pos.getX() + offsetX, (pos.getY() + 1) + offsetY, pos.getZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */       
/* 129 */       tileEntity.emptyGunpoweder();
/* 130 */       tileEntity.setHasCannonBall(false);
/* 131 */       return ActionResultType.SUCCESS;
/*     */     } 
/*     */     
/* 134 */     return ActionResultType.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
/* 140 */     if (!world.isRemote) {
/*     */       
/* 142 */       TileEntity tileEntity = world.getTileEntity(pos);
/* 143 */       if (tileEntity != null && tileEntity instanceof CannonTileEntity) {
/*     */         
/* 145 */         CannonTileEntity tile = (CannonTileEntity)tileEntity;
/* 146 */         if (tile.hasCannonBall()) {
/*     */           
/* 148 */           ItemEntity itementity = new ItemEntity(world, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), new ItemStack((IItemProvider)ModItems.CANNON_BALL));
/* 149 */           itementity.setDefaultPickupDelay();
/* 150 */           world.addEntity((Entity)itementity);
/*     */         } 
/*     */         
/* 153 */         int gunpowder = tile.getGunpowederLoaded();
/* 154 */         if (gunpowder > 0) {
/*     */           
/* 156 */           ItemStack gunpowderStack = new ItemStack((IItemProvider)Items.GUNPOWDER);
/* 157 */           gunpowderStack.setCount(gunpowder);
/* 158 */           ItemEntity itementity = new ItemEntity(world, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), gunpowderStack);
/* 159 */           itementity.setDefaultPickupDelay();
/* 160 */           world.addEntity((Entity)itementity);
/*     */         } 
/*     */       } 
/*     */     } 
/* 164 */     return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IFluidState getFluidState(BlockState state) {
/* 170 */     return ((Boolean)state.get((IProperty)WATERLOGGED)).booleanValue() ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/* 176 */     if (((Boolean)stateIn.get((IProperty)WATERLOGGED)).booleanValue())
/*     */     {
/* 178 */       worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate((IWorldReader)worldIn));
/*     */     }
/* 180 */     return (facing.getOpposite() == stateIn.get((IProperty)FACING) && !stateIn.isValidPosition((IWorldReader)worldIn, currentPos)) ? Blocks.AIR.getDefaultState() : stateIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState rotate(BlockState state, Rotation rot) {
/* 186 */     return (BlockState)state.with((IProperty)FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState mirror(BlockState state, Mirror mirrorIn) {
/* 192 */     return state.rotate(mirrorIn.toRotation((Direction)state.get((IProperty)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
/* 198 */     builder.add(new IProperty[] { (IProperty)WATERLOGGED });
/* 199 */     builder.add(new IProperty[] { (IProperty)FACING });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 205 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 212 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 218 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 224 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 230 */     return (TileEntity)new CannonTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 236 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\CannonBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */