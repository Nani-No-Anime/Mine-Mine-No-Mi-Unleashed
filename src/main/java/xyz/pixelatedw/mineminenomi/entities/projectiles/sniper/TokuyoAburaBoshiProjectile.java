/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TokuyoAburaBoshiProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public TokuyoAburaBoshiProjectile(World world) {
/* 17 */     super(SniperProjectiles.TOKUYO_ABURA_BOSHI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TokuyoAburaBoshiProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TokuyoAburaBoshiProjectile(World world, double x, double y, double z) {
/* 27 */     super(SniperProjectiles.TOKUYO_ABURA_BOSHI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public TokuyoAburaBoshiProjectile(World world, LivingEntity player) {
/* 32 */     super(SniperProjectiles.TOKUYO_ABURA_BOSHI, world, player);
/*    */     
/* 34 */     setDamage(4.0F);
/* 35 */     setPhysical(false);
/* 36 */     setAffectedByImbuing();
/* 37 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 42 */     for (int heightSpread = -3; heightSpread < 3; heightSpread++) {
/*    */       
/* 44 */       for (int i = 0; i < 20; i++) {
/*    */         
/* 46 */         double offsetX = WyHelper.randomWithRange(-3, 3);
/* 47 */         double offsetZ = WyHelper.randomWithRange(-3, 3);
/*    */         
/* 49 */         BlockPos location = new BlockPos(getPosX() + offsetX, getPosY() + heightSpread, getPosZ() + offsetZ);
/*    */         
/* 51 */         if (this.world.getBlockState(location.down()).isSolid() && this.world.getBlockState(location.down()).getBlock() != ModBlocks.OIL_SPILL)
/* 52 */           AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.OIL_SPILL, DefaultProtectionRules.AIR_FOLIAGE); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\TokuyoAburaBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */