/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FrozenEffect
/*    */   extends OverlayEffect {
/* 14 */   private static final DamageSource DAMAGE_SOURCE = (new DamageSource("frost")).setDamageBypassesArmor().setMagicDamage().setDamageIsAbsolute();
/*    */ 
/*    */   
/*    */   public FrozenEffect() {
/* 18 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 24 */     return (duration % 30 == 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 30 */     float damage = Math.max(2, 2 * amplifier);
/* 31 */     entity.attackEntityFrom(DAMAGE_SOURCE, damage);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 37 */     return new float[] { 0.3F, 0.92F, 0.87F, 0.8F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasBodyOverlayColor() {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getResourceLocation(int duration) {
/* 54 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay() {
/* 60 */     return Blocks.BLUE_ICE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 72 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\FrozenEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */