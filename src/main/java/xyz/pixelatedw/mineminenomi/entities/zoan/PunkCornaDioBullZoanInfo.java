package xyz.pixelatedw.mineminenomi.entities.zoan;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.jiki.PunkCornaDioAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.PunkCornaDioModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PunkCornaDioBullZoanInfo
  extends ZoanInfo
{
  public static final PunkCornaDioBullZoanInfo INSTANCE = new PunkCornaDioBullZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return (ZoanMorphModel)new PunkCornaDioModel();
  }


  
  @OnlyIn(Dist.CLIENT)
  public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.5F;
    matrixStack.scale(scale, scale, scale);
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.JIKI_JIKI_NO_MI;
  }


  
  public String getForm() {
    return "corna_dio_bull";
  }


  
  public Ability getAbility() {
    return (Ability)PunkCornaDioAbility.INSTANCE;
  }


  
  public float getShadowSize() {
    return 0.9F;
  }
}


