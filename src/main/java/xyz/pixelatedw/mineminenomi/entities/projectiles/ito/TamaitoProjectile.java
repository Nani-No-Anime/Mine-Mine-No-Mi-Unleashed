/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TamaitoProjectile extends AbilityProjectileEntity {
/*    */   public TamaitoProjectile(World world) {
/* 13 */     super(ItoProjectiles.TAMAITO, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TamaitoProjectile(EntityType type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TamaitoProjectile(World world, double x, double y, double z) {
/* 23 */     super(ItoProjectiles.TAMAITO, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public TamaitoProjectile(World world, LivingEntity player) {
/* 28 */     super(ItoProjectiles.TAMAITO, world, player);
/*    */     
/* 30 */     setDamage(10.0F);
/* 31 */     setPhysical(false);
/* 32 */     setAffectedByImbuing();
/* 33 */     setMaxLife(30);
/* 34 */     setArmorPiercing();
/* 35 */     this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ito\TamaitoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */