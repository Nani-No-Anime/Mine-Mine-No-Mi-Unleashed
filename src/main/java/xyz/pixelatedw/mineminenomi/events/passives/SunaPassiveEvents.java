/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.event.entity.ProjectileImpactEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class SunaPassiveEvents
/*    */ {
/* 26 */   public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.SUNA2, "Suna", SunaPassiveEvents::sunaDamage, new net.minecraft.util.DamageSource[0]);
/*    */   
/*    */   public static boolean sunaDamage(LivingEntity target, LivingEntity attacker) {
/* 29 */     if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled()) {
/* 30 */       attacker.addPotionEffect(new EffectInstance(Effects.WITHER, 40, 1));
/*    */     }
/* 32 */     return target.isWet();
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void playerUpdateEvent(TickEvent.PlayerTickEvent event) {
/* 38 */     PlayerEntity player = event.player;
/* 39 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*    */     
/* 41 */     if (!props.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI)) {
/*    */       return;
/*    */     }
/* 44 */     if (player.isWet()) {
/* 45 */       player.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 40, 0, true, true));
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void projectileImpactEvent(ProjectileImpactEvent.Throwable event) {
/* 51 */     if (event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
/*    */       
/* 53 */       EntityRayTraceResult entityHit = (EntityRayTraceResult)event.getRayTraceResult();
/* 54 */       if (entityHit.getEntity() instanceof LivingEntity && event.getThrowable() instanceof net.minecraft.entity.projectile.PotionEntity) {
/*    */         
/* 56 */         LivingEntity entity = (LivingEntity)entityHit.getEntity();
/* 57 */         IDevilFruit props = DevilFruitCapability.get(entity);
/* 58 */         if (props.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI))
/* 59 */           entity.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 80, 0, true, true)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\SunaPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */