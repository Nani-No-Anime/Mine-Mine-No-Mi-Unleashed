package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.world.features.PoneglyphFeature;
import xyz.pixelatedw.mineminenomi.world.features.ores.KairosekiOreFeature;
import xyz.pixelatedw.mineminenomi.world.features.structures.battleship.marine.MarineBattleshipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit.BanditCampStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine.MarineCampStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.ghostship.GhostShipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit.BanditLargeBaseStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBaseStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate.PirateLargeShipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.mediumship.pirate.PirateMediumShipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp.SkyIslandCampStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house.SkyIslandHouseStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.town.SkyIslandTownStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBaseStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine.MarineSmallBaseStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate.PirateSmallShipStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.training.blacklegkitchen.BlacklegKitchenStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.training.brawlerring.BrawlerRingStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.training.medictent.MedicTentStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.training.sniperrange.SniperRangeStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.training.swordsmandojo.SwordsmanDojoStructure;
import xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine.MarineWatchTowerStructure;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;























@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
  public static final Structure<NoFeatureConfig> MARINE_SMALL_BASE = (Structure<NoFeatureConfig>)new MarineSmallBaseStructure();
  public static final Structure<NoFeatureConfig> BANDIT_SMALL_BASE = (Structure<NoFeatureConfig>)new BanditSmallBaseStructure();
  
  public static final Structure<NoFeatureConfig> MARINE_LARGE_BASE = (Structure<NoFeatureConfig>)new MarineLargeBaseStructure();
  public static final Structure<NoFeatureConfig> BANDIT_LARGE_BASE = (Structure<NoFeatureConfig>)new BanditLargeBaseStructure();
  
  public static final Structure<NoFeatureConfig> MARINE_CAMP = (Structure<NoFeatureConfig>)new MarineCampStructure();
  public static final Structure<NoFeatureConfig> BANDIT_CAMP = (Structure<NoFeatureConfig>)new BanditCampStructure();
  
  public static final Structure<NoFeatureConfig> MARINE_WATCH_TOWER = (Structure<NoFeatureConfig>)new MarineWatchTowerStructure();
  
  public static final Structure<NoFeatureConfig> PIRATE_SMALL_SHIP = (Structure<NoFeatureConfig>)new PirateSmallShipStructure();
  
  public static final Structure<NoFeatureConfig> PIRATE_MEDIUM_SHIP = (Structure<NoFeatureConfig>)new PirateMediumShipStructure();
  
  public static final Structure<NoFeatureConfig> PIRATE_LARGE_SHIP = (Structure<NoFeatureConfig>)new PirateLargeShipStructure();
  
  public static final Structure<NoFeatureConfig> MARINE_BATTLESHIP = (Structure<NoFeatureConfig>)new MarineBattleshipStructure();
  
  public static final Structure<NoFeatureConfig> SWORDSMAN_DOJO = (Structure<NoFeatureConfig>)new SwordsmanDojoStructure();
  public static final Structure<NoFeatureConfig> BRAWLER_RING = (Structure<NoFeatureConfig>)new BrawlerRingStructure();
  public static final Structure<NoFeatureConfig> BLACKLEG_KITCHEN = (Structure<NoFeatureConfig>)new BlacklegKitchenStructure();
  public static final Structure<NoFeatureConfig> MEDIC_TENT = (Structure<NoFeatureConfig>)new MedicTentStructure();
  public static final Structure<NoFeatureConfig> SNIPER_RANGE = (Structure<NoFeatureConfig>)new SniperRangeStructure();
  
  public static final Structure<NoFeatureConfig> GHOST_SHIP = (Structure<NoFeatureConfig>)new GhostShipStructure();
  
  public static final Structure<NoFeatureConfig> SKY_ISLAND_CAMP = (Structure<NoFeatureConfig>)new SkyIslandCampStructure();
  public static final Structure<NoFeatureConfig> SKY_ISLAND_HOUSE = (Structure<NoFeatureConfig>)new SkyIslandHouseStructure();
  public static final Structure<NoFeatureConfig> SKY_ISLAND_TOWN = (Structure<NoFeatureConfig>)new SkyIslandTownStructure();
  
  public static final Feature<NoFeatureConfig> PONEGLYPH = (Feature<NoFeatureConfig>)new PoneglyphFeature();

  
  public static void init() {
    for (Biome biome : ForgeRegistries.BIOMES) {
      
      MarineSmallBaseStructure.register(biome);
      BanditSmallBaseStructure.register(biome);
      
      MarineLargeBaseStructure.register(biome);
      BanditLargeBaseStructure.register(biome);
      
      MarineCampStructure.register(biome);
      BanditCampStructure.register(biome);
      
      MarineWatchTowerStructure.register(biome);
      
      PirateSmallShipStructure.register(biome);
      
      PirateMediumShipStructure.register(biome);
      
      PirateLargeShipStructure.register(biome);
      
      MarineBattleshipStructure.register(biome);
      
      SwordsmanDojoStructure.register(biome);
      BrawlerRingStructure.register(biome);
      BlacklegKitchenStructure.register(biome);
      MedicTentStructure.register(biome);
      SniperRangeStructure.register(biome);
      
      GhostShipStructure.register(biome);
      
      SkyIslandCampStructure.register(biome);
      SkyIslandHouseStructure.register(biome);
      SkyIslandTownStructure.register(biome);
      
      KairosekiOreFeature.register(biome);
      PoneglyphFeature.register(biome);
    } 
    
    Pieces.registerAllPieces();
  }

  
  static {
    WyRegistry.registerFeature((Feature)MARINE_SMALL_BASE, "Marine Small Base");
    WyRegistry.registerFeature((Feature)BANDIT_SMALL_BASE, "Bandit Small Base");
    
    WyRegistry.registerFeature((Feature)MARINE_LARGE_BASE, "Marine Large Base");
    WyRegistry.registerFeature((Feature)BANDIT_LARGE_BASE, "Bandit Large Base");
    
    WyRegistry.registerFeature((Feature)MARINE_CAMP, "Marine Camp");
    WyRegistry.registerFeature((Feature)BANDIT_CAMP, "Bandit Camp");
    
    WyRegistry.registerFeature((Feature)MARINE_WATCH_TOWER, "Marine Watch Tower");
    
    WyRegistry.registerFeature((Feature)PIRATE_SMALL_SHIP, "Pirate Small Ship");
    
    WyRegistry.registerFeature((Feature)PIRATE_MEDIUM_SHIP, "Pirate Medium Ship");
    
    WyRegistry.registerFeature((Feature)PIRATE_LARGE_SHIP, "Pirate Large Ship");
    
    WyRegistry.registerFeature((Feature)MARINE_BATTLESHIP, "Marine Battleship");
    
    WyRegistry.registerFeature((Feature)SWORDSMAN_DOJO, "Swordsman Dojo");
    WyRegistry.registerFeature((Feature)BRAWLER_RING, "Brawler Ring");
    WyRegistry.registerFeature((Feature)BLACKLEG_KITCHEN, "Blackleg Kitchen");
    WyRegistry.registerFeature((Feature)MEDIC_TENT, "Medic Tent");
    WyRegistry.registerFeature((Feature)SNIPER_RANGE, "Sniper Range");
    
    WyRegistry.registerFeature((Feature)GHOST_SHIP, "Ghost Ship");
    
    WyRegistry.registerFeature((Feature)SKY_ISLAND_CAMP, "Sky Island Camp");
    WyRegistry.registerFeature((Feature)SKY_ISLAND_HOUSE, "Sky Island House");
    WyRegistry.registerFeature((Feature)SKY_ISLAND_TOWN, "Sky Island Town");
  }

  
  public static class Pieces
  {
    public static final IStructurePieceType PIRATE_SMALL_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate.PirateSmallShipPieces.Piece::new;
    public static final IStructurePieceType PIRATE_MEDIUM_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.mediumship.pirate.PirateMediumShipPieces.Piece::new;
    public static final IStructurePieceType PIRATE_LARGE_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate.PirateLargeShipPieces.Piece::new;
    public static final IStructurePieceType MARINE_BATTLESHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.battleship.marine.MarineBattleshipPieces.Piece::new;
    public static final IStructurePieceType GHOST_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.ghostship.GhostShipPieces.Piece::new;

    
    public static final IStructurePieceType MARINE_SMALL_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine.MarineSmallBasePieces.Piece::new;
    public static final IStructurePieceType BANDIT_SMALL_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBasePieces.Piece::new;
    public static final IStructurePieceType MARINE_LARGE_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBasePieces.Piece::new;
    public static final IStructurePieceType BANDIT_LARGE_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit.BanditLargeBasePieces.Piece::new;
    public static final IStructurePieceType MARINE_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine.MarineCampPieces.Piece::new;
    public static final IStructurePieceType BANDIT_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit.BanditCampPieces.Piece::new;
    public static final IStructurePieceType MARINE_WATCH_TOWER_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine.MarineWatchTowerPieces.Piece::new;

    
    public static final IStructurePieceType SWORDSMAN_DOJO_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.swordsmandojo.SwordsmanDojoPiece::new;
    public static final IStructurePieceType BRAWLER_RING_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.brawlerring.BrawlerRingPiece::new;
    public static final IStructurePieceType BLACKLEG_KITCHEN_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.blacklegkitchen.BlacklegKitchenPiece::new;
    public static final IStructurePieceType MEDIC_TENT_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.medictent.MedicTentPiece::new;
    public static final IStructurePieceType SNIPER_RANGE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.sniperrange.SniperRangePiece::new;

    
    public static final IStructurePieceType SKY_ISLAND_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp.SkyIslandCampPieces.Piece::new;
    public static final IStructurePieceType SKY_ISLAND_HOUSE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house.SkyIslandHousePieces.Piece::new;
    public static final IStructurePieceType SKY_ISLAND_TOWN_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.town.SkyIslandTownPieces.Piece::new;

    
    public static void registerAllPieces() {
      registerStructurePiece(PIRATE_SMALL_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_small_ship_piece"));
      registerStructurePiece(PIRATE_MEDIUM_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_medium_ship_piece"));
      registerStructurePiece(PIRATE_LARGE_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_large_ship_piece"));
      registerStructurePiece(MARINE_BATTLESHIP_PIECE, new ResourceLocation("mineminenomi", "marine_battleship_piece"));
      registerStructurePiece(GHOST_SHIP_PIECE, new ResourceLocation("mineminenomi", "ghost_ship_piece"));
      
      registerStructurePiece(MARINE_LARGE_BASE_PIECE, new ResourceLocation("mineminenomi", "marine_large_base_piece"));
      registerStructurePiece(MARINE_SMALL_BASE_PIECE, new ResourceLocation("mineminenomi", "marine_small_base_piece"));
      registerStructurePiece(BANDIT_SMALL_BASE_PIECE, new ResourceLocation("mineminenomi", "bandit_small_base_piece"));
      registerStructurePiece(BANDIT_LARGE_BASE_PIECE, new ResourceLocation("mineminenomi", "bandit_small_base_piece"));
      registerStructurePiece(MARINE_CAMP_PIECE, new ResourceLocation("mineminenomi", "marine_camp_piece"));
      registerStructurePiece(BANDIT_CAMP_PIECE, new ResourceLocation("mineminenomi", "bandit_camp_piece"));
      registerStructurePiece(MARINE_WATCH_TOWER_PIECE, new ResourceLocation("mineminenomi", "marine_watch_tower_piece"));
      
      registerStructurePiece(SWORDSMAN_DOJO_PIECE, new ResourceLocation("mineminenomi", "swordsman_dojo_piece"));
      registerStructurePiece(BRAWLER_RING_PIECE, new ResourceLocation("mineminenomi", "brawler_ring_piece"));
      registerStructurePiece(BLACKLEG_KITCHEN_PIECE, new ResourceLocation("mineminenomi", "black_leg_kitchen_piece"));
      registerStructurePiece(MEDIC_TENT_PIECE, new ResourceLocation("mineminenomi", "medic_tent_piece"));
      registerStructurePiece(SNIPER_RANGE_PIECE, new ResourceLocation("mineminenomi", "sniper_range_piece"));
      
      registerStructurePiece(SKY_ISLAND_CAMP_PIECE, new ResourceLocation("mineminenomi", "sky_island_camp_piece"));
      registerStructurePiece(SKY_ISLAND_HOUSE_PIECE, new ResourceLocation("mineminenomi", "sky_island_house_piece"));
      registerStructurePiece(SKY_ISLAND_TOWN_PIECE, new ResourceLocation("mineminenomi", "sky_island_town_piece"));
    }

    
    static void registerStructurePiece(IStructurePieceType structurePiece, ResourceLocation res) {
      Registry.register(Registry.STRUCTURE_PIECE, res, structurePiece);
    }
  }
}


