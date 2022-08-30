/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.gen.feature.Feature;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.structure.IStructurePieceType;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.PoneglyphFeature;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.ores.KairosekiOreFeature;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.battleship.marine.MarineBattleshipStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit.BanditCampStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine.MarineCampStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.ghostship.GhostShipStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit.BanditLargeBaseStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBaseStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate.PirateLargeShipStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.mediumship.pirate.PirateMediumShipStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp.SkyIslandCampStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house.SkyIslandHouseStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.town.SkyIslandTownStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBaseStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine.MarineSmallBaseStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate.PirateSmallShipStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.training.blacklegkitchen.BlacklegKitchenStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.training.brawlerring.BrawlerRingStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.training.medictent.MedicTentStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.training.sniperrange.SniperRangeStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.training.swordsmandojo.SwordsmanDojoStructure;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine.MarineWatchTowerStructure;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModFeatures
/*     */ {
/*  62 */   public static final Structure<NoFeatureConfig> MARINE_SMALL_BASE = (Structure<NoFeatureConfig>)new MarineSmallBaseStructure();
/*  63 */   public static final Structure<NoFeatureConfig> BANDIT_SMALL_BASE = (Structure<NoFeatureConfig>)new BanditSmallBaseStructure();
/*     */   
/*  65 */   public static final Structure<NoFeatureConfig> MARINE_LARGE_BASE = (Structure<NoFeatureConfig>)new MarineLargeBaseStructure();
/*  66 */   public static final Structure<NoFeatureConfig> BANDIT_LARGE_BASE = (Structure<NoFeatureConfig>)new BanditLargeBaseStructure();
/*     */   
/*  68 */   public static final Structure<NoFeatureConfig> MARINE_CAMP = (Structure<NoFeatureConfig>)new MarineCampStructure();
/*  69 */   public static final Structure<NoFeatureConfig> BANDIT_CAMP = (Structure<NoFeatureConfig>)new BanditCampStructure();
/*     */   
/*  71 */   public static final Structure<NoFeatureConfig> MARINE_WATCH_TOWER = (Structure<NoFeatureConfig>)new MarineWatchTowerStructure();
/*     */   
/*  73 */   public static final Structure<NoFeatureConfig> PIRATE_SMALL_SHIP = (Structure<NoFeatureConfig>)new PirateSmallShipStructure();
/*     */   
/*  75 */   public static final Structure<NoFeatureConfig> PIRATE_MEDIUM_SHIP = (Structure<NoFeatureConfig>)new PirateMediumShipStructure();
/*     */   
/*  77 */   public static final Structure<NoFeatureConfig> PIRATE_LARGE_SHIP = (Structure<NoFeatureConfig>)new PirateLargeShipStructure();
/*     */   
/*  79 */   public static final Structure<NoFeatureConfig> MARINE_BATTLESHIP = (Structure<NoFeatureConfig>)new MarineBattleshipStructure();
/*     */   
/*  81 */   public static final Structure<NoFeatureConfig> SWORDSMAN_DOJO = (Structure<NoFeatureConfig>)new SwordsmanDojoStructure();
/*  82 */   public static final Structure<NoFeatureConfig> BRAWLER_RING = (Structure<NoFeatureConfig>)new BrawlerRingStructure();
/*  83 */   public static final Structure<NoFeatureConfig> BLACKLEG_KITCHEN = (Structure<NoFeatureConfig>)new BlacklegKitchenStructure();
/*  84 */   public static final Structure<NoFeatureConfig> MEDIC_TENT = (Structure<NoFeatureConfig>)new MedicTentStructure();
/*  85 */   public static final Structure<NoFeatureConfig> SNIPER_RANGE = (Structure<NoFeatureConfig>)new SniperRangeStructure();
/*     */   
/*  87 */   public static final Structure<NoFeatureConfig> GHOST_SHIP = (Structure<NoFeatureConfig>)new GhostShipStructure();
/*     */   
/*  89 */   public static final Structure<NoFeatureConfig> SKY_ISLAND_CAMP = (Structure<NoFeatureConfig>)new SkyIslandCampStructure();
/*  90 */   public static final Structure<NoFeatureConfig> SKY_ISLAND_HOUSE = (Structure<NoFeatureConfig>)new SkyIslandHouseStructure();
/*  91 */   public static final Structure<NoFeatureConfig> SKY_ISLAND_TOWN = (Structure<NoFeatureConfig>)new SkyIslandTownStructure();
/*     */   
/*  93 */   public static final Feature<NoFeatureConfig> PONEGLYPH = (Feature<NoFeatureConfig>)new PoneglyphFeature();
/*     */ 
/*     */   
/*     */   public static void init() {
/*  97 */     for (Biome biome : ForgeRegistries.BIOMES) {
/*     */       
/*  99 */       MarineSmallBaseStructure.register(biome);
/* 100 */       BanditSmallBaseStructure.register(biome);
/*     */       
/* 102 */       MarineLargeBaseStructure.register(biome);
/* 103 */       BanditLargeBaseStructure.register(biome);
/*     */       
/* 105 */       MarineCampStructure.register(biome);
/* 106 */       BanditCampStructure.register(biome);
/*     */       
/* 108 */       MarineWatchTowerStructure.register(biome);
/*     */       
/* 110 */       PirateSmallShipStructure.register(biome);
/*     */       
/* 112 */       PirateMediumShipStructure.register(biome);
/*     */       
/* 114 */       PirateLargeShipStructure.register(biome);
/*     */       
/* 116 */       MarineBattleshipStructure.register(biome);
/*     */       
/* 118 */       SwordsmanDojoStructure.register(biome);
/* 119 */       BrawlerRingStructure.register(biome);
/* 120 */       BlacklegKitchenStructure.register(biome);
/* 121 */       MedicTentStructure.register(biome);
/* 122 */       SniperRangeStructure.register(biome);
/*     */       
/* 124 */       GhostShipStructure.register(biome);
/*     */       
/* 126 */       SkyIslandCampStructure.register(biome);
/* 127 */       SkyIslandHouseStructure.register(biome);
/* 128 */       SkyIslandTownStructure.register(biome);
/*     */       
/* 130 */       KairosekiOreFeature.register(biome);
/* 131 */       PoneglyphFeature.register(biome);
/*     */     } 
/*     */     
/* 134 */     Pieces.registerAllPieces();
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 139 */     WyRegistry.registerFeature((Feature)MARINE_SMALL_BASE, "Marine Small Base");
/* 140 */     WyRegistry.registerFeature((Feature)BANDIT_SMALL_BASE, "Bandit Small Base");
/*     */     
/* 142 */     WyRegistry.registerFeature((Feature)MARINE_LARGE_BASE, "Marine Large Base");
/* 143 */     WyRegistry.registerFeature((Feature)BANDIT_LARGE_BASE, "Bandit Large Base");
/*     */     
/* 145 */     WyRegistry.registerFeature((Feature)MARINE_CAMP, "Marine Camp");
/* 146 */     WyRegistry.registerFeature((Feature)BANDIT_CAMP, "Bandit Camp");
/*     */     
/* 148 */     WyRegistry.registerFeature((Feature)MARINE_WATCH_TOWER, "Marine Watch Tower");
/*     */     
/* 150 */     WyRegistry.registerFeature((Feature)PIRATE_SMALL_SHIP, "Pirate Small Ship");
/*     */     
/* 152 */     WyRegistry.registerFeature((Feature)PIRATE_MEDIUM_SHIP, "Pirate Medium Ship");
/*     */     
/* 154 */     WyRegistry.registerFeature((Feature)PIRATE_LARGE_SHIP, "Pirate Large Ship");
/*     */     
/* 156 */     WyRegistry.registerFeature((Feature)MARINE_BATTLESHIP, "Marine Battleship");
/*     */     
/* 158 */     WyRegistry.registerFeature((Feature)SWORDSMAN_DOJO, "Swordsman Dojo");
/* 159 */     WyRegistry.registerFeature((Feature)BRAWLER_RING, "Brawler Ring");
/* 160 */     WyRegistry.registerFeature((Feature)BLACKLEG_KITCHEN, "Blackleg Kitchen");
/* 161 */     WyRegistry.registerFeature((Feature)MEDIC_TENT, "Medic Tent");
/* 162 */     WyRegistry.registerFeature((Feature)SNIPER_RANGE, "Sniper Range");
/*     */     
/* 164 */     WyRegistry.registerFeature((Feature)GHOST_SHIP, "Ghost Ship");
/*     */     
/* 166 */     WyRegistry.registerFeature((Feature)SKY_ISLAND_CAMP, "Sky Island Camp");
/* 167 */     WyRegistry.registerFeature((Feature)SKY_ISLAND_HOUSE, "Sky Island House");
/* 168 */     WyRegistry.registerFeature((Feature)SKY_ISLAND_TOWN, "Sky Island Town");
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Pieces
/*     */   {
/* 174 */     public static final IStructurePieceType PIRATE_SMALL_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate.PirateSmallShipPieces.Piece::new;
/* 175 */     public static final IStructurePieceType PIRATE_MEDIUM_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.mediumship.pirate.PirateMediumShipPieces.Piece::new;
/* 176 */     public static final IStructurePieceType PIRATE_LARGE_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate.PirateLargeShipPieces.Piece::new;
/* 177 */     public static final IStructurePieceType MARINE_BATTLESHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.battleship.marine.MarineBattleshipPieces.Piece::new;
/* 178 */     public static final IStructurePieceType GHOST_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.ghostship.GhostShipPieces.Piece::new;
/*     */ 
/*     */     
/* 181 */     public static final IStructurePieceType MARINE_SMALL_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine.MarineSmallBasePieces.Piece::new;
/* 182 */     public static final IStructurePieceType BANDIT_SMALL_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBasePieces.Piece::new;
/* 183 */     public static final IStructurePieceType MARINE_LARGE_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBasePieces.Piece::new;
/* 184 */     public static final IStructurePieceType BANDIT_LARGE_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit.BanditLargeBasePieces.Piece::new;
/* 185 */     public static final IStructurePieceType MARINE_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine.MarineCampPieces.Piece::new;
/* 186 */     public static final IStructurePieceType BANDIT_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit.BanditCampPieces.Piece::new;
/* 187 */     public static final IStructurePieceType MARINE_WATCH_TOWER_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine.MarineWatchTowerPieces.Piece::new;
/*     */ 
/*     */     
/* 190 */     public static final IStructurePieceType SWORDSMAN_DOJO_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.swordsmandojo.SwordsmanDojoPiece::new;
/* 191 */     public static final IStructurePieceType BRAWLER_RING_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.brawlerring.BrawlerRingPiece::new;
/* 192 */     public static final IStructurePieceType BLACKLEG_KITCHEN_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.blacklegkitchen.BlacklegKitchenPiece::new;
/* 193 */     public static final IStructurePieceType MEDIC_TENT_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.medictent.MedicTentPiece::new;
/* 194 */     public static final IStructurePieceType SNIPER_RANGE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.sniperrange.SniperRangePiece::new;
/*     */ 
/*     */     
/* 197 */     public static final IStructurePieceType SKY_ISLAND_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp.SkyIslandCampPieces.Piece::new;
/* 198 */     public static final IStructurePieceType SKY_ISLAND_HOUSE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house.SkyIslandHousePieces.Piece::new;
/* 199 */     public static final IStructurePieceType SKY_ISLAND_TOWN_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.town.SkyIslandTownPieces.Piece::new;
/*     */ 
/*     */     
/*     */     public static void registerAllPieces() {
/* 203 */       registerStructurePiece(PIRATE_SMALL_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_small_ship_piece"));
/* 204 */       registerStructurePiece(PIRATE_MEDIUM_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_medium_ship_piece"));
/* 205 */       registerStructurePiece(PIRATE_LARGE_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_large_ship_piece"));
/* 206 */       registerStructurePiece(MARINE_BATTLESHIP_PIECE, new ResourceLocation("mineminenomi", "marine_battleship_piece"));
/* 207 */       registerStructurePiece(GHOST_SHIP_PIECE, new ResourceLocation("mineminenomi", "ghost_ship_piece"));
/*     */       
/* 209 */       registerStructurePiece(MARINE_LARGE_BASE_PIECE, new ResourceLocation("mineminenomi", "marine_large_base_piece"));
/* 210 */       registerStructurePiece(MARINE_SMALL_BASE_PIECE, new ResourceLocation("mineminenomi", "marine_small_base_piece"));
/* 211 */       registerStructurePiece(BANDIT_SMALL_BASE_PIECE, new ResourceLocation("mineminenomi", "bandit_small_base_piece"));
/* 212 */       registerStructurePiece(BANDIT_LARGE_BASE_PIECE, new ResourceLocation("mineminenomi", "bandit_small_base_piece"));
/* 213 */       registerStructurePiece(MARINE_CAMP_PIECE, new ResourceLocation("mineminenomi", "marine_camp_piece"));
/* 214 */       registerStructurePiece(BANDIT_CAMP_PIECE, new ResourceLocation("mineminenomi", "bandit_camp_piece"));
/* 215 */       registerStructurePiece(MARINE_WATCH_TOWER_PIECE, new ResourceLocation("mineminenomi", "marine_watch_tower_piece"));
/*     */       
/* 217 */       registerStructurePiece(SWORDSMAN_DOJO_PIECE, new ResourceLocation("mineminenomi", "swordsman_dojo_piece"));
/* 218 */       registerStructurePiece(BRAWLER_RING_PIECE, new ResourceLocation("mineminenomi", "brawler_ring_piece"));
/* 219 */       registerStructurePiece(BLACKLEG_KITCHEN_PIECE, new ResourceLocation("mineminenomi", "black_leg_kitchen_piece"));
/* 220 */       registerStructurePiece(MEDIC_TENT_PIECE, new ResourceLocation("mineminenomi", "medic_tent_piece"));
/* 221 */       registerStructurePiece(SNIPER_RANGE_PIECE, new ResourceLocation("mineminenomi", "sniper_range_piece"));
/*     */       
/* 223 */       registerStructurePiece(SKY_ISLAND_CAMP_PIECE, new ResourceLocation("mineminenomi", "sky_island_camp_piece"));
/* 224 */       registerStructurePiece(SKY_ISLAND_HOUSE_PIECE, new ResourceLocation("mineminenomi", "sky_island_house_piece"));
/* 225 */       registerStructurePiece(SKY_ISLAND_TOWN_PIECE, new ResourceLocation("mineminenomi", "sky_island_town_piece"));
/*     */     }
/*     */ 
/*     */     
/*     */     static void registerStructurePiece(IStructurePieceType structurePiece, ResourceLocation res) {
/* 230 */       Registry.register(Registry.STRUCTURE_PIECE, res, structurePiece);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModFeatures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */