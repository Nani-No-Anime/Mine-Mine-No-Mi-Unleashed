package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MedicBagModel<T extends LivingEntity>
  extends BipedModel<T> {
  public ModelRenderer backpack;
  public ModelRenderer backpack_2;
  public ModelRenderer backpack_strings;
  
  public MedicBagModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.backpack = new ModelRenderer((Model)this);
    this.backpack.setRotationPoint(0.0F, 3.7F, 2.0F);
    this.backpack.setTextureOffset(0, 0).addBox(-2.5F, 1.5F, 2.0F, 5.0F, 5.0F, 3.0F, 0.0F, false);
    
    this.backpack_2 = new ModelRenderer((Model)this);
    this.backpack_2.setRotationPoint(0.0F, 3.7F, 2.0F);
    this.backpack.addChild(this.backpack_2);
    this.backpack_2.setTextureOffset(0, 9).addBox(-3.5F, 0.5F, 0.0F, 7.0F, 4.0F, 4.0F, 0.0F, false);
    
    this.backpack_strings = new ModelRenderer((Model)this);
    this.backpack_strings.setRotationPoint(0.0F, 3.7F, 2.0F);
    this.backpack.addChild(this.backpack_strings);
    this.backpack_strings.setTextureOffset(20, 0).addBox(-5.0F, -4.0F, 0.1F, 10.0F, 9.0F, 0.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.backpack.copyModelAngles(this.bipedBody);
    this.backpack.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


