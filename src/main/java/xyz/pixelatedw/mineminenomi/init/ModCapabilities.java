package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataProvider;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitProvider;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsProvider;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataProvider;
import xyz.pixelatedw.mineminenomi.wypi.APIDefaults;






@EventBusSubscriber(modid = "mineminenomi")
public class ModCapabilities
{
  public static void init() {
    APIDefaults.initCapabilities();

    
    DevilFruitCapability.register();
    HakiDataCapability.register();
    EntityStatsCapability.register();
    ChallengesDataCapability.register();
  }

  
  @SubscribeEvent
  public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject() == null) {
      return;
    }
    if (event.getObject() instanceof net.minecraft.entity.player.PlayerEntity) {
      
      event.addCapability(new ResourceLocation("mineminenomi", "haki_data"), (ICapabilityProvider)new HakiDataProvider());
      event.addCapability(new ResourceLocation("mineminenomi", "challenges"), (ICapabilityProvider)new ChallengesDataProvider());
    } 
    
    if (event.getObject() instanceof net.minecraft.entity.LivingEntity) {
      
      event.addCapability(new ResourceLocation("mineminenomi", "devil_fruit"), (ICapabilityProvider)new DevilFruitProvider());
      event.addCapability(new ResourceLocation("mineminenomi", "entity_stats"), (ICapabilityProvider)new EntityStatsProvider());
    } 
  }
}


