/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IEquipItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class EquippedItemObjective
/*    */   extends Objective
/*    */   implements IEquipItemObjective {
/*    */   private Item itemTarget;
/*    */   private EquipmentSlotType slotTarget;
/*    */   
/*    */   public EquippedItemObjective(String title, int count, Item item, EquipmentSlotType slot) {
/* 19 */     super(title);
/* 20 */     setMaxProgress(count);
/* 21 */     this.itemTarget = item;
/* 22 */     this.slotTarget = slot;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkEquippedItem(PlayerEntity player) {
/* 28 */     return (player.getItemStackFromSlot(this.slotTarget).getItem() == this.itemTarget);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedTitle() {
/* 34 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective.mineminenomi.%s", new Object[] { getId() }), new Object[0])).getKey();
/* 35 */     return (new TranslationTextComponent(objectiveKey, new Object[] { (new ItemStack((IItemProvider)this.itemTarget)).getDisplayName() })).getFormattedText();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\EquippedItemObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */