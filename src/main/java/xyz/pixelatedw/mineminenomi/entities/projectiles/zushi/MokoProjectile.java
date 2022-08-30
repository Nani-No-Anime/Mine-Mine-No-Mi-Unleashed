/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class MokoProjectile extends AbilityProjectileEntity {
/*    */   public MokoProjectile(World world) {
/* 23 */     super(ZushiProjectiles.MOKO, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MokoProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MokoProjectile(World world, double x, double y, double z) {
/* 33 */     super(ZushiProjectiles.MOKO, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public MokoProjectile(World world, LivingEntity player) {
/* 38 */     super(ZushiProjectiles.MOKO, world, player);
/*    */     
/* 40 */     setDamage(10.0F);
/* 41 */     setPassThroughEntities();
/* 42 */     setChangeHurtTime(true);
/* 43 */     setDamageSource(this.bypassingSource);
/*    */     
/* 45 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 46 */     this.onBlockImpactEvent = this::onBlockImpact;
/* 47 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpact(BlockPos hit) {
/* 52 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
/* 53 */     explosion.setStaticDamage(15.0F);
/* 54 */     explosion.setExplosionSound(true);
/* 55 */     explosion.setDamageOwner(false);
/* 56 */     explosion.setDestroyBlocks(true);
/* 57 */     explosion.setFireAfterExplosion(false);
/* 58 */     explosion.setExplosionSound(false);
/* 59 */     explosion.setSmokeParticles(null);
/* 60 */     explosion.setDamageEntities(true);
/* 61 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 66 */     for (int x = -1; x < 1; x++) {
/*    */       
/* 68 */       for (int z = -1; z < 1; z++) {
/*    */         
/* 70 */         if (AbilityHelper.placeBlockIfAllowed(this.world, getPosX(), getPosY(), getPosZ(), Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/*    */           
/* 72 */           target.setMotion(0.0D, -5.0D, 0.0D);
/* 73 */           target.velocityChanged = true;
/* 74 */           target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 10));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 82 */     if (!this.world.isRemote)
/*    */     {
/* 84 */       for (int i = 0; i < 25; i++) {
/*    */         
/* 86 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 87 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 88 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 90 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
/* 91 */         data.setLife(10);
/* 92 */         data.setSize(1.0F);
/*    */         
/* 94 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\zushi\MokoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */