package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.challenges.ChallengeFailMessageThread;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ChallengeArenaTileEntity
  extends TileEntity
  implements ITickableTileEntity
{
  private int protectedSize = 10;
  private Challenge challenge;
  private String arenaName = "";
  private List<UUID> players = new ArrayList<>();
  private List<LivingEntity> targets = new ArrayList<>();
  private int timer = 36000;
  
  private boolean isComplete = false;
  
  public ChallengeArenaTileEntity() {
    super(ModTileEntities.CHALLENGE_ARENA);
  }

  
  public void addPlayer(PlayerEntity player) {
    if (!this.players.contains(player.getUniqueID())) {
      this.players.add(player.getUniqueID());
    }
  }
  
  public void removePlayer(UUID uuid, @Nullable ServerPlayerEntity player) {
    if (player != null) {
      
      ServerWorld overworld = player.getServer().getWorld(DimensionType.OVERWORLD);
      player.teleport(overworld, overworld.getSpawnPoint().getX(), overworld.getSpawnPoint().getY(), overworld.getSpawnPoint().getZ(), 270.0F, 0.0F);
      if (!this.isComplete) {
        (new ChallengeFailMessageThread(player)).start();
      }
    } 
    this.players.remove(uuid);
    
    (this.challenge.getArenaData()).isInUse = false;
    (this.challenge.getArenaData()).owner = null;
  }

  
  private void removeTargets() {
    Iterator<LivingEntity> targets = this.targets.iterator();
    if (targets.hasNext()) {
      
      LivingEntity entity = targets.next();
      entity.remove();
      this.targets.remove(entity);
    } 
  }

  
  private void removePlayers() {
    Iterator<UUID> playersIterator = this.players.iterator();
    if (playersIterator.hasNext()) {
      
      UUID uuid = playersIterator.next();
      PlayerEntity player = this.world.getPlayerByUuid(uuid);
      removePlayer(uuid, (ServerPlayerEntity)player);
    } 
  }

  
  private void removeExtra() {
    List<Entity> extras = WyHelper.getEntitiesNear(this.pos, this.world, 100.0D, new Class[] { Entity.class });
    for (Entity extra : extras) {
      
      if (extra instanceof ServerPlayerEntity) {
        
        ServerWorld overworld = extra.getServer().getWorld(DimensionType.OVERWORLD);
        ((ServerPlayerEntity)extra).teleport(overworld, overworld.getSpawnPoint().getX(), overworld.getSpawnPoint().getY(), overworld.getSpawnPoint().getZ(), 270.0F, 0.0F);
        
        continue;
      } 
      extra.remove();
    } 
  }


  
  public void addTarget(LivingEntity target) {
    this.targets.add(target);
  }

  
  public void setupArena(int size) {
    this.protectedSize = size;
    removeExtra();
  }

  
  public void setChallenge(Challenge challenge) {
    this.challenge = challenge;
    this.arenaName = challenge.getArenaName();
  }

  
  public int getSize() {
    return this.protectedSize;
  }


  
  public void tick() {
    if (!this.world.isRemote) {
      
      if (this.challenge == null || !this.challenge.hasStarted()) {
        return;
      }
      if (this.timer <= 0) {
        
        removePlayers();
        removeTargets();
        removeExtra();
        this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
      } 
      
      if (this.players.isEmpty()) {
        
        removeTargets();
        removeExtra();
        this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
        
        return;
      } 
      if (!this.players.isEmpty()) {
        
        Iterator<UUID> playersIterator = this.players.iterator();
        if (playersIterator.hasNext()) {
          
          UUID uuid = playersIterator.next();
          PlayerEntity player = this.world.getPlayerByUuid(uuid);
          if (player == null || !player.isAlive() || player.getHealth() <= 0.0F) {
            
            removePlayer(uuid, (ServerPlayerEntity)player);
            
            return;
          } 
          double distance = player.getDistanceSq(new Vec3d(this.pos.getX(), this.pos.getY(), this.pos.getZ()));
          if (distance > this.protectedSize) {
            removePlayer(uuid, (ServerPlayerEntity)player);
          }
          if (this.targets.isEmpty()) {
            
            removeExtra();
            if (this.challenge != null) {
              
              this.challenge.complete(player);
              this.isComplete = true;
              removePlayer(uuid, (ServerPlayerEntity)player);
            } 
          } 
        } 
        
        if (this.players.isEmpty()) {
          return;
        }
        Iterator<LivingEntity> targets = this.targets.iterator();
        if (targets.hasNext()) {
          
          LivingEntity target = targets.next();
          if (target == null || !target.isAlive() || target.getHealth() <= 0.0F) {
            this.targets.remove(target);
          }
        } 
      } 
      this.timer--;
    } 
  }


  
  public void read(CompoundNBT nbtTag) {
    super.read(nbtTag);
    
    this.protectedSize = nbtTag.getInt("Size");
    this.timer = nbtTag.getInt("Timer");
    this.arenaName = nbtTag.getString("ArenaName");
  }


  
  public CompoundNBT write(CompoundNBT nbtTag) {
    super.write(nbtTag);
    
    nbtTag.putInt("Size", this.protectedSize);
    nbtTag.putInt("Timer", this.timer);
    nbtTag.putString("ArenaName", this.arenaName);
    
    return nbtTag;
  }


  
  public CompoundNBT getUpdateTag() {
    return write(new CompoundNBT());
  }


  
  @Nullable
  public SUpdateTileEntityPacket getUpdatePacket() {
    CompoundNBT nbttagcompound = new CompoundNBT();
    write(nbttagcompound);
    return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
  }
}


