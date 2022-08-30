/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ import java.lang.invoke.SerializedLambda;

import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class DoppelmanAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 13 */   public static final DoppelmanAbility INSTANCE = new DoppelmanAbility();
/*    */   
/* 15 */   private DoppelmanEntity doppelman = null;
/*    */ 
/*    */   
/*    */   public DoppelmanAbility() {
/* 19 */     super("Doppelman", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Creates a living version of the user's shadow to help them fight\n\n§2SHIFT-USE§r: Switches between AGGRESSIVE and DEFENSIVE modes");
/* 21 */     setMaxCooldown(20.0D);
/* 22 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 23 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 28 */     this.doppelman = new DoppelmanEntity(player.world);
/* 29 */     this.doppelman.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/* 30 */     this.doppelman.setOwner((LivingEntity)player);
/* 31 */     player.world.addEntity((Entity)this.doppelman);
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 38 */     if (this.doppelman != null) {
/*    */       
/* 40 */       if (player.onGround && player.isSneaking()) {
/*    */         
/* 42 */         this.doppelman.isAggressive = !this.doppelman.isAggressive;
/* 43 */         String abilityName = (new TranslationTextComponent(getI18nKey(), new Object[0])).getFormattedText();
/* 44 */         String puppetState = (new TranslationTextComponent(this.doppelman.isAggressive ? ModI18n.ABILITY_PUPPET_STATE_AGGRESSIVE : ModI18n.ABILITY_PUPPET_STATE_DEFENSIVE, new Object[0])).getFormattedText();
/* 45 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_PUPPET_STATE, new Object[] { abilityName, puppetState }));
/* 46 */         return false;
/*    */       } 
/*    */       
/* 49 */       this.doppelman.remove();
/*    */     } 
/*    */     
/* 52 */     int cooldown = (int)Math.round(this.continueTime / 50.0D);
/* 53 */     setMaxCooldown(cooldown);
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public DoppelmanEntity getDoppelman() {
/* 60 */     return this.doppelman;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\DoppelmanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */