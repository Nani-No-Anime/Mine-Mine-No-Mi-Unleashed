/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierApplyEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierRemoveEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Effect.class})
/*    */ public class EffectMixin
/*    */ {
/*    */   @Inject(method = {"applyAttributesModifiersToEntity"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/attributes/IAttributeInstance;applyModifier(Lnet/minecraft/entity/ai/attributes/AttributeModifier;)V", shift = At.Shift.AFTER)})
/*    */   public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap map, int amplifier, CallbackInfo info) {
/* 28 */     AttributeModifierApplyEvent event = new AttributeModifierApplyEvent(entity, map, amplifier);
/* 29 */     MinecraftForge.EVENT_BUS.post(event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"removeAttributesModifiersFromEntity"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/attributes/IAttributeInstance;removeModifier(Lnet/minecraft/entity/ai/attributes/AttributeModifier;)V", shift = At.Shift.AFTER)})
/*    */   public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap map, int amplifier, CallbackInfo info) {
/* 42 */     AttributeModifierRemoveEvent event = new AttributeModifierRemoveEvent(entity, map, amplifier);
/* 43 */     MinecraftForge.EVENT_BUS.post(event);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\EffectMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */