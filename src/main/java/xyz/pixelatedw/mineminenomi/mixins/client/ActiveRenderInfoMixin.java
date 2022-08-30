/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @Mixin({ActiveRenderInfo.class})
/*    */ public class ActiveRenderInfoMixin
/*    */ {
/*    */   @Shadow
/*    */   public Vec3d pos;
/*    */   
/*    */   @Inject(method = {"calcCameraDistance"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void calcCameraDistance(double startingDistance, CallbackInfoReturnable<Double> callback) {
/* 29 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 30 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/* 31 */     ZoanInfo info = null;
/*    */     
/* 33 */     if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
/*    */       
/* 35 */       info = MorphHelper.getZoanInfo((LivingEntity)clientPlayerEntity);
/* 36 */       if (info != null) {
/*    */         
/* 38 */         double zoom = info.getCameraZoom((PlayerEntity)clientPlayerEntity);
/* 39 */         if (zoom != 0.0D) {
/* 40 */           callback.setReturnValue(Double.valueOf(zoom));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"setPosition"}, at = {@At("TAIL")})
/*    */   public void setPosition(double x, double y, double z, CallbackInfo callback) {
/* 48 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 49 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/* 50 */     ZoanInfo info = null;
/*    */     
/* 52 */     if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
/*    */       
/* 54 */       info = MorphHelper.getZoanInfo((LivingEntity)clientPlayerEntity);
/* 55 */       if (info != null) {
/*    */         
/* 57 */         double height = info.getCameraHeight((PlayerEntity)clientPlayerEntity);
/* 58 */         if (height != 0.0D)
/* 59 */           this.pos = new Vec3d(x, y + height, z); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\ActiveRenderInfoMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */