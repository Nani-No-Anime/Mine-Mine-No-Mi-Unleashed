/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIDefaults;
/*    */ 
/*    */ public abstract class ItemAbility extends ContinuousAbility implements IParallelContinuousAbility {
/*    */   public ItemAbility(String name, APIConfig.AbilityCategory category) {
/* 13 */     super(name, category);
/*    */     
/* 15 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 23 */     if (player.getHeldItemMainhand().isEmpty() && !getItemStack(player).isEmpty()) {
/*    */       
/* 25 */       player.inventory.setInventorySlotContents(player.inventory.currentItem, getItemStack(player));
/* 26 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 30 */     if (getItemStack(player).isEmpty()) {
/* 31 */       player.sendMessage((ITextComponent)new TranslationTextComponent(APIDefaults.ABILITY_MESSAGE_EMPTY_STACK, new Object[0]));
/*    */     } else {
/* 33 */       player.sendMessage((ITextComponent)new TranslationTextComponent(APIDefaults.ABILITY_MESSAGE_ANOTHER_ITEM_IN_HAND, new Object[0]));
/* 34 */     }  return false;
/*    */   }
/*    */   
/*    */   public abstract ItemStack getItemStack(PlayerEntity paramPlayerEntity);
/*    */   
/*    */   public abstract boolean canBeActive(PlayerEntity paramPlayerEntity);
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\ItemAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */