/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.GunArrayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class WootzSteelArmorModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   private final ModelRenderer armor;
/*     */   private final ModelRenderer rightArmArmor;
/*     */   private final ModelRenderer leftArmArmor;
/*     */   private final ModelRenderer bodyArmor;
/*     */   private final ModelRenderer rightShoulderBase;
/*     */   private final ModelRenderer rightShoulder1;
/*     */   private final ModelRenderer rightShoulder2;
/*     */   private final ModelRenderer rightShoulderGuns;
/*     */   private final ModelRenderer rightShoulderGun1;
/*     */   private final ModelRenderer rightShoulderGun2;
/*     */   private final ModelRenderer leftShoulderBase;
/*     */   private final ModelRenderer leftShoulder1;
/*     */   private final ModelRenderer leftShoulder2;
/*     */   private final ModelRenderer leftShoulderGuns;
/*     */   private final ModelRenderer leftShoulderGun2;
/*     */   private final ModelRenderer leftShoulderGun1;
/*     */   
/*     */   public WootzSteelArmorModel() {
/*  38 */     super(1.0F);
/*  39 */     this.textureWidth = 64;
/*  40 */     this.textureHeight = 32;
/*     */     
/*  42 */     this.armor = new ModelRenderer((Model)this);
/*  43 */     this.armor.setRotationPoint(0.0F, 0.25F, 0.0F);
/*     */     
/*  45 */     this.rightArmArmor = new ModelRenderer((Model)this);
/*  46 */     this.rightArmArmor.setRotationPoint(-6.0F, 1.75F, 0.0F);
/*     */     
/*  48 */     this.rightArmArmor.setTextureOffset(34, 0).addBox(-3.5F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/*  50 */     this.leftArmArmor = new ModelRenderer((Model)this);
/*  51 */     this.leftArmArmor.setRotationPoint(6.0F, 1.75F, 0.0F);
/*     */     
/*  53 */     this.leftArmArmor.setTextureOffset(34, 0).addBox(-1.5F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/*  55 */     this.bodyArmor = new ModelRenderer((Model)this);
/*  56 */     this.bodyArmor.setRotationPoint(0.0F, -0.25F, 0.0F);
/*  57 */     this.armor.addChild(this.bodyArmor);
/*  58 */     this.bodyArmor.setTextureOffset(0, 0).addBox(-5.5F, 0.0F, -3.0F, 11.0F, 11.0F, 6.0F, 0.0F, false);
/*     */     
/*  60 */     this.rightShoulderBase = new ModelRenderer((Model)this);
/*  61 */     this.rightShoulderBase.setRotationPoint(-8.0F, 0.25F, 3.4F);
/*  62 */     this.armor.addChild(this.rightShoulderBase);
/*  63 */     setRotateAngle(this.rightShoulderBase, 0.0F, 0.0456F, -0.5236F);
/*  64 */     this.rightShoulderBase.setTextureOffset(21, 17).addBox(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
/*     */     
/*  66 */     this.rightShoulder1 = new ModelRenderer((Model)this);
/*  67 */     this.rightShoulder1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  68 */     this.rightShoulderBase.addChild(this.rightShoulder1);
/*  69 */     this.rightShoulder1.setTextureOffset(0, 24).addBox(-3.5F, -1.0F, -6.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
/*     */     
/*  71 */     this.rightShoulder2 = new ModelRenderer((Model)this);
/*  72 */     this.rightShoulder2.setRotationPoint(8.0F, -0.5F, -3.4F);
/*  73 */     this.rightShoulder1.addChild(this.rightShoulder2);
/*  74 */     this.rightShoulder2.setTextureOffset(0, 17).addBox(-11.0531F, -1.5F, -2.6F, 6.0F, 1.0F, 6.0F, 0.0F, false);
/*     */     
/*  76 */     this.rightShoulderGuns = new ModelRenderer((Model)this);
/*  77 */     this.rightShoulderGuns.setRotationPoint(-6.0F, -0.25F, 0.0F);
/*  78 */     this.armor.addChild(this.rightShoulderGuns);
/*  79 */     this.rightShoulderGuns.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);
/*     */     
/*  81 */     this.rightShoulderGun1 = new ModelRenderer((Model)this);
/*  82 */     this.rightShoulderGun1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  83 */     this.rightShoulderGuns.addChild(this.rightShoulderGun1);
/*  84 */     this.rightShoulderGun1.setTextureOffset(48, 16).addBox(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  86 */     this.rightShoulderGun2 = new ModelRenderer((Model)this);
/*  87 */     this.rightShoulderGun2.setRotationPoint(-3.9F, 1.0F, 0.0F);
/*  88 */     this.rightShoulderGuns.addChild(this.rightShoulderGun2);
/*  89 */     this.rightShoulderGun2.setTextureOffset(48, 16).addBox(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  91 */     this.leftShoulderBase = new ModelRenderer((Model)this);
/*  92 */     this.leftShoulderBase.setRotationPoint(8.0F, 0.25F, 3.4F);
/*  93 */     this.armor.addChild(this.leftShoulderBase);
/*  94 */     setRotateAngle(this.leftShoulderBase, 0.0F, 0.0456F, 0.5236F);
/*  95 */     this.leftShoulderBase.setTextureOffset(21, 17).addBox(-4.0F, 0.0F, -7.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
/*     */     
/*  97 */     this.leftShoulder1 = new ModelRenderer((Model)this);
/*  98 */     this.leftShoulder1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  99 */     this.leftShoulderBase.addChild(this.leftShoulder1);
/* 100 */     this.leftShoulder1.setTextureOffset(0, 24).addBox(-3.5F, -1.0F, -6.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
/*     */     
/* 102 */     this.leftShoulder2 = new ModelRenderer((Model)this);
/* 103 */     this.leftShoulder2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 104 */     this.leftShoulder1.addChild(this.leftShoulder2);
/* 105 */     this.leftShoulder2.setTextureOffset(0, 17).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
/*     */     
/* 107 */     this.leftShoulderGuns = new ModelRenderer((Model)this);
/* 108 */     this.leftShoulderGuns.setRotationPoint(6.0F, -0.25F, 0.0F);
/* 109 */     this.armor.addChild(this.leftShoulderGuns);
/* 110 */     this.leftShoulderGuns.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, false);
/*     */     
/* 112 */     this.leftShoulderGun2 = new ModelRenderer((Model)this);
/* 113 */     this.leftShoulderGun2.setRotationPoint(3.9F, 1.0F, 0.0F);
/* 114 */     this.leftShoulderGuns.addChild(this.leftShoulderGun2);
/* 115 */     this.leftShoulderGun2.setTextureOffset(48, 16).addBox(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 117 */     this.leftShoulderGun1 = new ModelRenderer((Model)this);
/* 118 */     this.leftShoulderGun1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 119 */     this.leftShoulderGuns.addChild(this.leftShoulderGun1);
/* 120 */     this.leftShoulderGun1.setTextureOffset(48, 16).addBox(-1.5F, -2.3F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 126 */     this.armor.copyModelAngles(this.bipedBody);
/* 127 */     this.leftArmArmor.copyModelAngles(this.bipedLeftArm);
/* 128 */     this.rightArmArmor.copyModelAngles(this.bipedRightArm);
/*     */     
/* 130 */     this.armor.render(matrixStack, buffer, packedLight, packedOverlay);
/* 131 */     this.leftArmArmor.render(matrixStack, buffer, packedLight, packedOverlay);
/* 132 */     this.rightArmArmor.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 138 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 140 */     boolean hasGunsOut = false;
/* 141 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/*     */       
/* 143 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 144 */       IAbilityData aprops = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/* 146 */       if (aprops.getEquippedAbility((Ability)GunArrayAbility.INSTANCE) != null)
/*     */       {
/* 148 */         Ability ability = aprops.getEquippedAbility((Ability)GunArrayAbility.INSTANCE);
/*     */         
/* 150 */         if (ability.isContinuous())
/*     */         {
/* 152 */           hasGunsOut = true;
/*     */         }
/*     */       }
/*     */     
/* 156 */     } else if (entity instanceof DonKriegEntity) {
/*     */       
/* 158 */       if (((DonKriegEntity)entity).getAnimation() == 120) {
/*     */         
/* 160 */         hasGunsOut = true;
/* 161 */         this.leftArmArmor.rotateAngleX -= 1.55F;
/* 162 */         this.rightArmArmor.rotateAngleX -= 1.55F;
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     if (hasGunsOut) {
/*     */       
/* 168 */       this.leftShoulderBase.rotateAngleX = (float)Math.toRadians(-70.0D);
/* 169 */       this.rightShoulderBase.rotateAngleX = (float)Math.toRadians(-70.0D);
/*     */     }
/*     */     else {
/*     */       
/* 173 */       this.leftShoulderGuns.showModel = false;
/* 174 */       this.rightShoulderGuns.showModel = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 180 */     model.rotateAngleX = x;
/* 181 */     model.rotateAngleY = y;
/* 182 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\armors\WootzSteelArmorModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */