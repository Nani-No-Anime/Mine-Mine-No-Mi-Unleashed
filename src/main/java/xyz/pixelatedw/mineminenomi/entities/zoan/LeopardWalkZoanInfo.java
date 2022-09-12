package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.nekoleopard.LeopardWalkPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.LeopardWalkModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class LeopardWalkZoanInfo
  extends ZoanInfo
{
  public static final LeopardWalkZoanInfo INSTANCE = new LeopardWalkZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.0F, 1.3F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.0F, 1.1F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new LeopardWalkModel();
  }



  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}



  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.NEKO_NEKO_NO_MI_LEOPARD;
  }


  
  public String getForm() {
    return "walk";
  }


  
  public Ability getAbility() {
    return (Ability)LeopardWalkPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 1.3D;
  }


  
  public float getShadowSize() {
    return 0.9F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


