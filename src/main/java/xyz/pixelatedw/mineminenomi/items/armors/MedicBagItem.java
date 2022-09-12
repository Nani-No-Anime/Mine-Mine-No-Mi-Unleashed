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
import xyz.pixelatedw.mineminenomi.models.armors.MedicBagModel;

import javax.annotation.Nullable;



public class MedicBagItem
  extends ArmorItem
{
  public MedicBagItem() {
    super((IArmorMaterial)ModArmors.MEDIC_BAG_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModCreativeTabs.EQUIPMENT).maxDamage(1000));
  }


  
  @OnlyIn(Dist.CLIENT)
  @Nullable
  public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A)new MedicBagModel();
  }




  
  @Nullable
  public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
    return String.format("%s:textures/models/armor/medic_bag.png", new Object[] { "mineminenomi" });
  }
}


