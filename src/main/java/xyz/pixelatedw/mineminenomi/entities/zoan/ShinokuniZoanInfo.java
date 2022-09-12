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
import xyz.pixelatedw.mineminenomi.abilities.gasu.ShinokuniAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.ShinokuniModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ShinokuniRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import javax.annotation.Nullable;
import java.util.Map;




public class ShinokuniZoanInfo
  extends ZoanInfo
{
  public static final ShinokuniZoanInfo INSTANCE = new ShinokuniZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.8F, 6.0F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.8F, 5.9F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new ShinokuniRenderer.Factory(this);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new ShinokuniModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.scale(3.0F, 3.0F, 3.0F);
  }


  
  @Nullable
  public ResourceLocation getTexture() {
    return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/shinokuni.png");
  }


  
  public String getForm() {
    return "shinokuni";
  }


  
  public Ability getAbility() {
    return (Ability)ShinokuniAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 5.699999809265137D;
  }


  
  public float getShadowSize() {
    return 1.2F;
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
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


