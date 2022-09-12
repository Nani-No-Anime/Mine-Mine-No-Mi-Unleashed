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
import xyz.pixelatedw.mineminenomi.abilities.goro.VoltAmaruAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.HitoDaibutsuModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.GlowingModelRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class VoltAmaruZoanInfo
  extends ZoanInfo
{
  public static final VoltAmaruZoanInfo INSTANCE = new VoltAmaruZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.4F, 6.5F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.4F, 6.2F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new GlowingModelRenderer.Factory(this, GlowingModelRenderer.Type.AMARU);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new HitoDaibutsuModel();
  }




  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.scale(0.5F, 0.55F, 0.5F);
    matrixStack.translate(0.0D, -1.5D, 0.0D);
  }


  
  public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
    return null;
  }


  
  public String getForm() {
    return "volt_amaru";
  }


  
  public Ability getAbility() {
    return (Ability)VoltAmaruAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 6.300000190734863D;
  }


  
  public float getShadowSize() {
    return 1.9F;
  }


  
  @OnlyIn(Dist.CLIENT)
  public double getCameraZoom(PlayerEntity player) {
    return 12.0D;
  }


  
  public boolean canMount() {
    return false;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


