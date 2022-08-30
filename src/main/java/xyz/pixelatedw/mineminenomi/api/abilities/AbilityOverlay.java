/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class AbilityOverlay
/*    */ {
/* 10 */   private ResourceLocation texture = null;
/* 11 */   private RenderType renderType = RenderType.NORMAL;
/* 12 */   private Color color = WyHelper.hexToRGB("#FFFFFFFF");
/*    */ 
/*    */   
/*    */   public AbilityOverlay setTexture(ResourceLocation texture) {
/* 16 */     this.texture = texture;
/* 17 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityOverlay setColor(Color color) {
/* 22 */     this.color = color;
/* 23 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityOverlay setColor(String hex) {
/* 28 */     this.color = WyHelper.hexToRGB(hex);
/* 29 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityOverlay setRenderType(RenderType type) {
/* 34 */     this.renderType = type;
/* 35 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 40 */     return this.texture;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getColor() {
/* 45 */     return this.color;
/*    */   }
/*    */ 
/*    */   
/*    */   public RenderType getRenderType() {
/* 50 */     return this.renderType;
/*    */   }
/*    */   
/*    */   public enum RenderType
/*    */   {
/* 55 */     NORMAL, ENERGY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */