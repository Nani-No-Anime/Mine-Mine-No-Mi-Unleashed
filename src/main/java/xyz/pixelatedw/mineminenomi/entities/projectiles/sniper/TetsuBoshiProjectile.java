/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.SpikeEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TetsuBoshiProjectile extends AbilityProjectileEntity {
/*    */   public TetsuBoshiProjectile(World world) {
/* 15 */     super(SniperProjectiles.TETSU_BOSHI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TetsuBoshiProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TetsuBoshiProjectile(World world, double x, double y, double z) {
/* 25 */     super(SniperProjectiles.TETSU_BOSHI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public TetsuBoshiProjectile(World world, LivingEntity player) {
/* 30 */     super(SniperProjectiles.TETSU_BOSHI, world, player);
/*    */     
/* 32 */     setDamage(4.0F);
/* 33 */     setPhysical(false);
/* 34 */     setAffectedByImbuing();
/* 35 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 40 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 42 */       double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 43 */       double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 45 */       SpikeEntity spike = new SpikeEntity(this.world);
/* 46 */       spike.setPositionAndRotation(hit.getX() + offsetX, (hit.getY() + 1), hit.getZ() + offsetZ, 180.0F, 0.0F);
/* 47 */       this.world.addEntity((Entity)spike);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\TetsuBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */