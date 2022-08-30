/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GuardingEffect
/*    */   extends OverlayEffect
/*    */ {
/*    */   public boolean useOnlySources = false;
/*    */   public boolean reduceSpeedAfterHit = false;
/*    */   public boolean blockSwings = false;
/* 19 */   public ArrayList<String> acceptableSources = new ArrayList<>(Collections.emptyList());
/*    */ 
/*    */   
/*    */   public GuardingEffect(boolean blockSwings) {
/* 23 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/* 24 */     this.blockSwings = blockSwings;
/*    */   }
/*    */ 
/*    */   
/*    */   public GuardingEffect(boolean useOnlySources, boolean makeUserSlow, boolean blockSwings, String... s) {
/* 29 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */     
/* 31 */     this.useOnlySources = useOnlySources;
/* 32 */     this.reduceSpeedAfterHit = makeUserSlow;
/* 33 */     this.blockSwings = blockSwings;
/* 34 */     this.acceptableSources.addAll(Arrays.asList(s));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasBodyOverlayColor() {
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay() {
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getResourceLocation(int duration) {
/* 76 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 82 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 88 */     return this.blockSwings;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\GuardingEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */