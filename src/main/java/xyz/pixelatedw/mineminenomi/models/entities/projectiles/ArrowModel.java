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
public class ArrowModel
  extends EntityModel
{
  public ModelRenderer body;
  public ModelRenderer tail1;
  public ModelRenderer tail2;
  public ModelRenderer head1;
  public ModelRenderer head2;
  
  public ArrowModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.body = new ModelRenderer((Model)this, 0, 0);
    this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body.addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F);
    this.tail1 = new ModelRenderer((Model)this, 15, 0);
    this.tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.tail1.addBox(-5.0F, -1.7F, 0.4F, 4.0F, 4.0F, 0.0F, 0.0F);
    setRotateAngle(this.tail1, 0.0F, 1.5707964F, 0.7853982F);
    this.tail2 = new ModelRenderer((Model)this, 15, 5);
    this.tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.tail2.addBox(-5.0F, -1.8F, -0.3F, 4.0F, 4.0F, 0.0F, 0.0F);
    setRotateAngle(this.tail2, 0.0F, 1.5707964F, -0.7853982F);
    this.head2 = new ModelRenderer((Model)this, 0, 4);
    this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head2.addBox(-0.5F, 0.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F);
    this.head1 = new ModelRenderer((Model)this, 0, 0);
    this.head1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head1.addBox(-1.0F, -0.5F, -6.0F, 2.0F, 2.0F, 1.0F, 0.0F);
    this.body.addChild(this.tail1);
    this.body.addChild(this.tail2);
    this.head1.addChild(this.head2);
    this.body.addChild(this.head1);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


