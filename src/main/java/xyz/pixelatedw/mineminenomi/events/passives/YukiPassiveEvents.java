/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class YukiPassiveEvents {
/* 14 */   public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.YUKI, "Yuki", YukiPassiveEvents::yukiDamage, new net.minecraft.util.DamageSource[0]);
/*    */   
/*    */   public static boolean yukiDamage(LivingEntity target, LivingEntity attacker) {
/* 17 */     if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
/* 18 */       attacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 1)); 
/* 19 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\YukiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */