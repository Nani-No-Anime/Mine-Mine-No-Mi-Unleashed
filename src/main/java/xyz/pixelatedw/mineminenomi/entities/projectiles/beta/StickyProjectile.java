/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.beta;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class StickyProjectile extends AbilityProjectileEntity {
/*     */   public StickyProjectile(World world) {
/*  26 */     super(BetaProjectiles.STICKY_PROJECTILE, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public StickyProjectile(EntityType type, World world) {
/*  31 */     super(type, world);
/*     */   }
/*     */   private boolean causeExplosion = false;
/*     */   
/*     */   public StickyProjectile(World world, double x, double y, double z) {
/*  36 */     super(BetaProjectiles.STICKY_PROJECTILE, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public StickyProjectile(World world, LivingEntity player) {
/*  41 */     super(BetaProjectiles.STICKY_PROJECTILE, world, player);
/*     */     
/*  43 */     setDamage(2.0F);
/*  44 */     setMaxLife(20);
/*  45 */     setGravity(0.025F);
/*  46 */     setPhysical(false);
/*  47 */     setAffectedByImbuing();
/*     */     
/*  49 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  50 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  51 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  56 */     if (!this.world.isRemote) {
/*     */       int i;
/*  58 */       for (i = 0; i < 4; i++) {
/*     */         
/*  60 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/*  61 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/*  62 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*     */         
/*  64 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.BETA);
/*  65 */         data.setLife(10);
/*  66 */         data.setSize(1.3F);
/*  67 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + 0.25D + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */       
/*  70 */       if (this.causeExplosion)
/*     */       {
/*  72 */         for (i = 0; i < 2; i++) {
/*     */           
/*  74 */           double offsetX = WyHelper.randomDouble() / 2.0D;
/*  75 */           double offsetY = WyHelper.randomDouble() / 2.0D;
/*  76 */           double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */           
/*  78 */           GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/*  79 */           data.setLife(7);
/*  80 */           data.setSize(1.2F);
/*  81 */           WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEntityImpactEvent(LivingEntity entity) {
/*  89 */     if (this.causeExplosion) {
/*     */       
/*  91 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 2.0F);
/*  92 */       explosion.setStaticDamage(10.0F);
/*  93 */       explosion.setExplosionSound(true);
/*  94 */       explosion.setDamageOwner(false);
/*  95 */       explosion.setDestroyBlocks(true);
/*  96 */       explosion.setFireAfterExplosion(false);
/*  97 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*  98 */       explosion.setDamageEntities(true);
/*  99 */       explosion.doExplosion();
/*     */     }
/*     */     else {
/*     */       
/* 103 */       entity.addPotionEffect(new EffectInstance(ModEffects.STICKY, 300, 0, false, false));
/* 104 */       for (int i = 0; i < 20; i++) {
/*     */         
/* 106 */         double offsetX = WyHelper.randomWithRange(-2, 2);
/* 107 */         double offsetZ = WyHelper.randomWithRange(-2, 2);
/* 108 */         BlockPos location = new BlockPos(getPosX() + offsetX, getPosY() - 1.0D, getPosZ() + offsetZ);
/* 109 */         if (this.world.getBlockState(location.down()).isSolid()) {
/* 110 */           AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.MUCUS, DefaultProtectionRules.AIR_FOLIAGE);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/* 117 */     if (this.causeExplosion) {
/*     */       
/* 119 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
/* 120 */       explosion.setStaticDamage(10.0F);
/* 121 */       explosion.setExplosionSound(true);
/* 122 */       explosion.setDamageOwner(false);
/* 123 */       explosion.setDestroyBlocks(true);
/* 124 */       explosion.setFireAfterExplosion(true);
/* 125 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 126 */       explosion.setDamageEntities(true);
/* 127 */       explosion.doExplosion();
/*     */     }
/*     */     else {
/*     */       
/* 131 */       for (int i = 0; i < 20; i++) {
/*     */         
/* 133 */         double offsetX = WyHelper.randomWithRange(-2, 2);
/* 134 */         double offsetZ = WyHelper.randomWithRange(-2, 2);
/* 135 */         BlockPos location = new BlockPos(getPosX() + offsetX, getPosY(), getPosZ() + offsetZ);
/* 136 */         if (this.world.getBlockState(location.down()).isSolid()) {
/* 137 */           AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.MUCUS, DefaultProtectionRules.AIR_FOLIAGE);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCauseExplosion() {
/* 144 */     this.causeExplosion = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\beta\StickyProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */