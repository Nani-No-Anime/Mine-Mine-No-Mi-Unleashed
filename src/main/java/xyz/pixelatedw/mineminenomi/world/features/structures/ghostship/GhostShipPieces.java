package xyz.pixelatedw.mineminenomi.world.features.structures.ghostship;

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




public class GhostShipPieces
{
  private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "unaligned/ghost_ship");
  
  private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
    .put(PIECE, new BlockPos(0, 0, 0))
    .build();
  
  private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
    .put(PIECE, new BlockPos(0, 0, 0))
    .build();

  
  public static void addComponents(TemplateManager templateManager, BlockPos pos, Rotation rot, List<StructurePiece> components) {
    components.add(new Piece(templateManager, PIECE, pos, rot));
  }
  
  public static class Piece
    extends TemplateStructurePiece
  {
    private ResourceLocation resourceLocation;
    private Rotation rotation;
    
    public Piece(TemplateManager template, CompoundNBT nbt) {
      super(ModFeatures.Pieces.GHOST_SHIP_PIECE, nbt);
      this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
      this.rotation = Rotation.valueOf(nbt.getString("Rot"));
      build(template);
    }

    
    public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot) {
      super(ModFeatures.Pieces.GHOST_SHIP_PIECE, 0);
      this.rotation = rot;
      this.resourceLocation = res;
      BlockPos blockpos = (BlockPos)GhostShipPieces.POSITION_OFFSET.get(this.resourceLocation);
      this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
      build(template);
    }


    
    protected void readAdditional(CompoundNBT nbt) {
      super.readAdditional(nbt);
      nbt.putString("Template", this.resourceLocation.toString());
      nbt.putString("Rot", this.rotation.name());
    }

    
    private void build(TemplateManager templateManager) {
      Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)GhostShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
      setup(template, this.templatePosition, placementsettings);
    }



    
    protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
      if (function.equals("captain_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.GHOST_SHIP_CAPTAIN_CHEST);
      }
      if (function.equals("supplies_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.GHOST_SHIP_SUPPLIES_CHEST);
      }
      if (function.equals("mast_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.GHOST_SHIP_CAPTAIN_CHEST);
      }
      
      if (function.equals("deck_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
      }
      if (function.equals("captain_room_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.BRUTE, 2);
      }
      if (function.equals("cannons_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
      }
    }

    
    public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)GhostShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
      BlockPos offset = (BlockPos)GhostShipPieces.POSITION_OFFSET.get(this.resourceLocation);
      this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
      
      boolean flag = super.create(world, generator, random, bb, chunkPos);
      
      return flag;
    }
  }
}


