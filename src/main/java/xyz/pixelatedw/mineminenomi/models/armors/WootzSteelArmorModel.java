package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.abilities.GunArrayAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class WootzSteelArmorModel<T extends LivingEntity>
  extends BipedModel<T> {
  private final ModelRenderer armor;
  private final ModelRenderer rightArmArmor;
  private final ModelRenderer leftArmArmor;
  private final ModelRenderer bodyArmor;
  private final ModelRenderer rightShoulderBase;
  private final ModelRenderer rightShoulder1;
  private final ModelRenderer rightShoulder2;
  private final ModelRenderer rightShoulderGuns;
  private final ModelRenderer rightShoulderGun1;
  private final ModelRenderer rightShoulderGun2;
  private final ModelRenderer leftShoulderBase;
  private final ModelRenderer leftShoulder1;
  private final ModelRenderer leftShoulder2;
  private final ModelRenderer leftShoulderGuns;
  private final ModelRenderer leftShoulderGun2;
  private final ModelRenderer leftShoulderGun1;
  
  public WootzSteelArmorModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 32;
    
    this.armor = new ModelRenderer((Model)this);
    this.armor.setRotationPoint(0.0F, 0.25F, 0.0F);
    
    this.rightArmArmor = new ModelRenderer((Model)this);
    this.rightArmArmor.setRotationPoint(-6.0F, 1.75F, 0.0F);
    
    this.rightArmArmor.setTextureOffset(34, 0).addBox(-3.5F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
    
    this.leftArmArmor = new ModelRenderer((Model)this);
    this.leftArmArmor.setRotationPoint(6.0F, 1.75F, 0.0F);
    
    this.leftArmArmor.setTextureOffset(34, 0).addBox(-1.5F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
    
    this.bodyArmor = new ModelRenderer((Model)this);
    this.bodyArmor.setRotationPoint(0.0F, -0.25F, 0.0F);
    this.armor.addChild(this.bodyArmor);
    this.bodyArmor.setTextureOffset(0, 0).addBox(-5.5F, 0.0F, -3.0F, 11.0F, 11.0F, 6.0F, 0.0F, false);
    
    this.rightShoulderBase = new ModelRenderer((Model)this);
    this.rightShoulderBase.setRotationPoint(-8.0F, 0.25F, 3.4F);
    this.armor.addChild(this.rightShoulderBase);
    setRotateAngle(this.rightShoulderBase, 0.0F, 0.0456F, -0.5236F);
    this.rightShoulderBase.setTextureOffset(21, 17).addBox(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
    
    this.rightShoulder1 = new ModelRenderer((Model)this);
    this.rightShoulder1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightShoulderBase.addChild(this.rightShoulder1);
    this.rightShoulder1.setTextureOffset(0, 24).addBox(-3.5F, -1.0F, -6.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
    
    this.rightShoulder2 = new ModelRenderer((Model)this);
    this.rightShoulder2.setRotationPoint(8.0F, -0.5F, -3.4F);
    this.rightShoulder1.addChild(this.rightShoulder2);
    this.rightShoulder2.setTextureOffset(0, 17).addBox(-11.0531F, -1.5F, -2.6F, 6.0F, 1.0F, 6.0F, 0.0F, false);
    
    this.rightShoulderGuns = new ModelRenderer((Model)this);
    this.rightShoulderGuns.setRotationPoint(-6.0F, -0.25F, 0.0F);
    this.armor.addChild(this.rightShoulderGuns);
    this.rightShoulderGuns.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);
    
    this.rightShoulderGun1 = new ModelRenderer((Model)this);
    this.rightShoulderGun1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightShoulderGuns.addChild(this.rightShoulderGun1);
    this.rightShoulderGun1.setTextureOffset(48, 16).addBox(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.rightShoulderGun2 = new ModelRenderer((Model)this);
    this.rightShoulderGun2.setRotationPoint(-3.9F, 1.0F, 0.0F);
    this.rightShoulderGuns.addChild(this.rightShoulderGun2);
    this.rightShoulderGun2.setTextureOffset(48, 16).addBox(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.leftShoulderBase = new ModelRenderer((Model)this);
    this.leftShoulderBase.setRotationPoint(8.0F, 0.25F, 3.4F);
    this.armor.addChild(this.leftShoulderBase);
    setRotateAngle(this.leftShoulderBase, 0.0F, 0.0456F, 0.5236F);
    this.leftShoulderBase.setTextureOffset(21, 17).addBox(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
    
    this.leftShoulder1 = new ModelRenderer((Model)this);
    this.leftShoulder1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftShoulderBase.addChild(this.leftShoulder1);
    this.leftShoulder1.setTextureOffset(0, 24).addBox(-3.5F, -1.0F, -6.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
    
    this.leftShoulder2 = new ModelRenderer((Model)this);
    this.leftShoulder2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftShoulder1.addChild(this.leftShoulder2);
    this.leftShoulder2.setTextureOffset(0, 17).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
    
    this.leftShoulderGuns = new ModelRenderer((Model)this);
    this.leftShoulderGuns.setRotationPoint(6.0F, -0.25F, 0.0F);
    this.armor.addChild(this.leftShoulderGuns);
    this.leftShoulderGuns.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);
    
    this.leftShoulderGun2 = new ModelRenderer((Model)this);
    this.leftShoulderGun2.setRotationPoint(3.9F, 1.0F, 0.0F);
    this.leftShoulderGuns.addChild(this.leftShoulderGun2);
    this.leftShoulderGun2.setTextureOffset(48, 16).addBox(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.leftShoulderGun1 = new ModelRenderer((Model)this);
    this.leftShoulderGun1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftShoulderGuns.addChild(this.leftShoulderGun1);
    this.leftShoulderGun1.setTextureOffset(48, 16).addBox(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.armor.copyModelAngles(this.bipedBody);
    this.leftArmArmor.copyModelAngles(this.bipedLeftArm);
    this.rightArmArmor.copyModelAngles(this.bipedRightArm);
    
    this.armor.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArmArmor.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArmArmor.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    boolean hasGunsOut = false;
    if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
      
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      IAbilityData aprops = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
      
      if (aprops.getEquippedAbility((Ability)GunArrayAbility.INSTANCE) != null)
      {
        Ability ability = aprops.getEquippedAbility((Ability)GunArrayAbility.INSTANCE);
        
        if (ability.isContinuous())
        {
          hasGunsOut = true;
        }
      }
    
    } else if (entity instanceof DonKriegEntity) {
      
      if (((DonKriegEntity)entity).getAnimation() == 120) {
        
        hasGunsOut = true;
        this.leftArmArmor.rotateAngleX -= 1.55F;
        this.rightArmArmor.rotateAngleX -= 1.55F;
      } 
    } 
    
    if (hasGunsOut) {
      
      this.leftShoulderBase.rotateAngleX = (float)Math.toRadians(-70.0D);
      this.rightShoulderBase.rotateAngleX = (float)Math.toRadians(-70.0D);
    }
    else {
      
      this.leftShoulderGuns.showModel = false;
      this.rightShoulderGuns.showModel = false;
    } 
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


