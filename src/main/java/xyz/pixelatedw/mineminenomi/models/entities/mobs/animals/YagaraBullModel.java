package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;

@OnlyIn(Dist.CLIENT)
public class YagaraBullModel<T extends YagaraBullEntity> extends BipedModel<T> {
  public ModelRenderer body1;
  public ModelRenderer body2;
  public ModelRenderer tail1;
  public ModelRenderer neck1;
  public ModelRenderer leftfin1;
  public ModelRenderer leftfin2;
  public ModelRenderer rightfin1;
  public ModelRenderer rightfin2;
  public ModelRenderer saddle;
  public ModelRenderer belt1;
  public ModelRenderer belt2;
  public ModelRenderer tail2;
  public ModelRenderer tail3;
  public ModelRenderer tail4;
  public ModelRenderer tail5;
  public ModelRenderer tail6;
  public ModelRenderer neck2;
  public ModelRenderer neck3;
  public ModelRenderer neck4;
  public ModelRenderer head1;
  public ModelRenderer mane;
  public ModelRenderer head2;
  public ModelRenderer head3;
  public ModelRenderer righteye;
  public ModelRenderer lefteye;
  public ModelRenderer leftgill;
  public ModelRenderer rightgill;
  public ModelRenderer mout;
  public ModelRenderer saddleside1;
  public ModelRenderer saddleside2;
  public ModelRenderer saddlefront;
  public ModelRenderer saddleside3;
  public ModelRenderer saddleside4;
  public ModelRenderer saddleback;
  public ModelRenderer saddlemiddle;
  
  public YagaraBullModel() {
    super(1.0F);
    this.textureWidth = 180;
    this.textureHeight = 90;
    
    this.bipedBody.showModel = false;
    this.bipedHead.showModel = false;
    this.bipedHeadwear.showModel = false;
    this.bipedLeftArm.showModel = false;
    this.bipedLeftLeg.showModel = false;
    this.bipedRightArm.showModel = false;
    this.bipedRightLeg.showModel = false;
    
    this.saddleside2 = new ModelRenderer((Model)this, 25, 64);
    this.saddleside2.setRotationPoint(-4.8F, -1.2F, 10.1F);
    this.saddleside2.addBox(0.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
    setRotateAngle(this.saddleside2, 0.017453292F, 0.10471976F, -0.13962634F);
    this.rightfin1 = new ModelRenderer((Model)this, 99, 41);
    this.rightfin1.setRotationPoint(-3.9F, 20.3F, -6.0F);
    this.rightfin1.addBox(-8.0F, -0.5F, -6.0F, 8.0F, 1.0F, 6.0F, 0.0F);
    setRotateAngle(this.rightfin1, -0.19221127F, 0.6485391F, -0.4435539F);
    this.tail2 = new ModelRenderer((Model)this, 47, 15);
    this.tail2.setRotationPoint(0.0F, 0.0F, 4.0F);
    this.tail2.addBox(-4.0F, -3.5F, 0.0F, 8.0F, 7.0F, 5.0F, 0.0F);
    setRotateAngle(this.tail2, -0.034906585F, -0.0F, 0.0F);
    this.saddleside3 = new ModelRenderer((Model)this, 50, 64);
    this.saddleside3.setRotationPoint(5.2F, -0.8F, -0.6F);
    this.saddleside3.addBox(-1.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
    setRotateAngle(this.saddleside3, 0.05235988F, 0.10471976F, 0.13962634F);
    this.saddle = new ModelRenderer((Model)this, 115, 0);
    this.saddle.setRotationPoint(0.0F, 12.7F, -5.0F);
    this.saddle.addBox(-5.5F, -1.0F, -5.5F, 11.0F, 1.0F, 21.0F, 0.0F);
    setRotateAngle(this.saddle, -0.05235988F, -0.0F, 0.0F);
    this.neck3 = new ModelRenderer((Model)this, 78, 30);
    this.neck3.setRotationPoint(0.0F, -1.6F, -3.0F);
    this.neck3.addBox(-3.5F, -2.5F, 0.0F, 7.0F, 5.0F, 5.0F, 0.0F);
    setRotateAngle(this.neck3, -0.4886922F, 0.0F, 0.0F);
    this.body2 = new ModelRenderer((Model)this, 0, 23);
    this.body2.setRotationPoint(0.0F, 18.4F, 5.3F);
    this.body2.addBox(-6.0F, -5.0F, -4.5F, 12.0F, 10.0F, 9.0F, 0.0F);
    setRotateAngle(this.body2, 0.034906585F, -0.0F, 0.0F);
    this.rightgill = new ModelRenderer((Model)this, 0, 61);
    this.rightgill.setRotationPoint(-2.2F, -1.0F, 0.0F);
    this.rightgill.addBox(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
    setRotateAngle(this.rightgill, -0.17732546F, 0.3064798F, -0.93322754F);
    this.tail6 = new ModelRenderer((Model)this, 47, 42);
    this.tail6.setRotationPoint(0.0F, 0.0F, 2.5F);
    this.tail6.addBox(0.0F, -2.5F, -2.5F, 0.0F, 7.0F, 7.0F, 0.0F);
    setRotateAngle(this.tail6, 0.6981317F, -0.0F, 0.0F);
    this.body1 = new ModelRenderer((Model)this, 0, 0);
    this.body1.setRotationPoint(0.0F, 18.3F, -4.0F);
    this.body1.addBox(-6.0F, -5.5F, -5.5F, 12.0F, 11.0F, 11.0F, 0.0F);
    setRotateAngle(this.body1, -0.06981317F, -0.0F, 0.0F);
    this.tail4 = new ModelRenderer((Model)this, 47, 37);
    this.tail4.setRotationPoint(0.0F, 0.0F, 3.0F);
    this.tail4.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F);
    setRotateAngle(this.tail4, -0.034906585F, -0.0F, 0.0F);
    this.neck2 = new ModelRenderer((Model)this, 78, 0);
    this.neck2.setRotationPoint(0.0F, -3.0F, -3.5F);
    this.neck2.addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 7.0F, 0.0F);
    setRotateAngle(this.neck2, -0.6981317F, -0.0F, 0.0F);
    this.head2 = new ModelRenderer((Model)this, 0, 52);
    this.head2.setRotationPoint(0.0F, 0.0F, -0.5F);
    this.head2.addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 3.0F, 0.0F);
    setRotateAngle(this.head2, 0.034906585F, -0.0F, 0.0F);
    this.rightfin2 = new ModelRenderer((Model)this, 99, 49);
    this.rightfin2.setRotationPoint(-4.4F, 21.3F, 8.0F);
    this.rightfin2.addBox(-6.0F, -0.5F, -5.0F, 6.0F, 1.0F, 5.0F, 0.0F);
    setRotateAngle(this.rightfin2, -0.24228175F, 0.72677505F, -0.47536334F);
    this.leftfin2 = new ModelRenderer((Model)this, 99, 49);
    this.leftfin2.setRotationPoint(4.4F, 21.3F, 8.0F);
    this.leftfin2.addBox(0.0F, -0.5F, -5.0F, 6.0F, 1.0F, 5.0F, 0.0F);
    setRotateAngle(this.leftfin2, -0.24228175F, -0.72677505F, 0.47536334F);
    this.mout = new ModelRenderer((Model)this, 19, 52);
    this.mout.setRotationPoint(0.0F, 0.0F, -4.0F);
    this.mout.addBox(-3.0F, -2.5F, -4.0F, 6.0F, 5.0F, 4.0F, 0.0F);
    this.saddlemiddle = new ModelRenderer((Model)this, 100, 78);
    this.saddlemiddle.setRotationPoint(-0.2F, -0.9F, 4.5F);
    this.saddlemiddle.addBox(-5.0F, -5.0F, -1.0F, 11.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.saddlemiddle, -0.08726646F, -0.0F, 0.0F);
    this.saddleside4 = new ModelRenderer((Model)this, 75, 64);
    this.saddleside4.setRotationPoint(5.2F, -1.1F, 9.9F);
    this.saddleside4.addBox(-1.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
    setRotateAngle(this.saddleside4, 0.017453292F, -0.10367256F, 0.14032447F);
    this.head1 = new ModelRenderer((Model)this, 0, 43);
    this.head1.setRotationPoint(0.0F, -0.5F, 0.5F);
    this.head1.addBox(-3.0F, -5.0F, -1.0F, 6.0F, 5.0F, 3.0F, 0.0F);
    setRotateAngle(this.head1, 1.7976891F, -0.0F, 0.0F);
    this.lefteye = new ModelRenderer((Model)this, 40, 57);
    this.lefteye.setRotationPoint(2.5F, -4.5F, -1.5F);
    this.lefteye.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.lefteye, -0.08726646F, -0.0F, 0.0F);
    this.saddleback = new ModelRenderer((Model)this, 100, 71);
    this.saddleback.setRotationPoint(0.4F, -1.3F, 15.6F);
    this.saddleback.addBox(-4.5F, -5.0F, -1.0F, 9.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.saddleback, 0.017453292F, -0.0F, 0.0F);
    this.righteye = new ModelRenderer((Model)this, 40, 57);
    this.righteye.setRotationPoint(-2.5F, -4.5F, -1.5F);
    this.righteye.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.righteye, -0.08726646F, -0.0F, 0.0F);
    this.neck1 = new ModelRenderer((Model)this, 78, 14);
    this.neck1.setRotationPoint(0.0F, 16.0F, -13.8F);
    this.neck1.addBox(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 6.0F, 0.0F);
    setRotateAngle(this.neck1, -0.34906584F, -0.0F, 0.0F);
    this.saddleside1 = new ModelRenderer((Model)this, 0, 64);
    this.saddleside1.setRotationPoint(-5.0F, -0.9F, -0.7F);
    this.saddleside1.addBox(0.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
    setRotateAngle(this.saddleside1, 0.05235988F, -0.05235988F, -0.13962634F);
    this.tail3 = new ModelRenderer((Model)this, 47, 28);
    this.tail3.setRotationPoint(0.0F, 0.0F, 4.5F);
    this.tail3.addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 3.0F, 0.0F);
    setRotateAngle(this.tail3, -0.06981317F, -0.0F, 0.0F);
    this.belt1 = new ModelRenderer((Model)this, 152, 64);
    this.belt1.setRotationPoint(0.0F, 12.8F, 6.9F);
    this.belt1.addBox(-6.5F, 0.0F, -1.0F, 13.0F, 11.0F, 1.0F, 0.0F);
    setRotateAngle(this.belt1, -0.08726646F, -0.0F, 0.0F);
    this.head3 = new ModelRenderer((Model)this, 19, 43);
    this.head3.setRotationPoint(0.0F, -2.5F, -2.5F);
    this.head3.addBox(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 4.0F, 0.0F);
    setRotateAngle(this.head3, 0.13962634F, -0.0F, 0.0F);
    this.tail5 = new ModelRenderer((Model)this, 47, 44);
    this.tail5.setRotationPoint(0.0F, 0.0F, 3.0F);
    this.tail5.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F);
    setRotateAngle(this.tail5, -0.08726646F, -0.0F, 0.0F);
    this.belt2 = new ModelRenderer((Model)this, 152, 77);
    this.belt2.setRotationPoint(0.0F, 12.3F, -4.1F);
    this.belt2.addBox(-6.5F, 0.0F, -1.0F, 13.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.belt2, -0.08726646F, -0.0F, 0.0F);
    this.saddlefront = new ModelRenderer((Model)this, 100, 64);
    this.saddlefront.setRotationPoint(0.0F, -0.7F, -5.0F);
    this.saddlefront.addBox(-5.0F, -5.0F, -1.0F, 10.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.saddlefront, 0.08726646F, -0.0F, 0.0F);
    this.leftfin1 = new ModelRenderer((Model)this, 99, 41);
    this.leftfin1.setRotationPoint(3.9F, 20.3F, -6.0F);
    this.leftfin1.addBox(0.0F, -0.5F, -6.0F, 8.0F, 1.0F, 6.0F, 0.0F);
    setRotateAngle(this.leftfin1, -0.19221127F, -0.6485391F, 0.4435539F);
    this.mane = new ModelRenderer((Model)this, 67, 29);
    this.mane.setRotationPoint(0.0F, -7.5F, 1.0F);
    this.mane.addBox(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 4.0F, 0.0F);
    setRotateAngle(this.mane, -0.17453292F, -0.0F, 0.0F);
    this.neck4 = new ModelRenderer((Model)this, 78, 41);
    this.neck4.setRotationPoint(0.0F, -0.8F, -3.0F);
    this.neck4.addBox(-3.0F, -2.5F, 0.0F, 6.0F, 5.0F, 4.0F, 0.0F);
    setRotateAngle(this.neck4, -0.20943952F, -0.0F, 0.0F);
    this.tail1 = new ModelRenderer((Model)this, 47, 0);
    this.tail1.setRotationPoint(0.0F, 18.0F, 9.5F);
    this.tail1.addBox(-5.0F, -4.5F, 0.0F, 10.0F, 9.0F, 5.0F, 0.0F);
    setRotateAngle(this.tail1, 0.08726646F, -0.0F, 0.0F);
    this.leftgill = new ModelRenderer((Model)this, 0, 61);
    this.leftgill.setRotationPoint(2.2F, -1.0F, 0.0F);
    this.leftgill.addBox(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
    setRotateAngle(this.leftgill, -0.17732546F, -0.3064798F, 0.93322754F);
    this.saddle.addChild(this.saddleside2);
    this.tail1.addChild(this.tail2);
    this.saddle.addChild(this.saddleside3);
    this.neck2.addChild(this.neck3);
    this.head2.addChild(this.rightgill);
    this.tail5.addChild(this.tail6);
    this.tail3.addChild(this.tail4);
    this.neck1.addChild(this.neck2);
    this.head1.addChild(this.head2);
    this.head3.addChild(this.mout);
    this.saddle.addChild(this.saddlemiddle);
    this.saddle.addChild(this.saddleside4);
    this.neck4.addChild(this.head1);
    this.head2.addChild(this.lefteye);
    this.saddle.addChild(this.saddleback);
    this.head2.addChild(this.righteye);
    this.saddle.addChild(this.saddleside1);
    this.tail2.addChild(this.tail3);
    this.head2.addChild(this.head3);
    this.tail4.addChild(this.tail5);
    this.saddle.addChild(this.saddlefront);
    this.head1.addChild(this.mane);
    this.neck3.addChild(this.neck4);
    this.head2.addChild(this.leftgill);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  
  public void renderSaddle(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.saddle.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.belt1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.belt2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.tail1.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    this.tail2.rotateAngleY = 0.2F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    this.tail3.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    this.tail4.rotateAngleY = 0.2F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    this.tail5.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    
    this.leftfin1.rotateAngleY = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
    this.leftfin1.rotateAngleX = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
    this.leftfin2.rotateAngleY = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
    this.leftfin2.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
    
    this.rightfin1.rotateAngleY = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
    this.rightfin1.rotateAngleX = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
    this.rightfin2.rotateAngleY = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
    this.rightfin2.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.15F);

    
    if (ageInTicks % 300.0F > 0.0F && ageInTicks % 300.0F < 50.0F) {
      this.neck4.rotateAngleZ = 0.4F * MathHelper.cos(ageInTicks * 0.25F);
    } else {
      this.neck4.rotateAngleZ = (float)Math.toDegrees(0.0D);
    } 
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


