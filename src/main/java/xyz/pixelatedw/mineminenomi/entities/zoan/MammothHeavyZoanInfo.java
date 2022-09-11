package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.zoumammoth.MammothHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.MammothHeavyModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class MammothHeavyZoanInfo
  extends ZoanInfo
{
  public static final MammothHeavyZoanInfo INSTANCE = new MammothHeavyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.2F, 5.4F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.9F, 2.6F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new MammothHeavyModel();
  }


  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 2.0F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.ZOU_ZOU_NO_MI_MAMMOTH;
  }


  
  public String getForm() {
    return "heavy";
  }


  
  public Ability getAbility() {
    return (Ability)MammothHeavyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 5.3D;
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
      .build();
  }
}


