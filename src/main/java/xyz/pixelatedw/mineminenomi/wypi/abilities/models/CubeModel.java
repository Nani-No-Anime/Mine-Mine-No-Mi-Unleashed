package xyz.pixelatedw.mineminenomi.wypi.abilities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CubeModel
  extends EntityModel
{
  public ModelRenderer shape1;
  
  public CubeModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.shape1 = new ModelRenderer((Model)this, 0, 0);
    this.shape1.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape1.setTextureSize(64, 64);
    this.shape1.mirror = true;
    setRotation(this.shape1, 0.0F, 0.0F, 0.0F);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}



  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


