package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu.HitoDaibutsuPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.HitoDaibutsuModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.GlowingModelRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class HitoDaibutsuZoanInfo
  extends ZoanInfo
{
  public static final HitoDaibutsuZoanInfo INSTANCE = new HitoDaibutsuZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(3.2F, 10.0F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(3.2F, 9.8F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new GlowingModelRenderer.Factory(this, GlowingModelRenderer.Type.DAIBUTSU);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new HitoDaibutsuModel();
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}


  
  public ResourceLocation getTexture() {
    return null;
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.HITO_HITO_NO_MI_DAIBUTSU;
  }


  
  public String getForm() {
    return "daibutsu";
  }


  
  public Ability getAbility() {
    return (Ability)HitoDaibutsuPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 9.800000190734863D;
  }


  
  public float getShadowSize() {
    return 2.1F;
  }


  
  @OnlyIn(Dist.CLIENT)
  public double getCameraZoom(PlayerEntity player) {
    return 12.0D;
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


