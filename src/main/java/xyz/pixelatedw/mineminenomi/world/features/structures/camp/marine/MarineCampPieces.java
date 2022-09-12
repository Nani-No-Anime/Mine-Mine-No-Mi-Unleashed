package xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine;

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
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;





public class MarineCampPieces
{
  private static final ResourceLocation SMALL_TENT = new ResourceLocation("mineminenomi", "marine/small_tent");
  private static final ResourceLocation LARGE_TENT = new ResourceLocation("mineminenomi", "marine/large_tent");
  private static final ResourceLocation FIRE_PLACE = new ResourceLocation("mineminenomi", "props/fire_place");
  
  private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = (Map<ResourceLocation, BlockPos>)ImmutableMap.of(SMALL_TENT, new BlockPos(3, 0, 3), LARGE_TENT, new BlockPos(0, 0, 0), FIRE_PLACE, new BlockPos(0, 0, 0));





  
  public static void addComponents(TemplateManager templateManager, BlockPos pos, Random rand, List<StructurePiece> components) {
    List<BlockPos> list = new ArrayList<>();
    boolean hasExtraTents = false;
    
    if (WyHelper.randomDouble() < 0.1D) {
      hasExtraTents = true;
    }
    components.add(new Piece(templateManager, FIRE_PLACE, pos, Rotation.CLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
    
    components.add(new Piece(templateManager, LARGE_TENT, pos.add(13, 0, 0), Rotation.CLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
    if (hasExtraTents) {
      
      components.add(new Piece(templateManager, LARGE_TENT, pos.add(-19, 0, 6), Rotation.COUNTERCLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
      list.add(pos.add(-19, 0, 6));
    } 
    
    list.add(pos.add(13, 0, 0));
    
    for (int i = 0; i < (hasExtraTents ? 6 : 4); i++) {
      
      BlockPos piecePos = trySpawnTent(pos, 0, list);
      if (piecePos != null) {
        
        components.add(new Piece(templateManager, SMALL_TENT, piecePos, Rotation.randomRotation(rand), (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
        list.add(piecePos);
      } 
    } 
  }
  
  private static BlockPos trySpawnTent(BlockPos pos, int attempts, List<BlockPos> list) {
    if (attempts > 20)
      return null; 
    for (BlockPos tentPos : list) {
      
      if (Math.sqrt(tentPos.distanceSq((Vec3i)pos)) < 9.0D)
        return trySpawnTent(pos, ++attempts, list); 
    } 
    BlockPos random = new BlockPos(WyHelper.randomWithRange(-40, 40), 0.0D, WyHelper.randomWithRange(-40, 40));
    BlockPos piecePos = pos.add(random.getX(), 0, random.getZ());
    if (Math.sqrt(pos.distanceSq((Vec3i)piecePos)) < 15.0D) {
      return trySpawnTent(pos, ++attempts, list);
    }
    return piecePos;
  }
  
  public static class Piece
    extends TemplateStructurePiece
  {
    private ResourceLocation resourceLocation;
    private Rotation rotation;
    private StructureProcessor processor;
    
    public Piece(TemplateManager template, CompoundNBT nbt) {
      super(ModFeatures.Pieces.MARINE_CAMP_PIECE, nbt);
      this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
      this.rotation = Rotation.valueOf(nbt.getString("Rot"));
      this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
      build(template);
    }

    
    public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot, StructureProcessor proc) {
      super(ModFeatures.Pieces.MARINE_CAMP_PIECE, 0);
      this.rotation = rot;
      this.resourceLocation = res;
      this.templatePosition = pos;
      this.processor = proc;
      build(template);
    }


    
    protected void readAdditional(CompoundNBT nbt) {
      super.readAdditional(nbt);
      nbt.putString("Template", this.resourceLocation.toString());
      nbt.putString("Rot", this.rotation.name());
    }

    
    private void build(TemplateManager templateManager) {
      Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      setup(template, this.templatePosition, placementsettings);
    }



    
    protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
      if (function.equals("small_tent_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.CAMP_SMALL_TENT_CHEST);
      }
      if (function.equals("large_tent_chest")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.CAMP_LARGE_TENT_CHEST);
      }
      
      if (function.equals("small_tent_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 2);
      }
      if (function.equals("large_tent_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 2);
      }
    }

    
    public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      BlockPos offset = new BlockPos(0, 0, 0);
      this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
      
      BlockPos blockpos2 = this.templatePosition;
      int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.templatePosition.getX(), this.templatePosition.getZ());
      this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
      
      boolean flag = super.create(world, generator, random, bb, chunkPos);



      
      this.templatePosition = blockpos2;
      return flag;
    }
  }
}


