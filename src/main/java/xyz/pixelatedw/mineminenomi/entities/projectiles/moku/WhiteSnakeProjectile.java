/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.EntityPredicates;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class WhiteSnakeProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   private Optional<LivingEntity> target;
/*    */   
/*    */   public WhiteSnakeProjectile(World world) {
/* 28 */     super(MokuProjectiles.WHITE_SNAKE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteSnakeProjectile(EntityType type, World world) {
/* 33 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteSnakeProjectile(World world, double x, double y, double z) {
/* 38 */     super(MokuProjectiles.WHITE_SNAKE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteSnakeProjectile(World world, LivingEntity player) {
/* 43 */     super(MokuProjectiles.WHITE_SNAKE, world, player);
/*    */     
/* 45 */     setDamage(25.0F);
/* 46 */     setMaxLife(40);
/* 47 */     setPhysical(true);
/*    */     
/* 49 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.BLINDNESS, 300, 0), new EffectInstance(Effects.POISON, 300, 0) });
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 59 */     if (this.target == null || !this.target.isPresent() || !((LivingEntity)this.target.get()).isAlive() || ((LivingEntity)this.target.get()).getPositionVec().squareDistanceTo(getPositionVec()) > 15.0D) {
/*    */       
/* 61 */       List<LivingEntity> list = WyHelper.getEntitiesNear(getPosition(), this.world, 5.0D, (getThrower() instanceof net.minecraft.entity.player.PlayerEntity) ? FactionHelper.getOutsideGroupPredicate(getThrower()) : EntityPredicates.NOT_SPECTATING, new Class[] { LivingEntity.class });
/* 62 */       list.remove(getThrower());
/* 63 */       list.sort(MobsHelper.ENTITY_THREAT);
/* 64 */       if (list.size() > 0) {
/* 65 */         this.target = list.stream().findAny();
/*    */       }
/*    */     } else {
/*    */       
/* 69 */       Vec3d dist = getPositionVec().subtract(((LivingEntity)this.target.get()).getPositionVec()).add(0.0D, -1.0D, 0.0D);
/* 70 */       double speedReduction = 12.0D;
/* 71 */       double speed = 0.6D;
/* 72 */       double xSpeed = Math.min(speed, -dist.x / speedReduction);
/* 73 */       double ySpeed = Math.min(speed, -dist.y / speedReduction);
/* 74 */       double zSpeed = Math.min(speed, -dist.z / speedReduction);
/* 75 */       setMotion(xSpeed, ySpeed, zSpeed);
/* 76 */       this.velocityChanged = true;
/*    */     } 
/*    */     
/* 79 */     if (!this.world.isRemote)
/*    */     {
/* 81 */       for (int i = 0; i < 40; i++) {
/*    */         
/* 83 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 84 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 85 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 87 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 88 */         data.setLife(10);
/* 89 */         data.setSize((float)(WyHelper.randomDouble() * 4.0D));
/* 90 */         data.setColor(0.3F, 0.3F, 0.3F);
/* 91 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\WhiteSnakeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */