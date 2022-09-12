package xyz.pixelatedw.mineminenomi.abilities.hiso;


import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.List;

public class LookoutAbility extends Ability {
	public static final LookoutAbility INSTANCE = new LookoutAbility();

	public LookoutAbility() {
		super("Animal Lookout", AbilityHelper.getDevilFruitCategory());
		setDescription(
				"Allows the user to communicate with nearby animals and learn if other players passed near them");
		setMaxCooldown(10.0D);
		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player) {
		List<AnimalEntity> around = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D,
				new Class[] { AnimalEntity.class });

		around.forEach(entity -> {
			if (!(entity instanceof AnimalEntity)) {

				if (entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.COBBLESTONE) {
					CompoundNBT tag = entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getTag();

					ListNBT seen = (ListNBT) tag.get("seen");

					player.sendMessage((ITextComponent) new StringTextComponent("Recently:"));
					// [todo : figure this out]
					seen.forEach((string) -> {
						String[] split = string.getString().split(" ");
						player.sendMessage(new StringTextComponent(split[0] + " was around here "
								+ (player.world.getGameTime() - Long.parseLong(split[1])) + " ticks ago"));
					});
				}
			}
		});

		if (around.size() > 0) {
			((AnimalEntity) around.get(0)).addPotionEffect(new EffectInstance(ModEffects.ANIMAL_LOOKOUT, 9999));
		}
		return true;
	}
}
