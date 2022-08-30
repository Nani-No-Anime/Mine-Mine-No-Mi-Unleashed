/*    */ package xyz.pixelatedw.mineminenomi.api.helpers.abilities;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.FailedTempoParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class ArtOfWeatherHelper {
/* 18 */   private static final ParticleEffect FAILED_TEMPO_PARTICLES = (ParticleEffect)new FailedTempoParticleEffect();
/*    */ 
/*    */   
/*    */   public static void checkForTempo(PlayerEntity player, ParticleEffect chargeParticles) {
/* 22 */     ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
/* 23 */     String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
/* 24 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 25 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
/*    */     
/* 27 */     if (tempoCombo.length() == 3) {
/*    */       
/* 29 */       List<TempoAbility> availableTempos = (List)props.getUnlockedAbilities(AbilityHelper.getStyleCategory()).stream().filter(ability -> ability instanceof TempoAbility).collect(Collectors.toList());
/* 30 */       boolean hasTempo = false;
/*    */       
/* 32 */       for (TempoAbility tempo : availableTempos) {
/*    */         
/* 34 */         if (tempo.canUseTempo(player, null)) {
/*    */           
/* 36 */           tempo.use(player);
/* 37 */           climaTact.emptyCharge(player.getHeldItemMainhand());
/* 38 */           hasTempo = true;
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 43 */       if (!hasTempo) {
/*    */         
/* 45 */         FAILED_TEMPO_PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 46 */         climaTact.emptyCharge(player.getHeldItemMainhand());
/*    */       } 
/*    */     } else {
/*    */       
/* 50 */       chargeParticles.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\abilities\ArtOfWeatherHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */