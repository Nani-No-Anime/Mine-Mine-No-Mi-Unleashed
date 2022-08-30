/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class WeatherEggProjectile extends WeatherBallProjectile {
/*    */   public WeatherEggProjectile(World world) {
/* 15 */     super(ArtOfWeatherProjectiles.WEATHER_EGG, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherEggProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherEggProjectile(World world, double x, double y, double z) {
/* 25 */     super(ArtOfWeatherProjectiles.WEATHER_EGG, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherEggProjectile(World world, LivingEntity player) {
/* 30 */     super(ArtOfWeatherProjectiles.WEATHER_EGG, world, player);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 35 */     super.tick();
/*    */     
/* 37 */     if (this.world.isRemote || getThrower() == null || this.ticksExisted < 100) {
/*    */       return;
/*    */     }
/* 40 */     IAbilityData props = AbilityDataCapability.get(getThrower());
/* 41 */     WeatherCloudTempo ability = (WeatherCloudTempo)props.getUnlockedAbility((Ability)WeatherCloudTempo.INSTANCE);
/* 42 */     boolean canUseAbility = (ability != null && ability.canUseTempo((PlayerEntity)getThrower(), (player, check) -> true));
/*    */     
/* 44 */     if (canUseAbility) {
/*    */       
/* 46 */       WeatherCloudEntity cloud = new WeatherCloudEntity(this.world);
/* 47 */       cloud.setLife(300);
/* 48 */       cloud.setLocationAndAngles(getPosX(), getPosY() + 1.0D, getPosZ(), 0.0F, 0.0F);
/* 49 */       cloud.setMotion(0.0D, 0.0D, 0.0D);
/* 50 */       cloud.setThrower(getThrower());
/* 51 */       this.world.addEntity((Entity)cloud);
/*    */     } 
/*    */     
/* 54 */     remove();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\WeatherEggProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */