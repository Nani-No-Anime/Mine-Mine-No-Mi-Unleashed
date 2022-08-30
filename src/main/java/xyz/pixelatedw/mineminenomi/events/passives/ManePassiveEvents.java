/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SChatPacket;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.text.ChatType;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.event.RenderNameplateEvent;
/*    */ import net.minecraftforge.common.ForgeHooks;
/*    */ import net.minecraftforge.event.ServerChatEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ManePassiveEvents {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void onNameplateRender(RenderNameplateEvent event) {
/* 30 */     Entity entity = event.getEntity();
/* 31 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 33 */       PlayerEntity player = (PlayerEntity)entity;
/* 34 */       IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/* 35 */       ManeManeMemoryAbility ability = (ManeManeMemoryAbility)abilityData.getEquippedAbility((Ability)ManeManeMemoryAbility.INSTANCE);
/* 36 */       if (ability != null && ability.isTransformationActive((LivingEntity)player)) {
/* 37 */         event.setContent(ability.getMemory().getDisplayName());
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onServerChat(ServerChatEvent event) {
/* 44 */     ServerPlayerEntity serverPlayerEntity = event.getPlayer();
/* 45 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/* 46 */     ManeManeMemoryAbility ability = (ManeManeMemoryAbility)abilityData.getEquippedAbility((Ability)ManeManeMemoryAbility.INSTANCE);
/*    */     
/* 48 */     if (ability != null && ability.isTransformationActive((LivingEntity)serverPlayerEntity)) {
/*    */ 
/*    */ 
/*    */       
/* 52 */       event.setComponent(null);
/*    */       
/* 54 */       TranslationTextComponent translationTextComponent1 = new TranslationTextComponent("chat.type.text", new Object[] { ability.getMemory().getDisplayName(), ForgeHooks.newChatWithLinks(event.getMessage()) });
/* 55 */       TranslationTextComponent translationTextComponent2 = new TranslationTextComponent("chat.type.text", new Object[] { ability.getMemory().getDisplayName() + " §2(" + event.getPlayer().getGameProfile().getName() + ")§r", ForgeHooks.newChatWithLinks(event.getMessage()) });
/*    */       
/* 57 */       MinecraftServer server = serverPlayerEntity.getServer();
/*    */       
/* 59 */       server.sendMessage((ITextComponent)translationTextComponent1);
/* 60 */       for (ServerPlayerEntity serverPlayer : server.getPlayerList().getPlayers()) {
/*    */         
/* 62 */         TranslationTextComponent translationTextComponent = translationTextComponent1;
/* 63 */         boolean isOp = (server.getPermissionLevel(serverPlayer.getGameProfile()) >= 4);
/* 64 */         if (isOp) {
/* 65 */           translationTextComponent = translationTextComponent2;
/*    */         }
/* 67 */         serverPlayer.connection.sendPacket((IPacket)new SChatPacket((ITextComponent)translationTextComponent, ChatType.CHAT));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\ManePassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */