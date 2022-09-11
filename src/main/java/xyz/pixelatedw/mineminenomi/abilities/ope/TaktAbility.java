package xyz.pixelatedw.mineminenomi.abilities.ope;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.TaktBlockEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class TaktAbility extends ContinuousAbility {
  public static final Ability INSTANCE = (Ability)new TaktAbility();
  
  private List<Entity> grabbedEntities = new ArrayList<>();

  
  public TaktAbility() {
    super("Takt", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setThreshold(15.0D);
    setDescription("The user lifts entities its looking at, preventing them from moving freely");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    this.grabbedEntities.stream().filter(Entity::hasNoGravity).forEach(e -> e.setNoGravity(false));
    this.grabbedEntities.clear();
    return true;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    RoomAbility abl = (RoomAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)RoomAbility.INSTANCE);
    if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
      return false;
    }
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, abl.getROOMSize());
    
    BlockPos blockPos = new BlockPos(mop.getHitVec());
    
    if (mop.getType() == RayTraceResult.Type.BLOCK) {
      blockPos = new BlockPos((Vec3i)((BlockRayTraceResult)mop).getPos());
    } else if (mop.getType() == RayTraceResult.Type.ENTITY) {
      blockPos = new BlockPos((Vec3i)((EntityRayTraceResult)mop).getEntity().getPosition());
    } 
    Predicate<BlockPos> pred = pos -> {
        BlockState state = player.world.getBlockState(pos);
        
        boolean isBlockBanned = RestrictedBlockProtectionRule.INSTANCE.isBanned(state.getBlock());
        return !isBlockBanned;
      };
    Function<BlockPos, TaktBlockEntity> mapper = pos -> {
        BlockState state = player.world.getBlockState(pos);
        
        TaktBlockEntity fallingBlock = new TaktBlockEntity(player.world, pos.getX(), pos.getY(), pos.getZ(), state);
        
        fallingBlock.setMotion(0.0D, 0.0D, 0.0D);
        
        fallingBlock.velocityChanged = true;
        fallingBlock.fallTime = 5;
        fallingBlock.setNoGravity(true);
        fallingBlock.shouldDropItem = false;
        player.world.addEntity((Entity)fallingBlock);
        player.world.removeBlock(pos, true);
        return fallingBlock;
      };
    Objects.requireNonNull(this.grabbedEntities); WyHelper.getNearbyBlocks(blockPos, (IWorld)player.world, 2, pred, (List)ImmutableList.of(Blocks.AIR)).stream().<TaktBlockEntity>map(mapper).forEach((f)->{this.grabbedEntities.add((Entity)f);});


    
    Objects.requireNonNull(this.grabbedEntities); WyHelper.getEntitiesNear(blockPos, player.world, 2.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class ).stream().filter(Entity::isAlive).forEach(this.grabbedEntities::add);
    
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int timer) {
    if (!OpeHelper.hasRoomActive(player, (Ability)this) || this.grabbedEntities.isEmpty()) {
      
      endContinuity(player);
      
      return;
    } 
    this.grabbedEntities.stream().forEach(e -> {
          e.rotationPitch = e.prevRotationPitch;
          e.rotationYaw = e.prevRotationYaw;
          Random rand = new Random(e.hashCode());
          double offsetX = WyHelper.randomWithRange(rand, -2, 2);
          double offsetY = WyHelper.randomWithRange(rand, -2, 2);
          double offsetZ = WyHelper.randomWithRange(rand, -2, 2);
          double distance = 8.0D;
          Vec3d lookVec = player.getLookVec();
          Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance + offsetX, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance + offsetY, player.getPosZ() + lookVec.z * distance + offsetZ);
          if (!player.world.getBlockState(new BlockPos(pos)).isSolid())
            e.setPositionAndUpdate(pos.x, pos.y, pos.z); 
          e.fallDistance = 0.0F;
        });
  }
}


