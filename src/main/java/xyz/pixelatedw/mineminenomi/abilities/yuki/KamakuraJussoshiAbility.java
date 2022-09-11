package xyz.pixelatedw.mineminenomi.abilities.yuki;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.yuki.KamakuraParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class KamakuraJussoshiAbility extends Ability {
  public static final KamakuraJussoshiAbility INSTANCE = new KamakuraJussoshiAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { Blocks.SNOW });
  private static final KamakuraParticleEffect PARTICLES = new KamakuraParticleEffect();

  
  public KamakuraJussoshiAbility() {
    super("Kamakura Jussoshi", AbilityHelper.getDevilFruitCategory());
    setDescription("Like 'Kamakura', but creates a multi-layered snow barrier\n\n§2SHIFT-USE§r: Creates the igloo around the user");
    setMaxCooldown(12.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    World world = player.world;
    Vec3d vec3d = player.isSneaking() ? player.getPositionVector() : WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D).getHitVec();
    
    PARTICLES.life = 200;
    PARTICLES.size = 6;
    PARTICLES.spawn(world, vec3d.x, vec3d.y, vec3d.z, 0.0D, 0.0D, 0.0D);
    AbilityHelper.createEmptySphere(world, (int)vec3d.x, (int)vec3d.y, (int)vec3d.z, 4, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
    AbilityHelper.createEmptySphere(world, (int)vec3d.x, (int)vec3d.y, (int)vec3d.z, 6, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
    AbilityHelper.createEmptySphere(world, (int)vec3d.x, (int)vec3d.y, (int)vec3d.z, 8, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
    return true;
  }
}


