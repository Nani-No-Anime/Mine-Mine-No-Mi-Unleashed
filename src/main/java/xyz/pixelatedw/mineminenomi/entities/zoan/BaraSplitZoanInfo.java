package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.bara.BaraSplitAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.BaraSplitPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class BaraSplitZoanInfo
  extends ZoanInfo
{
  public static final BaraSplitZoanInfo INSTANCE = new BaraSplitZoanInfo();
  
  private static final EntitySize STANDING_SIZE = PlayerEntity.STANDING_SIZE;


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    boolean isSlim = entity.getSkinType().equals("slim");
    return (IRenderFactory)new BaraSplitPartialMorphRenderer.Factory(this, isSlim);
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
    return ModAbilities.BARA_BARA_NO_MI;
  }


  
  public String getForm() {
    return "bara_split";
  }


  
  public Ability getAbility() {
    return (Ability)BaraSplitAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 1.8D;
  }


  
  public float getShadowSize() {
    return 0.5F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .build();
  }
}


