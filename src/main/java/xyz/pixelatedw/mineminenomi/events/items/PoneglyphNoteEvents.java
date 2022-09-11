package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.ChallengesHelper;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


@EventBusSubscriber(modid = "mineminenomi")
public class PoneglyphNoteEvents
{
  @SubscribeEvent
  public static void onNoteInteract(PlayerInteractEvent.RightClickItem event) {
    PlayerEntity player = event.getPlayer();
    ItemStack stack = event.getItemStack();
    
    if (event.getHand() != Hand.MAIN_HAND || stack.isEmpty() || stack.getItem() != Items.PAPER || stack.getTag() == null || stack.getTag().isEmpty() || player.world.isRemote) {
      return;
    }
    if (stack.getOrCreateTag().getBoolean("encrypted") || stack.getOrCreateTag().getInt("type") <= 0) {
      return;
    }
    String challengeCategory = stack.getOrCreateTag().getString("challenge");
    IChallengesData challengeProps = ChallengesDataCapability.get(player);
    Challenge challenge = ChallengesHelper.getFirstAvailableChallenge(player, challengeCategory);
    
    if (challenge == null) {
      return;
    }
    if (challengeProps.addChallenge(challenge)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_UNLOCKED, new Object[] { challenge.getTitle() }));
      WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), challengeProps), player);
      stack.shrink(1);
    }
    else {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_ALREADY_UNLOCKED, new Object[] { challenge.getTitle() }));
    } 
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onVillegerInteract(PlayerInteractEvent.EntityInteract event) {
    boolean isTargetVillagerLibrarian = (event.getTarget() instanceof VillagerEntity && ((VillagerEntity)event.getTarget()).getVillagerData().getProfession() == VillagerProfession.LIBRARIAN);
    boolean isItemEncryptedPaper = (event.getItemStack().getItem() == Items.PAPER && event.getItemStack().getOrCreateTag().getBoolean("encrypted") && event.getItemStack().getOrCreateTag().getInt("type") > 0);
    
    if (!isTargetVillagerLibrarian || !isItemEncryptedPaper) {
      return;
    }
    if ((event.getWorld()).isRemote) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    event.getItemStack().shrink(1);
    ItemStack note = new ItemStack((IItemProvider)Items.PAPER);
    note.getOrCreateTag().putInt("type", event.getItemStack().getOrCreateTag().getInt("type"));
    note.getOrCreateTag().putString("challenge", event.getItemStack().getOrCreateTag().getString("challenge"));
    note.getOrCreateTag().putBoolean("encrypted", false);
    note.setDisplayName((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_PONEGLYPH_DECRYPTED_NOTE, new Object[0]));
    player.inventory.addItemStackToInventory(note);
    
    event.setCanceled(true);
  }
}


