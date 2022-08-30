/*     */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ public class DamnedPunkModel
/*     */   extends EntityModel<Entity>
/*     */ {
/*     */   public final ModelRenderer rightArm;
/*     */   private final ModelRenderer cube_r1;
/*     */   private final ModelRenderer cube_r2;
/*     */   private final ModelRenderer cube_r3;
/*     */   private final ModelRenderer cube_r4;
/*     */   private final ModelRenderer cube_r5;
/*     */   private final ModelRenderer cube_r6;
/*     */   private final ModelRenderer cube_r7;
/*     */   private final ModelRenderer cube_r8;
/*     */   private final ModelRenderer cube_r9;
/*     */   private final ModelRenderer cube_r10;
/*     */   private final ModelRenderer cube_r11;
/*     */   private final ModelRenderer cube_r12;
/*     */   private final ModelRenderer cube_r13;
/*     */   private final ModelRenderer cube_r14;
/*     */   private final ModelRenderer cube_r15;
/*     */   private final ModelRenderer cube_r16;
/*     */   private final ModelRenderer cube_r17;
/*     */   private final ModelRenderer cube_r18;
/*     */   private final ModelRenderer cube_r19;
/*     */   private final ModelRenderer RightArm_r1;
/*     */   private final ModelRenderer RightArm_r2;
/*     */   private final ModelRenderer RightArm_r3;
/*     */   private final ModelRenderer octagon;
/*     */   private final ModelRenderer octagon_r1;
/*     */   private final ModelRenderer octagon3;
/*     */   private final ModelRenderer octagon_r2;
/*     */   private final ModelRenderer octagon2;
/*     */   private final ModelRenderer octagon_r3;
/*     */   
/*     */   public DamnedPunkModel() {
/*  44 */     this.textureWidth = 128;
/*  45 */     this.textureHeight = 128;
/*     */     
/*  47 */     this.rightArm = new ModelRenderer((Model)this);
/*  48 */     this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/*  49 */     this.rightArm.setTextureOffset(0, 16).addBox(-9.0F, -4.0F, -4.0F, 5.0F, 5.0F, 8.0F, 0.002F, false);
/*  50 */     this.rightArm.setTextureOffset(24, 47).addBox(-9.0F, 1.0F, -4.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);
/*  51 */     this.rightArm.setTextureOffset(14, 73).addBox(-9.0F, 2.0F, -3.0F, 3.0F, 1.0F, 6.0F, 0.0F, false);
/*  52 */     this.rightArm.setTextureOffset(84, 49).addBox(-10.0F, -3.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
/*  53 */     this.rightArm.setTextureOffset(75, 14).addBox(-10.0F, -1.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  54 */     this.rightArm.setTextureOffset(56, 51).addBox(-10.0F, 3.0F, 0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  55 */     this.rightArm.setTextureOffset(56, 51).addBox(-10.0F, 3.0F, -1.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  56 */     this.rightArm.setTextureOffset(56, 51).addBox(-10.0F, 3.0F, -2.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  57 */     this.rightArm.setTextureOffset(56, 51).addBox(-10.0F, 3.0F, 1.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*  58 */     this.rightArm.setTextureOffset(75, 14).addBox(-10.0F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  59 */     this.rightArm.setTextureOffset(75, 14).addBox(-10.0F, -1.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*  60 */     this.rightArm.setTextureOffset(84, 41).addBox(-10.0F, 1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
/*  61 */     this.rightArm.setTextureOffset(57, 0).addBox(-11.0F, -5.0F, -5.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
/*  62 */     this.rightArm.setTextureOffset(85, 11).addBox(-9.0F, 3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*  63 */     this.rightArm.setTextureOffset(0, 0).addBox(-13.0F, -8.0F, -39.0F, 14.0F, 18.0F, 29.0F, 0.0F, false);
/*  64 */     this.rightArm.setTextureOffset(87, 23).addBox(-4.0F, -5.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
/*  65 */     this.rightArm.setTextureOffset(87, 23).addBox(-4.0F, 1.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
/*  66 */     this.rightArm.setTextureOffset(87, 23).addBox(-10.0F, 1.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
/*  67 */     this.rightArm.setTextureOffset(87, 23).addBox(-10.0F, -5.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
/*  68 */     this.rightArm.setTextureOffset(86, 29).addBox(-5.0F, -13.0F, -34.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*  69 */     this.rightArm.setTextureOffset(30, 67).addBox(-10.0F, -9.0F, -24.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
/*  70 */     this.rightArm.setTextureOffset(0, 47).addBox(-16.0F, -3.0F, -34.0F, 1.0F, 6.0F, 6.0F, -0.001F, false);
/*  71 */     this.rightArm.setTextureOffset(64, 47).addBox(-8.0F, -10.0F, -22.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
/*  72 */     this.rightArm.setTextureOffset(0, 47).addBox(2.5F, -4.0F, -20.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*  73 */     this.rightArm.setTextureOffset(0, 47).addBox(-16.5F, -1.0F, -32.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*  74 */     this.rightArm.setTextureOffset(0, 47).addBox(2.5F, 4.0F, -30.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/*  76 */     this.cube_r1 = new ModelRenderer((Model)this);
/*  77 */     this.cube_r1.setRotationPoint(-7.0F, -5.0F, 0.0F);
/*  78 */     this.rightArm.addChild(this.cube_r1);
/*  79 */     setRotationAngle(this.cube_r1, -0.7854F, 0.0F, 0.0F);
/*  80 */     this.cube_r1.setTextureOffset(83, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/*  82 */     this.cube_r2 = new ModelRenderer((Model)this);
/*  83 */     this.cube_r2.setRotationPoint(-7.0F, -5.0F, 0.0F);
/*  84 */     this.rightArm.addChild(this.cube_r2);
/*  85 */     setRotationAngle(this.cube_r2, 0.1968F, 0.3527F, 0.5763F);
/*  86 */     this.cube_r2.setTextureOffset(83, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/*  88 */     this.cube_r3 = new ModelRenderer((Model)this);
/*  89 */     this.cube_r3.setRotationPoint(-15.5F, 0.0F, -31.0F);
/*  90 */     this.rightArm.addChild(this.cube_r3);
/*  91 */     setRotationAngle(this.cube_r3, 0.7854F, 0.0F, 0.0F);
/*  92 */     this.cube_r3.setTextureOffset(0, 47).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);
/*     */     
/*  94 */     this.cube_r4 = new ModelRenderer((Model)this);
/*  95 */     this.cube_r4.setRotationPoint(-16.9153F, 6.0F, -11.7922F);
/*  96 */     this.rightArm.addChild(this.cube_r4);
/*  97 */     setRotationAngle(this.cube_r4, 2.5986F, 0.8367F, 2.459F);
/*  98 */     this.cube_r4.setTextureOffset(24, 56).addBox(-5.9F, -1.5F, -2.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 100 */     this.cube_r5 = new ModelRenderer((Model)this);
/* 101 */     this.cube_r5.setRotationPoint(-10.9153F, 3.0F, -6.8922F);
/* 102 */     this.rightArm.addChild(this.cube_r5);
/* 103 */     setRotationAngle(this.cube_r5, 0.0F, -2.7053F, 0.0F);
/* 104 */     this.cube_r5.setTextureOffset(25, 56).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 106 */     this.cube_r6 = new ModelRenderer((Model)this);
/* 107 */     this.cube_r6.setRotationPoint(-13.9323F, 3.0F, -6.7628F);
/* 108 */     this.rightArm.addChild(this.cube_r6);
/* 109 */     setRotationAngle(this.cube_r6, 0.0F, 3.0543F, 0.0F);
/* 110 */     this.cube_r6.setTextureOffset(27, 56).addBox(-1.5F, -1.0F, -0.7255F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 112 */     this.cube_r7 = new ModelRenderer((Model)this);
/* 113 */     this.cube_r7.setRotationPoint(-16.9153F, 6.0F, -19.7922F);
/* 114 */     this.rightArm.addChild(this.cube_r7);
/* 115 */     setRotationAngle(this.cube_r7, 0.0F, 1.5708F, 0.0F);
/* 116 */     this.cube_r7.setTextureOffset(24, 56).addBox(-8.4F, -1.0F, -2.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 118 */     this.cube_r8 = new ModelRenderer((Model)this);
/* 119 */     this.cube_r8.setRotationPoint(-16.3634F, 6.0F, -21.6518F);
/* 120 */     this.rightArm.addChild(this.cube_r8);
/* 121 */     setRotationAngle(this.cube_r8, 0.0F, 1.0908F, 0.0F);
/* 122 */     this.cube_r8.setTextureOffset(25, 56).addBox(-3.9043F, -1.0F, -0.6308F, 4.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 124 */     this.cube_r9 = new ModelRenderer((Model)this);
/* 125 */     this.cube_r9.setRotationPoint(-14.0F, 6.0F, -23.0F);
/* 126 */     this.rightArm.addChild(this.cube_r9);
/* 127 */     setRotationAngle(this.cube_r9, 0.0F, 0.6545F, 0.0F);
/* 128 */     this.cube_r9.setTextureOffset(25, 56).addBox(-3.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 130 */     this.cube_r10 = new ModelRenderer((Model)this);
/* 131 */     this.cube_r10.setRotationPoint(-8.0F, 11.7425F, -9.2669F);
/* 132 */     this.rightArm.addChild(this.cube_r10);
/* 133 */     setRotationAngle(this.cube_r10, -2.3998F, 0.0F, 0.0F);
/* 134 */     this.cube_r10.setTextureOffset(57, 2).addBox(-1.0F, -1.6462F, -1.2493F, 2.0F, 5.0F, 2.0F, 0.11F, false);
/*     */     
/* 136 */     this.cube_r11 = new ModelRenderer((Model)this);
/* 137 */     this.cube_r11.setRotationPoint(-8.0F, 9.7425F, -4.2669F);
/* 138 */     this.rightArm.addChild(this.cube_r11);
/* 139 */     setRotationAngle(this.cube_r11, -1.1781F, 0.0F, 0.0F);
/* 140 */     this.cube_r11.setTextureOffset(57, 2).addBox(-1.0F, 0.7386F, -0.3149F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 142 */     this.cube_r12 = new ModelRenderer((Model)this);
/* 143 */     this.cube_r12.setRotationPoint(-8.0F, 7.0F, -1.0F);
/* 144 */     this.rightArm.addChild(this.cube_r12);
/* 145 */     setRotationAngle(this.cube_r12, -0.829F, 0.0F, 0.0F);
/* 146 */     this.cube_r12.setTextureOffset(57, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.001F, false);
/*     */     
/* 148 */     this.cube_r13 = new ModelRenderer((Model)this);
/* 149 */     this.cube_r13.setRotationPoint(-8.5F, -7.0F, -2.0F);
/* 150 */     this.rightArm.addChild(this.cube_r13);
/* 151 */     setRotationAngle(this.cube_r13, 0.7854F, 0.0F, -0.6981F);
/* 152 */     this.cube_r13.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/* 153 */     this.cube_r13.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 155 */     this.cube_r14 = new ModelRenderer((Model)this);
/* 156 */     this.cube_r14.setRotationPoint(-7.8572F, -6.234F, 2.0F);
/* 157 */     this.rightArm.addChild(this.cube_r14);
/* 158 */     setRotationAngle(this.cube_r14, -0.6545F, 0.3054F, -0.6981F);
/* 159 */     this.cube_r14.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 161 */     this.cube_r15 = new ModelRenderer((Model)this);
/* 162 */     this.cube_r15.setRotationPoint(-9.3893F, -4.9484F, 0.0F);
/* 163 */     this.rightArm.addChild(this.cube_r15);
/* 164 */     setRotationAngle(this.cube_r15, -0.1745F, 0.0F, -0.6981F);
/* 165 */     this.cube_r15.setTextureOffset(25, 16).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 167 */     this.cube_r16 = new ModelRenderer((Model)this);
/* 168 */     this.cube_r16.setRotationPoint(-5.6823F, -6.7535F, 2.0F);
/* 169 */     this.rightArm.addChild(this.cube_r16);
/* 170 */     setRotationAngle(this.cube_r16, -0.8727F, 0.0F, 0.6109F);
/* 171 */     this.cube_r16.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 173 */     this.cube_r17 = new ModelRenderer((Model)this);
/* 174 */     this.cube_r17.setRotationPoint(-5.6823F, -6.7535F, 0.0F);
/* 175 */     this.rightArm.addChild(this.cube_r17);
/* 176 */     setRotationAngle(this.cube_r17, 0.0F, 0.0F, 0.6109F);
/* 177 */     this.cube_r17.setTextureOffset(25, 15).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/* 178 */     this.cube_r17.setTextureOffset(25, 15).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 180 */     this.cube_r18 = new ModelRenderer((Model)this);
/* 181 */     this.cube_r18.setRotationPoint(-8.5F, -7.0F, 0.0F);
/* 182 */     this.rightArm.addChild(this.cube_r18);
/* 183 */     setRotationAngle(this.cube_r18, 0.0F, 0.0F, -0.6981F);
/* 184 */     this.cube_r18.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 186 */     this.cube_r19 = new ModelRenderer((Model)this);
/* 187 */     this.cube_r19.setRotationPoint(-6.0747F, -6.6512F, -1.5F);
/* 188 */     this.rightArm.addChild(this.cube_r19);
/* 189 */     setRotationAngle(this.cube_r19, 0.4363F, 0.0F, 0.48F);
/* 190 */     this.cube_r19.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 192 */     this.RightArm_r1 = new ModelRenderer((Model)this);
/* 193 */     this.RightArm_r1.setRotationPoint(-1.0F, 1.0F, 0.0F);
/* 194 */     this.rightArm.addChild(this.RightArm_r1);
/* 195 */     setRotationAngle(this.RightArm_r1, -0.7854F, 0.0F, 0.0F);
/* 196 */     this.RightArm_r1.setTextureOffset(0, 0).addBox(-3.0F, -4.5F, -4.5F, 5.0F, 8.0F, 8.0F, 0.0F, false);
/*     */     
/* 198 */     this.RightArm_r2 = new ModelRenderer((Model)this);
/* 199 */     this.RightArm_r2.setRotationPoint(-7.0F, 0.0F, -9.5263F);
/* 200 */     this.rightArm.addChild(this.RightArm_r2);
/* 201 */     setRotationAngle(this.RightArm_r2, 0.0F, -1.5708F, -1.5708F);
/* 202 */     this.RightArm_r2.setTextureOffset(36, 78).addBox(-36.5F, 4.0F, -4.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
/* 203 */     this.RightArm_r2.setTextureOffset(82, 67).addBox(-36.5F, -4.0F, -4.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
/* 204 */     this.RightArm_r2.setTextureOffset(24, 47).addBox(-36.5F, -2.0F, 2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/* 205 */     this.RightArm_r2.setTextureOffset(57, 11).addBox(-36.5F, -2.0F, -6.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/* 206 */     this.RightArm_r2.setTextureOffset(0, 47).addBox(-34.5F, -5.0F, -7.0F, 5.0F, 12.0F, 14.0F, 0.0F, false);
/* 207 */     this.RightArm_r2.setTextureOffset(0, 73).addBox(-0.5F, -3.0F, -5.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
/* 208 */     this.RightArm_r2.setTextureOffset(38, 47).addBox(2.5F, -1.0F, -3.0F, 3.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 210 */     this.RightArm_r3 = new ModelRenderer((Model)this);
/* 211 */     this.RightArm_r3.setRotationPoint(-1.0F, 1.0F, 0.0F);
/* 212 */     this.rightArm.addChild(this.RightArm_r3);
/* 213 */     setRotationAngle(this.RightArm_r3, -1.5708F, 0.0F, 0.0F);
/* 214 */     this.RightArm_r3.setTextureOffset(0, 0).addBox(-3.0F, -4.0F, -5.0F, 5.0F, 8.0F, 8.0F, -0.001F, false);
/*     */     
/* 216 */     this.octagon = new ModelRenderer((Model)this);
/* 217 */     this.octagon.setRotationPoint(5.0F, 5.0F, -27.0F);
/* 218 */     this.rightArm.addChild(this.octagon);
/* 219 */     this.octagon.setTextureOffset(74, 55).addBox(-4.0F, -9.2426F, 5.0F, 2.0F, 2.0F, 6.0F, -0.003F, false);
/* 220 */     this.octagon.setTextureOffset(0, 0).addBox(-4.0F, -11.0F, 6.7574F, 2.0F, 6.0F, 2.0F, 0.001F, false);
/*     */     
/* 222 */     this.octagon_r1 = new ModelRenderer((Model)this);
/* 223 */     this.octagon_r1.setRotationPoint(-8.0F, -8.0F, 8.0F);
/* 224 */     this.octagon.addChild(this.octagon_r1);
/* 225 */     setRotationAngle(this.octagon_r1, -0.7854F, 0.0F, 0.0F);
/* 226 */     this.octagon_r1.setTextureOffset(0, 0).addBox(4.0F, -3.0F, -1.2426F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/* 227 */     this.octagon_r1.setTextureOffset(74, 55).addBox(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, -0.002F, false);
/*     */     
/* 229 */     this.octagon3 = new ModelRenderer((Model)this);
/* 230 */     this.octagon3.setRotationPoint(1.0F, 8.0F, -26.0F);
/* 231 */     this.rightArm.addChild(this.octagon3);
/* 232 */     this.octagon3.setTextureOffset(38, 47).addBox(-16.0F, -11.0F, -12.2426F, 2.0F, 6.0F, 14.0F, 0.002F, false);
/* 233 */     this.octagon3.setTextureOffset(56, 73).addBox(-16.0F, -15.2426F, -8.0F, 2.0F, 14.0F, 6.0F, -0.001F, false);
/*     */     
/* 235 */     this.octagon_r2 = new ModelRenderer((Model)this);
/* 236 */     this.octagon_r2.setRotationPoint(-8.0F, -8.0F, -5.0F);
/* 237 */     this.octagon3.addChild(this.octagon_r2);
/* 238 */     setRotationAngle(this.octagon_r2, -0.7854F, 0.0F, 0.0F);
/* 239 */     this.octagon_r2.setTextureOffset(56, 73).addBox(-8.0F, -7.2426F, -3.0F, 2.0F, 14.0F, 6.0F, -0.002F, false);
/* 240 */     this.octagon_r2.setTextureOffset(38, 47).addBox(-8.0F, -3.0F, -7.2426F, 2.0F, 6.0F, 14.0F, -0.004F, false);
/*     */     
/* 242 */     this.octagon2 = new ModelRenderer((Model)this);
/* 243 */     this.octagon2.setRotationPoint(5.0F, 13.0F, -37.0F);
/* 244 */     this.rightArm.addChild(this.octagon2);
/* 245 */     this.octagon2.setTextureOffset(74, 55).addBox(-4.0F, -9.2426F, 5.0F, 2.0F, 2.0F, 6.0F, 0.002F, false);
/* 246 */     this.octagon2.setTextureOffset(0, 0).addBox(-4.0F, -11.0F, 6.7574F, 2.0F, 6.0F, 2.0F, -0.001F, false);
/*     */     
/* 248 */     this.octagon_r3 = new ModelRenderer((Model)this);
/* 249 */     this.octagon_r3.setRotationPoint(-8.0F, -8.0F, 8.0F);
/* 250 */     this.octagon2.addChild(this.octagon_r3);
/* 251 */     setRotationAngle(this.octagon_r3, -0.7854F, 0.0F, 0.0F);
/* 252 */     this.octagon_r3.setTextureOffset(0, 0).addBox(4.0F, -3.0F, -1.2426F, 2.0F, 6.0F, 2.0F, -0.004F, false);
/* 253 */     this.octagon_r3.setTextureOffset(74, 55).addBox(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
/* 254 */     this.octagon_r3.setTextureOffset(74, 55).addBox(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, -0.002F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 265 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 270 */     modelRenderer.rotateAngleX = x;
/* 271 */     modelRenderer.rotateAngleY = y;
/* 272 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\abilities\DamnedPunkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */