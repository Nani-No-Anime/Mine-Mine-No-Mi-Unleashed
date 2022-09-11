package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class CandleWallAbility extends Ability {
  public static final Ability INSTANCE = new CandleWallAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });

  
  public CandleWallAbility() {
    super("Candle Wall", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(6.0D);
    setDescription("Creates a wax wall in front of the user");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    Direction dir = Direction.getFacingDirections((Entity)player)[0];
    
    int thiccness = 1;
    Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      thiccness = 2;
    }
    if (dir == Direction.NORTH)
      AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() - 4.0D, 3, 4, thiccness, ModBlocks.WAX, GRIEF_RULE); 
    if (dir == Direction.SOUTH)
      AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() + 4.0D, 3, 4, thiccness, ModBlocks.WAX, GRIEF_RULE); 
    if (dir == Direction.EAST)
      AbilityHelper.createFilledCube(player.world, player.getPosX() + 4.0D, player.getPosY(), player.getPosZ(), thiccness, 4, 3, ModBlocks.WAX, GRIEF_RULE); 
    if (dir == Direction.WEST) {
      AbilityHelper.createFilledCube(player.world, player.getPosX() - 4.0D, player.getPosY(), player.getPosZ(), thiccness, 4, 3, ModBlocks.WAX, GRIEF_RULE);
    }
    return true;
  }
}


