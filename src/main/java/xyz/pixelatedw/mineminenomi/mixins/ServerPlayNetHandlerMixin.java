package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.IServerPlayNetHandler;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.CClientStatusPacket;
import net.minecraft.network.play.client.CUseEntityPacket;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;





@Mixin({ServerPlayNetHandler.class})
public abstract class ServerPlayNetHandlerMixin
  implements IServerPlayNetHandler
{
  @Shadow
  public ServerPlayerEntity player;
  @Shadow
  @Final
  public MinecraftServer server;
  
  @ModifyConstant(method = {"processUseEntity(Lnet/minecraft/network/play/client/CUseEntityPacket;)V"}, constant = {@Constant(doubleValue = 36.0D)})
  public double getActualAttackRange(double attackRange, CUseEntityPacket packet) {
    if (packet.getAction() == CUseEntityPacket.Action.ATTACK) {
      return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.player, attackRange);
    }
    return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.player, attackRange);
  }









  
  @Inject(method = {"processClientStatus"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/server/management/PlayerList;recreatePlayerEntity(Lnet/minecraft/entity/player/ServerPlayerEntity;Lnet/minecraft/world/dimension/DimensionType;Z)Lnet/minecraft/entity/player/ServerPlayerEntity;", shift = At.Shift.BEFORE)}, cancellable = true)
  public void processClientStatus(CClientStatusPacket packet, CallbackInfo callback) {
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)this.player);
    
    if (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !props.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.INSTANCE.getForm())) {
      
      this.player = this.server.getPlayerList().recreatePlayerEntity(this.player, this.player.dimension, false);
      callback.cancel();
    } 
  }
}


