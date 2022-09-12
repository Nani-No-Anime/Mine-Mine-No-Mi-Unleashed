package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.MinkLionPartialModel;

public class MinkLionPartialRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel>
  extends PlayerRenderer {
  private MinkLionPartialModel model = new MinkLionPartialModel();
  private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/mink_lion.png");

  
  public MinkLionPartialRenderer(EntityRendererManager renderManager) {
    super(renderManager);
  }


  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    ((PlayerModel)this.entityModel).swingProgress = getSwingProgress(entity, partialTicks);
    
    boolean shouldSit = (entity.isPassenger() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
    ((PlayerModel)this.entityModel).isSitting = shouldSit;
    ((PlayerModel)this.entityModel).isChild = entity.isChild();
    float headYawOffset = MathHelper.interpolateAngle(partialTicks, entity.prevRenderYawOffset, entity.renderYawOffset);
    float headYaw = MathHelper.interpolateAngle(partialTicks, entity.prevRotationYawHead, entity.rotationYawHead);
    float netHeadYaw = headYaw - headYawOffset;
    if (shouldSit && entity.getRidingEntity() instanceof LivingEntity) {
      
      LivingEntity livingentity = (LivingEntity)entity.getRidingEntity();
      headYawOffset = MathHelper.interpolateAngle(partialTicks, livingentity.prevRenderYawOffset, livingentity.renderYawOffset);
      netHeadYaw = headYaw - headYawOffset;
      float f3 = MathHelper.wrapDegrees(netHeadYaw);
      if (f3 < -85.0F)
      {
        f3 = -85.0F;
      }
      
      if (f3 >= 85.0F)
      {
        f3 = 85.0F;
      }
      
      headYawOffset = headYaw - f3;
      if (f3 * f3 > 2500.0F)
      {
        headYawOffset += f3 * 0.2F;
      }
      
      netHeadYaw = headYaw - headYawOffset;
    } 
    
    float headPitch = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);
    if (entity.getPose() == Pose.SLEEPING) {
      
      Direction direction = entity.getBedDirection();
      if (direction != null) {
        
        float f4 = entity.getEyeHeight(Pose.STANDING) - 0.1F;
        matrixStack.translate((-direction.getXOffset() * f4), 0.0D, (-direction.getZOffset() * f4));
      } 
    } 
    
    float ageInTicks = handleRotationFloat(entity, partialTicks);
    applyRotations(entity, matrixStack, ageInTicks, headYawOffset, partialTicks);
    matrixStack.scale(-1.0F, -1.0F, 1.0F);
    preRenderCallback(entity, matrixStack, partialTicks);
    matrixStack.translate(0.0D, -1.5010000467300415D, 0.0D);
    float limbSwingAmount = 0.0F;
    float limbSwing = 0.0F;
    if (!shouldSit && entity.isAlive()) {
      
      limbSwingAmount = MathHelper.lerp(partialTicks, entity.prevLimbSwingAmount, entity.limbSwingAmount);
      limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
      if (entity.isChild())
      {
        limbSwing *= 3.0F;
      }
      
      if (limbSwingAmount > 1.0F)
      {
        limbSwingAmount = 1.0F;
      }
    } 
    
    RenderType renderType = ModRenderTypes.getZoanRenderType(this.texture);
    this.model.isSneak = entity.isCrouching();
    this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, entity.rotationYaw, partialTicks, packedLight);
    
    matrixStack.pop();
  }

  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new MinkLionPartialRenderer<>(manager);
    }
  }
}


