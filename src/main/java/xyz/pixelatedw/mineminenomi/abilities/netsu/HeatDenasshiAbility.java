/*    */ package xyz.pixelatedw.mineminenomi.abilities.netsu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class HeatDenasshiAbility extends PunchAbility {
/* 14 */   public static final HeatDenasshiAbility INSTANCE = new HeatDenasshiAbility();
/* 15 */   private int damage = 15;
/*    */ 
/*    */   
/*    */   public HeatDenasshiAbility() {
/* 19 */     super("Heat Denasshi", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("The user concentrates a high amount of heat in their fist to maximize damage");
/* 21 */     setMaxCooldown(5.0D);
/* 22 */     this.onHitEntityEvent = this::onHitEntity;
/* 23 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 28 */     SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, target, 7);
/* 29 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 30 */       target.setFire(7);
/*    */     }
/*    */   }
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 35 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 36 */     NetsuEnhancementAbility ability = (NetsuEnhancementAbility)abilityProps.getEquippedAbility((Ability)NetsuEnhancementAbility.INSTANCE);
/*    */     
/* 38 */     if (ability != null && ability.isContinuous()) {
/* 39 */       this.damage = (int)(this.damage + ability.damageToEntityWithSource(player, target));
/*    */     }
/* 41 */     return this.damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\netsu\HeatDenasshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */