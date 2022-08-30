/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.GenericArmorMaterial;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.AcesHatItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.BandanaArmorItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.BasicArmorItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.CaptainCapeItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.ChoppersHatItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.ColaBackpackItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.MH5GasMaskItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.MedicBagItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.SenorPinkBonnetItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.SniperGogglesItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.StrawHatItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.TomoeDrumsItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.WootzSteelArmorItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModArmors
/*     */ {
/*  30 */   public static final GenericArmorMaterial BASIC_ARMOR_MATERIAL = new GenericArmorMaterial("mineminenomi:basic_armor", 100, new int[] { 1, 2, 3, 1 }, 4, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LEATHER }));
/*  31 */   public static final GenericArmorMaterial CAPTAIN_CAPE_MATERIAL = new GenericArmorMaterial("mineminenomi:captain_cape", 100, new int[] { 0, 0, 4, 0 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.WHITE_WOOL }));
/*  32 */   public static final GenericArmorMaterial COLA_BACKPACK_MATERIAL = new GenericArmorMaterial("mineminenomi:cola_backpack", 100, new int[] { 0, 0, 3, 0 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)ModItems.COLA }));
/*  33 */   public static final GenericArmorMaterial MEDIC_BAG_MATERIAL = new GenericArmorMaterial("mineminenomi:medic_bag", 100, new int[] { 0, 0, 3, 0 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LEATHER }));
/*  34 */   public static final GenericArmorMaterial TOMOE_DRUMS_MATERIAL = new GenericArmorMaterial("mineminenomi:tomoe_drums", 100, new int[] { 0, 0, 4, 0 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LEATHER, (IItemProvider)Items.IRON_INGOT, (IItemProvider)Items.GOLD_INGOT }));
/*  35 */   public static final GenericArmorMaterial SNIPER_GOGGLES_MATERIAL = new GenericArmorMaterial("mineminenomi:sniper_goggles", 100, new int[] { 0, 0, 0, 1 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  36 */   public static final GenericArmorMaterial WOOTZ_STEEL_MATERIAL = new GenericArmorMaterial("mineminenomi:wootz_steel", 100, new int[] { 0, 0, 10, 0 }, 4, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT, (IItemProvider)Items.GOLD_INGOT }));
/*  37 */   public static final GenericArmorMaterial BANDANA_MATERIAL = new GenericArmorMaterial("mineminenomi:bandana", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.WHITE_WOOL }));
/*  38 */   public static final GenericArmorMaterial METALIC_FACE_MATERIAL = new GenericArmorMaterial("mineminenomi:metalic_face", 100, new int[] { 0, 0, 2, 0 }, 6, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  39 */   public static final GenericArmorMaterial CHOPPERS_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:chopper_hat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.WHITE_WOOL }));
/*  40 */   public static final GenericArmorMaterial STRAW_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:strawhat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.HAY_BLOCK }));
/*  41 */   public static final GenericArmorMaterial ACES_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:aces_hat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LEATHER }));
/*     */ 
/*     */   
/*  44 */   public static final Item PIRATE_CHEST = (Item)new BasicArmorItem("pirate", EquipmentSlotType.CHEST);
/*  45 */   public static final Item PIRATE_LEGS = (Item)new BasicArmorItem("pirate", EquipmentSlotType.LEGS);
/*  46 */   public static final Item PIRATE_FEET = (Item)new BasicArmorItem("pirate", EquipmentSlotType.FEET);
/*     */   
/*  48 */   public static final Item MARINE_HEAD = (Item)new BasicArmorItem("marine", EquipmentSlotType.HEAD);
/*  49 */   public static final Item MARINE_CHEST = (Item)new BasicArmorItem("marine", EquipmentSlotType.CHEST);
/*  50 */   public static final Item MARINE_LEGS = (Item)new BasicArmorItem("marine", EquipmentSlotType.LEGS);
/*  51 */   public static final Item MARINE_FEET = (Item)new BasicArmorItem("marine", EquipmentSlotType.FEET);
/*     */   
/*  53 */   public static final Item STRAW_HAT = (Item)new StrawHatItem();
/*  54 */   public static final Item LUFFY_CHEST = (Item)new BasicArmorItem("luffy", EquipmentSlotType.CHEST);
/*  55 */   public static final Item LUFFY_LEGS = (Item)new BasicArmorItem("luffy", EquipmentSlotType.LEGS);
/*  56 */   public static final Item LUFFY_FEET = (Item)new BasicArmorItem("luffy", EquipmentSlotType.FEET);
/*     */   
/*  58 */   public static final Item ZORO_CHEST = (Item)new BasicArmorItem("zoro", EquipmentSlotType.CHEST);
/*  59 */   public static final Item ZORO_LEGS = (Item)new BasicArmorItem("zoro", EquipmentSlotType.LEGS);
/*  60 */   public static final Item ZORO_FEET = (Item)new BasicArmorItem("zoro", EquipmentSlotType.FEET);
/*     */   
/*  62 */   public static final Item FRANKY_GLASSES = (Item)new BasicArmorItem("franky", EquipmentSlotType.HEAD);
/*  63 */   public static final Item FRANKY_CHEST = (Item)new BasicArmorItem("franky", EquipmentSlotType.CHEST);
/*     */   
/*  65 */   public static final Item SANJI_CHEST = (Item)new BasicArmorItem("sanji", EquipmentSlotType.CHEST);
/*  66 */   public static final Item SANJI_LEGS = (Item)new BasicArmorItem("sanji", EquipmentSlotType.LEGS);
/*  67 */   public static final Item SANJI_FEET = (Item)new BasicArmorItem("sanji", EquipmentSlotType.FEET);
/*     */   
/*  69 */   public static final Item SENOR_PINK_HEAD = (Item)new SenorPinkBonnetItem();
/*  70 */   public static final Item SENOR_PINK_CHEST = (Item)new BasicArmorItem("senorpink", EquipmentSlotType.CHEST);
/*     */ 
/*     */   
/*  73 */   public static final Item USOPP_CHEST = (Item)new BasicArmorItem("usopp", EquipmentSlotType.CHEST);
/*  74 */   public static final Item USOPP_LEGS = (Item)new BasicArmorItem("usopp", EquipmentSlotType.LEGS);
/*  75 */   public static final Item USOPP_FEET = (Item)new BasicArmorItem("usopp", EquipmentSlotType.FEET);
/*     */   
/*  77 */   public static final Item VICE_ADMIRAL_CHEST = (Item)new BasicArmorItem("vice_admiral", EquipmentSlotType.CHEST, true);
/*  78 */   public static final Item VICE_ADMIRAL_LEGS = (Item)new BasicArmorItem("vice_admiral", EquipmentSlotType.LEGS, true);
/*  79 */   public static final Item VICE_ADMIRAL_FEET = (Item)new BasicArmorItem("vice_admiral", EquipmentSlotType.FEET, true);
/*     */   
/*  81 */   public static final Item CP9_CHEST = (Item)new BasicArmorItem("cp9", EquipmentSlotType.CHEST);
/*  82 */   public static final Item CP9_LEGS = (Item)new BasicArmorItem("cp9", EquipmentSlotType.LEGS);
/*  83 */   public static final Item CP9_FEET = (Item)new BasicArmorItem("cp9", EquipmentSlotType.FEET);
/*     */   
/*  85 */   public static final Item KUMA_CHEST = (Item)new BasicArmorItem("kuma", EquipmentSlotType.CHEST);
/*  86 */   public static final Item KUMA_LEGS = (Item)new BasicArmorItem("kuma", EquipmentSlotType.LEGS);
/*  87 */   public static final Item KUMA_FEET = (Item)new BasicArmorItem("kuma", EquipmentSlotType.FEET);
/*     */   
/*  89 */   public static final Item SMOKER_CHEST = (Item)new BasicArmorItem("smoker", EquipmentSlotType.CHEST);
/*  90 */   public static final Item SMOKER_LEGS = (Item)new BasicArmorItem("smoker", EquipmentSlotType.LEGS);
/*  91 */   public static final Item SMOKER_FEET = (Item)new BasicArmorItem("smoker", EquipmentSlotType.FEET);
/*     */   
/*  93 */   public static final Item CHOPPERS_HAT = (Item)new ChoppersHatItem();
/*  94 */   public static final Item ACES_HAT = (Item)new AcesHatItem();
/*  95 */   public static final Item KIZARU_GLASSES = (Item)new BasicArmorItem("kizaru_glasses", EquipmentSlotType.HEAD);
/*     */   
/*  97 */   public static final Item BANDANA = (Item)new BandanaArmorItem();
/*  98 */   public static final Item COLA_BACKPACK = (Item)new ColaBackpackItem();
/*  99 */   public static final Item MEDIC_BAG = (Item)new MedicBagItem();
/* 100 */   public static final Item TOMOE_DRUMS = (Item)new TomoeDrumsItem();
/* 101 */   public static final Item SNIPER_GOGGLES = (Item)new SniperGogglesItem();
/* 102 */   public static final Item WOOTZ_STEEL_ARMOR = (Item)new WootzSteelArmorItem();
/* 103 */   public static final Item MH5_GAS_MASK = (Item)new MH5GasMaskItem();
/*     */   
/* 105 */   public static final Item MARINE_CAPTAIN_CAPE = (Item)new CaptainCapeItem("marine_captain_cape", true);
/* 106 */   public static final Item PIRATE_CAPTAIN_CAPE = (Item)new CaptainCapeItem("pirate_captain_cape", true);
/*     */ 
/*     */   
/*     */   static {
/* 110 */     WyRegistry.registerItem(PIRATE_CHEST, "Pirate Chest");
/* 111 */     WyRegistry.registerItem(PIRATE_LEGS, "Pirate Pants");
/* 112 */     WyRegistry.registerItem(PIRATE_FEET, "Pirate Boots");
/*     */     
/* 114 */     WyRegistry.registerItem(MARINE_HEAD, "Marine Hat");
/* 115 */     WyRegistry.registerItem(MARINE_CHEST, "Marine Chest");
/* 116 */     WyRegistry.registerItem(MARINE_LEGS, "Marine Pants");
/* 117 */     WyRegistry.registerItem(MARINE_FEET, "Marine Boots");
/*     */     
/* 119 */     WyRegistry.registerItem(STRAW_HAT, "Straw Hat");
/* 120 */     WyRegistry.registerItem(LUFFY_CHEST, "Luffy's Shirt");
/* 121 */     WyRegistry.registerItem(LUFFY_LEGS, "Luffy's Pants");
/* 122 */     WyRegistry.registerItem(LUFFY_FEET, "Luffy's Sandals");
/*     */     
/* 124 */     WyRegistry.registerItem(SENOR_PINK_HEAD, "Senior Pink's Bonnet");
/* 125 */     WyRegistry.registerItem(SENOR_PINK_CHEST, "Senior Pink's Shirt");
/*     */ 
/*     */     
/* 128 */     WyRegistry.registerItem(ZORO_CHEST, "Zoro's Shirt");
/* 129 */     WyRegistry.registerItem(ZORO_LEGS, "Zoro's Pants");
/* 130 */     WyRegistry.registerItem(ZORO_FEET, "Zoro's Boots");
/*     */     
/* 132 */     WyRegistry.registerItem(FRANKY_GLASSES, "Franky's Glasses");
/* 133 */     WyRegistry.registerItem(FRANKY_CHEST, "Franky's Shirt");
/*     */     
/* 135 */     WyRegistry.registerItem(SANJI_CHEST, "Sanji's Shirt");
/* 136 */     WyRegistry.registerItem(SANJI_LEGS, "Sanji's Pants");
/* 137 */     WyRegistry.registerItem(SANJI_FEET, "Sanji's Shoes");
/*     */     
/* 139 */     WyRegistry.registerItem(USOPP_CHEST, "Usopp's Overall");
/* 140 */     WyRegistry.registerItem(USOPP_LEGS, "Usopp's Pants");
/* 141 */     WyRegistry.registerItem(USOPP_FEET, "Usopp's Boots");
/*     */     
/* 143 */     WyRegistry.registerItem(VICE_ADMIRAL_CHEST, "Vice Admiral Jacket");
/* 144 */     WyRegistry.registerItem(VICE_ADMIRAL_LEGS, "Vice Admiral Pants");
/* 145 */     WyRegistry.registerItem(VICE_ADMIRAL_FEET, "Vice Admiral Boots");
/*     */     
/* 147 */     WyRegistry.registerItem(CP9_CHEST, "CP9 Jacket");
/* 148 */     WyRegistry.registerItem(CP9_LEGS, "CP9 Pants");
/* 149 */     WyRegistry.registerItem(CP9_FEET, "CP9 Boots");
/*     */     
/* 151 */     WyRegistry.registerItem(KUMA_CHEST, "Kuma Shirt");
/* 152 */     WyRegistry.registerItem(KUMA_LEGS, "Kuma Pants");
/* 153 */     WyRegistry.registerItem(KUMA_FEET, "Kuma Boots");
/*     */     
/* 155 */     WyRegistry.registerItem(SMOKER_CHEST, "Smoker Jacket");
/* 156 */     WyRegistry.registerItem(SMOKER_LEGS, "Smoker Pants");
/* 157 */     WyRegistry.registerItem(SMOKER_FEET, "Smoker Boots");
/*     */     
/* 159 */     WyRegistry.registerItem(CHOPPERS_HAT, "Chopper's Hat");
/* 160 */     WyRegistry.registerItem(ACES_HAT, "Ace's Hat");
/* 161 */     WyRegistry.registerItem(KIZARU_GLASSES, "Kizaru's Glasses");
/*     */     
/* 163 */     WyRegistry.registerItem(BANDANA, "Bandana");
/* 164 */     WyRegistry.registerItem(COLA_BACKPACK, "Cola Backpack");
/* 165 */     WyRegistry.registerItem(MEDIC_BAG, "Medic Bag");
/* 166 */     WyRegistry.registerItem(TOMOE_DRUMS, "Tomoe Drums");
/* 167 */     WyRegistry.registerItem(SNIPER_GOGGLES, "Sniper Goggles");
/* 168 */     WyRegistry.registerItem(WOOTZ_STEEL_ARMOR, "Wootz Steel Armor");
/* 169 */     WyRegistry.registerItem(MH5_GAS_MASK, "MH5 Gas Mask");
/*     */     
/* 171 */     WyRegistry.registerItem(MARINE_CAPTAIN_CAPE, "Marine Captain Cape");
/* 172 */     WyRegistry.registerItem(PIRATE_CAPTAIN_CAPE, "Pirate Captain Cape");
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModArmors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */