/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*    */ 
/*    */ public class HakiExpEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private float hakiExp;
/*    */   private HakiType hakiType;
/*    */   
/*    */   public HakiExpEvent(PlayerEntity player, float hakiExp, HakiType hakiType) {
/* 14 */     super(player);
/* 15 */     this.hakiExp = hakiExp;
/* 16 */     this.hakiType = hakiType;
/*    */   }
/*    */ 
/*    */   
/*    */   public HakiType getHakiType() {
/* 21 */     return this.hakiType;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getHakiExp() {
/* 26 */     return this.hakiExp;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\HakiExpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */