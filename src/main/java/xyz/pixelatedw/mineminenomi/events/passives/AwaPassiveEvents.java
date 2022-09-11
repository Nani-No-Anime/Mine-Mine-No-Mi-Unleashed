package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.awa.SoapDefenseAbility;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.models.abilities.SoapDefenseModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;




@EventBusSubscriber(modid = "mineminenomi")
public class AwaPassiveEvents
{
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class Client
  {
    private static final SoapDefenseModel SOAP = new SoapDefenseModel();

    
    @SubscribeEvent
    public static void onEntityRendered(RenderLivingEvent.Pre event) {
      if (!(event.getEntity() instanceof PlayerEntity)) {
        return;
      }
      PlayerEntity player = (PlayerEntity)event.getEntity();
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
      
      Ability ability = abilityProps.getEquippedAbility(SoapDefenseAbility.INSTANCE);
      if (ability == null || !ability.isContinuous()) {
        return;
      }
      event.setCanceled(true);
      
      event.getMatrixStack().push();
      
      event.getMatrixStack().translate(0.0D, player.getEyeHeight() - 1.2D, 0.0D);
      
      event.getMatrixStack().scale(1.2F, 1.2F, 1.2F);
      
      SOAP.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityBody(ModResources.AWA_SOAP)), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
      
      RenderSystem.enableLighting();
      event.getMatrixStack().pop();
    }
  }
}


