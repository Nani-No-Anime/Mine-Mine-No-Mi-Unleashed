/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class DorikiEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   public int doriki;
/*    */   
/*    */   public DorikiEvent(PlayerEntity player, int doriki) {
/* 14 */     super(player);
/* 15 */     this.doriki = doriki;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\DorikiEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */