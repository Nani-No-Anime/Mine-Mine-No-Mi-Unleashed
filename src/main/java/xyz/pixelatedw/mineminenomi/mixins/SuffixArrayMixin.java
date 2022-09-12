package xyz.pixelatedw.mineminenomi.mixins;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.client.util.SuffixArray;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;

import java.util.List;
import java.util.Set;






@Mixin({SuffixArray.class})
public class SuffixArrayMixin<T>
{
  @Inject(method = {"search"}, at = {@At("RETURN")}, cancellable = true)
  public void search(CallbackInfoReturnable<List<T>> info) {
    if (CommonConfig.INSTANCE.getRandomizedFruits()) {
      
      List<T> list = (List<T>)info.getReturnValue();
      Set<T> set = Sets.newLinkedHashSet();
      if (list != null && list.size() > 0)
      {
        for (T item : list) {
          
          if (item == null) {
            continue;
          }
          if (item instanceof ItemStack) {
            
            ItemStack stack = (ItemStack)item;
            if (!stack.isEmpty() && stack.getItem() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem) {
              continue;
            }
          } 
          set.add(item);
        } 
      }
      info.setReturnValue(Lists.newArrayList(set));
    } 
  }
}


