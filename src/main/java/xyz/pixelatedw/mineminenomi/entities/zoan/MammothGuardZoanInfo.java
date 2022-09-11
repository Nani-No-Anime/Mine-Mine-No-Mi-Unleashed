package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.zoumammoth.MammothGuardPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.MammothGuardModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class MammothGuardZoanInfo
  extends ZoanInfo
{
  public static final MammothGuardZoanInfo INSTANCE = new MammothGuardZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(5.5F, 6.5F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(5.5F, 6.3F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new MammothGuardModel();
  }


  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 2.5F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.ZOU_ZOU_NO_MI_MAMMOTH;
  }


  
  public String getForm() {
    return "guard";
  }


  
  public Ability getAbility() {
    return (Ability)MammothGuardPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 5.5D;
  }


  
  public float getShadowSize() {
    return 2.5F;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean hasCulling() {
    return true;
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


