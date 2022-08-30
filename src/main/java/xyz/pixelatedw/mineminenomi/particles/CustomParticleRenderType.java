/*    */ package xyz.pixelatedw.mineminenomi.particles;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.particle.IParticleRenderType;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ 
/*    */ public class CustomParticleRenderType
/*    */   implements IParticleRenderType
/*    */ {
/*    */   private ResourceLocation texture;
/*    */   
/*    */   public CustomParticleRenderType(ResourceLocation texture) {
/* 18 */     this.texture = texture;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void beginRender(BufferBuilder buffer, TextureManager textureManager) {
/* 24 */     RenderSystem.enableBlend();
/* 25 */     RenderSystem.defaultBlendFunc();
/* 26 */     RenderSystem.depthMask(false);
/* 27 */     textureManager.bindTexture(this.texture);
/* 28 */     buffer.begin(7, ModRenderTypes.PARTICLE_POSITION_TEX_COLOR_LMAP);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void finishRender(Tessellator tess) {
/* 34 */     tess.draw();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\CustomParticleRenderType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */