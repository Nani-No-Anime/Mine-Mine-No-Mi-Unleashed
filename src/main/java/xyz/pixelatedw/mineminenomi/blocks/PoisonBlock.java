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
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.TickPriority;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ public class PoisonBlock
/*     */   extends Block
/*     */ {
/*  28 */   protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
/*  29 */   private int ticks = 100;
/*     */ 
/*     */   
/*     */   public PoisonBlock() {
/*  33 */     super(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F).tickRandomly().noDrops());
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
/*     */   public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
/*  75 */     if (entity instanceof LivingEntity)
/*     */     {
/*  77 */       if (!((LivingEntity)entity).isPotionActive(Effects.POISON))
/*     */       {
/*  79 */         ((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.POISON, 300, 1));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
/*  87 */     world.getPendingBlockTicks().scheduleTick(pos, this, tickRate((IWorldReader)world));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
/*  93 */     if (this.ticks > 0) {
/*     */       
/*  95 */       this.ticks--;
/*     */     }
/*     */     else {
/*     */       
/*  99 */       world.setBlockState(pos, Blocks.AIR.getDefaultState());
/* 100 */       this.ticks = 100 + rand.nextInt(10);
/*     */     } 
/*     */     
/* 103 */     world.getPendingBlockTicks().scheduleTick(pos, this, 1, TickPriority.EXTREMELY_HIGH);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\PoisonBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */