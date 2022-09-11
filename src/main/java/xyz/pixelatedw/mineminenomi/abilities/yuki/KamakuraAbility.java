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
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yuki.KamakuraParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class KamakuraAbility extends Ability {
  public static final KamakuraAbility INSTANCE = new KamakuraAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { Blocks.SNOW });
  private static final ParticleEffect PARTICLES = (ParticleEffect)new KamakuraParticleEffect();

  
  public KamakuraAbility() {
    super("Kamakura", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates an igloo-like snow barrier where the user's pointing\n\n§2SHIFT-USE§r: Creates the igloo around the user");
    setMaxCooldown(8.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    World world = player.world;
    Vec3d vec3d = player.isSneaking() ? player.getPositionVector() : WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D).getHitVec();
    AbilityHelper.createEmptySphere(world, (int)vec3d.x, (int)vec3d.y, (int)vec3d.z, 4, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
    PARTICLES.spawn(world, vec3d.x, vec3d.y, vec3d.z, 0.0D, 0.0D, 0.0D);
    return true;
  }
}


