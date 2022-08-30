/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class BountyEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private long bounty;
/*    */   
/*    */   public BountyEvent(PlayerEntity player) {
/* 14 */     this(player, 0L);
/*    */   }
/*    */ 
/*    */   
/*    */   public BountyEvent(PlayerEntity player, long bounty) {
/* 19 */     super(player);
/* 20 */     this.bounty = bounty;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getBounty() {
/* 25 */     return this.bounty;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\BountyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */