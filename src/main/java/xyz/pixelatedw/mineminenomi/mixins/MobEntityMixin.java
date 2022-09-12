package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.potion.EffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;

import java.util.Objects;

@Mixin({MobEntity.class})
public abstract class MobEntityMixin
{
  @Inject(method = {"updateEntityActionState"}, at = {@At("HEAD")}, cancellable = true)
  protected void updateEntityActionState(CallbackInfo callback) {
    MobEntity entity = (MobEntity)(Object)this;


    
    Objects.requireNonNull(IBindHandsEffect.class); entity.getActivePotionEffects().stream().map(EffectInstance::getPotion).filter(IBindHandsEffect.class::isInstance)
      .filter(eff -> ((IBindHandsEffect)eff).isBlockingSwings())
      .forEach(eff -> {
          entity.goalSelector.disableFlag(Goal.Flag.JUMP);
          entity.goalSelector.disableFlag(Goal.Flag.MOVE);
          entity.goalSelector.disableFlag(Goal.Flag.TARGET);
        });
  }
}


