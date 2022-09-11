package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.doru.CandleChampionAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.CandleChampionModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.CandleChampionRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;



public class CandleChampionZoanInfo
  extends ZoanInfo
{
  public static final CandleChampionZoanInfo INSTANCE = new CandleChampionZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.8F, 4.0F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.8F, 3.9F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new CandleChampionRenderer.Factory(this);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new CandleChampionModel();
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
  }


  
  public ResourceLocation getTexture() {
    return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/candle_lock.png");
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.DORU_DORU_NO_MI;
  }


  
  public String getForm() {
    return "candle_champion";
  }


  
  public Ability getAbility() {
    return (Ability)CandleChampionAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 4.0D;
  }


  
  public float getShadowSize() {
    return 1.2F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


