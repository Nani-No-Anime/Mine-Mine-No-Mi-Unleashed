/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BambooPillarEntity
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BambooPillarEntity(World world) {
/* 12 */     super(ExtraProjectiles.BAMBOO_PILLAR, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BambooPillarEntity(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BambooPillarEntity(World world, double x, double y, double z) {
/* 22 */     super(ExtraProjectiles.BAMBOO_PILLAR, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BambooPillarEntity(World world, LivingEntity player) {
/* 27 */     super(ExtraProjectiles.BAMBOO_PILLAR, world, player);
/*    */     
/* 29 */     setPhysical(false);
/* 30 */     setMaxLife(8);
/* 31 */     setPassThroughEntities();
/* 32 */     setPassThroughBlocks();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\BambooPillarEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */