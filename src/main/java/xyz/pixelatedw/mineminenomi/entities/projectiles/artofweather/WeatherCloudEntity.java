/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*     */ import java.awt.Color;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.WeatherCloudChargedParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.WeatherCloudParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class WeatherCloudEntity extends EntityCloud {
/*  27 */   private static final ParticleEffect PARTICLES1 = (ParticleEffect)new WeatherCloudParticleEffect();
/*  28 */   private static final ParticleEffect PARTICLES2 = (ParticleEffect)new WeatherCloudChargedParticleEffect();
/*     */   
/*  30 */   private List<WeatherBallProjectile> weatherBalls = new ArrayList<>();
/*     */   
/*     */   private boolean charged = false;
/*     */   private boolean superCharged = false;
/*     */   
/*     */   public WeatherCloudEntity(World world) {
/*  36 */     super(world);
/*  37 */     setLife(200);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  43 */     super.tick();
/*     */     
/*  45 */     if (!this.world.isRemote) {
/*     */       
/*  47 */       IAbilityData props = AbilityDataCapability.get(getThrower());
/*     */       
/*  49 */       if (this.ticksExisted % 2 == 0) {
/*     */         
/*  51 */         PARTICLES1.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
/*  52 */         if (this.charged || this.superCharged) {
/*  53 */           PARTICLES2.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       } 
/*  56 */       if (getLife() <= 0 || getThrower() == null) {
/*  57 */         remove();
/*     */       }
/*  59 */       setLife(getLife() - 1);
/*     */ 
/*     */       
/*  62 */       if (this.charged) {
/*     */         
/*  64 */         List<LivingEntity> targets = WyHelper.getEntitiesNear(getPosition().down(15), this.world, 9.0D, new Class[] { LivingEntity.class });
/*  65 */         targets.remove(getThrower());
/*  66 */         targets = WyHelper.shuffle(targets);
/*     */         
/*  68 */         for (LivingEntity entity : targets) {
/*     */           
/*  70 */           double l = Math.sqrt(getDistanceSq(entity.getPosX(), entity.getPosY(), entity.getPosZ()));
/*  71 */           if (entity.getPosY() <= getPosY() && this.ticksExisted % 100 == 0 && entity.canEntityBeSeen((Entity)this)) {
/*     */             
/*  73 */             LightningEntity bolt = new LightningEntity((Entity)getThrower(), entity.getPosX(), entity.getPosY() + 14.0D, entity.getPosZ(), 0.0F, 90.0F, (float)(l + 1.0D), 5.0F);
/*  74 */             bolt.setAngle(30);
/*  75 */             bolt.setBranches(6);
/*  76 */             bolt.setSegments(15);
/*  77 */             bolt.setColor(new Color(253, 208, 35, 205));
/*  78 */             bolt.setSize(this.superCharged ? 0.05F : 0.035F);
/*  79 */             bolt.setExplosion(this.superCharged ? 1 : 0, false);
/*  80 */             bolt.setDamage(this.superCharged ? 20.0F : 10.0F);
/*  81 */             bolt.setAliveTicks(this.superCharged ? 20 : 10);
/*  82 */             this.world.addEntity((Entity)bolt);
/*     */ 
/*     */             
/*  85 */             if (!this.superCharged)
/*     */               break; 
/*  87 */             remove();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  92 */       List<WeatherBallProjectile> thunderBalls = (List<WeatherBallProjectile>)this.weatherBalls.stream().filter(ball -> ball instanceof ThunderBallProjectile).collect(Collectors.toList());
/*  93 */       List<WeatherBallProjectile> coolBalls = (List<WeatherBallProjectile>)this.weatherBalls.stream().filter(ball -> ball instanceof CoolBallProjectile).collect(Collectors.toList());
/*     */ 
/*     */ 
/*     */       
/*  97 */       ThunderstormTempo thunderstormTempo = (ThunderstormTempo)props.getUnlockedAbility((Ability)ThunderstormTempo.INSTANCE);
/*  98 */       boolean canUseAbility = (thunderstormTempo != null && !thunderstormTempo.isOnCooldown() && thunderstormTempo.canUseTempo((PlayerEntity)getThrower(), (player, check) -> {
/*     */             if (!ItemsHelper.isClimaTact(getThrower().getHeldItemMainhand())) {
/*     */               return false;
/*     */             }
/*     */             ClimaTactItem climaTact = (ClimaTactItem)getThrower().getHeldItemMainhand().getItem();
/* 103 */             return (climaTact.getLevel() >= 2 && !this.superCharged && this.charged && thunderBalls.size() >= 3);
/*     */           }));
/*     */       
/* 106 */       if (canUseAbility) {
/*     */         
/* 108 */         thunderstormTempo.use((PlayerEntity)getThrower());
/* 109 */         this.superCharged = true;
/* 110 */         thunderstormTempo.startCooldown((PlayerEntity)getThrower());
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 115 */       ThunderboltTempo thunderboltTempo = (ThunderboltTempo)props.getUnlockedAbility((Ability)ThunderboltTempo.INSTANCE);
/* 116 */       canUseAbility = (thunderboltTempo != null && !thunderboltTempo.isOnCooldown() && thunderboltTempo.canUseTempo((PlayerEntity)getThrower(), (player, check) -> 
/*     */           
/* 118 */           (thunderBalls.size() > 0 && !this.charged)));
/*     */ 
/*     */       
/* 121 */       if (canUseAbility) {
/*     */         
/* 123 */         thunderboltTempo.use((PlayerEntity)getThrower());
/* 124 */         this.charged = true;
/* 125 */         int extraLife = 0;
/* 126 */         for (WeatherBallProjectile ball : thunderBalls) {
/*     */           
/* 128 */           ball.remove();
/* 129 */           extraLife += 200;
/*     */         } 
/* 131 */         thunderboltTempo.startCooldown((PlayerEntity)getThrower());
/* 132 */         setLife(getLife() + extraLife);
/*     */       } 
/*     */ 
/*     */       
/* 136 */       RainTempo rainTempo = (RainTempo)props.getUnlockedAbility((Ability)RainTempo.INSTANCE);
/* 137 */       canUseAbility = (rainTempo != null && rainTempo.canUseTempo((PlayerEntity)getThrower(), (player, check) -> (coolBalls.size() >= 3)));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       if (canUseAbility) {
/*     */         
/* 144 */         rainTempo.use((PlayerEntity)getThrower());
/* 145 */         for (WeatherBallProjectile cb : coolBalls)
/*     */         {
/* 147 */           cb.remove();
/*     */         }
/* 149 */         remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCharged() {
/* 156 */     return this.charged;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSuperCharged() {
/* 161 */     return this.superCharged;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addWeatherBall(WeatherBallProjectile ball) {
/* 166 */     this.weatherBalls.add(ball);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\WeatherCloudEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */