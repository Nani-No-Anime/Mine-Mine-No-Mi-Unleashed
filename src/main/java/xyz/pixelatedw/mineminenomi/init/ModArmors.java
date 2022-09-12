package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.GenericArmorMaterial;
import xyz.pixelatedw.mineminenomi.items.armors.*;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModArmors
{
  public static final GenericArmorMaterial BASIC_ARMOR_MATERIAL = new GenericArmorMaterial("mineminenomi:basic_armor", 100, new int[] { 1, 2, 3, 1 }, 4, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LEATHER }));
  public static final GenericArmorMaterial CAPTAIN_CAPE_MATERIAL = new GenericArmorMaterial("mineminenomi:captain_cape", 100, new int[] { 0, 0, 4, 0 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.WHITE_WOOL }));
  public static final GenericArmorMaterial COLA_BACKPACK_MATERIAL = new GenericArmorMaterial("mineminenomi:cola_backpack", 100, new int[] { 0, 0, 3, 0 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)ModItems.COLA }));
  public static final GenericArmorMaterial MEDIC_BAG_MATERIAL = new GenericArmorMaterial("mineminenomi:medic_bag", 100, new int[] { 0, 0, 3, 0 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LEATHER }));
  public static final GenericArmorMaterial TOMOE_DRUMS_MATERIAL = new GenericArmorMaterial("mineminenomi:tomoe_drums", 100, new int[] { 0, 0, 4, 0 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LEATHER, (IItemProvider)Items.IRON_INGOT, (IItemProvider)Items.GOLD_INGOT }));
  public static final GenericArmorMaterial SNIPER_GOGGLES_MATERIAL = new GenericArmorMaterial("mineminenomi:sniper_goggles", 100, new int[] { 0, 0, 0, 1 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final GenericArmorMaterial WOOTZ_STEEL_MATERIAL = new GenericArmorMaterial("mineminenomi:wootz_steel", 100, new int[] { 0, 0, 10, 0 }, 4, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT, (IItemProvider)Items.GOLD_INGOT }));
  public static final GenericArmorMaterial BANDANA_MATERIAL = new GenericArmorMaterial("mineminenomi:bandana", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.WHITE_WOOL }));
  public static final GenericArmorMaterial METALIC_FACE_MATERIAL = new GenericArmorMaterial("mineminenomi:metalic_face", 100, new int[] { 0, 0, 2, 0 }, 6, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final GenericArmorMaterial CHOPPERS_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:chopper_hat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.WHITE_WOOL }));
  public static final GenericArmorMaterial STRAW_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:strawhat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.HAY_BLOCK }));
  public static final GenericArmorMaterial ACES_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:aces_hat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LEATHER }));

  
  public static final Item PIRATE_CHEST = (Item)new BasicArmorItem("pirate", EquipmentSlotType.CHEST);
  public static final Item PIRATE_LEGS = (Item)new BasicArmorItem("pirate", EquipmentSlotType.LEGS);
  public static final Item PIRATE_FEET = (Item)new BasicArmorItem("pirate", EquipmentSlotType.FEET);
  
  public static final Item MARINE_HEAD = (Item)new BasicArmorItem("marine", EquipmentSlotType.HEAD);
  public static final Item MARINE_CHEST = (Item)new BasicArmorItem("marine", EquipmentSlotType.CHEST);
  public static final Item MARINE_LEGS = (Item)new BasicArmorItem("marine", EquipmentSlotType.LEGS);
  public static final Item MARINE_FEET = (Item)new BasicArmorItem("marine", EquipmentSlotType.FEET);
  
  public static final Item STRAW_HAT = (Item)new StrawHatItem();
  public static final Item LUFFY_CHEST = (Item)new BasicArmorItem("luffy", EquipmentSlotType.CHEST);
  public static final Item LUFFY_LEGS = (Item)new BasicArmorItem("luffy", EquipmentSlotType.LEGS);
  public static final Item LUFFY_FEET = (Item)new BasicArmorItem("luffy", EquipmentSlotType.FEET);
  
  public static final Item ZORO_CHEST = (Item)new BasicArmorItem("zoro", EquipmentSlotType.CHEST);
  public static final Item ZORO_LEGS = (Item)new BasicArmorItem("zoro", EquipmentSlotType.LEGS);
  public static final Item ZORO_FEET = (Item)new BasicArmorItem("zoro", EquipmentSlotType.FEET);
  
  public static final Item FRANKY_GLASSES = (Item)new BasicArmorItem("franky", EquipmentSlotType.HEAD);
  public static final Item FRANKY_CHEST = (Item)new BasicArmorItem("franky", EquipmentSlotType.CHEST);
  
  public static final Item SANJI_CHEST = (Item)new BasicArmorItem("sanji", EquipmentSlotType.CHEST);
  public static final Item SANJI_LEGS = (Item)new BasicArmorItem("sanji", EquipmentSlotType.LEGS);
  public static final Item SANJI_FEET = (Item)new BasicArmorItem("sanji", EquipmentSlotType.FEET);
  
  public static final Item SENOR_PINK_HEAD = (Item)new SenorPinkBonnetItem();
  public static final Item SENOR_PINK_CHEST = (Item)new BasicArmorItem("senorpink", EquipmentSlotType.CHEST);

  
  public static final Item USOPP_CHEST = (Item)new BasicArmorItem("usopp", EquipmentSlotType.CHEST);
  public static final Item USOPP_LEGS = (Item)new BasicArmorItem("usopp", EquipmentSlotType.LEGS);
  public static final Item USOPP_FEET = (Item)new BasicArmorItem("usopp", EquipmentSlotType.FEET);
  
  public static final Item VICE_ADMIRAL_CHEST = (Item)new BasicArmorItem("vice_admiral", EquipmentSlotType.CHEST, true);
  public static final Item VICE_ADMIRAL_LEGS = (Item)new BasicArmorItem("vice_admiral", EquipmentSlotType.LEGS, true);
  public static final Item VICE_ADMIRAL_FEET = (Item)new BasicArmorItem("vice_admiral", EquipmentSlotType.FEET, true);
  
  public static final Item CP9_CHEST = (Item)new BasicArmorItem("cp9", EquipmentSlotType.CHEST);
  public static final Item CP9_LEGS = (Item)new BasicArmorItem("cp9", EquipmentSlotType.LEGS);
  public static final Item CP9_FEET = (Item)new BasicArmorItem("cp9", EquipmentSlotType.FEET);
  
  public static final Item KUMA_CHEST = (Item)new BasicArmorItem("kuma", EquipmentSlotType.CHEST);
  public static final Item KUMA_LEGS = (Item)new BasicArmorItem("kuma", EquipmentSlotType.LEGS);
  public static final Item KUMA_FEET = (Item)new BasicArmorItem("kuma", EquipmentSlotType.FEET);
  
  public static final Item SMOKER_CHEST = (Item)new BasicArmorItem("smoker", EquipmentSlotType.CHEST);
  public static final Item SMOKER_LEGS = (Item)new BasicArmorItem("smoker", EquipmentSlotType.LEGS);
  public static final Item SMOKER_FEET = (Item)new BasicArmorItem("smoker", EquipmentSlotType.FEET);
  
  public static final Item CHOPPERS_HAT = (Item)new ChoppersHatItem();
  public static final Item ACES_HAT = (Item)new AcesHatItem();
  public static final Item KIZARU_GLASSES = (Item)new BasicArmorItem("kizaru_glasses", EquipmentSlotType.HEAD);
  
  public static final Item BANDANA = (Item)new BandanaArmorItem();
  public static final Item COLA_BACKPACK = (Item)new ColaBackpackItem();
  public static final Item MEDIC_BAG = (Item)new MedicBagItem();
  public static final Item TOMOE_DRUMS = (Item)new TomoeDrumsItem();
  public static final Item SNIPER_GOGGLES = (Item)new SniperGogglesItem();
  public static final Item WOOTZ_STEEL_ARMOR = (Item)new WootzSteelArmorItem();
  public static final Item MH5_GAS_MASK = (Item)new MH5GasMaskItem();
  
  public static final Item MARINE_CAPTAIN_CAPE = (Item)new CaptainCapeItem("marine_captain_cape", true);
  public static final Item PIRATE_CAPTAIN_CAPE = (Item)new CaptainCapeItem("pirate_captain_cape", true);

  
  static {
    WyRegistry.registerItem(PIRATE_CHEST, "Pirate Chest");
    WyRegistry.registerItem(PIRATE_LEGS, "Pirate Pants");
    WyRegistry.registerItem(PIRATE_FEET, "Pirate Boots");
    
    WyRegistry.registerItem(MARINE_HEAD, "Marine Hat");
    WyRegistry.registerItem(MARINE_CHEST, "Marine Chest");
    WyRegistry.registerItem(MARINE_LEGS, "Marine Pants");
    WyRegistry.registerItem(MARINE_FEET, "Marine Boots");
    
    WyRegistry.registerItem(STRAW_HAT, "Straw Hat");
    WyRegistry.registerItem(LUFFY_CHEST, "Luffy's Shirt");
    WyRegistry.registerItem(LUFFY_LEGS, "Luffy's Pants");
    WyRegistry.registerItem(LUFFY_FEET, "Luffy's Sandals");
    
    WyRegistry.registerItem(SENOR_PINK_HEAD, "Senior Pink's Bonnet");
    WyRegistry.registerItem(SENOR_PINK_CHEST, "Senior Pink's Shirt");

    
    WyRegistry.registerItem(ZORO_CHEST, "Zoro's Shirt");
    WyRegistry.registerItem(ZORO_LEGS, "Zoro's Pants");
    WyRegistry.registerItem(ZORO_FEET, "Zoro's Boots");
    
    WyRegistry.registerItem(FRANKY_GLASSES, "Franky's Glasses");
    WyRegistry.registerItem(FRANKY_CHEST, "Franky's Shirt");
    
    WyRegistry.registerItem(SANJI_CHEST, "Sanji's Shirt");
    WyRegistry.registerItem(SANJI_LEGS, "Sanji's Pants");
    WyRegistry.registerItem(SANJI_FEET, "Sanji's Shoes");
    
    WyRegistry.registerItem(USOPP_CHEST, "Usopp's Overall");
    WyRegistry.registerItem(USOPP_LEGS, "Usopp's Pants");
    WyRegistry.registerItem(USOPP_FEET, "Usopp's Boots");
    
    WyRegistry.registerItem(VICE_ADMIRAL_CHEST, "Vice Admiral Jacket");
    WyRegistry.registerItem(VICE_ADMIRAL_LEGS, "Vice Admiral Pants");
    WyRegistry.registerItem(VICE_ADMIRAL_FEET, "Vice Admiral Boots");
    
    WyRegistry.registerItem(CP9_CHEST, "CP9 Jacket");
    WyRegistry.registerItem(CP9_LEGS, "CP9 Pants");
    WyRegistry.registerItem(CP9_FEET, "CP9 Boots");
    
    WyRegistry.registerItem(KUMA_CHEST, "Kuma Shirt");
    WyRegistry.registerItem(KUMA_LEGS, "Kuma Pants");
    WyRegistry.registerItem(KUMA_FEET, "Kuma Boots");
    
    WyRegistry.registerItem(SMOKER_CHEST, "Smoker Jacket");
    WyRegistry.registerItem(SMOKER_LEGS, "Smoker Pants");
    WyRegistry.registerItem(SMOKER_FEET, "Smoker Boots");
    
    WyRegistry.registerItem(CHOPPERS_HAT, "Chopper's Hat");
    WyRegistry.registerItem(ACES_HAT, "Ace's Hat");
    WyRegistry.registerItem(KIZARU_GLASSES, "Kizaru's Glasses");
    
    WyRegistry.registerItem(BANDANA, "Bandana");
    WyRegistry.registerItem(COLA_BACKPACK, "Cola Backpack");
    WyRegistry.registerItem(MEDIC_BAG, "Medic Bag");
    WyRegistry.registerItem(TOMOE_DRUMS, "Tomoe Drums");
    WyRegistry.registerItem(SNIPER_GOGGLES, "Sniper Goggles");
    WyRegistry.registerItem(WOOTZ_STEEL_ARMOR, "Wootz Steel Armor");
    WyRegistry.registerItem(MH5_GAS_MASK, "MH5 Gas Mask");
    
    WyRegistry.registerItem(MARINE_CAPTAIN_CAPE, "Marine Captain Cape");
    WyRegistry.registerItem(PIRATE_CAPTAIN_CAPE, "Pirate Captain Cape");
  }
}


