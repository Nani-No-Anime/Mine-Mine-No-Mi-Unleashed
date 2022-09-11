package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;





public class MarineLargeBasePieces
{
  private static final ResourceLocation STRUCTURE = new ResourceLocation("mineminenomi", "marine/large_base");
  private static final ResourceLocation PRISON = new ResourceLocation("mineminenomi", "marine/large_base_prison");
  
  private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
    .put(STRUCTURE, new BlockPos(0, 0, 0))
    .put(PRISON, new BlockPos(15, -14, 16))
    .build();
  
  private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
    .put(STRUCTURE, new BlockPos(0, 0, 0))
    .put(PRISON, new BlockPos(0, 0, 0))
    .build();

  
  public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
    components.add(new Piece(templateManager, STRUCTURE, pos, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
    components.add(new Piece(templateManager, PRISON, pos, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
  }
  
  public static class Piece
    extends TemplateStructurePiece
  {
    private ResourceLocation resourceLocation;
    private Rotation rotation;
    private StructureProcessor processor;
    private BlockPos centerPosition;
    
    public Piece(TemplateManager template, CompoundNBT nbt) {
      super(ModFeatures.Pieces.MARINE_LARGE_BASE_PIECE, nbt);
      this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
      this.rotation = Rotation.valueOf(nbt.getString("Rot"));
      int centerX = nbt.getShort("CenterX");
      int centerY = nbt.getShort("CenterY");
      int centerZ = nbt.getShort("CenterZ");
      this.centerPosition = new BlockPos(centerX, centerY, centerZ);
      this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
      build(template);
    }

    
    public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, StructureProcessor proc) {
      super(ModFeatures.Pieces.MARINE_LARGE_BASE_PIECE, 0);
      this.rotation = Rotation.NONE;
      this.resourceLocation = res;
      BlockPos blockpos = (BlockPos)MarineLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
      this.centerPosition = pos;
      this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
      this.processor = proc;
      build(template);
    }


    
    protected void readAdditional(CompoundNBT nbt) {
      super.readAdditional(nbt);
      nbt.putString("Template", this.resourceLocation.toString());
      nbt.putString("Rot", this.rotation.name());
      nbt.putInt("CenterX", this.centerPosition.getX());
      nbt.putInt("CenterY", this.centerPosition.getY());
      nbt.putInt("CenterZ", this.centerPosition.getZ());
    }

    
    private void build(TemplateManager templateManager) {
      Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      setup(template, this.templatePosition, placementsettings);
    }



    
    protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
      if (function.equals("food_storage_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_FOOD_CHEST);
      }
      if (function.equals("enchantment_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_LAB_CHEST);
      }
      if (function.equals("supplies_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_GENERIC_CHEST);
      }
      if (function.equals("captain_room_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
      }
      boolean chest = rand.nextBoolean();
      
      if (chest) {
        
        if (function.equals("office_chest_1")) {
          StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
        
        }
      }
      else if (function.equals("office_chest_2")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
      } 

      
      StructuresHelper.StructureFaction prisoners1 = world.getRandom().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
      StructuresHelper.StructureFaction prisoners2 = world.getRandom().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
      StructuresHelper.StructureFaction prisoners3 = world.getRandom().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
      StructuresHelper.StructureFaction prisoners4 = world.getRandom().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
      
      boolean extraSpawns = rand.nextBoolean();
      
      if (function.equals("prison_spawn_1")) {
        StructuresHelper.spawnMobs(world, pos, prisoners1, StructuresHelper.StructureSpawnType.GRUNT, 3);
      }
      if (extraSpawns && function.equals("prison_spawn_2")) {
        StructuresHelper.spawnMobs(world, pos, prisoners2, StructuresHelper.StructureSpawnType.GRUNT, 3);
      }
      if (function.equals("prison_spawn_3")) {
        StructuresHelper.spawnMobs(world, pos, prisoners3, StructuresHelper.StructureSpawnType.GRUNT, 2);
      }
      if (extraSpawns && function.equals("prison_spawn_4")) {
        StructuresHelper.spawnMobs(world, pos, prisoners4, StructuresHelper.StructureSpawnType.GRUNT, 2);
      }
      if (function.equals("front_desk_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
      }
      if (function.equals("prison_guard_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 3);
      }
      if (function.equals("dinner_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
      }
      if (function.equals("bedrom_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 3);
      }
      if (function.equals("lounge_spawn_1")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
      }
      if (function.equals("lounge_spawn_2")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.BOMBER, 5);
      }
      if (function.equals("captain_room_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
      }
      if (function.equals("office_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
      }
      if (function.equals("rooftop_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.SNIPER, 5);
      }
      if (function.equals("balcony_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.SNIPER, 1);
      }
      
      if (function.equals("special_prison")) {
        StructuresHelper.spawnMobs(world, pos, prisoners4, StructuresHelper.StructureSpawnType.GRUNT, 4);
      }
    }

    
    public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
      if (this.centerPosition == null) {
        
        WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
        return false;
      } 
      
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      BlockPos offset = (BlockPos)MarineLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
      this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
      
      return super.create(world, generator, random, bb, chunkPos);
    }
  }
}


