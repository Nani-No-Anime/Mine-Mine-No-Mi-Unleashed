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
public class AbareHimatsuriModel
  extends EntityModel
{
  public ModelRenderer platform1;
  public ModelRenderer platform2;
  
  public AbareHimatsuriModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.platform2 = new ModelRenderer((Model)this, 0, 0);
    this.platform2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.platform2.addBox(-10.0F, 10.0F, -10.0F, 20.0F, 10.0F, 20.0F, 0.0F);
    this.platform1 = new ModelRenderer((Model)this, 0, 0);
    this.platform1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.platform1.addBox(-15.0F, 0.0F, -15.0F, 30.0F, 10.0F, 30.0F, 0.0F);
    this.platform1.addChild(this.platform2);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.platform1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


