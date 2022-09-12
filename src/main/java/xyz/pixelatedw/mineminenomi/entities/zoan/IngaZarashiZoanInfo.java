package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.karu.IngaZarashiAbility;
import xyz.pixelatedw.mineminenomi.abilities.karu.KarmaAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ModifiedPlayerRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.util.Map;

public class IngaZarashiZoanInfo
  extends ZoanInfo
{
  public static final IngaZarashiZoanInfo INSTANCE = new IngaZarashiZoanInfo();
  private float karma = 0.0F;


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    KarmaAbility abl = (KarmaAbility)AbilityDataCapability.get((LivingEntity)entity).getUnlockedAbility((Ability)KarmaAbility.INSTANCE);
    if (abl != null)
      this.karma = abl.getKarma(); 
    boolean isSlim = entity.getSkinType().equals("slim");
    return (IRenderFactory)new ModifiedPlayerRenderer.Factory(this, isSlim, (1.0F + this.karma / 60.0F));
  }






  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonHand() {
    return true;
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
    return 1.85D + (this.karma / 30.0F);
  }


  
  public float getShadowSize() {
    return 0.5F + this.karma / 50.0F;
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
      .put(Pose.STANDING, EntitySize.flexible(0.6F + this.karma / 60.0F, 1.8F + this.karma / 30.0F))
      .put(Pose.CROUCHING, EntitySize.flexible(0.6F + this.karma / 60.0F, 1.6F + this.karma / 30.0F))
      .build();
  }
}


