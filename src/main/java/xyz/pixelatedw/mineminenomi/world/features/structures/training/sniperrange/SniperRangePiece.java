/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures.training.sniperrange;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.Mirror;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.Rotation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.util.math.Vec3i;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.Heightmap;
/*    */ import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
/*    */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*    */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*    */ 
/*    */ public class SniperRangePiece
/*    */   extends TemplateStructurePiece
/*    */ {
/*    */   private ResourceLocation resourceLocation;
/*    */   private Rotation rotation;
/*    */   
/*    */   public SniperRangePiece(TemplateManager template, CompoundNBT nbt) {
/* 34 */     super(ModFeatures.Pieces.SNIPER_RANGE_PIECE, nbt);
/* 35 */     this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/* 36 */     this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/* 37 */     build(template);
/*    */   }
/*    */ 
/*    */   
/*    */   public SniperRangePiece(TemplateManager template, BlockPos pos, Rotation rot) {
/* 42 */     super(ModFeatures.Pieces.SNIPER_RANGE_PIECE, 0);
/* 43 */     this.templatePosition = pos;
/* 44 */     this.rotation = rot;
/* 45 */     this.resourceLocation = new ResourceLocation("mineminenomi", "unaligned/sniper_range");
/* 46 */     build(template);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void readAdditional(CompoundNBT nbt) {
/* 52 */     super.readAdditional(nbt);
/* 53 */     nbt.putString("Template", this.resourceLocation.toString());
/* 54 */     nbt.putString("Rot", this.rotation.name());
/*    */   }
/*    */ 
/*    */   
/*    */   private void build(TemplateManager templateManager) {
/* 59 */     Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/* 60 */     PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
/* 61 */     setup(template, this.templatePosition, placementsettings);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 67 */     if (function.equals("trainer_spawn")) {
/*    */       
/* 69 */       world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
/* 70 */       TileEntity spawner = world.getTileEntity(pos);
/* 71 */       if (spawner instanceof CustomSpawnerTileEntity) {
/*    */         
/* 73 */         ((CustomSpawnerTileEntity)spawner).setSpawnerLimit(1);
/* 74 */         ((CustomSpawnerTileEntity)spawner).setSpawnerMob(ModEntities.SNIPER_TRAINER);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 82 */     PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
/* 83 */     BlockPos blockpos = BlockPos.ZERO;
/* 84 */     BlockPos blockpos1 = this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
/* 85 */     int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
/* 86 */     BlockPos blockpos2 = this.templatePosition;
/* 87 */     this.templatePosition = this.templatePosition.add(0, i - 90, 0);
/* 88 */     boolean flag = super.create(world, generator, random, bb, chunkPos);
/*    */     
/* 90 */     this.templatePosition = blockpos2;
/* 91 */     return flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\training\sniperrange\SniperRangePiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */