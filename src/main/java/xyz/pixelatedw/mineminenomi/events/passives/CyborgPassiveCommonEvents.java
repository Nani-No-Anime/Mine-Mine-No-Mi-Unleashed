/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class CyborgPassiveCommonEvents
/*    */ {
/* 20 */   private static final AttributeModifier CYBORG_ARMOR = (new AttributeModifier(UUID.fromString("01344b52-e35e-44a3-9895-6fba1c10fc20"), "Cyborg Armor Addition", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 21 */   private static final AttributeModifier CYBORG_ARMOR_TOUGHNESS = (new AttributeModifier(UUID.fromString("f2443845-6f63-4916-b57e-a6805cfa47ae"), "Cyborg Armor Toughness Addition", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 26 */     if (event.getEntityLiving() != null) {
/*    */       
/* 28 */       LivingEntity entity = event.getEntityLiving();
/* 29 */       IEntityStats props = EntityStatsCapability.get(entity);
/*    */       
/* 31 */       if (props.isCyborg()) {
/*    */         
/* 33 */         if (!entity.getAttribute(SharedMonsterAttributes.ARMOR).hasModifier(CYBORG_ARMOR)) {
/* 34 */           entity.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(CYBORG_ARMOR);
/*    */         }
/* 36 */         if (!entity.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).hasModifier(CYBORG_ARMOR_TOUGHNESS)) {
/* 37 */           entity.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).applyModifier(CYBORG_ARMOR_TOUGHNESS);
/*    */         }
/* 39 */         if (entity instanceof PlayerEntity && AbilityHelper.isAffectedByWater(entity) && !((PlayerEntity)entity).abilities.isCreativeMode)
/*    */         {
/* 41 */           if (entity.isActualySwimming()) {
/* 42 */             entity.setMotion((entity.getMotion()).x / 1.07D, (entity.getMotion()).y / 1.07D, (entity.getMotion()).z / 1.07D);
/*    */           } else {
/* 44 */             entity.setMotion((entity.getMotion()).x / 1.05D, (entity.getMotion()).y / 1.05D, (entity.getMotion()).z / 1.05D);
/*    */           } 
/*    */         }
/*    */       } else {
/* 48 */         if (entity.getAttribute(SharedMonsterAttributes.ARMOR).hasModifier(CYBORG_ARMOR))
/* 49 */           entity.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(CYBORG_ARMOR); 
/* 50 */         if (entity.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).hasModifier(CYBORG_ARMOR_TOUGHNESS))
/* 51 */           entity.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier(CYBORG_ARMOR_TOUGHNESS); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\CyborgPassiveCommonEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */