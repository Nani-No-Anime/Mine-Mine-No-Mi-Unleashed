package xyz.pixelatedw.mineminenomi.entities.zoan;

import javax.annotation.Nullable;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.kame.KameWalkPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.KameWalkPartialModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.KameWalkPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class KameWalkZoanInfo
  extends ZoanInfo
{
  public static final KameWalkZoanInfo INSTANCE = new KameWalkZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    boolean isSlim = entity.getSkinType().equals("slim");
    return (IRenderFactory)new KameWalkPartialMorphRenderer.Factory(this, isSlim);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new KameWalkPartialModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonHand() {
    return false;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonLeg() {
    return false;
  }


  
  public boolean isPartial() {
    return true;
  }


  
  public String getForm() {
    return "walk";
  }


  
  @Nullable
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.KAME_KAME_NO_MI;
  }


  
  public Ability getAbility() {
    return (Ability)KameWalkPointAbility.INSTANCE;
  }
}


