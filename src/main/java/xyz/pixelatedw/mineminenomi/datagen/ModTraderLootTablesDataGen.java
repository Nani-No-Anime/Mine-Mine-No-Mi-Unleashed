package xyz.pixelatedw.mineminenomi.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.data.functions.FakeWeaponFunction;
import xyz.pixelatedw.mineminenomi.data.functions.SetInfiniteStockFunction;
import xyz.pixelatedw.mineminenomi.data.functions.SetPriceFunction;
import xyz.pixelatedw.mineminenomi.init.*;
import xyz.pixelatedw.mineminenomi.wypi.datagen.LootTablesDataGen;

import java.util.HashMap;
import java.util.Map;

public class ModTraderLootTablesDataGen extends LootTablesDataGen {
  private final Map<EntityType, LootTable.Builder> lootTables = new HashMap<>();

  
  private static final LootPool.Builder INFINITE_AMMO_BUILDER = LootPool.builder()
    .name("mineminenomi:infinite_ammo")
    .rolls((IRandomRange)RandomValueRange.of(0.0F, 1.0F))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BULLET)
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(10.0F, 20.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.KAIROSEKI_BULLET)
      .weight(50)
      .acceptFunction((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(20.0F, 50.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.ARROW)
      .weight(90)
      .acceptFunction((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3.0F, 8.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.POP_GREEN)
      .weight(50)
      .acceptFunction((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(15.0F, 20.0F))));

  
  private static final LootPool.Builder FINITE_AMMO_BUILDER = LootPool.builder()
    .name("mineminenomi:finite_ammo")
    .rolls((IRandomRange)RandomValueRange.of(1.0F, 2.0F))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.GUNPOWDER)
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(10.0F, 64.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(8.0F, 12.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.CANNON_BALL)
      .weight(70)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 30.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(20.0F, 30.0F))));

  
  private static final LootPool.Builder GUNS_BUILDER = LootPool.builder()
    .name("mineminenomi:guns")
    .rolls((IRandomRange)RandomValueRange.of(0.0F, 1.0F))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.FLINTLOCK)
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 3.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(100.0F, 150.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.GINGA_PACHINKO)
      .weight(70)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(500.0F, 750.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.BOW)
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(80.0F, 120.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.SENRIKU)
      .weight(20)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 2000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.KABUTO)
      .weight(20)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000.0F, 4000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.BLACK_KABUTO)
      .weight(5)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000.0F, 5000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.WALKER)
      .weight(20)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000.0F, 4000.0F))));

  
  private static final LootPool.Builder MELEE_WEAPONS_BUILDER = LootPool.builder()
    .name("mineminenomi:melee_weapons")
    .rolls((IRandomRange)RandomValueRange.of(0.0F, 2.0F))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.MACE)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(500.0F, 700.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.PIPE)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 3.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(300.0F, 500.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.TONFA)
      .weight(80)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(500.0F, 600.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.UMBRELLA)
      .weight(70)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(300.0F, 500.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.JITTE)
      .weight(70)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1500.0F, 3000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.KIKOKU)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(4000.0F, 5000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.KIRIBACHI)
      .weight(50)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000.0F, 5000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.MURAKUMOGIRI)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.WADO_ICHIMONJI)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.SANDAI_KITETSU)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.NIDAI_KITETSU)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.WADO_ICHIMONJI)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.YORU)
      .weight(10)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(6000.0F, 8000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.ENMA)
      .weight(5)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(6000.0F, 7000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.AME_NO_HABAKIRI)
      .weight(10)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(6000.0F, 7000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.SHUSUI)
      .weight(10)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 7000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.DURANDAL)
      .weight(50)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000.0F, 4000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.SOUL_SOLID)
      .weight(50)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000.0F, 3500.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.ACE)
      .weight(1)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(7000.0F, 10000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.MIHAWKS_KNIFE)
      .weight(1)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 5000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.MOGURA)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 8000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.DALTONS_SPADE)
      .weight(50)
      .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000.0F, 4000.0F))));

  
  private static final LootPool.Builder MATERIALS_BUILDER = LootPool.builder()
    .name("mineminenomi:materials")
    .rolls((IRandomRange)RandomValueRange.of(1.0F, 2.0F))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.LAPIS_LAZULI)
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(10.0F, 15.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(20.0F, 50.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.IRON_INGOT)
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(10.0F, 40.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(30.0F, 70.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.BLAZE_POWDER)
      .weight(80)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(2.0F, 10.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(50.0F, 100.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.SEA_KING_MEAT)
      .weight(10)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 2.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1500.0F, 3000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.SAKE_BOTTLE)
      .weight(40)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(200.0F, 500.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BOTTLE_OF_RUM)
      .weight(60)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 20.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(100.0F, 300.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.CAKE)
      .weight(60)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(400.0F, 600.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.CIGAR)
      .weight(50)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(50.0F, 100.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.SMOKING_PIPE)
      .weight(20)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(300.0F, 400.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.CIGAR_LESS)
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(150.0F, 300.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BUBBLY_CORAL)
      .weight(10)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 2000.0F))));

  
  private static final LootPool.Builder BELLY_DIALS_BUILDER = LootPool.builder()
    .name("mineminenomi:belly_dials")
    .rolls((IRandomRange)RandomValueRange.of(0.0F, 2.0F))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.EISEN_DIAL.asItem())
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(500.0F, 700.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.FLAME_DIAL.asItem())
      .weight(80)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(700.0F, 1200.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.MILKY_DIAL.asItem())
      .weight(20)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 3.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 3000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.FLASH_DIAL.asItem())
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 3.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 1500.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.BREATH_DIAL.asItem())
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 1500.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.IMPACT_DIAL.asItem())
      .weight(10)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 3000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.AXE_DIAL.asItem())
      .weight(10)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 3000.0F))));

  
  private static final LootPool.Builder EXTOL_DIALS_BUILDER = LootPool.builder()
    .name("mineminenomi:extol_dials")
    .rolls((IRandomRange)RandomValueRange.of(3.0F, 5.0F))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.EISEN_DIAL.asItem())
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 15.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(700000.0F, 1000000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.FLAME_DIAL.asItem())
      .weight(90)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 15.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1500000.0F, 2000000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.MILKY_DIAL.asItem())
      .weight(40)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 10.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000000.0F, 3000000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.FLASH_DIAL.asItem())
      .weight(40)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 15.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000000.0F, 1500000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.BREATH_DIAL.asItem())
      .weight(50)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 10.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000000.0F, 2500000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.IMPACT_DIAL.asItem())
      .weight(30)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 10.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000000.0F, 4000000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.AXE_DIAL.asItem())
      .weight(80)
      .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 10.0F)))
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000000.0F, 2500000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.REJECT_DIAL.asItem())
      .weight(1)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1.0E8F, 2.0E8F))));

  
  private static final LootPool.Builder GENERIC_EQUIPMENT_BUILDER = LootPool.builder()
    .name("mineminenomi:generic_equipment")
    .rolls((IRandomRange)RandomValueRange.of(0.0F, 2.0F))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.BANDANA)
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(100.0F, 500.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.STRAW_HAT)
      .weight(100)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(100.0F, 500.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.SNIPER_GOGGLES)
      .weight(80)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(4000.0F, 5000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.MEDIC_BAG)
      .weight(80)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000.0F, 4000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.COLA_BACKPACK)
      .weight(70)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(4000.0F, 6000.0F))))
    .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.TOMOE_DRUMS)
      .weight(5)
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(25000.0F, 50000.0F))));

  
  private static final LootPool.Builder MARINE_CLOTHES_BUILDER = LootPool.builder()
    .name("mineminenomi:marine_clothes")
    .rolls((IRandomRange)RandomValueRange.of(0.0F, 5.0F))
    .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.MARINE_HEAD))
    .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.MARINE_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.MARINE_FEET))
    .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.MARINE_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.CAPE, ModArmors.MARINE_CAPTAIN_CAPE))
    .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.VICE_ADMIRAL_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.VICE_ADMIRAL_FEET))
    .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.VICE_ADMIRAL_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.KIZARU_GLASSES))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SMOKER_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SMOKER_FEET))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SMOKER_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.CP9_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.CP9_FEET))
    .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.CP9_LEGS));

  
  private static final LootPool.Builder PIRATE_CLOTHES_BUILDER = LootPool.builder()
    .name("mineminenomi:pirate_clothes")
    .rolls((IRandomRange)RandomValueRange.of(0.0F, 5.0F))
    .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.PIRATE_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.PIRATE_FEET))
    .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.PIRATE_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.CAPE, ModArmors.PIRATE_CAPTAIN_CAPE))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.LUFFY_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.LUFFY_FEET))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.LUFFY_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.ZORO_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.ZORO_FEET))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.ZORO_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SANJI_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SANJI_FEET))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SANJI_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.FRANKY_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.FRANKY_GLASSES))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SENOR_PINK_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SENOR_PINK_HEAD))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.USOPP_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.USOPP_FEET))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.USOPP_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.KUMA_CHEST))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.KUMA_FEET))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.KUMA_LEGS))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.ACES_HAT))
    .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.CHOPPERS_HAT));

  
  public ModTraderLootTablesDataGen(DataGenerator dataGenerator) {
    super(dataGenerator);
  }


  
  public void act(DirectoryCache cache) {
    addLootTable(ModEntities.MARINE_TRADER, new LootPool.Builder[] { INFINITE_AMMO_BUILDER, FINITE_AMMO_BUILDER, GUNS_BUILDER, MELEE_WEAPONS_BUILDER, MATERIALS_BUILDER, BELLY_DIALS_BUILDER, GENERIC_EQUIPMENT_BUILDER, MARINE_CLOTHES_BUILDER });
    addLootTable(ModEntities.PIRATE_TRADER, new LootPool.Builder[] { INFINITE_AMMO_BUILDER, FINITE_AMMO_BUILDER, GUNS_BUILDER, MELEE_WEAPONS_BUILDER, MATERIALS_BUILDER, BELLY_DIALS_BUILDER, GENERIC_EQUIPMENT_BUILDER, PIRATE_CLOTHES_BUILDER });
    addLootTable(ModEntities.SKYPIEAN_TRADER, new LootPool.Builder[] { EXTOL_DIALS_BUILDER });
    
    Map<ResourceLocation, LootTable> tables = new HashMap<>();
    this.lootTables.forEach((entityType, builder) -> {
          ResourceLocation resourcelocation = ForgeRegistries.ENTITIES.getKey(entityType);
          
          ResourceLocation traderRes = new ResourceLocation(resourcelocation.getNamespace(), "entities/trader/" + resourcelocation.getPath());
          tables.put(traderRes, builder.build());
        });
    writeTables(cache, tables);
  }

  
  private static LootEntry.Builder<?> setupClothingPrice(ClothesTier tier, Item item) {
    return (LootEntry.Builder<?>)ItemLootEntry.builder((IItemProvider)item)
      .weight(tier.getWeight())
      .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(tier.getMinPrice(), tier.getMaxPrice())));
  }

  
  protected void addLootTable(EntityType entityType, LootPool.Builder... pools) {
    LootTable.Builder builder = LootTable.builder();
    for (LootPool.Builder pool : pools)
    {
      builder.addLootPool(pool);
    }
    this.lootTables.put(entityType, builder);
  }


  
  public String getName() {
    return "Traders Loot Pools";
  }
  
  private enum ClothesTier
  {
    TRASH(100, 50.0F, 100.0F),
    NAMED(30, 2000.0F, 3000.0F),
    CAPE(50, 4000.0F, 5000.0F),
    RARE(20, 5000.0F, 7000.0F);
    
    private int weight;
    private float minPrice;
    private float maxPrice;
    
    ClothesTier(int weight, float minPrice, float maxPrice) {
      this.weight = weight;
      this.minPrice = minPrice;
      this.maxPrice = maxPrice;
    }

    
    public int getWeight() {
      return this.weight;
    }

    
    public float getMinPrice() {
      return this.minPrice;
    }

    
    public float getMaxPrice() {
      return this.maxPrice;
    }
  }
}


