package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeWalkPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.GiraffeWalkModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class GiraffeWalkZoanInfo
  extends ZoanInfo
{
  public static final GiraffeWalkZoanInfo INSTANCE = new GiraffeWalkZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.0F, 4.8F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.0F, 4.7F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new GiraffeWalkModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 2.0F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.USHI_USHI_NO_MI_GIRAFFE;
  }


  
  public String getForm() {
    return "walk";
  }


  
  public Ability getAbility() {
    return (Ability)GiraffeWalkPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 4.9D;
  }


  
  public float getShadowSize() {
    return 1.0F;
  }


  
  public boolean canMount() {
    return false;
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


