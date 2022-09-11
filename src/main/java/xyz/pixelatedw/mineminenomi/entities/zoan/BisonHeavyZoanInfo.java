package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.ushibison.BisonHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.BisonHeavyModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class BisonHeavyZoanInfo
  extends ZoanInfo
{
  public static final BisonHeavyZoanInfo INSTANCE = new BisonHeavyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.9F, 2.8F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.9F, 2.6F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new BisonHeavyModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.6F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.USHI_USHI_NO_MI_BISON;
  }


  
  public String getForm() {
    return "heavy";
  }


  
  public Ability getAbility() {
    return (Ability)BisonHeavyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 2.7D;
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


