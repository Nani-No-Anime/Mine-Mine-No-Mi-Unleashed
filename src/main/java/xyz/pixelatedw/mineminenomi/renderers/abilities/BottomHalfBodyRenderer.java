package xyz.pixelatedw.mineminenomi.renderers.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity;

@OnlyIn(Dist.CLIENT)
public class BottomHalfBodyRenderer
  extends EntityRenderer<BottomHalfBodyEntity> {
  private PlayerModel model = new PlayerModel(1.0F, false);

  
  public BottomHalfBodyRenderer(EntityRendererManager renderManager) {
    super(renderManager);
  }


  
  public void render(BottomHalfBodyEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    matrixStack.translate(0.0D, 1.5D, 0.0D);
    matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.YP, entity.rotationYaw + 180.0F, true));
    
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    
    if (entity.hurtTime > 0) {
      
      matrixStack.push();
      RenderSystem.color3f(1.0F, 0.0F, 0.0F);
      matrixStack.pop();
    } 
    
    float f = MathHelper.interpolateAngle(partialTicks, entity.prevRenderYawOffset, entity.renderYawOffset);
    float f1 = MathHelper.interpolateAngle(partialTicks, entity.prevRotationYawHead, entity.rotationYawHead);
    float f2 = f1 - f;
    
    float f6 = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);
    float f7 = entity.ticksExisted + partialTicks;
    
    float f8 = 0.0F;
    float f5 = 0.0F;
    if (entity.isAlive()) {
      
      f8 = MathHelper.lerp(partialTicks, entity.prevLimbSwingAmount, entity.limbSwingAmount);
      f5 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
      if (entity.isChild())
      {
        f5 *= 3.0F;
      }
      
      if (f8 > 1.0F)
      {
        f8 = 1.0F;
      }
    } 
    
    ResourceLocation res = getEntityTexture(entity);
    Minecraft.getInstance().getTextureManager().bindTexture(res);
    IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.getRenderType(res));
    this.model.isChild = false;
    this.model.setLivingAnimations((LivingEntity)entity, f5, f8, partialTicks);
    this.model.setRotationAngles(entity, f5, f8, f7, f2, f6);
    this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    this.model.setVisible(false);
    this.model.bipedLeftLeg.showModel = true;
    this.model.bipedRightLeg.showModel = true;
    
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(BottomHalfBodyEntity entity) {
    PlayerEntity player = entity.world.getPlayerByUuid(entity.getOwnerUUID());
    if (player != null) {
      return ((AbstractClientPlayerEntity)player).getLocationSkin();
    }
    return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
  }





  
  public static class Factory
    implements IRenderFactory<BottomHalfBodyEntity>
  {
    public EntityRenderer<? super BottomHalfBodyEntity> createRenderFor(EntityRendererManager manager) {
      return new BottomHalfBodyRenderer(manager);
    }
  }
}


