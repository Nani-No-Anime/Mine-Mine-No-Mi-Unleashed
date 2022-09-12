package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.FightingFishEntity;

public class FightingFishModel<T extends FightingFishEntity>
  extends EntityModel<T> {
  private final ModelRenderer head;
  private final ModelRenderer nose_r1;
  private final ModelRenderer eyebrow2_r1;
  private final ModelRenderer eyebrow1_r1;
  private final ModelRenderer body;
  private final ModelRenderer horn2;
  private final ModelRenderer Horn5_r1;
  private final ModelRenderer Horn4_r1;
  private final ModelRenderer Horn3_r1;
  private final ModelRenderer Horn2_r1;
  private final ModelRenderer Horn1_r1;
  private final ModelRenderer horn1;
  private final ModelRenderer Horn5_r2;
  private final ModelRenderer Horn4_r2;
  private final ModelRenderer Horn3_r2;
  private final ModelRenderer Horn2_r2;
  private final ModelRenderer Horn1_r2;
  private final ModelRenderer topFins;
  private final ModelRenderer Fin3_r1;
  private final ModelRenderer Fin2_r1;
  private final ModelRenderer flippers1;
  private final ModelRenderer flippers2;
  private final ModelRenderer tail;
  
  public FightingFishModel() {
    this.textureWidth = 288;
    this.textureHeight = 288;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.head.setTextureOffset(109, 0).addBox(-17.0F, -63.0F, -17.0F, 34.0F, 48.0F, 10.0F, 0.0F, false);
    this.head.setTextureOffset(75, 160).addBox(-16.0F, -62.0F, -25.0F, 32.0F, 46.0F, 8.0F, 0.0F, false);
    this.head.setTextureOffset(198, 0).addBox(-15.0F, -58.0F, -33.0F, 30.0F, 38.0F, 8.0F, 0.0F, false);
    this.head.setTextureOffset(184, 259).addBox(-13.5F, -36.0F, -38.0F, 27.0F, 11.0F, 5.0F, 0.0F, false);
    
    this.nose_r1 = new ModelRenderer((Model)this);
    this.nose_r1.setRotationPoint(1.0F, -45.0F, -33.0F);
    this.head.addChild(this.nose_r1);
    setRotationAngle(this.nose_r1, -0.48F, 0.0F, 0.0F);
    this.nose_r1.setTextureOffset(257, 267).addBox(-4.0F, -1.0F, 0.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);
    
    this.eyebrow2_r1 = new ModelRenderer((Model)this);
    this.eyebrow2_r1.setRotationPoint(13.0F, -58.0F, -35.0F);
    this.head.addChild(this.eyebrow2_r1);
    setRotationAngle(this.eyebrow2_r1, -0.2967F, 0.0F, -0.2182F);
    this.eyebrow2_r1.setTextureOffset(250, 248).addBox(-13.0F, 0.0F, 0.0F, 13.0F, 8.0F, 6.0F, 0.0F, false);
    
    this.eyebrow1_r1 = new ModelRenderer((Model)this);
    this.eyebrow1_r1.setRotationPoint(-13.0F, -58.0F, -35.0F);
    this.head.addChild(this.eyebrow1_r1);
    setRotationAngle(this.eyebrow1_r1, -0.2967F, 0.0F, 0.2182F);
    this.eyebrow1_r1.setTextureOffset(250, 248).addBox(0.0F, 0.0F, 0.0F, 13.0F, 8.0F, 6.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.body.setTextureOffset(83, 83).addBox(-18.0F, -64.0F, -7.0F, 36.0F, 50.0F, 26.0F, 0.0F, false);
    this.body.setTextureOffset(0, 105).addBox(-17.0F, -63.0F, 19.0F, 34.0F, 48.0F, 7.0F, 0.0F, false);
    this.body.setTextureOffset(192, 53).addBox(-16.0F, -62.0F, 26.0F, 32.0F, 46.0F, 6.0F, 0.0F, false);
    this.body.setTextureOffset(0, 161).addBox(-13.0F, -59.0F, 32.0F, 26.0F, 40.0F, 9.0F, 0.0F, false);
    this.body.setTextureOffset(55, 0).addBox(-7.0F, -54.0F, 41.0F, 14.0F, 30.0F, 9.0F, 0.0F, false);
    
    this.horn2 = new ModelRenderer((Model)this);
    this.horn2.setRotationPoint(-6.0F, 22.0F, -1.0F);
    setRotationAngle(this.horn2, 0.3927F, 0.3054F, 0.0F);

    
    this.Horn5_r1 = new ModelRenderer((Model)this);
    this.Horn5_r1.setRotationPoint(-26.1548F, -46.1359F, 0.7065F);
    this.horn2.addChild(this.Horn5_r1);
    setRotationAngle(this.Horn5_r1, -0.9599F, 0.0F, 0.0F);
    this.Horn5_r1.setTextureOffset(215, 215).addBox(17.8372F, -20.223F, -17.9248F, 10.0F, 11.0F, 13.0F, 0.0F, false);
    
    this.Horn4_r1 = new ModelRenderer((Model)this);
    this.Horn4_r1.setRotationPoint(-28.0F, -47.0F, -25.0F);
    this.horn2.addChild(this.Horn4_r1);
    setRotationAngle(this.Horn4_r1, 0.7418F, 0.0F, -0.1309F);
    this.Horn4_r1.setTextureOffset(25, 38).addBox(22.4127F, -19.896F, 12.5141F, 6.0F, 9.0F, 6.0F, 0.0F, false);
    
    this.Horn3_r1 = new ModelRenderer((Model)this);
    this.Horn3_r1.setRotationPoint(-26.0F, -45.5325F, -17.0188F);
    this.horn2.addChild(this.Horn3_r1);
    setRotationAngle(this.Horn3_r1, -0.0873F, 0.2182F, 0.0F);
    this.Horn3_r1.setTextureOffset(177, 232).addBox(17.4088F, -28.0357F, -3.7533F, 8.0F, 8.0F, 12.0F, 0.0F, false);
    
    this.Horn2_r1 = new ModelRenderer((Model)this);
    this.Horn2_r1.setRotationPoint(-27.0F, -50.4966F, -7.4925F);
    this.horn2.addChild(this.Horn2_r1);
    setRotationAngle(this.Horn2_r1, 0.5236F, 0.0F, 0.0F);
    this.Horn2_r1.setTextureOffset(29, 230).addBox(18.6824F, -21.7016F, 3.1475F, 10.0F, 8.0F, 12.0F, 0.0F, false);
    
    this.Horn1_r1 = new ModelRenderer((Model)this);
    this.Horn1_r1.setRotationPoint(-26.0F, -46.0F, 0.0F);
    this.horn2.addChild(this.Horn1_r1);
    setRotationAngle(this.Horn1_r1, -0.3491F, 0.0F, 0.0F);
    this.Horn1_r1.setTextureOffset(226, 106).addBox(18.6824F, -27.2156F, -15.4664F, 8.0F, 11.0F, 13.0F, 0.0F, false);
    
    this.horn1 = new ModelRenderer((Model)this);
    this.horn1.setRotationPoint(-28.0F, 22.0F, -1.0F);
    setRotationAngle(this.horn1, 0.3927F, -0.3054F, 0.0F);

    
    this.Horn5_r2 = new ModelRenderer((Model)this);
    this.Horn5_r2.setRotationPoint(26.1548F, -46.1359F, 0.7065F);
    this.horn1.addChild(this.Horn5_r2);
    setRotationAngle(this.Horn5_r2, -0.9599F, 0.0F, 0.0F);
    this.Horn5_r2.setTextureOffset(101, 215).addBox(4.5895F, -14.7305F, -26.547F, 10.0F, 11.0F, 13.0F, 0.0F, false);
    
    this.Horn4_r2 = new ModelRenderer((Model)this);
    this.Horn4_r2.setRotationPoint(28.0F, -47.0F, -25.0F);
    this.horn1.addChild(this.Horn4_r2);
    setRotationAngle(this.Horn4_r2, 0.7418F, 0.0F, 0.1309F);
    this.Horn4_r2.setTextureOffset(0, 38).addBox(3.226F, -32.2571F, 11.031F, 6.0F, 9.0F, 6.0F, 0.0F, false);
    
    this.Horn3_r2 = new ModelRenderer((Model)this);
    this.Horn3_r2.setRotationPoint(26.0F, -45.5325F, -17.0188F);
    this.horn1.addChild(this.Horn3_r2);
    setRotationAngle(this.Horn3_r2, -0.0873F, -0.2182F, 0.0F);
    this.Horn3_r2.setTextureOffset(136, 232).addBox(4.2045F, -30.517F, -20.2728F, 8.0F, 8.0F, 12.0F, 0.0F, false);
    
    this.Horn2_r2 = new ModelRenderer((Model)this);
    this.Horn2_r2.setRotationPoint(27.0F, -50.4966F, -7.4925F);
    this.horn1.addChild(this.Horn2_r2);
    setRotationAngle(this.Horn2_r2, 0.5236F, 0.0F, 0.0F);
    this.Horn2_r2.setTextureOffset(226, 131).addBox(3.7443F, -29.812F, -3.0758F, 10.0F, 8.0F, 12.0F, 0.0F, false);
    
    this.Horn1_r2 = new ModelRenderer((Model)this);
    this.Horn1_r2.setRotationPoint(26.0F, -46.0F, 0.0F);
    this.horn1.addChild(this.Horn1_r2);
    setRotationAngle(this.Horn1_r2, -0.3491F, 0.0F, 0.0F);
    this.Horn1_r2.setTextureOffset(215, 183).addBox(5.7443F, -27.6612F, -25.6796F, 8.0F, 11.0F, 13.0F, 0.0F, false);
    
    this.topFins = new ModelRenderer((Model)this);
    this.topFins.setRotationPoint(0.0F, 24.0F, 0.0F);
    setRotationAngle(this.topFins, 0.0873F, 0.0F, 0.0F);

    
    this.Fin3_r1 = new ModelRenderer((Model)this);
    this.Fin3_r1.setRotationPoint(-1.0F, -56.0F, 1.0F);
    this.topFins.addChild(this.Fin3_r1);
    setRotationAngle(this.Fin3_r1, 1.1345F, 0.0F, 0.0F);
    this.Fin3_r1.setTextureOffset(156, 160).addBox(0.0F, 7.3853F, 9.8288F, 0.0F, 42.0F, 29.0F, 0.0F, false);
    
    this.Fin2_r1 = new ModelRenderer((Model)this);
    this.Fin2_r1.setRotationPoint(-1.0F, -56.0F, 1.0F);
    this.topFins.addChild(this.Fin2_r1);
    setRotationAngle(this.Fin2_r1, 1.2217F, 0.0F, 0.0F);
    this.Fin2_r1.setTextureOffset(42, 186).addBox(0.0F, -1.0162F, 8.3144F, 0.0F, 14.0F, 29.0F, 0.0F, false);
    
    this.flippers1 = new ModelRenderer((Model)this);
    this.flippers1.setRotationPoint(3.0F, 3.0F, 4.0F);
    setRotationAngle(this.flippers1, 0.0F, 0.0F, 0.3927F);
    this.flippers1.setTextureOffset(186, 166).addBox(12.0F, -10.0F, -17.0F, 38.0F, 16.0F, 0.0F, 0.0F, false);
    
    this.flippers2 = new ModelRenderer((Model)this);
    this.flippers2.setRotationPoint(-37.0F, 3.0F, 4.0F);
    setRotationAngle(this.flippers2, 0.0F, 0.0F, -0.3927F);
    this.flippers2.setTextureOffset(109, 59).addBox(-18.5544F, 2.4588F, -17.0F, 38.0F, 16.0F, 0.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, -6.0F, 54.0F);
    this.tail.setTextureOffset(156, 160).addBox(-1.0F, -18.0F, -4.0F, 2.0F, 18.0F, 8.0F, 0.0F, false);
    this.tail.setTextureOffset(0, 0).addBox(0.0F, -28.0F, 4.0F, 0.0F, 50.0F, 54.0F, 0.0F, false);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.tail.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    this.flippers1.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    this.flippers2.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    this.topFins.rotateAngleX = 0.01F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
    this.topFins.rotateAngleY = 0.02F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.horn2.render(matrixStack, buffer, packedLight, packedOverlay);
    this.horn1.render(matrixStack, buffer, packedLight, packedOverlay);
    this.topFins.render(matrixStack, buffer, packedLight, packedOverlay);
    this.flippers1.render(matrixStack, buffer, packedLight, packedOverlay);
    this.flippers2.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


