package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

@EventBusSubscriber(modid = "mineminenomi")
public class ManePassiveEvents {
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onNameplateRender(RenderNameplateEvent event) {
    Entity entity = event.getEntity();
    if (entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
      ManeManeMemoryAbility ability = (ManeManeMemoryAbility)abilityData.getEquippedAbility((Ability)ManeManeMemoryAbility.INSTANCE);
      if (ability != null && ability.isTransformationActive((LivingEntity)player)) {
        event.setContent(ability.getMemory().getDisplayName());
      }
    } 
  }
  
  @SubscribeEvent
  public static void onServerChat(ServerChatEvent event) {
    ServerPlayerEntity serverPlayerEntity = event.getPlayer();
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
    ManeManeMemoryAbility ability = (ManeManeMemoryAbility)abilityData.getEquippedAbility((Ability)ManeManeMemoryAbility.INSTANCE);
    
    if (ability != null && ability.isTransformationActive((LivingEntity)serverPlayerEntity)) {


      
      event.setComponent(null);
      
      TranslationTextComponent translationTextComponent1 = new TranslationTextComponent("chat.type.text", new Object[] { ability.getMemory().getDisplayName(), ForgeHooks.newChatWithLinks(event.getMessage()) });
      TranslationTextComponent translationTextComponent2 = new TranslationTextComponent("chat.type.text", new Object[] { ability.getMemory().getDisplayName() + " §2(" + event.getPlayer().getGameProfile().getName() + ")§r", ForgeHooks.newChatWithLinks(event.getMessage()) });
      
      MinecraftServer server = serverPlayerEntity.getServer();
      
      server.sendMessage((ITextComponent)translationTextComponent1);
      for (ServerPlayerEntity serverPlayer : server.getPlayerList().getPlayers()) {
        
        TranslationTextComponent translationTextComponent = translationTextComponent1;
        boolean isOp = (server.getPermissionLevel(serverPlayer.getGameProfile()) >= 4);
        if (isOp) {
          translationTextComponent = translationTextComponent2;
        }
        serverPlayer.connection.sendPacket((IPacket)new SChatPacket((ITextComponent)translationTextComponent, ChatType.CHAT));
      } 
    } 
  }
}


