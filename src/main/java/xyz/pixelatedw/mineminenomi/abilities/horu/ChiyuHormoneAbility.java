/*    */ package xyz.pixelatedw.mineminenomi.abilities.horu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class ChiyuHormoneAbility extends PunchAbility {
/* 13 */   public static final ChiyuHormoneAbility INSTANCE = new ChiyuHormoneAbility();
/*    */ 
/*    */   
/*    */   public ChiyuHormoneAbility() {
/* 17 */     super("Chiyu Hormone", AbilityHelper.getDevilFruitCategory());
/* 18 */     setMaxCooldown(20.0D);
/* 19 */     setDescription("The user injects the target with special hormones to stimulate the body's immune system, healing them\n\n" + TextFormatting.DARK_GREEN + "SHIFT-USE" + TextFormatting.RESET + ": The user injects themselves");
/*    */     
/* 21 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 22 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 27 */     if (player.isSneaking()) {
/*    */       
/* 29 */       player.addPotionEffect(new EffectInstance(ModEffects.CHIYU_HORMONE, 300, 0));
/* 30 */       endContinuity(player);
/* 31 */       return false;
/*    */     } 
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 39 */     target.addPotionEffect(new EffectInstance(ModEffects.CHIYU_HORMONE, 300, 0));
/*    */     
/* 41 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\horu\ChiyuHormoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */