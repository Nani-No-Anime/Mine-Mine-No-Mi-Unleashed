/*    */ package xyz.pixelatedw.mineminenomi.abilities.mini;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.MiniZoanInfo;
/*    */ 
/*    */ public class MiniMiniAbility extends ZoanAbility {
/* 12 */   public static final MiniMiniAbility INSTANCE = new MiniMiniAbility();
/*    */ 
/*    */   
/*    */   public MiniMiniAbility() {
/* 16 */     super("Mini Mini", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("Allows the user to become smaller than their actual size.");
/*    */     
/* 19 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 20 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onStartContinuityEvent(PlayerEntity player) {
/* 26 */     if (super.onStartContinuityEvent(player)) {
/*    */       
/* 28 */       AbilityHelper.setPose((LivingEntity)player, Pose.STANDING);
/* 29 */       return true;
/*    */     } 
/*    */     
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 38 */     return super.onEndContinuityEvent(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 44 */     return (ZoanInfo)MiniZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mini\MiniMiniAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */