/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class HanaGenericEntity
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public HanaGenericEntity(World world) {
/* 11 */     super(HanaProjectiles.GENERIC_HANA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public HanaGenericEntity(World world, LivingEntity player) {
/* 16 */     super(HanaProjectiles.GENERIC_HANA, world, player);
/* 17 */     setCollisionSize(1.25D);
/* 18 */     setDamage(0.0F);
/* 19 */     setFake();
/* 20 */     setMaxLife(10);
/* 21 */     setPhysical(true);
/*    */   }
/*    */   
/*    */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\HanaGenericEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */