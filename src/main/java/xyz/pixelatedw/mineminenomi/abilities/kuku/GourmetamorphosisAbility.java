/*    */ package xyz.pixelatedw.mineminenomi.abilities.kuku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class GourmetamorphosisAbility extends ContinuousAbility {
/*  9 */   public static final GourmetamorphosisAbility INSTANCE = new GourmetamorphosisAbility();
/*    */ 
/*    */   
/*    */   public GourmetamorphosisAbility() {
/* 13 */     super("Gourmetamorphosis", AbilityHelper.getDevilFruitCategory());
/* 14 */     setDescription("Makes all items in the user's inventory edible.");
/* 15 */     setThreshold(60.0D);
/*    */     
/* 17 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 22 */     int cooldown = (int)Math.round(this.continueTime / 40.0D);
/* 23 */     setMaxCooldown(cooldown);
/* 24 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kuku\GourmetamorphosisAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */