package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.api.enums.Currency;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;

public class PirateTraderEntity extends TraderEntity {
	public static final String[] DEFAULT_TEXTURES = new String[] { "pirate_trader1", "pirate_trader2", "ace" };

	// "pirate_trader_usopp", "akatsuki_chopper", "alvida", "arlong", "barto", "bellamy", "bepo", "boa",
	// "bonclay_impel_down", "brook", "buggy","cabaji", "chopper", "chopper_girl", "crocodile", "crocus", "dalton", "damn_daniel",
	// "foxy", "franky", "hatchan", "killer", "kuro", "law", "luffy_chopper", "luffy_wc", "maid_sanji", "maid_zoro", "moria", "mr3",
	// "nami", "nami_ts", "patty","perona_ts", "pirate_trader_usopp", "robin", "sanji", "usopp", "whitebeard", "zeff", "zoro_ts"

	public PirateTraderEntity(World world) {     
		super(ModEntities.PIRATE_TRADER, world, DEFAULT_TEXTURES); 
	}

	public boolean canTrade(PlayerEntity player) {
		IEntityStats props = EntityStatsCapability.get((LivingEntity) player);
		return (props.isPirate() || props.isRevolutionary() || props.isBandit());
	}

	public ResourceLocation getTradeTable() {
		// public static final ResourceLocation PIRATE_TRADER = new ResourceLocation("mineminenomi", "entities/trader/pirate_trader");
		return ModLootTables.PIRATE_TRADER;
	}

	public String getTradeFailMessage() { return (new TranslationTextComponent(ModI18n.TRADER_NO_MARINE, new Object[0])).getFormattedText(); }

	public Currency getCurrency() { return Currency.BELLY; }
}