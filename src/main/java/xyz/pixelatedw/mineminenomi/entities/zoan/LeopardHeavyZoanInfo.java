package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.nekoleopard.LeopardHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.LeopardHeavyModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class LeopardHeavyZoanInfo
  extends ZoanInfo
{
  public static final LeopardHeavyZoanInfo INSTANCE = new LeopardHeavyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.9F, 2.8F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.9F, 2.6F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new LeopardHeavyModel();
  }



  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}



  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.NEKO_NEKO_NO_MI_LEOPARD;
  }


  
  public String getForm() {
    return "heavy";
  }


  
  public Ability getAbility() {
    return (Ability)LeopardHeavyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 2.7D;
  }


  
  public float getShadowSize() {
    return 0.8F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


