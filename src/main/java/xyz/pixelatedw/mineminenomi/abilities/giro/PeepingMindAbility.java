package xyz.pixelatedw.mineminenomi.abilities.giro;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class PeepingMindAbility extends PunchAbility {
  public static final PeepingMindAbility INSTANCE = new PeepingMindAbility();

  
  public PeepingMindAbility() {
    super("Peeping Mind", AbilityHelper.getDevilFruitCategory());
    setDescription("The user looks into the enemies mind, learning about its abilities and where it lives");
    
    setMaxCooldown(10.0D);
    this.onUseEvent = this::onUseEvent;
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private float onHitEntity(PlayerEntity entity, LivingEntity target) {
    IEntityStats statsData = EntityStatsCapability.get(target);
    IDevilFruit fruitData = DevilFruitCapability.get(target);
    IAbilityData abilityData = AbilityDataCapability.get(target);
    IHakiData hakiData = HakiDataCapability.get(target);
    ExtendedWorldData worldData = ExtendedWorldData.get(target.world);
    Crew crew = worldData.getCrewWithMember(target.getUniqueID());
    
    StringBuilder builder = new StringBuilder();
    
    builder.append("===============================================\n");
    
    builder.append("Name: " + target.getDisplayName().getFormattedText() + "\n");
    builder.append("Faction: " + statsData.getFaction() + "\n");
    if (statsData.isMarine() || statsData.isRevolutionary()) {
      
      builder.append("Loyalty: " + statsData.getLoyalty() + "\n");
      builder.append("Rank: " + statsData.getMarineRank().getLocalizedName() + "\n");
    } else {
      
      builder.append("Crew: " + ((crew != null) ? crew.getName() : "None") + "\n");
    } 
    
    builder.append("Race: " + statsData.getRace() + "\n");
    builder.append("Style: " + statsData.getFightingStyle() + "\n");
    builder.append("Doriki: " + statsData.getDoriki() + "\n");
    builder.append("Belly: " + statsData.getBelly() + "\n");
    builder.append("Extol: " + statsData.getExtol() + "\n");
    builder.append("Bounty: " + statsData.getBounty() + "\n");
    builder.append("Devil Fruit: " + (!WyHelper.isNullOrEmpty(fruitData.getDevilFruit()) ? fruitData.getDevilFruit() : "None") + "\n");
    if (target.getBedPosition().isPresent())
      builder.append("Spawn Point: " + target.getBedPosition().isPresent() + "\n"); 
    builder.append("Haki level: " + (hakiData.getTotalHakiExp() / 6.0F) + "\n");
    
    builder.append("Unlocked Abilities: \n");
    for (Ability ability : abilityData.getEquippedAbilities()) {
      
      if (ability != null) {
        builder.append("- " + ability.getName() + " \n");
      }
    } 
    builder.append("===============================================");
    
    entity.sendMessage((ITextComponent)new StringTextComponent(builder.toString()));
    return 0.0F;
  }
  
  private boolean onUseEvent(PlayerEntity playerEntity) {
    return true;
  }
}


