package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CaptainCapeModel<T extends LivingEntity>
  extends BipedModel<T> {
  public ModelRenderer cape;
  public ModelRenderer capeback;
  private ModelRenderer capeleftsholderpad2;
  private ModelRenderer capeleftsholderpad1;
  private ModelRenderer capeleftarm;
  private ModelRenderer capeleft;
  private ModelRenderer capefrontleft;
  private ModelRenderer caperightsholderpad2;
  private ModelRenderer caperightarm;
  private ModelRenderer caperightsholderpad1;
  private ModelRenderer caperight;
  private ModelRenderer capefrontright;
  private ModelRenderer caperightsholder;
  private ModelRenderer capeleftsholder;
  private ModelRenderer capeleftcollar1;
  private ModelRenderer capeleftcollar2;
  private ModelRenderer caperightcollar1;
  private ModelRenderer caperightcollar2;
  
  public CaptainCapeModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.cape = new ModelRenderer((Model)this);
    this.cape.setRotationPoint(0.0F, 0.5F, 0.0F);
    
    this.capeback = new ModelRenderer((Model)this);
    this.capeback.setRotationPoint(-0.5F, -0.5F, 2.5F);
    this.cape.addChild(this.capeback);
    this.capeback.setTextureOffset(5, 75).addBox(-8.0F, 0.0F, 0.0F, 17.0F, 22.0F, 0.0F, 0.0F, false);
    
    this.capeleftsholderpad2 = new ModelRenderer((Model)this);
    this.capeleftsholderpad2.setRotationPoint(6.4F, 0.0F, 0.0F);
    this.capeback.addChild(this.capeleftsholderpad2);
    this.capeleftsholderpad2.setTextureOffset(5, 106).addBox(0.0F, 0.0F, -5.5F, 5.0F, 3.0F, 6.0F, 0.0F, false);
    
    this.capeleftsholderpad1 = new ModelRenderer((Model)this);
    this.capeleftsholderpad1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.capeleftsholderpad2.addChild(this.capeleftsholderpad1);
    setRotateAngle(this.capeleftsholderpad1, 0.0F, 0.0F, 0.1745F);
    this.capeleftsholderpad1.setTextureOffset(5, 98).addBox(0.0F, -1.0F, -5.5F, 5.0F, 1.0F, 6.0F, 0.0F, false);
    
    this.capeleftarm = new ModelRenderer((Model)this);
    this.capeleftarm.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.capeleftsholderpad2.addChild(this.capeleftarm);
    this.capeleftarm.setTextureOffset(35, 98).addBox(2.6F, 1.0F, -4.5F, 2.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.capeleft = new ModelRenderer((Model)this);
    this.capeleft.setRotationPoint(9.0F, 0.0F, 0.0F);
    this.capeback.addChild(this.capeleft);
    this.capeleft.setTextureOffset(40, 70).addBox(0.0F, 0.0F, -5.0F, 0.0F, 22.0F, 5.0F, 0.0F, false);
    
    this.capefrontleft = new ModelRenderer((Model)this);
    this.capefrontleft.setRotationPoint(0.0F, 0.0F, -5.0F);
    this.capeleft.addChild(this.capefrontleft);
    this.capefrontleft.setTextureOffset(28, 98).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 22.0F, 0.0F, 0.0F, false);
    
    this.caperightsholderpad2 = new ModelRenderer((Model)this);
    this.caperightsholderpad2.setRotationPoint(-6.4F, 0.0F, 0.0F);
    this.capeback.addChild(this.caperightsholderpad2);
    this.caperightsholderpad2.setTextureOffset(5, 106).addBox(-4.0F, 0.0F, -5.5F, 5.0F, 3.0F, 6.0F, 0.0F, false);
    
    this.caperightarm = new ModelRenderer((Model)this);
    this.caperightarm.setRotationPoint(0.5F, 0.0F, -2.5F);
    this.caperightsholderpad2.addChild(this.caperightarm);
    this.caperightarm.setTextureOffset(35, 98).addBox(-4.1F, 1.0F, -2.0F, 2.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.caperightsholderpad1 = new ModelRenderer((Model)this);
    this.caperightsholderpad1.setRotationPoint(0.5F, 0.0F, -2.5F);
    this.caperightsholderpad2.addChild(this.caperightsholderpad1);
    setRotateAngle(this.caperightsholderpad1, 0.0F, 0.0F, -0.1745F);
    this.caperightsholderpad1.setTextureOffset(5, 98).addBox(-4.0F, -0.8F, -3.0F, 5.0F, 1.0F, 6.0F, 0.0F, false);
    
    this.caperight = new ModelRenderer((Model)this);
    this.caperight.setRotationPoint(-8.0F, 0.0F, 0.0F);
    this.capeback.addChild(this.caperight);
    this.caperight.setTextureOffset(40, 70).addBox(0.0F, 0.0F, -5.0F, 0.0F, 22.0F, 5.0F, 0.0F, false);
    
    this.capefrontright = new ModelRenderer((Model)this);
    this.capefrontright.setRotationPoint(0.0F, 0.0F, -5.0F);
    this.caperight.addChild(this.capefrontright);
    this.capefrontright.setTextureOffset(28, 98).addBox(0.0F, 0.0F, 0.0F, 3.0F, 22.0F, 0.0F, 0.0F, false);
    
    this.caperightsholder = new ModelRenderer((Model)this);
    this.caperightsholder.setRotationPoint(-3.5F, -0.51F, -2.5F);
    this.cape.addChild(this.caperightsholder);
    this.caperightsholder.setTextureOffset(51, 75).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);
    
    this.capeleftsholder = new ModelRenderer((Model)this);
    this.capeleftsholder.setRotationPoint(3.5F, -0.51F, -2.5F);
    this.cape.addChild(this.capeleftsholder);
    this.capeleftsholder.setTextureOffset(51, 75).addBox(0.0F, 0.0F, 0.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);
    
    this.capeleftcollar1 = new ModelRenderer((Model)this);
    this.capeleftcollar1.setRotationPoint(5.2F, -3.5F, -2.3F);
    this.cape.addChild(this.capeleftcollar1);
    setRotateAngle(this.capeleftcollar1, -0.0169F, -0.1913F, 0.0888F);
    this.capeleftcollar1.setTextureOffset(51, 81).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.capeleftcollar2 = new ModelRenderer((Model)this);
    this.capeleftcollar2.setRotationPoint(5.2F, -3.5F, -2.3F);
    this.cape.addChild(this.capeleftcollar2);
    setRotateAngle(this.capeleftcollar2, 0.0202F, -0.1909F, -0.1066F);
    this.capeleftcollar2.setTextureOffset(51, 90).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.caperightcollar1 = new ModelRenderer((Model)this);
    this.caperightcollar1.setRotationPoint(-5.2F, -3.5F, -2.3F);
    this.cape.addChild(this.caperightcollar1);
    setRotateAngle(this.caperightcollar1, -0.0169F, 0.1913F, -0.0888F);
    this.caperightcollar1.setTextureOffset(51, 81).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.caperightcollar2 = new ModelRenderer((Model)this);
    this.caperightcollar2.setRotationPoint(-5.2F, -3.5F, -2.3F);
    this.cape.addChild(this.caperightcollar2);
    setRotateAngle(this.caperightcollar2, 0.0202F, 0.1909F, 0.1066F);
    this.caperightcollar2.setTextureOffset(51, 90).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.cape.copyModelAngles(this.bipedBody);
    
    this.cape.rotationPointZ = 0.2F;
    
    this.cape.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    double dist = entity.getDistanceSq(((LivingEntity)entity).prevPosX, ((LivingEntity)entity).prevPosY, ((LivingEntity)entity).prevPosZ);
    if (dist > 0.0D && dist <= 0.02D)
      dist += 0.02D; 
    double angle = MathHelper.clamp(dist * 1000.0D - 1.0D, 0.0D, 70.0D);
    boolean isMoving = (dist > 0.02D);
    if (isMoving)
      angle += (MathHelper.sin(MathHelper.lerp(limbSwing, ((LivingEntity)entity).prevDistanceWalkedModified, ((LivingEntity)entity).distanceWalkedModified)) * 4.0F); 
    this.capeback.rotateAngleX = (float)Math.toRadians(angle);
    this.caperightsholderpad2.rotateAngleX = (float)Math.toRadians(-angle);
    this.capeleftsholderpad2.rotateAngleX = (float)Math.toRadians(-angle);
    this.caperightarm.rotateAngleX = (float)Math.toRadians((float)(angle - (!isMoving ? 0 : 20)));
    this.capeleftarm.rotateAngleX = (float)Math.toRadians((float)(angle - (!isMoving ? 0 : 20)));
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


