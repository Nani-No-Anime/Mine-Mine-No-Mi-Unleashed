/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class GoroPassiveEvents {
/* 18 */   public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.GORO, "Goro", GoroPassiveEvents::goroDamage, new DamageSource[] { DamageSource.LIGHTNING_BOLT, DamageSource.IN_FIRE, DamageSource.HOT_FLOOR });
/*    */ 
/*    */   
/*    */   public static boolean goroDamage(LivingEntity target, LivingEntity attacker) {
/* 22 */     IDevilFruit props = DevilFruitCapability.get(attacker);
/* 23 */     boolean attackerHasGomu = (props.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI) && attacker.getHeldItemMainhand().isEmpty());
/* 24 */     if (!attackerHasGomu) {
/*    */       
/* 26 */       if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
/* 27 */         attacker.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)target), 12.0F); 
/* 28 */       return false;
/*    */     } 
/* 30 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\GoroPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */