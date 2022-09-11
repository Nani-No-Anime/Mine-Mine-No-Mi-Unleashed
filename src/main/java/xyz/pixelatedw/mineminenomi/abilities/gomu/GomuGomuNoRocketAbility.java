package xyz.pixelatedw.mineminenomi.abilities.gomu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.brawler.KingPunchProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoRocketProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class GomuGomuNoRocketAbility extends PunchTriggerAbility {
  public static final GomuGomuNoRocketAbility INSTANCE = new GomuGomuNoRocketAbility();

  
  public GomuGomuNoRocketAbility() {
    super("Gomu Gomu no Rocket", AbilityHelper.getDevilFruitCategory());
    setDescription("Stretches towards a block, then launches the user on an arch depending on where they fist landed");
    setMaxCooldown(3.0D);
    setThreshold(0.5D);
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    AbilityHelper.slowEntityFall((LivingEntity)player, 2);
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    if (GomuHelper.hasGearFourthActive(props)) {
      
      setThreshold(10.0D);
      setMaxCooldown(30.0D);
      setDisplayName("King Kong Gun");
      setCustomTexture("king_kong_gun");
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
      return true;
    } 
    setMaxCooldown(3.0D);
    setThreshold(1.0D);
    setDisplayName("Gomu Gomu no Rocket");
    setCustomTexture("gomu_gomu_no_rocket");
    WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);

    
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    if (GomuHelper.hasGearFourthActive(props)) {
      
      if (getContinueTime() < getThreshold())
        return false; 
      KingPunchProjectile kingPunchProjectile = new KingPunchProjectile(player.world, (LivingEntity)player);
      kingPunchProjectile.setMaxLife(25);
      kingPunchProjectile.setDamage(120.0F);
      ((AbilityProjectileEntity)kingPunchProjectile).onBlockImpactEvent = (pos -> {
                    // fixed cannot find symbol
                    AbilityProjectileEntity proj=kingPunchProjectile;
          ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, proj.getPosX(), proj.getPosY(), proj.getPosZ(), 13.0F);
          
          explosion.setStaticDamage(80.0F);
          explosion.setExplosionSound(false);
          explosion.setDamageOwner(false);
          explosion.setDestroyBlocks(true);
          explosion.setFireAfterExplosion(false);
          explosion.setDamageEntities(false);
          explosion.doExplosion();
        });
      player.world.addEntity((Entity)kingPunchProjectile);
      kingPunchProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.0F);
      return true;
    } 
    
    AbilityHelper.slowEntityFall((LivingEntity)player, 10);
    GomuGomuNoRocketProjectile projectile = new GomuGomuNoRocketProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)projectile);
    projectile.setMotion(0.0D, 0.0D, 0.0D);
    projectile.gear2 = GomuHelper.hasGearSecondActive(props);
    float speed = GomuHelper.hasGearSecondActive(props) ? 4.0F : 3.125F;
    projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.GOMU_SFX, SoundCategory.PLAYERS, 0.8F, (float)MathHelper.clamp(player.getRNG().nextDouble() * 2.0D, 1.0D, 1.2999999523162842D));
    
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    return true;
  }
}


