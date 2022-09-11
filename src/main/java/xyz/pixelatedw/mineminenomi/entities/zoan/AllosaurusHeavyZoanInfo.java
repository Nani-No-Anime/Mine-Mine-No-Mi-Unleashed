package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AllosaurusHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.AllosaurusHeavyPartialModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.AllosaurusHeavyPartialMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;



public class AllosaurusHeavyZoanInfo
  extends ZoanInfo
{
  public static final AllosaurusHeavyZoanInfo INSTANCE = new AllosaurusHeavyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.9F, 2.8F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.9F, 2.6F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    boolean isSlim = entity.getSkinType().equals("slim");
    return (IRenderFactory)new AllosaurusHeavyPartialMorphRenderer.Factory(this, isSlim);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new AllosaurusHeavyPartialModel();
  }


  
  @Nullable
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.RYU_RYU_NO_MI_ALLOSAURUS;
  }


  
  public boolean isPartial() {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonHand() {
    return false;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonLeg() {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.4F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public String getForm() {
    return "heavy";
  }


  
  public Ability getAbility() {
    return (Ability)AllosaurusHeavyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 2.5D;
  }


  
  public float getShadowSize() {
    return 0.8F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


