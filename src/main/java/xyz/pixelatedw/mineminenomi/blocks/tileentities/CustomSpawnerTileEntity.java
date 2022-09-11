package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;

public class CustomSpawnerTileEntity
  extends TileEntity
  implements ITickableTileEntity {
  private EntityType entityToSpawn = EntityType.PIG;
  private int spawnLimit = 5;
  private int playerDistance = 100;
  private Class<? extends Entity> spawnClass = null;
  private ArrayList<UUID> spawnedEntities = new ArrayList<>();
  private ArrayList<LivingEntity> nearbyEntities = new ArrayList<>();

  
  public CustomSpawnerTileEntity() {
    super(ModTileEntities.CUSTOM_SPAWNER);
  }

  
  public CustomSpawnerTileEntity setSpawnerMob(EntityType toSpawn) {
    this.entityToSpawn = toSpawn;
    markDirty();
    return this;
  }

  
  public CustomSpawnerTileEntity setSpawnerLimit(int limit) {
    this.spawnLimit = limit;
    markDirty();
    return this;
  }

  
  public CustomSpawnerTileEntity setPlayerDistance(int distance) {
    this.playerDistance = distance;
    markDirty();
    return this;
  }

  
  private boolean isActivated() {
    BlockPos blockpos = getPos();
    return getWorld().isPlayerWithin(blockpos.getX() + 0.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D, this.playerDistance);
  }


  
  public void tick() {
    if (this.world == null) {
      return;
    }
    if (this.spawnClass == null) {
      this.spawnClass = (Class)this.entityToSpawn.create(this.world).getClass();
    }
    if (!this.world.isRemote && this.world.getGameTime() % 5L == 0L)
    {
      if (isActivated()) {
        
        if (this.world.getBlockState(this.pos.down()).getBlock() == Blocks.AIR) {
          this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
        }
        if (CommonConfig.INSTANCE.getDestroySpawner() && this.spawnedEntities.size() > 0) {
          
          int alive = 0;
          for (UUID spawnUUID : this.spawnedEntities) {
            
            Entity target = ((ServerWorld)this.world).getEntityByUuid(spawnUUID);
            if (target != null && target.isAlive()) {
              alive++;
            }
          } 
          if (alive == 0) {
            this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
          }
        } 
        if (this.entityToSpawn != null)
        {
          if (this.spawnedEntities.size() < this.spawnLimit)
          {
            LivingEntity newSpawn = (LivingEntity)this.entityToSpawn.spawn(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.pos.up(), SpawnReason.STRUCTURE, false, false);
            if (newSpawn != null)
            {
              double r1 = (this.world.rand.nextDouble() - this.world.rand.nextDouble()) * 2.0D + 0.5D;
              double r2 = (this.world.rand.nextDouble() - this.world.rand.nextDouble()) * 2.0D + 0.5D;
              BlockPos newPos = this.pos.add(r1, 0.0D, r2);
              newSpawn.setLocationAndAngles(newPos.getX(), newPos.getY(), newPos.getZ(), 0.0F, 0.0F);
              this.spawnedEntities.add(newSpawn.getUniqueID());
              markDirty();
            }
          
          }
        
        }
      }
      else if (this.spawnedEntities.size() > 0) {
        
        for (UUID spawnUUID : this.spawnedEntities) {
          
          Entity target = ((ServerWorld)this.world).getEntityByUuid(spawnUUID);
          if (target != null && target.isAlive())
            target.remove(); 
        } 
        this.spawnedEntities.clear();
        markDirty();
      } 
    }
  }



  
  public void read(CompoundNBT nbt) {
    super.read(nbt);
    this.spawnLimit = nbt.getInt("spawnLimit");
    this.playerDistance = nbt.getInt("playerDistance");
    if (this.playerDistance <= 0)
      this.playerDistance = 30; 
    this.entityToSpawn = EntityType.byKey(nbt.getString("entityToSpawn")).orElse(EntityType.PIG);
    
    this.spawnClass = (Class)this.entityToSpawn.create(this.world).getClass();
    
    ListNBT spawnedEntities = nbt.getList("spawns", 10);
    for (int i = 0; i < spawnedEntities.size(); i++) {
      
      CompoundNBT nbtEntity = spawnedEntities.getCompound(i);
      UUID nbtUUID = nbtEntity.getUniqueId("uuid");
      this.spawnedEntities.add(nbtUUID);
    } 
  }


  
  public CompoundNBT write(CompoundNBT nbt) {
    super.write(nbt);
    nbt.putInt("spawnLimit", this.spawnLimit);
    nbt.putInt("playerDistance", this.playerDistance);
    nbt.putString("entityToSpawn", EntityType.getKey(this.entityToSpawn).toString());
    
    ListNBT spawnedEntities = new ListNBT();
    for (UUID uuid : this.spawnedEntities) {
      
      CompoundNBT nbtEntity = new CompoundNBT();
      nbtEntity.putUniqueId("uuid", uuid);
      spawnedEntities.add(nbtEntity);
    } 
    nbt.put("spawns", (INBT)spawnedEntities);
    
    return nbt;
  }
}


