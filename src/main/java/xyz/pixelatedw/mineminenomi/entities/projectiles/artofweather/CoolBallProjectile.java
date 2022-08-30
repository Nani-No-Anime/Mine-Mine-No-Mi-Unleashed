/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class CoolBallProjectile
/*    */   extends WeatherBallProjectile
/*    */ {
/*    */   public CoolBallProjectile(World world) {
/* 11 */     super(ArtOfWeatherProjectiles.COOL_BALL, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CoolBallProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CoolBallProjectile(World world, double x, double y, double z) {
/* 21 */     super(ArtOfWeatherProjectiles.COOL_BALL, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public CoolBallProjectile(World world, LivingEntity player) {
/* 26 */     super(ArtOfWeatherProjectiles.COOL_BALL, world, player);
/* 27 */     setDamage(0.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\CoolBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */