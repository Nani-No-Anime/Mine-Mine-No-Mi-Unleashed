package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class AcesHatModel<T extends LivingEntity>
  extends BipedModel<T> {
  private final ModelRenderer hat;
  private final ModelRenderer base_0;
  private final ModelRenderer base_1;
  private final ModelRenderer mid_0;
  private final ModelRenderer symbols;
  private final ModelRenderer symb_1_r1;
  private final ModelRenderer beads_0;
  private final ModelRenderer beads_1;
  private final ModelRenderer beads_2;
  private final ModelRenderer beads_3;
  private final ModelRenderer right_string;
  private final ModelRenderer right_string_0;
  private final ModelRenderer right_string_1;
  private final ModelRenderer right_string_2;
  private final ModelRenderer left_string;
  private final ModelRenderer left_string_0;
  private final ModelRenderer left_string_1;
  private final ModelRenderer left_string_2;
  private final ModelRenderer ornament;
  
  public AcesHatModel() {
    super(1.0F);
    this.textureWidth = 80;
    this.textureHeight = 80;
    
    this.hat = new ModelRenderer((Model)this);
    this.hat.setRotationPoint(0.0F, 1.0F, 0.0F);

    
    this.base_0 = new ModelRenderer((Model)this);
    this.base_0.setRotationPoint(0.0F, -6.0F, 0.0F);
    this.hat.addChild(this.base_0);
    this.base_0.setTextureOffset(0, 0).addBox(-6.0F, -1.01F, -7.0F, 12.0F, 1.0F, 14.0F, 0.0F, false);
    this.base_0.setTextureOffset(0, 16).addBox(-7.0F, -1.0F, -6.0F, 14.0F, 1.0F, 12.0F, 0.0F, false);
    
    this.base_1 = new ModelRenderer((Model)this);
    this.base_1.setRotationPoint(1.0F, -7.0F, -1.0F);
    this.hat.addChild(this.base_1);
    this.base_1.setTextureOffset(0, 30).addBox(-6.0F, -1.0F, -5.0F, 10.0F, 1.0F, 12.0F, 0.0F, false);
    this.base_1.setTextureOffset(33, 30).addBox(-7.0F, -1.0F, -4.0F, 12.0F, 1.0F, 10.0F, 0.0F, false);
    
    this.mid_0 = new ModelRenderer((Model)this);
    this.mid_0.setRotationPoint(0.0F, -6.0F, 0.0F);
    this.hat.addChild(this.mid_0);
    this.mid_0.setTextureOffset(39, 0).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
    this.mid_0.setTextureOffset(41, 16).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
    this.mid_0.setTextureOffset(41, 42).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
    
    this.symbols = new ModelRenderer((Model)this);
    this.symbols.setRotationPoint(0.0F, -6.0F, 0.0F);
    this.hat.addChild(this.symbols);

    
    this.symb_1_r1 = new ModelRenderer((Model)this);
    this.symb_1_r1.setRotationPoint(1.5F, -3.0F, -4.5F);
    this.symbols.addChild(this.symb_1_r1);
    setRotationAngle(this.symb_1_r1, -0.3927F, 0.0F, 0.0F);
    this.symb_1_r1.setTextureOffset(5, 24).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
    this.symb_1_r1.setTextureOffset(5, 36).addBox(-4.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.beads_0 = new ModelRenderer((Model)this);
    this.beads_0.setRotationPoint(0.5F, -6.0F, 0.0F);
    this.hat.addChild(this.beads_0);
    this.beads_0.setTextureOffset(44, 10).addBox(2.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_0.setTextureOffset(35, 44).addBox(0.5F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_0.setTextureOffset(30, 44).addBox(-1.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_0.setTextureOffset(25, 44).addBox(-2.5F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_0.setTextureOffset(20, 44).addBox(-4.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.beads_1 = new ModelRenderer((Model)this);
    this.beads_1.setRotationPoint(0.5F, -6.0F, 9.5F);
    this.hat.addChild(this.beads_1);
    this.beads_1.setTextureOffset(15, 44).addBox(2.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_1.setTextureOffset(10, 44).addBox(0.5F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_1.setTextureOffset(5, 44).addBox(-1.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_1.setTextureOffset(0, 44).addBox(-2.5F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_1.setTextureOffset(41, 25).addBox(-4.0F, -3.0F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.beads_2 = new ModelRenderer((Model)this);
    this.beads_2.setRotationPoint(-4.5F, -6.0F, 0.5F);
    this.hat.addChild(this.beads_2);
    setRotationAngle(this.beads_2, 0.0F, -1.5708F, 0.0F);
    this.beads_2.setTextureOffset(41, 19).addBox(2.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_2.setTextureOffset(41, 16).addBox(0.5F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_2.setTextureOffset(39, 10).addBox(-1.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_2.setTextureOffset(39, 3).addBox(-2.5F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_2.setTextureOffset(39, 0).addBox(-4.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.beads_3 = new ModelRenderer((Model)this);
    this.beads_3.setRotationPoint(5.0F, -6.0F, 0.5F);
    this.hat.addChild(this.beads_3);
    setRotationAngle(this.beads_3, 0.0F, -1.5708F, 0.0F);
    this.beads_3.setTextureOffset(38, 36).addBox(2.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_3.setTextureOffset(38, 33).addBox(0.5F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_3.setTextureOffset(38, 30).addBox(-1.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_3.setTextureOffset(33, 30).addBox(-2.5F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.beads_3.setTextureOffset(9, 9).addBox(-4.0F, -3.0F, -0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.right_string = new ModelRenderer((Model)this);
    this.right_string.setRotationPoint(-5.0F, -6.0F, -3.0F);
    this.hat.addChild(this.right_string);

    
    this.right_string_0 = new ModelRenderer((Model)this);
    this.right_string_0.setRotationPoint(-0.5F, 0.0F, 1.5F);
    this.right_string.addChild(this.right_string_0);
    setRotationAngle(this.right_string_0, -0.3927F, 0.0F, -0.0873F);
    this.right_string_0.setTextureOffset(0, 36).addBox(-0.5048F, -0.2181F, -0.5F, 1.0F, 4.0F, 1.0F, -0.2F, false);
    
    this.right_string_1 = new ModelRenderer((Model)this);
    this.right_string_1.setRotationPoint(-0.0048F, 3.5319F, 0.0F);
    this.right_string_0.addChild(this.right_string_1);
    setRotationAngle(this.right_string_1, -0.1309F, 0.0F, -0.1309F);
    this.right_string_1.setTextureOffset(33, 33).addBox(-0.5F, -0.25F, -0.5F, 1.0F, 4.0F, 1.0F, -0.2F, false);
    
    this.right_string_2 = new ModelRenderer((Model)this);
    this.right_string_2.setRotationPoint(0.0F, 3.5F, 0.0F);
    this.right_string_1.addChild(this.right_string_2);
    setRotationAngle(this.right_string_2, 0.2182F, 0.0F, -0.3927F);
    this.right_string_2.setTextureOffset(5, 0).addBox(-0.5F, -0.25F, -0.5F, 1.0F, 8.0F, 1.0F, -0.2F, false);
    
    this.left_string = new ModelRenderer((Model)this);
    this.left_string.setRotationPoint(5.0F, -6.0F, -3.0F);
    this.hat.addChild(this.left_string);

    
    this.left_string_0 = new ModelRenderer((Model)this);
    this.left_string_0.setRotationPoint(0.5F, 0.0F, 1.5F);
    this.left_string.addChild(this.left_string_0);
    setRotationAngle(this.left_string_0, -0.3927F, 0.0F, 0.0873F);
    this.left_string_0.setTextureOffset(5, 30).addBox(-0.5048F, -0.2181F, -0.5F, 1.0F, 4.0F, 1.0F, -0.2F, false);
    
    this.left_string_1 = new ModelRenderer((Model)this);
    this.left_string_1.setRotationPoint(-0.0048F, 3.5319F, 0.0F);
    this.left_string_0.addChild(this.left_string_1);
    setRotationAngle(this.left_string_1, -0.1309F, 0.0F, 0.1309F);
    this.left_string_1.setTextureOffset(0, 30).addBox(-0.5F, -0.25F, -0.5F, 1.0F, 4.0F, 1.0F, -0.2F, false);
    
    this.left_string_2 = new ModelRenderer((Model)this);
    this.left_string_2.setRotationPoint(0.0F, 3.5F, 0.0F);
    this.left_string_1.addChild(this.left_string_2);
    setRotationAngle(this.left_string_2, 0.2182F, 0.0F, 0.3927F);
    this.left_string_2.setTextureOffset(0, 0).addBox(-0.5F, -0.25F, -0.5F, 1.0F, 8.0F, 1.0F, -0.2F, false);
    
    this.ornament = new ModelRenderer((Model)this);
    this.ornament.setRotationPoint(0.0F, 6.0F, -6.0F);
    this.hat.addChild(this.ornament);
    this.ornament.setTextureOffset(5, 20).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
    this.ornament.setTextureOffset(0, 20).addBox(-0.5F, -1.0F, -0.75F, 1.0F, 5.0F, 1.0F, -0.1F, false);
    this.ornament.setTextureOffset(0, 16).addBox(-1.0F, 1.25F, -1.25F, 2.0F, 1.0F, 2.0F, -0.3F, false);
    this.ornament.setTextureOffset(0, 10).addBox(-1.0F, 2.5F, -1.25F, 2.0F, 1.0F, 2.0F, -0.3F, false);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.hat.copyModelAngles(this.bipedHead);
    this.hat.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


