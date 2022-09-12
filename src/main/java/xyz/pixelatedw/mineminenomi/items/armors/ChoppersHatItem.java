package xyz.pixelatedw.mineminenomi.items.armors;

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
import xyz.pixelatedw.mineminenomi.models.armors.ChoppersHatModel;

import javax.annotation.Nullable;



public class ChoppersHatItem
  extends ArmorItem
{
  public ChoppersHatItem() {
    super((IArmorMaterial)ModArmors.CHOPPERS_HAT_MATERIAL, EquipmentSlotType.HEAD, (new Item.Properties()).group(ModCreativeTabs.EQUIPMENT));
  }


  
  @OnlyIn(Dist.CLIENT)
  @Nullable
  public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A)new ChoppersHatModel();
  }




  
  @Nullable
  public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
    return String.format("%s:textures/models/armor/chopper_hat.png", new Object[] { "mineminenomi" });
  }
}


