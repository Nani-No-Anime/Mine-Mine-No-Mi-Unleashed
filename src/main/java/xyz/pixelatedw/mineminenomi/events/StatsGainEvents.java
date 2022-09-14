package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.events.BountyEvent;
import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

@EventBusSubscriber(modid = "mineminenomi")
public class StatsGainEvents {
  //static private Logger LOGGER = LogManager.getLogger("mineminenomi");

  @SubscribeEvent
  public static void onEntityDeath(LivingDeathEvent event) {
    if (event.getSource().getTrueSource() instanceof PlayerEntity) {

      PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
      IEntityStats props = EntityStatsCapability.get((LivingEntity) player);
      LivingEntity target = event.getEntityLiving();

      IAttributeInstance attrAtk = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
      IAttributeInstance attrHP = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

      long plusBelly = 0L;
      long plusBounty = 0L;
      double plusDoriki = 0.0D;
      if (target instanceof PlayerEntity) {
        IEntityStats targetprops = EntityStatsCapability.get(target);
        if (props.getDoriki() * 0.75D <= targetprops.getDoriki()) {
          plusDoriki = (targetprops.getDoriki() / 4.0F);
        }
        plusBounty = targetprops.getBounty() / 2L;
        plusBelly = targetprops.getBelly();
      } else {
        if (props.isMarine() && target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity) {
          return;
        }
        if (target instanceof OPEntity) {
          OPEntity entity = (OPEntity) target;
          /*
           * if ( / 100 > ) { if (CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled()) { plusDoriki = 1.0D; } } else { plusDoriki =
           * entity.getDoriki(); }
           */
          plusDoriki = dorikiCalculation(props.getDoriki(), CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled() ? Math.max(entity.getDoriki(), 1) : entity.getDoriki(),entity.getThreat());
          plusDoriki *= CommonConfig.INSTANCE.getDorikiRewardMultiplier();
          plusBounty = (entity.getDoriki() * 2);
          plusBelly = entity.getBelly();
        } else if (attrAtk != null && attrHP != null) {
          IEntityStats targetProps = EntityStatsCapability.get(target);
          //LOGGER.log(Level.INFO, targetProps.getDoriki() + "");

          double i = attrAtk.getBaseValue();
          double j = attrHP.getBaseValue();
          // (int) Math.round((i + j) / 4.0D)
          Integer targetDoriki = CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled() ? Math.max(targetProps.getDoriki(), 1) : targetProps.getDoriki();
          /*
           * if ((props.getDoriki() / 100.0F) > targetDoriki) { if (CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled()) { plusDoriki =
           * 1.0D; } } else { plusDoriki = targetDoriki; }
           */
          plusDoriki = (double) dorikiCalculation(props.getDoriki(), targetDoriki, props.getThreat());
          plusDoriki *= CommonConfig.INSTANCE.getDorikiRewardMultiplier();
          plusBounty = (int) Math.round((i + j) / 10.0D);
          plusBelly = 1L;
          if (target instanceof net.minecraft.entity.merchant.villager.VillagerEntity) {
            plusBounty = 10L;
          }
        } else {
          plusDoriki = 0.0D;
          plusBounty = 1L;
        }
      }

      if (plusDoriki > 0.0D) {
        if (props.getDoriki() + plusDoriki <= CommonConfig.INSTANCE.getDorikiLimit()) {
          if (1.0D > CommonConfig.INSTANCE.getDorikiRewardMultiplier() && CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled() && plusDoriki > 0.0D && plusDoriki < 1.0D) {
            double random = WyHelper.randomWithRange(0, 100);
            if (plusDoriki * 100.0D > random) {
              plusDoriki = 1.0D;
            }
          }
          props.alterDoriki((int) Math.round(plusDoriki));
          DorikiEvent e = new DorikiEvent(player, props.getDoriki());
          if (MinecraftForge.EVENT_BUS.post((Event) e)) {
            return;
          }
        } else if (props.getDoriki() + plusDoriki > CommonConfig.INSTANCE.getDorikiLimit()) {
          props.setDoriki(CommonConfig.INSTANCE.getDorikiLimit());
          DorikiEvent e = new DorikiEvent(player, props.getDoriki());
          if (MinecraftForge.EVENT_BUS.post((Event) e)) {
            return;
          }
        }
      }

      if (BountyHelper.canGainBounty(player)) {
        if (plusBounty > 0L && props.getBounty() + plusBounty < 999999999L) {
          BountyEvent bountyEvent = new BountyEvent(player, plusBounty);
          if (!MinecraftForge.EVENT_BUS.post((Event) bountyEvent)) {
            props.alterBounty(plusBounty);
          }
        }
      }

      if (props.getBelly() + plusBelly < 999999999L) {
        props.alterBelly(plusBelly);
      }
      WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
    }
  }

  private static Integer dorikiCalculation(Integer playerDoriki, Integer mobDoriki, Integer mobThreat) { return dorikiCalculation(playerDoriki, mobDoriki, mobThreat, 600); }

  private static Integer dorikiCalculation(Integer playerDoriki, Integer mobDoriki, Integer mobThreat, Integer base) {

    Integer doriki = 0;
    if(mobThreat > playerDoriki / 2000 || mobThreat == -1 ){
      if (playerDoriki >= mobDoriki) {
        doriki = Math.round(mobDoriki / (float) ((Math.log(playerDoriki - mobDoriki) / Math.log(base)) + 1));
      } else {
        doriki = mobDoriki;
      }
    }
    return doriki;
  }
}
