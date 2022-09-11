package xyz.pixelatedw.mineminenomi.abilities.pero;
import java.lang.invoke.SerializedLambda;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class CandyWallAbility extends Ability {
  public static final CandyWallAbility INSTANCE = new CandyWallAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE })).setBypassGriefRule();
  
  public CandyWallAbility() {
    super("Candy Wall", AbilityHelper.getDevilFruitCategory());
    setDescription("The user creates a wall made out of candy.");
    setMaxCooldown(5.0D);
    
    this.onUseEvent = this::onUseEvent;
  }
  
  private boolean onUseEvent(PlayerEntity player) {
    Direction dir = Direction.getFacingDirections((Entity)player)[0];
    if (dir == Direction.NORTH)
      AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() - 4.0D, 3, 4, 1, ModBlocks.CANDY, GRIEF_RULE); 
    if (dir == Direction.SOUTH)
      AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() + 4.0D, 3, 4, 1, ModBlocks.CANDY, GRIEF_RULE); 
    if (dir == Direction.EAST)
      AbilityHelper.createFilledCube(player.world, player.getPosX() + 4.0D, player.getPosY(), player.getPosZ(), 1, 4, 3, ModBlocks.CANDY, GRIEF_RULE); 
    if (dir == Direction.WEST)
      AbilityHelper.createFilledCube(player.world, player.getPosX() - 4.0D, player.getPosY(), player.getPosZ(), 1, 4, 3, ModBlocks.CANDY, GRIEF_RULE); 
    return true;
  }
}


