package xyz.pixelatedw.mineminenomi.wypi.abilities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EntityArmModel
  extends EntityModel
{
  public final ModelRenderer entityArm;
  
  public EntityArmModel() {
    this(false);
  }

  
  public EntityArmModel(boolean smallArms) {
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.entityArm = new ModelRenderer((Model)this);
    this.entityArm.setRotationPoint(0.0F, 0.0F, 0.0F);
    setRotationAngle(this.entityArm, -1.5708F, 0.0F, 0.0F);
    if (smallArms) {
      this.entityArm.setTextureOffset(40, 16).addBox(-1.0F, -6.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, true);
    } else {
      this.entityArm.setTextureOffset(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
    } 
  }

  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.entityArm.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


