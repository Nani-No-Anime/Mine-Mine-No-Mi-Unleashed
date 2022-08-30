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
/*    */ public class DamnedPunkProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   private List<ItemStack> magneticItems;
/*    */   
/*    */   public DamnedPunkProjectile(World world) {
/* 20 */     super(JikiProjectiles.DAMNED_PUNK, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DamnedPunkProjectile(EntityType type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DamnedPunkProjectile(World world, double x, double y, double z) {
/* 30 */     super(JikiProjectiles.DAMNED_PUNK, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public DamnedPunkProjectile(World world, LivingEntity player, List<ItemStack> items) {
/* 35 */     super(JikiProjectiles.DAMNED_PUNK, world, player);
/*    */     
/* 37 */     setDamage(40.0F);
/* 38 */     setArmorPiercing();
/* 39 */     setPhysical(false);
/* 40 */     setAffectedByImbuing();
/*    */     
/* 42 */     this.magneticItems = items;
/*    */     
/* 44 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 49 */     if (getThrower() != null && getThrower() instanceof PlayerEntity)
/* 50 */       JikiHelper.dropComponentItems((PlayerEntity)getThrower(), hit, this.magneticItems); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\DamnedPunkProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */