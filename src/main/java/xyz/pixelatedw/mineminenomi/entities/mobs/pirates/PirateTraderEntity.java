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
  private static final String[] DEFAULT_TEXTURES = new String[] { "pirate_trader1", "pirate_trader2" };
  
  public static void loadCharcterTraders(){
	
  }
  
  public PirateTraderEntity(World world) {
    super(ModEntities.PIRATE_TRADER, world, DEFAULT_TEXTURES);
  }


  
  public boolean canTrade(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    return (props.isPirate() || props.isRevolutionary() || props.isBandit());
  }


  
  public ResourceLocation getTradeTable() {
	 //public static final ResourceLocation PIRATE_TRADER = new ResourceLocation("mineminenomi", "entities/trader/pirate_trader");
    return ModLootTables.PIRATE_TRADER;
  }


  
  public String getTradeFailMessage() {
    return (new TranslationTextComponent(ModI18n.TRADER_NO_MARINE, new Object[0])).getFormattedText();
  }


  
  public Currency getCurrency() {
    return Currency.BELLY;
  }
}