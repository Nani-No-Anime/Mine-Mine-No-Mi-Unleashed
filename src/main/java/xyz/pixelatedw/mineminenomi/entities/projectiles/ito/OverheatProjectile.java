/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class OverheatProjectile extends AbilityProjectileEntity {
/*    */   public OverheatProjectile(World world) {
/* 18 */     super(ItoProjectiles.OVERHEAT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public OverheatProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public OverheatProjectile(World world, double x, double y, double z) {
/* 28 */     super(ItoProjectiles.OVERHEAT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public OverheatProjectile(World world, LivingEntity player) {
/* 33 */     super(ItoProjectiles.OVERHEAT, world, player);
/*    */     
/* 35 */     setDamage(60.0F);
/* 36 */     setMaxLife(50);
/* 37 */     setPassThroughEntities();
/* 38 */     setPhysical(false);
/* 39 */     setAffectedByImbuing();
/* 40 */     setCanGetStuckInGround();
/*    */     
/* 42 */     this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
/* 43 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 44 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 49 */     this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
/* 50 */     SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 10);
/* 51 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 52 */       hitEntity.setFire(10);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 57 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
/* 58 */     explosion.setStaticDamage(20.0F);
/* 59 */     explosion.setExplosionSound(true);
/* 60 */     explosion.setDamageOwner(false);
/* 61 */     explosion.setDestroyBlocks(true);
/* 62 */     explosion.setFireAfterExplosion(true);
/* 63 */     explosion.setSmokeParticles(null);
/* 64 */     explosion.setDamageEntities(true);
/* 65 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ito\OverheatProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */