package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.YomiModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;



public class YomiZoanInfo
  extends ZoanInfo
{
  public static final YomiZoanInfo INSTANCE = new YomiZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.8F, 1.8F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.8F, 1.4F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new YomiModel();
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}


  
  public ResourceLocation getTexture() {
    return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/yomi_yomi_skeleton.png");
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.YOMI_YOMI_NO_MI;
  }


  
  public String getForm() {
    return "yomi";
  }


  
  public Ability getAbility() {
    return null;
  }


  
  public double getEyeHeight() {
    return 1.75D;
  }


  
  public float getShadowSize() {
    return 0.5F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


