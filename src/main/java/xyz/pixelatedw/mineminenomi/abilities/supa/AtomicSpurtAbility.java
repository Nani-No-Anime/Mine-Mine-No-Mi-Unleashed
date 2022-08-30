/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class AtomicSpurtAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 10 */   public static final AtomicSpurtAbility INSTANCE = new AtomicSpurtAbility();
/*    */ 
/*    */   
/*    */   public AtomicSpurtAbility() {
/* 14 */     super("Atomic Spurt", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(5.0D);
/* 16 */     setDescription("Allows the user to skate around using bladed feet");
/* 17 */     needsClientSide();
/*    */     
/* 19 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int continueTime) {
/* 24 */     if (player.onGround)
/*    */     {
/* 26 */       if (Math.abs(player.getMotion().getX()) < 0.2D || Math.abs(player.getMotion().getZ()) < 0.2D)
/*    */       {
/* 28 */         player.setMotion(player.getMotion().getX() * 1.6D, player.getMotion().getY(), player.getMotion().getZ() * 1.6D);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\AtomicSpurtAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */