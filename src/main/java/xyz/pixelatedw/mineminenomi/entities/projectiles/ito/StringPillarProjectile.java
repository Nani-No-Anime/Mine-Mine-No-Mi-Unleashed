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
/*    */ public class StringPillarProjectile extends AbilityProjectileEntity {
/*    */   public StringPillarProjectile(World world) {
/* 13 */     super(ItoProjectiles.STRING_PILLAR, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public StringPillarProjectile(EntityType type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public StringPillarProjectile(World world, double x, double y, double z) {
/* 23 */     super(ItoProjectiles.STRING_PILLAR, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public StringPillarProjectile(World world, LivingEntity player) {
/* 28 */     super(ItoProjectiles.STRING_PILLAR, world, player);
/*    */     
/* 30 */     setDamage(15.0F);
/* 31 */     setPhysical(false);
/* 32 */     setAffectedByImbuing();
/* 33 */     setPassThroughEntities();
/* 34 */     setArmorPiercing();
/* 35 */     this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 40 */     super.tick();
/* 41 */     this.rotationPitch = 90.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ito\StringPillarProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */