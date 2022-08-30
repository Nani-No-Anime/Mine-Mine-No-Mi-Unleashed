/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities.events;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class ProjectileBlockEvent
/*    */   extends Event
/*    */ {
/*    */   private Entity projectile;
/*    */   private boolean canBlock;
/*    */   
/*    */   public ProjectileBlockEvent(Entity entity) {
/* 15 */     this.projectile = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getProjectile() {
/* 20 */     return this.projectile;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCanBlock(boolean flag) {
/* 25 */     this.canBlock = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBlock() {
/* 30 */     return this.canBlock;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\events\ProjectileBlockEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */