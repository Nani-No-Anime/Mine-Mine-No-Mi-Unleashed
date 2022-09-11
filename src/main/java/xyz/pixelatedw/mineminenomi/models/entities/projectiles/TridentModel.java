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
public class TridentModel
  extends EntityModel
{
  public ModelRenderer spearCable;
  public ModelRenderer spear1;
  public ModelRenderer spear2;
  public ModelRenderer spear3;
  public ModelRenderer spear4;
  public ModelRenderer spear5;
  public ModelRenderer spear6;
  public ModelRenderer spear7;
  public ModelRenderer spear8;
  public ModelRenderer spear9;
  
  public TridentModel() {
    this.textureWidth = 128;
    this.textureHeight = 32;
    this.spear2 = new ModelRenderer((Model)this, 0, 5);
    this.spear2.setRotationPoint(0.0F, 9.5F, -7.0F);
    this.spear2.addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 2.0F, 0.0F);
    this.spear6 = new ModelRenderer((Model)this, 13, 19);
    this.spear6.setRotationPoint(-2.0F, 9.5F, -7.0F);
    this.spear6.addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.spear6, 3.1415927F, 1.5707963F, 3.1415927F);
    this.spear4 = new ModelRenderer((Model)this, 0, 15);
    this.spear4.setRotationPoint(0.9F, 9.5F, -6.0F);
    this.spear4.addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F);
    setRotateAngle(this.spear4, 0.0F, 0.17453292F, 0.0F);
    this.spear7 = new ModelRenderer((Model)this, 18, 19);
    this.spear7.setRotationPoint(-3.0F, 9.5F, -8.0F);
    this.spear7.addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.spear7, 0.0F, 1.2217305F, 0.0F);
    this.spear9 = new ModelRenderer((Model)this, 18, 22);
    this.spear9.setRotationPoint(3.0F, 9.5F, -8.0F);
    this.spear9.addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.spear9, 0.0F, -1.2217305F, 0.0F);
    this.spearCable = new ModelRenderer((Model)this, 0, 0);
    this.spearCable.setRotationPoint(0.0F, 10.0F, -5.0F);
    this.spearCable.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 30.0F, 0.0F);
    this.spear5 = new ModelRenderer((Model)this, 0, 22);
    this.spear5.setRotationPoint(-0.9F, 9.5F, -6.0F);
    this.spear5.addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F);
    setRotateAngle(this.spear5, 0.0F, -0.17453292F, 0.0F);
    this.spear1 = new ModelRenderer((Model)this, 0, 0);
    this.spear1.setRotationPoint(0.0F, 10.0F, -3.0F);
    this.spear1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
    this.spear8 = new ModelRenderer((Model)this, 13, 22);
    this.spear8.setRotationPoint(2.0F, 9.5F, -7.0F);
    this.spear8.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.spear8, 3.1415927F, 1.5707963F, 3.1415927F);
    this.spear3 = new ModelRenderer((Model)this, 0, 9);
    this.spear3.setRotationPoint(0.0F, 9.5F, -7.0F);
    this.spear3.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 1.0F, 4.0F, 0.0F);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.spear2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spear6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spear4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spear7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spear9.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spearCable.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spear5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spear1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spear8.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spear3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }
}


