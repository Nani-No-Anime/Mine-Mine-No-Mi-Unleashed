package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.saraaxolotl.AxolotlWalkPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.AxolotlWalkModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import javax.annotation.Nullable;
import java.util.Map;




public class AxolotlWalkZoanInfo
  extends ZoanInfo
{
  public static final AxolotlWalkZoanInfo INSTANCE = new AxolotlWalkZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.6F, 0.6F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.6F, 0.5F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new AxolotlWalkModel();
  }


  
  @Nullable
  public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
    int scheme = Math.max(0, (int)(entity.getUniqueID().getMostSignificantBits() % 4L));
    return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/sara_sara_axolotl_" + getForm() + "_" + scheme + ".png");
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.SARA_SARA_NO_MI_AXOLOTL;
  }


  
  public String getForm() {
    return "walk";
  }


  
  public Ability getAbility() {
    return (Ability)AxolotlWalkPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 0.5D;
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


