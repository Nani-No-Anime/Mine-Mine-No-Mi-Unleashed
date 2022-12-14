package xyz.pixelatedw.mineminenomi.items.armors;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

import javax.annotation.Nullable;


public class BasicArmorItem
  extends DyeableArmorItem
{
  private String name;
  private boolean hasOverlay;
  
  public BasicArmorItem(String name, EquipmentSlotType type) {
    this(name, type, false);
  }

  
  public BasicArmorItem(String name, EquipmentSlotType type, boolean hasOverlay) {
    super((IArmorMaterial)ModArmors.BASIC_ARMOR_MATERIAL, type, (new Item.Properties()).group(ModCreativeTabs.EQUIPMENT));
    this.name = name;
    this.hasOverlay = hasOverlay;
  }


  
  @OnlyIn(Dist.CLIENT)
  @Nullable
  public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A)new BipedModel(0.5F);
  }




  
  @Nullable
  public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
    return String.format("%s:textures/models/armor/%s_%d%s.png", new Object[] { "mineminenomi", this.name, Integer.valueOf((slot == EquipmentSlotType.LEGS) ? 2 : 1), (type == null || !this.hasOverlay) ? "" : String.format("_%s", new Object[] { type }) });
  }
}


