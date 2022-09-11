package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class GunArrayAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final GunArrayAbility INSTANCE = new GunArrayAbility();

  
  public GunArrayAbility() {
    super("Gun Array", AbilityHelper.getEquipmentCategory());
    setDescription("Automatically fires bullets from the inventory in groups of 4");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    return (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModArmors.WOOTZ_STEEL_ARMOR);
  }

  
  private void duringContinuityEvent(PlayerEntity player, int passiveTime) {
    if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() != ModArmors.WOOTZ_STEEL_ARMOR) {
      
      endContinuity(player);
      
      return;
    } 
    if (passiveTime % 60 == 0) {
      
      ItemStack bulletStack = null;
      for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
        
        ItemStack stack = player.inventory.getStackInSlot(i);
        if (stack != null && !stack.isEmpty() && stack.getItem() == ModItems.BULLET && stack.getCount() >= 4) {
          
          bulletStack = stack;
          
          break;
        } 
      } 
      if (bulletStack != null) {
        
        int projectileSpace = 1;
        
        int innacuracy = 1;
        if (player.isSprinting()) {
          innacuracy = 3;
        }
        for (int j = 0; j < 4; j++) {
          
          NormalBulletProjectile proj = new NormalBulletProjectile(player.world, (LivingEntity)player);
          proj.setLocationAndAngles(player
              .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
              .getPosY() + 1.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
              .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
          
          proj.setDamage(4.0F);
          proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, innacuracy);
          player.world.addEntity((Entity)proj);
        } 
        bulletStack.shrink(4);
      } 
    } 
  }
}


