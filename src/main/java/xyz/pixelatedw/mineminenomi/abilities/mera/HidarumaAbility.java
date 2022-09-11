package xyz.pixelatedw.mineminenomi.abilities.mera;


import java.util.List;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HidarumaProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.MeraProjectiles;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class HidarumaAbility extends Ability {
  public static final Ability INSTANCE = new HidarumaAbility();
  
  private static final int MIN_FIREFLIES = 6;
  
  private static final int MAX_FIREFLIES = 9;
  
  public HidarumaAbility() {
    super("Hidaruma", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("Creates small green fireballs that set the target on fire");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 100.0D);
    
    double i = (mop.getHitVec()).x;
    double j = (mop.getHitVec()).y;
    double k = (mop.getHitVec()).z;
    BlockPos originPos = new BlockPos(i, j, k);
    Optional<LivingEntity> target = null;
    
    List<LivingEntity> list = WyHelper.getEntitiesNear(originPos, player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    list.sort(MobsHelper.ENTITY_THREAT);
    
    if (list.size() > 0) {
      
      target = list.stream().findAny();
      originPos = ((LivingEntity)target.get()).getPosition();
    } 
    
    int random = (int)WyHelper.randomWithRange(6, 9);
    
    for (int n = 0; n < random; n++) {
      
      BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(player.world, MeraProjectiles.HIDARUMA, originPos, 10, 7);
      if (spawnPos != null) {
        
        HidarumaProjectile proj = new HidarumaProjectile(player.world, (LivingEntity)player);
        proj.setTarget(target);
        proj.setPosition(spawnPos.getX(), (spawnPos.getY() + 1), spawnPos.getZ());
        player.world.addEntity((Entity)proj);
      } 
    } 
    return true;
  }
}


