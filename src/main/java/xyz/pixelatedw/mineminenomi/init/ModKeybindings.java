/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.client.util.InputMappings;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
/*     */ import net.minecraftforge.client.event.InputEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CChangeCombatBarPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CToggleCombatModePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CUseAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenPlayerScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */ public class ModKeybindings
/*     */ {
/*     */   public static KeyBinding test;
/*     */   public static KeyBinding guiPlayer;
/*     */   public static KeyBinding enterCombatMode;
/*     */   public static KeyBinding nextCombatBar;
/*     */   public static KeyBinding prevCombatBar;
/*     */   public static KeyBinding combatSlot1;
/*     */   public static KeyBinding combatSlot2;
/*  44 */   private static final int[] PREVIOUS_INVENTORY_KEYBINDS = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }; public static KeyBinding combatSlot3; public static KeyBinding combatSlot4; public static KeyBinding combatSlot5; public static KeyBinding combatSlot6; public static KeyBinding combatSlot7; public static KeyBinding combatSlot8;
/*     */   private static KeyBinding[] keyBindsCombatbar;
/*     */   
/*     */   public static void init() {
/*  48 */     if (WyDebug.isDebug()) {
/*     */       
/*  50 */       test = new KeyBinding("Test Key", 80, ModI18n.CATEGORY_GENERAL);
/*  51 */       ClientRegistry.registerKeyBinding(test);
/*     */     } 
/*     */     
/*  54 */     guiPlayer = new KeyBinding(ModI18n.KEY_PLAYER, 82, ModI18n.CATEGORY_GENERAL);
/*  55 */     ClientRegistry.registerKeyBinding(guiPlayer);
/*     */     
/*  57 */     enterCombatMode = new KeyBinding(ModI18n.KEY_COMBATMODE, 342, ModI18n.CATEGORY_GENERAL);
/*  58 */     ClientRegistry.registerKeyBinding(enterCombatMode);
/*     */     
/*  60 */     nextCombatBar = new KeyBinding(ModI18n.KEY_NEXT_COMBAT_BAR, 93, ModI18n.CATEGORY_GENERAL);
/*  61 */     ClientRegistry.registerKeyBinding(nextCombatBar);
/*  62 */     prevCombatBar = new KeyBinding(ModI18n.KEY_PREV_COMBAT_BAR, 91, ModI18n.CATEGORY_GENERAL);
/*  63 */     ClientRegistry.registerKeyBinding(prevCombatBar);
/*     */     
/*  65 */     combatSlot1 = new KeyBinding(ModI18n.KEY_COMBATSLOT1, 49, ModI18n.CATEGORY_GENERAL);
/*  66 */     ClientRegistry.registerKeyBinding(combatSlot1);
/*  67 */     combatSlot2 = new KeyBinding(ModI18n.KEY_COMBATSLOT2, 50, ModI18n.CATEGORY_GENERAL);
/*  68 */     ClientRegistry.registerKeyBinding(combatSlot2);
/*  69 */     combatSlot3 = new KeyBinding(ModI18n.KEY_COMBATSLOT3, 51, ModI18n.CATEGORY_GENERAL);
/*  70 */     ClientRegistry.registerKeyBinding(combatSlot3);
/*  71 */     combatSlot4 = new KeyBinding(ModI18n.KEY_COMBATSLOT4, 52, ModI18n.CATEGORY_GENERAL);
/*  72 */     ClientRegistry.registerKeyBinding(combatSlot4);
/*  73 */     combatSlot5 = new KeyBinding(ModI18n.KEY_COMBATSLOT5, 53, ModI18n.CATEGORY_GENERAL);
/*  74 */     ClientRegistry.registerKeyBinding(combatSlot5);
/*  75 */     combatSlot6 = new KeyBinding(ModI18n.KEY_COMBATSLOT6, 54, ModI18n.CATEGORY_GENERAL);
/*  76 */     ClientRegistry.registerKeyBinding(combatSlot6);
/*  77 */     combatSlot7 = new KeyBinding(ModI18n.KEY_COMBATSLOT7, 55, ModI18n.CATEGORY_GENERAL);
/*  78 */     ClientRegistry.registerKeyBinding(combatSlot7);
/*  79 */     combatSlot8 = new KeyBinding(ModI18n.KEY_COMBATSLOT8, 56, ModI18n.CATEGORY_GENERAL);
/*  80 */     ClientRegistry.registerKeyBinding(combatSlot8);
/*     */     
/*  82 */     keyBindsCombatbar = new KeyBinding[] { combatSlot1, combatSlot2, combatSlot3, combatSlot4, combatSlot5, combatSlot6, combatSlot7, combatSlot8 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSneaking() {
/*  90 */     return (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), 340) || InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), 344));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSpaceKeyDown() {
/*  95 */     return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), 32);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerJoins(EntityJoinWorldEvent event) {
/* 101 */     for (int i = 0; i < PREVIOUS_INVENTORY_KEYBINDS.length; i++)
/*     */     {
/* 103 */       PREVIOUS_INVENTORY_KEYBINDS[i] = -1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerLeaves(ClientPlayerNetworkEvent.LoggedOutEvent event) {
/* 110 */     for (int i = 0; i < (Minecraft.getInstance()).gameSettings.keyBindsHotbar.length; i++) {
/*     */       
/* 112 */       KeyBinding kb = (Minecraft.getInstance()).gameSettings.keyBindsHotbar[i];
/* 113 */       if (PREVIOUS_INVENTORY_KEYBINDS[i] != -1)
/*     */       {
/* 115 */         kb.bind(InputMappings.getInputByCode(PREVIOUS_INVENTORY_KEYBINDS[i], 0));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onMouseInput(InputEvent.MouseInputEvent event) {
/* 123 */     Minecraft minecraft = Minecraft.getInstance();
/* 124 */     ClientPlayerEntity clientPlayerEntity = minecraft.player;
/* 125 */     if (clientPlayerEntity == null || event.getAction() == 0) {
/*     */       return;
/*     */     }
/* 128 */     checkKeybindings((PlayerEntity)clientPlayerEntity, event.getAction(), event.getButton(), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onKeyInput(InputEvent.KeyInputEvent event) {
/* 134 */     Minecraft minecraft = Minecraft.getInstance();
/* 135 */     ClientPlayerEntity clientPlayerEntity = minecraft.player;
/* 136 */     if (clientPlayerEntity == null || event.getAction() == 0) {
/*     */       return;
/*     */     }
/* 139 */     checkKeybindings((PlayerEntity)clientPlayerEntity, event.getAction(), event.getKey(), 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkKeybindings(PlayerEntity player, int action, int key, int type) {
/* 144 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/* 145 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/* 147 */     if (entityStatsProps.isInCombatMode() && action == 1)
/*     */     {
/* 149 */       if (nextCombatBar.isPressed()) {
/* 150 */         WyNetwork.sendToServer(new CChangeCombatBarPacket(0));
/* 151 */       } else if (prevCombatBar.isPressed()) {
/* 152 */         WyNetwork.sendToServer(new CChangeCombatBarPacket(1));
/*     */       } 
/*     */     }
/* 155 */     if (!WyDebug.isDebug() || test.isPressed());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     Screen screen = (Minecraft.getInstance()).currentScreen;
/* 168 */     boolean isPlayerScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.PlayerStatsScreen);
/* 169 */     boolean isAbilitiesScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen);
/* 170 */     boolean isCrewScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.CrewDetailsScreen);
/* 171 */     boolean isChallengesScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.ChallengesScreen);
/* 172 */     boolean isQuestScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.QuestsTrackerScreen);
/* 173 */     boolean isSecondaryScreenOpen = (isAbilitiesScreen || isCrewScreen || isChallengesScreen || isQuestScreen);
/*     */     
/* 175 */     if (action == 1 && (guiPlayer.isPressed() || ((screen == null || isPlayerScreen || isSecondaryScreenOpen) && type == 1 && key == guiPlayer.getKey().getKeyCode())))
/*     */     {
/* 177 */       if (isPlayerScreen) {
/* 178 */         Minecraft.getInstance().displayGuiScreen(null);
/* 179 */       } else if (isSecondaryScreenOpen) {
/* 180 */         WyNetwork.sendToServer(new COpenPlayerScreenPacket());
/* 181 */       } else if (screen == null) {
/* 182 */         WyNetwork.sendToServer(new COpenPlayerScreenPacket());
/*     */       } 
/*     */     }
/* 185 */     if (enterCombatMode.isPressed() && action == 1) {
/* 186 */       keybindsAssignment(entityStatsProps);
/*     */     }
/* 188 */     int j = keyBindsCombatbar.length;
/* 189 */     for (int i = 0; i < j; i++) {
/*     */       
/* 191 */       if (keyBindsCombatbar[i].isPressed() && action != 2) {
/*     */         
/* 193 */         int k = i + abilityDataProps.getCombatBarSet() * 8;
/* 194 */         Ability abl = abilityDataProps.getEquippedAbility(k);
/* 195 */         if (entityStatsProps.isInCombatMode() && abl != null) {
/*     */           
/* 197 */           if (!abl.isOnCooldown() || abl.getCooldown() <= 10.0D) {
/* 198 */             WyNetwork.sendToServer(new CUseAbilityPacket(k));
/*     */           }
/*     */         } else {
/* 201 */           player.inventory.currentItem = i;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void keybindsAssignment(IEntityStats entityStatsProps) {
/* 208 */     entityStatsProps.setCombatMode(!entityStatsProps.isInCombatMode());
/* 209 */     if (entityStatsProps.isInCombatMode()) {
/*     */       
/* 211 */       for (int i = 0; i < (Minecraft.getInstance()).gameSettings.keyBindsHotbar.length; i++) {
/*     */         
/* 213 */         KeyBinding kb = (Minecraft.getInstance()).gameSettings.keyBindsHotbar[i];
/* 214 */         PREVIOUS_INVENTORY_KEYBINDS[i] = kb.getKey().getKeyCode();
/* 215 */         kb.bind(InputMappings.getInputByCode(-1, 0));
/*     */       } 
/*     */       
/* 218 */       KeyBinding.resetKeyBindingArrayAndHash();
/*     */     }
/*     */     else {
/*     */       
/* 222 */       for (int i = 0; i < (Minecraft.getInstance()).gameSettings.keyBindsHotbar.length; i++) {
/*     */         
/* 224 */         KeyBinding kb = (Minecraft.getInstance()).gameSettings.keyBindsHotbar[i];
/* 225 */         if (PREVIOUS_INVENTORY_KEYBINDS[i] == -1) {
/* 226 */           kb.bind(InputMappings.getInputByCode(kb.getDefault().getKeyCode(), 0));
/*     */         } else {
/* 228 */           kb.bind(InputMappings.getInputByCode(PREVIOUS_INVENTORY_KEYBINDS[i], 0));
/*     */         } 
/*     */       } 
/* 231 */       KeyBinding.resetKeyBindingArrayAndHash();
/*     */     } 
/* 233 */     WyNetwork.sendToServer(new CToggleCombatModePacket(entityStatsProps.isInCombatMode()));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModKeybindings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */