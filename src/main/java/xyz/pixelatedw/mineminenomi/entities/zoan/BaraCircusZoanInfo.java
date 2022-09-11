package xyz.pixelatedw.mineminenomi.entities.zoan;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.bara.KuchuKirimomiDaiCircusAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.BaraPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BaraCircusZoanInfo
  extends ZoanInfo {
  public static final BaraCircusZoanInfo INSTANCE = new BaraCircusZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new BaraPartialMorphRenderer.Factory(this, BaraPartialMorphRenderer.BaraMode.CIRCUS);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }


  
  public String getForm() {
    return "bara_circus";
  }


  
  public Ability getAbility() {
    return (Ability)KuchuKirimomiDaiCircusAbility.INSTANCE;
  }
}


