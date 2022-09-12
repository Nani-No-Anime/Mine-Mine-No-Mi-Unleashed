package xyz.pixelatedw.mineminenomi.wypi;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.IProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;









public class ModdedSpawnEggItem
  extends SpawnEggItem
{
  protected static final List<ModdedSpawnEggItem> UNADDED_EGGS = new ArrayList<>();
  
  private final Supplier<? extends EntityType<?>> entityTypeSupplier;
  
  public ModdedSpawnEggItem(NonNullSupplier<? extends EntityType<?>> entityTypeSupplier, int p_i48465_2_, int p_i48465_3_, Item.Properties p_i48465_4_) {
    super(null, p_i48465_2_, p_i48465_3_, p_i48465_4_);
    Objects.requireNonNull(entityTypeSupplier); this.entityTypeSupplier = entityTypeSupplier::get;
    UNADDED_EGGS.add(this);
  }







  
  public static void initUnaddedEggs() {
    Map<EntityType<?>, SpawnEggItem> EGGS = (Map<EntityType<?>, SpawnEggItem>)ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
    DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior()
      {
        
        public ItemStack dispenseStack(IBlockSource source, ItemStack stack)
        {
          Direction direction = (Direction)source.getBlockState().get((IProperty)DispenserBlock.FACING);
          EntityType<?> entitytype = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
          entitytype.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, (direction != Direction.UP), false);
          stack.shrink(1);
          return stack;
        }
      };
    for (SpawnEggItem egg : UNADDED_EGGS) {
      
      EGGS.put(egg.getType(null), egg);
      DispenserBlock.registerDispenseBehavior((IItemProvider)egg, (IDispenseItemBehavior)defaultDispenseItemBehavior);
    } 
    
    UNADDED_EGGS.clear();
  }


  
  public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
    return this.entityTypeSupplier.get();
  }
}


