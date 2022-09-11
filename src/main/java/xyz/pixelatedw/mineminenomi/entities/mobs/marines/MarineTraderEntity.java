package xyz.pixelatedw.mineminenomi.entities.mobs.marines;

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

public class MarineTraderEntity extends TraderEntity {
	private static final String[] DEFAULT_TEXTURES = new String[] { "marine_trader1", "marine_trader2", "akainu", "aokiji", "coby", "garp", "kizaru", "smoker" };

	public MarineTraderEntity(World world) { super(ModEntities.MARINE_TRADER, world, DEFAULT_TEXTURES); }

	public boolean canTrade(PlayerEntity player) {
		IEntityStats props = EntityStatsCapability.get((LivingEntity) player);
		return (props.isMarine() || props.isBountyHunter());
	}

	public ResourceLocation getTradeTable() { return ModLootTables.MARINE_TRADER; }

	public String getTradeFailMessage() { return (new TranslationTextComponent(ModI18n.TRADER_NO_PIRATE, new Object[0])).getFormattedText(); }

	public Currency getCurrency() { return Currency.BELLY; }
}
