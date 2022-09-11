package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.PhoenixFlyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.PhoenixFlyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.FlyPointRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class PhoenixFlyZoanInfo
  extends ZoanInfo
{
  public static final PhoenixFlyZoanInfo INSTANCE = new PhoenixFlyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.2F, 1.2F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new FlyPointRenderer.Factory(this, true);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new PhoenixFlyModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.8F;
    matrixStack.scale(scale, scale, scale);
    matrixStack.translate(0.0D, 1.0D, 0.0D);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.TORI_TORI_NO_MI_PHOENIX;
  }


  
  public String getForm() {
    return "fly";
  }


  
  public Ability getAbility() {
    return (Ability)PhoenixFlyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 0.8D;
  }


  
  public float getShadowSize() {
    return 1.0F;
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


