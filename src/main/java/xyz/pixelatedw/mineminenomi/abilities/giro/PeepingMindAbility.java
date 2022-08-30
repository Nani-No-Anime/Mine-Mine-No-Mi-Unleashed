/*    */ package xyz.pixelatedw.mineminenomi.abilities.giro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class PeepingMindAbility extends PunchAbility {
/* 23 */   public static final PeepingMindAbility INSTANCE = new PeepingMindAbility();
/*    */ 
/*    */   
/*    */   public PeepingMindAbility() {
/* 27 */     super("Peeping Mind", AbilityHelper.getDevilFruitCategory());
/* 28 */     setDescription("The user looks into the enemies mind, learning about its abilities and where it lives");
/*    */     
/* 30 */     setMaxCooldown(10.0D);
/* 31 */     this.onUseEvent = this::onUseEvent;
/* 32 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity entity, LivingEntity target) {
/* 37 */     IEntityStats statsData = EntityStatsCapability.get(target);
/* 38 */     IDevilFruit fruitData = DevilFruitCapability.get(target);
/* 39 */     IAbilityData abilityData = AbilityDataCapability.get(target);
/* 40 */     IHakiData hakiData = HakiDataCapability.get(target);
/* 41 */     ExtendedWorldData worldData = ExtendedWorldData.get(target.world);
/* 42 */     Crew crew = worldData.getCrewWithMember(target.getUniqueID());
/*    */     
/* 44 */     StringBuilder builder = new StringBuilder();
/*    */     
/* 46 */     builder.append("===============================================\n");
/*    */     
/* 48 */     builder.append("Name: " + target.getDisplayName().getFormattedText() + "\n");
/* 49 */     builder.append("Faction: " + statsData.getFaction() + "\n");
/* 50 */     if (statsData.isMarine() || statsData.isRevolutionary()) {
/*    */       
/* 52 */       builder.append("Loyalty: " + statsData.getLoyalty() + "\n");
/* 53 */       builder.append("Rank: " + statsData.getMarineRank().getLocalizedName() + "\n");
/*    */     } else {
/*    */       
/* 56 */       builder.append("Crew: " + ((crew != null) ? crew.getName() : "None") + "\n");
/*    */     } 
/*    */     
/* 59 */     builder.append("Race: " + statsData.getRace() + "\n");
/* 60 */     builder.append("Style: " + statsData.getFightingStyle() + "\n");
/* 61 */     builder.append("Doriki: " + statsData.getDoriki() + "\n");
/* 62 */     builder.append("Belly: " + statsData.getBelly() + "\n");
/* 63 */     builder.append("Extol: " + statsData.getExtol() + "\n");
/* 64 */     builder.append("Bounty: " + statsData.getBounty() + "\n");
/* 65 */     builder.append("Devil Fruit: " + (!WyHelper.isNullOrEmpty(fruitData.getDevilFruit()) ? fruitData.getDevilFruit() : "None") + "\n");
/* 66 */     if (target.getBedPosition().isPresent())
/* 67 */       builder.append("Spawn Point: " + target.getBedPosition().isPresent() + "\n"); 
/* 68 */     builder.append("Haki level: " + (hakiData.getTotalHakiExp() / 6.0F) + "\n");
/*    */     
/* 70 */     builder.append("Unlocked Abilities: \n");
/* 71 */     for (Ability ability : abilityData.getEquippedAbilities()) {
/*    */       
/* 73 */       if (ability != null) {
/* 74 */         builder.append("- " + ability.getName() + " \n");
/*    */       }
/*    */     } 
/* 77 */     builder.append("===============================================");
/*    */     
/* 79 */     entity.sendMessage((ITextComponent)new StringTextComponent(builder.toString()));
/* 80 */     return 0.0F;
/*    */   }
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity playerEntity) {
/* 84 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\giro\PeepingMindAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */