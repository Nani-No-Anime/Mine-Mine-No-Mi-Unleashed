/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.EffectInstance;
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
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class MucusBlock
/*     */   extends Block
/*     */ {
/*  30 */   protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
/*  31 */   private int ticks = 1200;
/*     */ 
/*     */   
/*     */   public MucusBlock() {
/*  35 */     super(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(8.0F).tickRandomly().noDrops());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  41 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  47 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube(BlockState state) {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
/*  58 */     return world.getBlockState(pos.down()).isSolid();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  64 */     return !stateIn.isValidPosition((IWorldReader)worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/*  71 */     return (adjacentBlockState.getBlock() == this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
/*  77 */     if (entity instanceof LivingEntity)
/*     */     {
/*  79 */       if (!((LivingEntity)entity).isPotionActive(ModEffects.STICKY))
/*     */       {
/*  81 */         ((LivingEntity)entity).addPotionEffect(new EffectInstance(ModEffects.STICKY, 50, 0, false, false, false));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
/*  89 */     world.getPendingBlockTicks().scheduleTick(pos, this, tickRate((IWorldReader)world));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
/*  95 */     if (world.isRemote) {
/*     */       return;
/*     */     }
/*  98 */     if (this.ticks > 0) {
/*     */       
/* 100 */       this.ticks--;
/*     */       
/* 102 */       if (areFlamesClose((World)world, pos))
/*     */       {
/* 104 */         world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, CommonConfig.INSTANCE.isAbilityGriefingEnabled() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 109 */       world.setBlockState(pos, Blocks.AIR.getDefaultState());
/* 110 */       this.ticks = 1200 + rand.nextInt(400);
/*     */     } 
/*     */     
/* 113 */     world.getPendingBlockTicks().scheduleTick(pos, this, 1, TickPriority.EXTREMELY_HIGH);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean areFlamesClose(World world, BlockPos pos) {
/* 118 */     int range = 2;
/*     */     
/* 120 */     for (int i = -range; i < range; i++) {
/*     */       
/* 122 */       for (int j = -range; j < range; j++) {
/*     */         
/* 124 */         for (int k = -range; k < range; k++) {
/*     */           
/* 126 */           double posX = (pos.getX() + i);
/* 127 */           double posY = (pos.getY() + j);
/* 128 */           double posZ = (pos.getZ() + k);
/*     */           
/* 130 */           Block currentBlock = world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
/* 131 */           if (currentBlock == Blocks.FIRE)
/* 132 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 136 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\MucusBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */