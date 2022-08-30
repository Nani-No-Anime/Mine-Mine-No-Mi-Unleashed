/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.electro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectricalLunaParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class ElectricalLunaProjectile extends AbilityProjectileEntity {
/* 13 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new ElectricalLunaParticleEffect();
/*    */ 
/*    */   
/*    */   public ElectricalLunaProjectile(World world) {
/* 17 */     super(ElectroProjectiles.ELECTRICAL_LUNA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ElectricalLunaProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ElectricalLunaProjectile(World world, double x, double y, double z) {
/* 27 */     super(ElectroProjectiles.ELECTRICAL_LUNA, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public ElectricalLunaProjectile(World world, LivingEntity player) {
/* 32 */     super(ElectroProjectiles.ELECTRICAL_LUNA, world, player);
/*    */     
/* 34 */     setMaxLife(20);
/* 35 */     setPassThroughEntities();
/* 36 */     setDamageSource(ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player));
/*    */     
/* 38 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 43 */     if (this.ticksExisted < 0) {
/*    */       return;
/*    */     }
/* 46 */     PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\electro\ElectricalLunaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */