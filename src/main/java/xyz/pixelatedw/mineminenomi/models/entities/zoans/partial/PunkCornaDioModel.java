package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class PunkCornaDioModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer head;
  private final ModelRenderer snout;
  private final ModelRenderer rightHorn;
  private final ModelRenderer rightHorn2;
  private final ModelRenderer rightHorn3;
  private final ModelRenderer leftHorn;
  private final ModelRenderer leftHorn2;
  private final ModelRenderer leftHorn3;
  private final ModelRenderer body;
  private final ModelRenderer lowerBack;
  private final ModelRenderer tail;
  private final ModelRenderer tail_r1;
  private final ModelRenderer collar;
  private final ModelRenderer collarMargin8;
  private final ModelRenderer collarMargin7;
  private final ModelRenderer collarMargin6;
  private final ModelRenderer collarMargin5;
  private final ModelRenderer collarMargin4;
  private final ModelRenderer collarMargin3;
  private final ModelRenderer collarMargin2;
  private final ModelRenderer collarMargin1;
  private final ModelRenderer collarFill2;
  private final ModelRenderer collarFill;
  private final ModelRenderer collarSpike12;
  private final ModelRenderer collarSpike11;
  private final ModelRenderer collarSpike10;
  private final ModelRenderer collarSpike9;
  private final ModelRenderer collarSpike8;
  private final ModelRenderer collarSpike7;
  private final ModelRenderer collarSpike6;
  private final ModelRenderer collarSpike5;
  private final ModelRenderer collarSpike4;
  private final ModelRenderer collarSpike3;
  private final ModelRenderer collarSpike2;
  private final ModelRenderer collarSpike1;
  private final ModelRenderer leftFrontLeg;
  private final ModelRenderer leftFrontLegJoint;
  private final ModelRenderer leftFrontLeg2;
  private final ModelRenderer leftFrontNail1;
  private final ModelRenderer leftFrontNail2;
  private final ModelRenderer leftLegCollar;
  private final ModelRenderer leftLegCollarMargin1;
  private final ModelRenderer leftLegCollarMargin2;
  private final ModelRenderer leftLegCollarMargin3;
  private final ModelRenderer leftLegCollarMargin4;
  private final ModelRenderer leftLegCollarMargin5;
  private final ModelRenderer leftLegCollarMargin6;
  private final ModelRenderer leftLegCollarMargin7;
  private final ModelRenderer leftLegCollarMargin8;
  private final ModelRenderer leftLegCollarFill1;
  private final ModelRenderer leftLegCollarFill2;
  private final ModelRenderer leftLegCollarSpike1;
  private final ModelRenderer leftLegCollarSpike2;
  private final ModelRenderer leftLegCollarSpike3;
  private final ModelRenderer leftLegCollarSpike4;
  private final ModelRenderer leftLegCollarSpike5;
  private final ModelRenderer leftLegCollarSpike6;
  private final ModelRenderer leftLegCollarSpike7;
  private final ModelRenderer leftLegCollarSpike8;
  private final ModelRenderer leftLegCollarSpike9;
  private final ModelRenderer leftLegCollarSpike10;
  private final ModelRenderer leftLegCollarSpike11;
  private final ModelRenderer leftLegCollarSpike12;
  private final ModelRenderer leftLegCollarConnection;
  private final ModelRenderer leftRearLeg;
  private final ModelRenderer leftRearNail1;
  private final ModelRenderer leftRearNail2;
  private final ModelRenderer leftRearLegCollar;
  private final ModelRenderer leftRearLegCollarMargin1;
  private final ModelRenderer leftRearLegCollarMargin2;
  private final ModelRenderer leftRearLegCollarMargin3;
  private final ModelRenderer leftRearLegCollarMargin4;
  private final ModelRenderer leftRearLegCollarMargin5;
  private final ModelRenderer leftRearLegCollarMargin6;
  private final ModelRenderer leftRearLegCollarMargin7;
  private final ModelRenderer leftRearLegCollarMargin8;
  private final ModelRenderer leftRearLegCollarSpike1;
  private final ModelRenderer leftRearLegCollarSpike2;
  private final ModelRenderer leftRearLegCollarSpike3;
  private final ModelRenderer leftRearLegCollarSpike4;
  private final ModelRenderer leftRearLegCollarSpike5;
  private final ModelRenderer leftRearLegCollarSpike6;
  private final ModelRenderer leftRearLegCollarSpike7;
  private final ModelRenderer leftRearLegCollarSpike8;
  private final ModelRenderer leftRearLegCollarSpikeConnection;
  private final ModelRenderer leftRearLegCollarFill3;
  private final ModelRenderer leftRearLegCollarFill2;
  private final ModelRenderer leftRearLegCollarFill1;
  private final ModelRenderer rightFrontLeg;
  private final ModelRenderer rightFrontLegJoint;
  private final ModelRenderer rightFrontLeg2;
  private final ModelRenderer leftFrontNail3;
  private final ModelRenderer leftFrontNail4;
  private final ModelRenderer rightLegCollar;
  private final ModelRenderer rightLegCollarMargin1;
  private final ModelRenderer rightLegCollarMargin2;
  private final ModelRenderer rightLegCollarMargin3;
  private final ModelRenderer rightLegCollarMargin4;
  private final ModelRenderer rightLegCollarMargin5;
  private final ModelRenderer rightLegCollarMargin6;
  private final ModelRenderer rightLegCollarMargin7;
  private final ModelRenderer rightLegCollarMargin8;
  private final ModelRenderer rightLegCollarFill1;
  private final ModelRenderer rightLegCollarFill2;
  private final ModelRenderer rightLegCollarSpike1;
  private final ModelRenderer rightLegCollarSpike2;
  private final ModelRenderer rightLegCollarSpike3;
  private final ModelRenderer rightLegCollarSpike4;
  private final ModelRenderer rightLegCollarSpike5;
  private final ModelRenderer rightLegCollarSpike6;
  private final ModelRenderer rightLegCollarSpike7;
  private final ModelRenderer rightLegCollarSpike8;
  private final ModelRenderer rightLegCollarSpike9;
  private final ModelRenderer rightLegCollarSpike10;
  private final ModelRenderer rightLegCollarSpike11;
  private final ModelRenderer rightLegCollarSpike12;
  private final ModelRenderer rightLegCollarConnector;
  private final ModelRenderer rightRearLeg;
  private final ModelRenderer rightRearNail1;
  private final ModelRenderer rightRearNail2;
  private final ModelRenderer rightRearLegCollar;
  private final ModelRenderer rightRearLegCollarMargin1;
  private final ModelRenderer rightRearLegCollarMargin2;
  private final ModelRenderer rightRearLegCollarMargin3;
  private final ModelRenderer rightRearLegCollarMargin4;
  private final ModelRenderer rightRearLegCollarMargin5;
  private final ModelRenderer rightRearLegCollarMargin6;
  private final ModelRenderer rightRearLegCollarMargin7;
  private final ModelRenderer rightRearLegCollarMargin8;
  private final ModelRenderer rightRearLegCollarSpike1;
  private final ModelRenderer rightRearLegCollarSpike2;
  private final ModelRenderer rightRearLegCollarSpike3;
  private final ModelRenderer rightRearLegCollarSpike4;
  private final ModelRenderer rightRearLegCollarSpike5;
  private final ModelRenderer rightRearLegCollarSpike6;
  private final ModelRenderer rightRearLegCollarSpike7;
  private final ModelRenderer rightRearLegCollarSpike8;
  private final ModelRenderer rightRearLegCollarConnection;
  private final ModelRenderer rightRearLegCollarFill1;
  private final ModelRenderer rightRearLegCollarFill2;
  private final ModelRenderer rightRearLegCollarFill3;
  
  public PunkCornaDioModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(-2.5F, 8.0F, -9.5F);
    setRotationAngle(this.head, 0.0873F, 0.0F, 0.0F);
    this.head.setTextureOffset(0, 38).addBox(0.0F, 0.3861F, -7.0606F, 5.0F, 5.0F, 5.0F, 0.0F, false);
    
    this.snout = new ModelRenderer((Model)this);
    this.snout.setRotationPoint(2.5F, 5.4999F, -6.6855F);
    this.head.addChild(this.snout);
    setRotationAngle(this.snout, -0.6109F, 0.0F, 0.0F);
    this.snout.setTextureOffset(0, 48).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.rightHorn = new ModelRenderer((Model)this);
    this.rightHorn.setRotationPoint(-0.5F, 1.0F, -3.5F);
    this.head.addChild(this.rightHorn);
    setRotationAngle(this.rightHorn, 1.4835F, -0.5236F, 0.7854F);
    this.rightHorn.setTextureOffset(0, 60).addBox(-2.25F, -1.5732F, -0.5F, 4.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.rightHorn2 = new ModelRenderer((Model)this);
    this.rightHorn2.setRotationPoint(-2.1744F, -0.8963F, 0.5F);
    this.rightHorn.addChild(this.rightHorn2);
    setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 0.8116F);
    this.rightHorn2.setTextureOffset(2, 55).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.01F, false);
    
    this.rightHorn3 = new ModelRenderer((Model)this);
    this.rightHorn3.setRotationPoint(-0.75F, 0.0F, 0.0F);
    this.rightHorn2.addChild(this.rightHorn3);
    setRotationAngle(this.rightHorn3, -0.0385F, 0.2148F, -0.0914F);
    this.rightHorn3.setTextureOffset(0, 66).addBox(-3.0282F, -0.9763F, -1.0F, 3.0F, 2.0F, 2.0F, 0.01F, false);
    
    this.leftHorn = new ModelRenderer((Model)this);
    this.leftHorn.setRotationPoint(4.0F, 1.75F, -3.25F);
    this.head.addChild(this.leftHorn);
    setRotationAngle(this.leftHorn, 1.4835F, 0.5236F, -0.7854F);
    this.leftHorn.setTextureOffset(0, 60).addBox(-0.2905F, -0.9022F, -1.0059F, 4.0F, 2.0F, 2.0F, 0.0F, true);
    
    this.leftHorn2 = new ModelRenderer((Model)this);
    this.leftHorn2.setRotationPoint(4.0008F, -0.0562F, -0.8383F);
    this.leftHorn.addChild(this.leftHorn2);
    setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -0.8029F);
    this.leftHorn2.setTextureOffset(2, 55).addBox(-1.0913F, -1.3938F, -0.1676F, 2.0F, 2.0F, 2.0F, 0.01F, true);
    
    this.leftHorn3 = new ModelRenderer((Model)this);
    this.leftHorn3.setRotationPoint(0.5F, -0.25F, 0.75F);
    this.leftHorn2.addChild(this.leftHorn3);
    setRotationAngle(this.leftHorn3, -0.0349F, -0.2094F, 0.0873F);
    this.leftHorn3.setTextureOffset(0, 66).addBox(0.0918F, -1.1438F, -0.9617F, 3.0F, 2.0F, 2.0F, 0.01F, true);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(-4.5F, 7.0F, -3.5F);
    this.body.setTextureOffset(0, 0).addBox(0.0F, 1.0F, -7.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);
    
    this.lowerBack = new ModelRenderer((Model)this);
    this.lowerBack.setRotationPoint(4.5F, 4.5F, 9.5F);
    this.body.addChild(this.lowerBack);
    this.lowerBack.setTextureOffset(0, 20).addBox(-4.0F, -3.5F, -6.0F, 8.0F, 8.0F, 10.0F, -0.01F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, -0.5F, 3.5F);
    this.lowerBack.addChild(this.tail);
    setRotationAngle(this.tail, 0.3491F, 0.0F, 0.0F);

    
    this.tail_r1 = new ModelRenderer((Model)this);
    this.tail_r1.setRotationPoint(0.0F, 3.0E-4F, 0.5F);
    this.tail.addChild(this.tail_r1);
    setRotationAngle(this.tail_r1, 0.3054F, 0.0F, 0.0F);
    this.tail_r1.setTextureOffset(31, 3).addBox(-0.5F, -1.3933F, -0.8051F, 1.0F, 5.0F, 2.0F, 0.0F, false);
    
    this.collar = new ModelRenderer((Model)this);
    this.collar.setRotationPoint(4.0F, 3.5F, -7.75F);
    this.body.addChild(this.collar);
    this.collar.setTextureOffset(38, 31).addBox(-3.5F, -3.51F, -0.5F, 8.0F, 8.0F, 2.0F, -0.02F, false);
    
    this.collarMargin8 = new ModelRenderer((Model)this);
    this.collarMargin8.setRotationPoint(-3.0F, 4.0F, 1.0F);
    this.collar.addChild(this.collarMargin8);
    setRotationAngle(this.collarMargin8, 0.0F, 0.0F, 0.7854F);
    this.collarMargin8.setTextureOffset(45, 0).addBox(-1.35F, -10.7F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.collarMargin7 = new ModelRenderer((Model)this);
    this.collarMargin7.setRotationPoint(5.25F, 0.5F, 0.5F);
    this.collar.addChild(this.collarMargin7);
    this.collarMargin7.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.collarMargin6 = new ModelRenderer((Model)this);
    this.collarMargin6.setRotationPoint(4.25F, 4.25F, 1.0F);
    this.collar.addChild(this.collarMargin6);
    setRotationAngle(this.collarMargin6, 0.0F, 0.0F, -0.7854F);
    this.collarMargin6.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.collarMargin5 = new ModelRenderer((Model)this);
    this.collarMargin5.setRotationPoint(-1.0F, 5.75F, 1.0F);
    this.collar.addChild(this.collarMargin5);
    this.collarMargin5.setTextureOffset(42, 5).addBox(-1.4F, -1.05F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.collarMargin4 = new ModelRenderer((Model)this);
    this.collarMargin4.setRotationPoint(-3.0F, 4.0F, 1.0F);
    this.collar.addChild(this.collarMargin4);
    setRotationAngle(this.collarMargin4, 0.0F, 0.0F, 0.7854F);
    this.collarMargin4.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.collarMargin3 = new ModelRenderer((Model)this);
    this.collarMargin3.setRotationPoint(-4.0F, 0.5F, 0.5F);
    this.collar.addChild(this.collarMargin3);
    this.collarMargin3.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.collarMargin2 = new ModelRenderer((Model)this);
    this.collarMargin2.setRotationPoint(-3.5F, -3.25F, 1.0F);
    this.collar.addChild(this.collarMargin2);
    setRotationAngle(this.collarMargin2, 0.0F, 0.0F, -0.7854F);
    this.collarMargin2.setTextureOffset(45, 0).addBox(-1.35F, -0.15F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.collarMargin1 = new ModelRenderer((Model)this);
    this.collarMargin1.setRotationPoint(-1.0F, -3.5F, 1.0F);
    this.collar.addChild(this.collarMargin1);
    this.collarMargin1.setTextureOffset(42, 5).addBox(-1.4F, -0.99F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.collarFill2 = new ModelRenderer((Model)this);
    this.collarFill2.setRotationPoint(0.0F, 5.0F, 0.5F);
    this.collar.addChild(this.collarFill2);
    this.collarFill2.setTextureOffset(40, 38).addBox(-3.0F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.016F, false);
    
    this.collarFill = new ModelRenderer((Model)this);
    this.collarFill.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.collar.addChild(this.collarFill);
    this.collarFill.setTextureOffset(38, 31).addBox(4.0F, -2.25F, -0.5F, 1.0F, 6.0F, 2.0F, -0.015F, false);
    
    this.collarSpike12 = new ModelRenderer((Model)this);
    this.collarSpike12.setRotationPoint(-3.8536F, -3.8536F, 0.5F);
    this.collar.addChild(this.collarSpike12);
    setRotationAngle(this.collarSpike12, 0.0F, 0.0F, 0.7854F);
    this.collarSpike12.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.collarSpike11 = new ModelRenderer((Model)this);
    this.collarSpike11.setRotationPoint(-1.0F, -5.0F, 0.5F);
    this.collar.addChild(this.collarSpike11);
    this.collarSpike11.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.collarSpike10 = new ModelRenderer((Model)this);
    this.collarSpike10.setRotationPoint(2.0F, -5.0F, 0.5F);
    this.collar.addChild(this.collarSpike10);
    this.collarSpike10.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.collarSpike9 = new ModelRenderer((Model)this);
    this.collarSpike9.setRotationPoint(4.8964F, -3.8964F, 0.5F);
    this.collar.addChild(this.collarSpike9);
    setRotationAngle(this.collarSpike9, 0.0F, 0.0F, -0.7854F);
    this.collarSpike9.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.collarSpike8 = new ModelRenderer((Model)this);
    this.collarSpike8.setRotationPoint(6.0F, -1.0F, 0.5F);
    this.collar.addChild(this.collarSpike8);
    this.collarSpike8.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.collarSpike7 = new ModelRenderer((Model)this);
    this.collarSpike7.setRotationPoint(6.0F, 2.0F, 0.5F);
    this.collar.addChild(this.collarSpike7);
    this.collarSpike7.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.collarSpike6 = new ModelRenderer((Model)this);
    this.collarSpike6.setRotationPoint(4.8964F, 4.8964F, 0.5F);
    this.collar.addChild(this.collarSpike6);
    setRotationAngle(this.collarSpike6, 0.0F, 0.0F, 0.7854F);
    this.collarSpike6.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.collarSpike5 = new ModelRenderer((Model)this);
    this.collarSpike5.setRotationPoint(2.0F, 6.25F, 0.5F);
    this.collar.addChild(this.collarSpike5);
    this.collarSpike5.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.collarSpike4 = new ModelRenderer((Model)this);
    this.collarSpike4.setRotationPoint(-1.0F, 6.25F, 0.5F);
    this.collar.addChild(this.collarSpike4);
    this.collarSpike4.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.collarSpike3 = new ModelRenderer((Model)this);
    this.collarSpike3.setRotationPoint(-3.5F, 4.5F, 0.5F);
    this.collar.addChild(this.collarSpike3);
    setRotationAngle(this.collarSpike3, 0.0F, 0.0F, -0.7854F);
    this.collarSpike3.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.collarSpike2 = new ModelRenderer((Model)this);
    this.collarSpike2.setRotationPoint(-4.5F, 2.0F, 0.5F);
    this.collar.addChild(this.collarSpike2);
    this.collarSpike2.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.collarSpike1 = new ModelRenderer((Model)this);
    this.collarSpike1.setRotationPoint(-4.5F, -1.0F, 0.5F);
    this.collar.addChild(this.collarSpike1);
    this.collarSpike1.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftFrontLeg = new ModelRenderer((Model)this);
    this.leftFrontLeg.setRotationPoint(4.75F, 14.0F, -6.0F);
    this.leftFrontLeg.setTextureOffset(25, 49).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, true);
    
    this.leftFrontLegJoint = new ModelRenderer((Model)this);
    this.leftFrontLegJoint.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.leftFrontLeg.addChild(this.leftFrontLegJoint);
    this.leftFrontLegJoint.setTextureOffset(6, 6).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.3F, true);
    
    this.leftFrontLeg2 = new ModelRenderer((Model)this);
    this.leftFrontLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.leftFrontLeg.addChild(this.leftFrontLeg2);
    this.leftFrontLeg2.setTextureOffset(25, 58).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);
    
    this.leftFrontNail1 = new ModelRenderer((Model)this);
    this.leftFrontNail1.setRotationPoint(0.0F, 3.1F, 6.5F);
    this.leftFrontLeg2.addChild(this.leftFrontNail1);
    setRotationAngle(this.leftFrontNail1, -0.1211F, -0.4883F, -0.0394F);
    this.leftFrontNail1.setTextureOffset(6, 3).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, true);
    
    this.leftFrontNail2 = new ModelRenderer((Model)this);
    this.leftFrontNail2.setRotationPoint(0.0F, 3.1F, 6.5F);
    this.leftFrontLeg2.addChild(this.leftFrontNail2);
    setRotationAngle(this.leftFrontNail2, -0.1211F, 0.4883F, 0.0394F);
    this.leftFrontNail2.setTextureOffset(6, 3).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, true);
    
    this.leftLegCollar = new ModelRenderer((Model)this);
    this.leftLegCollar.setRotationPoint(2.25F, -1.51F, 0.25F);
    this.leftFrontLeg.addChild(this.leftLegCollar);
    setRotationAngle(this.leftLegCollar, 0.0F, -1.5708F, 0.0F);
    this.leftLegCollar.setTextureOffset(38, 31).addBox(-4.0F, -4.0F, -1.0F, 8.0F, 8.0F, 2.0F, -0.02F, false);
    
    this.leftLegCollarMargin1 = new ModelRenderer((Model)this);
    this.leftLegCollarMargin1.setRotationPoint(-3.5F, 3.51F, 0.5F);
    this.leftLegCollar.addChild(this.leftLegCollarMargin1);
    setRotationAngle(this.leftLegCollarMargin1, 0.0F, 0.0F, 0.7854F);
    this.leftLegCollarMargin1.setTextureOffset(45, 0).addBox(-1.35F, -10.7F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.leftLegCollarMargin2 = new ModelRenderer((Model)this);
    this.leftLegCollarMargin2.setRotationPoint(4.75F, 0.01F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarMargin2);
    this.leftLegCollarMargin2.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.leftLegCollarMargin3 = new ModelRenderer((Model)this);
    this.leftLegCollarMargin3.setRotationPoint(3.75F, 3.76F, 0.5F);
    this.leftLegCollar.addChild(this.leftLegCollarMargin3);
    setRotationAngle(this.leftLegCollarMargin3, 0.0F, 0.0F, -0.7854F);
    this.leftLegCollarMargin3.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.leftLegCollarMargin4 = new ModelRenderer((Model)this);
    this.leftLegCollarMargin4.setRotationPoint(-1.5F, 5.26F, 0.5F);
    this.leftLegCollar.addChild(this.leftLegCollarMargin4);
    this.leftLegCollarMargin4.setTextureOffset(42, 5).addBox(-1.4F, -1.05F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftLegCollarMargin5 = new ModelRenderer((Model)this);
    this.leftLegCollarMargin5.setRotationPoint(-3.5F, 3.51F, 0.5F);
    this.leftLegCollar.addChild(this.leftLegCollarMargin5);
    setRotationAngle(this.leftLegCollarMargin5, 0.0F, 0.0F, 0.7854F);
    this.leftLegCollarMargin5.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.leftLegCollarMargin6 = new ModelRenderer((Model)this);
    this.leftLegCollarMargin6.setRotationPoint(-4.5F, 0.01F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarMargin6);
    this.leftLegCollarMargin6.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.leftLegCollarMargin7 = new ModelRenderer((Model)this);
    this.leftLegCollarMargin7.setRotationPoint(-4.0F, -3.74F, 0.5F);
    this.leftLegCollar.addChild(this.leftLegCollarMargin7);
    setRotationAngle(this.leftLegCollarMargin7, 0.0F, 0.0F, -0.7854F);
    this.leftLegCollarMargin7.setTextureOffset(45, 0).addBox(-1.35F, -0.15F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.leftLegCollarMargin8 = new ModelRenderer((Model)this);
    this.leftLegCollarMargin8.setRotationPoint(-1.5F, -3.99F, 0.5F);
    this.leftLegCollar.addChild(this.leftLegCollarMargin8);
    this.leftLegCollarMargin8.setTextureOffset(42, 5).addBox(-1.4F, -0.99F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftLegCollarFill1 = new ModelRenderer((Model)this);
    this.leftLegCollarFill1.setRotationPoint(-0.5F, 4.51F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarFill1);
    this.leftLegCollarFill1.setTextureOffset(40, 38).addBox(-3.0F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.016F, false);
    
    this.leftLegCollarFill2 = new ModelRenderer((Model)this);
    this.leftLegCollarFill2.setRotationPoint(-0.5F, -0.49F, -0.5F);
    this.leftLegCollar.addChild(this.leftLegCollarFill2);
    this.leftLegCollarFill2.setTextureOffset(38, 31).addBox(4.0F, -2.25F, -0.5F, 1.0F, 6.0F, 2.0F, -0.015F, false);
    
    this.leftLegCollarSpike1 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike1.setRotationPoint(-4.3536F, -4.3436F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike1);
    setRotationAngle(this.leftLegCollarSpike1, 0.0F, 0.0F, 0.7854F);
    this.leftLegCollarSpike1.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike2 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike2.setRotationPoint(-1.5F, -5.49F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike2);
    this.leftLegCollarSpike2.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike3 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike3.setRotationPoint(1.5F, -5.49F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike3);
    this.leftLegCollarSpike3.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike4 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike4.setRotationPoint(4.3964F, -4.3864F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike4);
    setRotationAngle(this.leftLegCollarSpike4, 0.0F, 0.0F, -0.7854F);
    this.leftLegCollarSpike4.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike5 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike5.setRotationPoint(5.5F, -1.49F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike5);
    this.leftLegCollarSpike5.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike6 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike6.setRotationPoint(5.5F, 1.51F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike6);
    this.leftLegCollarSpike6.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike7 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike7.setRotationPoint(4.3964F, 4.4064F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike7);
    setRotationAngle(this.leftLegCollarSpike7, 0.0F, 0.0F, 0.7854F);
    this.leftLegCollarSpike7.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike8 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike8.setRotationPoint(1.5F, 5.76F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike8);
    this.leftLegCollarSpike8.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike9 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike9.setRotationPoint(-1.5F, 5.76F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike9);
    this.leftLegCollarSpike9.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike10 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike10.setRotationPoint(-4.0F, 4.01F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike10);
    setRotationAngle(this.leftLegCollarSpike10, 0.0F, 0.0F, -0.7854F);
    this.leftLegCollarSpike10.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike11 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike11.setRotationPoint(-5.0F, 1.51F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike11);
    this.leftLegCollarSpike11.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarSpike12 = new ModelRenderer((Model)this);
    this.leftLegCollarSpike12.setRotationPoint(-5.0F, -1.49F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarSpike12);
    this.leftLegCollarSpike12.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftLegCollarConnection = new ModelRenderer((Model)this);
    this.leftLegCollarConnection.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftLegCollar.addChild(this.leftLegCollarConnection);
    this.leftLegCollarConnection.setTextureOffset(40, 20).addBox(-3.5F, -3.5F, 0.5F, 7.0F, 7.0F, 2.0F, 0.0F, false);
    
    this.leftRearLeg = new ModelRenderer((Model)this);
    this.leftRearLeg.setRotationPoint(4.0F, 14.0F, 8.0F);
    this.leftRearLeg.setTextureOffset(17, 50).addBox(-0.75F, 0.0F, -1.25F, 1.0F, 10.0F, 2.0F, 0.0F, true);
    
    this.leftRearNail1 = new ModelRenderer((Model)this);
    this.leftRearNail1.setRotationPoint(0.0F, 9.1F, 6.5F);
    this.leftRearLeg.addChild(this.leftRearNail1);
    setRotationAngle(this.leftRearNail1, -0.1211F, 0.4883F, 0.0394F);
    this.leftRearNail1.setTextureOffset(6, 3).addBox(2.6808F, 0.7975F, -7.4708F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearNail2 = new ModelRenderer((Model)this);
    this.leftRearNail2.setRotationPoint(0.0F, 9.1F, 6.5F);
    this.leftRearLeg.addChild(this.leftRearNail2);
    setRotationAngle(this.leftRearNail2, -0.1211F, -0.4883F, -0.0394F);
    this.leftRearNail2.setTextureOffset(6, 3).addBox(-4.122F, 0.7496F, -7.2405F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollar = new ModelRenderer((Model)this);
    this.leftRearLegCollar.setRotationPoint(2.0F, -1.01F, -0.15F);
    this.leftRearLeg.addChild(this.leftRearLegCollar);
    setRotationAngle(this.leftRearLegCollar, 0.0F, -1.5708F, 0.0F);
    this.leftRearLegCollar.setTextureOffset(60, 34).addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, -0.02F, false);
    
    this.leftRearLegCollarMargin1 = new ModelRenderer((Model)this);
    this.leftRearLegCollarMargin1.setRotationPoint(-2.1F, 2.01F, 0.5F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin1);
    setRotationAngle(this.leftRearLegCollarMargin1, 0.0F, 0.0F, 0.7854F);
    this.leftRearLegCollarMargin1.setTextureOffset(45, 0).addBox(-1.35F, -6.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.leftRearLegCollarMargin2 = new ModelRenderer((Model)this);
    this.leftRearLegCollarMargin2.setRotationPoint(3.15F, -1.49F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin2);
    this.leftRearLegCollarMargin2.setTextureOffset(42, 10).addBox(-0.55F, 0.1F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    
    this.leftRearLegCollarMargin3 = new ModelRenderer((Model)this);
    this.leftRearLegCollarMargin3.setRotationPoint(2.15F, 2.26F, 0.5F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin3);
    setRotationAngle(this.leftRearLegCollarMargin3, 0.0F, 0.0F, -0.7854F);
    this.leftRearLegCollarMargin3.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.leftRearLegCollarMargin4 = new ModelRenderer((Model)this);
    this.leftRearLegCollarMargin4.setRotationPoint(-0.1F, 3.76F, 0.5F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin4);
    this.leftRearLegCollarMargin4.setTextureOffset(45, 0).addBox(-1.4F, -1.05F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftRearLegCollarMargin5 = new ModelRenderer((Model)this);
    this.leftRearLegCollarMargin5.setRotationPoint(-2.1F, 2.01F, 0.5F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin5);
    setRotationAngle(this.leftRearLegCollarMargin5, 0.0F, 0.0F, 0.7854F);
    this.leftRearLegCollarMargin5.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.leftRearLegCollarMargin6 = new ModelRenderer((Model)this);
    this.leftRearLegCollarMargin6.setRotationPoint(-3.1F, -1.49F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin6);
    this.leftRearLegCollarMargin6.setTextureOffset(42, 10).addBox(-0.5F, 0.1F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    
    this.leftRearLegCollarMargin7 = new ModelRenderer((Model)this);
    this.leftRearLegCollarMargin7.setRotationPoint(-2.6F, -2.24F, 0.5F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin7);
    setRotationAngle(this.leftRearLegCollarMargin7, 0.0F, 0.0F, -0.7854F);
    this.leftRearLegCollarMargin7.setTextureOffset(45, 0).addBox(-1.34F, -0.1F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.leftRearLegCollarMargin8 = new ModelRenderer((Model)this);
    this.leftRearLegCollarMargin8.setRotationPoint(-0.1F, -5.49F, 0.5F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin8);
    this.leftRearLegCollarMargin8.setTextureOffset(45, 0).addBox(-1.4F, 2.01F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftRearLegCollarSpike1 = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpike1.setRotationPoint(-2.7768F, -2.8383F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike1);
    setRotationAngle(this.leftRearLegCollarSpike1, 0.0F, 0.0F, 0.7854F);
    this.leftRearLegCollarSpike1.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollarSpike2 = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpike2.setRotationPoint(-0.1F, -3.99F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike2);
    this.leftRearLegCollarSpike2.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollarSpike3 = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpike3.setRotationPoint(2.7912F, -2.8812F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike3);
    setRotationAngle(this.leftRearLegCollarSpike3, 0.0F, 0.0F, -0.7854F);
    this.leftRearLegCollarSpike3.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollarSpike4 = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpike4.setRotationPoint(3.9F, 0.01F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike4);
    this.leftRearLegCollarSpike4.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollarSpike5 = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpike5.setRotationPoint(3.0464F, 3.1564F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike5);
    setRotationAngle(this.leftRearLegCollarSpike5, 0.0F, 0.0F, 0.7854F);
    this.leftRearLegCollarSpike5.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollarSpike6 = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpike6.setRotationPoint(-0.1F, 4.26F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike6);
    this.leftRearLegCollarSpike6.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollarSpike7 = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpike7.setRotationPoint(-2.6F, 2.51F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike7);
    setRotationAngle(this.leftRearLegCollarSpike7, 0.0F, 0.0F, -0.7854F);
    this.leftRearLegCollarSpike7.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollarSpike8 = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpike8.setRotationPoint(-3.6F, 0.01F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike8);
    this.leftRearLegCollarSpike8.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearLegCollarSpikeConnection = new ModelRenderer((Model)this);
    this.leftRearLegCollarSpikeConnection.setRotationPoint(1.4F, -1.5F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarSpikeConnection);
    this.leftRearLegCollarSpikeConnection.setTextureOffset(61, 23).addBox(-3.5F, -0.5F, 0.5F, 4.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.leftRearLegCollarFill3 = new ModelRenderer((Model)this);
    this.leftRearLegCollarFill3.setRotationPoint(-2.6F, 0.0F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarFill3);
    this.leftRearLegCollarFill3.setTextureOffset(38, 34).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, -0.025F, false);
    
    this.leftRearLegCollarFill2 = new ModelRenderer((Model)this);
    this.leftRearLegCollarFill2.setRotationPoint(1.4F, -1.5F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarFill2);
    this.leftRearLegCollarFill2.setTextureOffset(38, 34).addBox(0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, -0.025F, false);
    
    this.leftRearLegCollarFill1 = new ModelRenderer((Model)this);
    this.leftRearLegCollarFill1.setRotationPoint(1.4F, -1.5F, 0.0F);
    this.leftRearLegCollar.addChild(this.leftRearLegCollarFill1);
    this.leftRearLegCollarFill1.setTextureOffset(45, 0).addBox(-3.0F, 3.75F, -1.0F, 3.0F, 1.0F, 2.0F, -0.025F, false);
    
    this.rightFrontLeg = new ModelRenderer((Model)this);
    this.rightFrontLeg.setRotationPoint(-4.5F, 14.0F, -6.0F);
    this.rightFrontLeg.setTextureOffset(25, 49).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.rightFrontLegJoint = new ModelRenderer((Model)this);
    this.rightFrontLegJoint.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.rightFrontLeg.addChild(this.rightFrontLegJoint);
    this.rightFrontLegJoint.setTextureOffset(6, 6).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.3F, false);
    
    this.rightFrontLeg2 = new ModelRenderer((Model)this);
    this.rightFrontLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.rightFrontLeg.addChild(this.rightFrontLeg2);
    this.rightFrontLeg2.setTextureOffset(25, 58).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.leftFrontNail3 = new ModelRenderer((Model)this);
    this.leftFrontNail3.setRotationPoint(0.0F, 3.1F, 6.5F);
    this.rightFrontLeg2.addChild(this.leftFrontNail3);
    setRotationAngle(this.leftFrontNail3, -0.1211F, -0.4883F, -0.0394F);
    this.leftFrontNail3.setTextureOffset(6, 3).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftFrontNail4 = new ModelRenderer((Model)this);
    this.leftFrontNail4.setRotationPoint(0.0F, 3.1F, 6.5F);
    this.rightFrontLeg2.addChild(this.leftFrontNail4);
    setRotationAngle(this.leftFrontNail4, -0.1211F, 0.4883F, 0.0394F);
    this.leftFrontNail4.setTextureOffset(6, 3).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollar = new ModelRenderer((Model)this);
    this.rightLegCollar.setRotationPoint(-2.25F, -1.51F, 0.25F);
    this.rightFrontLeg.addChild(this.rightLegCollar);
    setRotationAngle(this.rightLegCollar, 0.0F, -1.5708F, 0.0F);
    this.rightLegCollar.setTextureOffset(38, 31).addBox(-4.0F, -4.0F, -1.0F, 8.0F, 8.0F, 2.0F, -0.02F, false);
    
    this.rightLegCollarMargin1 = new ModelRenderer((Model)this);
    this.rightLegCollarMargin1.setRotationPoint(-3.5F, 3.51F, 0.5F);
    this.rightLegCollar.addChild(this.rightLegCollarMargin1);
    setRotationAngle(this.rightLegCollarMargin1, 0.0F, 0.0F, 0.7854F);
    this.rightLegCollarMargin1.setTextureOffset(45, 0).addBox(-1.35F, -10.7F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightLegCollarMargin2 = new ModelRenderer((Model)this);
    this.rightLegCollarMargin2.setRotationPoint(4.75F, 0.01F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarMargin2);
    this.rightLegCollarMargin2.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.rightLegCollarMargin3 = new ModelRenderer((Model)this);
    this.rightLegCollarMargin3.setRotationPoint(3.75F, 3.76F, 0.5F);
    this.rightLegCollar.addChild(this.rightLegCollarMargin3);
    setRotationAngle(this.rightLegCollarMargin3, 0.0F, 0.0F, -0.7854F);
    this.rightLegCollarMargin3.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightLegCollarMargin4 = new ModelRenderer((Model)this);
    this.rightLegCollarMargin4.setRotationPoint(-1.5F, 5.26F, 0.5F);
    this.rightLegCollar.addChild(this.rightLegCollarMargin4);
    this.rightLegCollarMargin4.setTextureOffset(42, 5).addBox(-1.4F, -1.05F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightLegCollarMargin5 = new ModelRenderer((Model)this);
    this.rightLegCollarMargin5.setRotationPoint(-3.5F, 3.51F, 0.5F);
    this.rightLegCollar.addChild(this.rightLegCollarMargin5);
    setRotationAngle(this.rightLegCollarMargin5, 0.0F, 0.0F, 0.7854F);
    this.rightLegCollarMargin5.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightLegCollarMargin6 = new ModelRenderer((Model)this);
    this.rightLegCollarMargin6.setRotationPoint(-4.5F, 0.01F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarMargin6);
    this.rightLegCollarMargin6.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.rightLegCollarMargin7 = new ModelRenderer((Model)this);
    this.rightLegCollarMargin7.setRotationPoint(-4.0F, -3.74F, 0.5F);
    this.rightLegCollar.addChild(this.rightLegCollarMargin7);
    setRotationAngle(this.rightLegCollarMargin7, 0.0F, 0.0F, -0.7854F);
    this.rightLegCollarMargin7.setTextureOffset(45, 0).addBox(-1.35F, -0.15F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightLegCollarMargin8 = new ModelRenderer((Model)this);
    this.rightLegCollarMargin8.setRotationPoint(-1.5F, -3.99F, 0.5F);
    this.rightLegCollar.addChild(this.rightLegCollarMargin8);
    this.rightLegCollarMargin8.setTextureOffset(42, 5).addBox(-1.4F, -0.99F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightLegCollarFill1 = new ModelRenderer((Model)this);
    this.rightLegCollarFill1.setRotationPoint(-0.5F, 4.51F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarFill1);
    this.rightLegCollarFill1.setTextureOffset(40, 38).addBox(-3.0F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.016F, false);
    
    this.rightLegCollarFill2 = new ModelRenderer((Model)this);
    this.rightLegCollarFill2.setRotationPoint(-0.5F, -0.49F, -0.5F);
    this.rightLegCollar.addChild(this.rightLegCollarFill2);
    this.rightLegCollarFill2.setTextureOffset(38, 31).addBox(4.0F, -2.25F, -0.5F, 1.0F, 6.0F, 2.0F, -0.015F, false);
    
    this.rightLegCollarSpike1 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike1.setRotationPoint(-4.3536F, -4.3436F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike1);
    setRotationAngle(this.rightLegCollarSpike1, 0.0F, 0.0F, 0.7854F);
    this.rightLegCollarSpike1.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike2 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike2.setRotationPoint(-1.5F, -5.49F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike2);
    this.rightLegCollarSpike2.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike3 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike3.setRotationPoint(1.5F, -5.49F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike3);
    this.rightLegCollarSpike3.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike4 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike4.setRotationPoint(4.3964F, -4.3864F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike4);
    setRotationAngle(this.rightLegCollarSpike4, 0.0F, 0.0F, -0.7854F);
    this.rightLegCollarSpike4.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike5 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike5.setRotationPoint(5.5F, -1.49F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike5);
    this.rightLegCollarSpike5.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike6 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike6.setRotationPoint(5.5F, 1.51F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike6);
    this.rightLegCollarSpike6.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike7 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike7.setRotationPoint(4.3964F, 4.4064F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike7);
    setRotationAngle(this.rightLegCollarSpike7, 0.0F, 0.0F, 0.7854F);
    this.rightLegCollarSpike7.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike8 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike8.setRotationPoint(1.5F, 5.76F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike8);
    this.rightLegCollarSpike8.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike9 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike9.setRotationPoint(-1.5F, 5.76F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike9);
    this.rightLegCollarSpike9.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike10 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike10.setRotationPoint(-4.0F, 4.01F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike10);
    setRotationAngle(this.rightLegCollarSpike10, 0.0F, 0.0F, -0.7854F);
    this.rightLegCollarSpike10.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike11 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike11.setRotationPoint(-5.0F, 1.51F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike11);
    this.rightLegCollarSpike11.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarSpike12 = new ModelRenderer((Model)this);
    this.rightLegCollarSpike12.setRotationPoint(-5.0F, -1.49F, 0.0F);
    this.rightLegCollar.addChild(this.rightLegCollarSpike12);
    this.rightLegCollarSpike12.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightLegCollarConnector = new ModelRenderer((Model)this);
    this.rightLegCollarConnector.setRotationPoint(0.0F, 0.0F, -3.0F);
    this.rightLegCollar.addChild(this.rightLegCollarConnector);
    this.rightLegCollarConnector.setTextureOffset(40, 20).addBox(-3.5F, -3.5F, 0.5F, 7.0F, 7.0F, 2.0F, 0.0F, false);
    
    this.rightRearLeg = new ModelRenderer((Model)this);
    this.rightRearLeg.setRotationPoint(-3.5F, 14.0F, 8.0F);
    this.rightRearLeg.setTextureOffset(17, 50).addBox(-0.75F, 0.0F, -1.25F, 1.0F, 10.0F, 2.0F, 0.0F, false);
    
    this.rightRearNail1 = new ModelRenderer((Model)this);
    this.rightRearNail1.setRotationPoint(0.0F, 9.1F, 6.5F);
    this.rightRearLeg.addChild(this.rightRearNail1);
    setRotationAngle(this.rightRearNail1, -0.1211F, 0.4883F, 0.0394F);
    this.rightRearNail1.setTextureOffset(6, 3).addBox(2.6808F, 0.7975F, -7.4708F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearNail2 = new ModelRenderer((Model)this);
    this.rightRearNail2.setRotationPoint(0.0F, 9.1F, 6.5F);
    this.rightRearLeg.addChild(this.rightRearNail2);
    setRotationAngle(this.rightRearNail2, -0.1211F, -0.4883F, -0.0394F);
    this.rightRearNail2.setTextureOffset(6, 3).addBox(-4.122F, 0.7496F, -7.2405F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollar = new ModelRenderer((Model)this);
    this.rightRearLegCollar.setRotationPoint(-2.5F, -1.01F, -0.15F);
    this.rightRearLeg.addChild(this.rightRearLegCollar);
    setRotationAngle(this.rightRearLegCollar, 0.0F, -1.5708F, 0.0F);
    this.rightRearLegCollar.setTextureOffset(60, 34).addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, -0.02F, false);
    
    this.rightRearLegCollarMargin1 = new ModelRenderer((Model)this);
    this.rightRearLegCollarMargin1.setRotationPoint(-2.1F, 2.01F, 0.5F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin1);
    setRotationAngle(this.rightRearLegCollarMargin1, 0.0F, 0.0F, 0.7854F);
    this.rightRearLegCollarMargin1.setTextureOffset(45, 0).addBox(-1.35F, -6.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightRearLegCollarMargin2 = new ModelRenderer((Model)this);
    this.rightRearLegCollarMargin2.setRotationPoint(3.15F, -1.49F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin2);
    this.rightRearLegCollarMargin2.setTextureOffset(42, 10).addBox(-0.55F, 0.1F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    
    this.rightRearLegCollarMargin3 = new ModelRenderer((Model)this);
    this.rightRearLegCollarMargin3.setRotationPoint(2.15F, 2.26F, 0.5F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin3);
    setRotationAngle(this.rightRearLegCollarMargin3, 0.0F, 0.0F, -0.7854F);
    this.rightRearLegCollarMargin3.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightRearLegCollarMargin4 = new ModelRenderer((Model)this);
    this.rightRearLegCollarMargin4.setRotationPoint(-0.1F, 3.76F, 0.5F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin4);
    this.rightRearLegCollarMargin4.setTextureOffset(45, 0).addBox(-1.4F, -1.05F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightRearLegCollarMargin5 = new ModelRenderer((Model)this);
    this.rightRearLegCollarMargin5.setRotationPoint(-2.1F, 2.01F, 0.5F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin5);
    setRotationAngle(this.rightRearLegCollarMargin5, 0.0F, 0.0F, 0.7854F);
    this.rightRearLegCollarMargin5.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightRearLegCollarMargin6 = new ModelRenderer((Model)this);
    this.rightRearLegCollarMargin6.setRotationPoint(-3.1F, -1.49F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin6);
    this.rightRearLegCollarMargin6.setTextureOffset(42, 10).addBox(-0.5F, 0.1F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    
    this.rightRearLegCollarMargin7 = new ModelRenderer((Model)this);
    this.rightRearLegCollarMargin7.setRotationPoint(-2.6F, -2.24F, 0.5F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin7);
    setRotationAngle(this.rightRearLegCollarMargin7, 0.0F, 0.0F, -0.7854F);
    this.rightRearLegCollarMargin7.setTextureOffset(45, 0).addBox(-1.34F, -0.1F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightRearLegCollarMargin8 = new ModelRenderer((Model)this);
    this.rightRearLegCollarMargin8.setRotationPoint(-0.1F, -5.49F, 0.5F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin8);
    this.rightRearLegCollarMargin8.setTextureOffset(45, 0).addBox(-1.4F, 2.01F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightRearLegCollarSpike1 = new ModelRenderer((Model)this);
    this.rightRearLegCollarSpike1.setRotationPoint(-2.7768F, -2.8383F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike1);
    setRotationAngle(this.rightRearLegCollarSpike1, 0.0F, 0.0F, 0.7854F);
    this.rightRearLegCollarSpike1.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollarSpike2 = new ModelRenderer((Model)this);
    this.rightRearLegCollarSpike2.setRotationPoint(-0.1F, -3.99F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike2);
    this.rightRearLegCollarSpike2.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollarSpike3 = new ModelRenderer((Model)this);
    this.rightRearLegCollarSpike3.setRotationPoint(2.7912F, -2.8812F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike3);
    setRotationAngle(this.rightRearLegCollarSpike3, 0.0F, 0.0F, -0.7854F);
    this.rightRearLegCollarSpike3.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollarSpike4 = new ModelRenderer((Model)this);
    this.rightRearLegCollarSpike4.setRotationPoint(3.9F, 0.01F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike4);
    this.rightRearLegCollarSpike4.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollarSpike5 = new ModelRenderer((Model)this);
    this.rightRearLegCollarSpike5.setRotationPoint(3.0464F, 3.1564F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike5);
    setRotationAngle(this.rightRearLegCollarSpike5, 0.0F, 0.0F, 0.7854F);
    this.rightRearLegCollarSpike5.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollarSpike6 = new ModelRenderer((Model)this);
    this.rightRearLegCollarSpike6.setRotationPoint(-0.1F, 4.26F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike6);
    this.rightRearLegCollarSpike6.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollarSpike7 = new ModelRenderer((Model)this);
    this.rightRearLegCollarSpike7.setRotationPoint(-2.6F, 2.51F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike7);
    setRotationAngle(this.rightRearLegCollarSpike7, 0.0F, 0.0F, -0.7854F);
    this.rightRearLegCollarSpike7.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollarSpike8 = new ModelRenderer((Model)this);
    this.rightRearLegCollarSpike8.setRotationPoint(-3.6F, 0.01F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike8);
    this.rightRearLegCollarSpike8.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearLegCollarConnection = new ModelRenderer((Model)this);
    this.rightRearLegCollarConnection.setRotationPoint(-0.1F, 0.0F, -1.5F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarConnection);
    this.rightRearLegCollarConnection.setTextureOffset(61, 23).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.rightRearLegCollarFill1 = new ModelRenderer((Model)this);
    this.rightRearLegCollarFill1.setRotationPoint(-2.6F, 0.0F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarFill1);
    this.rightRearLegCollarFill1.setTextureOffset(42, 10).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, -0.025F, false);
    
    this.rightRearLegCollarFill2 = new ModelRenderer((Model)this);
    this.rightRearLegCollarFill2.setRotationPoint(1.4F, -1.5F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarFill2);
    this.rightRearLegCollarFill2.setTextureOffset(38, 34).addBox(0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, -0.025F, false);
    
    this.rightRearLegCollarFill3 = new ModelRenderer((Model)this);
    this.rightRearLegCollarFill3.setRotationPoint(1.4F, -1.5F, 0.0F);
    this.rightRearLegCollar.addChild(this.rightRearLegCollarFill3);
    this.rightRearLegCollarFill3.setTextureOffset(45, 0).addBox(-3.0F, 3.75F, -1.0F, 3.0F, 1.0F, 2.0F, -0.025F, false);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
    this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
    this.rightRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
    this.leftRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
    if (entity.isSprinting()) {
      this.tail.rotateAngleX = 1.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
    }
  }

  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


