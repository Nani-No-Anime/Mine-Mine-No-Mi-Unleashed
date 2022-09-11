package xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house;

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
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;





public class SkyIslandHousePieces
{
  private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "sky_islands/house");
  
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
      super(ModFeatures.Pieces.SKY_ISLAND_HOUSE_PIECE, nbt);
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
      super(ModFeatures.Pieces.SKY_ISLAND_HOUSE_PIECE, 0);
      this.rotation = Rotation.NONE;
      this.resourceLocation = res;
      BlockPos blockpos = (BlockPos)SkyIslandHousePieces.POSITION_OFFSET.get(this.resourceLocation);
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
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)SkyIslandHousePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      setup(template, this.templatePosition, placementsettings);
    }





    
    protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
      if (function.equals("islander_spawn")) {
        
        int spawn = rand.nextInt(3);
        if (spawn == 0) {
          StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.CIVILIAN, 3);
        } else if (spawn == 1) {
          StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.TRADER, 1);
        } else if (spawn == 2) {
          
          world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
          if (world.getTileEntity(pos) instanceof CustomSpawnerTileEntity) {
            
            CustomSpawnerTileEntity spawner = null;
            spawner = (CustomSpawnerTileEntity)world.getTileEntity(pos);
            spawner.setSpawnerLimit(1);
            spawner.setSpawnerMob(ModEntities.ART_OF_WEATHER_TRAINER);
          } 
        } 
      } 
    }


    
    public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
      if (this.centerPosition == null) {
        
        WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
        return false;
      } 
      
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)SkyIslandHousePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      BlockPos offset = (BlockPos)SkyIslandHousePieces.POSITION_OFFSET.get(this.resourceLocation);
      this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
      
      return super.create(world, generator, random, bb, chunkPos);
    }
  }
}


