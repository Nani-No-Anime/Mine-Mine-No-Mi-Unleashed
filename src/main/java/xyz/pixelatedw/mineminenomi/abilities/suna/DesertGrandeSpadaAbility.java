/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.DesertGrandeSpadaProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class DesertGrandeSpadaAbility extends ChargeableAbility {
/* 15 */   public static final Ability INSTANCE = (Ability)new DesertGrandeSpadaAbility();
/*    */ 
/*    */   
/*    */   public DesertGrandeSpadaAbility() {
/* 19 */     super("Desert Grande Spada", AbilityHelper.getDevilFruitCategory());
/* 20 */     setMaxCooldown(20.0D);
/* 21 */     setMaxChargeTime(0.5D);
/* 22 */     setDescription("Shoots a large sand blade cutting through multiple enemies and blocks");
/*    */     
/* 24 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 25 */     this.duringChargingEvent = this::duringChargingEvent;
/* 26 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 31 */     if (!player.onGround) {
/*    */       
/* 33 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
/* 34 */       return false;
/*    */     } 
/*    */     
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int i) {
/* 41 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 46 */     DesertGrandeSpadaProjectile proj = new DesertGrandeSpadaProjectile(player.world, (LivingEntity)player);
/* 47 */     player.world.addEntity((Entity)proj);
/* 48 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 0.0F);
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\DesertGrandeSpadaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */