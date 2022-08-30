/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class PopGreenProjectile extends AbilityProjectileEntity {
/*     */   private PopGreenType type;
/*     */   
/*  20 */   public PopGreenProjectile(World world) { super(ExtraProjectiles.POP_GREEN, world);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     this.type = null; } public PopGreenProjectile(EntityType type, World world) { super(type, world); this.type = null; } public PopGreenProjectile(World world, double x, double y, double z) { super(ExtraProjectiles.POP_GREEN, world, x, y, z); this.type = null; }
/*     */ 
/*     */   
/*     */   public PopGreenProjectile(World world, LivingEntity player, PopGreenType type) {
/*  37 */     super(ExtraProjectiles.POP_GREEN, world, player); this.type = null;
/*  38 */     this.type = type;
/*  39 */     setDamage((this.type == PopGreenType.IMPACT_WOLF) ? 25.0F : 4.0F);
/*  40 */     setPhysical(false);
/*  41 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  42 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  43 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  48 */     if (this.type == PopGreenType.IMPACT_WOLF && 
/*  49 */       !this.world.isRemote) {
/*     */       int i;
/*  51 */       for (i = 0; i < 8; i++) {
/*     */         
/*  53 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  54 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  55 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  57 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/*  58 */         data.setLife(10);
/*  59 */         data.setSize(2.5F);
/*  60 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */       
/*  63 */       for (i = 0; i < 4; i++) {
/*     */         
/*  65 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  66 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  67 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  69 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA2);
/*  70 */         data.setLife(7);
/*  71 */         data.setSize(1.0F);
/*  72 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void onBlockImpactEvent(BlockPos block) {
/*     */     ExplosionAbility bakuhatsu_explosion;
/*     */     ExplosionAbility wolf_explosion;
/*  79 */     switch (this.type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case BAKUHATSU:
/*  87 */         bakuhatsu_explosion = AbilityHelper.newExplosion((Entity)this.owner, this.world, block.getX(), block.getY(), block.getZ(), 1.0F);
/*  88 */         bakuhatsu_explosion.setExplosionSound(true);
/*  89 */         bakuhatsu_explosion.setDestroyBlocks(true);
/*  90 */         bakuhatsu_explosion.setFireAfterExplosion(false);
/*  91 */         bakuhatsu_explosion.setDamageOwner(true);
/*  92 */         bakuhatsu_explosion.setDamageEntities(true);
/*  93 */         bakuhatsu_explosion.doExplosion();
/*     */         break;
/*     */       case IMPACT_WOLF:
/*  96 */         wolf_explosion = AbilityHelper.newExplosion((Entity)this.owner, this.world, block.getX(), block.getY(), block.getZ(), 3.0F);
/*  97 */         wolf_explosion.setExplosionSound(true);
/*  98 */         wolf_explosion.setDestroyBlocks(true);
/*  99 */         wolf_explosion.setFireAfterExplosion(true);
/* 100 */         wolf_explosion.setDamageOwner(true);
/* 101 */         wolf_explosion.setDamageEntities(true);
/* 102 */         wolf_explosion.doExplosion();
/*     */         break;
/*     */     } 
/*     */   } private void onEntityImpactEvent(LivingEntity entity) {
/*     */     int i;
/*     */     ExplosionAbility bakuhatsu_explosion;
/*     */     Vec3d speed;
/*     */     ExplosionAbility wolf_explosion;
/* 110 */     switch (this.type) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case TAKE_JAVELIN:
/* 116 */         for (i = 0; i < 7; i++) {
/* 117 */           entity.hurtResistantTime = 0;
/* 118 */           BambooPillarEntity pillar = new BambooPillarEntity(entity.world, entity);
/* 119 */           pillar.rotationPitch = 90.0F;
/* 120 */           pillar.setPosition(entity.getPosX() + Math.random(), entity.getPosY(), entity.getPosZ() + Math.random());
/* 121 */           pillar.setMotion(0.0D, 0.4D, 0.0D);
/* 122 */           entity.world.addEntity((Entity)pillar);
/* 123 */           entity.hurtResistantTime = 0;
/* 124 */           entity.attackEntityFrom(pillar.source, 1.0F);
/*     */         } 
/*     */         break;
/*     */       case BAKUHATSU:
/* 128 */         bakuhatsu_explosion = AbilityHelper.newExplosion((Entity)this.owner, this.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 1.0F);
/* 129 */         bakuhatsu_explosion.setExplosionSound(true);
/* 130 */         bakuhatsu_explosion.setDestroyBlocks(true);
/* 131 */         bakuhatsu_explosion.setFireAfterExplosion(false);
/* 132 */         bakuhatsu_explosion.setDamageOwner(true);
/* 133 */         bakuhatsu_explosion.setDamageEntities(true);
/* 134 */         bakuhatsu_explosion.doExplosion();
/*     */         break;
/*     */       case TRAMPOLIA:
/* 137 */         speed = WyHelper.propulsion(entity, 2.0D, 2.0D);
/* 138 */         entity.setMotion(speed.x, 0.8D, speed.z);
/* 139 */         entity.velocityChanged = true;
/*     */         break;
/*     */       case IMPACT_WOLF:
/* 142 */         wolf_explosion = AbilityHelper.newExplosion((Entity)this.owner, this.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 3.0F);
/* 143 */         wolf_explosion.setExplosionSound(true);
/* 144 */         wolf_explosion.setDestroyBlocks(true);
/* 145 */         wolf_explosion.setFireAfterExplosion(true);
/* 146 */         wolf_explosion.setDamageOwner(true);
/* 147 */         wolf_explosion.setDamageEntities(true);
/* 148 */         wolf_explosion.doExplosion();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum PopGreenType
/*     */   {
/* 158 */     NONE, DEVIL, RAFFLESIA, TAKE_JAVELIN, BAKUHATSU, HUMANDRAKE, TRAMPOLIA, IMPACT_WOLF;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\PopGreenProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */