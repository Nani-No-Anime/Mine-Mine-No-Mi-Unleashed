/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
/*    */ 
/*    */ 
/*    */ public class RenderMorphEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private final PlayerRenderer renderer;
/*    */   private final float partialRenderTick;
/*    */   private final MatrixStack stack;
/*    */   private final IRenderTypeBuffer buffers;
/*    */   private final int light;
/*    */   
/*    */   public RenderMorphEvent(PlayerEntity player, ZoanMorphRenderer renderer, float partialRenderTick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
/* 22 */     super(player);
/* 23 */     this.renderer = (PlayerRenderer)renderer;
/* 24 */     this.partialRenderTick = partialRenderTick;
/* 25 */     this.stack = stack;
/* 26 */     this.buffers = buffers;
/* 27 */     this.light = light;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerRenderer getRenderer() {
/* 32 */     return this.renderer;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPartialRenderTick() {
/* 37 */     return this.partialRenderTick;
/*    */   }
/*    */ 
/*    */   
/*    */   public MatrixStack getMatrixStack() {
/* 42 */     return this.stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public IRenderTypeBuffer getBuffers() {
/* 47 */     return this.buffers;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLight() {
/* 52 */     return this.light;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre
/*    */     extends RenderMorphEvent
/*    */   {
/*    */     public Pre(PlayerEntity player, ZoanMorphRenderer renderer, float tick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
/* 60 */       super(player, renderer, tick, stack, buffers, light);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post
/*    */     extends RenderMorphEvent
/*    */   {
/*    */     public Post(PlayerEntity player, ZoanMorphRenderer renderer, float tick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
/* 68 */       super(player, renderer, tick, stack, buffers, light);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\RenderMorphEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */