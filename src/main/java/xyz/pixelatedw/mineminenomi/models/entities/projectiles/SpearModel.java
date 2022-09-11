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
public class SpearModel
  extends EntityModel
{
  public ModelRenderer harpooncable;
  public ModelRenderer harpoon1;
  public ModelRenderer harpoon2;
  public ModelRenderer harpoon3;
  public ModelRenderer harpoon4;
  public ModelRenderer harpoon5;
  public ModelRenderer harpoon6;
  
  public SpearModel() {
    this.textureWidth = 128;
    this.textureHeight = 32;
    this.harpoon4 = new ModelRenderer((Model)this, 0, 12);
    this.harpoon4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.harpoon4.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F);
    this.harpooncable = new ModelRenderer((Model)this, 0, 0);
    this.harpooncable.setRotationPoint(0.0F, 0.0F, -5.0F);
    this.harpooncable.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 30.0F, 0.0F);
    this.harpoon3 = new ModelRenderer((Model)this, 0, 8);
    this.harpoon3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.harpoon3.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 2.0F, 0.0F);
    this.harpoon5 = new ModelRenderer((Model)this, 0, 15);
    this.harpoon5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.harpoon5.addBox(-1.1F, 0.0F, -2.25F, 5.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.harpoon5, 0.0F, 1.134464F, 0.0F);
    this.harpoon2 = new ModelRenderer((Model)this, 0, 5);
    this.harpoon2.setRotationPoint(0.5F, 0.0F, 0.0F);
    this.harpoon2.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F);
    this.harpoon6 = new ModelRenderer((Model)this, 0, 18);
    this.harpoon6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.harpoon6.addBox(-3.9F, 0.0F, -2.25F, 5.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.harpoon6, 0.0F, -1.134464F, 0.0F);
    this.harpoon1 = new ModelRenderer((Model)this, 0, 0);
    this.harpoon1.setRotationPoint(0.0F, 0.0F, 5.0F);
    this.harpoon1.addBox(-0.5F, -0.5F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
    this.harpoon2.addChild(this.harpoon4);
    this.harpoon2.addChild(this.harpoon3);
    this.harpoon2.addChild(this.harpoon5);
    this.harpooncable.addChild(this.harpoon2);
    this.harpoon2.addChild(this.harpoon6);
    this.harpooncable.addChild(this.harpoon1);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.harpooncable.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


