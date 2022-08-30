/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.entity.merchant.villager.VillagerEntity;
/*    */ import net.minecraft.entity.merchant.villager.VillagerProfession;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ChallengesHelper;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class PoneglyphNoteEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onNoteInteract(PlayerInteractEvent.RightClickItem event) {
/* 29 */     PlayerEntity player = event.getPlayer();
/* 30 */     ItemStack stack = event.getItemStack();
/*    */     
/* 32 */     if (event.getHand() != Hand.MAIN_HAND || stack.isEmpty() || stack.getItem() != Items.PAPER || stack.getTag() == null || stack.getTag().isEmpty() || player.world.isRemote) {
/*    */       return;
/*    */     }
/* 35 */     if (stack.getOrCreateTag().getBoolean("encrypted") || stack.getOrCreateTag().getInt("type") <= 0) {
/*    */       return;
/*    */     }
/* 38 */     String challengeCategory = stack.getOrCreateTag().getString("challenge");
/* 39 */     IChallengesData challengeProps = ChallengesDataCapability.get(player);
/* 40 */     Challenge challenge = ChallengesHelper.getFirstAvailableChallenge(player, challengeCategory);
/*    */     
/* 42 */     if (challenge == null) {
/*    */       return;
/*    */     }
/* 45 */     if (challengeProps.addChallenge(challenge)) {
/*    */       
/* 47 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_UNLOCKED, new Object[] { challenge.getTitle() }));
/* 48 */       WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), challengeProps), player);
/* 49 */       stack.shrink(1);
/*    */     }
/*    */     else {
/*    */       
/* 53 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_ALREADY_UNLOCKED, new Object[] { challenge.getTitle() }));
/*    */     } 
/* 55 */     event.setCanceled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onVillegerInteract(PlayerInteractEvent.EntityInteract event) {
/* 61 */     boolean isTargetVillagerLibrarian = (event.getTarget() instanceof VillagerEntity && ((VillagerEntity)event.getTarget()).getVillagerData().getProfession() == VillagerProfession.LIBRARIAN);
/* 62 */     boolean isItemEncryptedPaper = (event.getItemStack().getItem() == Items.PAPER && event.getItemStack().getOrCreateTag().getBoolean("encrypted") && event.getItemStack().getOrCreateTag().getInt("type") > 0);
/*    */     
/* 64 */     if (!isTargetVillagerLibrarian || !isItemEncryptedPaper) {
/*    */       return;
/*    */     }
/* 67 */     if ((event.getWorld()).isRemote) {
/*    */       return;
/*    */     }
/* 70 */     PlayerEntity player = event.getPlayer();
/* 71 */     event.getItemStack().shrink(1);
/* 72 */     ItemStack note = new ItemStack((IItemProvider)Items.PAPER);
/* 73 */     note.getOrCreateTag().putInt("type", event.getItemStack().getOrCreateTag().getInt("type"));
/* 74 */     note.getOrCreateTag().putString("challenge", event.getItemStack().getOrCreateTag().getString("challenge"));
/* 75 */     note.getOrCreateTag().putBoolean("encrypted", false);
/* 76 */     note.setDisplayName((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_PONEGLYPH_DECRYPTED_NOTE, new Object[0]));
/* 77 */     player.inventory.addItemStackToInventory(note);
/*    */     
/* 79 */     event.setCanceled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\items\PoneglyphNoteEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */