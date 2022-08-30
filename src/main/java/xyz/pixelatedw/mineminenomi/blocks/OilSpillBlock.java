/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.TickPriority;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ 
/*     */ public class OilSpillBlock
/*     */   extends Block
/*     */ {
/*  28 */   protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
/*  29 */   private int ticks = 400;
/*     */ 
/*     */   
/*     */   public OilSpillBlock() {
/*  33 */     super(Block.Properties.create(Material.PACKED_ICE).slipperiness(0.98F).hardnessAndResistance(0.5F).sound(SoundType.SLIME).tickRandomly());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  39 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  45 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube(BlockState state) {
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
/*  56 */     return world.getBlockState(pos.down()).isSolid();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  62 */     return !stateIn.isValidPosition((IWorldReader)worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/*  69 */     return (adjacentBlockState.getBlock() == this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
/*  75 */     world.getPendingBlockTicks().scheduleTick(pos, this, tickRate((IWorldReader)world));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
/*  81 */     float x = (float)Math.min(1.5D, (entity.getMotion()).x * 1.149999976158142D);
/*  82 */     float z = (float)Math.min(1.5D, (entity.getMotion()).z * 1.149999976158142D);
/*  83 */     entity.setMotion(x, (entity.getMotion()).y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
/*  89 */     if (world.isRemote) {
/*     */       return;
/*     */     }
/*  92 */     if (this.ticks > 0) {
/*     */       
/*  94 */       this.ticks--;
/*     */       
/*  96 */       if (areFlamesClose((World)world, pos))
/*     */       {
/*  98 */         world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3.0F, true, CommonConfig.INSTANCE.isAbilityGriefingEnabled() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 103 */       world.setBlockState(pos, Blocks.AIR.getDefaultState());
/* 104 */       this.ticks = 400 + rand.nextInt(10);
/*     */     } 
/*     */     
/* 107 */     world.getPendingBlockTicks().scheduleTick(pos, this, 10, TickPriority.EXTREMELY_HIGH);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean areFlamesClose(World world, BlockPos pos) {
/* 112 */     int range = 2;
/*     */     
/* 114 */     for (int i = -range; i < range; i++) {
/*     */       
/* 116 */       for (int j = -range; j < range; j++) {
/*     */         
/* 118 */         for (int k = -range; k < range; k++) {
/*     */           
/* 120 */           double posX = (pos.getX() + i);
/* 121 */           double posY = (pos.getY() + j);
/* 122 */           double posZ = (pos.getZ() + k);
/*     */           
/* 124 */           Block currentBlock = world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
/* 125 */           if (currentBlock == Blocks.FIRE)
/* 126 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 130 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\OilSpillBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */