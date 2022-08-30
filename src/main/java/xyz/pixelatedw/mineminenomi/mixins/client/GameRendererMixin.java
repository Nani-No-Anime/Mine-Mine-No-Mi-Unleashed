/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.resource.ISelectiveResourceReloadListener;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Constant;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyConstant;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ 
/*    */ @Mixin({GameRenderer.class})
/*    */ public abstract class GameRendererMixin
/*    */   implements ISelectiveResourceReloadListener
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private Minecraft mc;
/*    */   
/*    */   @ModifyConstant(method = {"getMouseOver(F)V"}, require = 1, allow = 1, constant = {@Constant(doubleValue = 6.0D)})
/*    */   private double getActualAttackRangeInCreative(double attackRange) {
/* 26 */     if (this.mc.player != null)
/* 27 */       return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.mc.player, attackRange); 
/* 28 */     return attackRange;
/*    */   }
/*    */ 
/*    */   
/*    */   @ModifyVariable(method = {"getMouseOver(F)V"}, at = @At("STORE"), ordinal = 1)
/*    */   private double getActualAttackRangeInSurvival0(double attackRange) {
/* 34 */     if (this.mc.player != null)
/* 35 */       return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.mc.player, attackRange); 
/* 36 */     return attackRange;
/*    */   }
/*    */ 
/*    */   
/*    */   @ModifyConstant(method = {"getMouseOver(F)V"}, constant = {@Constant(doubleValue = 9.0D)})
/*    */   private double getActualAttackRangeInSurvival1(double attackRange) {
/* 42 */     if (this.mc.player != null)
/* 43 */       return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.mc.player, attackRange); 
/* 44 */     return attackRange;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\GameRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */