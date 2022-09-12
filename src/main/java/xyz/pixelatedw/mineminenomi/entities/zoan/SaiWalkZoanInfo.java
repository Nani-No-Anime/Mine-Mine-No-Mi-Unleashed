package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.sai.SaiWalkPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.SaiWalkModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class SaiWalkZoanInfo
  extends ZoanInfo
{
  public static final SaiWalkZoanInfo INSTANCE = new SaiWalkZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.7F, 2.3F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.7F, 2.2F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new SaiWalkModel();
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));
    
    float scale = 2.0F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.SAI_SAI_NO_MI;
  }


  
  public String getForm() {
    return "walk";
  }


  
  public Ability getAbility() {
    return (Ability)SaiWalkPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 2.1D;
  }


  
  public float getShadowSize() {
    return 1.5F;
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


