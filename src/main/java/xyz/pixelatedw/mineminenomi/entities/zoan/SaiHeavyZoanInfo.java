package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.sai.SaiHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.SaiHeavyModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class SaiHeavyZoanInfo
  extends ZoanInfo
{
  public static final SaiHeavyZoanInfo INSTANCE = new SaiHeavyZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.9F, 2.8F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.9F, 2.6F);


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new SaiHeavyModel();
  }



  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));
    
    float scale = 1.6F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.SAI_SAI_NO_MI;
  }


  
  public String getForm() {
    return "heavy";
  }


  
  public Ability getAbility() {
    return (Ability)SaiHeavyPointAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 2.7D;
  }


  
  public float getShadowSize() {
    return 0.8F;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder().put(Pose.STANDING, STANDING_SIZE).put(Pose.CROUCHING, CROUCHING_SIZE).build();
  }
}


