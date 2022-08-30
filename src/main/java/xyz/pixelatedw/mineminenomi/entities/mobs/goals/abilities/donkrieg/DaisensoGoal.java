/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class DaisensoGoal
/*    */   extends Goal {
/*    */   private DonKriegEntity entity;
/* 20 */   private static final AttributeModifier RANGE_MODIFIER = (new AttributeModifier(UUID.fromString("0c873824-648f-4121-b7e0-e4e6522d45d4"), "Range Multiplier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public DaisensoGoal(DonKriegEntity entity) {
/* 24 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 30 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 31 */     boolean hasLowHP = (this.entity.getHealth() <= this.entity.getMaxHealth() / 2.0F);
/* 32 */     boolean hasState = !this.entity.isDaisensoActive();
/* 33 */     boolean hasMovementUnblocked = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
/*    */     
/* 35 */     if (hasTarget && hasLowHP && hasState && hasMovementUnblocked) {
/* 36 */       return true;
/*    */     }
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 44 */     this.entity.triggerDaisensoPhase();
/* 45 */     this.entity.getAttackTarget().sendMessage((ITextComponent)new StringTextComponent("<" + this.entity.getDisplayName().getFormattedText() + "> DAISENSO"));
/* 46 */     this.entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.DAISENSO));
/*    */     
/* 48 */     this.entity.getAttribute(ModAttributes.ATTACK_RANGE).applyModifier(RANGE_MODIFIER);
/*    */     
/* 50 */     this.entity.fistCleaveAttack.setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.getId());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\donkrieg\DaisensoGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */