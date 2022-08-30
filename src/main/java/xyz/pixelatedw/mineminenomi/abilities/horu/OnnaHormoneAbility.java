/*    */ package xyz.pixelatedw.mineminenomi.abilities.horu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class OnnaHormoneAbility extends PunchAbility {
/* 13 */   public static final OnnaHormoneAbility INSTANCE = new OnnaHormoneAbility();
/*    */ 
/*    */   
/*    */   public OnnaHormoneAbility() {
/* 17 */     super("Onna Hormone", AbilityHelper.getDevilFruitCategory());
/* 18 */     setMaxCooldown(5.0D);
/* 19 */     setDescription("By injecting an enemy with special female hormones, the user can inflict moderate debuffs on them\n\n" + TextFormatting.DARK_GREEN + "SHIFT-USE" + TextFormatting.RESET + ": The user injects themselves");
/*    */     
/* 21 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 22 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 27 */     if (player.isSneaking()) {
/*    */       
/* 29 */       player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 600, 2));
/* 30 */       player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 1));
/* 31 */       player.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));
/* 32 */       endContinuity(player);
/* 33 */       return false;
/*    */     } 
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 41 */     target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 600, 2));
/* 42 */     target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 1));
/* 43 */     target.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));
/*    */     
/* 45 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\horu\OnnaHormoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */