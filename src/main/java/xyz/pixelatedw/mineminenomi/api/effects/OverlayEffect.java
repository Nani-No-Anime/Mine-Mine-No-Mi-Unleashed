/*    */ package xyz.pixelatedw.mineminenomi.api.effects;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ public abstract class OverlayEffect
/*    */   extends ModEffect
/*    */ {
/*    */   protected OverlayEffect(EffectType typeIn, int liquidColorIn) {
/* 15 */     super(typeIn, liquidColorIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract float[] getOverlayColor();
/*    */   
/*    */   public abstract boolean hasBodyOverlayColor();
/*    */   
/*    */   public abstract Block getBlockOverlay();
/*    */   
/*    */   public abstract ResourceLocation getResourceLocation(int paramInt);
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public RenderType getRenderType() {
/* 29 */     return ModRenderTypes.TRANSPARENT_COLOR;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\effects\OverlayEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */