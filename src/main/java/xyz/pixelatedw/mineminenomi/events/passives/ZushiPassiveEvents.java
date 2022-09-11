package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.zushi.AbareHimatsuriAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.models.abilities.AbareHimatsuriModel;
import xyz.pixelatedw.mineminenomi.renderers.abilities.AbareHimatsuriRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;



@EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
public class ZushiPassiveEvents
{
  private static final AbareHimatsuriRenderer.Factory ABARE_HIMATSURI = new AbareHimatsuriRenderer.Factory(new AbareHimatsuriModel());

  
  @SubscribeEvent
  public static void onEntityRendered(RenderLivingEvent.Pre event) {
    if (!(event.getEntity() instanceof net.minecraft.entity.player.PlayerEntity)) {
      return;
    }
    LivingEntity entity = event.getEntity();
    IDevilFruit props = DevilFruitCapability.get(entity);
    
    if (!props.hasDevilFruit(ModAbilities.ZUSHI_ZUSHI_NO_MI)) {
      return;
    }
    IAbilityData abilityProps = AbilityDataCapability.get(entity);
    
    Ability abareAbility = abilityProps.getEquippedAbility((Ability)AbareHimatsuriAbility.INSTANCE);
    boolean isAbareActive = (abareAbility != null && abareAbility.isContinuous());
    
    if (!isAbareActive) {
      return;
    }

    
    if (!(event.getEntity()).onGround)
      ABARE_HIMATSURI.createRenderFor(Minecraft.getInstance().getRenderManager()).render((Entity)entity, entity.rotationYaw, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight()); 
  }
}


