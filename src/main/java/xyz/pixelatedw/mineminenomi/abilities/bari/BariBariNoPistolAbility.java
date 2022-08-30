/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class BariBariNoPistolAbility extends PunchAbility {
/* 10 */   public static final BariBariNoPistolAbility INSTANCE = new BariBariNoPistolAbility();
/*    */ 
/*    */   
/*    */   public BariBariNoPistolAbility() {
/* 14 */     super("Bari Bari no Pistol", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("The user shapes a barrier aroud their fist, which greatly increases the power of a punch");
/*    */     
/* 17 */     setMaxCooldown(5.0D);
/* 18 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 23 */     return 12.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BariBariNoPistolAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */