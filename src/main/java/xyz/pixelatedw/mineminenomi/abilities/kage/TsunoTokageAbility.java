package xyz.pixelatedw.mineminenomi.abilities.kage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.TsunoTokagePillarEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class TsunoTokageAbility extends Ability {
  public static final TsunoTokageAbility INSTANCE = new TsunoTokageAbility();

  
  public TsunoTokageAbility() {
    super("Tsuno Tokage", AbilityHelper.getDevilFruitCategory());
    setDescription("The user creates a lizard-like shadow under his opponent, which pierces them from below");
    setMaxCooldown(13.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 32.0D);
    
    double i = (mop.getHitVec()).x;
    double j = (mop.getHitVec()).y;
    double k = (mop.getHitVec()).z;
    
    TsunoTokagePillarEntity pillar = new TsunoTokagePillarEntity(player.world, (LivingEntity)player);
    pillar.rotationPitch = 90.0F;
    pillar.setPosition(i, j - 3.0D, k);
    pillar.setMotion(0.0D, 0.7D, 0.0D);
    player.world.addEntity((Entity)pillar);
    
    return true;
  }
}


