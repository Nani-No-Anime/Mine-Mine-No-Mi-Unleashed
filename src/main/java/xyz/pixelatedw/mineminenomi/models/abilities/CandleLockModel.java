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
public class CandleLockModel
  extends EntityModel
{
  public ModelRenderer candleLock1;
  public ModelRenderer candleLock2;
  public ModelRenderer candleLock3;
  
  public CandleLockModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.candleLock3 = new ModelRenderer((Model)this, 0, 17);
    this.candleLock3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.candleLock3.addBox(7.5F, -4.0F, -4.0F, 4.0F, 8.0F, 8.0F, 0.0F);
    this.candleLock1 = new ModelRenderer((Model)this, 0, 0);
    this.candleLock1.setRotationPoint(0.0F, 18.9F, 0.0F);
    this.candleLock1.addBox(-7.5F, -3.5F, -3.5F, 15.0F, 7.0F, 7.0F, 0.0F);
    this.candleLock2 = new ModelRenderer((Model)this, 0, 36);
    this.candleLock2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.candleLock2.addBox(-11.5F, -4.0F, -4.0F, 4.0F, 8.0F, 8.0F, 0.0F);
    this.candleLock1.addChild(this.candleLock3);
    this.candleLock1.addChild(this.candleLock2);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.candleLock1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


