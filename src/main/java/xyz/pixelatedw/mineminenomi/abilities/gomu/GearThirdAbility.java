/*    */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class GearThirdAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 18 */   public static final GearThirdAbility INSTANCE = new GearThirdAbility();
/*    */ 
/*    */   
/*    */   public GearThirdAbility() {
/* 22 */     super("Gear Third", AbilityHelper.getDevilFruitCategory());
/* 23 */     setThreshold(10.0D);
/* 24 */     setDescription("By blowing air and inflating their body, the user's attacks get bigger and gain incredible strength");
/*    */     
/* 26 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 27 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 32 */     if (!GomuHelper.canActivateGear(AbilityDataCapability.get((LivingEntity)player), (Ability)INSTANCE)) {
/*    */       
/* 34 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_GEAR_ACTIVE, new Object[0]));
/* 35 */       return false;
/*    */     } 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 43 */     int cooldown = (int)Math.round(this.continueTime / 20.0D);
/* 44 */     setMaxCooldown(cooldown);
/* 45 */     if (EntityStatsCapability.get((LivingEntity)player).getDoriki() < 3000) {
/*    */       
/* 47 */       player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 300, 1, true, true));
/* 48 */       player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 300, 1, true, true));
/* 49 */       player.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 300, 1, true, true));
/*    */     } 
/*    */     
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GearThirdAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */