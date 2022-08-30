/*    */ package xyz.pixelatedw.mineminenomi.abilities.suke;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class SkattingAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 13 */   public static final Ability INSTANCE = (Ability)new SkattingAbility();
/*    */ 
/*    */   
/*    */   public SkattingAbility() {
/* 17 */     super("Skatting", AbilityHelper.getDevilFruitCategory());
/* 18 */     setDescription("Turns the user's entire body invisible");
/*    */     
/* 20 */     this.duringContinuityEvent = this::duringContinuity;
/* 21 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int tick) {
/* 26 */     player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 60, 1, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 31 */     player.removePotionEffect(Effects.INVISIBILITY);
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suke\SkattingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */