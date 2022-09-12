package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.MiniRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Map;


public class MiniZoanInfo
  extends ZoanInfo
{
  public static final MiniZoanInfo INSTANCE = new MiniZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.2F, 0.4F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.2F, 0.39F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    boolean isSlim = entity.getSkinType().equals("slim");
    return (IRenderFactory)new MiniRenderer.Factory(this, isSlim);
  }






  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }



  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}



  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.MINI_MINI_NO_MI;
  }


  
  public String getForm() {
    return "mini";
  }


  
  public Ability getAbility() {
    return (Ability)MiniMiniAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 0.4D;
  }


  
  public float getShadowSize() {
    return 0.2F;
  }



  
  @OnlyIn(Dist.CLIENT)
  public double getCameraHeight(PlayerEntity player) {
    boolean isFirstPerson = ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0);
    boolean shouldSit = (player.isPassenger() && player.getRidingEntity() != null && player.getRidingEntity().shouldRiderSit());
    if (isFirstPerson && shouldSit)
    {
      return 0.5D;
    }
    return 0.0D;
  }


  
  public Map<Pose, EntitySize> getSizes() {
    return ImmutableMap.<Pose, EntitySize>builder()
      .put(Pose.STANDING, STANDING_SIZE)
      .put(Pose.CROUCHING, CROUCHING_SIZE)
      .build();
  }
}


