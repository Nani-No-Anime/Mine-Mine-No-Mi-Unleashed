package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

@Mixin({ActiveRenderInfo.class})
public class ActiveRenderInfoMixin
{
  @Shadow
  public Vec3d pos;
  
  @Inject(method = {"calcCameraDistance"}, at = {@At("HEAD")}, cancellable = true)
  public void calcCameraDistance(double startingDistance, CallbackInfoReturnable<Double> callback) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
    ZoanInfo info = null;
    
    if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
      
      info = MorphHelper.getZoanInfo((LivingEntity)clientPlayerEntity);
      if (info != null) {
        
        double zoom = info.getCameraZoom((PlayerEntity)clientPlayerEntity);
        if (zoom != 0.0D) {
          callback.setReturnValue(Double.valueOf(zoom));
        }
      } 
    } 
  }
  
  @Inject(method = {"setPosition"}, at = {@At("TAIL")})
  public void setPosition(double x, double y, double z, CallbackInfo callback) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
    ZoanInfo info = null;
    
    if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
      
      info = MorphHelper.getZoanInfo((LivingEntity)clientPlayerEntity);
      if (info != null) {
        
        double height = info.getCameraHeight((PlayerEntity)clientPlayerEntity);
        if (height != 0.0D)
          this.pos = new Vec3d(x, y + height, z); 
      } 
    } 
  }
}


