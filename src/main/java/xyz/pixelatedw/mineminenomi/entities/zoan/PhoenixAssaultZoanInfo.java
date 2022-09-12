package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.PhoenixAssaultPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.PhoenixAssaultPartialModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.PhoenixAssaultPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class PhoenixAssaultZoanInfo
  extends ZoanInfo
{
  public static final PhoenixAssaultZoanInfo INSTANCE = new PhoenixAssaultZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new PhoenixAssaultPartialMorphRenderer.Factory(this);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new PhoenixAssaultPartialModel();
  }


  
  public boolean isPartial() {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonHand() {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonLeg() {
    return true;
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.TORI_TORI_NO_MI_PHOENIX;
  }


  
  public String getForm() {
    return "assault";
  }


  
  public Ability getAbility() {
    return (Ability)PhoenixAssaultPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 1.75D;
  }


  
  public float getShadowSize() {
    return 0.5F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return null;
  }
}


