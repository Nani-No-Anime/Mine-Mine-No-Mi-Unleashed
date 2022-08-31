package xyz.pixelatedw.mineminenomi.packets.client.trade;

import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.api.enums.Currency;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class CBuyFromTraderPacket {
	private int traderId;
	private ItemStack stack;

	public CBuyFromTraderPacket(int traderId, ItemStack stack, int amount) {
		this.traderId = traderId;
		this.stack = stack;
		this.amount = amount;
	}

	private int amount;

	public CBuyFromTraderPacket() {}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.traderId);
		buffer.writeItemStack(this.stack);
		buffer.writeInt(this.amount);
	}

	public static CBuyFromTraderPacket decode(PacketBuffer buffer) {
		CBuyFromTraderPacket msg = new CBuyFromTraderPacket();
		msg.traderId = buffer.readInt();
		msg.stack = buffer.readItemStack();
		msg.amount = buffer.readInt();
		return msg;
	}

	public static void handle(CBuyFromTraderPacket message, Supplier<NetworkEvent.Context> ctx) {
		if (((NetworkEvent.Context) ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			((NetworkEvent.Context) ctx.get()).enqueueWork(() -> {
				ServerPlayerEntity player = ((NetworkEvent.Context) ctx.get()).getSender();
				TraderEntity trader = (player.world.getEntityByID(message.traderId) instanceof TraderEntity) ? (TraderEntity) player.world.getEntityByID(message.traderId) : null;
				if (trader == null) {
					return;
				}
				if (player.getDistanceSq((Entity) trader) > 25.0D) {
					return;
				}
				ItemStack stack = message.stack;
				if (stack == null || stack.isEmpty()) {
					return;
				}
				Optional<TradeEntry> optional = trader.getTradingItems().stream().filter((entry) -> {
					return entry.getItemStack().getItem().equals(stack.getItem());
				}).findFirst();
				if (!optional.isPresent()) {
					return;
				}
				TradeEntry tradeEntry = optional.get();
				long emptySlots = player.inventory.mainInventory.stream().filter((slot) -> slot.isEmpty()).count();
				if (emptySlots < MathHelper.ceil((message.amount / 64))) {
					return;
				}
				if (message.amount > tradeEntry.getCount() && !tradeEntry.hasInfiniteStock()) {
					return;
				}
				IEntityStats props = EntityStatsCapability.get((LivingEntity) player);
				int totalPrice = tradeEntry.getPrice() * message.amount;
				long currency = (trader.getCurrency() == Currency.BELLY) ? props.getBelly() : props.getExtol();
				if (currency < totalPrice) {
					return;
				}
				ItemStack boughtStack = new ItemStack((IItemProvider) tradeEntry.getItemStack().getItem());
				boughtStack.setCount(message.amount);
				if (tradeEntry.getItemStack().getOrCreateTag().getBoolean("isClone")) {
					boughtStack.getOrCreateTag().putBoolean("isClone", true);
				}
				if (!tradeEntry.hasInfiniteStock()) {
					int count = tradeEntry.getCount() - message.amount;
					if (count <= 0) {
						trader.getTradingItems().remove(tradeEntry);
					} else {
						tradeEntry.setCount(count);
					}
				}
				player.inventory.addItemStackToInventory(boughtStack);
				if (trader.getCurrency() == Currency.BELLY) {
					props.alterBelly(-totalPrice);
				} else if (trader.getCurrency() == Currency.EXTOL) {
					props.alterExtol(-totalPrice);
				}
				WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), (PlayerEntity) player);
				trader.setTradingItems(trader.getTradingItems());
			});
		}
		((NetworkEvent.Context) ctx.get()).setPacketHandled(true);
	}
}