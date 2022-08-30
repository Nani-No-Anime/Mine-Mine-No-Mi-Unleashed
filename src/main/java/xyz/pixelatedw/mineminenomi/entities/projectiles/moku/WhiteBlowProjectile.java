/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class WhiteBlowProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public WhiteBlowProjectile(World world) {
/* 19 */     super(MokuProjectiles.WHITE_BLOW, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteBlowProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteBlowProjectile(World world, double x, double y, double z) {
/* 29 */     super(MokuProjectiles.WHITE_BLOW, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteBlowProjectile(World world, LivingEntity player) {
/* 34 */     super(MokuProjectiles.WHITE_BLOW, world, player);
/*    */     
/* 36 */     setDamage(2.0F);
/* 37 */     setMaxLife(24);
/* 38 */     setChangeHurtTime(true);
/* 39 */     setPhysical(true);
/*    */     
/* 41 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.SMOKE, 200, 0) });
/*    */     
/* 43 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 48 */     if (!this.world.isRemote)
/*    */     {
/* 50 */       for (int i = 0; i < 10; i++) {
/*    */         
/* 52 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 53 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 54 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 56 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 57 */         data.setLife(10);
/* 58 */         data.setSize(1.3F);
/* 59 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\WhiteBlowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */