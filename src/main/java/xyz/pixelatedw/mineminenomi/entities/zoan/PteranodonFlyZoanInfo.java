package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.ryupteranodon.PteranodonFlyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.PteranodonFlyModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.FlyPointRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class PteranodonFlyZoanInfo
  extends ZoanInfo
{
  public static final PteranodonFlyZoanInfo INSTANCE = new PteranodonFlyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.2F, 1.2F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new FlyPointRenderer.Factory(this, false);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new PteranodonFlyModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.8F;
    matrixStack.scale(scale, scale, scale);
  }



  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.RYU_RYU_NO_MI_PTERANODON;
  }


  
  public String getForm() {
    return "fly";
  }


  
  public Ability getAbility() {
    return (Ability)PteranodonFlyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 0.8D;
  }


  
  public float getShadowSize() {
    return 1.0F;
  }


  
  @OnlyIn(Dist.CLIENT)
  public double getCameraZoom(PlayerEntity player) {
    return 7.0D;
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


