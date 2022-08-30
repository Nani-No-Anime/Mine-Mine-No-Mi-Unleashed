/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class ChampFightProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public ChampFightProjectile(World world) {
/* 12 */     super(DoruProjectiles.CHAMP_FIGHT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChampFightProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChampFightProjectile(World world, double x, double y, double z) {
/* 22 */     super(DoruProjectiles.CHAMP_FIGHT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChampFightProjectile(World world, LivingEntity player) {
/* 27 */     super(DoruProjectiles.CHAMP_FIGHT, world, player);
/*    */     
/* 29 */     setDamage(10.0F);
/* 30 */     setMaxLife(20);
/* 31 */     setPhysical(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doru\ChampFightProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */