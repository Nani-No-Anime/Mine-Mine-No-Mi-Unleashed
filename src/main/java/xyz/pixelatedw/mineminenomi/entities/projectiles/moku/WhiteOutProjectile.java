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
/*    */ public class WhiteOutProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public WhiteOutProjectile(World world) {
/* 19 */     super(MokuProjectiles.WHITE_OUT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteOutProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteOutProjectile(World world, double x, double y, double z) {
/* 29 */     super(MokuProjectiles.WHITE_OUT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteOutProjectile(World world, LivingEntity player) {
/* 34 */     super(MokuProjectiles.WHITE_OUT, world, player);
/*    */     
/* 36 */     setDamage(10.0F);
/* 37 */     setMaxLife(128);
/* 38 */     setPhysical(true);
/*    */     
/* 40 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.SMOKE, 500, 0) });
/*    */ 
/*    */ 
/*    */     
/* 44 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 49 */     if (!this.world.isRemote)
/*    */     {
/* 51 */       for (int i = 0; i < 10; i++) {
/*    */         
/* 53 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 54 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 55 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 57 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 58 */         data.setLife(10);
/* 59 */         data.setSize(1.3F);
/* 60 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\WhiteOutProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */