/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.feature.structure.StructurePiece;
/*     */ import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
/*     */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*     */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.Template;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarineLargeBasePieces
/*     */ {
/*  35 */   private static final ResourceLocation STRUCTURE = new ResourceLocation("mineminenomi", "marine/large_base");
/*  36 */   private static final ResourceLocation PRISON = new ResourceLocation("mineminenomi", "marine/large_base_prison");
/*     */   
/*  38 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  39 */     .put(STRUCTURE, new BlockPos(0, 0, 0))
/*  40 */     .put(PRISON, new BlockPos(15, -14, 16))
/*  41 */     .build();
/*     */   
/*  43 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  44 */     .put(STRUCTURE, new BlockPos(0, 0, 0))
/*  45 */     .put(PRISON, new BlockPos(0, 0, 0))
/*  46 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
/*  50 */     components.add(new Piece(templateManager, STRUCTURE, pos, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*  51 */     components.add(new Piece(templateManager, PRISON, pos, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece
/*     */   {
/*     */     private ResourceLocation resourceLocation;
/*     */     private Rotation rotation;
/*     */     private StructureProcessor processor;
/*     */     private BlockPos centerPosition;
/*     */     
/*     */     public Piece(TemplateManager template, CompoundNBT nbt) {
/*  63 */       super(ModFeatures.Pieces.MARINE_LARGE_BASE_PIECE, nbt);
/*  64 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/*  65 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/*  66 */       int centerX = nbt.getShort("CenterX");
/*  67 */       int centerY = nbt.getShort("CenterY");
/*  68 */       int centerZ = nbt.getShort("CenterZ");
/*  69 */       this.centerPosition = new BlockPos(centerX, centerY, centerZ);
/*  70 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
/*  71 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, StructureProcessor proc) {
/*  76 */       super(ModFeatures.Pieces.MARINE_LARGE_BASE_PIECE, 0);
/*  77 */       this.rotation = Rotation.NONE;
/*  78 */       this.resourceLocation = res;
/*  79 */       BlockPos blockpos = (BlockPos)MarineLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/*  80 */       this.centerPosition = pos;
/*  81 */       this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/*  82 */       this.processor = proc;
/*  83 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void readAdditional(CompoundNBT nbt) {
/*  89 */       super.readAdditional(nbt);
/*  90 */       nbt.putString("Template", this.resourceLocation.toString());
/*  91 */       nbt.putString("Rot", this.rotation.name());
/*  92 */       nbt.putInt("CenterX", this.centerPosition.getX());
/*  93 */       nbt.putInt("CenterY", this.centerPosition.getY());
/*  94 */       nbt.putInt("CenterZ", this.centerPosition.getZ());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/*  99 */       Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/* 100 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 101 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 108 */       if (function.equals("food_storage_chest")) {
/* 109 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_FOOD_CHEST);
/*     */       }
/* 111 */       if (function.equals("enchantment_chest")) {
/* 112 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_LAB_CHEST);
/*     */       }
/* 114 */       if (function.equals("supplies_chest")) {
/* 115 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_GENERIC_CHEST);
/*     */       }
/* 117 */       if (function.equals("captain_room_chest")) {
/* 118 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
/*     */       }
/* 120 */       boolean chest = rand.nextBoolean();
/*     */       
/* 122 */       if (chest) {
/*     */         
/* 124 */         if (function.equals("office_chest_1")) {
/* 125 */           StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
/*     */         
/*     */         }
/*     */       }
/* 129 */       else if (function.equals("office_chest_2")) {
/* 130 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_MARINE_BASE_CAPTAIN_CHEST);
/*     */       } 
/*     */ 
/*     */       
/* 134 */       StructuresHelper.StructureFaction prisoners1 = world.getRandom().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
/* 135 */       StructuresHelper.StructureFaction prisoners2 = world.getRandom().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
/* 136 */       StructuresHelper.StructureFaction prisoners3 = world.getRandom().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
/* 137 */       StructuresHelper.StructureFaction prisoners4 = world.getRandom().nextBoolean() ? StructuresHelper.StructureFaction.BANDIT : StructuresHelper.StructureFaction.PIRATE;
/*     */       
/* 139 */       boolean extraSpawns = rand.nextBoolean();
/*     */       
/* 141 */       if (function.equals("prison_spawn_1")) {
/* 142 */         StructuresHelper.spawnMobs(world, pos, prisoners1, StructuresHelper.StructureSpawnType.GRUNT, 3);
/*     */       }
/* 144 */       if (extraSpawns && function.equals("prison_spawn_2")) {
/* 145 */         StructuresHelper.spawnMobs(world, pos, prisoners2, StructuresHelper.StructureSpawnType.GRUNT, 3);
/*     */       }
/* 147 */       if (function.equals("prison_spawn_3")) {
/* 148 */         StructuresHelper.spawnMobs(world, pos, prisoners3, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*     */       }
/* 150 */       if (extraSpawns && function.equals("prison_spawn_4")) {
/* 151 */         StructuresHelper.spawnMobs(world, pos, prisoners4, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*     */       }
/* 153 */       if (function.equals("front_desk_spawn")) {
/* 154 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 156 */       if (function.equals("prison_guard_spawn")) {
/* 157 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 3);
/*     */       }
/* 159 */       if (function.equals("dinner_spawn")) {
/* 160 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 162 */       if (function.equals("bedrom_spawn")) {
/* 163 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 3);
/*     */       }
/* 165 */       if (function.equals("lounge_spawn_1")) {
/* 166 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 168 */       if (function.equals("lounge_spawn_2")) {
/* 169 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.BOMBER, 5);
/*     */       }
/* 171 */       if (function.equals("captain_room_spawn")) {
/* 172 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       }
/* 174 */       if (function.equals("office_spawn")) {
/* 175 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       }
/* 177 */       if (function.equals("rooftop_spawn")) {
/* 178 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.SNIPER, 5);
/*     */       }
/* 180 */       if (function.equals("balcony_spawn")) {
/* 181 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.SNIPER, 1);
/*     */       }
/*     */       
/* 184 */       if (function.equals("special_prison")) {
/* 185 */         StructuresHelper.spawnMobs(world, pos, prisoners4, StructuresHelper.StructureSpawnType.GRUNT, 4);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 191 */       if (this.centerPosition == null) {
/*     */         
/* 193 */         WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
/* 194 */         return false;
/*     */       } 
/*     */       
/* 197 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 198 */       BlockPos offset = (BlockPos)MarineLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/* 199 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 201 */       return super.create(world, generator, random, bb, chunkPos);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\largebase\marine\MarineLargeBasePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */