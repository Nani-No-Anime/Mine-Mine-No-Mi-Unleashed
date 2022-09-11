package xyz.pixelatedw.mineminenomi.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import xyz.pixelatedw.mineminenomi.api.ModRegistries;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.DonKriegChallenge;
import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.GinChallenge;
import xyz.pixelatedw.mineminenomi.challenges.shellstown.MorganChallenge;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModChallenges
{
  public static final DeferredRegister<Challenge> CHALLENGES = DeferredRegister.create(ModRegistries.CHALLENGES, "mineminenomi");

  
  public static void registerChallengesGroup(Challenge[] elements) {
    for (Challenge elem : elements) {
      registerChallenge(elem);
    }
  }
  
  public static void registerChallenge(Challenge element) {
    String resourceName = element.getId();
    WyRegistry.getLangMap().put(resourceName, element.getTitle());
    
    CHALLENGES.register(resourceName, () -> element);
  }

  
  public static final Challenge MORGAN = (Challenge)new MorganChallenge();
  public static final Challenge[] MARINE_153TH_BRANCH = new Challenge[] { MORGAN };

  
  public static final Challenge GIN = (Challenge)new GinChallenge();
  public static final Challenge DON_KRIEG = (Challenge)new DonKriegChallenge();
  public static final Challenge[] KRIEG_PIRATES = new Challenge[] { GIN, DON_KRIEG };

  
  static {
    registerChallengesGroup(KRIEG_PIRATES);
    registerChallengesGroup(MARINE_153TH_BRANCH);
  }
}


