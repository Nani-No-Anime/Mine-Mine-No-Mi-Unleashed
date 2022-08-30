/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Matrix4f;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VoltVariProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class VoltVariProjectileRenderer<T extends VoltVariProjectile, M extends EntityModel<T>> extends EntityRenderer<T> {
/*  31 */   private float scaleX = 1.0F; private float scaleY = 1.0F; private float scaleZ = 1.0F;
/*     */   private float red; private float blue; private float green; private float alpha; protected M model; private ResourceLocation texture; Random random;
/*     */   
/*     */   public void setTexture(ResourceLocation res) {
/*     */     this.texture = res;
/*     */   }
/*     */   
/*  38 */   public VoltVariProjectileRenderer(EntityRendererManager renderManager, M model) { super(renderManager);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     this.random = new Random();
/*     */     this.model = model; }
/*     */   public void setColor(double red, double green, double blue, double alpha) { this.red = (float)(red / 255.0D);
/*     */     this.green = (float)(green / 255.0D);
/*     */     this.blue = (float)(blue / 255.0D);
/*  77 */     this.alpha = (float)(alpha / 255.0D); } public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) { if (((VoltVariProjectile)entity).ticksExisted < 2) {
/*     */       return;
/*     */     }
/*     */     
/*  81 */     setScale(entity.getSize(), entity.getSize(), entity.getSize());
/*  82 */     matrixStack.push();
/*  83 */     IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.getLightning());
/*     */     
/*  85 */     float randMovement = (((VoltVariProjectile)entity).ticksExisted + partialTicks) / 600.0F;
/*  86 */     float rays = 1000.0F;
/*  87 */     matrixStack.push();
/*     */     
/*  89 */     for (int i = 0; i < rays; i++) {
/*     */       
/*  91 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(this.random.nextFloat() * 360.0F + randMovement * 90.0F));
/*     */       
/*  93 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(this.random.nextFloat() * 360.0F + randMovement * 90.0F));
/*  94 */       float f3 = (float)((getScale()).x / 3.0D * this.random.nextFloat());
/*  95 */       float f4 = (float)((getScale()).x / 3.0D * this.random.nextFloat());
/*  96 */       Matrix4f matrix4f = matrixStack.getLast().getMatrix();
/*     */       
/*  98 */       Color a = new Color(125, 249, 255, 80);
/*  99 */       Color b = new Color(125, 100, 255, 0);
/* 100 */       Color c = new Color(125, 255, 255, 0);
/*     */       
/* 102 */       RendererHelper.drawA(vertexBuilder, matrix4f, a);
/* 103 */       RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, b);
/* 104 */       RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, c);
/* 105 */       RendererHelper.drawA(vertexBuilder, matrix4f, a);
/* 106 */       RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, c);
/* 107 */       RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, c);
/* 108 */       RendererHelper.drawA(vertexBuilder, matrix4f, a);
/* 109 */       RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, c);
/* 110 */       RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, b);
/*     */     } 
/* 112 */     matrixStack.pop();
/*     */ 
/*     */     
/* 115 */     matrixStack.rotate(new Quaternion(Vector3f.YP, ((VoltVariProjectile)entity).prevRotationYaw + (((VoltVariProjectile)entity).rotationYaw - ((VoltVariProjectile)entity).prevRotationYaw) * partialTicks - 180.0F, true));
/* 116 */     matrixStack.rotate(new Quaternion(Vector3f.XP, ((VoltVariProjectile)entity).prevRotationPitch + (((VoltVariProjectile)entity).rotationPitch - ((VoltVariProjectile)entity).prevRotationPitch) * partialTicks, true));
/* 117 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*     */     
/* 119 */     matrixStack.scale(this.scaleX, this.scaleY, this.scaleZ);
/*     */     
/* 121 */     ResourceLocation finalTexture = getEntityTexture((VoltVariProjectile)entity);
/* 122 */     RenderType type = (finalTexture == null) ? ModRenderTypes.TRANSPARENT_COLOR : RenderType.getEntityTranslucent(finalTexture);
/*     */     
/* 124 */     if (this.model != null) {
/* 125 */       this.model.render(matrixStack, buffer.getBuffer(type), packedLight, OverlayTexture.NO_OVERLAY, getRGB().getRed() / 255.0F, getRGB().getGreen() / 255.0F, getRGB().getBlue() / 255.0F, getRGB().getAlpha() / 255.0F);
/*     */     }
/* 127 */     matrixStack.pop(); }
/*     */    public void setScale(double scaleX, double scaleY, double scaleZ) {
/*     */     this.scaleX = (float)scaleX;
/*     */     this.scaleY = (float)scaleY;
/*     */     this.scaleZ = (float)scaleZ;
/*     */   } public ResourceLocation getEntityTexture(VoltVariProjectile entity) {
/* 133 */     if (entity.getThrower() != null && entity.isAffectedByHaki()) {
/*     */       
/* 135 */       boolean hardeningCheck = (entity.isAffectedByHardening() && HakiHelper.hasHardeningActive(entity.getThrower()));
/* 136 */       if (hardeningCheck) {
/* 137 */         return ModResources.BUSOSHOKU_HAKI_ARM;
/*     */       }
/*     */     } 
/* 140 */     return this.texture;
/*     */   } public Vec3d getScale() {
/*     */     return new Vec3d(this.scaleX, this.scaleY, this.scaleZ);
/*     */   } public Color getRGB() {
/*     */     return new Color(this.red, this.green, this.blue, this.alpha);
/* 145 */   } public static class Factory implements IRenderFactory<VoltVariProjectile> { protected EntityModel model = (EntityModel)new CubeModel();
/* 146 */     protected double scaleX = 1.0D, scaleY = 1.0D, scaleZ = 1.0D;
/* 147 */     protected double red = 255.0D; protected double green = 255.0D; protected double blue = 255.0D; protected double alpha = 255.0D;
/*     */     
/*     */     protected ResourceLocation texture;
/*     */     
/*     */     public Factory(EntityModel model) {
/* 152 */       this.model = model;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setTexture(String textureName) {
/* 157 */       this.texture = new ResourceLocation(APIConfig.projectId, "textures/models/projectiles/" + textureName + ".png");
/* 158 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setTexture(ResourceLocation location) {
/* 163 */       this.texture = location;
/* 164 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setColor(double red, double green, double blue, double alpha) {
/* 169 */       this.red = red;
/* 170 */       this.green = green;
/* 171 */       this.blue = blue;
/* 172 */       this.alpha = alpha;
/* 173 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setColor(String hex) {
/* 178 */       Color color = WyHelper.hexToRGB(hex);
/* 179 */       this.red = color.getRed();
/* 180 */       this.green = color.getGreen();
/* 181 */       this.blue = color.getBlue();
/* 182 */       this.alpha = color.getAlpha();
/* 183 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setAlpha(double alpha) {
/* 188 */       this.alpha = alpha;
/* 189 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setScale(double scale) {
/* 194 */       this.scaleX = this.scaleY = this.scaleZ = scale;
/* 195 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setScale(double scaleX, double scaleY, double scaleZ) {
/* 200 */       this.scaleX = scaleX;
/* 201 */       this.scaleY = scaleY;
/* 202 */       this.scaleZ = scaleZ;
/* 203 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super VoltVariProjectile> createRenderFor(EntityRendererManager manager) {
/* 209 */       VoltVariProjectileRenderer renderer = new VoltVariProjectileRenderer(manager, this.model);
/* 210 */       renderer.setTexture(this.texture);
/* 211 */       renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
/* 212 */       renderer.setColor(this.red, this.green, this.blue, this.alpha);
/* 213 */       return renderer;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\VoltVariProjectileRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */