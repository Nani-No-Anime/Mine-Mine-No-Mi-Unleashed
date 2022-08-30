/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class KamieAbility
/*    */   extends ContinuousAbility implements IOnDamageAbility {
/* 16 */   public static final Ability INSTANCE = (Ability)new KamieAbility();
/*    */ 
/*    */   
/*    */   public KamieAbility() {
/* 20 */     super("Kami-E", AbilityHelper.getRacialCategory());
/* 21 */     setThreshold(6.0D);
/* 22 */     setDescription("Makes the user's body flexible in order to avoid attacks");
/*    */     
/* 24 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 29 */     int cooldown = (int)Math.round(this.continueTime / 10.0D) + 4;
/* 30 */     setMaxCooldown(cooldown);
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
/* 37 */     ArrayList<String> acceptableInstantSources = new ArrayList<>(Arrays.asList(new String[] { "mob", "player", "ability_projectile" }));
/*    */     
/* 39 */     if (source.getImmediateSource() instanceof LivingEntity) {
/* 40 */       return !acceptableInstantSources.contains(source.getDamageType());
/*    */     }
/* 42 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\KamieAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */