package xyz.pixelatedw.mineminenomi.world.features.structures.training.swordsmandojo;

import java.util.Random;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
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
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;

public class SwordsmanDojoPiece
  extends TemplateStructurePiece
{
  private ResourceLocation resourceLocation;
  private Rotation rotation;
  
  public SwordsmanDojoPiece(TemplateManager template, CompoundNBT nbt) {
    super(ModFeatures.Pieces.SWORDSMAN_DOJO_PIECE, nbt);
    this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
    this.rotation = Rotation.valueOf(nbt.getString("Rot"));
    build(template);
  }

  
  public SwordsmanDojoPiece(TemplateManager template, BlockPos pos, Rotation rot) {
    super(ModFeatures.Pieces.SWORDSMAN_DOJO_PIECE, 0);
    this.templatePosition = pos;
    this.rotation = rot;
    this.resourceLocation = new ResourceLocation("mineminenomi", "unaligned/swordsman_dojo");
    build(template);
  }


  
  protected void readAdditional(CompoundNBT nbt) {
    super.readAdditional(nbt);
    nbt.putString("Template", this.resourceLocation.toString());
    nbt.putString("Rot", this.rotation.name());
  }

  
  private void build(TemplateManager templateManager) {
    Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
    PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
    setup(template, this.templatePosition, placementsettings);
  }


  
  protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
    if (function.equals("trainer_spawn")) {
      
      world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
      TileEntity spawner = world.getTileEntity(pos);
      if (spawner instanceof CustomSpawnerTileEntity) {
        
        ((CustomSpawnerTileEntity)spawner).setSpawnerLimit(1);
        ((CustomSpawnerTileEntity)spawner).setSpawnerMob(ModEntities.SWORDSMAN_TRAINER);
      } 
    } 
  }


  
  public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
    PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
    BlockPos blockpos = BlockPos.ZERO;
    BlockPos blockpos1 = this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
    int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
    BlockPos blockpos2 = this.templatePosition;
    this.templatePosition = this.templatePosition.add(0, i - 90, 0);
    boolean flag = super.create(world, generator, random, bb, chunkPos);
    
    this.templatePosition = blockpos2;
    return flag;
  }
}


