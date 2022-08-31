package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaOverdriveAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeBooAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeVentAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.FreshFireAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.RadicalBeamAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.StrongRightAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.EleclawAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalLunaAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalMissileAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalShowerAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalTempestaAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.SulongAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KachiageHaisokuAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.TwoFishEngineAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.YarinamiAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.KamieAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RokuoganAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.ShiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

@EventBusSubscriber(modid = "mineminenomi")
public class AbilityProgressionEvents {
	@SubscribeEvent
	public static void onHakiExpGained(HakiExpEvent event) { HakiHelper.checkForUnlocks(event.getPlayer()); }

	@SubscribeEvent
	public static void onDorikiGained(DorikiEvent event) {
		IEntityStats props = EntityStatsCapability.get((LivingEntity) event.getPlayer());
		if (props.isHuman()) {
			gainAbility(event.getPlayer(), 500, SoruAbility.INSTANCE);
			gainAbility(event.getPlayer(), 1000, TekkaiAbility.INSTANCE);
			gainAbility(event.getPlayer(), 1250, GeppoAbility.INSTANCE);
			gainAbility(event.getPlayer(), 3000, ShiganAbility.INSTANCE);
			gainAbility(event.getPlayer(), 3600, KamieAbility.INSTANCE);
			gainAbility(event.getPlayer(), 5000, RankyakuAbility.INSTANCE);
			gainAbility(event.getPlayer(), 9000, RokuoganAbility.INSTANCE);
		} else if (props.isFishman()) {
			gainAbility(event.getPlayer(), 800, UchimizuAbility.INSTANCE);
			gainAbility(event.getPlayer(), 1500, KachiageHaisokuAbility.INSTANCE);
			gainAbility(event.getPlayer(), 2000, TwoFishEngineAbility.INSTANCE);
			gainAbility(event.getPlayer(), 3000, SamehadaShoteiAbility.INSTANCE);
			gainAbility(event.getPlayer(), 5000, MurasameAbility.INSTANCE);
			gainAbility(event.getPlayer(), 5500, YarinamiAbility.INSTANCE);
			gainAbility(event.getPlayer(), 7500, KarakusagawaraSeikenAbility.INSTANCE);
		} else if (props.isCyborg()) {
			gainAbility(event.getPlayer(), 0, FreshFireAbility.INSTANCE);
			gainAbility(event.getPlayer(), 0, ColaOverdriveAbility.INSTANCE);
			gainAbility(event.getPlayer(), 0, StrongRightAbility.INSTANCE);
			gainAbility(event.getPlayer(), 0, RadicalBeamAbility.INSTANCE);
			gainAbility(event.getPlayer(), 0, CoupDeVentAbility.INSTANCE);
			gainAbility(event.getPlayer(), 0, CoupDeBooAbility.INSTANCE);
		} else if (props.isMink()) {
			gainAbility(event.getPlayer(), 500, EleclawAbility.INSTANCE);
			gainAbility(event.getPlayer(), 800, ElectricalMissileAbility.INSTANCE);
			gainAbility(event.getPlayer(), 1200, SulongAbility.INSTANCE);
			gainAbility(event.getPlayer(), 3000, ElectricalTempestaAbility.INSTANCE);
			gainAbility(event.getPlayer(), 3600, ElectricalLunaAbility.INSTANCE);
			gainAbility(event.getPlayer(), 7000, ElectricalShowerAbility.INSTANCE);
		}
		HakiHelper.checkForUnlocks(event.getPlayer());
	}

	private static void gainAbility(PlayerEntity player, int doriki, Ability ability) {
		IEntityStats props = EntityStatsCapability.get((LivingEntity) player);
		IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity) player);
		if (props.getDoriki() >= doriki && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability))
			abilityProps.addUnlockedAbility(ability);
		if ((props.getDoriki() < doriki || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability) && abilityProps.getUnlockedAbility(ability).getUnlockType() != AbilityUnlock.COMMAND) {
			abilityProps.removeUnlockedAbility(ability);
		}
		WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
	}
}