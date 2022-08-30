/*    */ package xyz.pixelatedw.mineminenomi.abilities.yomi;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yomi.SoulParadeParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class SoulParadeAbility extends ContinuousAbility {
/* 17 */   public static final SoulParadeAbility INSTANCE = new SoulParadeAbility();
/* 18 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new SoulParadeParticleEffect();
/*    */ 
/*    */   
/*    */   public SoulParadeAbility() {
/* 22 */     super("Soul Parade", AbilityHelper.getDevilFruitCategory());
/* 23 */     setDescription("The user blocks incoming attacks, an enemy hitting them gets frozen immediately");
/* 24 */     setThreshold(15.0D);
/*    */     
/* 26 */     addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
/* 27 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 28 */     this.duringContinuityEvent = this::duringContinuity;
/* 29 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 34 */     if (!ItemsHelper.isSword(player.getHeldItemMainhand())) {
/*    */       
/* 36 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
/* 37 */       return false;
/*    */     } 
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 45 */     player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 3, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 50 */     int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
/* 51 */     setMaxCooldown(cooldown);
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yomi\SoulParadeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */