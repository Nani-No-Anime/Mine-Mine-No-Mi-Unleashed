package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;

@Mixin({GameRenderer.class})
public abstract class GameRendererMixin
  implements ISelectiveResourceReloadListener
{
  @Shadow
  @Final
  private Minecraft mc;
  
  @ModifyConstant(method = {"getMouseOver(F)V"}, require = 1, allow = 1, constant = {@Constant(doubleValue = 6.0D)})
  private double getActualAttackRangeInCreative(double attackRange) {
    if (this.mc.player != null)
      return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.mc.player, attackRange); 
    return attackRange;
  }

  
  @ModifyVariable(method = {"getMouseOver(F)V"}, at = @At("STORE"), ordinal = 1)
  private double getActualAttackRangeInSurvival0(double attackRange) {
    if (this.mc.player != null)
      return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.mc.player, attackRange); 
    return attackRange;
  }

  
  @ModifyConstant(method = {"getMouseOver(F)V"}, constant = {@Constant(doubleValue = 9.0D)})
  private double getActualAttackRangeInSurvival1(double attackRange) {
    if (this.mc.player != null)
      return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.mc.player, attackRange); 
    return attackRange;
  }
}


