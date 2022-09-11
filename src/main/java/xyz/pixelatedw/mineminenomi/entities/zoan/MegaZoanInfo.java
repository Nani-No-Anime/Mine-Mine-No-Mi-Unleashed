package xyz.pixelatedw.mineminenomi.entities.zoan;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.mega.MegaMegaAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.MegaRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


public class MegaZoanInfo
  extends ZoanInfo
{
  public static final MegaZoanInfo INSTANCE = new MegaZoanInfo();
  
  private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.7F, 2.4F);
  private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.7F, 2.39F);


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    boolean isSlim = entity.getSkinType().equals("slim");
    return (IRenderFactory)new MegaRenderer.Factory(this, isSlim);
  }






  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }



  
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}



  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.MEGA_MEGA_NO_MI;
  }


  
  public String getForm() {
    return "mega";
  }


  
  public Ability getAbility() {
    return (Ability)MegaMegaAbility.INSTANCE;
  }


  
  public double getEyeHeight() {
    return 8.45D;
  }


  
  public float getShadowSize() {
    return 1.25F;
  }


  
  @OnlyIn(Dist.CLIENT)
  public double getCameraZoom(PlayerEntity player) {
    return 8.0D;
  }


  
  public boolean canMount() {
    return false;
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
            Builder<Pose, EntitySize> builder = ImmutableMap.builder();
    return builder
      .put(Pose.STANDING, EntitySize.flexible(4.0F, 8.5F))
      .put(Pose.CROUCHING, EntitySize.flexible(4.0F, 7.6F))
      .build();
  }
}


