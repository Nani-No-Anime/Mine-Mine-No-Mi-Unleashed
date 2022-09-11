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
import xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus.BrachiosaurusGuardPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.BrachiosaurusGuardModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class BrachiosaurusGuardZoanInfo
  extends ZoanInfo
{
  public static final BrachiosaurusGuardZoanInfo INSTANCE = new BrachiosaurusGuardZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(5.5F, 10.5F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(5.5F, 10.3F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new BrachiosaurusGuardModel();
  }


  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 3.5F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.RYU_RYU_NO_MI_BRACHIOSAURUS;
  }


  
  public String getForm() {
    return "guard";
  }


  
  public Ability getAbility() {
    return (Ability)BrachiosaurusGuardPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 10.0D;
  }


  
  public float getShadowSize() {
    return 3.5F;
  }


  
  @OnlyIn(Dist.CLIENT)
  public double getCameraZoom(PlayerEntity player) {
    return 8.0D;
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


