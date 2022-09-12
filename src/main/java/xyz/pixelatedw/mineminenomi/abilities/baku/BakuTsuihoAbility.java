package xyz.pixelatedw.mineminenomi.abilities.baku;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.baku.BeroCannonProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.util.ArrayList;
import java.util.List;

public class BakuTsuihoAbility extends ChargeableAbility {
  public static final BakuTsuihoAbility INSTANCE = new BakuTsuihoAbility();
  
  private List<ItemStack> projectiles = new ArrayList<>();
  private List<Block> loadedProjectiles = new ArrayList<>();
  private int limit = 0;

  
  public BakuTsuihoAbility() {
    super("Baku Tsuiho", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(8.0D);
    setMaxChargeTime(4.0D);
    setCancelable();
    setDescription("Allows the user to charge multiple blocks from their inventory in their mouth and shoot them all at the same time");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
  }

  
  private void duringCooldownEvent(PlayerEntity player, int cooldown) {
    int projectileSpace = 2;
    if (this.limit > 0 && (this.cooldown - 10.0D) % 2.0D == 0.0D) {
      
      BeroCannonProjectile proj = new BeroCannonProjectile(player.world, (LivingEntity)player);
      proj.setLocationAndAngles(player
          .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
          .getPosY() + 0.3D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
          .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
      
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 3.0F);
      this.limit--;
    } 
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    this.limit = 1 + this.loadedProjectiles.size();
    
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    if (!this.projectiles.isEmpty())
    {
      if (chargeTime % 10 == 0) {
        
        ItemStack stack = this.projectiles.stream().findAny().orElse(null);
        if (stack != null) {
          
          if (stack.getCount() > 1) {
            stack.shrink(1);
          } else {
            
            player.inventory.deleteStack(stack);
            this.projectiles.remove(stack);
          } 
          this.loadedProjectiles.add(((BlockItem)stack.getItem()).getBlock());
        } 
      } 
    }
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    this.loadedProjectiles.clear();
    this.projectiles.clear();
    
    for (ItemStack item : player.inventory.mainInventory) {
      
      if (item != null && item.getItem() instanceof BlockItem && BakuMunchAbility.GRIEF_RULE.getApprovedBlocks().stream().anyMatch(p -> (p == ((BlockItem)item.getItem()).getBlock()))) {
        this.projectiles.add(item);
      }
    } 
    if (!this.projectiles.isEmpty()) {
      return true;
    }
    player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS, new Object[0]));
    return false;
  }
}


