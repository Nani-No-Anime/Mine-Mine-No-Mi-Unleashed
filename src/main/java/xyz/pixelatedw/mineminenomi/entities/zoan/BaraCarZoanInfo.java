package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.bara.BaraBaraCarAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.BaraCarPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class BaraCarZoanInfo
  extends ZoanInfo
{
  public static final BaraCarZoanInfo INSTANCE = new BaraCarZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.3F, 0.7F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new BaraCarPartialMorphRenderer.Factory(this);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.translate(0.0D, -0.15D, 0.0D);
  }


  
  public ResourceLocation getTexture() {
    return null;
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.BARA_BARA_NO_MI;
  }


  
  public String getForm() {
    return "bara_car";
  }


  
  public Ability getAbility() {
    return (Ability)BaraBaraCarAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 0.7D;
  }


  
  public float getShadowSize() {
    return 1.1F;
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


