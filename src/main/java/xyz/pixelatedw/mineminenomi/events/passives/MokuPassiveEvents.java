/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class MokuPassiveEvents {
/* 15 */   public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.MOKU2, "Moku", MokuPassiveEvents::mokuDamage, new DamageSource[] { DamageSource.IN_FIRE });
/*    */   
/*    */   public static boolean mokuDamage(LivingEntity target, LivingEntity attacker) {
/* 18 */     if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
/* 19 */       attacker.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 1)); 
/* 20 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\MokuPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */