package xyz.pixelatedw.mineminenomi.entities.mobs;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.api.enums.Currency;
import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;
import xyz.pixelatedw.mineminenomi.packets.server.trade.SUpdateTraderOffersPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenTraderScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class SkypieanTraderEntity
  extends TraderEntity {
  private static final String[] DEFAULT_TEXTURES = new String[] { "skypiean_trader1", "skypiean_trader2" };
  private int dirtTradesLeft = 120;

  
  public SkypieanTraderEntity(World world) {
    super(ModEntities.SKYPIEAN_TRADER, world, DEFAULT_TEXTURES);
    setCanBuyFromPlayers();
  }


  
  public boolean canTrade(PlayerEntity player) {
    return true;
  }


  
  public ResourceLocation getTradeTable() {
    return ModLootTables.SKYPIEAN_TRADER;
  }


  
  public void writeAdditional(CompoundNBT nbt) {
    super.writeAdditional(nbt);
    nbt.putInt("tradesLeft", this.dirtTradesLeft);
  }


  
  public void readAdditional(CompoundNBT nbt) {
    super.readAdditional(nbt);
    this.dirtTradesLeft = nbt.getInt("tradesLeft");
  }


  
  protected boolean processInteract(PlayerEntity player, Hand hand) {
    if (!player.world.isRemote) {
      
      WyNetwork.sendTo(new SOpenTraderScreenPacket(getEntityId()), player);
      WyNetwork.sendTo(new SUpdateTraderOffersPacket(getEntityId(), this.tradeEntries, this.dirtTradesLeft), player);
      return true;
    } 
    return false;
  }

  
  public void removeTradesLeft(int value) {
    if (this.dirtTradesLeft - value > 0) {
      this.dirtTradesLeft -= value;
    } else {
      this.dirtTradesLeft = 0;
    } 
  }
  
  public void setTradesLeft(int value) {
    this.dirtTradesLeft = value;
  }

  
  public int getTradesLeft() {
    return this.dirtTradesLeft;
  }

  
  public long getExtolLeftInStock() {
    return CurrencyHelper.getExtolFromBelly(this.dirtTradesLeft);
  }


  
  public String getTradeFailMessage() {
    return "";
  }


  
  public Currency getCurrency() {
    return Currency.EXTOL;
  }
}


