/*    */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.NoFallDamageAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ 
/*    */ public class BouncyAbility
/*    */   extends NoFallDamageAbility {
/*  9 */   public static final BouncyAbility INSTANCE = new BouncyAbility();
/*    */   
/*    */   private boolean touchedGround = true;
/* 12 */   private float bounceValue = 0.0F;
/*    */ 
/*    */   
/*    */   public BouncyAbility() {
/* 16 */     super("Bouncy", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("Makes the user bounce upon landing");
/* 18 */     hideInGUI(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void duringPassiveEvent(PlayerEntity player) {
/* 24 */     super.duringPassiveEvent(player);
/* 25 */     if (player.fallDistance > 12.0F || !this.touchedGround) {
/*    */       
/* 27 */       this.touchedGround = false;
/* 28 */       if (player.fallDistance != 0.0F) {
/* 29 */         this.bounceValue = player.fallDistance;
/*    */       }
/* 31 */       if (player.onGround && this.bounceValue / 30.0F > 0.0F) {
/*    */         
/* 33 */         this.touchedGround = true;
/* 34 */         player.setMotion((player.getMotion()).x, (this.bounceValue / 30.0F), (player.getMotion()).z);
/* 35 */         player.velocityChanged = true;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\BouncyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */