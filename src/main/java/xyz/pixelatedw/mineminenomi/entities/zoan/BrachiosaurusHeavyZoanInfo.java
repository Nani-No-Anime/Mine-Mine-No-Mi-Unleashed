package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus.BrachiosaurusHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.BrachiosaurusHeavyModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class BrachiosaurusHeavyZoanInfo
  extends ZoanInfo
{
  public static final BrachiosaurusHeavyZoanInfo INSTANCE = new BrachiosaurusHeavyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.3F, 3.2F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.3F, 3.0F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new BrachiosaurusHeavyModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.6F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.RYU_RYU_NO_MI_BRACHIOSAURUS;
  }


  
  public String getForm() {
    return "heavy";
  }


  
  public Ability getAbility() {
    return (Ability)BrachiosaurusHeavyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 3.5D;
  }


  
  public float getShadowSize() {
    return 1.3F;
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


