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
public class NegativeHollowModel
  extends EntityModel
{
  public ModelRenderer head;
  public ModelRenderer body;
  public ModelRenderer rightarm1;
  public ModelRenderer rightarm2;
  public ModelRenderer leftarm1;
  public ModelRenderer leftarm2;
  public ModelRenderer rightleg;
  public ModelRenderer leftleg;
  
  public NegativeHollowModel() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.body = new ModelRenderer((Model)this, 35, 0);
    this.body.setRotationPoint(0.0F, 5.0F, -6.0F);
    this.body.addBox(0.0F, 0.0F, 0.0F, 3.0F, 8.0F, 2.0F, 0.0F);
    setRotateAngle(this.body, 1.5707964F, -0.0F, 0.0F);
    this.leftleg = new ModelRenderer((Model)this, 46, 0);
    this.leftleg.setRotationPoint(2.0F, 4.5F, 2.0F);
    this.leftleg.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftleg, 1.5707964F, -0.0F, 0.0F);
    this.rightarm1 = new ModelRenderer((Model)this, 28, 0);
    this.rightarm1.setRotationPoint(-3.0F, 5.0F, -9.0F);
    this.rightarm1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 2.0F, 0.0F);
    setRotateAngle(this.rightarm1, 1.5707964F, 0.55850536F, 0.0F);
    this.leftarm1 = new ModelRenderer((Model)this, 28, 0);
    this.leftarm1.setRotationPoint(5.0F, 5.0F, -9.5F);
    this.leftarm1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 2.0F, 0.0F);
    setRotateAngle(this.leftarm1, 1.5707964F, -0.55850536F, 0.0F);
    this.leftarm2 = new ModelRenderer((Model)this, 17, 0);
    this.leftarm2.setRotationPoint(4.0F, 5.5F, -14.5F);
    this.leftarm2.addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 3.0F, 0.0F);
    setRotateAngle(this.leftarm2, 1.5707964F, -0.0F, 0.0F);
    this.rightarm2 = new ModelRenderer((Model)this, 17, 0);
    this.rightarm2.setRotationPoint(-3.0F, 5.5F, -14.5F);
    this.rightarm2.addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 3.0F, 0.0F);
    setRotateAngle(this.rightarm2, 1.5707964F, -0.0F, 0.0F);
    this.rightleg = new ModelRenderer((Model)this, 46, 0);
    this.rightleg.setRotationPoint(0.0F, 4.5F, 2.0F);
    this.rightleg.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightleg, 1.5707964F, -0.0F, 0.0F);
    this.head = new ModelRenderer((Model)this, 0, 0);
    this.head.mirror = true;
    this.head.setRotationPoint(-0.5F, 2.0F, -10.0F);
    this.head.addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightarm1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftarm1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftarm2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightarm2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


