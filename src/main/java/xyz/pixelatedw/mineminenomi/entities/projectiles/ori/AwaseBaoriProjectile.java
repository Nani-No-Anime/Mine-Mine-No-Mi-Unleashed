/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ori;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ 
/*    */ public class AwaseBaoriProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   private List<BlockPos> blocks;
/*    */   
/*    */   public AwaseBaoriProjectile(World world) {
/* 22 */     super(OriProjectiles.AWASE_BAORI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public AwaseBaoriProjectile(EntityType type, World world) {
/* 27 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public AwaseBaoriProjectile(World world, double x, double y, double z) {
/* 32 */     super(OriProjectiles.AWASE_BAORI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public AwaseBaoriProjectile(World world, LivingEntity player) {
/* 37 */     super(OriProjectiles.AWASE_BAORI, world, player);
/*    */     
/* 39 */     setDamage(6.0F);
/* 40 */     setPhysical(true);
/* 41 */     setAffectedByImbuing();
/* 42 */     setMaxLife(4);
/*    */     
/* 44 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 49 */     if (this.blocks == null) {
/* 50 */       this.blocks = AbilityHelper.createEmptyCube(target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 2, 2, 2, ModBlocks.ORI_BARS, DefaultProtectionRules.AIR_FOLIAGE);
/*    */     }
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public List<BlockPos> getBlocks() {
/* 56 */     return this.blocks;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ori\AwaseBaoriProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */