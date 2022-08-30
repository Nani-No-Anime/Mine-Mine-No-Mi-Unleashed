/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoJetPistolProjectile extends AbilityProjectileEntity {
/* 12 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GearSecondParticleEffect();
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetPistolProjectile(World world) {
/* 16 */     super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetPistolProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetPistolProjectile(World world, double x, double y, double z) {
/* 26 */     super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetPistolProjectile(World world, LivingEntity player) {
/* 31 */     super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world, player);
/*    */     
/* 33 */     setDamage(10.0F);
/* 34 */     setMaxLife(9);
/* 35 */     setPhysical(true);
/* 36 */     setChangeHurtTime(true);
/*    */     
/* 38 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 43 */     if (this.ticksExisted % 2 == 0)
/* 44 */       PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoJetPistolProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */