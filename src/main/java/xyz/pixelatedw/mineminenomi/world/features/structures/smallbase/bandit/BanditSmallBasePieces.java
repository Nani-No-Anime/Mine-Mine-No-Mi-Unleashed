package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit;

import com.google.common.collect.ImmutableMap;
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
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

import java.util.List;
import java.util.Map;
import java.util.Random;





public class BanditSmallBasePieces
{
  private static final ResourceLocation HOUSE = new ResourceLocation("mineminenomi", "bandit/small_base_house");
  private static final ResourceLocation UNDERGROUND = new ResourceLocation("mineminenomi", "bandit/small_base_underground");
  
  private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
    .put(HOUSE, new BlockPos(18, 27, 23))
    .put(UNDERGROUND, new BlockPos(0, 0, 0))
    .build();
  
  private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
    .put(HOUSE, new BlockPos(0, 0, 0))
    .put(UNDERGROUND, new BlockPos(0, 0, 0))
    .build();

  
  public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
    components.add(new Piece(templateManager, HOUSE, pos));
    components.add(new Piece(templateManager, UNDERGROUND, pos));
  }
  
  public static class Piece
    extends TemplateStructurePiece
  {
    private ResourceLocation resourceLocation;
    private Rotation rotation;
    private BlockPos centerPosition;
    
    public Piece(TemplateManager template, CompoundNBT nbt) {
      super(ModFeatures.Pieces.BANDIT_SMALL_BASE_PIECE, nbt);
      this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
      this.rotation = Rotation.valueOf(nbt.getString("Rot"));
      int centerX = nbt.getShort("CenterX");
      int centerY = nbt.getShort("CenterY");
      int centerZ = nbt.getShort("CenterZ");
      this.centerPosition = new BlockPos(centerX, centerY, centerZ);
      build(template);
    }

    
    public Piece(TemplateManager template, ResourceLocation res, BlockPos pos) {
      super(ModFeatures.Pieces.BANDIT_SMALL_BASE_PIECE, 0);
      this.rotation = Rotation.NONE;
      this.resourceLocation = res;
      BlockPos blockpos = (BlockPos)BanditSmallBasePieces.POSITION_OFFSET.get(this.resourceLocation);
      this.centerPosition = pos;
      this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
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
      BlockIgnoreStructureProcessor blockIgnoreStructureProcessor = BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK;

      
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditSmallBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)blockIgnoreStructureProcessor);
      setup(template, this.templatePosition, placementsettings);
    }



    
    protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
      if (function.equals("equipment_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_BANDIT_BASE_MINE_CHEST);
      }
      if (function.equals("gem_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_BANDIT_BASE_ORES_CHEST);
      }
      if (function.equals("lab_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_BANDIT_BASE_LAB_CHEST);
      }
      if (function.equals("gold_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_BANDIT_BASE_GOLD_CHEST);
      }

      
      if (function.equals("lounge_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 5);
      }
      if (function.equals("hall_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
      }
      if (function.equals("bedroom_spawn_1")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 5);
      }
      if (function.equals("bedroom_spawn_2")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
      }
      if (function.equals("lab_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
      }
    }

    
    public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
      if (this.centerPosition == null) {
        
        WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
        return false;
      } 
      
      BlockIgnoreStructureProcessor blockIgnoreStructureProcessor = BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK;

      
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditSmallBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)blockIgnoreStructureProcessor);
      BlockPos offset = (BlockPos)BanditSmallBasePieces.POSITION_OFFSET.get(this.resourceLocation);
      this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
      boolean flag = false;

      
      try {
        BlockPos blockpos2 = this.templatePosition;
        int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.centerPosition.getX(), this.centerPosition.getZ());
        this.templatePosition = this.templatePosition.add(0, i - 118, 0);
        
        flag = super.create(world, generator, random, bb, chunkPos);
        
        this.templatePosition = blockpos2;
      }
      catch (Exception e) {
        
        e.printStackTrace();
      } 
      
      return flag;
    }
  }
}


