package xyz.pixelatedw.mineminenomi.abilities.yuki;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yuki.YukiGakiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class YukiGakiAbility extends Ability {
  public static final YukiGakiAbility INSTANCE = new YukiGakiAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
  private static final ParticleEffect PARTICLES = (ParticleEffect)new YukiGakiParticleEffect();

  
  public YukiGakiAbility() {
    super("Yuki Gaki", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("Creates a wall made of hardened snow to protect the user");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    int posZ = 0;
    int posX = 0;
    
    Direction facingDirection = Direction.getFacingDirections((Entity)player)[0];
    if (facingDirection == Direction.NORTH) { posZ = -4; }
    else if (facingDirection == Direction.SOUTH) { posZ = 4; }
    else if (facingDirection == Direction.EAST) { posX = 4; }
    else if (facingDirection == Direction.WEST) { posX = -4; }
    
    AbilityHelper.createFilledCube(player.world, player.getPosX() + posX, player.getPosY(), player.getPosZ() + posZ, (posX == 0) ? 3 : 1, 4, (posZ == 0) ? 3 : 1, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
    PARTICLES.spawn(player.world, player.getPosX() + posX, player.getPosY(), player.getPosZ() + posZ, 0.0D, 0.0D, 0.0D);
    return true;
  }
}


