/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class WeatherBallProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public WeatherBallProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */   protected Item weaponUsed;
/*    */   
/*    */   public WeatherBallProjectile(EntityType type, World world, double x, double y, double z) {
/* 27 */     super(type, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherBallProjectile(EntityType type, World world, LivingEntity player) {
/* 32 */     super(type, world, player);
/*    */     
/* 34 */     setMaxLife(300);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 40 */     super.tick();
/*    */     
/* 42 */     setMotion((getMotion()).x / 1.5D, (getMotion()).y, (getMotion()).z / 1.5D);
/* 43 */     if (this.ticksExisted < 100) {
/* 44 */       getMotion().add(0.0D, 0.25D, 0.0D);
/*    */     } else {
/* 46 */       setMotion(0.0D, 0.0D, 0.0D);
/*    */     } 
/* 48 */     if (this.world.isRemote || getThrower() == null || this.ticksExisted < 70) {
/*    */       return;
/*    */     }
/* 51 */     List<WeatherCloudEntity> clouds = WyHelper.getEntitiesNear(getPosition(), this.world, 5.0D, new Class[] { WeatherCloudEntity.class });
/*    */     
/* 53 */     if (clouds.size() > 0) {
/*    */       
/* 55 */       ((WeatherCloudEntity)clouds.get(0)).addWeatherBall(this);
/* 56 */       remove();
/*    */       
/*    */       return;
/*    */     } 
/* 60 */     if (this instanceof CoolBallProjectile) {
/*    */       
/* 62 */       List<HeatBallProjectile> heatBalls = WyHelper.getEntitiesNear(getPosition(), this.world, 4.0D, new Class[] { HeatBallProjectile.class });
/* 63 */       IAbilityData props = AbilityDataCapability.get(getThrower());
/* 64 */       WeatherCloudTempo ability = (WeatherCloudTempo)props.getUnlockedAbility((Ability)WeatherCloudTempo.INSTANCE);
/* 65 */       boolean canUseAbility = (ability != null && ability.canUseTempo((PlayerEntity)getThrower(), (player, check) -> (heatBalls.size() > 0)));
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 70 */       if (canUseAbility) {
/*    */         
/* 72 */         for (HeatBallProjectile heatBall : heatBalls) {
/*    */           
/* 74 */           ability.use((PlayerEntity)getThrower());
/*    */           
/* 76 */           WeatherCloudEntity cloud = new WeatherCloudEntity(this.world);
/* 77 */           cloud.setLocationAndAngles(getPosX(), getPosY() + 1.0D, getPosZ(), 0.0F, 0.0F);
/* 78 */           cloud.setMotion(0.0D, 0.0D, 0.0D);
/* 79 */           cloud.setThrower(getThrower());
/* 80 */           this.world.addEntity((Entity)cloud);
/*    */           
/* 82 */           heatBall.remove();
/*    */         } 
/* 84 */         remove();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getWeaponUsed() {
/* 91 */     return this.weaponUsed;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\WeatherBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */