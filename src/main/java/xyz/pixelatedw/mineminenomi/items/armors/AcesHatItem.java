package xyz.pixelatedw.mineminenomi.items.armors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.models.armors.AcesHatModel;

import javax.annotation.Nullable;


public class AcesHatItem
  extends ArmorItem
  implements IDyeableArmorItem
{
  public AcesHatItem() {
    super((IArmorMaterial)ModArmors.ACES_HAT_MATERIAL, EquipmentSlotType.HEAD, (new Item.Properties()).group(ModCreativeTabs.EQUIPMENT));
  }


  
  @OnlyIn(Dist.CLIENT)
  @Nullable
  public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A)new AcesHatModel();
  }




  
  @Nullable
  public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
    return String.format("%s:textures/models/armor/aces_hat.png", new Object[] { "mineminenomi" });
  }
}


