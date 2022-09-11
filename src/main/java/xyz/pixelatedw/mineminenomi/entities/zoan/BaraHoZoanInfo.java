package xyz.pixelatedw.mineminenomi.entities.zoan;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.BaraPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BaraHoZoanInfo
  extends ZoanInfo {
  public static final BaraHoZoanInfo INSTANCE = new BaraHoZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    return (IRenderFactory)new BaraPartialMorphRenderer.Factory(this, BaraPartialMorphRenderer.BaraMode.HO);
  }


  
  public String getForm() {
    return "bara_ho";
  }


  
  public Ability getAbility() {
    return null;
  }


  
  public ZoanMorphModel getModel() {
    return null;
  }
}


