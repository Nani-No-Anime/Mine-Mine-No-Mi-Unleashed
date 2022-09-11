package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.client.ability.CChangeCombatBarPacket;
import xyz.pixelatedw.mineminenomi.packets.client.ability.CToggleCombatModePacket;
import xyz.pixelatedw.mineminenomi.packets.client.ability.CUseAbilityPacket;
import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenPlayerScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;






@EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
public class ModKeybindings
{
  public static KeyBinding test;
  public static KeyBinding guiPlayer;
  public static KeyBinding enterCombatMode;
  public static KeyBinding nextCombatBar;
  public static KeyBinding prevCombatBar;
  public static KeyBinding combatSlot1;
  public static KeyBinding combatSlot2;
  private static final int[] PREVIOUS_INVENTORY_KEYBINDS = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }; public static KeyBinding combatSlot3; public static KeyBinding combatSlot4; public static KeyBinding combatSlot5; public static KeyBinding combatSlot6; public static KeyBinding combatSlot7; public static KeyBinding combatSlot8;
  private static KeyBinding[] keyBindsCombatbar;
  
  public static void init() {
    if (WyDebug.isDebug()) {
      
      test = new KeyBinding("Test Key", 80, ModI18n.CATEGORY_GENERAL);
      ClientRegistry.registerKeyBinding(test);
    } 
    
    guiPlayer = new KeyBinding(ModI18n.KEY_PLAYER, 82, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(guiPlayer);
    
    enterCombatMode = new KeyBinding(ModI18n.KEY_COMBATMODE, 342, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(enterCombatMode);
    
    nextCombatBar = new KeyBinding(ModI18n.KEY_NEXT_COMBAT_BAR, 93, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(nextCombatBar);
    prevCombatBar = new KeyBinding(ModI18n.KEY_PREV_COMBAT_BAR, 91, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(prevCombatBar);
    
    combatSlot1 = new KeyBinding(ModI18n.KEY_COMBATSLOT1, 49, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(combatSlot1);
    combatSlot2 = new KeyBinding(ModI18n.KEY_COMBATSLOT2, 50, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(combatSlot2);
    combatSlot3 = new KeyBinding(ModI18n.KEY_COMBATSLOT3, 51, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(combatSlot3);
    combatSlot4 = new KeyBinding(ModI18n.KEY_COMBATSLOT4, 52, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(combatSlot4);
    combatSlot5 = new KeyBinding(ModI18n.KEY_COMBATSLOT5, 53, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(combatSlot5);
    combatSlot6 = new KeyBinding(ModI18n.KEY_COMBATSLOT6, 54, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(combatSlot6);
    combatSlot7 = new KeyBinding(ModI18n.KEY_COMBATSLOT7, 55, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(combatSlot7);
    combatSlot8 = new KeyBinding(ModI18n.KEY_COMBATSLOT8, 56, ModI18n.CATEGORY_GENERAL);
    ClientRegistry.registerKeyBinding(combatSlot8);
    
    keyBindsCombatbar = new KeyBinding[] { combatSlot1, combatSlot2, combatSlot3, combatSlot4, combatSlot5, combatSlot6, combatSlot7, combatSlot8 };
  }




  
  public static boolean isSneaking() {
    return (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), 340) || InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), 344));
  }

  
  public static boolean isSpaceKeyDown() {
    return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), 32);
  }

  
  @SubscribeEvent
  public static void onPlayerJoins(EntityJoinWorldEvent event) {
    for (int i = 0; i < PREVIOUS_INVENTORY_KEYBINDS.length; i++)
    {
      PREVIOUS_INVENTORY_KEYBINDS[i] = -1;
    }
  }

  
  @SubscribeEvent
  public static void onPlayerLeaves(ClientPlayerNetworkEvent.LoggedOutEvent event) {
    for (int i = 0; i < (Minecraft.getInstance()).gameSettings.keyBindsHotbar.length; i++) {
      
      KeyBinding kb = (Minecraft.getInstance()).gameSettings.keyBindsHotbar[i];
      if (PREVIOUS_INVENTORY_KEYBINDS[i] != -1)
      {
        kb.bind(InputMappings.getInputByCode(PREVIOUS_INVENTORY_KEYBINDS[i], 0));
      }
    } 
  }

  
  @SubscribeEvent
  public static void onMouseInput(InputEvent.MouseInputEvent event) {
    Minecraft minecraft = Minecraft.getInstance();
    ClientPlayerEntity clientPlayerEntity = minecraft.player;
    if (clientPlayerEntity == null || event.getAction() == 0) {
      return;
    }
    checkKeybindings((PlayerEntity)clientPlayerEntity, event.getAction(), event.getButton(), 0);
  }

  
  @SubscribeEvent
  public static void onKeyInput(InputEvent.KeyInputEvent event) {
    Minecraft minecraft = Minecraft.getInstance();
    ClientPlayerEntity clientPlayerEntity = minecraft.player;
    if (clientPlayerEntity == null || event.getAction() == 0) {
      return;
    }
    checkKeybindings((PlayerEntity)clientPlayerEntity, event.getAction(), event.getKey(), 1);
  }

  
  private static void checkKeybindings(PlayerEntity player, int action, int key, int type) {
    IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
    IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
    
    if (entityStatsProps.isInCombatMode() && action == 1)
    {
      if (nextCombatBar.isPressed()) {
        WyNetwork.sendToServer(new CChangeCombatBarPacket(0));
      } else if (prevCombatBar.isPressed()) {
        WyNetwork.sendToServer(new CChangeCombatBarPacket(1));
      } 
    }
    if (!WyDebug.isDebug() || test.isPressed());










    
    Screen screen = (Minecraft.getInstance()).currentScreen;
    boolean isPlayerScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.PlayerStatsScreen);
    boolean isAbilitiesScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen);
    boolean isCrewScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.CrewDetailsScreen);
    boolean isChallengesScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.ChallengesScreen);
    boolean isQuestScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.QuestsTrackerScreen);
    boolean isSecondaryScreenOpen = (isAbilitiesScreen || isCrewScreen || isChallengesScreen || isQuestScreen);
    
    if (action == 1 && (guiPlayer.isPressed() || ((screen == null || isPlayerScreen || isSecondaryScreenOpen) && type == 1 && key == guiPlayer.getKey().getKeyCode())))
    {
      if (isPlayerScreen) {
        Minecraft.getInstance().displayGuiScreen(null);
      } else if (isSecondaryScreenOpen) {
        WyNetwork.sendToServer(new COpenPlayerScreenPacket());
      } else if (screen == null) {
        WyNetwork.sendToServer(new COpenPlayerScreenPacket());
      } 
    }
    if (enterCombatMode.isPressed() && action == 1) {
      keybindsAssignment(entityStatsProps);
    }
    int j = keyBindsCombatbar.length;
    for (int i = 0; i < j; i++) {
      
      if (keyBindsCombatbar[i].isPressed() && action != 2) {
        
        int k = i + abilityDataProps.getCombatBarSet() * 8;
        Ability abl = abilityDataProps.getEquippedAbility(k);
        if (entityStatsProps.isInCombatMode() && abl != null) {
          
          if (!abl.isOnCooldown() || abl.getCooldown() <= 10.0D) {
            WyNetwork.sendToServer(new CUseAbilityPacket(k));
          }
        } else {
          player.inventory.currentItem = i;
        } 
      } 
    } 
  }
  
  private static void keybindsAssignment(IEntityStats entityStatsProps) {
    entityStatsProps.setCombatMode(!entityStatsProps.isInCombatMode());
    if (entityStatsProps.isInCombatMode()) {
      
      for (int i = 0; i < (Minecraft.getInstance()).gameSettings.keyBindsHotbar.length; i++) {
        
        KeyBinding kb = (Minecraft.getInstance()).gameSettings.keyBindsHotbar[i];
        PREVIOUS_INVENTORY_KEYBINDS[i] = kb.getKey().getKeyCode();
        kb.bind(InputMappings.getInputByCode(-1, 0));
      } 
      
      KeyBinding.resetKeyBindingArrayAndHash();
    }
    else {
      
      for (int i = 0; i < (Minecraft.getInstance()).gameSettings.keyBindsHotbar.length; i++) {
        
        KeyBinding kb = (Minecraft.getInstance()).gameSettings.keyBindsHotbar[i];
        if (PREVIOUS_INVENTORY_KEYBINDS[i] == -1) {
          kb.bind(InputMappings.getInputByCode(kb.getDefault().getKeyCode(), 0));
        } else {
          kb.bind(InputMappings.getInputByCode(PREVIOUS_INVENTORY_KEYBINDS[i], 0));
        } 
      } 
      KeyBinding.resetKeyBindingArrayAndHash();
    } 
    WyNetwork.sendToServer(new CToggleCombatModePacket(entityStatsProps.isInCombatMode()));
  }
}


