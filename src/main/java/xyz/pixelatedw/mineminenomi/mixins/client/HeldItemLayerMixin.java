/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IHeldItemAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({HeldItemLayer.class})
/*    */ public class HeldItemLayerMixin
/*    */ {
/*    */   @Inject(method = {"func_229135_a_(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/model/ItemCameraTransforms$TransformType;Lnet/minecraft/util/HandSide;Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/FirstPersonRenderer;renderItemSide(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/model/ItemCameraTransforms$TransformType;ZLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V", shift = At.Shift.BEFORE)})
/*    */   public void renderItemStack(LivingEntity entity, ItemStack itemStack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixType, IRenderTypeBuffer renderBuffer, int packedLight, CallbackInfo info) {
/* 34 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/*    */       
/* 36 */       IAnimatedAbility ability = AbilityHelper.getActiveAnimationAbility(entity);
/* 37 */       if (ability != null && ability.getAnimation() instanceof IHeldItemAnimation)
/* 38 */         ((IHeldItemAnimation)ability.getAnimation()).setupHeldItem(entity, itemStack, transformType, handSide, matrixType, renderBuffer, packedLight); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\HeldItemLayerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */