/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;
/*    */ 
/*    */ public class DragonsRoarProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*    */   public DragonsRoarProjectile(World world) {
/* 18 */     super(GoeProjectiles.DRAGONS_ROAR, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonsRoarProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonsRoarProjectile(World world, double x, double y, double z) {
/* 28 */     super(GoeProjectiles.DRAGONS_ROAR, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonsRoarProjectile(World world, LivingEntity player, float size) {
/* 33 */     super(GoeProjectiles.DRAGONS_ROAR, world, player);
/*    */     
/* 35 */     setDamage(10.0F);
/* 36 */     setMaxLife(30);
/* 37 */     setPassThroughEntities();
/* 38 */     setDamageSource(this.bypassingSource);
/*    */     
/* 40 */     setSize(size);
/* 41 */     setCollisionSize((size / 4.0F));
/*    */     
/* 43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 48 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), getSize() / 2.0F);
/* 49 */     explosion.setHeightDifference(45);
/* 50 */     explosion.setStaticBlockResistance(1.35F);
/* 51 */     explosion.setProtectOwnerFromFalling(true);
/* 52 */     explosion.setExplosionSound(true);
/* 53 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)getSize()));
/* 54 */     explosion.setDamageSource(this.bypassingSource);
/* 55 */     explosion.setStaticDamage(0.0F);
/* 56 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerData() {
/* 62 */     super.registerData();
/* 63 */     this.dataManager.register(SIZE, Float.valueOf(1.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSize(float size) {
/* 69 */     this.dataManager.set(SIZE, Float.valueOf(MathHelper.clamp(size, 1.0F, 50.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getSize() {
/* 75 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goe\DragonsRoarProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */