/*    */ package xyz.pixelatedw.mineminenomi.abilities.mini;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseBothArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class PaperFloatAbility extends PassiveAbility implements IAnimatedAbility {
/* 16 */   public static final PaperFloatAbility INSTANCE = new PaperFloatAbility();
/*    */   
/*    */   private boolean hasAnimation = false;
/*    */ 
/*    */   
/*    */   public PaperFloatAbility() {
/* 22 */     super("Paper Float", AbilityHelper.getDevilFruitCategory());
/* 23 */     setDescription("While in the mini form and holding a piece of paper the user is able to float");
/* 24 */     hideInGUI(false);
/*    */     
/* 26 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringPassiveEvent(PlayerEntity player) {
/* 31 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 33 */     player.fallDistance = 0.0F;
/*    */     
/* 35 */     Ability ability = abilityProps.getEquippedAbility((Ability)MiniMiniAbility.INSTANCE);
/* 36 */     boolean isActive = (ability != null && ability.isContinuous());
/* 37 */     boolean hasPaper = (player.getHeldItemMainhand().getItem() == Items.PAPER || player.getHeldItemOffhand().getItem() == Items.PAPER);
/* 38 */     boolean inAir = (!player.onGround && (player.getMotion()).y < 0.0D);
/*    */     
/* 40 */     this.hasAnimation = (isActive && hasPaper && inAir);
/* 41 */     if (this.hasAnimation) {
/* 42 */       player.setMotion((player.getMotion()).x, (player.getMotion()).y / 2.0D, (player.getMotion()).z);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 48 */     return (IAnimation)RaiseBothArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 54 */     return this.hasAnimation;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mini\PaperFloatAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */