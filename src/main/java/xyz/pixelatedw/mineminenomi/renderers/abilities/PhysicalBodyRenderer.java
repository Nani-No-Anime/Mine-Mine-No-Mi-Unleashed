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
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.models.abilities.PhysicalBodyModel;

import java.util.UUID;


@OnlyIn(Dist.CLIENT)
public class PhysicalBodyRenderer
  extends EntityRenderer<PhysicalBodyEntity>
{
  private PhysicalBodyModel model = new PhysicalBodyModel();

  
  public PhysicalBodyRenderer(EntityRendererManager renderManager) {
    super(renderManager);
  }


  
  public void render(PhysicalBodyEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    boolean isSkeleton = false;
    if (entity.getOwner() != null) {
      
      IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)entity.getOwner());
      isSkeleton = (devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && YomiZoanInfo.INSTANCE.isActive((LivingEntity)entity.getOwner()));
    } 
    
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
    
    ResourceLocation res = getEntityTexture(entity);
    if (isSkeleton) {
      
      res = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/yomi_yomi_skeleton.png");
      Minecraft.getInstance().getTextureManager().bindTexture(res);
    }
    else {
      
      res = getEntityTexture(entity);
      Minecraft.getInstance().getTextureManager().bindTexture(res);
    } 
    IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.getRenderType(res));
    this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(PhysicalBodyEntity entity) {
    PlayerEntity player = entity.getOwner();
    if (player != null) {
      return ((AbstractClientPlayerEntity)player).getLocationSkin();
    }
    
    UUID uuid = entity.getOwnerUUID();
    if (uuid != null) {
      return DefaultPlayerSkin.getDefaultSkin(uuid);
    }
    return DefaultPlayerSkin.getDefaultSkinLegacy();
  }






  
  public static class Factory
    implements IRenderFactory<PhysicalBodyEntity>
  {
    public EntityRenderer<? super PhysicalBodyEntity> createRenderFor(EntityRendererManager manager) {
      return new PhysicalBodyRenderer(manager);
    }
  }
}


