package xyz.pixelatedw.mineminenomi.wypi;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.IntegrityProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ChunkHolder;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.versions.mcp.MCPVersion;
import xyz.pixelatedw.mineminenomi.mixins.ITemplateMixin;









public class WyHelper
{
  @Deprecated
  public static boolean isNullOrEmpty(String str) {
    if (str != null && !str.isEmpty() && !str.equalsIgnoreCase("n/a"))
      return false; 
    return true;
  }

  
  public static String formatBytes(long bytes) {
    int unit = 1024;
    if (bytes < unit)
      return bytes + " B"; 
    int exp = (int)(Math.log(bytes) / Math.log(unit));
    String pre = "KMGTPE".charAt(exp - 1) + "";
    return String.format("%.1f %sB", new Object[] { Double.valueOf(bytes / Math.pow(unit, exp)), pre });
  }

  
  public static String upperCaseFirst(String text) {
    return Character.toUpperCase(text.charAt(0)) + text.substring(1) + " ";
  }

  
  public static String getResourceName(String text) {
    return text
      .replaceAll("[ \\t]+$", "")
      .replaceAll("\\(", "")
      .replaceAll("\\)", "")
      .replaceAll("\\s+", "_")
      .replaceAll("[\\'\\:\\-\\,\\#]", "")
      .replaceAll("\\&", "and").toLowerCase();
  }

  
  public static String escapeJSON(String raw) {
    String escaped = raw;
    escaped = escaped.replace("\\", "\\\\");
    escaped = escaped.replace("\"", "\\\"");
    escaped = escaped.replace("\b", "\\b");
    escaped = escaped.replace("\f", "\\f");
    escaped = escaped.replace("\n", "\\n");
    escaped = escaped.replace("\r", "\\r");
    escaped = escaped.replace("\t", "\\t");
    return escaped;
  }







  
  public static Color hslToColor(float h, float s, float l) {
    if (s < 0.0F || s > 100.0F) {
      
      String message = "Color parameter outside of expected range - Saturation";
      throw new IllegalArgumentException(message);
    } 
    
    if (l < 0.0F || l > 100.0F) {
      
      String message = "Color parameter outside of expected range - Luminance";
      throw new IllegalArgumentException(message);
    } 
    
    h %= 360.0F;
    h /= 360.0F;
    s /= 100.0F;
    l /= 100.0F;
    
    float q = 0.0F;
    
    if (l < 0.5D) {
      q = l * (1.0F + s);
    } else {
      q = l + s - s * l;
    } 
    float p = 2.0F * l - q;
    
    float r = Math.max(0.0F, hueToRGB(p, q, h + 0.33333334F));
    float g = Math.max(0.0F, hueToRGB(p, q, h));
    float b = Math.max(0.0F, hueToRGB(p, q, h - 0.33333334F));
    
    r = Math.min(r, 1.0F);
    g = Math.min(g, 1.0F);
    b = Math.min(b, 1.0F);
    
    return new Color(r, g, b);
  }

  
  private static float hueToRGB(float p, float q, float h) {
    if (h < 0.0F) {
      h++;
    }
    if (h > 1.0F) {
      h--;
    }
    if (6.0F * h < 1.0F) {
      return p + (q - p) * 6.0F * h;
    }
    if (2.0F * h < 1.0F) {
      return q;
    }
    if (3.0F * h < 2.0F) {
      return p + (q - p) * 6.0F * (0.6666667F - h);
    }
    return p;
  }

  
  public static String rgbToHex(int red, int green, int blue) {
    return String.format("#%02X%02X%02X", new Object[] { Integer.valueOf(red), Integer.valueOf(green), Integer.valueOf(blue) });
  }

  
  public static Color hexToRGB(String hexColor) {
    if (hexColor.startsWith("#")) {
      hexColor = hexColor.substring(1);
    }
    if (hexColor.length() == 8) {
      
      int r = Integer.parseInt(hexColor.substring(0, 2), 16);
      int g = Integer.parseInt(hexColor.substring(2, 4), 16);
      int b = Integer.parseInt(hexColor.substring(4, 6), 16);
      int a = Integer.parseInt(hexColor.substring(6, 8), 16);
      return new Color(r, g, b, a);
    } 
    
    return Color.decode("#" + hexColor);
  }

  
  public static Color getComplementaryColor(Color color) {
    return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
  }

  
  public static float colorTolerance(float tolerance) {
    return colorTolerance(tolerance, false);
  }

  
  public static float colorTolerance(float tolerance, boolean hasDisturbance) {
    float color = (new Random()).nextFloat();
    
    if (color <= tolerance || (!hasDisturbance && color >= tolerance + 0.3D)) {
      return tolerance;
    }
    return color;
  }





  
  public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ) {
    spawnParticles(data, world, posX, posY, posZ, 0.0F, 0.0F, 0.0F);
  }

  
  public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ, float motionX, float motionY, float motionZ) {
    SSpawnParticlePacket sSpawnParticlePacket = new SSpawnParticlePacket(data, true, (float)posX, (float)posY, (float)posZ, motionX, motionY, motionZ, 0.0F, 1);
    
    for (int j = 0; j < world.getPlayers().size(); j++) {
      
      ServerPlayerEntity player = world.getPlayers().get(j);
      BlockPos blockpos = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());
      if (blockpos.withinDistance((IPosition)new Vec3d(posX, posY, posZ), 512.0D))
      {
        player.connection.sendPacket((IPacket)sSpawnParticlePacket);
      }
    } 
  }

  
  public static Vec3d propulsion(LivingEntity entity, double extraVelX, double extraVelZ) {
    return propulsion(entity, extraVelX, 0.0D, extraVelZ);
  }

  
  public static Vec3d propulsion(LivingEntity entity, double extraVelX, double extraVelY, double extraVelZ) {
    return entity.getLook(1.0F).mul(extraVelX, extraVelY, extraVelZ);
  }

  
  public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius) {
    return getEntitiesNear(pos, world, radius, (Class<? extends T>[])new Class[] { LivingEntity.class });
  }

  
  public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Class<? extends T>... classEntities) {
    return getEntitiesNear(pos, world, radius, null, classEntities);
  }

  
  public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Predicate<Entity> predicate, Class<? extends T>... classEntities) {
    if (predicate != null) {
      predicate = predicate.and(EntityPredicates.NOT_SPECTATING);
    } else {
      predicate = EntityPredicates.NOT_SPECTATING;
    } 
    AxisAlignedBB aabb = (new AxisAlignedBB(pos)).grow(radius, radius, radius);
    List<T> list = new ArrayList<>();
    for (Class<? extends T> clzz : classEntities)
    {
      list.addAll(world.getEntitiesWithinAABB(clzz, aabb, predicate));
    }
    return list;
  }

  
  public static <T extends PlayerEntity> List<T> getNearestPlayers(IWorld world, double x, double y, double z, double distance) {
    List<T> list = new ArrayList<>();
    for (PlayerEntity target : world.getPlayers()) {
      
      if (EntityPredicates.NOT_SPECTATING.test(target) && EntityPredicates.IS_LIVING_ALIVE.test(target)) {
        
        double d0 = target.getDistanceSq(x, y, z);
        if (distance < 0.0D || d0 < distance * distance)
        {
          list.add((T)target);
        }
      } 
    } 
    
    return list;
  }


  
  public static <T extends Entity> List<T> getEntitiesNearSphere(BlockPos pos, World world, double radius, @Nullable Predicate<Entity> predicate, Class<? extends T>... classEntities) {
    radius *= 2.0D;
    if (predicate != null) {
      predicate = predicate.and(EntityPredicates.NOT_SPECTATING);
    } else {
      predicate = EntityPredicates.NOT_SPECTATING;
    } 
    int x = pos.getX();
    int y = pos.getY();
    int z = pos.getZ();
    
    AxisAlignedBB aabb = (new AxisAlignedBB(x, y, z, x, y, z)).grow(radius, radius, radius);
    List<T> list = new ArrayList<>();
    for (Class<? extends T> clzz : classEntities)
    {
      list.addAll(world.getEntitiesWithinAABB(clzz, aabb, predicate));
    }
    
    for (int i = 0; i < list.size(); i++) {
      
      if (((Entity)list.get(i)).getDistanceSq(x, y, z) >= radius) {
        
        list.remove(i);
        
        break;
      } 
    } 
    return list;
  }
  
  public static RayTraceResult rayTraceBlocksAndEntities(Entity entity) {
    return rayTraceBlocksAndEntities(entity, 1024.0D, 0.4F);
  }
  
  public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance) {
    return rayTraceBlocksAndEntities(entity, distance, 0.2F);
  }
  public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance, float entityBoxRange) {
    EntityRayTraceResult entityRayTraceResult = null;
    Vec3d lookVec = entity.getLookVec();
    Vec3d startVec = entity.getPositionVec().add(0.0D, entity.getEyeHeight(), 0.0D);
    Vec3d endVec = startVec.add(entity.getLookVec().scale(distance));
    BlockRayTraceResult blockRayTraceResult = entity.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity));
    RayTraceResult entityResult = null;
    
    for (int i = 0; i < distance * 2.0D; i++) {
      
      if (entityResult != null) {
        break;
      }
      float scale = i / 2.0F;
      Vec3d pos = startVec.add(lookVec.scale(scale));
      
      Vec3d min = pos.add(entityBoxRange, entityBoxRange, entityBoxRange);
      Vec3d max = pos.add(-entityBoxRange, -entityBoxRange, -entityBoxRange);
      for (Entity e : entity.world.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(min.x, min.y, min.z, max.x, max.y, max.z))) {
        if (e instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity) {
          continue;
        }
        entityRayTraceResult = new EntityRayTraceResult(e, pos);
      } 
    } 

    
    if (entityRayTraceResult != null && entityRayTraceResult.getHitVec().distanceTo(startVec) <= blockRayTraceResult.getHitVec().distanceTo(startVec)) {
      return (RayTraceResult)entityRayTraceResult;
    }
    return (RayTraceResult)blockRayTraceResult;
  }



  
  public static BlockPos rayTraceBlockSafe(PlayerEntity player, float range) {
    World world = player.world;
    Vec3d startVec = player.getPositionVec().add(0.0D, player.getEyeHeight(), 0.0D);
    Vec3d endVec = startVec.add(player.getLookVec().scale(range));
    BlockRayTraceResult result = world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)player));
    BlockPos dashPos = result.getFace().equals(Direction.DOWN) ? result.getPos().down(2) : result.getPos().offset(result.getFace());
    
    if (dashPos.getY() > player.world.getMaxHeight()) {
      dashPos = dashPos.add(0, player.world.getMaxHeight() - dashPos.getY(), 0);
    }
    return dashPos;
  }

  
  public static boolean isPosClearForPlayer(World world, BlockPos pos) {
    return ((world.isAirBlock(pos) || world.getBlockState(pos).getCollisionShape((IBlockReader)world, pos).isEmpty()) && (world
      .isAirBlock(pos.up()) || world.getBlockState(pos.up()).getCollisionShape((IBlockReader)world, pos.up()).isEmpty()));
  }

  
  public static BlockPos getClearPositionForPlayer(PlayerEntity player, BlockPos pos) {
    boolean posIsFree = isPosClearForPlayer(player.world, pos);
    int i = 0;
    
    while (!posIsFree) {
      
      Direction dir = Direction.values()[i];
      pos = pos.add(
          -dir.getXOffset() * 2, 
          -dir.getYOffset() * 2, 
          -dir.getZOffset() * 2);
      posIsFree = isPosClearForPlayer(player.world, pos);
      if (posIsFree)
        break; 
      if (i < (Direction.values()).length - 1) {
        i++;
      }
    } 

    
    return posIsFree ? pos : null;
  }

  
  public static BlockRayTraceResult rayTraceBlocks(Entity source, double distance) {
    Vec3d startVec = source.getPositionVec().add(0.0D, source.getEyeHeight(), 0.0D);
    Vec3d endVec = startVec.add(source.getLookVec().scale(distance));
    return source.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, source));
  }

  
  public static BlockRayTraceResult rayTraceBlocks(World world, Vec3d startVec, Vec3d endVec) {
    return world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null));
  }

  
  public static EntityRayTraceResult rayTraceEntities(Entity source, double distance) {
    Vec3d startVec = source.getPositionVec().add(0.0D, source.getEyeHeight(), 0.0D);
    Vec3d endVec = startVec.add(source.getLookVec().scale(distance));
    AxisAlignedBB boundingBox = source.getBoundingBox().grow(distance);
    
    for (Entity entity : source.world.getEntitiesInAABBexcluding(source, boundingBox, entity -> (entity != source))) {



      
      AxisAlignedBB entityBB = entity.getBoundingBox().grow(1.0D);
      Optional<Vec3d> optional = entityBB.rayTrace(startVec, endVec);
      
      if (optional.isPresent()) {
        
        Vec3d targetVec = optional.get();
        double distFromSource = MathHelper.sqrt(startVec.squareDistanceTo(targetVec));
        
        if (distFromSource < distance) {
          
          List<Entity> targets = getEntitiesNear(new BlockPos(targetVec), source.world, 1.25D);
          targets.remove(source);
          Optional<Entity> target = targets.stream().findFirst();
          
          if (target.isPresent())
          {
            return new EntityRayTraceResult(target.get(), endVec);
          }
        } 
      } 
    } 
    
    return new EntityRayTraceResult(null, endVec);
  }

  
  public static boolean isBlockNearby(LivingEntity player, int radius, Block... blocks) {
    for (Block b : blocks) {
      
      for (int x = -radius; x <= radius; x++) {
        for (int y = -radius; y <= radius; y++) {
          for (int z = -radius; z <= radius; z++) {
            
            if (player.world.getBlockState(new BlockPos(player.getPosX() + x, player.getPosY() + y, player.getPosZ() + z)).getBlock() == b)
            {
              return true; } 
          } 
        } 
      } 
    } 
    return false;
  }

  
  public static List<BlockPos> getNearbyBlocks(LivingEntity player, int radius) {
    return getNearbyBlocks(player.getPosition(), (IWorld)player.world, radius);
  }

  
  public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius) {
    return getNearbyBlocks(pos, world, radius, (List<Block>)ImmutableList.of(Blocks.AIR));
  }

  
  public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius, List<Block> bannedBlocks) {
    return getNearbyBlocks(pos, world, radius, null, (List<Block>)ImmutableList.of(Blocks.AIR));
  }

  
  public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius, @Nullable Predicate<BlockPos> predicate, List<Block> bannedBlocks) {
    if (predicate == null) {
      predicate = (blockPos -> true);
    }
    List<BlockPos> blockLocations = new ArrayList<>();
    for (int x = -radius; x <= radius; x++) {
      
      for (int y = -radius; y <= radius; y++) {
        
        for (int z = -radius; z <= radius; z++) {
          
          BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
          if (predicate.test(newPos)) {

            
            Block block = world.getBlockState(newPos).getBlock();
            if (!bannedBlocks.contains(block))
              blockLocations.add(newPos); 
          } 
        } 
      } 
    } 
    return blockLocations;
  }

  
  public static List<BlockPos> getNearbyTileEntities(LivingEntity player, int radius) {
    return getNearbyTileEntities(player.getPosition(), player.world, radius);
  }

  
  public static List<BlockPos> getNearbyTileEntities(BlockPos pos, World world, int radius) {
    List<BlockPos> blockLocations = new ArrayList<>();
    for (int x = -radius; x <= radius; x++) {
      
      for (int y = -radius; y <= radius; y++) {
        
        for (int z = -radius; z <= radius; z++) {
          
          BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
          if (world.getBlockState(newPos).getBlock() != Blocks.AIR && world.getTileEntity(newPos) != null)
          {
            blockLocations.add(newPos);
          }
        } 
      } 
    } 
    
    return blockLocations;
  }

  
  public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius) {
    return findOnGroundSpawnLocation(world, type, spawnLocation, radius, 0);
  }

  
  @Nullable
  public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius, int offset) {
    BlockPos blockpos = null;
    for (int i = 0; i < 10; i++) {
      
      int x = (int)randomWithRange(spawnLocation.getX() - offset - radius, spawnLocation.getX() + offset + radius);
      int z = (int)randomWithRange(spawnLocation.getZ() - offset - radius, spawnLocation.getZ() + offset + radius);
      int y = world.getHeight(Heightmap.Type.WORLD_SURFACE, x, z);
      BlockPos blockpos1 = new BlockPos(x, y, z);
      if (WorldEntitySpawner.canCreatureTypeSpawnAtLocation(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, (IWorldReader)world, blockpos1, type)) {
        
        blockpos = blockpos1;
        
        break;
      } 
    } 
    return blockpos;
  }

  
  public static String getTextureName(String texture) {
    for (String s : texture.split("/")) {
      
      if (s.contains(".png"))
      {
        return s.replace(".png", "");
      }
    } 
    return null;
  }

  
  public static boolean hasInventoryFull(PlayerEntity player) {
    for (ItemStack stack : player.inventory.mainInventory) {
      
      if (stack.isEmpty()) {
        return false;
      }
    } 
    return true;
  }












  
  public static boolean setBlockStateInChunk(World world, BlockPos pos, BlockState newState, int flags) {
    if (World.isOutsideBuildHeight(pos))
      return false; 
    if (!world.isRemote && world.getWorldInfo().getGenerator() == WorldType.DEBUG_ALL_BLOCK_STATES) {
      return false;
    }
    
    Chunk chunk = world.getChunkAt(pos);
    pos = pos.toImmutable();
    
    BlockState blockstate = chunk.setBlockState(pos, newState, ((flags & 0x40) != 0));
    if (blockstate != null) {
      
      world.getChunkProvider().getLightManager().checkBlock(pos);
      
      if ((flags & 0x2) != 0 && (!world.isRemote || (flags & 0x4) == 0) && (world.isRemote || chunk == null || (chunk.getLocationType() != null && chunk.getLocationType().isAtLeast(ChunkHolder.LocationType.TICKING)))) {
        
        world.markAndNotifyBlock(pos, chunk, blockstate, newState, flags);
        world.notifyBlockUpdate(pos, blockstate, newState, flags);
      } 
    } 
    return true;
  }


  
  public static BlockState swapBlockData(IWorld world, BlockPos pos, BlockState newState) {
    IChunk chunk = world.getChunk(pos);
    ChunkSection cs = chunk.getSections()[pos.getY() >> 4];
    if (cs == Chunk.EMPTY_SECTION) {
      
      cs = new ChunkSection(pos.getY() >> 4 << 4);
      chunk.getSections()[pos.getY() >> 4] = cs;
    } 
    BlockState state = (BlockState)cs.getData().swap(pos.getX() & 0xF, pos.getY() & 0xF, pos.getZ() & 0xF, newState);
    return state;
  }





  
  public static boolean afterDate(String date) {
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    Calendar target = null;
    
    try {
      target = Calendar.getInstance();
      target.setTime(df.parse(date));
    }
    catch (ParseException e) {
      
      e.printStackTrace();
      return false;
    } 
    
    Calendar now = Calendar.getInstance();
    return now.after(target);
  }

  
  public static long getDaysSince(Date date) {
    Date now = new Date();
    
    long diffInMillies = Math.abs(now.getTime() - date.getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    
    return diff;
  }





  
  public static double percentage(double percent, double value) {
    return percent / 100.0D * value;
  }

  
  public static double randomWithRange(int min, int max) {
    return ((new Random()).nextInt(max + 1 - min) + min);
  }

  
  public static double randomWithRange(Random rand, int min, int max) {
    return (rand.nextInt(max + 1 - min) + min);
  }

  
  public static double randomDouble(Random rand) {
    return rand.nextDouble() * 2.0D - 1.0D;
  }

  
  public static double randomDouble() {
    return (new Random()).nextDouble() * 2.0D - 1.0D;
  }

  
  public static int round(int value) {
    String valueString = "" + value;
    
    if (valueString.length() < 1) {
      return value;
    }
    return round(value, valueString.length() - 1);
  }

  
  public static int round(int value, int nth) {
    String valueString = "" + value;
    
    if (valueString.length() < 1 || nth < 0) {
      return value;
    }
    if (nth == 0) {
      nth = 1;
    }
    int n = (int)Math.pow(10.0D, (nth - 1));
    int r = 5 * n / 10;
    
    return (value + r) / n * n;
  }

  
  public static long clamp(long num, long min, long max) {
    return (num < min) ? min : Math.min(num, max);
  }





  
  public static void drawColourOnScreen(int colour, int alpha, double posX, double posY, double width, double height, double zLevel) {
    int r = colour >> 16 & 0xFF;
    int g = colour >> 8 & 0xFF;
    int b = colour & 0xFF;
    drawColourOnScreen(r, g, b, alpha, posX, posY, width, height, zLevel);
  }

  
  public static void drawColourOnScreen(int red, int green, int blue, int alpha, double posX, double posY, double width, double height, double zLevel) {
    if (width <= 0.0D || height <= 0.0D)
      return; 
    RenderSystem.enableBlend();
    RenderSystem.disableTexture();
    BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(posX, posY + height, zLevel).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(posX + width, posY + height, zLevel).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(posX + width, posY, zLevel).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(posX, posY, zLevel).color(red, green, blue, alpha).endVertex();
    Tessellator.getInstance().draw();
    RenderSystem.enableTexture();
    RenderSystem.disableBlend();
  }

  
  public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v) {
    Minecraft.getInstance().getTextureManager().bindTexture(rs);
    BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
    bufferbuilder.pos(x, (y + v), 1.0D).tex(0.0F, 1.0F).endVertex();
    bufferbuilder.pos((x + u), (y + v), 1.0D).tex(1.0F, 1.0F).endVertex();
    bufferbuilder.pos((x + u), y, 1.0D).tex(1.0F, 0.0F).endVertex();
    bufferbuilder.pos(x, y, 1.0D).tex(0.0F, 0.0F).endVertex();
    Tessellator.getInstance().draw();
  }

  
  public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v, String hexColor) {
    Color color = hexToRGB(hexColor);
    drawIcon(rs, x, y, u, v, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  
  public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v, int intColor) {
    String hex = String.format("#%06X", new Object[] { Integer.valueOf(0xFFFFFF & intColor) });
    Color color = hexToRGB(hex);
    drawIcon(rs, x, y, u, v, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  
  public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v, int red, int green, int blue, int alpha) {
    Minecraft.getInstance().getTextureManager().bindTexture(rs);
    BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
    bufferbuilder.pos(x, (y + v), 1.0D).color(red, green, blue, alpha).tex(0.0F, 1.0F).endVertex();
    bufferbuilder.pos((x + u), (y + v), 1.0D).color(red, green, blue, alpha).tex(1.0F, 1.0F).endVertex();
    bufferbuilder.pos((x + u), y, 1.0D).color(red, green, blue, alpha).tex(1.0F, 0.0F).endVertex();
    bufferbuilder.pos(x, y, 1.0D).color(red, green, blue, alpha).tex(0.0F, 0.0F).endVertex();
    Tessellator.getInstance().draw();
  }





  
  @Deprecated
  public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, LivingEntity entity) {
    InventoryScreen.drawEntityOnScreen(posX, posY, scale, mouseX, mouseY, entity);
  }

  
  public static void drawStringWithBorder(FontRenderer font, String text, int posX, int posY, int color) {
    String unformattedText = escapeTextFormattingChars(text);
    font.drawStringWithShadow(unformattedText, posX, posY - 0.7F, 1);
    font.drawStringWithShadow(unformattedText, posX, posY + 0.7F, 1);
    font.drawStringWithShadow(unformattedText, posX + 0.7F, posY, 1);
    font.drawStringWithShadow(unformattedText, posX - 0.7F, posY, 1);
    font.drawString(text, posX, posY, color);
    RenderSystem.enableAlphaTest();
    RenderSystem.enableBlend();
  }

  
  public static String escapeTextFormattingChars(String text) {
    return text.replaceAll("§[0-9a-f]", "");
  }

  
  public static List<String> splitString(FontRenderer font, String text, int posX, int wrapWidth) {
    while (text != null && text.endsWith("\n"))
    {
      text = text.substring(0, text.length() - 1);
    }
    
    List<String> newText = new ArrayList<>();
    for (String s : font.listFormattedStringToWidth(text, wrapWidth)) {
      
      if (font.getBidiFlag()) {
        
        int i = font.getStringWidth(font.bidiReorder(s));
        posX += wrapWidth - i;
      } 
      
      newText.add(s);
    } 
    return newText;
  }

  
  public static float handleRotationFloat(LivingEntity entity, float partialTicks) {
    return entity.ticksExisted + partialTicks;
  }

  
  public static void rotateCorpse(MatrixStack matrixStack, LivingEntity entityLiving, float ageInTicks, float headYawOffset, float partialTicks) {
    matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F + headYawOffset));
    
    if (entityLiving.deathTime > 0) {
      
      float animTime = (entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
      animTime = MathHelper.sqrt(animTime);
      if (animTime > 1.0F) {
        animTime = 1.0F;
      }
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(animTime * 90.0F));
    } 
  }






  
  @Deprecated
  public static float interpolateRotation(float lowerLimit, float upperLimit, float range) {
    float f3;
    for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F);



    
    while (f3 >= 180.0F)
    {
      f3 -= 360.0F;
    }
    
    return lowerLimit + range * f3;
  }





  
  public static void generateJSONLangs() {
    Map<String, String> sorted = sortAlphabetically(WyRegistry.getLangMap());
    Set<Map.Entry<String, String>> set = sorted.entrySet();
    Iterator<Map.Entry<String, String>> iter = set.iterator();
    
    Map.Entry<String, String> prevEntry = null;
    
    File langFolder = new File(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.projectId + "/lang/");
    langFolder.mkdirs();
    
    if (langFolder.exists()) {
      try {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(APIConfig.getResourceFolderPath() + "/assets/" + APIConfig.projectId + "/lang/en_us.json"), "UTF-8"));
        
        try { writer.write("{\n");
          while (iter.hasNext()) {
            
            Map.Entry<String, String> entry = iter.next();
            
            if (prevEntry != null)
            {
              if (!((String)prevEntry.getKey()).substring(0, 2).equals(((String)entry.getKey()).substring(0, 2)))
              {
                writer.write("\n");
              }
            }
            
            String value = escapeJSON(entry.getValue());
            if (iter.hasNext()) {
              writer.write("\t\"" + (String)entry.getKey() + "\": \"" + value + "\",\n");
            } else {
              writer.write("\t\"" + (String)entry.getKey() + "\": \"" + value + "\"\n");
            } 
            prevEntry = entry;
          } 
          writer.write("}\n");
          writer.close();
          writer.close(); } catch (Throwable throwable) { try { writer.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; } 
      } catch (Exception e) {
        
        e.printStackTrace();
      } 
    }
  }


  
  @Deprecated
  public static <T> List<T> shuffle(List<T> ar) {
    Random rnd = new Random();
    
    for (int i = ar.size() - 1; i > 0; i--) {
      
      int index = rnd.nextInt(i + 1);
      
      T a = ar.get(index);
      ar.set(index, ar.get(i));
      ar.set(i, a);
    } 
    
    return ar;
  }


  
  @Deprecated
  public static <T> List<T> shuffle(List<T> ar, long seed) {
    Random rnd = new Random(seed);
    
    for (int i = ar.size() - 1; i > 0; i--) {
      
      int index = rnd.nextInt(i + 1);
      
      T a = ar.get(index);
      ar.set(index, ar.get(i));
      ar.set(i, a);
    } 
    
    return ar;
  }

  
  public static <K extends Comparable, V extends Comparable> Map<K, V> sortAlphabetically(Map<K, V> map) {
    List<Map.Entry<K, V>> entries = new LinkedList<>(map.entrySet());
    
    Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
        {
          
          public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
          {
            return ((Comparable)o1.getKey()).compareTo(o2.getKey());
          }
        });
    
    Map<K, V> sortedMap = new LinkedHashMap<>();
    
    for (Map.Entry<K, V> entry : entries)
    {
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    
    return sortedMap;
  }


  
  public static BlockPos[][] splitArray(BlockPos[] arrayToSplit, int chunkSize) {
    if (chunkSize <= 0)
    {
      return null;
    }

    
    int rest = arrayToSplit.length % chunkSize;
    
    int chunks = arrayToSplit.length / chunkSize + ((rest > 0) ? 1 : 0);
    
    BlockPos[][] arrays = new BlockPos[chunks][];



    
    for (int i = 0; i < ((rest > 0) ? (chunks - 1) : chunks); i++)
    {
      
      arrays[i] = Arrays.<BlockPos>copyOfRange(arrayToSplit, i * chunkSize, i * chunkSize + chunkSize);
    }
    if (rest > 0)
    {
      
      arrays[chunks - 1] = Arrays.<BlockPos>copyOfRange(arrayToSplit, (chunks - 1) * chunkSize, (chunks - 1) * chunkSize + rest);
    }
    return arrays;
  }

  
  public static byte[] serialize(Object obj) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(out);
    os.writeObject(obj);
    return out.toByteArray();
  }


  
  public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
    try {
      ByteArrayInputStream in = new ByteArrayInputStream(data);
      ObjectInputStream is = new ObjectInputStream(in);
      is.close();
      return is.readObject();
    }
    catch (EOFException eOFException) {

      
      return null;
    } 
  }
  
  public static final int getIndexOfItemStack(Item item, IInventory inven) {
    for (int i = 0; i < inven.getSizeInventory(); i++) {
      
      if (inven.getStackInSlot(i).getItem() == item)
      {
        return i;
      }
    } 
    return -1;
  }

  
  public static boolean saveNBTStructure(ServerWorld world, String name, BlockPos pos, BlockPos size, List<Block> toIgnore) {
    if (!world.isRemote) {
      Template template;
      ServerWorld serverworld = world;
      TemplateManager templatemanager = serverworld.getStructureTemplateManager();
      ResourceLocation res = new ResourceLocation(APIConfig.projectId, name);


      
      try {
        template = templatemanager.getTemplateDefaulted(res);
      }
      catch (ResourceLocationException ex) {
        
        ex.printStackTrace();
        return false;
      } 
      
      toIgnore.add(Blocks.STRUCTURE_VOID);
      toIgnore.add(Blocks.BEDROCK);
      
      takeBlocksFromWorld(template, (World)world, pos, size, toIgnore);
      template.setAuthor("?");
      
      try {
        return templatemanager.writeToFile(res);
      }
      catch (ResourceLocationException var7) {
        
        return false;
      } 
    } 

    
    return false;
  }


  
  public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos) {
    PlacementSettings placement = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk((ChunkPos)null);
    placement.clearProcessors()
      .addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK)
      .addProcessor((StructureProcessor)new IntegrityProcessor(1.0F)).setRandom(new Random(Util.milliTime()));
    
    return loadNBTStructure(world, name, pos, placement);
  }

  
  public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos, PlacementSettings settings) {
    if (!world.isRemote) {
      Template template;
      TemplateManager templatemanager = world.getStructureTemplateManager();
      ResourceLocation res = new ResourceLocation("mineminenomi", name);


      
      try {
        template = templatemanager.getTemplate(res);
      }
      catch (ResourceLocationException ex) {
        
        ex.printStackTrace();
        return false;
      } 
      
      if (template == null)
      {
        return false;
      }

      
      BlockState blockstate = world.getBlockState(pos);
      world.notifyBlockUpdate(pos, blockstate, blockstate, 3);



      
      template.addBlocksToWorldChunk((IWorld)world, pos, settings);
      return true;
    } 

    
    return false;
  }


  
  public static void takeBlocksFromWorld(Template template, World world, BlockPos startPos, BlockPos size, @Nullable List<Block> toIgnore) {
    if (size.getX() >= 1 && size.getY() >= 1 && size.getZ() >= 1) {
      
      BlockPos blockpos = startPos.add((Vec3i)size).add(-1, -1, -1);
      List<Template.BlockInfo> list = Lists.newArrayList();
      List<Template.BlockInfo> list1 = Lists.newArrayList();
      List<Template.BlockInfo> list2 = Lists.newArrayList();
      BlockPos blockpos1 = new BlockPos(Math.min(startPos.getX(), blockpos.getX()), Math.min(startPos.getY(), blockpos.getY()), Math.min(startPos.getZ(), blockpos.getZ()));
      BlockPos blockpos2 = new BlockPos(Math.max(startPos.getX(), blockpos.getX()), Math.max(startPos.getY(), blockpos.getY()), Math.max(startPos.getZ(), blockpos.getZ()));
      ((ITemplateMixin)template).setSize(size);
      
      for (BlockPos blockpos3 : BlockPos.getAllInBoxMutable(blockpos1, blockpos2)) {
        
        BlockPos blockpos4 = blockpos3.subtract((Vec3i)blockpos1);
        BlockState blockstate = world.getBlockState(blockpos3);
        
        if (toIgnore != null && toIgnore.contains(blockstate.getBlock())) {
          
          world.setBlockState(blockpos3, Blocks.AIR.getDefaultState());
          blockstate = world.getBlockState(blockpos3);
        } 


        
        TileEntity tileentity = world.getTileEntity(blockpos3);
        if (tileentity != null) {
          
          CompoundNBT compoundnbt = tileentity.write(new CompoundNBT());
          compoundnbt.remove("x");
          compoundnbt.remove("y");
          compoundnbt.remove("z");
          list1.add(new Template.BlockInfo(blockpos4, blockstate, compoundnbt)); continue;
        } 
        if (!blockstate.isOpaqueCube((IBlockReader)world, blockpos3) && !blockstate.isCollisionShapeOpaque((IBlockReader)world, blockpos3)) {
          
          list2.add(new Template.BlockInfo(blockpos4, blockstate, (CompoundNBT)null));
          
          continue;
        } 
        list.add(new Template.BlockInfo(blockpos4, blockstate, (CompoundNBT)null));
      } 

      
      List<Template.BlockInfo> list3 = Lists.newArrayList();
      list3.addAll(list);
      list3.addAll(list1);
      list3.addAll(list2);
      ((ITemplateMixin)template).getBlocks().clear();
      ((ITemplateMixin)template).getBlocks().add(list3);
      ((ITemplateMixin)template).getEntities().clear();
    } 
  }

  
  public static boolean isSurfaceFlat(ChunkGenerator<?> chunkGen, int chunkPosX, int chunkPosZ, int difference) {
    int offset = 16;
    
    int xStart = (chunkPosX << 4) + 7 - offset / 2;
    int zStart = (chunkPosZ << 4) + 7 - offset / 2;
    
    int i1 = chunkGen.getHeight(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
    int j1 = chunkGen.getHeight(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
    int k1 = chunkGen.getHeight(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
    int l1 = chunkGen.getHeight(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
    int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
    int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));
    
    return (Math.abs(maxHeight - minHeight) <= difference);
  }

  
  @Nullable
  public static <T> T sendGET(String sendUrl, Class resultType) throws IOException {
    T result = null;

    
    URL url = new URL("https://pixelatedw.xyz/api/v1" + sendUrl);

    
    HttpURLConnection connection = (HttpURLConnection)url.openConnection();

    
    connection.setRequestMethod("GET");
    connection.setRequestProperty("User-Agent", "mineminenomi/0.8.1-" + MCPVersion.getMCVersion() + "-" + APIConfig.BUILD_MODE.toString().toLowerCase());
    
    int responseCode = connection.getResponseCode();
    if (responseCode == 200 || responseCode == 202) {
      
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder sb = new StringBuilder();
      
      String line;
      while ((line = in.readLine()) != null)
      {
        sb.append(line + "\n");
      }
      
      in.close();
      
      result = (T)(new Gson()).fromJson(sb.toString(), resultType);
    }
    else {
      
      System.out.println("==============ERROR WHILE RETRIEVING SERVER DATA==============");
      System.out.println("Response Code: " + responseCode + " - " + connection.getResponseMessage());
      System.out.println("=============================================================");
    } 
    
    return result;
  }

  
  public static void removeAllModifiers(IAttributeInstance attr) {
    Collection<AttributeModifier> collection = attr.func_225505_c_();
    if (collection != null)
    {
      for (AttributeModifier attributemodifier : Lists.newArrayList(collection))
      {
        attr.removeModifier(attributemodifier);
      }
    }
  }

 }


