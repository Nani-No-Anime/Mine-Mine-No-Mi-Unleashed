/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.BaraPartialMorphRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class BaraFestivalZoanInfo
/*    */   extends ZoanInfo {
/* 14 */   public static final BaraFestivalZoanInfo INSTANCE = new BaraFestivalZoanInfo();
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/* 20 */     return (IRenderFactory)new BaraPartialMorphRenderer.Factory(this, BaraPartialMorphRenderer.BaraMode.FESTIVAL);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 26 */     return "bara_festival";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 32 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanMorphModel getModel() {
/* 38 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\BaraFestivalZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */