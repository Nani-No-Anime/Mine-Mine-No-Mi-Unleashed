/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnchantmentType;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ 
/*    */ public class GenericEnchantment
/*    */   extends Enchantment
/*    */ {
/*    */   private final boolean isTreasure;
/*    */   
/*    */   public GenericEnchantment(Enchantment.Rarity rarityIn, boolean isTreasure, EquipmentSlotType... slots) {
/* 13 */     super(rarityIn, EnchantmentType.WEAPON, slots);
/* 14 */     this.isTreasure = isTreasure;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 20 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTreasureEnchantment() {
/* 25 */     return this.isTreasure;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\GenericEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */