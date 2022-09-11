package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;


@OnlyIn(Dist.CLIENT)
public class SkypieanModel<T extends OPEntity>
  extends HumanoidModel<T>
{
  private final ModelRenderer antennas;
  private final ModelRenderer antennas_2;
  private final ModelRenderer antenna_2_ball;
  private final ModelRenderer antennas_1;
  private final ModelRenderer antenna_1_ball;
  private final ModelRenderer wings;
  private final ModelRenderer wing_1;
  private final ModelRenderer wing_1_r1;
  private final ModelRenderer wing_2;
  private final ModelRenderer wing_2_r1;
  
  public SkypieanModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.antennas = new ModelRenderer((Model)this);
    this.antennas.setRotationPoint(0.0F, 28.0F, 0.0F);
    
    this.antennas_2 = new ModelRenderer((Model)this);
    this.antennas_2.setRotationPoint(3.0F, -5.5F, 0.0F);
    this.antennas.addChild(this.antennas_2);
    setRotationAngle(this.antennas_2, 0.0F, 0.0F, 0.5236F);
    this.antennas_2.setTextureOffset(0, 34).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.antenna_2_ball = new ModelRenderer((Model)this);
    this.antenna_2_ball.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.antennas_2.addChild(this.antenna_2_ball);
    this.antenna_2_ball.setTextureOffset(6, 35).addBox(-1.5F, -6.0F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.antennas_1 = new ModelRenderer((Model)this);
    this.antennas_1.setRotationPoint(-2.0F, -6.0F, 0.0F);
    this.antennas.addChild(this.antennas_1);
    setRotationAngle(this.antennas_1, 0.0F, 0.0F, -0.5236F);
    this.antennas_1.setTextureOffset(0, 34).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.antenna_1_ball = new ModelRenderer((Model)this);
    this.antenna_1_ball.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.antennas_1.addChild(this.antenna_1_ball);
    this.antenna_1_ball.setTextureOffset(6, 35).addBox(-1.5F, -6.0F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.wings = new ModelRenderer((Model)this);
    this.wings.setRotationPoint(0.0F, 27.0F, -1.0F);
    
    this.wing_1 = new ModelRenderer((Model)this);
    this.wing_1.setRotationPoint(-3.0F, 6.0F, 1.0F);
    this.wings.addChild(this.wing_1);
    setRotationAngle(this.wing_1, 0.0F, 0.0F, 0.1309F);
    
    this.wing_1_r1 = new ModelRenderer((Model)this);
    this.wing_1_r1.setRotationPoint(0.0F, -5.0F, 0.0F);
    this.wing_1.addChild(this.wing_1_r1);
    setRotationAngle(this.wing_1_r1, 0.0F, 0.1309F, 0.0F);
    this.wing_1_r1.setTextureOffset(16, 33).addBox(-11.0F, -1.0F, 1.0F, 11.0F, 6.0F, 0.0F, 0.0F, false);
    
    this.wing_2 = new ModelRenderer((Model)this);
    this.wing_2.setRotationPoint(3.0F, 6.0F, 2.0F);
    this.wings.addChild(this.wing_2);
    setRotationAngle(this.wing_2, 0.0F, 3.1416F, -0.1309F);
    
    this.wing_2_r1 = new ModelRenderer((Model)this);
    this.wing_2_r1.setRotationPoint(0.0F, -5.0F, 0.0F);
    this.wing_2.addChild(this.wing_2_r1);
    setRotationAngle(this.wing_2_r1, 0.0F, -0.1309F, 0.0F);
    this.wing_2_r1.setTextureOffset(16, 33).addBox(-11.0F, -1.0F, 0.0F, 11.0F, 6.0F, 0.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.antennas.copyModelAngles(this.bipedHead);
    this.antennas.render(matrixStack, buffer, packedLight, packedOverlay);
    this.wings.copyModelAngles(this.bipedBody);
    this.wings.render(matrixStack, buffer, packedLight, packedOverlay);
  }
}


