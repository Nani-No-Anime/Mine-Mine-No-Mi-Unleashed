/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.EntityPredicates;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FactionHelper
/*     */ {
/*     */   public static final Predicate<Entity> IN_CREW_PREDICATE;
/*     */   public static final Predicate<Entity> IN_MARINES_PREDICATE;
/*     */   public static final Predicate<Entity> IN_REVO_ARMY_PREDICATE;
/*     */   
/*     */   @Nullable
/*     */   public static ResourceLocation getFactionIcon(IEntityStats props) {
/*  38 */     ResourceLocation icon = null;
/*  39 */     if (props.isPirate()) {
/*  40 */       icon = ModResources.PIRATE_ICON;
/*  41 */     } else if (props.isMarine()) {
/*  42 */       icon = ModResources.MARINE_ICON_GREYSCALE;
/*  43 */     } else if (props.isBountyHunter()) {
/*  44 */       icon = ModResources.BOUNTY_HUNTER_ICON_GREYSCALE;
/*  45 */     } else if (props.isRevolutionary()) {
/*  46 */       icon = ModResources.REVOLUTIONARY_ARMY_ICON_GREYSCALE;
/*     */     } 
/*  48 */     return icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFactionColor(IEntityStats props) {
/*  53 */     String rgb = "#55FF55";
/*  54 */     if (props.isPirate() || props.isRevolutionary()) {
/*  55 */       rgb = "#FF2200";
/*  56 */     } else if (props.isMarine()) {
/*  57 */       rgb = "#55DDFF";
/*  58 */     } else if (props.isBountyHunter()) {
/*  59 */       rgb = "#BBFF88";
/*  60 */     } else if (props.isBandit()) {
/*  61 */       rgb = "#925959";
/*     */     } 
/*  63 */     return WyHelper.hexToRGB(rgb).getRGB();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendUpdateMessageToCrew(World world, Crew crew) {
/*  68 */     ExtendedWorldData worldData = ExtendedWorldData.get(world);
/*  69 */     for (Crew.Member member : crew.getMembers()) {
/*     */       
/*  71 */       PlayerEntity crewPlayer = world.getPlayerByUuid(member.getUUID());
/*  72 */       if (crewPlayer != null) {
/*  73 */         WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), crewPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendMessageToCrew(World world, Crew crew, ITextComponent message) {
/*  79 */     for (Crew.Member member : crew.getMembers()) {
/*     */       
/*  81 */       UUID uuid = member.getUUID();
/*  82 */       PlayerEntity memberPlayer = world.getPlayerByUuid(uuid);
/*  83 */       if (memberPlayer != null && memberPlayer.isAlive())
/*     */       {
/*  85 */         memberPlayer.sendMessage(message);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void validateFaction(PlayerEntity player) {
/*  92 */     ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/*  93 */     if (!EntityStatsCapability.get((LivingEntity)player).isPirate()) {
/*     */       
/*  95 */       Crew crew = worldData.getCrewWithMember(player.getUniqueID());
/*  96 */       if (crew != null) {
/*  97 */         worldData.removeCrewMember(crew, player.getUniqueID());
/*     */       }
/*     */     } 
/* 100 */     WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), player);
/*     */   }
/*     */   static {
/* 103 */     IN_CREW_PREDICATE = (entity -> {
/*     */         ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);
/*     */         
/*     */         Crew crew = worldData.getCrewWithMember(entity.getUniqueID());
/*     */         
/*     */         boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
/*     */         
/*     */         boolean isInCrew = (crew != null);
/* 111 */         return (isNotSpectating && isInCrew);
/*     */       });
/*     */     
/* 114 */     IN_MARINES_PREDICATE = (entity -> {
/*     */         boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
/*     */         
/*     */         boolean isInMarines = false;
/*     */         
/*     */         if (entity instanceof LivingEntity) {
/*     */           IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/*     */           
/* 122 */           isInMarines = (props.isMarine() || entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity || entity instanceof net.minecraft.entity.merchant.villager.VillagerEntity || props.isCivilian());
/*     */         } 
/*     */         
/* 125 */         return (isNotSpectating && isInMarines);
/*     */       });
/*     */     
/* 128 */     IN_REVO_ARMY_PREDICATE = (entity -> {
/*     */         boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
/*     */         
/*     */         boolean isInRevoArmy = false;
/*     */         
/*     */         if (entity instanceof LivingEntity) {
/*     */           IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/*     */           
/* 136 */           isInRevoArmy = (props.isRevolutionary() || entity instanceof net.minecraft.entity.merchant.villager.VillagerEntity || props.isCivilian());
/*     */         } 
/*     */         
/* 139 */         return (isNotSpectating && isInRevoArmy);
/*     */       });
/*     */   }
/*     */   
/*     */   public static Predicate<Entity> getOutsideGroupPredicate(LivingEntity entity) {
/* 144 */     return getSameGroupPredicate(entity).negate();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Predicate<Entity> getSameGroupPredicate(LivingEntity entity) {
/* 149 */     IEntityStats props = EntityStatsCapability.get(entity);
/*     */     
/* 151 */     if (props.isRogue()) {
/* 152 */       return target -> false;
/*     */     }
/* 154 */     if (props.isPirate()) {
/*     */       
/* 156 */       ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);
/* 157 */       Crew crew = worldData.getCrewWithMember(entity.getUniqueID());
/*     */       
/* 159 */       return target -> {
/*     */           if (target.equals(entity)) {
/*     */             return true;
/*     */           }
/*     */ 
/*     */           
/*     */           if (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity && target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity) {
/*     */             return true;
/*     */           }
/*     */           
/*     */           Crew targetCrew = worldData.getCrewWithMember(target.getUniqueID());
/*     */           
/* 171 */           return (crew != null && targetCrew != null && crew.equals(targetCrew));
/*     */         };
/*     */     } 
/*     */     
/* 175 */     if (props.isMarine() || props.isBountyHunter())
/* 176 */       return IN_MARINES_PREDICATE; 
/* 177 */     if (props.isRevolutionary())
/* 178 */       return IN_REVO_ARMY_PREDICATE; 
/* 179 */     if (props.isBandit())
/*     */     {
/* 181 */       return target -> 
/*     */         
/* 183 */         (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity && target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     return Predicates.alwaysFalse();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum MarineRank
/*     */   {
/* 199 */     CHORE_BOY(ModI18n.MARINE_TITLE_CHORE_BOY, 0),
/* 200 */     SEAMAN(ModI18n.MARINE_TITLE_SEAMAN, 5),
/* 201 */     PETTY_OFFICER(ModI18n.MARINE_TITLE_PETTY_OFFICER, 10),
/* 202 */     LIEUTENANT(ModI18n.MARINE_TITLE_LIEUTENANT, 15),
/* 203 */     COMMANDER(ModI18n.MARINE_TITLE_COMMANDER, 20),
/* 204 */     CAPTAIN(ModI18n.MARINE_TITLE_CAPTAIN, 25),
/* 205 */     COMMODORE(ModI18n.MARINE_TITLE_COMMODORE, 40),
/* 206 */     VICE_ADMIRAL(ModI18n.MARINE_TITLE_VICE_ADMIRAL, 50),
/* 207 */     ADMIRAL(ModI18n.MARINE_TITLE_ADMIRAL, 70),
/* 208 */     FLEET_ADMIRAL(ModI18n.MARINE_TITLE_FLEET_ADMIRAL, 100);
/*     */     
/*     */     private String unlocalizedName;
/*     */     
/*     */     private int loyaltyRequired;
/*     */     
/*     */     MarineRank(String unlocalizedName, int loyaltyRequired) {
/* 215 */       this.unlocalizedName = unlocalizedName;
/* 216 */       this.loyaltyRequired = loyaltyRequired;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getLocalizedName() {
/* 221 */       return (new TranslationTextComponent(this.unlocalizedName, new Object[0])).getFormattedText();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRequiredLoyalty() {
/* 226 */       return this.loyaltyRequired;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum RevolutionaryRank
/*     */   {
/* 232 */     MEMBER(ModI18n.REVOLUTIONARY_TITLE_MEMBER, 0),
/* 233 */     OFFICER(ModI18n.REVOLUTIONARY_TITLE_OFFICER, 30),
/* 234 */     COMMANDER(ModI18n.REVOLUTIONARY_TITLE_COMMANDER, 50),
/* 235 */     CHIEF_OF_STAFF(ModI18n.REVOLUTIONARY_TITLE_CHIEF_OF_STAFF, 80),
/* 236 */     SUPREME_COMMANDER(ModI18n.REVOLUTIONARY_TITLE_SUPREME_COMMANDER, 100);
/*     */     
/*     */     private String unlocalizedName;
/*     */     
/*     */     private int loyaltyRequired;
/*     */     
/*     */     RevolutionaryRank(String unlocalizedName, int loyaltyRequired) {
/* 243 */       this.unlocalizedName = unlocalizedName;
/* 244 */       this.loyaltyRequired = loyaltyRequired;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getLocalizedName() {
/* 249 */       return (new TranslationTextComponent(this.unlocalizedName, new Object[0])).getFormattedText();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRequiredLoyalty() {
/* 254 */       return this.loyaltyRequired;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\FactionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */