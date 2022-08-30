/*    */ package xyz.pixelatedw.mineminenomi.abilities.horu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class TensionHormoneAbility extends PunchAbility {
/* 12 */   public static final TensionHormoneAbility INSTANCE = new TensionHormoneAbility();
/*    */ 
/*    */   
/*    */   public TensionHormoneAbility() {
/* 16 */     super("Tension Hormone", AbilityHelper.getDevilFruitCategory());
/* 17 */     setMaxCooldown(10.0D);
/* 18 */     setDescription("The user injects a target with special hormones providing a supply of adrenaline that strengthens them\n\n§2SHIFT-USE§r: The user injects themselves");
/*    */     
/* 20 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 21 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {

/* 26 */     if (player.isSneaking()) {
/*    */       
/* 28 */       player.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 1));
/* 29 */       endContinuity(player);
/* 30 */       return false;
/*    */     } 
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 38 */     target.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 1));
/*    */     
/* 40 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\horu\TensionHormoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */