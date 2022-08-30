/*     */ package xyz.pixelatedw.mineminenomi.packets.client;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class CFinishCCPacket
/*     */ {
/*     */   private int factionId;
/*     */   private int raceId;
/*     */   private int styleId;
/*  33 */   private static final String[] FACTIONS = new String[] { "Random", "pirate", "marine", "bounty_hunter", "revolutionary" };
/*  34 */   private static final String[] RACES = new String[] { "Random", "human", "fishman", "cyborg", "mink" };
/*  35 */   private static final String[] STYLES = new String[] { "Random", "swordsman", "sniper", "doctor", "art_of_weather", "brawler", "black_leg" };
/*  36 */   private static final String[] MINK_SUB_RACES = new String[] { "mink_bunny", "mink_dog", "mink_lion" };
/*     */ 
/*     */   
/*     */   public CFinishCCPacket() {}
/*     */   
/*     */   public CFinishCCPacket(int factionId, int raceId, int styleId) {
/*  42 */     this.factionId = factionId;
/*  43 */     this.raceId = raceId;
/*  44 */     this.styleId = styleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  49 */     buffer.writeInt(this.factionId);
/*  50 */     buffer.writeInt(this.raceId);
/*  51 */     buffer.writeInt(this.styleId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CFinishCCPacket decode(PacketBuffer buffer) {
/*  56 */     CFinishCCPacket msg = new CFinishCCPacket();
/*  57 */     msg.factionId = buffer.readInt();
/*  58 */     msg.raceId = buffer.readInt();
/*  59 */     msg.styleId = buffer.readInt();
/*  60 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(CFinishCCPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  65 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*     */     {
/*  67 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             String faction; String race;
/*     */             String style;
/*     */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*     */             IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*     */             Random rand = serverPlayerEntity.getRNG();
/*  74 */             boolean hasCharBook = (!serverPlayerEntity.getHeldItemMainhand().isEmpty() && serverPlayerEntity.getHeldItemMainhand().getItem().equals(ModItems.CHARACTER_CREATOR));
/*  75 */             boolean hasEmptyStats = (WyHelper.isNullOrEmpty(entityProps.getFaction()) || WyHelper.isNullOrEmpty(entityProps.getRace()) || WyHelper.isNullOrEmpty(entityProps.getFightingStyle()));
/*     */             
/*     */             if (!hasCharBook && !hasEmptyStats) {
/*     */               return;
/*     */             }
/*     */             
/*     */             message.factionId %= FACTIONS.length;
/*     */             
/*     */             message.raceId %= RACES.length;
/*     */             
/*     */             message.styleId %= STYLES.length;
/*     */             
/*     */             if (message.factionId == 0) {
/*     */               faction = FACTIONS[1 + rand.nextInt(FACTIONS.length - 1)];
/*     */             } else {
/*     */               faction = FACTIONS[message.factionId];
/*     */             } 
/*     */             
/*     */             entityProps.setFaction(faction);
/*     */             
/*     */             if (message.raceId == 0 || CommonConfig.INSTANCE.getRaceRandomizer()) {
/*     */               race = RACES[1 + rand.nextInt(RACES.length - 1)];
/*     */             } else {
/*     */               race = RACES[message.raceId];
/*     */             } 
/*     */             
/*     */             entityProps.setRace(race);
/*     */             
/*     */             if (entityProps.isMink()) {
/*     */               String subRace = MINK_SUB_RACES[rand.nextInt(MINK_SUB_RACES.length)];
/*     */               
/*     */               entityProps.setSubRace(subRace);
/*     */             } 
/*     */             
/*     */             if (message.styleId == 0) {
/*     */               style = STYLES[1 + rand.nextInt(STYLES.length - 1)];
/*     */             } else {
/*     */               style = STYLES[message.styleId];
/*     */             } 
/*     */             entityProps.setFightingStyle(style);
/*     */             DorikiEvent e = new DorikiEvent((PlayerEntity)serverPlayerEntity, entityProps.getDoriki());
/*     */             MinecraftForge.EVENT_BUS.post((Event)e);
/*     */             AbilityHelper.validateRacialMoves((PlayerEntity)serverPlayerEntity);
/*     */             if (entityProps.isCyborg()) {
/*     */               if (entityProps.getMaxCola() < 100) {
/*     */                 entityProps.setMaxCola(100);
/*     */               }
/*     */               entityProps.setCola(entityProps.getMaxCola());
/*     */             } 
/*     */             for (ItemStack is : ((PlayerEntity)serverPlayerEntity).inventory.mainInventory) {
/*     */               if (is != null && is.getItem() instanceof xyz.pixelatedw.mineminenomi.items.CharacterCreatorItem) {
/*     */                 ((PlayerEntity)serverPlayerEntity).inventory.deleteStack(is);
/*     */               }
/*     */             } 
/*     */             SetPlayerDetailsEvent event = new SetPlayerDetailsEvent((PlayerEntity)serverPlayerEntity);
/*     */             MinecraftForge.EVENT_BUS.post(event);
/*     */             WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(serverPlayerEntity.getEntityId(), entityProps), (LivingEntity)serverPlayerEntity);
/*     */             WyNetwork.sendTo(new SSyncAbilityDataPacket(serverPlayerEntity.getEntityId(), abilityProps), (PlayerEntity)serverPlayerEntity);
/*     */           });
/*     */     }
/* 135 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\CFinishCCPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */