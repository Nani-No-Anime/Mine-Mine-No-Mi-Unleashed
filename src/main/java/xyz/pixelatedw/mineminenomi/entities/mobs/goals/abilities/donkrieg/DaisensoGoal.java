package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg;

import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class DaisensoGoal
  extends Goal {
  private DonKriegEntity entity;
  private static final AttributeModifier RANGE_MODIFIER = (new AttributeModifier(UUID.fromString("0c873824-648f-4121-b7e0-e4e6522d45d4"), "Range Multiplier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public DaisensoGoal(DonKriegEntity entity) {
    this.entity = entity;
  }


  
  public boolean shouldExecute() {
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    boolean hasLowHP = (this.entity.getHealth() <= this.entity.getMaxHealth() / 2.0F);
    boolean hasState = !this.entity.isDaisensoActive();
    boolean hasMovementUnblocked = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
    
    if (hasTarget && hasLowHP && hasState && hasMovementUnblocked) {
      return true;
    }
    return false;
  }


  
  public void startExecuting() {
    this.entity.triggerDaisensoPhase();
    this.entity.getAttackTarget().sendMessage((ITextComponent)new StringTextComponent("<" + this.entity.getDisplayName().getFormattedText() + "> DAISENSO"));
    this.entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.DAISENSO));
    
    this.entity.getAttribute(ModAttributes.ATTACK_RANGE).applyModifier(RANGE_MODIFIER);
    
    this.entity.fistCleaveAttack.setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.getId());
  }
}


