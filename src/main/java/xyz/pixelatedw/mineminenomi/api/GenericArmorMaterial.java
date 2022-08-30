/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class GenericArmorMaterial
/*    */   implements IArmorMaterial
/*    */ {
/* 14 */   private static final int[] MAX_DAMAGE = new int[] { 13, 15, 16, 11 };
/*    */   
/*    */   private final String name;
/*    */   
/*    */   private final int maxDamageFactor;
/*    */   
/*    */   private final int[] damageReductionAmountArray;
/*    */   
/*    */   private final int enchantability;
/*    */   private final SoundEvent soundEvent;
/*    */   private final float toughness;
/*    */   private final Ingredient repairMaterial;
/*    */   
/*    */   public GenericArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> supplier) {
/* 28 */     this.name = name;
/* 29 */     this.maxDamageFactor = maxDamageFactor;
/* 30 */     this.damageReductionAmountArray = damageReductionAmountArray;
/* 31 */     this.enchantability = enchantability;
/* 32 */     this.soundEvent = soundEvent;
/* 33 */     this.toughness = toughness;
/* 34 */     this.repairMaterial = supplier.get();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDurability(EquipmentSlotType slotIn) {
/* 40 */     return MAX_DAMAGE[slotIn.getIndex()] * this.maxDamageFactor;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDamageReductionAmount(EquipmentSlotType slotIn) {
/* 46 */     return this.damageReductionAmountArray[slotIn.getIndex()];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEnchantability() {
/* 52 */     return this.enchantability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SoundEvent getSoundEvent() {
/* 58 */     return this.soundEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ingredient getRepairMaterial() {
/* 64 */     return this.repairMaterial;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public String getName() {
/* 71 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getToughness() {
/* 77 */     return this.toughness;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\GenericArmorMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */