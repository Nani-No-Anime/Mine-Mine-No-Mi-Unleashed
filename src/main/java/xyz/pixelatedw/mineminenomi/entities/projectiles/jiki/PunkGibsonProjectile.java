/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.JikiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class PunkGibsonProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   private List<ItemStack> magneticItems;
/*    */   
/*    */   public PunkGibsonProjectile(World world) {
/* 20 */     super(JikiProjectiles.PUNK_GIBSON, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PunkGibsonProjectile(EntityType type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PunkGibsonProjectile(World world, double x, double y, double z) {
/* 30 */     super(JikiProjectiles.PUNK_GIBSON, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public PunkGibsonProjectile(World world, LivingEntity player, List<ItemStack> items) {
/* 35 */     super(JikiProjectiles.PUNK_GIBSON, world, player);
/*    */     
/* 37 */     setDamage(40.0F);
/* 38 */     setPhysical(false);
/* 39 */     setAffectedByImbuing();
/*    */     
/* 41 */     this.magneticItems = items;
/*    */     
/* 43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 48 */     if (getThrower() != null && getThrower() instanceof PlayerEntity)
/* 49 */       JikiHelper.dropComponentItems((PlayerEntity)getThrower(), hit, this.magneticItems); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\PunkGibsonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */