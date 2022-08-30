/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class MeraPassiveEvents {
/* 15 */   public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.MERA, "Mera", MeraPassiveEvents::meraDamage, new DamageSource[] { DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.HOT_FLOOR });
/*    */   
/*    */   public static boolean meraDamage(LivingEntity target, LivingEntity attacker) {
/* 18 */     if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled()) {
/* 19 */       SetOnFireEvent event = new SetOnFireEvent(attacker, target, 10);
/* 20 */       if (!MinecraftForge.EVENT_BUS.post(event))
/* 21 */         attacker.setFire(5); 
/*    */     } 
/* 23 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\MeraPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */