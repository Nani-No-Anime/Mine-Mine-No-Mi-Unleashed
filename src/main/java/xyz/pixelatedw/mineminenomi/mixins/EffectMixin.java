package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Effect;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierApplyEvent;
import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierRemoveEvent;









@Mixin({Effect.class})
public class EffectMixin
{
  @Inject(method = {"applyAttributesModifiersToEntity"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/attributes/IAttributeInstance;applyModifier(Lnet/minecraft/entity/ai/attributes/AttributeModifier;)V", shift = At.Shift.AFTER)})
  public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap map, int amplifier, CallbackInfo info) {
    AttributeModifierApplyEvent event = new AttributeModifierApplyEvent(entity, map, amplifier);
    MinecraftForge.EVENT_BUS.post(event);
  }








  
  @Inject(method = {"removeAttributesModifiersFromEntity"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/attributes/IAttributeInstance;removeModifier(Lnet/minecraft/entity/ai/attributes/AttributeModifier;)V", shift = At.Shift.AFTER)})
  public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap map, int amplifier, CallbackInfo info) {
    AttributeModifierRemoveEvent event = new AttributeModifierRemoveEvent(entity, map, amplifier);
    MinecraftForge.EVENT_BUS.post(event);
  }
}


