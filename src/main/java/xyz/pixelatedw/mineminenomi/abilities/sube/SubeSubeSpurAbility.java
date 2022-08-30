/*    */ package xyz.pixelatedw.mineminenomi.abilities.sube;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class SubeSubeSpurAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 10 */   public static final SubeSubeSpurAbility INSTANCE = new SubeSubeSpurAbility();
/*    */ 
/*    */   
/*    */   public SubeSubeSpurAbility() {
/* 14 */     super("Sube Sube Spur", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(5.0D);
/* 16 */     setDescription("Allows the user to skate around using their feet.");
/* 17 */     needsClientSide();
/*    */     
/* 19 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int continueTime) {
/* 24 */     if (player.onGround && (Math.abs(player.getMotion().getX() * 1.7D) < 0.3D || Math.abs(player.getMotion().getZ() * 1.7D) < 0.3D))
/*    */     {
/* 26 */       player.setMotion(player.getMotion().getX() * 1.7D, player.getMotion().getY(), player.getMotion().getZ() * 1.7D);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sube\SubeSubeSpurAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */