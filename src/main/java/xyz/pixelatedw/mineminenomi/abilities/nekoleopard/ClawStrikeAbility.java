/*    */ package xyz.pixelatedw.mineminenomi.abilities.nekoleopard;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.LeopardHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.LeopardWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class ClawStrikeAbility extends PunchAbility implements IFormRequiredAbility {
/* 14 */   public static final ClawStrikeAbility INSTANCE = new ClawStrikeAbility();
/*    */ 
/*    */   
/*    */   public ClawStrikeAbility() {
/* 18 */     super("Claw Strike", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Hits using the user's sharp claws");
/* 20 */     setMaxCooldown(5.0D);
/*    */     
/* 22 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 27 */     return 20.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 33 */     return new ZoanInfo[] { (ZoanInfo)LeopardHeavyZoanInfo.INSTANCE, (ZoanInfo)LeopardWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\nekoleopard\ClawStrikeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */