package xyz.pixelatedw.mineminenomi.api.morph;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import javax.annotation.Nullable;
import java.util.Map;










public abstract class ZoanInfo
{
  @Nullable
  public AkumaNoMiItem getDevilFruit() {
    return null;
  }


  
  public abstract String getForm();

  
  public boolean isActive(LivingEntity player) {
    return DevilFruitCapability.get(player).getZoanPoint().equalsIgnoreCase(getForm());
  }

  
  @Deprecated
  public abstract Ability getAbility();
  
  @Deprecated
  public String getDisplayName() {
    return getAbility().getDisplayName();
  }
  @OnlyIn(Dist.CLIENT)
  public abstract ZoanMorphModel getModel();
  @Nullable
  public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
    return getTexture();
  }

  
  @Nullable
  public ResourceLocation getTexture() {
    if (getDevilFruit() != null) {
      return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/" + DevilFruitHelper.getDevilFruitKey(getDevilFruit()) + "_" + getForm() + ".png");
    }
    return null;
  }

  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    return getRendererFactory();
  }

  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new ZoanMorphRenderer.Factory(this, hasCulling());
  }

  
  public boolean isPartial() {
    return false;
  }

  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonHand() {
    return (getModel() != null);
  }

  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonLeg() {
    return shouldRenderFirstPersonHand();
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}


  
  @Nullable
  public Map<Pose, EntitySize> getSizes() {
    return null;
  }

  
  public double getEyeHeight() {
    return 0.0D;
  }

  
  public float getShadowSize() {
    return -1.0F;
  }

  
  public boolean canMount() {
    return true;
  }

  
  @OnlyIn(Dist.CLIENT)
  public boolean hasCulling() {
    return false;
  }

  
  @OnlyIn(Dist.CLIENT)
  public double getCameraZoom(PlayerEntity player) {
    return 0.0D;
  }

  
  @OnlyIn(Dist.CLIENT)
  public double getCameraHeight(PlayerEntity player) {
    return 0.0D;
  }
}


