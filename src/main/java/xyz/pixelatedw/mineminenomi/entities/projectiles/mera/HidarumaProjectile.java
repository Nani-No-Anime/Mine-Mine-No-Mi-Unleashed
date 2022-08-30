/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityPredicates;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class HidarumaProjectile
/*     */   extends AbilityProjectileEntity {
/*     */   public HidarumaProjectile(World world) {
/*  29 */     super(MeraProjectiles.HIDARUMA, world);
/*     */   }
/*     */   private Optional<LivingEntity> target;
/*     */   
/*     */   public HidarumaProjectile(EntityType type, World world) {
/*  34 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public HidarumaProjectile(World world, double x, double y, double z) {
/*  39 */     super(MeraProjectiles.HIDARUMA, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public HidarumaProjectile(World world, LivingEntity player) {
/*  44 */     super(MeraProjectiles.HIDARUMA, world, player);
/*     */     
/*  46 */     setDamage(3.5F);
/*  47 */     setMaxLife(120);
/*  48 */     setGravity(0.0F);
/*  49 */     setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
/*     */     
/*  51 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  52 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/*  57 */     SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 3);
/*  58 */     if (!MinecraftForge.EVENT_BUS.post(event))
/*  59 */       hitEntity.setFire(3); 
/*  60 */     remove();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(Optional<LivingEntity> target) {
/*  65 */     this.target = target;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  70 */     if (this.target == null || !this.target.isPresent() || !((LivingEntity)this.target.get()).isAlive()) {
/*     */       
/*  72 */       List<LivingEntity> list = WyHelper.getEntitiesNear(getPosition(), this.world, 16.0D, (getThrower() instanceof net.minecraft.entity.player.PlayerEntity) ? FactionHelper.getOutsideGroupPredicate(getThrower()) : EntityPredicates.NOT_SPECTATING, new Class[] { LivingEntity.class });
/*  73 */       list.remove(getThrower());
/*  74 */       list.sort(MobsHelper.ENTITY_THREAT);
/*  75 */       if (list.size() > 0)
/*     */       {
/*  77 */         this.target = list.stream().findAny();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  82 */       Vec3d dist = getPositionVec().subtract(((LivingEntity)this.target.get()).getPositionVec()).add(0.0D, -1.0D, 0.0D);
/*  83 */       double speedReduction = 12.0D;
/*  84 */       double speed = 0.5D;
/*  85 */       double xSpeed = Math.min(speed, -dist.x / speedReduction);
/*  86 */       double ySpeed = Math.min(speed, -dist.y / speedReduction);
/*  87 */       double zSpeed = Math.min(speed, -dist.z / speedReduction);
/*  88 */       setMotion(xSpeed, ySpeed, zSpeed);
/*  89 */       this.velocityChanged = true;
/*     */     } 
/*     */     
/*  92 */     if (areEyesInFluid(FluidTags.WATER)) {
/*     */       
/*  94 */       remove();
/*  95 */       getEntityWorld().addParticle(ParticleTypes.SMOKE, getPosX(), getPosY() + 1.1D, getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/*  98 */     if (!this.world.isRemote)
/*     */     {
/* 100 */       for (int i = 0; i < 1; i++) {
/*     */         
/* 102 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 103 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 104 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/* 105 */         WyHelper.spawnParticles(ParticleTypes.HAPPY_VILLAGER, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\HidarumaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */