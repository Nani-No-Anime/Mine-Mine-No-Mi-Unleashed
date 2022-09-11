package xyz.pixelatedw.mineminenomi.quests.sniper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.packets.server.quest.SDespawnQuestObjectivePacket;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class SniperTrial04Quest extends Quest {
  static {
    ITEM_WITH_PUNCH_2 = (itemStack -> 
      
      (ItemsHelper.isBow(itemStack) && EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, itemStack) > 1));
  }
  private static final Predicate<ItemStack> ITEM_WITH_PUNCH_2;
  private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a bow with Punch II", 1, ITEM_WITH_PUNCH_2);
  private Objective objective02 = (new ObtainItemObjective("Collect %s cacti", 50, Items.CACTUS)).addRequirement(this.objective01);
  private Objective objective03 = (new KillEntityObjective("Hit all %s targets before they touch the ground", 10, ModEntities.SNIPER_TARGET)).addRequirement(this.objective02);
  
  private List<SniperTargetEntity> targets = new ArrayList<>();

  
  public SniperTrial04Quest() {
    super("sniper_trial_04", "Trial: Sakuretsu Saboten Boshi");
    addRequirements(new Quest[] { ModQuests.SNIPER_TRIAL_01, ModQuests.SNIPER_TRIAL_02 });
    
    this.objective03.setHasEvent(true);
    this.objective03.onStartEvent = this::spawnTargets;
    this.objective03.onRestartEvent = this::respawnTargets;
    
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    this.onCompleteEvent = this::giveReward;
  }

  
  private void spawnTargets(PlayerEntity player) {
    for (int i = 0; i < 10; i++) {
      
      SniperTargetEntity target = new SniperTargetEntity(player.world);
      double posX = player.getPosX() + WyHelper.randomWithRange(-10, 10);
      double posY = player.getPosY() + 30.0D;
      double posZ = player.getPosZ() + WyHelper.randomWithRange(-10, 10);
      target.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
      
      player.world.addEntity((Entity)target);
      this.targets.add(target);
      
      if (target.isAlive()) {
        WyNetwork.sendToAll(new SDespawnQuestObjectivePacket(player.getUniqueID(), target.getEntityId()));
      }
    } 
  }
  
  private boolean respawnTargets(PlayerEntity player) {
    if (this.targets.size() <= 0) {
      return true;
    }
    for (SniperTargetEntity target : this.targets)
    {
      target.remove();
    }
    
    this.targets.clear();
    this.objective03.setProgress(0.0D);
    
    return true;
  }

  
  private boolean giveReward(PlayerEntity player) {
    if (!removeQuestItem(player, Items.CACTUS, 50)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility(SakuretsuSabotenBoshiAbility.INSTANCE);
    
    return true;
  }
}


