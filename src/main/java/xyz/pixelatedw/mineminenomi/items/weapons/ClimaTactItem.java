package xyz.pixelatedw.mineminenomi.items.weapons;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ClimaTactItem
  extends Item {
  private int damage = 1;
  private int level = 1;


  
  private Ingredient repairIngredient;


  
  private IItemPropertyGetter openProperty;



  
  public ClimaTactItem(int damage, int level, int maxDamage) {
    super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage)); this.openProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null)
          return 0.0F;  boolean mainHandFlag = (livingEntity.getHeldItemMainhand() == itemStack); return mainHandFlag ? 1.0F : 0.0F; }); addPropertyOverride(new ResourceLocation("open"), this.openProperty);
    this.damage = damage;
    this.level = level;
  }

  
  public String checkCharge(ItemStack itemStack) {
    StringBuilder sb = new StringBuilder();
    
    if (!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("firstSlot"))) {
      sb.append(itemStack.getOrCreateTag().getString("firstSlot"));
    }
    if (!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("secondSlot"))) {
      sb.append(itemStack.getOrCreateTag().getString("secondSlot"));
    }
    if (!WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("thirdSlot"))) {
      sb.append(itemStack.getOrCreateTag().getString("thirdSlot"));
    }
    return sb.toString();
  }

  
  public void chargeWeatherBall(ItemStack itemStack, String ball) {
    if (WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("firstSlot"))) {
      itemStack.getOrCreateTag().putString("firstSlot", ball);
    } else if (WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("secondSlot"))) {
      itemStack.getOrCreateTag().putString("secondSlot", ball);
    } else if (WyHelper.isNullOrEmpty(itemStack.getOrCreateTag().getString("thirdSlot"))) {
      itemStack.getOrCreateTag().putString("thirdSlot", ball);
    } 
  }
  
  public void emptyCharge(ItemStack itemStack) {
    itemStack.getOrCreateTag().putString("firstSlot", "");
    itemStack.getOrCreateTag().putString("secondSlot", "");
    itemStack.getOrCreateTag().putString("thirdSlot", "");
  }

  
  public void setDamageModifier(ItemStack stack, double multiplier) {
    stack.getOrCreateTag().putDouble("multiplier", multiplier);
  }

  
  public void setCharged(ItemStack stack, boolean flag) {
    stack.getOrCreateTag().putBoolean("isCharged", flag);
  }

  
  public boolean isCharged(ItemStack stack) {
    return stack.getOrCreateTag().getBoolean("isCharged");
  }

  
  public int getLevel() {
    return this.level;
  }

  
  public <T extends ClimaTactItem> T setRepairIngredient(Ingredient ingredient) {
    this.repairIngredient = ingredient;
    return (T)this;
  }

  
  public Ingredient getRepairIngredient() {
    return this.repairIngredient;
  }


  
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
    HashMultimap hashMultimap = HashMultimap.create();
    
    if (equipmentSlot == EquipmentSlotType.MAINHAND) {
      
      double multiplier = stack.getOrCreateTag().getDouble("multiplier");
      if (multiplier <= 0.0D)
        multiplier = 1.0D; 
      hashMultimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damage * multiplier, AttributeModifier.Operation.ADDITION));
      hashMultimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Speed modifier", -2.8D, AttributeModifier.Operation.ADDITION));
    } 
    
    return (Multimap<String, AttributeModifier>)hashMultimap;
  }


  
  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    return (this.repairIngredient.test(repair) || super.getIsRepairable(toRepair, repair));
  }
}


