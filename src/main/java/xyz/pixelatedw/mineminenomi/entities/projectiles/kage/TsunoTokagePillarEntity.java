/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TsunoTokagePillarEntity
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public TsunoTokagePillarEntity(World world) {
/* 12 */     super(KageProjectiles.TSUNO_TOKAGE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TsunoTokagePillarEntity(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TsunoTokagePillarEntity(World world, double x, double y, double z) {
/* 22 */     super(KageProjectiles.TSUNO_TOKAGE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public TsunoTokagePillarEntity(World world, LivingEntity player) {
/* 27 */     super(KageProjectiles.TSUNO_TOKAGE, world, player);
/*    */     
/* 29 */     setDamage(30.0F);
/* 30 */     setMaxLife(10);
/* 31 */     setPassThroughEntities();
/* 32 */     setPassThroughBlocks();
/* 33 */     setPhysical(false);
/* 34 */     setAffectedByImbuing();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\kage\TsunoTokagePillarEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */