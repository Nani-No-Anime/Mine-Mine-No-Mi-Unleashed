package xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp;

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
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

import java.util.List;
import java.util.Map;
import java.util.Random;





public class SkyIslandCampPieces
{
  private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "sky_islands/camp");
  
  private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
    .put(PIECE, new BlockPos(0, 0, 0))
    .build();
  
  private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
    .put(PIECE, new BlockPos(0, 0, 0))
    .build();

  
  public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
    components.add(new Piece(templateManager, PIECE, pos, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
  }
  
  public static class Piece
    extends TemplateStructurePiece
  {
    private ResourceLocation resourceLocation;
    private Rotation rotation;
    private StructureProcessor processor;
    private BlockPos centerPosition;
    
    public Piece(TemplateManager template, CompoundNBT nbt) {
      super(ModFeatures.Pieces.SKY_ISLAND_CAMP_PIECE, nbt);
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
      super(ModFeatures.Pieces.SKY_ISLAND_CAMP_PIECE, 0);
      this.rotation = Rotation.NONE;
      this.resourceLocation = res;
      BlockPos blockpos = (BlockPos)SkyIslandCampPieces.POSITION_OFFSET.get(this.resourceLocation);
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
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)SkyIslandCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      setup(template, this.templatePosition, placementsettings);
    }





    
    protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
      if (function.equals("campers_spawn")) {
        
        int spawn = rand.nextInt(4);
        if (spawn > 0) {
          StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.CIVILIAN, 3);
        } else if (spawn == 0) {
          StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.TRADER, 1);
        } 
      } 
    }

    
    public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
      if (this.centerPosition == null) {
        
        WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
        return false;
      } 
      
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)SkyIslandCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      BlockPos offset = (BlockPos)SkyIslandCampPieces.POSITION_OFFSET.get(this.resourceLocation);
      this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
      
      return super.create(world, generator, random, bb, chunkPos);
    }
  }
}


