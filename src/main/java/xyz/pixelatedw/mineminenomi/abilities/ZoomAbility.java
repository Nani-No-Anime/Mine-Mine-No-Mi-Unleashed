/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class ZoomAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 12 */   public static final ZoomAbility INSTANCE = new ZoomAbility();
/*    */ 
/*    */   
/*    */   public ZoomAbility() {
/* 16 */     super("Zoom", AbilityHelper.getEquipmentCategory());
/* 17 */     setDescription("Zooms into the direction the user is looking");
/*    */     
/* 19 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 24 */     if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() != ModArmors.SNIPER_GOGGLES) {
/* 25 */       return false;
/*    */     }
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ZoomAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */