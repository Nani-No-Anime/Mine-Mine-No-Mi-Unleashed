package xyz.pixelatedw.mineminenomi.quests.sniper;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.CureEffectObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SniperTrial02Quest extends Quest {
  static {
    KAEN_BOSHI_HIT_CHECK = ((player, target, source, amount) -> 
      
      (source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile && !target.isImmuneToFire()));
  }
  private static final HitEntityObjective.ICheckHit KAEN_BOSHI_HIT_CHECK;
  private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s spider eyes", 30, Items.SPIDER_EYE);
  private Objective objective02 = (new HitEntityObjective("Set %s enemies on fire using Kaen Boshi", 20, KAEN_BOSHI_HIT_CHECK)).addRequirement(this.objective01);
  private Objective objective03 = (new CureEffectObjective("Cure yourself of Poison", 1, Effects.POISON)).addRequirement(this.objective01);

  
  public SniperTrial02Quest() {
    super("sniper_trial_01", "Trial: Kemuri Boshi");
    addRequirements(new Quest[] { ModQuests.SNIPER_TRIAL_01 });
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    if (!removeQuestItem(player, Items.SPIDER_EYE, 30)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility(KemuriBoshiAbility.INSTANCE);
    
    return true;
  }
}


