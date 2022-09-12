package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.SpringLegsPartialModel;

public class SpringLegsLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M>
{
  private SpringLegsPartialModel model = new SpringLegsPartialModel();
  private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/spring_legs.png");
  
  private float renderingTicks;
  
  public SpringLegsLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    RenderType renderType = ModRenderTypes.getZoanRenderType(this.texture);
    this.model.isSneak = entity.isCrouching();
    
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      
      if (!((LivingEntity)entity).world.isAirBlock(entity.getPosition().down(i)))
        sum++; 
    } 
    boolean isSolid = (sum >= 2);
    
    if (!((LivingEntity)entity).onGround) {
      
      limbSwing /= 4.0F;
      limbSwingAmount /= 4.0F;
      if (ModKeybindings.isSpaceKeyDown() && (entity.getMotion()).y > 0.3D && isSolid) {
        
        matrixStack.translate(0.0D, -0.7300000190734863D, 0.0D);
        matrixStack.scale(1.0F, 2.0F, 1.0F);
      } 
    } 
    
    this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
  }

  
  public SpringLegsPartialModel getPartialModel() {
    return this.model;
  }
}


