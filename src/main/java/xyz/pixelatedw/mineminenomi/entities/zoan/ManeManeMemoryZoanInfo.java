package xyz.pixelatedw.mineminenomi.entities.zoan;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ManeManeMemoryRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.util.UUID;

public class ManeManeMemoryZoanInfo
  extends ZoanInfo {
  public static final ManeManeMemoryZoanInfo INSTANCE = new ManeManeMemoryZoanInfo();


  
  @OnlyIn(Dist.CLIENT)
  public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
    boolean isSlim = false;
    ManeManeMemoryAbility ability = (ManeManeMemoryAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility((Ability)ManeManeMemoryAbility.INSTANCE);
    if (ability != null && ability.isTransformationActive((LivingEntity)entity)) {
      
      UUID uuid = ability.getMemory().getUUID();
      PlayerEntity target = entity.world.getPlayerByUuid(uuid);
      if (target != null && target instanceof AbstractClientPlayerEntity) {
        isSlim = ((AbstractClientPlayerEntity)target).getSkinType().equals("slim");
      } else {
        isSlim = DefaultPlayerSkin.getSkinType(uuid).equals("slim");
      } 
    }  return (IRenderFactory)new ManeManeMemoryRenderer.Factory(this, isSlim);
  }






  
  @OnlyIn(Dist.CLIENT)
  public ZoanMorphModel getModel() {
    return null;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean shouldRenderFirstPersonHand() {
    return true;
  }


  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.MANE_MANE_NO_MI;
  }


  
  public String getForm() {
    return "mane_memory";
  }


  
  public Ability getAbility() {
    return (Ability)ManeManeMemoryAbility.INSTANCE;
  }
}


