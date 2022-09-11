package xyz.pixelatedw.mineminenomi.entities;

import net.minecraft.entity.MobEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class WantedPosterPackageEntity
  extends MobEntity
{
  public WantedPosterPackageEntity(World world) {
    super(ModEntities.WANTED_POSTER_PACKAGE, world);
    setHealth(1.0F);
  }


  
  public void spawnDrops(DamageSource cause) {
    if (!this.onGround) {
      ItemsHelper.dropWantedPosters(this.world, (int)getPosX(), (int)getPosY(), (int)getPosZ());
    }
  }

  
  public void tick() {
    setMotion(getMotion().getX(), getMotion().getY() / (1.5D + this.world.rand.nextDouble()), getMotion().getZ());
    this.fallDistance = 0.0F;
    BlockPos pos = new BlockPos(getPosX(), getPosY(), getPosZ());
    
    if (this.onGround && !this.world.isRemote)
    {
      if (this.world.isAirBlock(pos)) {
        
        this.world.setBlockState(pos, ModBlocks.WANTED_POSTER_PACKAGE.getDefaultState());
        remove();
      }
      else if (this.world.isAirBlock(pos.up())) {
        
        this.world.setBlockState(pos.up(), ModBlocks.WANTED_POSTER_PACKAGE.getDefaultState());
        remove();
      } 
    }
    
    if (isInWater() || isInLava()) {
      onKillCommand();
    }
    super.tick();
  }
}


