package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModCreativeTabs {
  public static final ItemGroup DEVIL_FRUITS = new ItemGroup("devil_fruits")
    {
      
      @OnlyIn(Dist.CLIENT)
      public ItemStack createIcon()
      {
        return new ItemStack((IItemProvider)ModAbilities.MERA_MERA_NO_MI);
      }
    };
  
  public static final ItemGroup WEAPONS = new ItemGroup("weapons")
    {
      
      @OnlyIn(Dist.CLIENT)
      public ItemStack createIcon()
      {
        return new ItemStack((IItemProvider)ModWeapons.YORU);
      }
    };
  
  public static final ItemGroup EQUIPMENT = new ItemGroup("equipment")
    {
      
      @OnlyIn(Dist.CLIENT)
      public ItemStack createIcon()
      {
        return new ItemStack((IItemProvider)ModArmors.STRAW_HAT);
      }
    };
  
  public static final ItemGroup MISC = new ItemGroup("misc")
    {
      
      @OnlyIn(Dist.CLIENT)
      public ItemStack createIcon()
      {
        return new ItemStack((IItemProvider)ModItems.KAIROSEKI);
      }
    };
}


