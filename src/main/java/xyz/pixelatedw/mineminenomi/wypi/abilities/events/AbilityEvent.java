/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class AbilityEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private Ability ability;
/*    */   
/*    */   public AbilityEvent(PlayerEntity player, Ability ability) {
/* 13 */     super(player);
/* 14 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 19 */     return this.ability;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\events\AbilityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */