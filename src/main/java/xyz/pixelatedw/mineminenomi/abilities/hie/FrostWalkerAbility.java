package xyz.pixelatedw.mineminenomi.abilities.hie;
import java.lang.invoke.SerializedLambda;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OceanPlantsProtectionRule;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public class FrostWalkerAbility extends PassiveAbility {
  public static final FrostWalkerAbility INSTANCE = new FrostWalkerAbility();
  private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)OceanPlantsProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { Blocks.WATER });

  
  public FrostWalkerAbility() {
    super("Frost Walker", AbilityHelper.getDevilFruitCategory());
    setDescription("Turns all water the user walks on into ice\n\n§2SHIFT-USE§r: Disables the ability");
    
    hideInGUI(false);
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  private void duringPassiveEvent(PlayerEntity player) {
    if (player.isSneaking() || 
      AbilityHelper.isNearbyKairoseki(player) || player
      .getRidingEntity() != null || player
      .getHealth() < player.getMaxHealth() / 5.0F || 
      !WyHelper.isBlockNearby((LivingEntity)player, 3, new Block[] { Blocks.WATER })) {
      return;
    }

    
    for (int x = -1; x <= 1; x++) {
      
      for (int y = -2; y <= 2; y++) {
        
        for (int z = -1; z <= 1; z++) {
          
          BlockPos pos = new BlockPos(player.getPosX() + x, player.getPosY() + y, player.getPosZ() + z);
          if (AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), Blocks.FROSTED_ICE, 3, GRIEF_RULE))
            player.world.getPendingBlockTicks().scheduleTick(pos, Blocks.FROSTED_ICE, MathHelper.nextInt(player.getRNG(), 20, 60)); 
        } 
      } 
    } 
  }
}


