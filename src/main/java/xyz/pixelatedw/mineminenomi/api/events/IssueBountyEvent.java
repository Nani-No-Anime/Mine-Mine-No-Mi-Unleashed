/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Event.HasResult;
/*    */ 
/*    */ @HasResult
/*    */ public class IssueBountyEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private PlayerEntity issuer;
/*    */   private long bounty;
/*    */   
/*    */   public IssueBountyEvent(PlayerEntity target, PlayerEntity issuer, long bounty) {
/* 15 */     super(target);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerEntity getIssuer() {
/* 20 */     return this.issuer;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getBounty() {
/* 25 */     return this.bounty;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\IssueBountyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */