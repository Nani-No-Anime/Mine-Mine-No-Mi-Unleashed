package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.GiraffeHeavyModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class GiraffeHeavyZoanInfo
  extends ZoanInfo
{
  public static final GiraffeHeavyZoanInfo INSTANCE = new GiraffeHeavyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.3F, 4.8F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.3F, 4.7F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new GiraffeHeavyModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.8F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.USHI_USHI_NO_MI_GIRAFFE;
  }


  
  public String getForm() {
    return "heavy";
  }


  
  public Ability getAbility() {
    return (Ability)GiraffeHeavyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 4.9D;
  }


  
  public float getShadowSize() {
    return 0.9F;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean hasCulling() {
    return true;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


