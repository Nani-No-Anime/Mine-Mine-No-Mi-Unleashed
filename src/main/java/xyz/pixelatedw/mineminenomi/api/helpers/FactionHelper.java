package xyz.pixelatedw.mineminenomi.api.helpers;

import com.google.common.base.Predicates;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;






public class FactionHelper
{
  public static final Predicate<Entity> IN_CREW_PREDICATE;
  public static final Predicate<Entity> IN_MARINES_PREDICATE;
  public static final Predicate<Entity> IN_REVO_ARMY_PREDICATE;
  
  @Nullable
  public static ResourceLocation getFactionIcon(IEntityStats props) {
    ResourceLocation icon = null;
    if (props.isPirate()) {
      icon = ModResources.PIRATE_ICON;
    } else if (props.isMarine()) {
      icon = ModResources.MARINE_ICON_GREYSCALE;
    } else if (props.isBountyHunter()) {
      icon = ModResources.BOUNTY_HUNTER_ICON_GREYSCALE;
    } else if (props.isRevolutionary()) {
      icon = ModResources.REVOLUTIONARY_ARMY_ICON_GREYSCALE;
    } 
    return icon;
  }

  
  public static int getFactionColor(IEntityStats props) {
    String rgb = "#55FF55";
    if (props.isPirate() || props.isRevolutionary()) {
      rgb = "#FF2200";
    } else if (props.isMarine()) {
      rgb = "#55DDFF";
    } else if (props.isBountyHunter()) {
      rgb = "#BBFF88";
    } else if (props.isBandit()) {
      rgb = "#925959";
    } 
    return WyHelper.hexToRGB(rgb).getRGB();
  }

  
  public static void sendUpdateMessageToCrew(World world, Crew crew) {
    ExtendedWorldData worldData = ExtendedWorldData.get(world);
    for (Crew.Member member : crew.getMembers()) {
      
      PlayerEntity crewPlayer = world.getPlayerByUuid(member.getUUID());
      if (crewPlayer != null) {
        WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), crewPlayer);
      }
    } 
  }
  
  public static void sendMessageToCrew(World world, Crew crew, ITextComponent message) {
    for (Crew.Member member : crew.getMembers()) {
      
      UUID uuid = member.getUUID();
      PlayerEntity memberPlayer = world.getPlayerByUuid(uuid);
      if (memberPlayer != null && memberPlayer.isAlive())
      {
        memberPlayer.sendMessage(message);
      }
    } 
  }

  
  public static void validateFaction(PlayerEntity player) {
    ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
    if (!EntityStatsCapability.get((LivingEntity)player).isPirate()) {
      
      Crew crew = worldData.getCrewWithMember(player.getUniqueID());
      if (crew != null) {
        worldData.removeCrewMember(crew, player.getUniqueID());
      }
    } 
    WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), player);
  }
  static {
    IN_CREW_PREDICATE = (entity -> {
        ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);
        
        Crew crew = worldData.getCrewWithMember(entity.getUniqueID());
        
        boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
        
        boolean isInCrew = (crew != null);
        return (isNotSpectating && isInCrew);
      });
    
    IN_MARINES_PREDICATE = (entity -> {
        boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
        
        boolean isInMarines = false;
        
        if (entity instanceof LivingEntity) {
          IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
          
          isInMarines = (props.isMarine() || entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity || entity instanceof net.minecraft.entity.merchant.villager.VillagerEntity || props.isCivilian());
        } 
        
        return (isNotSpectating && isInMarines);
      });
    
    IN_REVO_ARMY_PREDICATE = (entity -> {
        boolean isNotSpectating = EntityPredicates.NOT_SPECTATING.test(entity);
        
        boolean isInRevoArmy = false;
        
        if (entity instanceof LivingEntity) {
          IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
          
          isInRevoArmy = (props.isRevolutionary() || entity instanceof net.minecraft.entity.merchant.villager.VillagerEntity || props.isCivilian());
        } 
        
        return (isNotSpectating && isInRevoArmy);
      });
  }
  
  public static Predicate<Entity> getOutsideGroupPredicate(LivingEntity entity) {
    return getSameGroupPredicate(entity).negate();
  }

  
  public static Predicate<Entity> getSameGroupPredicate(LivingEntity entity) {
    IEntityStats props = EntityStatsCapability.get(entity);
    
    if (props.isRogue()) {
      return target -> false;
    }
    if (props.isPirate()) {
      
      ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);
      Crew crew = worldData.getCrewWithMember(entity.getUniqueID());
      
      return target -> {
          if (target.equals(entity)) {
            return true;
          }

          
          if (entity instanceof AbstractPirateEntity && target instanceof AbstractPirateEntity) {
            return true;
          }
          
          Crew targetCrew = worldData.getCrewWithMember(target.getUniqueID());
          
          return (crew != null && targetCrew != null && crew.equals(targetCrew));
        };
    } 
    
    if (props.isMarine() || props.isBountyHunter())
      return IN_MARINES_PREDICATE; 
    if (props.isRevolutionary())
      return IN_REVO_ARMY_PREDICATE; 
    if (props.isBandit())
    {
      return target -> 
        
        (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity && target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity);
    }





    
    return Predicates.alwaysFalse();
  }



  
  public enum MarineRank
  {
    CHORE_BOY(ModI18n.MARINE_TITLE_CHORE_BOY, 0),
    SEAMAN(ModI18n.MARINE_TITLE_SEAMAN, 5),
    PETTY_OFFICER(ModI18n.MARINE_TITLE_PETTY_OFFICER, 10),
    LIEUTENANT(ModI18n.MARINE_TITLE_LIEUTENANT, 15),
    COMMANDER(ModI18n.MARINE_TITLE_COMMANDER, 20),
    CAPTAIN(ModI18n.MARINE_TITLE_CAPTAIN, 25),
    COMMODORE(ModI18n.MARINE_TITLE_COMMODORE, 40),
    VICE_ADMIRAL(ModI18n.MARINE_TITLE_VICE_ADMIRAL, 50),
    ADMIRAL(ModI18n.MARINE_TITLE_ADMIRAL, 70),
    FLEET_ADMIRAL(ModI18n.MARINE_TITLE_FLEET_ADMIRAL, 100);
    
    private String unlocalizedName;
    
    private int loyaltyRequired;
    
    MarineRank(String unlocalizedName, int loyaltyRequired) {
      this.unlocalizedName = unlocalizedName;
      this.loyaltyRequired = loyaltyRequired;
    }

    
    public String getLocalizedName() {
      return (new TranslationTextComponent(this.unlocalizedName, new Object[0])).getFormattedText();
    }

    
    public int getRequiredLoyalty() {
      return this.loyaltyRequired;
    }
  }
  
  public enum RevolutionaryRank
  {
    MEMBER(ModI18n.REVOLUTIONARY_TITLE_MEMBER, 0),
    OFFICER(ModI18n.REVOLUTIONARY_TITLE_OFFICER, 30),
    COMMANDER(ModI18n.REVOLUTIONARY_TITLE_COMMANDER, 50),
    CHIEF_OF_STAFF(ModI18n.REVOLUTIONARY_TITLE_CHIEF_OF_STAFF, 80),
    SUPREME_COMMANDER(ModI18n.REVOLUTIONARY_TITLE_SUPREME_COMMANDER, 100);
    
    private String unlocalizedName;
    
    private int loyaltyRequired;
    
    RevolutionaryRank(String unlocalizedName, int loyaltyRequired) {
      this.unlocalizedName = unlocalizedName;
      this.loyaltyRequired = loyaltyRequired;
    }

    
    public String getLocalizedName() {
      return (new TranslationTextComponent(this.unlocalizedName, new Object[0])).getFormattedText();
    }

    
    public int getRequiredLoyalty() {
      return this.loyaltyRequired;
    }
  }
}


