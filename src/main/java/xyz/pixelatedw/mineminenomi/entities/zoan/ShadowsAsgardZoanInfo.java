package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.kage.ShadowsAsgardAbility;
import xyz.pixelatedw.mineminenomi.abilities.karu.IngaZarashiAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ModifiedPlayerRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class ShadowsAsgardZoanInfo
  extends ZoanInfo
{
  public static final ShadowsAsgardZoanInfo INSTANCE = new ShadowsAsgardZoanInfo();
  private float shadows = 0.0F;


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    this.shadows = ((ShadowsAsgardAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility((Ability)ShadowsAsgardAbility.INSTANCE)).getShadows();
    boolean isSlim = entity.getSkinType().equals("slim");
    return (IRenderFactory)new ModifiedPlayerRenderer.Factory(this, isSlim, (1.0F + this.shadows / 60.0F));
  }






  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }



  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}



  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.KARU_KARU_NO_MI;
  }


  
  public String getForm() {
    return "karu";
  }


  
  public Ability getAbility() {
    return (Ability)IngaZarashiAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 1.85D + (this.shadows / 30.0F);
  }


  
  public float getShadowSize() {
    return 0.5F + this.shadows / 50.0F;
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
      .put(Pose.STANDING, EntitySize.flexible(0.6F + this.shadows / 60.0F, 1.8F + this.shadows / 30.0F))
      .put(Pose.CROUCHING, EntitySize.flexible(0.6F + this.shadows / 60.0F, 1.6F + this.shadows / 30.0F))
      .build();
  }
}


