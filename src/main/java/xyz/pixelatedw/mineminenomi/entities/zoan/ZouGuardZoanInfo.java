package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.zou.ZouGuardPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.ZouGuardModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class ZouGuardZoanInfo
  extends ZoanInfo
{
  public static final ZouGuardZoanInfo INSTANCE = new ZouGuardZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.5F, 3.0F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.5F, 2.9F);
  private static final EntitySize FLYING_SIZE = EntitySize.flexible(1.5F, 3.0F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new ZouGuardModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.8F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.ZOU_ZOU_NO_MI;
  }


  
  public String getForm() {
    return "guard";
  }


  
  public Ability getAbility() {
    return (Ability)ZouGuardPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 2.9D;
  }


  
  public float getShadowSize() {
    return 1.5F;
  }


  
  public boolean canMount() {
    return false;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .put(Pose.FALL_FLYING, FLYING_SIZE)
      .build();
  }
}


