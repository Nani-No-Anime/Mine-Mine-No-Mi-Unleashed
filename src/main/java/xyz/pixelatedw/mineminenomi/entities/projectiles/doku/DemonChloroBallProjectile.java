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
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallCloudParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class DemonChloroBallProjectile extends AbilityProjectileEntity {
/*  23 */   private static final ChloroBallParticleEffect PARTICLES1 = new ChloroBallParticleEffect();
/*  24 */   private static final ChloroBallCloudParticleEffect PARTICLES2 = new ChloroBallCloudParticleEffect();
/*     */ 
/*     */   
/*     */   public DemonChloroBallProjectile(World world) {
/*  28 */     super(DokuProjectiles.DEMON_CHLORO_BALL, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public DemonChloroBallProjectile(EntityType type, World world) {
/*  33 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public DemonChloroBallProjectile(World world, double x, double y, double z) {
/*  38 */     super(DokuProjectiles.DEMON_CHLORO_BALL, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public DemonChloroBallProjectile(World world, LivingEntity player) {
/*  43 */     super(DokuProjectiles.DEMON_CHLORO_BALL, world, player);
/*     */     
/*  45 */     setDamage(12.0F);
/*  46 */     setPhysical(false);
/*  47 */     setAffectedByImbuing();
/*     */     
/*  49 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  50 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  51 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/*  56 */     hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 300, 4));
/*  57 */     this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos pos) {
/*  62 */     for (int i = 0; i < 80; i++) {
/*     */       
/*  64 */       double offsetX = WyHelper.randomWithRange(-5, 5);
/*  65 */       double offsetZ = WyHelper.randomWithRange(-5, 5);
/*     */       
/*  67 */       BlockPos location = new BlockPos(getPosX() + offsetX, getPosY(), getPosZ() + offsetZ);
/*     */       
/*  69 */       if (this.world.getBlockState(location.down()).isSolid())
/*  70 */         AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.DEMON_POISON, DefaultProtectionRules.AIR_FOLIAGE); 
/*     */     } 
/*  72 */     PARTICLES1.venomDemon = true;
/*  73 */     PARTICLES2.venomDemon = true;
/*  74 */     PARTICLES1.spawn(this.world, getPosX(), getPosY() + 1.0D, getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/*  76 */     DeathChloroBallCloudEntity smokeCloud = new DeathChloroBallCloudEntity(this.world);
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
/*  95 */         data.setColor(1.0F, 0.0F, 0.0F);
/*  96 */         data.setLife(5);
/*  97 */         data.setSize(1.3F);
/*  98 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DeathChloroBallCloudEntity
/*     */     extends EntityCloud
/*     */   {
/*     */     public DeathChloroBallCloudEntity(World world) {
/* 107 */       super(world);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void tick() {
/* 113 */       super.tick();
/* 114 */       if (!this.world.isRemote) {
/*     */         
/* 116 */         for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(getPosition(), this.world, 8.0D)) {
/*     */           
/* 118 */           if (getThrower() != target && target.isPotionActive(Effects.POISON)) {
/* 119 */             target.addPotionEffect(new EffectInstance(Effects.POISON, 200, 3));
/*     */           }
/*     */         } 
/* 122 */         if (this.ticksExisted % 2 == 0)
/* 123 */           DemonChloroBallProjectile.PARTICLES2.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doku\DemonChloroBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */