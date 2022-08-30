/*    */ package xyz.pixelatedw.mineminenomi.abilities.suke;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class SukePunchAbility extends PunchAbility implements IParallelContinuousAbility {
/* 14 */   public static final Ability INSTANCE = (Ability)new SukePunchAbility();
/*    */ 
/*    */   
/*    */   public SukePunchAbility() {
/* 18 */     super("Suke Punch", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Turns an entity's entire body invisible after hitting them");
/*    */     
/* 21 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 26 */     if (target.isPotionActive(Effects.INVISIBILITY)) {
/* 27 */       target.removePotionEffect(Effects.INVISIBILITY);
/*    */     } else {
/* 29 */       target.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 2000, 1, false, false));
/*    */     } 
/* 31 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suke\SukePunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */