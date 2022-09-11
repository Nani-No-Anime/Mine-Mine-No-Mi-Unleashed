package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ReducedFallEffect
  extends Effect {
  public ReducedFallEffect() {
    super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
  }

  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    Vec3d startVec = entity.getPositionVec();
    
    boolean blockUnder = entity.world.rayTraceBlocks(new RayTraceContext(startVec, startVec.add(0.0D, -3.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)entity)).getType().equals(RayTraceResult.Type.BLOCK);
    
    if ((entity.getMotion()).y < -1.0E-5D && !blockUnder) {
      
      entity.setMotion(entity.getMotion().mul(1.0D, 0.1D, 1.0D));
      entity.velocityChanged = true;
    } 
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }
}


