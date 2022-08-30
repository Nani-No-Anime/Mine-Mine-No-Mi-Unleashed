/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kame.KameWalkPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.KameWalkPartialModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.KameWalkPartialMorphRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class KameWalkZoanInfo
/*    */   extends ZoanInfo
/*    */ {
/* 20 */   public static final KameWalkZoanInfo INSTANCE = new KameWalkZoanInfo();
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/* 26 */     boolean isSlim = entity.getSkinType().equals("slim");
/* 27 */     return (IRenderFactory)new KameWalkPartialMorphRenderer.Factory(this, isSlim);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public ZoanMorphModel getModel() {
/* 34 */     return (ZoanMorphModel)new KameWalkPartialModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean shouldRenderFirstPersonHand() {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean shouldRenderFirstPersonLeg() {
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isPartial() {
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 60 */     return "walk";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 67 */     return ModAbilities.KAME_KAME_NO_MI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 73 */     return (Ability)KameWalkPointAbility.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\KameWalkZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */