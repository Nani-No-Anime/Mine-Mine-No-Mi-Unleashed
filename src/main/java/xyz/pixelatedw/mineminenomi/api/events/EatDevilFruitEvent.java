package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import javax.annotation.Nonnull;


public class EatDevilFruitEvent
  extends PlayerEvent
{
  private final ItemStack devilFruit;
  
  private EatDevilFruitEvent(PlayerEntity entity, @Nonnull ItemStack devilFruit) {
    super(entity);
    this.devilFruit = devilFruit;
  }

  
  @Nonnull
  public ItemStack getItem() {
    return this.devilFruit;
  }
  
  @Cancelable
  public static class Pre
    extends EatDevilFruitEvent
  {
    public Pre(PlayerEntity entity, @Nonnull ItemStack devilFruit) {
      super(entity, devilFruit);
    }
  }
  
  public static class Post
    extends EatDevilFruitEvent
  {
    public Post(PlayerEntity entity, @Nonnull ItemStack devilFruit) {
      super(entity, devilFruit);
    }
  }
}


