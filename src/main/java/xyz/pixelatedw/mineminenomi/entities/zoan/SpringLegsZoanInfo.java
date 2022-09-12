package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringHopperAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.SpringLegsPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class SpringLegsZoanInfo
  extends ZoanInfo
{
  public static final SpringLegsZoanInfo INSTANCE = new SpringLegsZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new SpringLegsPartialMorphRenderer.Factory(this);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}


  
  public ResourceLocation getTexture() {
    return null;
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.BANE_BANE_NO_MI;
  }


  
  public String getForm() {
    return "spring_legs";
  }


  
  public Ability getAbility() {
    return SpringHopperAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 0.0D;
  }


  
  public float getShadowSize() {
    return -1.0F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return null;
  }
}


