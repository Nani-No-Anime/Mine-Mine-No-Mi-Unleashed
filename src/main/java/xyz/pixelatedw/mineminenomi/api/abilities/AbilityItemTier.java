/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraft.item.IItemTier;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ 
/*    */ public enum AbilityItemTier implements IItemTier {
/*  7 */   DORU(500, 8.0F, 3.0F, 2, 10),
/*  8 */   WEAPON(1000, 8.0F, 0.0F, 1, 20);
/*    */   private int maxUses;
/*    */   private float efficiency;
/*    */   private float attackDamage;
/*    */   private int harvestLevel;
/*    */   private int enchantability;
/*    */   
/*    */   AbilityItemTier(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability) {
/* 16 */     this.maxUses = maxUses;
/* 17 */     this.efficiency = efficiency;
/* 18 */     this.attackDamage = attackDamage;
/* 19 */     this.harvestLevel = harvestLevel;
/* 20 */     this.enchantability = enchantability;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxUses() {
/* 25 */     return this.maxUses;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getEfficiency() {
/* 30 */     return this.efficiency;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getAttackDamage() {
/* 35 */     return this.attackDamage;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHarvestLevel() {
/* 40 */     return this.harvestLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEnchantability() {
/* 45 */     return this.enchantability;
/*    */   }
/*    */ 
/*    */   
/*    */   public Ingredient getRepairMaterial() {
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityItemTier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */