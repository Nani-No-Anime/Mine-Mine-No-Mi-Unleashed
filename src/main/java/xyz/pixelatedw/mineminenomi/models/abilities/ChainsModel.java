package xyz.pixelatedw.mineminenomi.models.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChainsModel
  extends EntityModel
{
  public ModelRenderer shape15;
  
  public ChainsModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.shape15 = new ModelRenderer((Model)this, 0, 0);
    this.shape15.setRotationPoint(0.0F, 5.8F, 0.0F);
    this.shape15.addBox(-9.0F, 0.0F, -6.0F, 18.0F, 5.0F, 12.0F, 0.0F);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.shape15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


