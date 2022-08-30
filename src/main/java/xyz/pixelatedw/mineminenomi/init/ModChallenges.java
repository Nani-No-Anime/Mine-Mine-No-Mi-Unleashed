/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.DonKriegChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.kriegpirates.GinChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.shellstown.MorganChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModChallenges
/*    */ {
/* 16 */   public static final DeferredRegister<Challenge> CHALLENGES = DeferredRegister.create(ModRegistries.CHALLENGES, "mineminenomi");
/*    */ 
/*    */   
/*    */   public static void registerChallengesGroup(Challenge[] elements) {
/* 20 */     for (Challenge elem : elements) {
/* 21 */       registerChallenge(elem);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void registerChallenge(Challenge element) {
/* 26 */     String resourceName = element.getId();
/* 27 */     WyRegistry.getLangMap().put(resourceName, element.getTitle());
/*    */     
/* 29 */     CHALLENGES.register(resourceName, () -> element);
/*    */   }
/*    */ 
/*    */   
/* 33 */   public static final Challenge MORGAN = (Challenge)new MorganChallenge();
/* 34 */   public static final Challenge[] MARINE_153TH_BRANCH = new Challenge[] { MORGAN };
/*    */ 
/*    */   
/* 37 */   public static final Challenge GIN = (Challenge)new GinChallenge();
/* 38 */   public static final Challenge DON_KRIEG = (Challenge)new DonKriegChallenge();
/* 39 */   public static final Challenge[] KRIEG_PIRATES = new Challenge[] { GIN, DON_KRIEG };
/*    */ 
/*    */   
/*    */   static {
/* 43 */     registerChallengesGroup(KRIEG_PIRATES);
/* 44 */     registerChallengesGroup(MARINE_153TH_BRANCH);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModChallenges.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */