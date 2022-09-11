package xyz.pixelatedw.mineminenomi.init;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.GenericEnchantment;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments {
  public static final Enchantment DIAL_IMPACT = (Enchantment)new GenericEnchantment(Enchantment.Rarity.RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND });
  public static final Enchantment DIAL_FLASH = (Enchantment)new GenericEnchantment(Enchantment.Rarity.RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND });
  public static final Enchantment KAIROSEKI = (Enchantment)new GenericEnchantment(Enchantment.Rarity.RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND });
  
  static {
    WyRegistry.registerEnchantment(DIAL_IMPACT, "Impact Dial");
    WyRegistry.registerEnchantment(DIAL_FLASH, "Flash Dial");
    WyRegistry.registerEnchantment(KAIROSEKI, "Kairoseki");
  }
}


