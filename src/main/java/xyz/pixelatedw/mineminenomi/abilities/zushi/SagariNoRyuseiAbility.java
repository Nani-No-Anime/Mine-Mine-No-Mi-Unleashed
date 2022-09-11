package xyz.pixelatedw.mineminenomi.abilities.zushi;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.zushi.SagariNoRyuseiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class SagariNoRyuseiAbility extends RepeaterAbility {
  public static final SagariNoRyuseiAbility INSTANCE = new SagariNoRyuseiAbility();

  
  public SagariNoRyuseiAbility() {
    super("Sagari no Ryusei", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(45.0D);
    setMaxRepeaterCount(4, 20);
    setDescription("Using gravity, the user pulls a nearby meteorites down on their enemies");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }
  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    setMaxRepeaterCount((int)WyHelper.randomWithRange(1, 2), 20);
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
    
    for (int i = 2; i < 16; i += 2) {
      GraviZoneAbility.gravityRing((LivingEntity)player, 4, i, false);
    }
    
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
    
    double x = (mop.getHitVec()).x;
    double y = (mop.getHitVec()).y;
    double z = (mop.getHitVec()).z;
    
    SagariNoRyuseiProjectile proj = new SagariNoRyuseiProjectile(player.world, (LivingEntity)player);

    
    float size = (getRepeaterCount() == getMaxRepeaterCount()) ? (float)WyHelper.randomWithRange(24, 30) : (float)(WyHelper.randomWithRange(4, 8) * getRepeaterCount());
    
    proj.setSize(size);
    proj.setLocationAndAngles(x, y + 90.0D, z, 0.0F, 0.0F);
    proj.setMotion(0.0D, -1.85D, 0.0D);
    player.world.addEntity((Entity)proj);
    
    return true;
  }
}


