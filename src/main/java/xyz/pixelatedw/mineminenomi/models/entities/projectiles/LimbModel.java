package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LimbModel
  extends EntityModel
{
  public final ModelRenderer limb;
  
  public LimbModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.limb = new ModelRenderer((Model)this);
    this.limb.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.limb.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}



  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.limb.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


