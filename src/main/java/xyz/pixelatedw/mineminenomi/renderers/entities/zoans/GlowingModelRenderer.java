/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Matrix4f;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import org.apache.commons.lang3.tuple.Triple;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*     */ 
/*     */ public class GlowingModelRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel>
/*     */   extends ZoanMorphRenderer<T, M> {
/*     */   protected Type type;
/*     */   
/*     */   public GlowingModelRenderer(EntityRendererManager rendererManager, ZoanInfo info, Type type) {
/*  33 */     super(rendererManager, info);
/*  34 */     addLayer((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/*  35 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  41 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */     
/*  43 */     matrixStack.push();
/*  44 */     matrixStack.translate(0.0D, 2.6D, 0.0D);
/*     */     
/*  46 */     int lightLevel = entity.getEntityWorld().getLight(entity.getPosition());
/*  47 */     if (this.type == Type.AMARU)
/*     */     {
/*  49 */       lightLevel = 8 + entity.getEntityWorld().getLight(entity.getPosition());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     if (lightLevel > 7) {
/*     */       
/*  62 */       Random random = new Random(500L);
/*  63 */       float rays = (20 + lightLevel * 16);
/*  64 */       float randMovement = (entity.ticksExisted + partialTicks) / 500.0F;
/*     */       
/*  66 */       IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.getLightning());
/*  67 */       matrixStack.push();
/*  68 */       matrixStack.translate(0.0D, 0.75D, 0.0D);
/*     */       
/*  70 */       for (int i = 0; i < rays; i++) {
/*     */         
/*  72 */         float size = 9.6F + ((this.type == Type.AMARU) ? 0.0F : 2.4F);
/*  73 */         matrixStack.rotate(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
/*  74 */         matrixStack.rotate(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
/*  75 */         matrixStack.rotate(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F));
/*  76 */         matrixStack.rotate(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
/*  77 */         matrixStack.rotate(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
/*  78 */         matrixStack.rotate(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F + randMovement * 90.0F));
/*  79 */         float f3 = size * random.nextFloat();
/*  80 */         float f4 = size * random.nextFloat();
/*  81 */         Matrix4f matrix4f = matrixStack.getLast().getMatrix();
/*     */         
/*  83 */         int alpha = 8 + lightLevel / 2;
/*     */         
/*  85 */         Color color1 = (Color)this.type.getColorScheme(alpha).getRight();
/*  86 */         Color color2 = (Color)this.type.getColorScheme(alpha).getMiddle();
/*  87 */         Color color3 = (Color)this.type.getColorScheme(alpha).getLeft();
/*     */         
/*  89 */         RendererHelper.drawA(vertexBuilder, matrix4f, color3);
/*  90 */         RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, color2);
/*  91 */         RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, color1);
/*  92 */         RendererHelper.drawA(vertexBuilder, matrix4f, color3);
/*  93 */         RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, color2);
/*  94 */         RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, color1);
/*  95 */         RendererHelper.drawA(vertexBuilder, matrix4f, color3);
/*  96 */         RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, color1);
/*  97 */         RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, color2);
/*     */       } 
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
/* 113 */       matrixStack.pop();
/*     */     } 
/* 115 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
/* 121 */     return entity.getLocationSkin();
/*     */   }
/*     */   
/*     */   public static class Factory<T extends PlayerEntity>
/*     */     implements IRenderFactory<T>
/*     */   {
/*     */     private ZoanInfo info;
/*     */     private GlowingModelRenderer.Type type;
/*     */     
/*     */     public Factory(ZoanInfo info, GlowingModelRenderer.Type type) {
/* 131 */       this.info = info;
/* 132 */       this.type = type;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 138 */       GlowingModelRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new GlowingModelRenderer<>(manager, this.info, this.type);
/* 139 */       return (EntityRenderer)renderer;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Type
/*     */   {
/* 149 */     AMARU((alpha -> Triple.of(new Color(0, 249, 255, 0), new Color(0, 100, 255, 0), new Color(125, 255, 255, 2)))), 
              DAIBUTSU((alpha -> Triple.of(new Color(0, 249, 255, 0), new Color(0, 100, 255, 0), new Color(125, 255, 255, 2))));
/*     */     private Function<Integer, Triple> colorScheme;
/*     */     
/*     */     
/*     */     Type(Function<Integer, Triple> colorScheme) {
/* 157 */       this.colorScheme = colorScheme;
/*     */     }
/*     */ 
/*     */     
/*     */     public Triple<Color, Color, Color> getColorScheme(int alpha) {
/* 162 */       return (Triple<Color, Color, Color>)this.colorScheme.apply(Integer.valueOf(alpha));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\GlowingModelRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */