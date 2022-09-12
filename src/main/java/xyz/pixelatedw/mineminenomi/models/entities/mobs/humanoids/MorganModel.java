package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity;








public class MorganModel<T extends MorganEntity>
  extends HumanoidModel<T>
{
  public ModelRenderer head;
  public ModelRenderer body;
  public ModelRenderer rightarm1;
  public ModelRenderer leftarm1;
  public ModelRenderer rightleg;
  public ModelRenderer leftleg;
  public ModelRenderer jaw;
  public ModelRenderer rightarm2;
  public ModelRenderer axe_hand;
  
  public MorganModel() {
    this.textureWidth = 128;
    this.textureHeight = 64;
    this.axe51 = new ModelRenderer((Model)this, 105, 59);
    this.axe51.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe51.addBox(-1.5F, 12.1F, 5.8F, 1.0F, 3.0F, 2.0F, 0.0F);
    setRotateAngle(this.axe51, -0.41887903F, -0.0F, 0.0F);
    this.axe2 = new ModelRenderer((Model)this, 76, 53);
    this.axe2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe2.addBox(-2.0F, 10.0F, 9.5F, 2.0F, 7.0F, 4.0F, 0.0F);
    setRotateAngle(this.axe2, -0.9599311F, 0.0F, 0.0F);
    this.jaw = new ModelRenderer((Model)this, 25, 0);
    this.jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.jaw.addBox(-3.6F, -2.0F, -4.0F, 7.0F, 2.0F, 7.0F, 0.0F);
    this.leftarm1 = new ModelRenderer((Model)this, 21, 35);
    this.leftarm1.setRotationPoint(6.0F, -7.0F, 0.0F);
    this.leftarm1.addBox(-1.0F, -2.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
    setRotateAngle(this.leftarm1, 0.0F, -0.0F, -0.05235988F);
    this.body = new ModelRenderer((Model)this, 54, 0);
    this.body.setRotationPoint(0.0F, -9.0F, 0.0F);
    this.body.addBox(-5.0F, 0.0F, -2.5F, 10.0F, 16.0F, 5.0F, 0.0F);
    this.axe61 = new ModelRenderer((Model)this, 112, 59);
    this.axe61.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe61.addBox(-1.5F, 7.4F, 19.9F, 1.0F, 3.0F, 2.0F, 0.0F);
    setRotateAngle(this.axe61, -1.5358897F, -0.0F, 0.0F);
    this.axe41 = new ModelRenderer((Model)this, 100, 54);
    this.axe41.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe41.addBox(-1.5F, 9.0F, 13.5F, 1.0F, 9.0F, 1.0F, 0.0F);
    setRotateAngle(this.axe41, -0.9599311F, -0.0F, 0.0F);
    this.leftarm2 = new ModelRenderer((Model)this, 21, 50);
    this.leftarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftarm2.addBox(-0.9F, 6.9F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
    setRotateAngle(this.leftarm2, 0.0F, -0.0F, 0.017453292F);
    this.axe5 = new ModelRenderer((Model)this, 105, 52);
    this.axe5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe5.addBox(-2.0F, 12.1F, 2.5F, 2.0F, 2.0F, 4.0F, 0.0F);
    setRotateAngle(this.axe5, -0.41887903F, -0.0F, 0.0F);
    this.head = new ModelRenderer((Model)this, 0, 0);
    this.head.setRotationPoint(0.0F, -9.0F, 0.0F);
    this.head.addBox(-3.0F, -7.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F);
    this.leftleg = new ModelRenderer((Model)this, 0, 42);
    this.leftleg.setRotationPoint(2.5F, 7.0F, 0.0F);
    this.leftleg.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 17.0F, 5.0F, 0.0F);
    this.rightarm2 = new ModelRenderer((Model)this, 21, 14);
    this.rightarm2.mirror = true;
    this.rightarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightarm2.addBox(-4.0F, 3.2F, 3.9F, 5.0F, 7.0F, 5.0F, 0.0F);
    setRotateAngle(this.rightarm2, -0.9599311F, -0.0F, 0.0F);
    this.cable = new ModelRenderer((Model)this, 42, 47);
    this.cable.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.cable.addBox(-2.5F, 0.0F, 5.5F, 2.0F, 20.0F, 2.0F, 0.0F);
    setRotateAngle(this.cable, -0.9599311F, 0.0F, 0.0F);
    this.axe_hand = new ModelRenderer((Model)this, 0, 0);
    this.axe_hand.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe_hand.addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    setRotateAngle(this.axe_hand, 0.9599311F, 0.0F, 0.0F);
    this.rightarm1 = new ModelRenderer((Model)this, 0, 14);
    this.rightarm1.setRotationPoint(-6.0F, -7.0F, 0.0F);
    this.rightarm1.addBox(-4.0F, -2.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F);
    this.axe1 = new ModelRenderer((Model)this, 51, 51);
    this.axe1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe1.addBox(-4.5F, 10.0F, 3.5F, 6.0F, 7.0F, 6.0F, 0.0F);
    setRotateAngle(this.axe1, -0.9599311F, -0.0F, 0.0F);
    this.axe6 = new ModelRenderer((Model)this, 105, 52);
    this.axe6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe6.addBox(-2.0F, 8.6F, 16.6F, 2.0F, 2.0F, 4.0F, 0.0F);
    setRotateAngle(this.axe6, -1.5184364F, -0.0F, 0.0F);
    this.rightleg = new ModelRenderer((Model)this, 0, 42);
    this.rightleg.setRotationPoint(-2.5F, 7.0F, 0.0F);
    this.rightleg.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 17.0F, 5.0F, 0.0F);
    this.axe4 = new ModelRenderer((Model)this, 89, 52);
    this.axe4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.axe4.addBox(-2.0F, 9.0F, 10.5F, 2.0F, 9.0F, 3.0F, 0.0F);
    setRotateAngle(this.axe4, -0.9599311F, -0.0F, 0.0F);
    this.axe_hand.addChild(this.axe51);
    this.axe_hand.addChild(this.axe2);
    this.head.addChild(this.jaw);
    this.axe_hand.addChild(this.axe61);
    this.axe_hand.addChild(this.axe41);
    this.leftarm1.addChild(this.leftarm2);
    this.axe_hand.addChild(this.axe5);
    this.rightarm1.addChild(this.rightarm2);
    this.axe_hand.addChild(this.cable);
    this.rightarm2.addChild(this.axe_hand);
    this.axe_hand.addChild(this.axe1);
    this.axe_hand.addChild(this.axe6);
    this.axe_hand.addChild(this.axe4);
  }
  public ModelRenderer cable; public ModelRenderer axe1; public ModelRenderer axe2; public ModelRenderer axe4; public ModelRenderer axe41; public ModelRenderer axe5; public ModelRenderer axe51; public ModelRenderer axe6; public ModelRenderer axe61;
  public ModelRenderer leftarm2;
  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.bipedLeftLeg.showModel = false;
    this.bipedRightLeg.showModel = false;
    this.bipedLeftArm.showModel = false;
    this.bipedRightArm.showModel = true;
    this.bipedHead.showModel = false;
    this.bipedHeadwear.showModel = false;
    this.bipedBody.showModel = false;
    
    this.bipedRightArm = this.rightarm1;
    this.bipedRightArm.rotationPointY = -7.0F;
    this.bipedRightArm.rotationPointX = -6.0F;
    
    this.leftarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    
    this.jaw.rotationPointZ = 0.01F;
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
    this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.0F * limbSwingAmount;
    this.leftarm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


