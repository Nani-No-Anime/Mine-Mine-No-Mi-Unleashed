package xyz.pixelatedw.mineminenomi.mixins.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;

















@Mixin({LivingRenderer.class})
public class LivingRendererMixin<T extends LivingEntity, M extends EntityModel<T>>
{
  @Shadow
  public M entityModel;
  
  @Inject(method = {"render"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/model/EntityModel;setRotationAngles(Lnet/minecraft/entity/Entity;FFFFF)V", shift = At.Shift.AFTER)})
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, CallbackInfo callback) {
    if (entity instanceof PlayerEntity) {
      
      IAnimatedAbility ability = AbilityHelper.getActiveAnimationAbility((LivingEntity)entity);
      if (ability != null) {
        
        if (ability.getAnimation() instanceof TimeAnimation)
          ((TimeAnimation)ability.getAnimation()).tick(); 
        ability.getAnimation().setupAnimation((PlayerEntity)entity, matrixStack, buffer, packedLight, entityYaw, partialTicks);
      } 
    } 
  }

  
  @Inject(method = {"applyRotations"}, at = {@At("HEAD")})
  public void applyRotations(T entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks, CallbackInfo callback) {
    if (entity instanceof PlayerEntity);
  }
}


