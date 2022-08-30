/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ClimaTactItem
/*     */   extends Item {
/*  20 */   private int damage = 1;
/*  21 */   private int level = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   private Ingredient repairIngredient;
/*     */ 
/*     */ 
/*     */   
/*     */   private IItemPropertyGetter openProperty;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClimaTactItem(int damage, int level, int maxDamage) {
/*  35 */     super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage)); this.openProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null)
/*  36 */           return 0.0F;  boolean mainHandFlag = (livingEntity.getHeldItemMainhand() == itemStack); return mainHandFlag ? 1.0F : 0.0F; }); addPropertyOverride(new ResourceLocation("open"), this.openProperty);
/*  37 */     this.damage = damage;
/*  38 */     this.level = level;
/*     */   }
/*     */ 
/*     */   
/*     */   public String checkCharge(ItemStack itemStack) {
/*  43 */     StringBuilder sb = new StringBuilder();
/*     */     
/*  45 */     if (!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("firstSlot"))) {
/*  46 */       sb.append(itemStack.getOrCreateTag().getString("firstSlot"));
/*     */     }
/*  48 */     if (!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("secondSlot"))) {
/*  49 */       sb.append(itemStack.getOrCreateTag().getString("secondSlot"));
/*     */     }
/*  51 */     if (!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("thirdSlot"))) {
/*  52 */       sb.append(itemStack.getOrCreateTag().getString("thirdSlot"));
/*     */     }
/*  54 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void chargeWeatherBall(ItemStack itemStack, String ball) {
/*  59 */     if (WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("firstSlot"))) {
/*  60 */       itemStack.getOrCreateTag().putString("firstSlot", ball);
/*  61 */     } else if (WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("secondSlot"))) {
/*  62 */       itemStack.getOrCreateTag().putString("secondSlot", ball);
/*  63 */     } else if (WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("thirdSlot"))) {
/*  64 */       itemStack.getOrCreateTag().putString("thirdSlot", ball);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void emptyCharge(ItemStack itemStack) {
/*  69 */     itemStack.getOrCreateTag().putString("firstSlot", "");
/*  70 */     itemStack.getOrCreateTag().putString("secondSlot", "");
/*  71 */     itemStack.getOrCreateTag().putString("thirdSlot", "");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamageModifier(ItemStack stack, double multiplier) {
/*  76 */     stack.getOrCreateTag().putDouble("multiplier", multiplier);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCharged(ItemStack stack, boolean flag) {
/*  81 */     stack.getOrCreateTag().putBoolean("isCharged", flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCharged(ItemStack stack) {
/*  86 */     return stack.getOrCreateTag().getBoolean("isCharged");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  91 */     return this.level;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ClimaTactItem> T setRepairIngredient(Ingredient ingredient) {
/*  96 */     this.repairIngredient = ingredient;
/*  97 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Ingredient getRepairIngredient() {
/* 102 */     return this.repairIngredient;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
/* 108 */     HashMultimap hashMultimap = HashMultimap.create();
/*     */     
/* 110 */     if (equipmentSlot == EquipmentSlotType.MAINHAND) {
/*     */       
/* 112 */       double multiplier = stack.getOrCreateTag().getDouble("multiplier");
/* 113 */       if (multiplier <= 0.0D)
/* 114 */         multiplier = 1.0D; 
/* 115 */       hashMultimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damage * multiplier, AttributeModifier.Operation.ADDITION));
/* 116 */       hashMultimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Speed modifier", -2.8D, AttributeModifier.Operation.ADDITION));
/*     */     } 
/*     */     
/* 119 */     return (Multimap<String, AttributeModifier>)hashMultimap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
/* 125 */     return (this.repairIngredient.test(repair) || super.getIsRepairable(toRepair, repair));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\ClimaTactItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */