/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gura;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class GekishinProjectile extends AbilityProjectileEntity {
/*     */   public GekishinProjectile(World world) {
/*  20 */     super(GuraProjectiles.GEKISHIN, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public GekishinProjectile(EntityType type, World world) {
/*  25 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public GekishinProjectile(World world, double x, double y, double z) {
/*  30 */     super(GuraProjectiles.GEKISHIN, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public GekishinProjectile(World world, LivingEntity player) {
/*  35 */     super(GuraProjectiles.GEKISHIN, world, player);
/*  36 */     setDamage(70.0F);
/*  37 */     setMaxLife(50);
/*  38 */     setCollisionSize(2.0D);
/*  39 */     setPassThroughEntities();
/*  40 */     setPassThroughBlocks();
/*  41 */     setCollisionSize(3.0D);
/*     */     
/*  43 */     setDamageSource(this.bypassingSource);
/*     */     
/*  45 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  50 */     for (int i = 0; i < 3; i++) {
/*     */       
/*  52 */       if (i % 2 == 0) {
/*     */         
/*  54 */         ((ServerWorld)this.world)
/*  55 */           .spawnParticle((IParticleData)ParticleTypes.EXPLOSION, 
/*     */             
/*  57 */             getPosX() + WyHelper.randomDouble() * 1.5D, 
/*  58 */             getPosY() + WyHelper.randomDouble() * 1.5D, 
/*  59 */             getPosZ() + WyHelper.randomDouble() * 1.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/*  67 */         double offsetX = WyHelper.randomDouble() * 5.0D;
/*  68 */         double offsetY = WyHelper.randomDouble() * 5.0D;
/*  69 */         double offsetZ = WyHelper.randomDouble() * 5.0D;
/*     */         
/*  71 */         GenericParticleData data = new GenericParticleData((this.rand.nextDouble() > 0.5D) ? ModParticleTypes.MOKU : ModParticleTypes.MOKU2);
/*  72 */         data.setLife(3);
/*  73 */         data.setSize(10.0F);
/*  74 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     int size = 0;
/*  79 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), 8.0F);
/*  80 */     explosion.setHeightDifference(45);
/*  81 */     explosion.setStaticBlockResistance(1.35F);
/*  82 */     explosion.setProtectOwnerFromFalling(true);
/*  83 */     explosion.setExplosionSound(false);
/*  84 */     explosion.setSmokeParticles(null);
/*  85 */     explosion.setDamageSource(this.bypassingSource);
/*  86 */     explosion.setStaticDamage(15.0F);
/*  87 */     explosion.addRemovedBlocksToList();
/*  88 */     explosion.doExplosion();
/*     */     
/*  90 */     for (FallingBlockEntity entity : explosion.removedBlocks) {
/*     */       
/*  92 */       entity.setMotion(WyHelper.randomWithRange(-1, 1) / 2.0D, 0.5D + 
/*  93 */           WyHelper.randomDouble(), 
/*  94 */           WyHelper.randomWithRange(-1, 1) / 2.0D);
/*  95 */       entity.velocityChanged = true;
/*  96 */       entity.shouldDropItem = false;
/*  97 */       entity.fallTime = 1;
/*  98 */       this.world.addEntity((Entity)entity);
/*  99 */       size++;
/*     */       
/* 101 */       if (size > 50)
/*     */         break; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gura\GekishinProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */