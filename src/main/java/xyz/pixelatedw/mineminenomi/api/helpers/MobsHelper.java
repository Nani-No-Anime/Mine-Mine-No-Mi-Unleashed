/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class MobsHelper
/*    */ {
/* 20 */   public static final Comparator<LivingEntity> ENTITY_THREAT = new Comparator<LivingEntity>()
/*    */     {
/*    */       
/*    */       public int compare(LivingEntity e1, LivingEntity e2)
/*    */       {
/* 25 */         int e1Threat = MobsHelper.getEntityThreatLevel(e1);
/* 26 */         int e2Threat = MobsHelper.getEntityThreatLevel(e2);
/*    */         
/* 28 */         return e1Threat - e2Threat;
/*    */       }
/*    */     };
/*    */ 
/*    */   
/*    */   public static int getEntityThreatLevel(LivingEntity entity) {
/* 34 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/*    */       
/* 36 */       IHakiData hakiProps = HakiDataCapability.get(entity);
/* 37 */       IEntityStats statsProps = EntityStatsCapability.get(entity);
/* 38 */       IDevilFruit dfProps = DevilFruitCapability.get(entity);
/* 39 */       IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*    */       
/* 41 */       int threat = (int)((statsProps.getDoriki() / 2000) + hakiProps.getTotalHakiExp() / 60.0F);
/* 42 */       if (dfProps.hasDevilFruit()) {
/*    */         
/* 44 */         threat += 2;
/* 45 */         if (dfProps.isLogia()) {
/* 46 */           threat += 2;
/*    */         }
/*    */       } 
/* 49 */       if (abilityProps.hasUnlockedAbility((Ability)HaoshokuHakiAbility.INSTANCE)) {
/*    */         
/* 51 */         threat += 2;
/* 52 */         if (abilityProps.hasEquippedAbility((Ability)HaoshokuHakiAbility.INSTANCE)) {
/* 53 */           threat += 3;
/*    */         }
/*    */       } 
/* 56 */       return threat;
/*    */     } 
/* 58 */     if (entity instanceof OPEntity)
/*    */     {
/* 60 */       return ((OPEntity)entity).getThreat();
/*    */     }
/*    */     
/* 63 */     return (int)entity.getMaxHealth();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\MobsHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */