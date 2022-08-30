/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class PlayDeadAbility extends ContinuousAbility implements IFormRequiredAbility {
/* 17 */   public static final PlayDeadAbility INSTANCE = new PlayDeadAbility();
/*    */ 
/*    */   
/*    */   public PlayDeadAbility() {
/* 21 */     super("Play Dead", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("While playing dead the user focuses all of their power into regeneration.");
/* 23 */     setThreshold(10.0D);
/*    */     
/* 25 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 26 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int passiveTimer) {
/* 31 */     EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, 2, 1, false, false);
/* 32 */     player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2, 1, false, false));
/* 33 */     player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 1, false, false));
/* 34 */     player.addPotionEffect(instance);
/* 35 */     if (player instanceof ServerPlayerEntity) {
/* 36 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(player.getEntityId(), instance));
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 41 */     int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
/* 42 */     setMaxCooldown(cooldown);
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 49 */     return new ZoanInfo[] { (ZoanInfo)AxolotlWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\PlayDeadAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */