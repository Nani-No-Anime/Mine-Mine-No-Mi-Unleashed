package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;


public class ModTags
{
  public static class Items
  {
    public static final Tag<Item> KAIROSEKI = tag("kairoseki");
    public static final Tag<Item> MAGNETIC = tag("magnetic");
    public static final Tag<Item> RUSTY = tag("rusty");

    
    private static Tag<Item> tag(String id) {
      return (Tag<Item>)new ItemTags.Wrapper(new ResourceLocation("mineminenomi", id));
    }
  }
  
  public static class Blocks
  {
    public static final Tag<Block> NO_PHASE = tag("nophase");
    public static final Tag<Block> RUSTY = tag("rusty");

    
    private static Tag<Block> tag(String id) {
      return (Tag<Block>)new BlockTags.Wrapper(new ResourceLocation("mineminenomi", id));
    }
  }
  
  public static class Entities
  {
    public static final Tag<EntityType<?>> MAGNETIC = tag("magnetic");

    
    private static Tag<EntityType<?>> tag(String id) {
      return (Tag<EntityType<?>>)new EntityTypeTags.Wrapper(new ResourceLocation("mineminenomi", id));
    }
  }
}


