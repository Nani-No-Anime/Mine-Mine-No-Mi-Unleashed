/*    */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.toriphoenix.PhoenixGoenProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class PhoenixGoenAbility extends RepeaterAbility implements IFormRequiredAbility {
/* 16 */   public static final PhoenixGoenAbility INSTANCE = new PhoenixGoenAbility();
/*    */ 
/*    */   
/*    */   public PhoenixGoenAbility() {
/* 20 */     super("Phoenix Goen", AbilityHelper.getDevilFruitCategory());
/* 21 */     setMaxCooldown(8.0D);
/* 22 */     setMaxRepeaterCount(5, 4);
/* 23 */     setDescription("Launches high speed blue flames while midair.");
/*    */     
/* 25 */     this.onStartContinuityEvent = this::onStartContinuity;
/* 26 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuity(PlayerEntity player) {
/* 31 */     if (player.onGround) {
/*    */       
/* 33 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { getName() }));
/* 34 */       return false;
/*    */     } 
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 42 */     PhoenixGoenProjectile proj = new PhoenixGoenProjectile(player.world, (LivingEntity)player, player.getLookVec());
/* 43 */     player.world.addEntity((Entity)proj);
/* 44 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 5.0F);
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 52 */     return new ZoanInfo[] { (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE, (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\PhoenixGoenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */