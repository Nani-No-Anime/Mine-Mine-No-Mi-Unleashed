package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AllosaurusWalkPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.AllosaurusWalkModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class AllosaurusWalkZoanInfo
  extends ZoanInfo
{
  public static final AllosaurusWalkZoanInfo INSTANCE = new AllosaurusWalkZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(3.0F, 3.7F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(3.0F, 3.6F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new AllosaurusWalkModel();
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}



  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.RYU_RYU_NO_MI_ALLOSAURUS;
  }


  
  public String getForm() {
    return "walk";
  }


  
  public Ability getAbility() {
    return (Ability)AllosaurusWalkPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 3.5D;
  }


  
  public float getShadowSize() {
    return 1.5F;
  }


  
  public boolean canMount() {
    return false;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    Builder<Pose, EntitySize> builder = ImmutableMap.builder();
    return builder
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


