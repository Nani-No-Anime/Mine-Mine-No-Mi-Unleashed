/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.enchantment.Enchantments;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.quest.SDespawnQuestObjectivePacket;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class SniperTrial04Quest extends Quest {
/*    */   static {
/* 29 */     ITEM_WITH_PUNCH_2 = (itemStack -> 
/*    */       
/* 31 */       (ItemsHelper.isBow(itemStack) && EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, itemStack) > 1));
/*    */   }
/*    */   private static final Predicate<ItemStack> ITEM_WITH_PUNCH_2;
/* 34 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a bow with Punch II", 1, ITEM_WITH_PUNCH_2);
/* 35 */   private Objective objective02 = (new ObtainItemObjective("Collect %s cacti", 50, Items.CACTUS)).addRequirement(this.objective01);
/* 36 */   private Objective objective03 = (new KillEntityObjective("Hit all %s targets before they touch the ground", 10, ModEntities.SNIPER_TARGET)).addRequirement(this.objective02);
/*    */   
/* 38 */   private List<SniperTargetEntity> targets = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public SniperTrial04Quest() {
/* 42 */     super("sniper_trial_04", "Trial: Sakuretsu Saboten Boshi");
/* 43 */     addRequirements(new Quest[] { ModQuests.SNIPER_TRIAL_01, ModQuests.SNIPER_TRIAL_02 });
/*    */     
/* 45 */     this.objective03.setHasEvent(true);
/* 46 */     this.objective03.onStartEvent = this::spawnTargets;
/* 47 */     this.objective03.onRestartEvent = this::respawnTargets;
/*    */     
/* 49 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 50 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   private void spawnTargets(PlayerEntity player) {
/* 55 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 57 */       SniperTargetEntity target = new SniperTargetEntity(player.world);
/* 58 */       double posX = player.getPosX() + WyHelper.randomWithRange(-10, 10);
/* 59 */       double posY = player.getPosY() + 30.0D;
/* 60 */       double posZ = player.getPosZ() + WyHelper.randomWithRange(-10, 10);
/* 61 */       target.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
/*    */       
/* 63 */       player.world.addEntity((Entity)target);
/* 64 */       this.targets.add(target);
/*    */       
/* 66 */       if (target.isAlive()) {
/* 67 */         WyNetwork.sendToAll(new SDespawnQuestObjectivePacket(player.getUniqueID(), target.getEntityId()));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean respawnTargets(PlayerEntity player) {
/* 73 */     if (this.targets.size() <= 0) {
/* 74 */       return true;
/*    */     }
/* 76 */     for (SniperTargetEntity target : this.targets)
/*    */     {
/* 78 */       target.remove();
/*    */     }
/*    */     
/* 81 */     this.targets.clear();
/* 82 */     this.objective03.setProgress(0.0D);
/*    */     
/* 84 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean giveReward(PlayerEntity player) {
/* 89 */     if (!removeQuestItem(player, Items.CACTUS, 50)) {
/* 90 */       return false;
/*    */     }
/* 92 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 94 */     props.addUnlockedAbility(SakuretsuSabotenBoshiAbility.INSTANCE);
/*    */     
/* 96 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */