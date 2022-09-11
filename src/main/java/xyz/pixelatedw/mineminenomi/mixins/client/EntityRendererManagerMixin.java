package xyz.pixelatedw.mineminenomi.mixins.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;





@Mixin({EntityRendererManager.class})
public class EntityRendererManagerMixin
{
  @Inject(method = {"renderShadow"}, at = {@At("HEAD")}, cancellable = true)
  private static void renderShadow(MatrixStack matrixStack, IRenderTypeBuffer buffer, Entity entity, float weight, float partialTicks, IWorldReader world, float size, CallbackInfo callback) {
    if (entity instanceof LivingEntity)
    {
      if (((LivingEntity)entity).isPotionActive(ModEffects.UNCONSCIOUS) || !EntityStatsCapability.get((LivingEntity)entity).hasShadow()) {
        callback.cancel();
      }
    }
  }






  
  @ModifyArgs(method = {"renderEntityStatic"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRendererManager;renderShadow(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/entity/Entity;FFLnet/minecraft/world/IWorldReader;F)V"))
  public void shadowSize(Args args) {
    Entity entity = (Entity)args.get(2);
    
    if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
      
      IDevilFruit props = DevilFruitCapability.get((LivingEntity)entity);
      if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
        
        ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)entity);
        if (info != null && info.getShadowSize() >= 0.0F)
        {
          args.set(6, Float.valueOf(info.getShadowSize()));
        }
      } 
    } 
  }
}


