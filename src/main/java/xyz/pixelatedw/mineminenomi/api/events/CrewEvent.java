/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ 
/*    */ public class CrewEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private Crew crew;
/*    */   
/*    */   public CrewEvent(PlayerEntity player, Crew crew) {
/* 14 */     super(player);
/* 15 */     this.crew = crew;
/*    */   }
/*    */ 
/*    */   
/*    */   public Crew getCrew() {
/* 20 */     return this.crew;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Join
/*    */     extends CrewEvent
/*    */   {
/*    */     public Join(PlayerEntity player, Crew crew) {
/* 28 */       super(player, crew);
/*    */     }
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Leave
/*    */     extends CrewEvent
/*    */   {
/*    */     public Leave(PlayerEntity player, Crew crew) {
/* 37 */       super(player, crew);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\CrewEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */