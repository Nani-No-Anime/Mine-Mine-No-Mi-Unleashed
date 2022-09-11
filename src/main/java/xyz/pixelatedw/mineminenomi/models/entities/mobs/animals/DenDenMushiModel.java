package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DenDenMushiModel
  extends BipedModel
{
  private ModelRenderer shape1;
  private ModelRenderer shape2;
  private ModelRenderer shape3;
  private ModelRenderer shape4;
  private ModelRenderer shape5;
  private ModelRenderer shape6;
  private ModelRenderer shape7;
  
  public DenDenMushiModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.bipedBody.showModel = false;
    this.bipedHead.showModel = false;
    this.bipedHeadwear.showModel = false;
    this.bipedLeftArm.showModel = false;
    this.bipedLeftLeg.showModel = false;
    this.bipedRightArm.showModel = false;
    this.bipedRightLeg.showModel = false;
    
    this.shape1 = new ModelRenderer((Model)this, 0, 0);
    this.shape1.addBox(-2.0F, 0.0F, -3.0F, 5.0F, 1.0F, 9.0F);
    this.shape1.setRotationPoint(0.0F, 23.0F, 0.0F);
    this.shape1.setTextureSize(64, 64);
    this.shape1.mirror = true;
    setRotation(this.shape1, 0.0F, 0.0F, 0.0F);
    this.shape2 = new ModelRenderer((Model)this, 21, 11);
    this.shape2.addBox(-1.0F, -3.0F, -3.0F, 3.0F, 3.0F, 3.0F);
    this.shape2.setRotationPoint(0.0F, 23.0F, 0.0F);
    this.shape2.setTextureSize(64, 64);
    this.shape2.mirror = true;
    setRotation(this.shape2, 0.0F, 0.0F, 0.0F);
    this.shape3 = new ModelRenderer((Model)this, 0, 11);
    this.shape3.addBox(-2.0F, -5.0F, 0.0F, 5.0F, 5.0F, 5.0F);
    this.shape3.setRotationPoint(0.0F, 23.0F, 0.0F);
    this.shape3.setTextureSize(64, 64);
    this.shape3.mirror = true;
    setRotation(this.shape3, 0.0F, 0.0F, 0.0F);
    this.shape4 = new ModelRenderer((Model)this, 29, 0);
    this.shape4.addBox(-1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 1.0F);
    this.shape4.setRotationPoint(0.0F, 23.0F, 0.0F);
    this.shape4.setTextureSize(64, 64);
    this.shape4.mirror = true;
    setRotation(this.shape4, 0.0F, 0.0F, 0.0F);
    this.shape5 = new ModelRenderer((Model)this, 34, 3);
    this.shape5.addBox(-1.666667F, -7.0F, -2.5F, 2.0F, 2.0F, 2.0F);
    this.shape5.setRotationPoint(0.0F, 23.0F, 0.0F);
    this.shape5.setTextureSize(64, 64);
    this.shape5.mirror = true;
    setRotation(this.shape5, 0.0F, 0.0F, 0.0F);
    this.shape6 = new ModelRenderer((Model)this, 29, 0);
    this.shape6.addBox(1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 1.0F);
    this.shape6.setRotationPoint(0.0F, 23.0F, 0.0F);
    this.shape6.setTextureSize(64, 64);
    this.shape6.mirror = true;
    setRotation(this.shape6, 0.0F, 0.0F, 0.0F);
    this.shape7 = new ModelRenderer((Model)this, 34, 3);
    this.shape7.addBox(0.7F, -7.0F, -2.5F, 2.0F, 2.0F, 2.0F);
    this.shape7.setRotationPoint(0.0F, 23.0F, 0.0F);
    this.shape7.setTextureSize(64, 64);
    this.shape7.mirror = true;
    setRotation(this.shape7, 0.0F, 0.0F, 0.0F);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.shape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.shape6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.shape7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


