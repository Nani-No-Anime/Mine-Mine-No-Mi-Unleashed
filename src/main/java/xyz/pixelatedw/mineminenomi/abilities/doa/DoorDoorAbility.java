package xyz.pixelatedw.mineminenomi.abilities.doa;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class DoorDoorAbility extends Ability {
  public static final DoorDoorAbility INSTANCE = new DoorDoorAbility();

  
  public DoorDoorAbility() {
    super("Door Door", AbilityHelper.getDevilFruitCategory());
    setDescription("By making a door, the user transports to the other side of any surface");
    setMaxCooldown(5.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BlockRayTraceResult hitBlock = WyHelper.rayTraceBlocks((Entity)player, 16.0D);
    if (Math.sqrt(player.getDistanceSq(hitBlock.getHitVec())) > 2.5D)
    {
      return false;
    }
    
    Vec3d dir = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
    int x = (int)Math.floor(dir.x), z = (int)Math.floor(dir.z);
    if ((dir.x > -0.3D && dir.x < 0.0D) || (dir.x > 0.0D && dir.x < 0.3D))
      x = 0; 
    if ((dir.z > -0.3D && dir.z < 0.0D) || (dir.z > 0.0D && dir.z < 0.3D))
      z = 0; 
    Vec3i iDir = new Vec3i(x, dir.y, z);
    BlockPos pos = player.getPosition().add(iDir);
    boolean firstSolid = false;
    int airBlocks = 0;
    for (int i = 0; i < 40; i++) {
      
      if (player.world.getBlockState(pos) == Blocks.AIR.getDefaultState() && (firstSolid || airBlocks > 1)) {
        
        player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        
        break;
      } 
      dir = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
      if ((dir.x > -0.3D && dir.x < 0.0D) || (dir.x > 0.0D && dir.x < 0.3D))
        x = 0; 
      if ((dir.z > -0.3D && dir.z < 0.0D) || (dir.z > 0.0D && dir.z < 0.3D))
        z = 0; 
      iDir = new Vec3i(x, dir.y, z);
      pos = pos.add(iDir);
      
      if (player.world.getBlockState(pos).isSolid()) {
        firstSolid = true;
      }
      if (player.world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
        airBlocks++;
      }
    } 
    player.world.playSound(null, player.getPosition(), ModSounds.DOA_IN_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }
}


