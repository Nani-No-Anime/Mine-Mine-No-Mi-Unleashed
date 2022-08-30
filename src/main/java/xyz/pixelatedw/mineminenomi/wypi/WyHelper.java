/*      */ package xyz.pixelatedw.mineminenomi.wypi;
/*      */ 
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.gson.Gson;
/*      */ import com.mojang.blaze3d.matrix.MatrixStack;
/*      */ import com.mojang.blaze3d.systems.RenderSystem;
/*      */ import java.awt.Color;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.Writer;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.URL;
/*      */ import java.text.DateFormat;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.function.Predicate;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockState;
/*      */ import net.minecraft.block.Blocks;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.gui.screen.inventory.InventoryScreen;
/*      */ import net.minecraft.client.renderer.BufferBuilder;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.Vector3f;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.dispenser.IPosition;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntitySpawnPlacementRegistry;
/*      */ import net.minecraft.entity.EntityType;
/*      */ import net.minecraft.entity.LivingEntity;
/*      */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.player.PlayerEntity;
/*      */ import net.minecraft.entity.player.ServerPlayerEntity;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.CompoundNBT;
/*      */ import net.minecraft.network.IPacket;
/*      */ import net.minecraft.network.play.server.SSpawnParticlePacket;
/*      */ import net.minecraft.particles.IParticleData;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.Direction;
/*      */ import net.minecraft.util.EntityPredicates;
/*      */ import net.minecraft.util.Mirror;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.ResourceLocationException;
/*      */ import net.minecraft.util.Rotation;
/*      */ import net.minecraft.util.Util;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.BlockRayTraceResult;
/*      */ import net.minecraft.util.math.ChunkPos;
/*      */ import net.minecraft.util.math.EntityRayTraceResult;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.math.RayTraceContext;
/*      */ import net.minecraft.util.math.RayTraceResult;
/*      */ import net.minecraft.util.math.Vec3d;
/*      */ import net.minecraft.util.math.Vec3i;
/*      */ import net.minecraft.world.IBlockReader;
/*      */ import net.minecraft.world.IWorld;
/*      */ import net.minecraft.world.IWorldReader;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldType;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import net.minecraft.world.chunk.ChunkSection;
/*      */ import net.minecraft.world.chunk.IChunk;
/*      */ import net.minecraft.world.gen.ChunkGenerator;
/*      */ import net.minecraft.world.gen.Heightmap;
/*      */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*      */ import net.minecraft.world.gen.feature.template.IntegrityProcessor;
/*      */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*      */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*      */ import net.minecraft.world.gen.feature.template.Template;
/*      */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*      */ import net.minecraft.world.server.ChunkHolder;
/*      */ import net.minecraft.world.server.ServerWorld;
/*      */ import net.minecraft.world.spawner.WorldEntitySpawner;
/*      */ import net.minecraftforge.versions.mcp.MCPVersion;
/*      */ import xyz.pixelatedw.mineminenomi.mixins.ITemplateMixin;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WyHelper
/*      */ {
/*      */   @Deprecated
/*      */   public static boolean isNullOrEmpty(String str) {
/*  123 */     if (str != null && !str.isEmpty() && !str.equalsIgnoreCase("n/a"))
/*  124 */       return false; 
/*  125 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String formatBytes(long bytes) {
/*  130 */     int unit = 1024;
/*  131 */     if (bytes < unit)
/*  132 */       return bytes + " B"; 
/*  133 */     int exp = (int)(Math.log(bytes) / Math.log(unit));
/*  134 */     String pre = "KMGTPE".charAt(exp - 1) + "";
/*  135 */     return String.format("%.1f %sB", new Object[] { Double.valueOf(bytes / Math.pow(unit, exp)), pre });
/*      */   }
/*      */ 
/*      */   
/*      */   public static String upperCaseFirst(String text) {
/*  140 */     return Character.toUpperCase(text.charAt(0)) + text.substring(1) + " ";
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getResourceName(String text) {
/*  145 */     return text
/*  146 */       .replaceAll("[ \\t]+$", "")
/*  147 */       .replaceAll("\\(", "")
/*  148 */       .replaceAll("\\)", "")
/*  149 */       .replaceAll("\\s+", "_")
/*  150 */       .replaceAll("[\\'\\:\\-\\,\\#]", "")
/*  151 */       .replaceAll("\\&", "and").toLowerCase();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String escapeJSON(String raw) {
/*  156 */     String escaped = raw;
/*  157 */     escaped = escaped.replace("\\", "\\\\");
/*  158 */     escaped = escaped.replace("\"", "\\\"");
/*  159 */     escaped = escaped.replace("\b", "\\b");
/*  160 */     escaped = escaped.replace("\f", "\\f");
/*  161 */     escaped = escaped.replace("\n", "\\n");
/*  162 */     escaped = escaped.replace("\r", "\\r");
/*  163 */     escaped = escaped.replace("\t", "\\t");
/*  164 */     return escaped;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Color hslToColor(float h, float s, float l) {
/*  175 */     if (s < 0.0F || s > 100.0F) {
/*      */       
/*  177 */       String message = "Color parameter outside of expected range - Saturation";
/*  178 */       throw new IllegalArgumentException(message);
/*      */     } 
/*      */     
/*  181 */     if (l < 0.0F || l > 100.0F) {
/*      */       
/*  183 */       String message = "Color parameter outside of expected range - Luminance";
/*  184 */       throw new IllegalArgumentException(message);
/*      */     } 
/*      */     
/*  187 */     h %= 360.0F;
/*  188 */     h /= 360.0F;
/*  189 */     s /= 100.0F;
/*  190 */     l /= 100.0F;
/*      */     
/*  192 */     float q = 0.0F;
/*      */     
/*  194 */     if (l < 0.5D) {
/*  195 */       q = l * (1.0F + s);
/*      */     } else {
/*  197 */       q = l + s - s * l;
/*      */     } 
/*  199 */     float p = 2.0F * l - q;
/*      */     
/*  201 */     float r = Math.max(0.0F, hueToRGB(p, q, h + 0.33333334F));
/*  202 */     float g = Math.max(0.0F, hueToRGB(p, q, h));
/*  203 */     float b = Math.max(0.0F, hueToRGB(p, q, h - 0.33333334F));
/*      */     
/*  205 */     r = Math.min(r, 1.0F);
/*  206 */     g = Math.min(g, 1.0F);
/*  207 */     b = Math.min(b, 1.0F);
/*      */     
/*  209 */     return new Color(r, g, b);
/*      */   }
/*      */ 
/*      */   
/*      */   private static float hueToRGB(float p, float q, float h) {
/*  214 */     if (h < 0.0F) {
/*  215 */       h++;
/*      */     }
/*  217 */     if (h > 1.0F) {
/*  218 */       h--;
/*      */     }
/*  220 */     if (6.0F * h < 1.0F) {
/*  221 */       return p + (q - p) * 6.0F * h;
/*      */     }
/*  223 */     if (2.0F * h < 1.0F) {
/*  224 */       return q;
/*      */     }
/*  226 */     if (3.0F * h < 2.0F) {
/*  227 */       return p + (q - p) * 6.0F * (0.6666667F - h);
/*      */     }
/*  229 */     return p;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String rgbToHex(int red, int green, int blue) {
/*  234 */     return String.format("#%02X%02X%02X", new Object[] { Integer.valueOf(red), Integer.valueOf(green), Integer.valueOf(blue) });
/*      */   }
/*      */ 
/*      */   
/*      */   public static Color hexToRGB(String hexColor) {
/*  239 */     if (hexColor.startsWith("#")) {
/*  240 */       hexColor = hexColor.substring(1);
/*      */     }
/*  242 */     if (hexColor.length() == 8) {
/*      */       
/*  244 */       int r = Integer.parseInt(hexColor.substring(0, 2), 16);
/*  245 */       int g = Integer.parseInt(hexColor.substring(2, 4), 16);
/*  246 */       int b = Integer.parseInt(hexColor.substring(4, 6), 16);
/*  247 */       int a = Integer.parseInt(hexColor.substring(6, 8), 16);
/*  248 */       return new Color(r, g, b, a);
/*      */     } 
/*      */     
/*  251 */     return Color.decode("#" + hexColor);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Color getComplementaryColor(Color color) {
/*  256 */     return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
/*      */   }
/*      */ 
/*      */   
/*      */   public static float colorTolerance(float tolerance) {
/*  261 */     return colorTolerance(tolerance, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float colorTolerance(float tolerance, boolean hasDisturbance) {
/*  266 */     float color = (new Random()).nextFloat();
/*      */     
/*  268 */     if (color <= tolerance || (!hasDisturbance && color >= tolerance + 0.3D)) {
/*  269 */       return tolerance;
/*      */     }
/*  271 */     return color;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ) {
/*  280 */     spawnParticles(data, world, posX, posY, posZ, 0.0F, 0.0F, 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ, float motionX, float motionY, float motionZ) {
/*  285 */     SSpawnParticlePacket sSpawnParticlePacket = new SSpawnParticlePacket(data, true, (float)posX, (float)posY, (float)posZ, motionX, motionY, motionZ, 0.0F, 1);
/*      */     
/*  287 */     for (int j = 0; j < world.getPlayers().size(); j++) {
/*      */       
/*  289 */       ServerPlayerEntity player = world.getPlayers().get(j);
/*  290 */       BlockPos blockpos = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());
/*  291 */       if (blockpos.withinDistance((IPosition)new Vec3d(posX, posY, posZ), 512.0D))
/*      */       {
/*  293 */         player.connection.sendPacket((IPacket)sSpawnParticlePacket);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static Vec3d propulsion(LivingEntity entity, double extraVelX, double extraVelZ) {
/*  300 */     return propulsion(entity, extraVelX, 0.0D, extraVelZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Vec3d propulsion(LivingEntity entity, double extraVelX, double extraVelY, double extraVelZ) {
/*  305 */     return entity.getLook(1.0F).mul(extraVelX, extraVelY, extraVelZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius) {
/*  310 */     return getEntitiesNear(pos, world, radius, (Class<? extends T>[])new Class[] { LivingEntity.class });
/*      */   }
/*      */ 
/*      */   
/*      */   public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Class<? extends T>... classEntities) {
/*  315 */     return getEntitiesNear(pos, world, radius, null, classEntities);
/*      */   }
/*      */ 
/*      */   
/*      */   public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Predicate<Entity> predicate, Class<? extends T>... classEntities) {
/*  320 */     if (predicate != null) {
/*  321 */       predicate = predicate.and(EntityPredicates.NOT_SPECTATING);
/*      */     } else {
/*  323 */       predicate = EntityPredicates.NOT_SPECTATING;
/*      */     } 
/*  325 */     AxisAlignedBB aabb = (new AxisAlignedBB(pos)).grow(radius, radius, radius);
/*  326 */     List<T> list = new ArrayList<>();
/*  327 */     for (Class<? extends T> clzz : classEntities)
/*      */     {
/*  329 */       list.addAll(world.getEntitiesWithinAABB(clzz, aabb, predicate));
/*      */     }
/*  331 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public static <T extends PlayerEntity> List<T> getNearestPlayers(IWorld world, double x, double y, double z, double distance) {
/*  336 */     List<T> list = new ArrayList<>();
/*  337 */     for (PlayerEntity target : world.getPlayers()) {
/*      */       
/*  339 */       if (EntityPredicates.NOT_SPECTATING.test(target) && EntityPredicates.IS_LIVING_ALIVE.test(target)) {
/*      */         
/*  341 */         double d0 = target.getDistanceSq(x, y, z);
/*  342 */         if (distance < 0.0D || d0 < distance * distance)
/*      */         {
/*  344 */           list.add((T)target);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  349 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends Entity> List<T> getEntitiesNearSphere(BlockPos pos, World world, double radius, @Nullable Predicate<Entity> predicate, Class<? extends T>... classEntities) {
/*  355 */     radius *= 2.0D;
/*  356 */     if (predicate != null) {
/*  357 */       predicate = predicate.and(EntityPredicates.NOT_SPECTATING);
/*      */     } else {
/*  359 */       predicate = EntityPredicates.NOT_SPECTATING;
/*      */     } 
/*  361 */     int x = pos.getX();
/*  362 */     int y = pos.getY();
/*  363 */     int z = pos.getZ();
/*      */     
/*  365 */     AxisAlignedBB aabb = (new AxisAlignedBB(x, y, z, x, y, z)).grow(radius, radius, radius);
/*  366 */     List<T> list = new ArrayList<>();
/*  367 */     for (Class<? extends T> clzz : classEntities)
/*      */     {
/*  369 */       list.addAll(world.getEntitiesWithinAABB(clzz, aabb, predicate));
/*      */     }
/*      */     
/*  372 */     for (int i = 0; i < list.size(); i++) {
/*      */       
/*  374 */       if (((Entity)list.get(i)).getDistanceSq(x, y, z) >= radius) {
/*      */         
/*  376 */         list.remove(i);
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  381 */     return list;
/*      */   }
/*      */   
/*      */   public static RayTraceResult rayTraceBlocksAndEntities(Entity entity) {
/*  385 */     return rayTraceBlocksAndEntities(entity, 1024.0D, 0.4F);
/*      */   }
/*      */   
/*      */   public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance) {
/*  389 */     return rayTraceBlocksAndEntities(entity, distance, 0.2F);
/*      */   }
/*      */   public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance, float entityBoxRange) {
/*      */     EntityRayTraceResult entityRayTraceResult = null;
/*  393 */     Vec3d lookVec = entity.getLookVec();
/*  394 */     Vec3d startVec = entity.getPositionVec().add(0.0D, entity.getEyeHeight(), 0.0D);
/*  395 */     Vec3d endVec = startVec.add(entity.getLookVec().scale(distance));
/*  396 */     BlockRayTraceResult blockRayTraceResult = entity.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity));
/*  397 */     RayTraceResult entityResult = null;
/*      */     
/*  399 */     for (int i = 0; i < distance * 2.0D; i++) {
/*      */       
/*  401 */       if (entityResult != null) {
/*      */         break;
/*      */       }
/*  404 */       float scale = i / 2.0F;
/*  405 */       Vec3d pos = startVec.add(lookVec.scale(scale));
/*      */       
/*  407 */       Vec3d min = pos.add(entityBoxRange, entityBoxRange, entityBoxRange);
/*  408 */       Vec3d max = pos.add(-entityBoxRange, -entityBoxRange, -entityBoxRange);
/*  409 */       for (Entity e : entity.world.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(min.x, min.y, min.z, max.x, max.y, max.z))) {
/*  410 */         if (e instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity) {
/*      */           continue;
/*      */         }
/*  413 */         entityRayTraceResult = new EntityRayTraceResult(e, pos);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  418 */     if (entityRayTraceResult != null && entityRayTraceResult.getHitVec().distanceTo(startVec) <= blockRayTraceResult.getHitVec().distanceTo(startVec)) {
/*  419 */       return (RayTraceResult)entityRayTraceResult;
/*      */     }
/*  421 */     return (RayTraceResult)blockRayTraceResult;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BlockPos rayTraceBlockSafe(PlayerEntity player, float range) {
/*  428 */     World world = player.world;
/*  429 */     Vec3d startVec = player.getPositionVec().add(0.0D, player.getEyeHeight(), 0.0D);
/*  430 */     Vec3d endVec = startVec.add(player.getLookVec().scale(range));
/*  431 */     BlockRayTraceResult result = world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)player));
/*  432 */     BlockPos dashPos = result.getFace().equals(Direction.DOWN) ? result.getPos().down(2) : result.getPos().offset(result.getFace());
/*      */     
/*  434 */     if (dashPos.getY() > player.world.getMaxHeight()) {
/*  435 */       dashPos = dashPos.add(0, player.world.getMaxHeight() - dashPos.getY(), 0);
/*      */     }
/*  437 */     return dashPos;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPosClearForPlayer(World world, BlockPos pos) {
/*  442 */     return ((world.isAirBlock(pos) || world.getBlockState(pos).getCollisionShape((IBlockReader)world, pos).isEmpty()) && (world
/*  443 */       .isAirBlock(pos.up()) || world.getBlockState(pos.up()).getCollisionShape((IBlockReader)world, pos.up()).isEmpty()));
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockPos getClearPositionForPlayer(PlayerEntity player, BlockPos pos) {
/*  448 */     boolean posIsFree = isPosClearForPlayer(player.world, pos);
/*  449 */     int i = 0;
/*      */     
/*  451 */     while (!posIsFree) {
/*      */       
/*  453 */       Direction dir = Direction.values()[i];
/*  454 */       pos = pos.add(
/*  455 */           -dir.getXOffset() * 2, 
/*  456 */           -dir.getYOffset() * 2, 
/*  457 */           -dir.getZOffset() * 2);
/*  458 */       posIsFree = isPosClearForPlayer(player.world, pos);
/*  459 */       if (posIsFree)
/*      */         break; 
/*  461 */       if (i < (Direction.values()).length - 1) {
/*  462 */         i++;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  467 */     return posIsFree ? pos : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockRayTraceResult rayTraceBlocks(Entity source, double distance) {
/*  472 */     Vec3d startVec = source.getPositionVec().add(0.0D, source.getEyeHeight(), 0.0D);
/*  473 */     Vec3d endVec = startVec.add(source.getLookVec().scale(distance));
/*  474 */     return source.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, source));
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockRayTraceResult rayTraceBlocks(World world, Vec3d startVec, Vec3d endVec) {
/*  479 */     return world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null));
/*      */   }
/*      */ 
/*      */   
/*      */   public static EntityRayTraceResult rayTraceEntities(Entity source, double distance) {
/*  484 */     Vec3d startVec = source.getPositionVec().add(0.0D, source.getEyeHeight(), 0.0D);
/*  485 */     Vec3d endVec = startVec.add(source.getLookVec().scale(distance));
/*  486 */     AxisAlignedBB boundingBox = source.getBoundingBox().grow(distance);
/*      */     
/*  488 */     for (Entity entity : source.world.getEntitiesInAABBexcluding(source, boundingBox, entity -> (entity != source))) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  493 */       AxisAlignedBB entityBB = entity.getBoundingBox().grow(1.0D);
/*  494 */       Optional<Vec3d> optional = entityBB.rayTrace(startVec, endVec);
/*      */       
/*  496 */       if (optional.isPresent()) {
/*      */         
/*  498 */         Vec3d targetVec = optional.get();
/*  499 */         double distFromSource = MathHelper.sqrt(startVec.squareDistanceTo(targetVec));
/*      */         
/*  501 */         if (distFromSource < distance) {
/*      */           
/*  503 */           List<Entity> targets = getEntitiesNear(new BlockPos(targetVec), source.world, 1.25D);
/*  504 */           targets.remove(source);
/*  505 */           Optional<Entity> target = targets.stream().findFirst();
/*      */           
/*  507 */           if (target.isPresent())
/*      */           {
/*  509 */             return new EntityRayTraceResult(target.get(), endVec);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  515 */     return new EntityRayTraceResult(null, endVec);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBlockNearby(LivingEntity player, int radius, Block... blocks) {
/*  520 */     for (Block b : blocks) {
/*      */       
/*  522 */       for (int x = -radius; x <= radius; x++) {
/*  523 */         for (int y = -radius; y <= radius; y++) {
/*  524 */           for (int z = -radius; z <= radius; z++) {
/*      */             
/*  526 */             if (player.world.getBlockState(new BlockPos(player.getPosX() + x, player.getPosY() + y, player.getPosZ() + z)).getBlock() == b)
/*      */             {
/*  528 */               return true; } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  533 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<BlockPos> getNearbyBlocks(LivingEntity player, int radius) {
/*  538 */     return getNearbyBlocks(player.getPosition(), (IWorld)player.world, radius);
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius) {
/*  543 */     return getNearbyBlocks(pos, world, radius, (List<Block>)ImmutableList.of(Blocks.AIR));
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius, List<Block> bannedBlocks) {
/*  548 */     return getNearbyBlocks(pos, world, radius, null, (List<Block>)ImmutableList.of(Blocks.AIR));
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius, @Nullable Predicate<BlockPos> predicate, List<Block> bannedBlocks) {
/*  553 */     if (predicate == null) {
/*  554 */       predicate = (blockPos -> true);
/*      */     }
/*  556 */     List<BlockPos> blockLocations = new ArrayList<>();
/*  557 */     for (int x = -radius; x <= radius; x++) {
/*      */       
/*  559 */       for (int y = -radius; y <= radius; y++) {
/*      */         
/*  561 */         for (int z = -radius; z <= radius; z++) {
/*      */           
/*  563 */           BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
/*  564 */           if (predicate.test(newPos)) {
/*      */ 
/*      */             
/*  567 */             Block block = world.getBlockState(newPos).getBlock();
/*  568 */             if (!bannedBlocks.contains(block))
/*  569 */               blockLocations.add(newPos); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  574 */     return blockLocations;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<BlockPos> getNearbyTileEntities(LivingEntity player, int radius) {
/*  579 */     return getNearbyTileEntities(player.getPosition(), player.world, radius);
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<BlockPos> getNearbyTileEntities(BlockPos pos, World world, int radius) {
/*  584 */     List<BlockPos> blockLocations = new ArrayList<>();
/*  585 */     for (int x = -radius; x <= radius; x++) {
/*      */       
/*  587 */       for (int y = -radius; y <= radius; y++) {
/*      */         
/*  589 */         for (int z = -radius; z <= radius; z++) {
/*      */           
/*  591 */           BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
/*  592 */           if (world.getBlockState(newPos).getBlock() != Blocks.AIR && world.getTileEntity(newPos) != null)
/*      */           {
/*  594 */             blockLocations.add(newPos);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  600 */     return blockLocations;
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius) {
/*  605 */     return findOnGroundSpawnLocation(world, type, spawnLocation, radius, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius, int offset) {
/*  611 */     BlockPos blockpos = null;
/*  612 */     for (int i = 0; i < 10; i++) {
/*      */       
/*  614 */       int x = (int)randomWithRange(spawnLocation.getX() - offset - radius, spawnLocation.getX() + offset + radius);
/*  615 */       int z = (int)randomWithRange(spawnLocation.getZ() - offset - radius, spawnLocation.getZ() + offset + radius);
/*  616 */       int y = world.getHeight(Heightmap.Type.WORLD_SURFACE, x, z);
/*  617 */       BlockPos blockpos1 = new BlockPos(x, y, z);
/*  618 */       if (WorldEntitySpawner.canCreatureTypeSpawnAtLocation(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, (IWorldReader)world, blockpos1, type)) {
/*      */         
/*  620 */         blockpos = blockpos1;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  625 */     return blockpos;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getTextureName(String texture) {
/*  630 */     for (String s : texture.split("/")) {
/*      */       
/*  632 */       if (s.contains(".png"))
/*      */       {
/*  634 */         return s.replace(".png", "");
/*      */       }
/*      */     } 
/*  637 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean hasInventoryFull(PlayerEntity player) {
/*  642 */     for (ItemStack stack : player.inventory.mainInventory) {
/*      */       
/*  644 */       if (stack.isEmpty()) {
/*  645 */         return false;
/*      */       }
/*      */     } 
/*  648 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean setBlockStateInChunk(World world, BlockPos pos, BlockState newState, int flags) {
/*  664 */     if (World.isOutsideBuildHeight(pos))
/*  665 */       return false; 
/*  666 */     if (!world.isRemote && world.getWorldInfo().getGenerator() == WorldType.DEBUG_ALL_BLOCK_STATES) {
/*  667 */       return false;
/*      */     }
/*      */     
/*  670 */     Chunk chunk = world.getChunkAt(pos);
/*  671 */     pos = pos.toImmutable();
/*      */     
/*  673 */     BlockState blockstate = chunk.setBlockState(pos, newState, ((flags & 0x40) != 0));
/*  674 */     if (blockstate != null) {
/*      */       
/*  676 */       world.getChunkProvider().getLightManager().checkBlock(pos);
/*      */       
/*  678 */       if ((flags & 0x2) != 0 && (!world.isRemote || (flags & 0x4) == 0) && (world.isRemote || chunk == null || (chunk.getLocationType() != null && chunk.getLocationType().isAtLeast(ChunkHolder.LocationType.TICKING)))) {
/*      */         
/*  680 */         world.markAndNotifyBlock(pos, chunk, blockstate, newState, flags);
/*  681 */         world.notifyBlockUpdate(pos, blockstate, newState, flags);
/*      */       } 
/*      */     } 
/*  684 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BlockState swapBlockData(IWorld world, BlockPos pos, BlockState newState) {
/*  690 */     IChunk chunk = world.getChunk(pos);
/*  691 */     ChunkSection cs = chunk.getSections()[pos.getY() >> 4];
/*  692 */     if (cs == Chunk.EMPTY_SECTION) {
/*      */       
/*  694 */       cs = new ChunkSection(pos.getY() >> 4 << 4);
/*  695 */       chunk.getSections()[pos.getY() >> 4] = cs;
/*      */     } 
/*  697 */     BlockState state = (BlockState)cs.getData().swap(pos.getX() & 0xF, pos.getY() & 0xF, pos.getZ() & 0xF, newState);
/*  698 */     return state;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean afterDate(String date) {
/*  707 */     DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
/*  708 */     Calendar target = null;
/*      */     
/*      */     try {
/*  711 */       target = Calendar.getInstance();
/*  712 */       target.setTime(df.parse(date));
/*      */     }
/*  714 */     catch (ParseException e) {
/*      */       
/*  716 */       e.printStackTrace();
/*  717 */       return false;
/*      */     } 
/*      */     
/*  720 */     Calendar now = Calendar.getInstance();
/*  721 */     return now.after(target);
/*      */   }
/*      */ 
/*      */   
/*      */   public static long getDaysSince(Date date) {
/*  726 */     Date now = new Date();
/*      */     
/*  728 */     long diffInMillies = Math.abs(now.getTime() - date.getTime());
/*  729 */     long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
/*      */     
/*  731 */     return diff;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double percentage(double percent, double value) {
/*  740 */     return percent / 100.0D * value;
/*      */   }
/*      */ 
/*      */   
/*      */   public static double randomWithRange(int min, int max) {
/*  745 */     return ((new Random()).nextInt(max + 1 - min) + min);
/*      */   }
/*      */ 
/*      */   
/*      */   public static double randomWithRange(Random rand, int min, int max) {
/*  750 */     return (rand.nextInt(max + 1 - min) + min);
/*      */   }
/*      */ 
/*      */   
/*      */   public static double randomDouble(Random rand) {
/*  755 */     return rand.nextDouble() * 2.0D - 1.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   public static double randomDouble() {
/*  760 */     return (new Random()).nextDouble() * 2.0D - 1.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int round(int value) {
/*  765 */     String valueString = "" + value;
/*      */     
/*  767 */     if (valueString.length() < 1) {
/*  768 */       return value;
/*      */     }
/*  770 */     return round(value, valueString.length() - 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int round(int value, int nth) {
/*  775 */     String valueString = "" + value;
/*      */     
/*  777 */     if (valueString.length() < 1 || nth < 0) {
/*  778 */       return value;
/*      */     }
/*  780 */     if (nth == 0) {
/*  781 */       nth = 1;
/*      */     }
/*  783 */     int n = (int)Math.pow(10.0D, (nth - 1));
/*  784 */     int r = 5 * n / 10;
/*      */     
/*  786 */     return (value + r) / n * n;
/*      */   }
/*      */ 
/*      */   
/*      */   public static long clamp(long num, long min, long max) {
/*  791 */     return (num < min) ? min : Math.min(num, max);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawColourOnScreen(int colour, int alpha, double posX, double posY, double width, double height, double zLevel) {
/*  800 */     int r = colour >> 16 & 0xFF;
/*  801 */     int g = colour >> 8 & 0xFF;
/*  802 */     int b = colour & 0xFF;
/*  803 */     drawColourOnScreen(r, g, b, alpha, posX, posY, width, height, zLevel);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawColourOnScreen(int red, int green, int blue, int alpha, double posX, double posY, double width, double height, double zLevel) {
/*  808 */     if (width <= 0.0D || height <= 0.0D)
/*      */       return; 
/*  810 */     RenderSystem.enableBlend();
/*  811 */     RenderSystem.disableTexture();
/*  812 */     BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
/*  813 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
/*  814 */     bufferbuilder.pos(posX, posY + height, zLevel).color(red, green, blue, alpha).endVertex();
/*  815 */     bufferbuilder.pos(posX + width, posY + height, zLevel).color(red, green, blue, alpha).endVertex();
/*  816 */     bufferbuilder.pos(posX + width, posY, zLevel).color(red, green, blue, alpha).endVertex();
/*  817 */     bufferbuilder.pos(posX, posY, zLevel).color(red, green, blue, alpha).endVertex();
/*  818 */     Tessellator.getInstance().draw();
/*  819 */     RenderSystem.enableTexture();
/*  820 */     RenderSystem.disableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v) {
/*  825 */     Minecraft.getInstance().getTextureManager().bindTexture(rs);
/*  826 */     BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
/*  827 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
/*  828 */     bufferbuilder.pos(x, (y + v), 1.0D).tex(0.0F, 1.0F).endVertex();
/*  829 */     bufferbuilder.pos((x + u), (y + v), 1.0D).tex(1.0F, 1.0F).endVertex();
/*  830 */     bufferbuilder.pos((x + u), y, 1.0D).tex(1.0F, 0.0F).endVertex();
/*  831 */     bufferbuilder.pos(x, y, 1.0D).tex(0.0F, 0.0F).endVertex();
/*  832 */     Tessellator.getInstance().draw();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v, String hexColor) {
/*  837 */     Color color = hexToRGB(hexColor);
/*  838 */     drawIcon(rs, x, y, u, v, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v, int intColor) {
/*  843 */     String hex = String.format("#%06X", new Object[] { Integer.valueOf(0xFFFFFF & intColor) });
/*  844 */     Color color = hexToRGB(hex);
/*  845 */     drawIcon(rs, x, y, u, v, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v, int red, int green, int blue, int alpha) {
/*  850 */     Minecraft.getInstance().getTextureManager().bindTexture(rs);
/*  851 */     BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
/*  852 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
/*  853 */     bufferbuilder.pos(x, (y + v), 1.0D).color(red, green, blue, alpha).tex(0.0F, 1.0F).endVertex();
/*  854 */     bufferbuilder.pos((x + u), (y + v), 1.0D).color(red, green, blue, alpha).tex(1.0F, 1.0F).endVertex();
/*  855 */     bufferbuilder.pos((x + u), y, 1.0D).color(red, green, blue, alpha).tex(1.0F, 0.0F).endVertex();
/*  856 */     bufferbuilder.pos(x, y, 1.0D).color(red, green, blue, alpha).tex(0.0F, 0.0F).endVertex();
/*  857 */     Tessellator.getInstance().draw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, LivingEntity entity) {
/*  867 */     InventoryScreen.drawEntityOnScreen(posX, posY, scale, mouseX, mouseY, entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawStringWithBorder(FontRenderer font, String text, int posX, int posY, int color) {
/*  872 */     String unformattedText = escapeTextFormattingChars(text);
/*  873 */     font.drawStringWithShadow(unformattedText, posX, posY - 0.7F, 1);
/*  874 */     font.drawStringWithShadow(unformattedText, posX, posY + 0.7F, 1);
/*  875 */     font.drawStringWithShadow(unformattedText, posX + 0.7F, posY, 1);
/*  876 */     font.drawStringWithShadow(unformattedText, posX - 0.7F, posY, 1);
/*  877 */     font.drawString(text, posX, posY, color);
/*  878 */     RenderSystem.enableAlphaTest();
/*  879 */     RenderSystem.enableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String escapeTextFormattingChars(String text) {
/*  884 */     return text.replaceAll("§[0-9a-f]", "");
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<String> splitString(FontRenderer font, String text, int posX, int wrapWidth) {
/*  889 */     while (text != null && text.endsWith("\n"))
/*      */     {
/*  891 */       text = text.substring(0, text.length() - 1);
/*      */     }
/*      */     
/*  894 */     List<String> newText = new ArrayList<>();
/*  895 */     for (String s : font.listFormattedStringToWidth(text, wrapWidth)) {
/*      */       
/*  897 */       if (font.getBidiFlag()) {
/*      */         
/*  899 */         int i = font.getStringWidth(font.bidiReorder(s));
/*  900 */         posX += wrapWidth - i;
/*      */       } 
/*      */       
/*  903 */       newText.add(s);
/*      */     } 
/*  905 */     return newText;
/*      */   }
/*      */ 
/*      */   
/*      */   public static float handleRotationFloat(LivingEntity entity, float partialTicks) {
/*  910 */     return entity.ticksExisted + partialTicks;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void rotateCorpse(MatrixStack matrixStack, LivingEntity entityLiving, float ageInTicks, float headYawOffset, float partialTicks) {
/*  915 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F + headYawOffset));
/*      */     
/*  917 */     if (entityLiving.deathTime > 0) {
/*      */       
/*  919 */       float animTime = (entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
/*  920 */       animTime = MathHelper.sqrt(animTime);
/*  921 */       if (animTime > 1.0F) {
/*  922 */         animTime = 1.0F;
/*      */       }
/*  924 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(animTime * 90.0F));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static float interpolateRotation(float lowerLimit, float upperLimit, float range) {
/*      */     float f3;
/*  937 */     for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  942 */     while (f3 >= 180.0F)
/*      */     {
/*  944 */       f3 -= 360.0F;
/*      */     }
/*      */     
/*  947 */     return lowerLimit + range * f3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void generateJSONLangs() {
/*  956 */     Map<String, String> sorted = sortAlphabetically(WyRegistry.getLangMap());
/*  957 */     Set<Map.Entry<String, String>> set = sorted.entrySet();
/*  958 */     Iterator<Map.Entry<String, String>> iter = set.iterator();
/*      */     
/*  960 */     Map.Entry<String, String> prevEntry = null;
/*      */     
/*  962 */     File langFolder = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.projectId + "/lang/");
/*  963 */     langFolder.mkdirs();
/*      */     
/*  965 */     if (langFolder.exists()) {
/*      */       try {
/*  967 */         Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.projectId + "/lang/en_us.json"), "UTF-8"));
/*      */         
/*  969 */         try { writer.write("{\n");
/*  970 */           while (iter.hasNext()) {
/*      */             
/*  972 */             Map.Entry<String, String> entry = iter.next();
/*      */             
/*  974 */             if (prevEntry != null)
/*      */             {
/*  976 */               if (!((String)prevEntry.getKey()).substring(0, 2).equals(((String)entry.getKey()).substring(0, 2)))
/*      */               {
/*  978 */                 writer.write("\n");
/*      */               }
/*      */             }
/*      */             
/*  982 */             String value = escapeJSON(entry.getValue());
/*  983 */             if (iter.hasNext()) {
/*  984 */               writer.write("\t\"" + (String)entry.getKey() + "\": \"" + value + "\",\n");
/*      */             } else {
/*  986 */               writer.write("\t\"" + (String)entry.getKey() + "\": \"" + value + "\"\n");
/*      */             } 
/*  988 */             prevEntry = entry;
/*      */           } 
/*  990 */           writer.write("}\n");
/*  991 */           writer.close();
/*  992 */           writer.close(); } catch (Throwable throwable) { try { writer.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; } 
/*  993 */       } catch (Exception e) {
/*      */         
/*  995 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static <T> List<T> shuffle(List<T> ar) {
/* 1004 */     Random rnd = new Random();
/*      */     
/* 1006 */     for (int i = ar.size() - 1; i > 0; i--) {
/*      */       
/* 1008 */       int index = rnd.nextInt(i + 1);
/*      */       
/* 1010 */       T a = ar.get(index);
/* 1011 */       ar.set(index, ar.get(i));
/* 1012 */       ar.set(i, a);
/*      */     } 
/*      */     
/* 1015 */     return ar;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static <T> List<T> shuffle(List<T> ar, long seed) {
/* 1022 */     Random rnd = new Random(seed);
/*      */     
/* 1024 */     for (int i = ar.size() - 1; i > 0; i--) {
/*      */       
/* 1026 */       int index = rnd.nextInt(i + 1);
/*      */       
/* 1028 */       T a = ar.get(index);
/* 1029 */       ar.set(index, ar.get(i));
/* 1030 */       ar.set(i, a);
/*      */     } 
/*      */     
/* 1033 */     return ar;
/*      */   }
/*      */ 
/*      */   
/*      */   public static <K extends Comparable, V extends Comparable> Map<K, V> sortAlphabetically(Map<K, V> map) {
/* 1038 */     List<Map.Entry<K, V>> entries = new LinkedList<>(map.entrySet());
/*      */     
/* 1040 */     Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
/*      */         {
/*      */           
/*      */           public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
/*      */           {
/* 1045 */             return ((Comparable)o1.getKey()).compareTo(o2.getKey());
/*      */           }
/*      */         });
/*      */     
/* 1049 */     Map<K, V> sortedMap = new LinkedHashMap<>();
/*      */     
/* 1051 */     for (Map.Entry<K, V> entry : entries)
/*      */     {
/* 1053 */       sortedMap.put(entry.getKey(), entry.getValue());
/*      */     }
/*      */     
/* 1056 */     return sortedMap;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BlockPos[][] splitArray(BlockPos[] arrayToSplit, int chunkSize) {
/* 1062 */     if (chunkSize <= 0)
/*      */     {
/* 1064 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1068 */     int rest = arrayToSplit.length % chunkSize;
/*      */     
/* 1070 */     int chunks = arrayToSplit.length / chunkSize + ((rest > 0) ? 1 : 0);
/*      */     
/* 1072 */     BlockPos[][] arrays = new BlockPos[chunks][];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1077 */     for (int i = 0; i < ((rest > 0) ? (chunks - 1) : chunks); i++)
/*      */     {
/*      */       
/* 1080 */       arrays[i] = Arrays.<BlockPos>copyOfRange(arrayToSplit, i * chunkSize, i * chunkSize + chunkSize);
/*      */     }
/* 1082 */     if (rest > 0)
/*      */     {
/*      */       
/* 1085 */       arrays[chunks - 1] = Arrays.<BlockPos>copyOfRange(arrayToSplit, (chunks - 1) * chunkSize, (chunks - 1) * chunkSize + rest);
/*      */     }
/* 1087 */     return arrays;
/*      */   }
/*      */ 
/*      */   
/*      */   public static byte[] serialize(Object obj) throws IOException {
/* 1092 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 1093 */     ObjectOutputStream os = new ObjectOutputStream(out);
/* 1094 */     os.writeObject(obj);
/* 1095 */     return out.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
/*      */     try {
/* 1102 */       ByteArrayInputStream in = new ByteArrayInputStream(data);
/* 1103 */       ObjectInputStream is = new ObjectInputStream(in);
/* 1104 */       is.close();
/* 1105 */       return is.readObject();
/*      */     }
/* 1107 */     catch (EOFException eOFException) {
/*      */ 
/*      */       
/* 1110 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final int getIndexOfItemStack(Item item, IInventory inven) {
/* 1115 */     for (int i = 0; i < inven.getSizeInventory(); i++) {
/*      */       
/* 1117 */       if (inven.getStackInSlot(i).getItem() == item)
/*      */       {
/* 1119 */         return i;
/*      */       }
/*      */     } 
/* 1122 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean saveNBTStructure(ServerWorld world, String name, BlockPos pos, BlockPos size, List<Block> toIgnore) {
/* 1127 */     if (!world.isRemote) {
/*      */       Template template;
/* 1129 */       ServerWorld serverworld = world;
/* 1130 */       TemplateManager templatemanager = serverworld.getStructureTemplateManager();
/* 1131 */       ResourceLocation res = new ResourceLocation(APIConfig.projectId, name);
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1136 */         template = templatemanager.getTemplateDefaulted(res);
/*      */       }
/* 1138 */       catch (ResourceLocationException ex) {
/*      */         
/* 1140 */         ex.printStackTrace();
/* 1141 */         return false;
/*      */       } 
/*      */       
/* 1144 */       toIgnore.add(Blocks.STRUCTURE_VOID);
/* 1145 */       toIgnore.add(Blocks.BEDROCK);
/*      */       
/* 1147 */       takeBlocksFromWorld(template, (World)world, pos, size, toIgnore);
/* 1148 */       template.setAuthor("?");
/*      */       
/*      */       try {
/* 1151 */         return templatemanager.writeToFile(res);
/*      */       }
/* 1153 */       catch (ResourceLocationException var7) {
/*      */         
/* 1155 */         return false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1160 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos) {
/* 1166 */     PlacementSettings placement = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk((ChunkPos)null);
/* 1167 */     placement.clearProcessors()
/* 1168 */       .addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK)
/* 1169 */       .addProcessor((StructureProcessor)new IntegrityProcessor(1.0F)).setRandom(new Random(Util.milliTime()));
/*      */     
/* 1171 */     return loadNBTStructure(world, name, pos, placement);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos, PlacementSettings settings) {
/* 1176 */     if (!world.isRemote) {
/*      */       Template template;
/* 1178 */       TemplateManager templatemanager = world.getStructureTemplateManager();
/* 1179 */       ResourceLocation res = new ResourceLocation("mineminenomi", name);
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1184 */         template = templatemanager.getTemplate(res);
/*      */       }
/* 1186 */       catch (ResourceLocationException ex) {
/*      */         
/* 1188 */         ex.printStackTrace();
/* 1189 */         return false;
/*      */       } 
/*      */       
/* 1192 */       if (template == null)
/*      */       {
/* 1194 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1198 */       BlockState blockstate = world.getBlockState(pos);
/* 1199 */       world.notifyBlockUpdate(pos, blockstate, blockstate, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1204 */       template.addBlocksToWorldChunk((IWorld)world, pos, settings);
/* 1205 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1209 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void takeBlocksFromWorld(Template template, World world, BlockPos startPos, BlockPos size, @Nullable List<Block> toIgnore) {
/* 1215 */     if (size.getX() >= 1 && size.getY() >= 1 && size.getZ() >= 1) {
/*      */       
/* 1217 */       BlockPos blockpos = startPos.add((Vec3i)size).add(-1, -1, -1);
/* 1218 */       List<Template.BlockInfo> list = Lists.newArrayList();
/* 1219 */       List<Template.BlockInfo> list1 = Lists.newArrayList();
/* 1220 */       List<Template.BlockInfo> list2 = Lists.newArrayList();
/* 1221 */       BlockPos blockpos1 = new BlockPos(Math.min(startPos.getX(), blockpos.getX()), Math.min(startPos.getY(), blockpos.getY()), Math.min(startPos.getZ(), blockpos.getZ()));
/* 1222 */       BlockPos blockpos2 = new BlockPos(Math.max(startPos.getX(), blockpos.getX()), Math.max(startPos.getY(), blockpos.getY()), Math.max(startPos.getZ(), blockpos.getZ()));
/* 1223 */       ((ITemplateMixin)template).setSize(size);
/*      */       
/* 1225 */       for (BlockPos blockpos3 : BlockPos.getAllInBoxMutable(blockpos1, blockpos2)) {
/*      */         
/* 1227 */         BlockPos blockpos4 = blockpos3.subtract((Vec3i)blockpos1);
/* 1228 */         BlockState blockstate = world.getBlockState(blockpos3);
/*      */         
/* 1230 */         if (toIgnore != null && toIgnore.contains(blockstate.getBlock())) {
/*      */           
/* 1232 */           world.setBlockState(blockpos3, Blocks.AIR.getDefaultState());
/* 1233 */           blockstate = world.getBlockState(blockpos3);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1238 */         TileEntity tileentity = world.getTileEntity(blockpos3);
/* 1239 */         if (tileentity != null) {
/*      */           
/* 1241 */           CompoundNBT compoundnbt = tileentity.write(new CompoundNBT());
/* 1242 */           compoundnbt.remove("x");
/* 1243 */           compoundnbt.remove("y");
/* 1244 */           compoundnbt.remove("z");
/* 1245 */           list1.add(new Template.BlockInfo(blockpos4, blockstate, compoundnbt)); continue;
/*      */         } 
/* 1247 */         if (!blockstate.isOpaqueCube((IBlockReader)world, blockpos3) && !blockstate.isCollisionShapeOpaque((IBlockReader)world, blockpos3)) {
/*      */           
/* 1249 */           list2.add(new Template.BlockInfo(blockpos4, blockstate, (CompoundNBT)null));
/*      */           
/*      */           continue;
/*      */         } 
/* 1253 */         list.add(new Template.BlockInfo(blockpos4, blockstate, (CompoundNBT)null));
/*      */       } 
/*      */ 
/*      */       
/* 1257 */       List<Template.BlockInfo> list3 = Lists.newArrayList();
/* 1258 */       list3.addAll(list);
/* 1259 */       list3.addAll(list1);
/* 1260 */       list3.addAll(list2);
/* 1261 */       ((ITemplateMixin)template).getBlocks().clear();
/* 1262 */       ((ITemplateMixin)template).getBlocks().add(list3);
/* 1263 */       ((ITemplateMixin)template).getEntities().clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSurfaceFlat(ChunkGenerator<?> chunkGen, int chunkPosX, int chunkPosZ, int difference) {
/* 1269 */     int offset = 16;
/*      */     
/* 1271 */     int xStart = (chunkPosX << 4) + 7 - offset / 2;
/* 1272 */     int zStart = (chunkPosZ << 4) + 7 - offset / 2;
/*      */     
/* 1274 */     int i1 = chunkGen.getHeight(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
/* 1275 */     int j1 = chunkGen.getHeight(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
/* 1276 */     int k1 = chunkGen.getHeight(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
/* 1277 */     int l1 = chunkGen.getHeight(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
/* 1278 */     int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
/* 1279 */     int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));
/*      */     
/* 1281 */     return (Math.abs(maxHeight - minHeight) <= difference);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static <T> T sendGET(String sendUrl, Class resultType) throws IOException {
/* 1287 */     T result = null;
/*      */ 
/*      */     
/* 1290 */     URL url = new URL("https://pixelatedw.xyz/api/v1" + sendUrl);
/*      */ 
/*      */     
/* 1293 */     HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/*      */ 
/*      */     
/* 1296 */     connection.setRequestMethod("GET");
/* 1297 */     connection.setRequestProperty("User-Agent", "mineminenomi/0.8.1-" + MCPVersion.getMCVersion() + "-" + APIConfig.BUILD_MODE.toString().toLowerCase());
/*      */     
/* 1299 */     int responseCode = connection.getResponseCode();
/* 1300 */     if (responseCode == 200 || responseCode == 202) {
/*      */       
/* 1302 */       BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/* 1303 */       StringBuilder sb = new StringBuilder();
/*      */       
/*      */       String line;
/* 1306 */       while ((line = in.readLine()) != null)
/*      */       {
/* 1308 */         sb.append(line + "\n");
/*      */       }
/*      */       
/* 1311 */       in.close();
/*      */       
/* 1313 */       result = (T)(new Gson()).fromJson(sb.toString(), resultType);
/*      */     }
/*      */     else {
/*      */       
/* 1317 */       System.out.println("==============ERROR WHILE RETRIEVING SERVER DATA==============");
/* 1318 */       System.out.println("Response Code: " + responseCode + " - " + connection.getResponseMessage());
/* 1319 */       System.out.println("=============================================================");
/*      */     } 
/*      */     
/* 1322 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void removeAllModifiers(IAttributeInstance attr) {
/* 1327 */     Collection<AttributeModifier> collection = attr.func_225505_c_();
/* 1328 */     if (collection != null)
/*      */     {
/* 1330 */       for (AttributeModifier attributemodifier : Lists.newArrayList(collection))
/*      */       {
/* 1332 */         attr.removeModifier(attributemodifier);
/*      */       }
/*      */     }
/*      */   }
/*      */
 }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\WyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */