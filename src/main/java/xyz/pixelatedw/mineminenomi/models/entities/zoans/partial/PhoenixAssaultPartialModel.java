package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class PhoenixAssaultPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer rightWing;
  private final ModelRenderer rightWing2;
  private final ModelRenderer rightWingLayer1;
  private final ModelRenderer rightWingLayer1b;
  private final ModelRenderer rightWingLayer2;
  private final ModelRenderer rightWingLayer2b;
  private final ModelRenderer leftWing;
  private final ModelRenderer leftWing2;
  private final ModelRenderer leftWingLayer1;
  private final ModelRenderer leftWingLayer1b;
  private final ModelRenderer leftWingLayer2;
  private final ModelRenderer leftWingLayer2b;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightLeg3;
  private final ModelRenderer rightTalon1;
  private final ModelRenderer rightTalon1b;
  private final ModelRenderer rightTalon1b_r1;
  private final ModelRenderer rightTalon1c;
  private final ModelRenderer rightTalon1c_r1;
  private final ModelRenderer rightTalon2;
  private final ModelRenderer rightTalon2b;
  private final ModelRenderer rightTalon2b_r1;
  private final ModelRenderer rightTalon2c;
  private final ModelRenderer rightTalon2c_r1;
  private final ModelRenderer rightTalon3;
  private final ModelRenderer rightTalon3_r1;
  private final ModelRenderer rightTalon3b;
  private final ModelRenderer rightTalon3c;
  private final ModelRenderer rightTalon4;
  private final ModelRenderer rightTalon4b;
  private final ModelRenderer rightTalon4b_r1;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftLeg3;
  private final ModelRenderer leftTalon1;
  private final ModelRenderer leftTalon1_r1;
  private final ModelRenderer leftTalon1b;
  private final ModelRenderer leftTalon1c;
  private final ModelRenderer leftTalon1c_r1;
  private final ModelRenderer leftTalon2;
  private final ModelRenderer leftTalon2_r1;
  private final ModelRenderer leftTalon2b;
  private final ModelRenderer leftTalon2c;
  private final ModelRenderer leftTalon3;
  private final ModelRenderer leftTalon3_r1;
  private final ModelRenderer leftTalon3b;
  private final ModelRenderer leftTalon3c;
  private final ModelRenderer leftTalon4;
  private final ModelRenderer leftTalon4b;
  private final ModelRenderer tail1;
  private final ModelRenderer tail1b;
  private final ModelRenderer tail1c;
  private final ModelRenderer tail2;
  private final ModelRenderer tail2b;
  private final ModelRenderer tail2c;
  private final ModelRenderer tail3;
  private final ModelRenderer tail3b;
  private final ModelRenderer tail3c;
  
  public PhoenixAssaultPartialModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;
    
    this.rightWing = new ModelRenderer((Model)this);
    this.rightWing.setRotationPoint(-1.5F, 1.0F, 2.5F);
    setRotationAngle(this.rightWing, 1.5708F, 0.0F, -1.3963F);
    this.rightWing.setTextureOffset(71, 54).addBox(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
    
    this.rightWing2 = new ModelRenderer((Model)this);
    this.rightWing2.setRotationPoint(-12.1F, -5.05F, 1.0F);
    this.rightWing.addChild(this.rightWing2);
    setRotationAngle(this.rightWing2, 0.0F, 0.0F, 0.1047F);
    this.rightWing2.setTextureOffset(98, 52).addBox(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
    
    this.rightWingLayer1 = new ModelRenderer((Model)this);
    this.rightWingLayer1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightWing.addChild(this.rightWingLayer1);
    setRotationAngle(this.rightWingLayer1, -0.1745F, 0.0F, 0.0F);
    this.rightWingLayer1.setTextureOffset(71, 54).addBox(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
    
    this.rightWingLayer1b = new ModelRenderer((Model)this);
    this.rightWingLayer1b.setRotationPoint(-12.1F, -5.05F, 1.0F);
    this.rightWingLayer1.addChild(this.rightWingLayer1b);
    setRotationAngle(this.rightWingLayer1b, 0.0F, 0.0F, 0.1047F);
    this.rightWingLayer1b.setTextureOffset(98, 52).addBox(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
    
    this.rightWingLayer2 = new ModelRenderer((Model)this);
    this.rightWingLayer2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightWing.addChild(this.rightWingLayer2);
    setRotationAngle(this.rightWingLayer2, 0.1745F, 0.0F, 0.0F);
    this.rightWingLayer2.setTextureOffset(71, 54).addBox(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
    
    this.rightWingLayer2b = new ModelRenderer((Model)this);
    this.rightWingLayer2b.setRotationPoint(-12.1F, -5.05F, 1.0F);
    this.rightWingLayer2.addChild(this.rightWingLayer2b);
    setRotationAngle(this.rightWingLayer2b, 0.0F, 0.0F, 0.1047F);
    this.rightWingLayer2b.setTextureOffset(98, 52).addBox(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
    
    this.leftWing = new ModelRenderer((Model)this);
    this.leftWing.setRotationPoint(1.5F, 1.0F, 2.5F);
    setRotationAngle(this.leftWing, 1.5708F, 0.0F, 1.3963F);
    this.leftWing.setTextureOffset(72, 39).addBox(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
    
    this.leftWing2 = new ModelRenderer((Model)this);
    this.leftWing2.setRotationPoint(12.0F, -5.05F, 1.0F);
    this.leftWing.addChild(this.leftWing2);
    setRotationAngle(this.leftWing2, 0.0F, 0.0F, -0.1047F);
    this.leftWing2.setTextureOffset(98, 39).addBox(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
    
    this.leftWingLayer1 = new ModelRenderer((Model)this);
    this.leftWingLayer1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftWing.addChild(this.leftWingLayer1);
    setRotationAngle(this.leftWingLayer1, 0.1745F, 0.0F, 0.0F);
    this.leftWingLayer1.setTextureOffset(72, 39).addBox(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
    
    this.leftWingLayer1b = new ModelRenderer((Model)this);
    this.leftWingLayer1b.setRotationPoint(12.0F, -5.05F, 1.0F);
    this.leftWingLayer1.addChild(this.leftWingLayer1b);
    setRotationAngle(this.leftWingLayer1b, 0.0F, 0.0F, -0.1047F);
    this.leftWingLayer1b.setTextureOffset(98, 39).addBox(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
    
    this.leftWingLayer2 = new ModelRenderer((Model)this);
    this.leftWingLayer2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftWing.addChild(this.leftWingLayer2);
    setRotationAngle(this.leftWingLayer2, -0.1745F, 0.0F, 0.0F);
    this.leftWingLayer2.setTextureOffset(72, 39).addBox(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
    
    this.leftWingLayer2b = new ModelRenderer((Model)this);
    this.leftWingLayer2b.setRotationPoint(12.0F, -5.05F, 1.0F);
    this.leftWingLayer2.addChild(this.leftWingLayer2b);
    setRotationAngle(this.leftWingLayer2b, 0.0F, 0.0F, -0.1047F);
    this.leftWingLayer2b.setTextureOffset(98, 39).addBox(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
    this.rightLeg.setTextureOffset(0, 34).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(0.0F, 4.75F, 0.0F);
    this.rightLeg.addChild(this.rightLeg2);
    this.rightLeg2.setTextureOffset(0, 45).addBox(-1.5F, 0.0F, -1.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.rightLeg3 = new ModelRenderer((Model)this);
    this.rightLeg3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightLeg2.addChild(this.rightLeg3);
    this.rightLeg3.setTextureOffset(0, 54).addBox(-1.0F, 3.0F, -0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.rightTalon1 = new ModelRenderer((Model)this);
    this.rightTalon1.setRotationPoint(-0.75F, 6.5F, 0.25F);
    this.rightLeg3.addChild(this.rightTalon1);
    setRotationAngle(this.rightTalon1, 0.0437F, 0.1309F, 0.0F);
    this.rightTalon1.setTextureOffset(13, 46).addBox(-0.5F, -0.5F, -2.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightTalon1b = new ModelRenderer((Model)this);
    this.rightTalon1b.setRotationPoint(0.0F, 0.0F, -1.5F);
    this.rightTalon1.addChild(this.rightTalon1b);
    setRotationAngle(this.rightTalon1b, -0.0436F, 0.0F, 0.0F);

    
    this.rightTalon1b_r1 = new ModelRenderer((Model)this);
    this.rightTalon1b_r1.setRotationPoint(2.75F, 0.75F, 1.25F);
    this.rightTalon1b.addChild(this.rightTalon1b_r1);
    setRotationAngle(this.rightTalon1b_r1, 0.0436F, 0.0F, 0.0F);
    this.rightTalon1b_r1.setTextureOffset(13, 53).addBox(-3.25F, -1.15F, -3.7264F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.rightTalon1c = new ModelRenderer((Model)this);
    this.rightTalon1c.setRotationPoint(0.0F, 0.0F, -1.0F);
    this.rightTalon1b.addChild(this.rightTalon1c);
    setRotationAngle(this.rightTalon1c, 0.0873F, 0.0F, 0.0F);

    
    this.rightTalon1c_r1 = new ModelRenderer((Model)this);
    this.rightTalon1c_r1.setRotationPoint(2.75F, 0.75F, 1.25F);
    this.rightTalon1c.addChild(this.rightTalon1c_r1);
    setRotationAngle(this.rightTalon1c_r1, 0.0436F, 0.0F, 0.0F);
    this.rightTalon1c_r1.setTextureOffset(13, 53).addBox(-3.25F, -1.15F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.rightTalon2 = new ModelRenderer((Model)this);
    this.rightTalon2.setRotationPoint(0.0F, 6.5F, 0.25F);
    this.rightLeg3.addChild(this.rightTalon2);
    setRotationAngle(this.rightTalon2, 0.0437F, 0.0F, 0.0F);
    this.rightTalon2.setTextureOffset(13, 46).addBox(-0.5F, -0.5F, -2.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightTalon2b = new ModelRenderer((Model)this);
    this.rightTalon2b.setRotationPoint(0.0F, 0.0F, -1.5F);
    this.rightTalon2.addChild(this.rightTalon2b);
    setRotationAngle(this.rightTalon2b, 0.1833F, 0.0F, 0.0F);

    
    this.rightTalon2b_r1 = new ModelRenderer((Model)this);
    this.rightTalon2b_r1.setRotationPoint(2.0F, 0.75F, 1.25F);
    this.rightTalon2b.addChild(this.rightTalon2b_r1);
    setRotationAngle(this.rightTalon2b_r1, -0.1745F, 0.0F, 0.0F);
    this.rightTalon2b_r1.setTextureOffset(13, 53).addBox(-2.5F, -0.9F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.rightTalon2c = new ModelRenderer((Model)this);
    this.rightTalon2c.setRotationPoint(0.0F, -0.25F, -2.75F);
    this.rightTalon2b.addChild(this.rightTalon2c);
    setRotationAngle(this.rightTalon2c, 0.0873F, 0.0F, 0.0F);

    
    this.rightTalon2c_r1 = new ModelRenderer((Model)this);
    this.rightTalon2c_r1.setRotationPoint(2.0F, 0.8154F, 2.7486F);
    this.rightTalon2c.addChild(this.rightTalon2c_r1);
    setRotationAngle(this.rightTalon2c_r1, -0.1745F, 0.0F, 0.0F);
    this.rightTalon2c_r1.setTextureOffset(13, 53).addBox(-2.5F, -0.8F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.rightTalon3 = new ModelRenderer((Model)this);
    this.rightTalon3.setRotationPoint(0.75F, 6.5F, 0.25F);
    this.rightLeg3.addChild(this.rightTalon3);
    setRotationAngle(this.rightTalon3, 0.0873F, -0.1309F, 0.0F);

    
    this.rightTalon3_r1 = new ModelRenderer((Model)this);
    this.rightTalon3_r1.setRotationPoint(1.25F, 0.75F, -0.25F);
    this.rightTalon3.addChild(this.rightTalon3_r1);
    setRotationAngle(this.rightTalon3_r1, -0.0436F, 0.0F, 0.0F);
    this.rightTalon3_r1.setTextureOffset(13, 46).addBox(-1.75F, -1.25F, -1.98F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightTalon3b = new ModelRenderer((Model)this);
    this.rightTalon3b.setRotationPoint(0.0F, 0.0F, -2.0F);
    this.rightTalon3.addChild(this.rightTalon3b);
    setRotationAngle(this.rightTalon3b, -0.0436F, 0.0F, 0.0F);
    this.rightTalon3b.setTextureOffset(13, 53).addBox(-0.5F, -0.45F, -1.9883F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.rightTalon3c = new ModelRenderer((Model)this);
    this.rightTalon3c.setRotationPoint(0.0F, 0.0F, -1.25F);
    this.rightTalon3b.addChild(this.rightTalon3c);
    setRotationAngle(this.rightTalon3c, 0.0436F, 0.0F, 0.0F);
    this.rightTalon3c.setTextureOffset(13, 53).addBox(-0.5F, -0.35F, -1.9883F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.rightTalon4 = new ModelRenderer((Model)this);
    this.rightTalon4.setRotationPoint(0.0F, 6.5F, 0.0F);
    this.rightLeg3.addChild(this.rightTalon4);
    setRotationAngle(this.rightTalon4, 0.0873F, 3.1416F, 0.0F);
    this.rightTalon4.setTextureOffset(13, 46).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.rightTalon4b = new ModelRenderer((Model)this);
    this.rightTalon4b.setRotationPoint(0.0F, 0.0F, -3.0F);
    this.rightTalon4.addChild(this.rightTalon4b);
    setRotationAngle(this.rightTalon4b, 0.1309F, 0.0F, 0.0F);

    
    this.rightTalon4b_r1 = new ModelRenderer((Model)this);
    this.rightTalon4b_r1.setRotationPoint(2.0F, 0.9665F, 1.4763F);
    this.rightTalon4b.addChild(this.rightTalon4b_r1);
    setRotationAngle(this.rightTalon4b_r1, -0.0873F, 0.0F, 0.0F);
    this.rightTalon4b_r1.setTextureOffset(13, 53).addBox(-2.5F, -1.3165F, -2.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
    this.leftLeg.setTextureOffset(0, 34).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(0.0F, 4.75F, 0.0F);
    this.leftLeg.addChild(this.leftLeg2);
    this.leftLeg2.setTextureOffset(0, 45).addBox(-1.5F, 0.0F, -1.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.leftLeg3 = new ModelRenderer((Model)this);
    this.leftLeg3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftLeg2.addChild(this.leftLeg3);
    this.leftLeg3.setTextureOffset(0, 54).addBox(-1.0F, 3.0F, -0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.leftTalon1 = new ModelRenderer((Model)this);
    this.leftTalon1.setRotationPoint(-0.75F, 6.5F, 0.25F);
    this.leftLeg3.addChild(this.leftTalon1);
    setRotationAngle(this.leftTalon1, 0.0873F, 0.1309F, 0.0F);

    
    this.leftTalon1_r1 = new ModelRenderer((Model)this);
    this.leftTalon1_r1.setRotationPoint(-1.25F, 0.75F, -0.25F);
    this.leftTalon1.addChild(this.leftTalon1_r1);
    setRotationAngle(this.leftTalon1_r1, -0.0433F, -6.0E-4F, -0.0057F);
    this.leftTalon1_r1.setTextureOffset(13, 46).addBox(0.7892F, -1.2759F, -2.0463F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftTalon1b = new ModelRenderer((Model)this);
    this.leftTalon1b.setRotationPoint(0.0F, 0.0F, -2.0F);
    this.leftTalon1.addChild(this.leftTalon1b);
    setRotationAngle(this.leftTalon1b, -0.0436F, 0.0F, 0.0F);
    this.leftTalon1b.setTextureOffset(13, 53).addBox(-0.4608F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.leftTalon1c = new ModelRenderer((Model)this);
    this.leftTalon1c.setRotationPoint(0.0F, 0.0F, -1.25F);
    this.leftTalon1b.addChild(this.leftTalon1c);
    setRotationAngle(this.leftTalon1c, 0.0436F, 0.0F, 0.0F);

    
    this.leftTalon1c_r1 = new ModelRenderer((Model)this);
    this.leftTalon1c_r1.setRotationPoint(-1.25F, 0.75F, 1.25F);
    this.leftTalon1c.addChild(this.leftTalon1c_r1);
    setRotationAngle(this.leftTalon1c_r1, 0.0436F, 0.0F, 0.0F);
    this.leftTalon1c_r1.setTextureOffset(13, 53).addBox(0.7892F, -1.2395F, -3.2601F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.leftTalon2 = new ModelRenderer((Model)this);
    this.leftTalon2.setRotationPoint(0.0F, 6.5F, 0.25F);
    this.leftLeg3.addChild(this.leftTalon2);
    setRotationAngle(this.leftTalon2, 0.0873F, 0.0F, 0.0F);

    
    this.leftTalon2_r1 = new ModelRenderer((Model)this);
    this.leftTalon2_r1.setRotationPoint(-2.0F, 0.75F, -0.25F);
    this.leftTalon2.addChild(this.leftTalon2_r1);
    setRotationAngle(this.leftTalon2_r1, -0.0436F, 0.0F, 0.0F);
    this.leftTalon2_r1.setTextureOffset(13, 46).addBox(1.5F, -1.2762F, -2.0489F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftTalon2b = new ModelRenderer((Model)this);
    this.leftTalon2b.setRotationPoint(0.0F, 0.0F, -2.0F);
    this.leftTalon2.addChild(this.leftTalon2b);
    setRotationAngle(this.leftTalon2b, -0.0436F, 0.0F, 0.0F);
    this.leftTalon2b.setTextureOffset(13, 53).addBox(-0.5F, -0.4902F, -2.0125F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.leftTalon2c = new ModelRenderer((Model)this);
    this.leftTalon2c.setRotationPoint(0.0F, 0.0F, -1.25F);
    this.leftTalon2b.addChild(this.leftTalon2c);
    setRotationAngle(this.leftTalon2c, 0.0873F, 0.0F, 0.0F);
    this.leftTalon2c.setTextureOffset(13, 53).addBox(-0.5F, -0.4902F, -2.1F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.leftTalon3 = new ModelRenderer((Model)this);
    this.leftTalon3.setRotationPoint(0.75F, 6.5F, 0.25F);
    this.leftLeg3.addChild(this.leftTalon3);
    setRotationAngle(this.leftTalon3, 0.0873F, -0.1309F, 0.0F);

    
    this.leftTalon3_r1 = new ModelRenderer((Model)this);
    this.leftTalon3_r1.setRotationPoint(-2.75F, 0.75F, -0.25F);
    this.leftTalon3.addChild(this.leftTalon3_r1);
    setRotationAngle(this.leftTalon3_r1, -0.0433F, 6.0E-4F, 0.0057F);
    this.leftTalon3_r1.setTextureOffset(13, 46).addBox(2.2108F, -1.2759F, -2.0463F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftTalon3b = new ModelRenderer((Model)this);
    this.leftTalon3b.setRotationPoint(0.0F, 0.0F, -2.0F);
    this.leftTalon3.addChild(this.leftTalon3b);
    setRotationAngle(this.leftTalon3b, -0.0349F, 0.0F, 0.0F);
    this.leftTalon3b.setTextureOffset(13, 53).addBox(-0.5392F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.leftTalon3c = new ModelRenderer((Model)this);
    this.leftTalon3c.setRotationPoint(0.0F, 0.0F, -1.25F);
    this.leftTalon3b.addChild(this.leftTalon3c);
    setRotationAngle(this.leftTalon3c, 0.096F, 0.0F, 0.0F);
    this.leftTalon3c.setTextureOffset(13, 53).addBox(-0.5392F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.leftTalon4 = new ModelRenderer((Model)this);
    this.leftTalon4.setRotationPoint(0.0F, 6.5F, 0.0F);
    this.leftLeg3.addChild(this.leftTalon4);
    setRotationAngle(this.leftTalon4, 0.0873F, 3.1416F, 0.0F);
    this.leftTalon4.setTextureOffset(13, 46).addBox(-0.5F, -0.55F, -2.9962F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.leftTalon4b = new ModelRenderer((Model)this);
    this.leftTalon4b.setRotationPoint(0.0F, -0.25F, -3.0F);
    this.leftTalon4.addChild(this.leftTalon4b);
    setRotationAngle(this.leftTalon4b, 0.0437F, 0.0F, 0.0F);
    this.leftTalon4b.setTextureOffset(13, 53).addBox(-0.5F, -0.2F, -1.4F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.tail1 = new ModelRenderer((Model)this);
    this.tail1.setRotationPoint(0.5F, 11.25F, 2.0F);
    setRotationAngle(this.tail1, 0.0F, -0.2182F, 0.0F);
    this.tail1.setTextureOffset(18, 53).addBox(-2.2651F, -1.0F, -0.0855F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail1b = new ModelRenderer((Model)this);
    this.tail1b.setRotationPoint(0.0F, 0.0F, 8.5F);
    this.tail1.addChild(this.tail1b);
    setRotationAngle(this.tail1b, 0.0F, -0.1745F, 0.0F);
    this.tail1b.setTextureOffset(18, 53).addBox(-2.2835F, -1.0F, -0.125F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail1c = new ModelRenderer((Model)this);
    this.tail1c.setRotationPoint(-1.5F, 0.0F, 9.0F);
    this.tail1b.addChild(this.tail1c);
    setRotationAngle(this.tail1c, 0.0F, 0.1746F, 0.0F);
    this.tail1c.setTextureOffset(20, 45).addBox(-0.7708F, -1.0F, -0.2162F, 2.0F, 0.0F, 7.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.0F, 11.25F, 2.0F);
    setRotationAngle(this.tail2, 0.0F, 0.1745F, 0.0F);
    this.tail2.setTextureOffset(18, 53).addBox(-0.2842F, -1.0F, -0.304F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail2b = new ModelRenderer((Model)this);
    this.tail2b.setRotationPoint(0.4696F, 0.0F, 8.1526F);
    this.tail2.addChild(this.tail2b);
    setRotationAngle(this.tail2b, 0.0F, 0.1746F, 0.0F);
    this.tail2b.setTextureOffset(18, 53).addBox(-0.7708F, -1.0F, -0.0452F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail2c = new ModelRenderer((Model)this);
    this.tail2c.setRotationPoint(-0.0057F, 0.0F, 8.8693F);
    this.tail2b.addChild(this.tail2c);
    setRotationAngle(this.tail2c, 0.0F, -0.1309F, 0.0F);
    this.tail2c.setTextureOffset(20, 45).addBox(-0.7616F, -1.0F, -0.0766F, 2.0F, 0.0F, 7.0F, 0.0F, false);
    
    this.tail3 = new ModelRenderer((Model)this);
    this.tail3.setRotationPoint(-0.25F, 11.5F, 2.0F);
    setRotationAngle(this.tail3, 0.0F, 0.0F, 0.0F);
    this.tail3.setTextureOffset(18, 53).addBox(-1.0342F, -1.0F, -0.304F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail3b = new ModelRenderer((Model)this);
    this.tail3b.setRotationPoint(-0.2804F, 0.0F, 8.1526F);
    this.tail3.addChild(this.tail3b);
    setRotationAngle(this.tail3b, 0.0F, -0.0436F, 0.0F);
    this.tail3b.setTextureOffset(18, 53).addBox(-0.7708F, -1.0F, -0.0452F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail3c = new ModelRenderer((Model)this);
    this.tail3c.setRotationPoint(-0.0057F, 0.0F, 8.8693F);
    this.tail3b.addChild(this.tail3c);
    setRotationAngle(this.tail3c, 0.0F, 0.0436F, 0.0F);
    this.tail3c.setTextureOffset(20, 45).addBox(-0.7616F, -1.0F, -0.0766F, 2.0F, 0.0F, 7.0F, 0.0F, false);
    
    this.bipedLeftLeg = this.leftLeg;
    this.bipedRightLeg = this.rightLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    matrixStack.push();
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    matrixStack.scale(1.6F, 1.6F, 1.6F);
    matrixStack.translate(0.0D, -0.1D, 0.0D);
    this.rightWing.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    this.leftWing.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    matrixStack.pop();
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    this.tail1.rotateAngleY = (float)(this.tail1.rotateAngleY + Math.sin(ageInTicks * 0.04D) / 10.0D);
    this.tail1.rotateAngleX = (float)(this.tail1.rotateAngleX + Math.sin(ageInTicks * 0.01D) / 8.0D);
    this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY - Math.sin(ageInTicks * 0.08D) / 10.0D);
    this.tail2.rotateAngleX = (float)(this.tail2.rotateAngleX - Math.sin(ageInTicks * 0.05D) / 8.0D);
    this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY + Math.sin(ageInTicks * 0.08D) / 10.0D);
    this.tail3.rotateAngleX = (float)(this.tail3.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 8.0D);
    
    this.tail1b.rotateAngleY = (float)(this.tail1b.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 10.0D);
    this.tail1b.rotateAngleX = (float)(this.tail1b.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 5.0D);
    this.tail2b.rotateAngleY = (float)(this.tail2b.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 10.0D);
    this.tail2b.rotateAngleX = (float)(this.tail2b.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 5.0D);
    this.tail3b.rotateAngleY = (float)(this.tail3b.rotateAngleY - Math.sin(ageInTicks * 0.04D) / 10.0D);
    this.tail3b.rotateAngleX = (float)(this.tail3b.rotateAngleX - Math.sin(ageInTicks * 0.01D) / 5.0D);
    
    this.tail1c.rotateAngleY = (float)(this.tail1c.rotateAngleY + Math.sin(ageInTicks * 0.08D) / 10.0D);
    this.tail1c.rotateAngleX = (float)(this.tail1c.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 8.0D);
    this.tail2c.rotateAngleY = (float)(this.tail2c.rotateAngleY + Math.sin(ageInTicks * 0.08D) / 10.0D);
    this.tail2c.rotateAngleX = (float)(this.tail2c.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 8.0D);
    this.tail3c.rotateAngleY = (float)(this.tail3c.rotateAngleY + Math.sin(ageInTicks * 0.08D) / 10.0D);
    this.tail3c.rotateAngleX = (float)(this.tail3c.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 8.0D);
    
    this.tail1.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.2F) * 0.3F;
    this.tail1b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.3F) * 0.8F;
    this.tail1c.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.4F) * 0.8F;
    this.tail2.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.2F + 3.1415927F) * 0.3F;
    this.tail2b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.3F + 3.1415927F) * 0.8F;
    this.tail2c.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.8F;
    this.tail3b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.3F + 3.1415927F) * 0.8F;
    this.tail3c.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.8F;
    
    if (!((LivingEntity)entity).onGround) {
      
      this.rightWing.rotationPointX--;
      this.rightWing.rotationPointY += 2.0F;
      this.rightWing.rotateAngleZ = 0.3F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D));
      this.rightWing.rotateAngleY = MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
      this.rightWing2.rotateAngleY = MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
      this.rightWingLayer1b.rotateAngleY = MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
      this.rightWingLayer2b.rotateAngleY = MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
      
      this.leftWing.rotationPointX += 0.3F;
      this.leftWing.rotationPointY += 1.55F;
      this.leftWing.rotateAngleZ = -0.3F - MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D));
      this.leftWing.rotateAngleY = -MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
      this.leftWing2.rotateAngleY = -MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
      this.leftWingLayer1b.rotateAngleY = -MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
      this.leftWingLayer2b.rotateAngleY = -MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
      
      if (!entity.getHeldItemMainhand().isEmpty()) {
        
        this.rightLeg.rotateAngleX -= 0.15F;
        this.rightLeg2.rotationPointY -= 3.0F;
        this.rightLeg2.rotateAngleX = (float)(this.rightLeg2.rotateAngleX - 0.4D);
        this.rightLeg2.rotateAngleY = (float)(this.rightLeg2.rotateAngleY + 0.8D);
        this.rightTalon1.rotateAngleX = (float)(this.rightTalon1.rotateAngleX + 0.7D);
        this.rightTalon1b.rotateAngleX = (float)(this.rightTalon1b.rotateAngleX + 0.7D);
        this.rightTalon2.rotateAngleX = (float)(this.rightTalon2.rotateAngleX + 0.5D);
        this.rightTalon2b.rotateAngleX = (float)(this.rightTalon2b.rotateAngleX + 0.7D);
        this.rightTalon3.rotateAngleX = (float)(this.rightTalon3.rotateAngleX + 0.4D);
        this.rightTalon3b.rotateAngleX = (float)(this.rightTalon3b.rotateAngleX + 0.7D);
        this.rightTalon4.rotateAngleX = (float)(this.rightTalon4.rotateAngleX + 0.6D);
        this.rightTalon4b.rotateAngleX = (float)(this.rightTalon4b.rotateAngleX + 0.7D);
      } 

      
      this.swingProgress = ((LivingEntity)entity).swingProgress;
      if (this.swingProgress > 0.0F && !EntityStatsCapability.get((LivingEntity)entity).isBlackLeg())
      {
        float swingProgress = ((LivingEntity)entity).swingProgress;
        this.bipedRightLeg.rotateAngleX -= ((LivingEntity)entity).swingProgress * 2.0F;
        this.bipedRightLeg.rotateAngleZ += ((LivingEntity)entity).swingProgress * 2.0F;
        this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F) * 0.2F;
        this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
      }
    
    } else {
      
      this.rightWing2.showModel = false;
      this.rightWingLayer1b.showModel = false;
      this.rightWingLayer2b.showModel = false;
      this.leftWing2.showModel = false;
      this.leftWingLayer1b.showModel = false;
      this.leftWingLayer2b.showModel = false;




      
      float f = 1.0F;
      this.rightWing.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
      this.leftWing.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;

      
      this.rightWing.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
      this.leftWing.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;

      
      this.swingProgress = ((LivingEntity)entity).swingProgress;
      boolean isBlackLeg = (EntityStatsCapability.get((LivingEntity)entity).isBlackLeg() && entity.getHeldItemMainhand().isEmpty());
      if (this.swingProgress > 0.0F && !isBlackLeg) {
        
        this.rightWing.rotateAngleY += this.bipedBody.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
        this.rightWing.rotateAngleY -= (float)(this.rightWing.rotateAngleX - f2 * 1.5D + f3);
        this.rightWing.rotateAngleZ -= this.bipedBody.rotateAngleY * 2.0F;
        this.rightWing.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
      } 
    } 
  }




  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}



  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(0.0D, -1.2D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
      this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(0.0D, -1.2D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
      this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }


  
  public boolean renderItemInHand(T entity, HandSide side, MatrixStack matrixStack) {
    if (entity instanceof PlayerEntity)
    {
      if (!((PlayerEntity)entity).abilities.isFlying)
      {
        return false;
      }
    }
    this.rightLeg.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.YP.rotationDegrees(-40.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(-30.0F));
    matrixStack.translate(-0.12D, -0.05D, 0.3D);
    return true;
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


