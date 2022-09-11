package xyz.pixelatedw.mineminenomi.mixins;

import java.util.function.Consumer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;




@Mixin({ItemStack.class})
public class ItemStackMixin
{
  @Inject(method = {"damageItem"}, at = {@At("HEAD")}, cancellable = true)
  public <T extends LivingEntity> void damageItem(int amount, T entity, Consumer<T> onBroken, CallbackInfo callback) {
    boolean hakiActiveFlag = HakiHelper.hasImbuingActive((LivingEntity)entity);
    if (hakiActiveFlag)
      callback.cancel(); 
  }
}


