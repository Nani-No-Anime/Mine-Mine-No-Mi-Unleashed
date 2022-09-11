package xyz.pixelatedw.mineminenomi.entities.zoan;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.jiki.DamnedPunkAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.PunkGibsonPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class DamnedPunkZoanInfo
  extends ZoanInfo {
  public static final DamnedPunkZoanInfo INSTANCE = new DamnedPunkZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    return (IRenderFactory)new PunkGibsonPartialMorphRenderer.Factory(this, true);
  }


  
  public String getForm() {
    return "damned_punk";
  }


  
  public Ability getAbility() {
    return (Ability)DamnedPunkAbility.INSTANCE;
  }


  
  public ZoanMorphModel getModel() {
    return null;
  }
}


