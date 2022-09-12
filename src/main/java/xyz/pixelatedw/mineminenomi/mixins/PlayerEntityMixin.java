package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.Map;


@Mixin({PlayerEntity.class})
public abstract class PlayerEntityMixin
  extends LivingEntity
{
  public PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
    super(type, world);
  }

  
  @ModifyConstant(method = {"attackTargetEntityWithCurrentItem(Lnet/minecraft/entity/Entity;)V"}, constant = {@Constant(doubleValue = 9.0D)})
  private double getActualAttackRange(double attackRange) {
    return AttributeHelper.getSquaredAttackRangeDistance(this, attackRange);
  }

  
  @Inject(method = {"getSize"}, at = {@At("HEAD")}, cancellable = true)
  public void getSize(Pose pose, CallbackInfoReturnable<EntitySize> callback) {
    IDevilFruit props = DevilFruitCapability.get(this);
    if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
      
      ZoanInfo info = MorphHelper.getZoanInfo(this);
      if (info == null)
        return; 
      Map<Pose, EntitySize> poses = info.getSizes();
      if (poses != null && poses.containsKey(getPose()) && poses.get(getPose()) != null) {
        callback.setReturnValue(poses.get(getPose()));
      }
    } 
  }







  
  @Inject(method = {"attackTargetEntityWithCurrentItem"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;spawnSweepParticles()V", shift = At.Shift.AFTER)})
  public void attackTargetEntityWithCurrentItem(Entity targetEntity, CallbackInfo callback) {
    targetEntity.hurtResistantTime = 0;
    targetEntity.attackEntityFrom((DamageSource)new EntityDamageSource("sweep_damage", (Entity)this), 0.0F);
  }
}


