package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.ryupteranodon.PteranodonAssaultPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.PteranodonAssaultPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class PteranodonAssaultZoanInfo
  extends ZoanInfo
{
  public static final PteranodonAssaultZoanInfo INSTANCE = new PteranodonAssaultZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new PteranodonAssaultPartialMorphRenderer.Factory(this);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonHand() {
    return true;
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}


  
  public ResourceLocation getTexture() {
    return null;
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.RYU_RYU_NO_MI_PTERANODON;
  }


  
  public String getForm() {
    return "assault";
  }


  
  public Ability getAbility() {
    return (Ability)PteranodonAssaultPointAbility.INSTANCE;
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


