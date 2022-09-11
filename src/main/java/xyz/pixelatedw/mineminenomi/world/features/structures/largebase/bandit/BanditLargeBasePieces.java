package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit;

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





public class BanditLargeBasePieces
{
  private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "bandit/large_base");
  
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
      super(ModFeatures.Pieces.BANDIT_LARGE_BASE_PIECE, nbt);
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
      super(ModFeatures.Pieces.BANDIT_LARGE_BASE_PIECE, 0);
      this.rotation = Rotation.NONE;
      this.resourceLocation = res;
      BlockPos blockpos = (BlockPos)BanditLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
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
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      setup(template, this.templatePosition, placementsettings);
    }



    
    protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
      if (function.equals("tower_supplies")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_TOWER_CHEST);
      }
      if (function.equals("tent_supplies")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_TENT_CHEST);
      }
      if (function.equals("secret_stash")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_SECRET_STASH_CHEST);
      }
      if (function.equals("kitchen_supplies")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_FOOD_CHEST);
      }
      if (function.equals("dorm_supplies")) {
        StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_DORM_CHEST);
      }
      
      if (function.equals("tent_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 2);
      }
      if (function.equals("back_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 6);
      }
      if (function.equals("tower_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.SNIPER, 1);
      }
      if (function.equals("main_tower_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.SNIPER, 3);
      }
      if (function.equals("court_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 5);
      }
      if (function.equals("storage_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.CAPTAIN, 3);
      }
      if (function.equals("kitchen_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
      }
      if (function.equals("bedroom_spawn")) {
        StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
      }
    }

    
    public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
      if (this.centerPosition == null) {
        
        WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
        return false;
      } 
      
      PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
      BlockPos offset = (BlockPos)BanditLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
      this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
      
      return super.create(world, generator, random, bb, chunkPos);
    }
  }
}


