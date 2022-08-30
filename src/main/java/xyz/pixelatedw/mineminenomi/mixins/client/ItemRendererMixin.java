/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.color.ItemColors;
/*    */ import net.minecraft.client.renderer.model.BakedQuad;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.gen.Accessor;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ItemRenderer.class})
/*    */ public abstract class ItemRendererMixin
/*    */ {
/*    */   @Accessor
/*    */   public abstract ItemColors getItemColors();
/*    */   
/*    */   @Inject(method = {"renderQuads"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void renderQuads(MatrixStack matrixStack, IVertexBuilder buffer, List<BakedQuad> quads, ItemStack itemStack, int lightmap, int overlay, CallbackInfo info) {
/* 28 */     if (itemStack.isDamageable() && itemStack.hasTag() && itemStack.getTag().getBoolean("imbuingHakiActive")) {
/*    */       
/* 30 */       MatrixStack.Entry entry = matrixStack.getLast();
/* 31 */       for (BakedQuad bakedquad : quads)
/* 32 */         buffer.addVertexData(entry, bakedquad, 0.4F, 0.0F, 0.4F, lightmap, overlay, true); 
/* 33 */       info.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\ItemRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */