/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DemonHydraProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   private boolean teleport = false;
/*    */   
/*    */   public DemonHydraProjectile(World world) {
/* 19 */     super(DokuProjectiles.DEMON_HYDRA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DemonHydraProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DemonHydraProjectile(World world, double x, double y, double z) {
/* 29 */     super(DokuProjectiles.DEMON_HYDRA, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public DemonHydraProjectile(World world, LivingEntity player, boolean teleport) {
/* 34 */     super(DokuProjectiles.DEMON_HYDRA, world, player);
/*    */     
/* 36 */     setPhysical(false);
/* 37 */     setAffectedByImbuing();
/* 38 */     setDamage(teleport ? 15.0F : 30.0F);
/* 39 */     setMaxLife(teleport ? 50 : 25);
/* 40 */     setPassThroughEntities();
/* 41 */     this.teleport = teleport;
/*    */     
/* 43 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 44 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 48 */     if (this.teleport) {
/*    */       
/* 50 */       if (getThrower() == null) {
/*    */         return;
/*    */       }
/* 53 */       BlockState state = (getThrower()).world.getBlockState(pos);
/* 54 */       if (state.getBlock().isAir(state, (IBlockReader)this.world, pos)) {
/*    */         return;
/*    */       }
/* 57 */       if (getThrower().getRidingEntity() != null) {
/* 58 */         getThrower().stopRiding();
/*    */       }
/* 60 */       getThrower().setPositionAndUpdate(pos.getX(), pos.getY() + 1.5D, pos.getZ());
/* 61 */       (getThrower()).fallDistance = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 67 */     hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 500, this.teleport ? 5 : 4));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doku\DemonHydraProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */