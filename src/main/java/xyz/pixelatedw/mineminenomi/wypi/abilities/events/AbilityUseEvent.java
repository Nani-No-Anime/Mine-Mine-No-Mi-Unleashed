/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ @Cancelable
/*    */ public class AbilityUseEvent
/*    */   extends AbilityEvent
/*    */ {
/*    */   public AbilityUseEvent(PlayerEntity player, Ability ability) {
/* 12 */     super(player, ability);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\events\AbilityUseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */