/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ 
/*    */ public class YomiTriggerEvent
/*    */   extends PlayerEvent {
/*    */   public IDevilFruit oldPlayerData;
/*    */   public IDevilFruit newPlayerData;
/*    */   
/*    */   public YomiTriggerEvent(PlayerEntity player, IDevilFruit oldPlayerData, IDevilFruit newPlayerData) {
/* 13 */     super(player);
/* 14 */     this.oldPlayerData = oldPlayerData;
/* 15 */     this.newPlayerData = newPlayerData;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\YomiTriggerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */