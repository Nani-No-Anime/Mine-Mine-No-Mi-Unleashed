package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;
import java.util.Optional;


import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaHandsEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class HanaHandsLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M> {
  private EntityArmModel model = new EntityArmModel();

  
  public HanaHandsLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    Optional<HanaHandsEntity> OptionalHands = WyHelper.<HanaHandsEntity>getEntitiesNear(entity.getPosition(), ((LivingEntity)entity).world, 1.0D,new Class[]{HanaHandsEntity.class}).stream().findFirst();
HanaHandsEntity hands= OptionalHands.orElse((HanaHandsEntity)null);
    boolean inClutch = (hands != null);
    boolean hasEffect = (entity.isPotionActive(ModEffects.HANA_HANDS) && entity.getActivePotionEffect(ModEffects.HANA_HANDS).getDuration() > 0);
    
    if (!inClutch || hands == null || hands.getCaster() == null || !hasEffect) {
      return;
    }
    int type = hands.getHandsType();
    float progress = hands.getAnimationProgress(partialTicks);
    
    float animation = 1.0F;
    if (progress > 0.5F && progress < 0.6F) {
      animation += progress / 2.0F;
    } else if (progress > 0.6F) {
      animation = (float)(animation * (1.0D - progress) / 0.4000000059604645D);
    } 
    matrixStack.push();
    ModelRenderer pivotModel = null;
    float armsPos = -1.0F;
    if (getEntityModel() instanceof BipedModel) {
      pivotModel = ((BipedModel)getEntityModel()).bipedBody;
    } else if (getEntityModel() instanceof IHasHead) {
      pivotModel = ((IHasHead)getEntityModel()).getModelHead();
    } else {
      armsPos = entity.getHeight() / 8.0F;
    } 
    if (pivotModel != null) {
      pivotModel.translateRotate(matrixStack);
    } else {
      matrixStack.translate(0.0D, -armsPos, 0.0D);
    } 
    ResourceLocation skin = ((AbstractClientPlayerEntity)hands.getCaster()).getLocationSkin();
    RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
    if (type == 0) {
      
      matrixStack.scale(animation, animation, animation);
      matrixStack.rotate(Vector3f.ZN.rotationDegrees(90.0F));
      matrixStack.translate(0.3D, -0.35D, -0.1D);
      this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      matrixStack.translate(-0.5D, 0.0D, 0.0D);
      this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    else if (type == 1) {
      
      matrixStack.rotate(Vector3f.ZN.rotationDegrees(120.0F));
      matrixStack.rotate(Vector3f.ZN.rotationDegrees(90.0F * animation));
      matrixStack.translate(0.35D, -0.35D, -0.1D);
      matrixStack.translate(-0.0D, 0.0D, -0.1D);
      this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    } 
    matrixStack.pop();
  }
}


