package xyz.pixelatedw.mineminenomi.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

public class DFItemEntity
  extends ItemEntity {
  public DFItemEntity(World world) {
    super(ModEntities.DEVIL_FRUIT_ITEM, world);
  }

  
  public DFItemEntity(World world, double x, double y, double z, ItemStack stack) {
    this(world);
    setItem(stack);
    this.lifespan = (stack.getItem() == null) ? 6000 : stack.getEntityLifespan(world);
    setPosition(x, y, z);
    this.rotationYaw = this.rand.nextFloat() * 360.0F;
  }


  
  public void remove() {
    if (getItem().getItem() instanceof AkumaNoMiItem && !getItem().isEmpty() && !this.world.isRemote)
      ExtendedWorldData.get(this.world).removeDevilFruitInWorld((AkumaNoMiItem)getItem().getItem()); 
    super.remove();
  }


  
  public void onCollideWithPlayer(PlayerEntity player) {
    super.onCollideWithPlayer(player);
    if (!player.world.isRemote && !cannotPickup()) {
      ExtendedWorldData.get(this.world).addDevilFruitInWorld((AkumaNoMiItem)getItem().getItem());
    }
  }



  
  protected void updatePortal() {}


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket((Entity)this);
  }
}


