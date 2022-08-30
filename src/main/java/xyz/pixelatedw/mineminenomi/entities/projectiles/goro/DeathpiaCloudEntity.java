/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherBallProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.DeathpiaCloudParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class DeathpiaCloudEntity
/*    */   extends EntityCloud
/*    */ {
/* 21 */   private static final ParticleEffect PARTICLES1 = (ParticleEffect)new DeathpiaCloudParticleEffect();
/*    */   
/* 23 */   private List<WeatherBallProjectile> weatherBalls = new ArrayList<>();
/*    */   
/*    */   private boolean superCharged = false;
/*    */   
/*    */   public DeathpiaCloudEntity(World world) {
/* 28 */     super(world);
/* 29 */     setLife(3000);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 35 */     super.tick();
/*    */     
/* 37 */     if (!this.world.isRemote) {
/*    */       
/* 39 */       IAbilityData props = AbilityDataCapability.get(getThrower());
/*    */       
/* 41 */       if (this.ticksExisted % 2 == 0)
/*    */       {
/* 43 */         PARTICLES1.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */       }
/*    */       
/* 46 */       if (getLife() <= 0 || getThrower() == null) {
/* 47 */         remove();
/*    */       }
/* 49 */       setLife(getLife() - 1);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 54 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(getPosition().down(15), this.world, 9.0D, new Class[] { LivingEntity.class });
/* 55 */       targets.remove(getThrower());
/* 56 */       targets = WyHelper.shuffle(targets);
/*    */       
/* 58 */       for (LivingEntity entity : targets) {
/*    */         
/* 60 */         double l = Math.sqrt(getDistanceSq(entity.getPosX(), entity.getPosY(), entity.getPosZ()));
/* 61 */         if (entity.getPosY() <= getPosY() && this.ticksExisted % 100 == 0 && entity.canEntityBeSeen((Entity)this)) {
/*    */           
/* 63 */           LightningEntity bolt = new LightningEntity((Entity)getThrower(), entity.getPosX(), entity.getPosY() + 14.0D, entity.getPosZ(), 0.0F, 90.0F, (float)(l + 1.0D), 5.0F);
/* 64 */           bolt.setAngle(30);
/* 65 */           bolt.setBranches(6);
/* 66 */           bolt.setSegments(15);
/* 67 */           bolt.setSize(this.superCharged ? 0.05F : 0.035F);
/* 68 */           bolt.setExplosion(this.superCharged ? 1 : 0, false);
/* 69 */           bolt.setDamage(this.superCharged ? 20.0F : 10.0F);
/* 70 */           bolt.setAliveTicks(this.superCharged ? 20 : 10);
/* 71 */           this.world.addEntity(bolt);
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 76 */       List<WeatherBallProjectile> thunderBalls = (List<WeatherBallProjectile>)this.weatherBalls.stream().filter(ball -> ball instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.ThunderBallProjectile).collect(Collectors.toList());
/* 77 */       List list = (List)this.weatherBalls.stream().filter(ball -> ball instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.CoolBallProjectile).collect(Collectors.toList());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\DeathpiaCloudEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */