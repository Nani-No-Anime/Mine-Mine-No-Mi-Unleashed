/*     */ package xyz.pixelatedw.mineminenomi.blocks.traps;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TrapAbilityBlock
/*     */   extends Block
/*     */ {
/*  25 */   private int damageDealt = 0;
/*  26 */   private double horizontalFallSpeed = 0.05D;
/*  27 */   private double verticalFallSpeed = 0.25D;
/*  28 */   private EffectInstance potionEffect = null;
/*     */ 
/*     */   
/*     */   public TrapAbilityBlock(Block.Properties properties) {
/*  32 */     super(properties);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
/*  38 */     if (entity instanceof LivingEntity) {
/*     */       
/*  40 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)entity);
/*  41 */       if (!props.hasDevilFruit(getDevilFruit())) {
/*  42 */         entity.setMotionMultiplier(state, new Vec3d(getHorizontalFallSpeed(), getVerticalFallSpeed(), getHorizontalFallSpeed()));
/*     */       }
/*  44 */     } else if (entity instanceof net.minecraft.entity.item.BoatEntity) {
/*  45 */       entity.remove();
/*     */     } else {
/*  47 */       entity.setMotionMultiplier(state, new Vec3d(getVerticalFallSpeed(), getHorizontalFallSpeed(), getVerticalFallSpeed()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  53 */     Entity entity = context.getEntity();
/*  54 */     if (entity instanceof PlayerEntity) {
/*     */       
/*  56 */       PlayerEntity player = (PlayerEntity)entity;
/*  57 */       if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(getDevilFruit()))
/*     */       {
/*  59 */         return state.getShape(worldIn, pos);
/*     */       }
/*     */     } 
/*     */     
/*  63 */     return super.getCollisionShape(state, worldIn, pos, context);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageDealt() {
/*  68 */     return this.damageDealt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamageDealt(int damageDealt) {
/*  73 */     this.damageDealt = damageDealt;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getVerticalFallSpeed() {
/*  78 */     return this.verticalFallSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVerticalFallSpeed(double verticalFallSpeed) {
/*  83 */     this.verticalFallSpeed = verticalFallSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public EffectInstance getPotionEffect() {
/*  88 */     return this.potionEffect;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPotionEffect(EffectInstance potionEffect) {
/*  93 */     this.potionEffect = potionEffect;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract AkumaNoMiItem getDevilFruit();
/*     */ 
/*     */   
/*     */   public boolean canSpawnInBlock() {
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getHorizontalFallSpeed() {
/* 106 */     return this.horizontalFallSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHorizontalFallSpeed(double horizontalFallSpeed) {
/* 111 */     this.horizontalFallSpeed = horizontalFallSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEntityInsideOpaqueBlock(LivingEntity e, int offSet) {
/* 116 */     BlockPos.PooledMutable blockpos$pooledmutableblockpos = BlockPos.PooledMutable.retain(); try {
/*     */       int i;
/* 118 */       for (i = 0; i < 8; i++)
/*     */       
/* 120 */       { int j = MathHelper.floor(e.getPosY() - offSet + ((((i >> 0) % 2) - 0.5F) * 0.1F) + e.getEyeHeight());
/* 121 */         int k = MathHelper.floor(e.getPosX() + ((((i >> 1) % 2) - 0.5F) * e.getHeight() * 0.8F));
/* 122 */         int l = MathHelper.floor(e.getPosZ() + ((((i >> 2) % 2) - 0.5F) * e.getWidth() * 0.8F));
/* 123 */         if (blockpos$pooledmutableblockpos.getX() != k || blockpos$pooledmutableblockpos.getY() != j || blockpos$pooledmutableblockpos.getZ() != l)
/*     */         
/* 125 */         { blockpos$pooledmutableblockpos.setPos(k, j, l);
/* 126 */           if (e.world.getBlockState((BlockPos)blockpos$pooledmutableblockpos).getBlock() instanceof TrapAbilityBlock)
/*     */           
/* 128 */           { boolean bool = true;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 133 */             if (blockpos$pooledmutableblockpos != null) blockpos$pooledmutableblockpos.close();  return bool; }  }  }  i = 0; if (blockpos$pooledmutableblockpos != null) blockpos$pooledmutableblockpos.close(); 
/*     */       return false;
/*     */     } catch (Throwable throwable) {
/*     */       if (blockpos$pooledmutableblockpos != null)
/*     */         try {
/*     */           blockpos$pooledmutableblockpos.close();
/*     */         } catch (Throwable throwable1) {
/*     */           throwable.addSuppressed(throwable1);
/*     */         }  
/*     */       throw throwable;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\traps\TrapAbilityBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */