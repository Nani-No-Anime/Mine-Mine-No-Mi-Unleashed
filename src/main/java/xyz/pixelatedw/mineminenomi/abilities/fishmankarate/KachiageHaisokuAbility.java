/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class KachiageHaisokuAbility extends PunchAbility {
/* 11 */   public static final Ability INSTANCE = (Ability)new KachiageHaisokuAbility();
/*    */ 
/*    */   
/*    */   public KachiageHaisokuAbility() {
/* 15 */     super("Kachiage Haisoku", AbilityHelper.getRacialCategory());
/* 16 */     setDescription("The user delivers a powerful kick to the opponent's chin, which is stronger inside water");
/* 17 */     setMaxCooldown(8.0D);
/*    */     
/* 19 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 24 */     return player.isInWater() ? 40.0F : 20.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\KachiageHaisokuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */