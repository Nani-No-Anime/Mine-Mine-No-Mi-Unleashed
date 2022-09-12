package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.PunkCornaDioRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class PunkCornaDioZoanInfo
  extends ZoanInfo
{
  public static final PunkCornaDioZoanInfo INSTANCE = new PunkCornaDioZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(3.0F, 2.1F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    return (IRenderFactory)new PunkCornaDioRenderer.Factory(this);
  }


  
  public String getForm() {
    return "punk_corna_dio";
  }


  
  public Ability getAbility() {
    return null;
  }


  
  public ZoanMorphModel getModel() {
    return null;
  }


  
  public double getEyeHeight() {
    return 2.0D;
  }


  
  public float getShadowSize() {
    return 1.5F;
  }


  
  public boolean canMount() {
    return false;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .build();
  }
}


