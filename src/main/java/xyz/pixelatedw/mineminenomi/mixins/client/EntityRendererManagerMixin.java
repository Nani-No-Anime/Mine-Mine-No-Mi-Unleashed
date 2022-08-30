/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({EntityRendererManager.class})
/*    */ public class EntityRendererManagerMixin
/*    */ {
/*    */   @Inject(method = {"renderShadow"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void renderShadow(MatrixStack matrixStack, IRenderTypeBuffer buffer, Entity entity, float weight, float partialTicks, IWorldReader world, float size, CallbackInfo callback) {
/* 32 */     if (entity instanceof LivingEntity)
/*    */     {
/* 34 */       if (((LivingEntity)entity).isPotionActive(ModEffects.UNCONSCIOUS) || !EntityStatsCapability.get((LivingEntity)entity).hasShadow()) {
/* 35 */         callback.cancel();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ModifyArgs(method = {"renderEntityStatic"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRendererManager;renderShadow(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/entity/Entity;FFLnet/minecraft/world/IWorldReader;F)V"))
/*    */   public void shadowSize(Args args) {
/* 48 */     Entity entity = (Entity)args.get(2);
/*    */     
/* 50 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/*    */       
/* 52 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)entity);
/* 53 */       if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
/*    */         
/* 55 */         ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)entity);
/* 56 */         if (info != null && info.getShadowSize() >= 0.0F)
/*    */         {
/* 58 */           args.set(6, Float.valueOf(info.getShadowSize()));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\EntityRendererManagerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */