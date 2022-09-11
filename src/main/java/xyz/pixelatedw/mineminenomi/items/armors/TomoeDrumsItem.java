package xyz.pixelatedw.mineminenomi.items.armors;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.models.armors.TomoeDrumsModel;




public class TomoeDrumsItem
  extends ArmorItem
{
  public TomoeDrumsItem() {
    super((IArmorMaterial)ModArmors.TOMOE_DRUMS_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModCreativeTabs.EQUIPMENT).maxDamage(1000));
  }


  
  @OnlyIn(Dist.CLIENT)
  @Nullable
  public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A)new TomoeDrumsModel();
  }




  
  public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
    if (getDamage(stack) + amount > getMaxDamage(stack))
      return 0; 
    return super.damageItem(stack, amount, entity, onBroken);
  }


  
  @Nullable
  public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
    return String.format("%s:textures/models/armor/tomoe_drums.png", new Object[] { "mineminenomi" });
  }
}


