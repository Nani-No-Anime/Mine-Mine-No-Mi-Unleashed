package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.GearFourthModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.GearFourthRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class GearFourthZoanInfo
  extends ZoanInfo
{
  public static final GearFourthZoanInfo INSTANCE = new GearFourthZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.8F, 4.0F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.8F, 3.9F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory() {
    return (IRenderFactory)new GearFourthRenderer.Factory(this);
  }


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new GearFourthModel(true);
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float partialTickTime) {
    double x = entity.prevPosX - entity.getPosX();
    double z = entity.prevPosZ - entity.getPosZ();
    boolean isMoving = (x != 0.0D || z != 0.0D);
    if (isMoving) {
      
      matrixStack.translate(0.0D, -2.0D, 2.0D);
      matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
    } 
  }


  
  public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
    return entity.getLocationSkin();
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.GOMU_GOMU_NO_MI;
  }


  
  public String getForm() {
    return "gear_4th";
  }


  
  public Ability getAbility() {
    return (Ability)GearFourthAbility.INSTANCE;
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


