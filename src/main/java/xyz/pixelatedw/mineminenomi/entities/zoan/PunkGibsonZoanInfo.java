package xyz.pixelatedw.mineminenomi.entities.zoan;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.jiki.PunkGibsonAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.PunkGibsonPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PunkGibsonZoanInfo
  extends ZoanInfo {
  public static final PunkGibsonZoanInfo INSTANCE = new PunkGibsonZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    return (IRenderFactory)new PunkGibsonPartialMorphRenderer.Factory(this, false);
  }


  
  public String getForm() {
    return "punk_gibson";
  }


  
  public Ability getAbility() {
    return (Ability)PunkGibsonAbility.INSTANCE;
  }


  
  public ZoanMorphModel getModel() {
    return null;
  }
}


