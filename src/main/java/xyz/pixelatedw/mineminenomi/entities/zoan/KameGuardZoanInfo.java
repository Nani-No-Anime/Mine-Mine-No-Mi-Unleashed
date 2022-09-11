package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.kame.KameGuardPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.KameGuardModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class KameGuardZoanInfo
  extends ZoanInfo
{
  public static final KameGuardZoanInfo INSTANCE = new KameGuardZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.7F, 0.7F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.7F, 0.6F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new KameGuardModel();
  }


  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 2.0F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.KAME_KAME_NO_MI;
  }


  
  public String getForm() {
    return "guard";
  }


  
  public Ability getAbility() {
    return (Ability)KameGuardPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 0.7D;
  }


  
  public float getShadowSize() {
    return 1.1F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


