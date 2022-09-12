package xyz.pixelatedw.mineminenomi.items.armors;

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
import xyz.pixelatedw.mineminenomi.models.armors.CaptainCapeModel;

import javax.annotation.Nullable;



public class CaptainCapeItem
  extends DyeableArmorItem
{
  private String texture;
  private boolean hasOverlay;
  
  public CaptainCapeItem(String texture, boolean hasOverlay) {
    super((IArmorMaterial)ModArmors.CAPTAIN_CAPE_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModCreativeTabs.EQUIPMENT));
    this.texture = texture;
    this.hasOverlay = hasOverlay;
  }


  
  @OnlyIn(Dist.CLIENT)
  @Nullable
  public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A)new CaptainCapeModel();
  }




  
  @Nullable
  public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
    return String.format("%s:textures/models/armor/%s_layer_1%s.png", new Object[] { "mineminenomi", this.texture, (type == null || !this.hasOverlay) ? "" : String.format("_%s", new Object[] { type }) });
  }
}


