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
public class TokuHollowModel
  extends EntityModel
{
  public ModelRenderer head;
  public ModelRenderer head2;
  public ModelRenderer head3;
  public ModelRenderer head4;
  public ModelRenderer body;
  public ModelRenderer rightarm;
  public ModelRenderer leftarm;
  public ModelRenderer rightleg;
  public ModelRenderer leftleg;
  
  public TokuHollowModel() {
    this.textureWidth = 256;
    this.textureHeight = 128;
    this.head2 = new ModelRenderer((Model)this, 0, 50);
    this.head2.setRotationPoint(-11.0F, -7.0F, -5.5F);
    this.head2.addBox(0.0F, 0.0F, 0.0F, 22.0F, 22.0F, 25.0F, 0.0F);
    this.head3 = new ModelRenderer((Model)this, 98, 50);
    this.head3.setRotationPoint(-12.5F, -7.0F, -4.0F);
    this.head3.addBox(0.0F, 0.0F, 0.0F, 25.0F, 22.0F, 22.0F, 0.0F);
    this.head4 = new ModelRenderer((Model)this, 98, 0);
    this.head4.setRotationPoint(-11.0F, -8.5F, -4.0F);
    this.head4.addBox(0.0F, 0.0F, 0.0F, 22.0F, 25.0F, 22.0F, 0.0F);
    this.rightarm = new ModelRenderer((Model)this, 188, 22);
    this.rightarm.setRotationPoint(-17.0F, 8.0F, -9.0F);
    this.rightarm.addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 12.0F, 0.0F);
    setRotateAngle(this.rightarm, 0.13962634F, 0.41887903F, 0.0F);
    this.head = new ModelRenderer((Model)this, 0, 0);
    this.head.setRotationPoint(-12.0F, -8.0F, -5.0F);
    this.head.addBox(0.0F, 0.0F, 0.0F, 24.0F, 24.0F, 24.0F, 0.0F);
    this.rightleg = new ModelRenderer((Model)this, 188, 14);
    this.rightleg.setRotationPoint(-4.0F, 5.0F, 28.5F);
    this.rightleg.addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
    this.body = new ModelRenderer((Model)this, 188, 0);
    this.body.setRotationPoint(-4.0F, 4.0F, 19.5F);
    this.body.addBox(0.0F, 0.0F, 0.0F, 8.0F, 4.0F, 9.0F, 0.0F);
    this.leftarm = new ModelRenderer((Model)this, 188, 22);
    this.leftarm.setRotationPoint(14.2F, 8.0F, -10.0F);
    this.leftarm.addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 12.0F, 0.0F);
    setRotateAngle(this.leftarm, 0.13962634F, -0.41887903F, 0.0F);
    this.leftleg = new ModelRenderer((Model)this, 188, 14);
    this.leftleg.setRotationPoint(2.0F, 5.0F, 28.5F);
    this.leftleg.addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.head2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


