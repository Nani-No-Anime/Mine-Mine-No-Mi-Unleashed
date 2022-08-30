/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Matrix4f;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.culling.ClippingHelperImpl;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LightningEntityRenderer
/*     */   extends EntityRenderer<LightningEntity>
/*     */ {
/*     */   public LightningEntityRenderer(EntityRendererManager manager) {
/*  25 */     super(manager);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(LightningEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
/*  30 */     matrixStack.push();
/*  31 */     renderLightning(entity, partialTicks, matrixStack, bufferIn, packedLightIn);
/*  32 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderLightning(LightningEntity entity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
/*  37 */     if (0 > entity.getSegments()) {
/*     */       return;
/*     */     }
/*  40 */     Random random = new Random(entity.seed);
/*  41 */     int angle = entity.getAngle();
/*  42 */     int segments = entity.getSegments();
/*  43 */     float length = entity.getLength() * Math.min((entity.ticksExisted + partialTicks) / 2.0F, 1.0F);
/*  44 */     float size = entity.getSize();
/*  45 */     int branches = entity.getBranches();
/*  46 */     float maxDistance = entity.getLength() / segments;
/*  47 */     Color rgb = new Color(entity.getColor());
/*     */     
/*  49 */     float[] arr = new float[segments];
/*  50 */     int targetNumber = (int)(segments * Math.min((entity.ticksExisted + partialTicks) / 2.0F, 1.0F));
/*  51 */     int renderTime = entity.getAliveTicks() / 2;
/*     */     
/*  53 */     float alpha = (entity.ticksExisted < renderTime) ? 0.3F : Math.max(0.3F * (1.0F - ((entity.ticksExisted - renderTime) + partialTicks) / renderTime), 0.0F);
/*     */     
/*  55 */     for (int segment = 0; segment < arr.length; segment++) {
/*  56 */       arr[segment] = (segment == targetNumber) ? (length - maxDistance * segment) : maxDistance;
/*     */     }
/*  58 */     float[] offsetsY = new float[segments];
/*  59 */     float[] offsetsX = new float[segments];
/*     */     
/*  61 */     IVertexBuilder vertex = bufferIn.getBuffer(entity.getEnergyEffect() ? ModRenderTypes.ENERGY : ModRenderTypes.SOLID);
/*     */     
/*  63 */     Matrix4f matrix4f = matrixStack.getLast().getMatrix();
/*     */     
/*  65 */     matrixStack.rotate(Vector3f.YN.rotationDegrees(entity.getYaw(partialTicks)));
/*  66 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(entity.getPitch(partialTicks)));
/*  67 */     matrixStack.translate(0.0D, 0.0D, 0.1D);
/*     */ 
/*     */     
/*  70 */     for (int i = 0; i < branches; i++) {
/*  71 */       float lastOffsetY = 0.0F;
/*  72 */       float lastOffsetX = 0.0F;
/*     */       
/*     */       int j;
/*  75 */       for (j = 0; j < segments; j++) {
/*     */         
/*  77 */         offsetsY[j] = lastOffsetY;
/*  78 */         offsetsX[j] = lastOffsetX;
/*  79 */         lastOffsetY = (float)(lastOffsetY + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
/*  80 */         lastOffsetX = (float)(lastOffsetX + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
/*     */       } 
/*     */       
/*  83 */       for (j = 0; j < segments; j++) {
/*     */         
/*  85 */         float y = offsetsY[j];
/*  86 */         float x = offsetsX[j];
/*     */         
/*  88 */         for (int depth = 1; depth < 4; depth++) {
/*     */           
/*  90 */           float depthY = size / 2.0F + depth * size;
/*  91 */           float depthZ = size / 2.0F + depth * size;
/*     */           
/*  93 */           float endY = (j == segments - 1) ? y : offsetsY[j + 1];
/*  94 */           float endX = (j == segments - 1) ? x : offsetsX[j + 1];
/*  95 */           if (j <= targetNumber) {
/*     */             
/*  97 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, false, true, false, maxDistance, arr[j], packedLightIn);
/*  98 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, false, true, true, maxDistance, arr[j], packedLightIn);
/*  99 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, true, false, true, maxDistance, arr[j], packedLightIn);
/* 100 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, true, false, false, maxDistance, arr[j], packedLightIn);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void drawQuad(Matrix4f matrix4f, IVertexBuilder vertex, float startY, float startX, int segmentIndex, float endY, float endX, float red, float green, float blue, float alpha, float firstOffset, float secondOffset, boolean negativeOffset, boolean bl2, boolean bl3, boolean bl4, float segmentLength, float segmentLengthAdded, int light) {
/* 109 */     vertex.pos(matrix4f, startX + (bl2 ? secondOffset : -secondOffset), startY + (negativeOffset ? secondOffset : -secondOffset), segmentIndex * segmentLength).color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha).lightmap(light).endVertex();
/* 110 */     vertex.pos(matrix4f, endX + (bl2 ? firstOffset : -firstOffset), endY + (negativeOffset ? firstOffset : -firstOffset), segmentIndex * segmentLength + segmentLengthAdded).color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha).lightmap(light).endVertex();
/* 111 */     vertex.pos(matrix4f, endX + (bl4 ? firstOffset : -firstOffset), endY + (bl3 ? firstOffset : -firstOffset), segmentIndex * segmentLength + segmentLengthAdded).color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha).lightmap(light).endVertex();
/* 112 */     vertex.pos(matrix4f, startX + (bl4 ? secondOffset : -secondOffset), startY + (bl3 ? secondOffset : -secondOffset), segmentIndex * segmentLength).color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha).lightmap(light).endVertex();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderLightning(Entity entity, long seed, float len, float pitch, float yaw, Color rgb, float alpha, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
/* 117 */     float partialTicks = 1.0F;
/* 118 */     Random random = new Random(seed);
/* 119 */     int angle = 90;
/* 120 */     int segments = 6;
/* 121 */     float size = 0.02F;
/* 122 */     int branches = 4;
/* 123 */     float length = len * Math.min((entity.ticksExisted + partialTicks) / 2.0F, 1.0F);
/* 124 */     float maxDistance = length / segments;
/*     */     
/* 126 */     float[] arr = new float[segments];
/* 127 */     int targetNumber = (int)(segments * Math.min((entity.ticksExisted + partialTicks) / 2.0F, 1.0F));
/*     */     
/* 129 */     for (int segment = 0; segment < arr.length; segment++) {
/* 130 */       arr[segment] = (segment == targetNumber) ? (length - maxDistance * segment) : maxDistance;
/*     */     }
/* 132 */     float[] offsetsY = new float[segments];
/* 133 */     float[] offsetsX = new float[segments];
/* 134 */     IVertexBuilder vertex = bufferIn.getBuffer(ModRenderTypes.ENERGY);
/* 135 */     Matrix4f matrix4f = matrixStack.getLast().getMatrix();
/*     */     
/* 137 */     matrixStack.rotate(Vector3f.YN.rotationDegrees(pitch));
/* 138 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(yaw));
/*     */ 
/*     */ 
/*     */     
/* 142 */     for (int i = 0; i < branches; i++) {
/* 143 */       float lastOffsetY = 0.0F;
/* 144 */       float lastOffsetX = 0.0F;
/*     */       
/*     */       int j;
/* 147 */       for (j = 0; j < segments; j++) {
/*     */         
/* 149 */         offsetsY[j] = lastOffsetY;
/* 150 */         offsetsX[j] = lastOffsetX;
/* 151 */         lastOffsetY = (float)(lastOffsetY + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
/* 152 */         lastOffsetX = (float)(lastOffsetX + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
/*     */       } 
/*     */       
/* 155 */       for (j = 0; j < segments; j++) {
/*     */         
/* 157 */         float y = offsetsY[j];
/* 158 */         float x = offsetsX[j];
/*     */         
/* 160 */         for (int depth = 1; depth < 4; depth++) {
/*     */           
/* 162 */           float depthY = size / 2.0F + depth * size;
/* 163 */           float depthZ = size / 2.0F + depth * size;
/*     */           
/* 165 */           float endY = (j == segments - 1) ? y : offsetsY[j + 1];
/* 166 */           float endX = (j == segments - 1) ? x : offsetsX[j + 1];
/* 167 */           if (j <= targetNumber) {
/*     */             
/* 169 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, false, true, false, maxDistance, arr[j], packedLightIn);
/* 170 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, false, true, true, maxDistance, arr[j], packedLightIn);
/* 171 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, true, false, true, maxDistance, arr[j], packedLightIn);
/* 172 */             drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, true, false, false, maxDistance, arr[j], packedLightIn);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(LightningEntity entity) {
/* 182 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRender(LightningEntity livingEntityIn, ClippingHelperImpl camera, double camX, double camY, double camZ) {
/* 187 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<LightningEntity>
/*     */   {
/*     */     public EntityRenderer<? super LightningEntity> createRenderFor(EntityRendererManager manager) {
/* 201 */       return new LightningEntityRenderer(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\LightningEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */