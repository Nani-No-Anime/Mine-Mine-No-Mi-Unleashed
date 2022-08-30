/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class HeatBallProjectile
/*    */   extends WeatherBallProjectile
/*    */ {
/*    */   public HeatBallProjectile(World world) {
/* 12 */     super(ArtOfWeatherProjectiles.HEAT_BALL, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public HeatBallProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public HeatBallProjectile(World world, double x, double y, double z) {
/* 22 */     super(ArtOfWeatherProjectiles.HEAT_BALL, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public HeatBallProjectile(World world, LivingEntity player) {
/* 27 */     super(ArtOfWeatherProjectiles.HEAT_BALL, world, player);
/* 28 */     setDamage(2.0F);
/* 29 */     setDamageSource(DamageSource.ON_FIRE);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\HeatBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */