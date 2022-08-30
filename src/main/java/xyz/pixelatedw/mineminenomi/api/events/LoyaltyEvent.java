/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class LoyaltyEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private long loyalty;
/*    */   
/*    */   public LoyaltyEvent(PlayerEntity player) {
/* 14 */     this(player, 0L);
/*    */   }
/*    */ 
/*    */   
/*    */   public LoyaltyEvent(PlayerEntity player, long bounty) {
/* 19 */     super(player);
/* 20 */     this.loyalty = bounty;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getLoyalty() {
/* 25 */     return this.loyalty;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\LoyaltyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */