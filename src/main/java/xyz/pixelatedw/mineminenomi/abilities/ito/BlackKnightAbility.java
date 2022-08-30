/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*    */ import java.lang.invoke.SerializedLambda;

import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class BlackKnightAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 13 */   public static final BlackKnightAbility INSTANCE = new BlackKnightAbility();
/*    */   
/* 15 */   private BlackKnightEntity knight = null;
/*    */ 
/*    */   
/*    */   public BlackKnightAbility() {
/* 19 */     super("Black Knight", AbilityHelper.getDevilFruitCategory());
/* 20 */     setMaxCooldown(10.0D);
/* 21 */     setThreshold(100.0D);
/* 22 */     setDescription("Creates a clone of the user made entirely out of compressed strings\n\n§2SHIFT-USE§r: Switches between AGGRESSIVE and DEFENSIVE modes");
/*    */     
/* 24 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 25 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 30 */     this.knight = new BlackKnightEntity(player.world);
/* 31 */     this.knight.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/* 32 */     this.knight.setOwner((LivingEntity)player);
/* 33 */     player.world.addEntity((Entity)this.knight);
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 40 */     if (this.knight != null) {
/*    */       
/* 42 */       if (player.onGround && player.isSneaking()) {
/*    */         
/* 44 */         this.knight.isAggressive = !this.knight.isAggressive;
/* 45 */         String abilityName = (new TranslationTextComponent(getI18nKey(), new Object[0])).getFormattedText();
/* 46 */         String puppetState = (new TranslationTextComponent(this.knight.isAggressive ? ModI18n.ABILITY_PUPPET_STATE_AGGRESSIVE : ModI18n.ABILITY_PUPPET_STATE_DEFENSIVE, new Object[0])).getFormattedText();
/* 47 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_PUPPET_STATE, new Object[] { abilityName, puppetState }));
/* 48 */         return false;
/*    */       } 
/*    */       
/* 51 */       this.knight.remove();
/*    */     } 
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\BlackKnightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */