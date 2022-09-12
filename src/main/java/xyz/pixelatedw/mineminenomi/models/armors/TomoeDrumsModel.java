package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class TomoeDrumsModel<T extends LivingEntity>
  extends BipedModel<T> {
  private final ModelRenderer tomoe_drums;
  private final ModelRenderer bars;
  private final ModelRenderer Bar10_r1;
  private final ModelRenderer Bar9_r1;
  private final ModelRenderer Bar8_r1;
  private final ModelRenderer Bar7_r1;
  private final ModelRenderer Bar6_r1;
  private final ModelRenderer Bar5_r1;
  private final ModelRenderer Bar4_r1;
  private final ModelRenderer Bar3_r1;
  private final ModelRenderer Bar2_r1;
  private final ModelRenderer Bar1_r1;
  private final ModelRenderer Bar10;
  private final ModelRenderer Bar8;
  private final ModelRenderer drums;
  private final ModelRenderer Drum1;
  private final ModelRenderer Drum2;
  private final ModelRenderer Drum3;
  private final ModelRenderer Drum4;
  
  public TomoeDrumsModel() {
    super(1.0F);
    this.textureWidth = 32;
    this.textureHeight = 32;
    
    this.tomoe_drums = new ModelRenderer((Model)this);
    this.tomoe_drums.setRotationPoint(0.0F, 1.0F, 3.0F);
    
    this.bars = new ModelRenderer((Model)this);
    this.bars.setRotationPoint(0.0F, 4.0F, -1.0F);
    this.tomoe_drums.addChild(this.bars);
    this.bars.setTextureOffset(0, 0).addBox(-1.5F, -18.1F, 3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar10_r1 = new ModelRenderer((Model)this);
    this.Bar10_r1.setRotationPoint(-23.0F, -46.0F, 1.0F);
    this.bars.addChild(this.Bar10_r1);
    setRotationAngle(this.Bar10_r1, 0.0F, 0.0F, 2.7925F);
    this.Bar10_r1.setTextureOffset(0, 0).addBox(-10.7F, -34.6F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar9_r1 = new ModelRenderer((Model)this);
    this.Bar9_r1.setRotationPoint(21.0F, -12.75F, 1.0F);
    this.bars.addChild(this.Bar9_r1);
    setRotationAngle(this.Bar9_r1, 0.0F, 0.0F, -2.7925F);
    this.Bar9_r1.setTextureOffset(0, 0).addBox(15.1792F, -2.65F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar8_r1 = new ModelRenderer((Model)this);
    this.Bar8_r1.setRotationPoint(-38.0F, -20.75F, 1.0F);
    this.bars.addChild(this.Bar8_r1);
    setRotationAngle(this.Bar8_r1, 0.0F, 0.0F, 2.0944F);
    this.Bar8_r1.setTextureOffset(0, 0).addBox(-12.0F, -30.5F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar7_r1 = new ModelRenderer((Model)this);
    this.Bar7_r1.setRotationPoint(38.0F, -20.25F, 1.0F);
    this.bars.addChild(this.Bar7_r1);
    setRotationAngle(this.Bar7_r1, 0.0F, 0.0F, -2.0944F);
    this.Bar7_r1.setTextureOffset(0, 0).addBox(7.7F, -30.5F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar6_r1 = new ModelRenderer((Model)this);
    this.Bar6_r1.setRotationPoint(-34.25F, 1.25F, 1.0F);
    this.bars.addChild(this.Bar6_r1);
    setRotationAngle(this.Bar6_r1, 0.0F, 0.0F, 1.5708F);
    this.Bar6_r1.setTextureOffset(0, 0).addBox(-13.25F, -26.25F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar5_r1 = new ModelRenderer((Model)this);
    this.Bar5_r1.setRotationPoint(34.75F, -1.0F, 1.0F);
    this.bars.addChild(this.Bar5_r1);
    setRotationAngle(this.Bar5_r1, 0.0F, 0.0F, -1.6581F);
    this.Bar5_r1.setTextureOffset(0, 0).addBox(8.25F, -26.1F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar4_r1 = new ModelRenderer((Model)this);
    this.Bar4_r1.setRotationPoint(19.5F, 14.5F, 1.0F);
    this.bars.addChild(this.Bar4_r1);
    setRotationAngle(this.Bar4_r1, 0.0F, 0.0F, -0.8727F);
    this.Bar4_r1.setTextureOffset(0, 0).addBox(5.0F, -23.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar3_r1 = new ModelRenderer((Model)this);
    this.Bar3_r1.setRotationPoint(-19.5F, 14.75F, 1.0F);
    this.bars.addChild(this.Bar3_r1);
    setRotationAngle(this.Bar3_r1, 0.0F, 0.0F, 0.8727F);
    this.Bar3_r1.setTextureOffset(0, 0).addBox(-10.0F, -23.0F, 2.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar2_r1 = new ModelRenderer((Model)this);
    this.Bar2_r1.setRotationPoint(-10.0F, 17.25F, 1.0F);
    this.bars.addChild(this.Bar2_r1);
    setRotationAngle(this.Bar2_r1, 0.0F, 0.0873F, 0.4887F);
    this.Bar2_r1.setTextureOffset(0, 0).addBox(-5.9564F, -21.0F, 1.5019F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar1_r1 = new ModelRenderer((Model)this);
    this.Bar1_r1.setRotationPoint(10.0F, 17.0F, 1.0F);
    this.bars.addChild(this.Bar1_r1);
    setRotationAngle(this.Bar1_r1, 0.0F, -0.0873F, -0.4887F);
    this.Bar1_r1.setTextureOffset(0, 0).addBox(0.9564F, -21.0F, 1.5019F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.Bar10 = new ModelRenderer((Model)this);
    this.Bar10.setRotationPoint(-5.7F, -15.4F, 0.0F);
    this.bars.addChild(this.Bar10);
    setRotationAngle(this.Bar10, 0.0F, 0.0F, 2.7925F);
    
    this.Bar8 = new ModelRenderer((Model)this);
    this.Bar8.setRotationPoint(-7.7F, -11.5F, 0.0F);
    this.bars.addChild(this.Bar8);
    setRotationAngle(this.Bar8, 0.0F, 0.0F, 2.0944F);
    
    this.drums = new ModelRenderer((Model)this);
    this.drums.setRotationPoint(0.0F, 2.0F, -1.0F);
    this.tomoe_drums.addChild(this.drums);
    
    this.Drum1 = new ModelRenderer((Model)this);
    this.Drum1.setRotationPoint(-10.0F, -7.0F, -1.0F);
    this.drums.addChild(this.Drum1);
    this.Drum1.setTextureOffset(0, 5).addBox(0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.Drum2 = new ModelRenderer((Model)this);
    this.Drum2.setRotationPoint(7.0F, -7.0F, -1.0F);
    this.drums.addChild(this.Drum2);
    this.Drum2.setTextureOffset(0, 5).addBox(0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.Drum3 = new ModelRenderer((Model)this);
    this.Drum3.setRotationPoint(-8.0F, -15.0F, -1.0F);
    this.drums.addChild(this.Drum3);
    this.Drum3.setTextureOffset(0, 5).addBox(0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.Drum4 = new ModelRenderer((Model)this);
    this.Drum4.setRotationPoint(4.0F, -15.0F, -1.0F);
    this.drums.addChild(this.Drum4);
    this.Drum4.setTextureOffset(0, 5).addBox(0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.tomoe_drums.copyModelAngles(this.bipedBody);
    this.tomoe_drums.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


