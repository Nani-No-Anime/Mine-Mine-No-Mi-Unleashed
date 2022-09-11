package xyz.pixelatedw.mineminenomi.abilities.hana;
import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaFeetEntity;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class CienFleurStompAbility extends RepeaterAbility implements IAnimatedAbility {
  public static final CienFleurStompAbility INSTANCE = new CienFleurStompAbility();
  
  private static final GreatStompParticleEffect PARTICLES = new GreatStompParticleEffect();
  
  private static final int RADIUS = 10;
  
  private Iterator<BlockPos> targetedBlocks;
  
  public CienFleurStompAbility() {
    super("Cien Fleur: Stomp", AbilityHelper.getDevilFruitCategory());
    setDescription("§2Range:§r 10 blocks\nStomps the ground in front of the user using giant feet");
    
    setMaxCooldown(10.0D);
    setMaxRepeaterCount(7, 4);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    Predicate<BlockPos> predicate = pos -> (player.world.getBlockState(pos.up()).isAir() && pos.getY() > player.getPosY() - 3.0D);
    Vec3d look = player.getPositionVec().add(player.getLookVec().mul(7.0D, 1.0D, 7.0D));
    BlockPos ogPos = new BlockPos(look.getX(), player.getPosY(), look.getZ());
    List<BlockPos> poses = WyHelper.getNearbyBlocks(ogPos, (IWorld)player.world, 10, predicate, (List)ImmutableList.of(Blocks.AIR));
    this.targetedBlocks = WyHelper.shuffle(poses).stream().limit(7L).iterator();
    MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
    player.world.playSound(null, player.getPosition(), ModSounds.HANA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (this.targetedBlocks == null || !this.targetedBlocks.hasNext()) {
      return false;
    }
    BlockPos pos = this.targetedBlocks.next();
    
    HanaFeetEntity stompFeet = new HanaFeetEntity(player.world, (LivingEntity)player);
    stompFeet.setPositionAndUpdate(pos.getX(), (pos.getY() + 15), pos.getZ());
    stompFeet.setMotion(0.0D, -0.9D, 0.0D);
    player.world.addEntity((Entity)stompFeet);
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)CrossedArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


