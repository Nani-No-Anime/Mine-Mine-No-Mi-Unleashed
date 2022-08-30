/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ThunderBallProjectile
/*    */   extends WeatherBallProjectile
/*    */ {
/*    */   public ThunderBallProjectile(World world) {
/* 12 */     super(ArtOfWeatherProjectiles.THUNDER_BALL, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ThunderBallProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ThunderBallProjectile(World world, double x, double y, double z) {
/* 22 */     super(ArtOfWeatherProjectiles.THUNDER_BALL, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public ThunderBallProjectile(World world, LivingEntity player) {
/* 27 */     super(ArtOfWeatherProjectiles.THUNDER_BALL, world, player);
/* 28 */     setDamage(2.0F);
/* 29 */     setDamageSource(DamageSource.LIGHTNING_BOLT);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\ThunderBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */