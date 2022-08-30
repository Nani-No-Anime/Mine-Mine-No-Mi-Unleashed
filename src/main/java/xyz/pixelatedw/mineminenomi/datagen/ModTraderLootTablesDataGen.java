/*     */ package xyz.pixelatedw.mineminenomi.datagen;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.DirectoryCache;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.storage.loot.IRandomRange;
/*     */ import net.minecraft.world.storage.loot.ItemLootEntry;
/*     */ import net.minecraft.world.storage.loot.LootEntry;
/*     */ import net.minecraft.world.storage.loot.LootPool;
/*     */ import net.minecraft.world.storage.loot.LootTable;
/*     */ import net.minecraft.world.storage.loot.RandomValueRange;
/*     */ import net.minecraft.world.storage.loot.functions.ILootFunction;
/*     */ import net.minecraft.world.storage.loot.functions.SetCount;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.FakeWeaponFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetInfiniteStockFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetPriceFunction;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.datagen.LootTablesDataGen;
/*     */ 
/*     */ public class ModTraderLootTablesDataGen extends LootTablesDataGen {
/*  32 */   private final Map<EntityType, LootTable.Builder> lootTables = new HashMap<>();
/*     */ 
/*     */   
/*  35 */   private static final LootPool.Builder INFINITE_AMMO_BUILDER = LootPool.builder()
/*  36 */     .name("mineminenomi:infinite_ammo")
/*  37 */     .rolls((IRandomRange)RandomValueRange.of(0.0F, 1.0F))
/*  38 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BULLET)
/*  39 */       .weight(100)
/*  40 */       .acceptFunction((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
/*  41 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(10.0F, 20.0F))))
/*  42 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.KAIROSEKI_BULLET)
/*  43 */       .weight(50)
/*  44 */       .acceptFunction((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
/*  45 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(20.0F, 50.0F))))
/*  46 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.ARROW)
/*  47 */       .weight(90)
/*  48 */       .acceptFunction((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
/*  49 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3.0F, 8.0F))))
/*  50 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.POP_GREEN)
/*  51 */       .weight(50)
/*  52 */       .acceptFunction((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
/*  53 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(15.0F, 20.0F))));
/*     */ 
/*     */   
/*  56 */   private static final LootPool.Builder FINITE_AMMO_BUILDER = LootPool.builder()
/*  57 */     .name("mineminenomi:finite_ammo")
/*  58 */     .rolls((IRandomRange)RandomValueRange.of(1.0F, 2.0F))
/*  59 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.GUNPOWDER)
/*  60 */       .weight(100)
/*  61 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(10.0F, 64.0F)))
/*  62 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(8.0F, 12.0F))))
/*  63 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.CANNON_BALL)
/*  64 */       .weight(70)
/*  65 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 30.0F)))
/*  66 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(20.0F, 30.0F))));
/*     */ 
/*     */   
/*  69 */   private static final LootPool.Builder GUNS_BUILDER = LootPool.builder()
/*  70 */     .name("mineminenomi:guns")
/*  71 */     .rolls((IRandomRange)RandomValueRange.of(0.0F, 1.0F))
/*  72 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.FLINTLOCK)
/*  73 */       .weight(100)
/*  74 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 3.0F)))
/*  75 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(100.0F, 150.0F))))
/*  76 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.GINGA_PACHINKO)
/*  77 */       .weight(70)
/*  78 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(500.0F, 750.0F))))
/*  79 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.BOW)
/*  80 */       .weight(100)
/*  81 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
/*  82 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(80.0F, 120.0F))))
/*  83 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.SENRIKU)
/*  84 */       .weight(20)
/*  85 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
/*  86 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 2000.0F))))
/*  87 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.KABUTO)
/*  88 */       .weight(20)
/*  89 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000.0F, 4000.0F))))
/*  90 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.BLACK_KABUTO)
/*  91 */       .weight(5)
/*  92 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000.0F, 5000.0F))))
/*  93 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.WALKER)
/*  94 */       .weight(20)
/*  95 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000.0F, 4000.0F))));
/*     */ 
/*     */   
/*  98 */   private static final LootPool.Builder MELEE_WEAPONS_BUILDER = LootPool.builder()
/*  99 */     .name("mineminenomi:melee_weapons")
/* 100 */     .rolls((IRandomRange)RandomValueRange.of(0.0F, 2.0F))
/* 101 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.MACE)
/* 102 */       .weight(30)
/* 103 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
/* 104 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(500.0F, 700.0F))))
/* 105 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.PIPE)
/* 106 */       .weight(30)
/* 107 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 3.0F)))
/* 108 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(300.0F, 500.0F))))
/* 109 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.TONFA)
/* 110 */       .weight(80)
/* 111 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
/* 112 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(500.0F, 600.0F))))
/* 113 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.UMBRELLA)
/* 114 */       .weight(70)
/* 115 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(300.0F, 500.0F))))
/* 116 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.JITTE)
/* 117 */       .weight(70)
/* 118 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1500.0F, 3000.0F))))
/* 119 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.KIKOKU)
/* 120 */       .weight(30)
/* 121 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 122 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(4000.0F, 5000.0F))))
/* 123 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.KIRIBACHI)
/* 124 */       .weight(50)
/* 125 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 126 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000.0F, 5000.0F))))
/* 127 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.MURAKUMOGIRI)
/* 128 */       .weight(30)
/* 129 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 130 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
/* 131 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.WADO_ICHIMONJI)
/* 132 */       .weight(30)
/* 133 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 134 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
/* 135 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.SANDAI_KITETSU)
/* 136 */       .weight(30)
/* 137 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 138 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
/* 139 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.NIDAI_KITETSU)
/* 140 */       .weight(30)
/* 141 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 142 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
/* 143 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.WADO_ICHIMONJI)
/* 144 */       .weight(30)
/* 145 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 146 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 6000.0F))))
/* 147 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.YORU)
/* 148 */       .weight(10)
/* 149 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 150 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(6000.0F, 8000.0F))))
/* 151 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.ENMA)
/* 152 */       .weight(5)
/* 153 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 154 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(6000.0F, 7000.0F))))
/* 155 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.AME_NO_HABAKIRI)
/* 156 */       .weight(10)
/* 157 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 158 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(6000.0F, 7000.0F))))
/* 159 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.SHUSUI)
/* 160 */       .weight(10)
/* 161 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 162 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 7000.0F))))
/* 163 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.DURANDAL)
/* 164 */       .weight(50)
/* 165 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 166 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000.0F, 4000.0F))))
/* 167 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.SOUL_SOLID)
/* 168 */       .weight(50)
/* 169 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 170 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000.0F, 3500.0F))))
/* 171 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.ACE)
/* 172 */       .weight(1)
/* 173 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 174 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(7000.0F, 10000.0F))))
/* 175 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.MIHAWKS_KNIFE)
/* 176 */       .weight(1)
/* 177 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 178 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 5000.0F))))
/* 179 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.MOGURA)
/* 180 */       .weight(30)
/* 181 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 182 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(5000.0F, 8000.0F))))
/* 183 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModWeapons.DALTONS_SPADE)
/* 184 */       .weight(50)
/* 185 */       .acceptFunction((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 186 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000.0F, 4000.0F))));
/*     */ 
/*     */   
/* 189 */   private static final LootPool.Builder MATERIALS_BUILDER = LootPool.builder()
/* 190 */     .name("mineminenomi:materials")
/* 191 */     .rolls((IRandomRange)RandomValueRange.of(1.0F, 2.0F))
/* 192 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.LAPIS_LAZULI)
/* 193 */       .weight(100)
/* 194 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(10.0F, 15.0F)))
/* 195 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(20.0F, 50.0F))))
/* 196 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.IRON_INGOT)
/* 197 */       .weight(100)
/* 198 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(10.0F, 40.0F)))
/* 199 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(30.0F, 70.0F))))
/* 200 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.BLAZE_POWDER)
/* 201 */       .weight(80)
/* 202 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(2.0F, 10.0F)))
/* 203 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(50.0F, 100.0F))))
/* 204 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.SEA_KING_MEAT)
/* 205 */       .weight(10)
/* 206 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 2.0F)))
/* 207 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1500.0F, 3000.0F))))
/* 208 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.SAKE_BOTTLE)
/* 209 */       .weight(40)
/* 210 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F)))
/* 211 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(200.0F, 500.0F))))
/* 212 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BOTTLE_OF_RUM)
/* 213 */       .weight(60)
/* 214 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 20.0F)))
/* 215 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(100.0F, 300.0F))))
/* 216 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.CAKE)
/* 217 */       .weight(60)
/* 218 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(400.0F, 600.0F))))
/* 219 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.CIGAR)
/* 220 */       .weight(50)
/* 221 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(50.0F, 100.0F))))
/* 222 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.SMOKING_PIPE)
/* 223 */       .weight(20)
/* 224 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(300.0F, 400.0F))))
/* 225 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.CIGAR_LESS)
/* 226 */       .weight(30)
/* 227 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(150.0F, 300.0F))))
/* 228 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BUBBLY_CORAL)
/* 229 */       .weight(10)
/* 230 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 2000.0F))));
/*     */ 
/*     */   
/* 233 */   private static final LootPool.Builder BELLY_DIALS_BUILDER = LootPool.builder()
/* 234 */     .name("mineminenomi:belly_dials")
/* 235 */     .rolls((IRandomRange)RandomValueRange.of(0.0F, 2.0F))
/* 236 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.EISEN_DIAL.asItem())
/* 237 */       .weight(100)
/* 238 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F)))
/* 239 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(500.0F, 700.0F))))
/* 240 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.FLAME_DIAL.asItem())
/* 241 */       .weight(80)
/* 242 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F)))
/* 243 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(700.0F, 1200.0F))))
/* 244 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.MILKY_DIAL.asItem())
/* 245 */       .weight(20)
/* 246 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 3.0F)))
/* 247 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 3000.0F))))
/* 248 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.FLASH_DIAL.asItem())
/* 249 */       .weight(30)
/* 250 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 3.0F)))
/* 251 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 1500.0F))))
/* 252 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.BREATH_DIAL.asItem())
/* 253 */       .weight(30)
/* 254 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F)))
/* 255 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 1500.0F))))
/* 256 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.IMPACT_DIAL.asItem())
/* 257 */       .weight(10)
/* 258 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
/* 259 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 3000.0F))))
/* 260 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.AXE_DIAL.asItem())
/* 261 */       .weight(10)
/* 262 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F)))
/* 263 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000.0F, 3000.0F))));
/*     */ 
/*     */   
/* 266 */   private static final LootPool.Builder EXTOL_DIALS_BUILDER = LootPool.builder()
/* 267 */     .name("mineminenomi:extol_dials")
/* 268 */     .rolls((IRandomRange)RandomValueRange.of(3.0F, 5.0F))
/* 269 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.EISEN_DIAL.asItem())
/* 270 */       .weight(100)
/* 271 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 15.0F)))
/* 272 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(700000.0F, 1000000.0F))))
/* 273 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.FLAME_DIAL.asItem())
/* 274 */       .weight(90)
/* 275 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 15.0F)))
/* 276 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1500000.0F, 2000000.0F))))
/* 277 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.MILKY_DIAL.asItem())
/* 278 */       .weight(40)
/* 279 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 10.0F)))
/* 280 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000000.0F, 3000000.0F))))
/* 281 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.FLASH_DIAL.asItem())
/* 282 */       .weight(40)
/* 283 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 15.0F)))
/* 284 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1000000.0F, 1500000.0F))))
/* 285 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.BREATH_DIAL.asItem())
/* 286 */       .weight(50)
/* 287 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 10.0F)))
/* 288 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000000.0F, 2500000.0F))))
/* 289 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.IMPACT_DIAL.asItem())
/* 290 */       .weight(30)
/* 291 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 10.0F)))
/* 292 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(3000000.0F, 4000000.0F))))
/* 293 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.AXE_DIAL.asItem())
/* 294 */       .weight(80)
/* 295 */       .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(5.0F, 10.0F)))
/* 296 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000000.0F, 2500000.0F))))
/* 297 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModBlocks.REJECT_DIAL.asItem())
/* 298 */       .weight(1)
/* 299 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(1.0E8F, 2.0E8F))));
/*     */ 
/*     */   
/* 302 */   private static final LootPool.Builder GENERIC_EQUIPMENT_BUILDER = LootPool.builder()
/* 303 */     .name("mineminenomi:generic_equipment")
/* 304 */     .rolls((IRandomRange)RandomValueRange.of(0.0F, 2.0F))
/* 305 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.BANDANA)
/* 306 */       .weight(100)
/* 307 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(100.0F, 500.0F))))
/* 308 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.STRAW_HAT)
/* 309 */       .weight(100)
/* 310 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(100.0F, 500.0F))))
/* 311 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.SNIPER_GOGGLES)
/* 312 */       .weight(80)
/* 313 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(4000.0F, 5000.0F))))
/* 314 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.MEDIC_BAG)
/* 315 */       .weight(80)
/* 316 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(2000.0F, 4000.0F))))
/* 317 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.COLA_BACKPACK)
/* 318 */       .weight(70)
/* 319 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(4000.0F, 6000.0F))))
/* 320 */     .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModArmors.TOMOE_DRUMS)
/* 321 */       .weight(5)
/* 322 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(25000.0F, 50000.0F))));
/*     */ 
/*     */   
/* 325 */   private static final LootPool.Builder MARINE_CLOTHES_BUILDER = LootPool.builder()
/* 326 */     .name("mineminenomi:marine_clothes")
/* 327 */     .rolls((IRandomRange)RandomValueRange.of(0.0F, 5.0F))
/* 328 */     .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.MARINE_HEAD))
/* 329 */     .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.MARINE_CHEST))
/* 330 */     .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.MARINE_FEET))
/* 331 */     .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.MARINE_LEGS))
/* 332 */     .addEntry(setupClothingPrice(ClothesTier.CAPE, ModArmors.MARINE_CAPTAIN_CAPE))
/* 333 */     .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.VICE_ADMIRAL_CHEST))
/* 334 */     .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.VICE_ADMIRAL_FEET))
/* 335 */     .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.VICE_ADMIRAL_LEGS))
/* 336 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.KIZARU_GLASSES))
/* 337 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SMOKER_CHEST))
/* 338 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SMOKER_FEET))
/* 339 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SMOKER_LEGS))
/* 340 */     .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.CP9_CHEST))
/* 341 */     .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.CP9_FEET))
/* 342 */     .addEntry(setupClothingPrice(ClothesTier.RARE, ModArmors.CP9_LEGS));
/*     */ 
/*     */   
/* 345 */   private static final LootPool.Builder PIRATE_CLOTHES_BUILDER = LootPool.builder()
/* 346 */     .name("mineminenomi:pirate_clothes")
/* 347 */     .rolls((IRandomRange)RandomValueRange.of(0.0F, 5.0F))
/* 348 */     .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.PIRATE_CHEST))
/* 349 */     .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.PIRATE_FEET))
/* 350 */     .addEntry(setupClothingPrice(ClothesTier.TRASH, ModArmors.PIRATE_LEGS))
/* 351 */     .addEntry(setupClothingPrice(ClothesTier.CAPE, ModArmors.PIRATE_CAPTAIN_CAPE))
/* 352 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.LUFFY_CHEST))
/* 353 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.LUFFY_FEET))
/* 354 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.LUFFY_LEGS))
/* 355 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.ZORO_CHEST))
/* 356 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.ZORO_FEET))
/* 357 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.ZORO_LEGS))
/* 358 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SANJI_CHEST))
/* 359 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SANJI_FEET))
/* 360 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SANJI_LEGS))
/* 361 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.FRANKY_CHEST))
/* 362 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.FRANKY_GLASSES))
/* 363 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SENOR_PINK_CHEST))
/* 364 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.SENOR_PINK_HEAD))
/* 365 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.USOPP_CHEST))
/* 366 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.USOPP_FEET))
/* 367 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.USOPP_LEGS))
/* 368 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.KUMA_CHEST))
/* 369 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.KUMA_FEET))
/* 370 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.KUMA_LEGS))
/* 371 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.ACES_HAT))
/* 372 */     .addEntry(setupClothingPrice(ClothesTier.NAMED, ModArmors.CHOPPERS_HAT));
/*     */ 
/*     */   
/*     */   public ModTraderLootTablesDataGen(DataGenerator dataGenerator) {
/* 376 */     super(dataGenerator);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void act(DirectoryCache cache) {
/* 382 */     addLootTable(ModEntities.MARINE_TRADER, new LootPool.Builder[] { INFINITE_AMMO_BUILDER, FINITE_AMMO_BUILDER, GUNS_BUILDER, MELEE_WEAPONS_BUILDER, MATERIALS_BUILDER, BELLY_DIALS_BUILDER, GENERIC_EQUIPMENT_BUILDER, MARINE_CLOTHES_BUILDER });
/* 383 */     addLootTable(ModEntities.PIRATE_TRADER, new LootPool.Builder[] { INFINITE_AMMO_BUILDER, FINITE_AMMO_BUILDER, GUNS_BUILDER, MELEE_WEAPONS_BUILDER, MATERIALS_BUILDER, BELLY_DIALS_BUILDER, GENERIC_EQUIPMENT_BUILDER, PIRATE_CLOTHES_BUILDER });
/* 384 */     addLootTable(ModEntities.SKYPIEAN_TRADER, new LootPool.Builder[] { EXTOL_DIALS_BUILDER });
/*     */     
/* 386 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/* 387 */     this.lootTables.forEach((entityType, builder) -> {
/*     */           ResourceLocation resourcelocation = ForgeRegistries.ENTITIES.getKey(entityType);
/*     */           
/*     */           ResourceLocation traderRes = new ResourceLocation(resourcelocation.getNamespace(), "entities/trader/" + resourcelocation.getPath());
/*     */           tables.put(traderRes, builder.build());
/*     */         });
/* 393 */     writeTables(cache, tables);
/*     */   }
/*     */ 
/*     */   
/*     */   private static LootEntry.Builder<?> setupClothingPrice(ClothesTier tier, Item item) {
/* 398 */     return (LootEntry.Builder<?>)ItemLootEntry.builder((IItemProvider)item)
/* 399 */       .weight(tier.getWeight())
/* 400 */       .acceptFunction((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.of(tier.getMinPrice(), tier.getMaxPrice())));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addLootTable(EntityType entityType, LootPool.Builder... pools) {
/* 405 */     LootTable.Builder builder = LootTable.builder();
/* 406 */     for (LootPool.Builder pool : pools)
/*     */     {
/* 408 */       builder.addLootPool(pool);
/*     */     }
/* 410 */     this.lootTables.put(entityType, builder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 416 */     return "Traders Loot Pools";
/*     */   }
/*     */   
/*     */   private enum ClothesTier
/*     */   {
/* 421 */     TRASH(100, 50.0F, 100.0F),
/* 422 */     NAMED(30, 2000.0F, 3000.0F),
/* 423 */     CAPE(50, 4000.0F, 5000.0F),
/* 424 */     RARE(20, 5000.0F, 7000.0F);
/*     */     
/*     */     private int weight;
/*     */     private float minPrice;
/*     */     private float maxPrice;
/*     */     
/*     */     ClothesTier(int weight, float minPrice, float maxPrice) {
/* 431 */       this.weight = weight;
/* 432 */       this.minPrice = minPrice;
/* 433 */       this.maxPrice = maxPrice;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getWeight() {
/* 438 */       return this.weight;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getMinPrice() {
/* 443 */       return this.minPrice;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getMaxPrice() {
/* 448 */       return this.maxPrice;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\datagen\ModTraderLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */