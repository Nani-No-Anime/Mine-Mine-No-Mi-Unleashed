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
public class MiniHollowModel
  extends EntityModel
{
  public ModelRenderer head;
  public ModelRenderer body;
  public ModelRenderer rightarm;
  public ModelRenderer leftarm;
  public ModelRenderer rightleg;
  public ModelRenderer rightleg_1;
  
  public MiniHollowModel() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.rightleg_1 = new ModelRenderer((Model)this, 19, 0);
    this.rightleg_1.setRotationPoint(0.6F, 10.1F, 0.5F);
    this.rightleg_1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightleg_1, 0.0F, -0.0F, -0.61086524F);
    this.rightarm = new ModelRenderer((Model)this, 14, 0);
    this.rightarm.setRotationPoint(0.5F, 7.7F, 0.5F);
    this.rightarm.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightarm, 0.0F, -0.0F, 0.9424778F);
    this.body = new ModelRenderer((Model)this, 9, 0);
    this.body.setRotationPoint(0.5F, 8.0F, 0.5F);
    this.body.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    this.head = new ModelRenderer((Model)this, 0, 0);
    this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.head.addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
    this.rightleg = new ModelRenderer((Model)this, 19, 0);
    this.rightleg.setRotationPoint(0.6F, 9.5F, 0.5F);
    this.rightleg.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightleg, 0.0F, -0.0F, 0.61086524F);
    this.leftarm = new ModelRenderer((Model)this, 14, 0);
    this.leftarm.setRotationPoint(1.0F, 8.5F, 0.5F);
    this.leftarm.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftarm, 0.0F, -0.0F, -0.9424778F);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.rightleg_1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


