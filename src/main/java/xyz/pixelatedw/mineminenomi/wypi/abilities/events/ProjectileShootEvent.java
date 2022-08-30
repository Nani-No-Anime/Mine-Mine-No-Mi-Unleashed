/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities.events;
/*    */ 
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ @Cancelable
/*    */ public class ProjectileShootEvent
/*    */   extends Event
/*    */ {
/*    */   private AbilityProjectileEntity projectile;
/*    */   private float velocity;
/*    */   private float inaccuracy;
/*    */   
/*    */   public ProjectileShootEvent(AbilityProjectileEntity abilityProjectileEntity, float velocity, float inaccuracy) {
/* 16 */     this.projectile = abilityProjectileEntity;
/* 17 */     this.velocity = velocity;
/* 18 */     this.inaccuracy = inaccuracy;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityProjectileEntity getProjectile() {
/* 23 */     return this.projectile;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getVelocity() {
/* 28 */     return this.velocity;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getInaccuracy() {
/* 33 */     return this.inaccuracy;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\events\ProjectileShootEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */