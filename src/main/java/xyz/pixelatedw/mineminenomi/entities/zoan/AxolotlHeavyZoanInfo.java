package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.saraaxolotl.AxolotlHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.AxolotlHeavyModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;




public class AxolotlHeavyZoanInfo
  extends ZoanInfo
{
  public static final AxolotlHeavyZoanInfo INSTANCE = new AxolotlHeavyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.5F, 4.0F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.5F, 3.8F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new AxolotlHeavyModel();
  }


  
  @Nullable
  public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
    int scheme = Math.max(0, (int)(entity.getUniqueID().getMostSignificantBits() % 4L));
    return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/sara_sara_axolotl_" + getForm() + "_" + scheme + ".png");
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.6F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.SARA_SARA_NO_MI_AXOLOTL;
  }


  
  public String getForm() {
    return "heavy";
  }


  
  public Ability getAbility() {
    return (Ability)AxolotlHeavyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 3.7D;
  }


  
  public float getShadowSize() {
    return 1.8F;
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


