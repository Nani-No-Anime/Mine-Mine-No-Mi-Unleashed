/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallCloudParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class ChloroBallProjectile extends AbilityProjectileEntity {
/*  24 */   private static final ParticleEffect PARTICLES1 = (ParticleEffect)new ChloroBallParticleEffect();
/*  25 */   private static final ParticleEffect PARTICLES2 = (ParticleEffect)new ChloroBallCloudParticleEffect();
/*     */ 
/*     */   
/*     */   public ChloroBallProjectile(World world) {
/*  29 */     super(DokuProjectiles.CHLORO_BALL, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChloroBallProjectile(EntityType type, World world) {
/*  34 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChloroBallProjectile(World world, double x, double y, double z) {
/*  39 */     super(DokuProjectiles.CHLORO_BALL, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChloroBallProjectile(World world, LivingEntity player) {
/*  44 */     super(DokuProjectiles.CHLORO_BALL, world, player);
/*     */     
/*  46 */     setDamage(7.0F);
/*  47 */     setPhysical(false);
/*  48 */     setAffectedByImbuing();
/*     */     
/*  50 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  51 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  52 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/*  57 */     hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 300, 0));
/*  58 */     this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos pos) {
/*  63 */     for (int i = 0; i < 20; i++) {
/*     */       
/*  65 */       double offsetX = WyHelper.randomWithRange(-3, 3);
/*  66 */       double offsetZ = WyHelper.randomWithRange(-3, 3);
/*     */       
/*  68 */       BlockPos location = new BlockPos(getPosX() + offsetX, getPosY(), getPosZ() + offsetZ);
/*     */       
/*  70 */       if (this.world.getBlockState(location.down()).isSolid()) {
/*  71 */         AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.POISON, DefaultProtectionRules.AIR_FOLIAGE);
/*     */       }
/*     */     } 
/*  74 */     PARTICLES1.spawn(this.world, getPosX(), getPosY() + 1.0D, getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/*  76 */     ChloroBallCloudEntity smokeCloud = new ChloroBallCloudEntity(this.world);
/*  77 */     smokeCloud.setLife(30);
/*  78 */     smokeCloud.setLocationAndAngles(getPosX(), getPosY() + 1.0D, getPosZ(), 0.0F, 0.0F);
/*  79 */     smokeCloud.setMotion(0.0D, 0.0D, 0.0D);
/*  80 */     smokeCloud.setThrower(getThrower());
/*  81 */     this.world.addEntity((Entity)smokeCloud);
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  86 */     if (!this.world.isRemote)
/*     */     {
/*  88 */       for (int i = 0; i < 2; i++) {
/*     */         
/*  90 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  91 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  92 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  94 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
/*  95 */         data.setLife(5);
/*  96 */         data.setSize(1.3F);
/*  97 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ChloroBallCloudEntity
/*     */     extends EntityCloud
/*     */   {
/*     */     public ChloroBallCloudEntity(World world) {
/* 106 */       super(world);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void tick() {
/* 112 */       super.tick();
/* 113 */       if (!this.world.isRemote) {
/*     */         
/* 115 */         for (Entity target : WyHelper.getEntitiesNear(getPosition(), this.world, 5.0D)) {
/*     */           
/* 117 */           if (getThrower() != target && ((LivingEntity)target).isPotionActive(Effects.POISON)) {
/* 118 */             ((LivingEntity) target).addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
/*     */           }
/*     */         } 
/* 121 */         if (this.ticksExisted % 2 == 0)
/* 122 */           ChloroBallProjectile.PARTICLES2.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doku\ChloroBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */