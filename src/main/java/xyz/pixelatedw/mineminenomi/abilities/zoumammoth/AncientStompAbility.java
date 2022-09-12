package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class AncientStompAbility extends RepeaterAbility implements IFormRequiredAbility {
  public static final AncientStompAbility INSTANCE = new AncientStompAbility();
  
  private static final GreatStompParticleEffect PARTICLES = new GreatStompParticleEffect();
  
  private static final int RADIUS = 5;
  
  private Iterator<BlockPos> targetedBlocks;
  
  public AncientStompAbility() {
    super("Ancient Stomp", AbilityHelper.getDevilFruitCategory());
    setDescription("By stomping the ground in front of the user ground shockwaves are created dealing damage to all nearby enemies");
    setMaxCooldown(10.0D);
    setMaxRepeaterCount(12, 8);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    Predicate<BlockPos> predicate = pos -> (player.world.getBlockState(pos.up()).isAir() && pos.getY() > player.getPosY() - 3.0D);
    Vec3d look = player.getPositionVec().add(player.getLookVec().mul(7.0D, 1.0D, 7.0D));
    BlockPos ogPos = new BlockPos(look.getX(), player.getPosY(), look.getZ());
    List<BlockPos> poses = WyHelper.getNearbyBlocks(ogPos, (IWorld)player.world, 5, predicate, (List)ImmutableList.of(Blocks.AIR));
    this.targetedBlocks = WyHelper.shuffle(poses).stream().limit(10L).iterator();
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (this.targetedBlocks == null || !this.targetedBlocks.hasNext()) {
      return false;
    }
    if (!canUse(player)) {
      
      endContinuity(player);
      return false;
    } 
    
    BlockPos pos = this.targetedBlocks.next();
    
    List<LivingEntity> list = WyHelper.getEntitiesNear(pos, player.world, 7.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    Iterator<LivingEntity> iter = list.iterator();
    
    while (iter.hasNext()) {
      
      LivingEntity target = iter.next();
      target.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 20, 0));
      target.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this), 10.0F);
      target.setMotion(target.getMotion().add(0.0D, 0.25D, 0.0D));
      target.velocityChanged = true;
    } 
    
    List<BlockPos> blocks = WyHelper.getNearbyBlocks(player.getPosition(), (IWorld)player.world, 7, p -> FoliageBlockProtectionRule.INSTANCE.isPresent(player.world.getBlockState(p)), (List)ImmutableList.of(Blocks.AIR));
    for (BlockPos p : blocks) {
      
      BlockState blockState1 = player.world.getBlockState(new BlockPos(p.getX(), p.getY(), p.getZ()));
      for (int i = 0; i < 150; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        ((ServerWorld)player.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState1), p
            
            .getX() + offsetX, p
            .getY() + offsetY, p
            .getZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
      
      AbilityHelper.placeBlockIfAllowed(player.world, p.getX(), p.getY(), p.getZ(), Blocks.AIR, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE);
    } 
    
    BlockState blockState = player.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
    if (blockState.getMaterial().isSolid())
    {
      for (int i = 0; i < 150; i++) {
        
        double x = WyHelper.randomDouble();
        double z = WyHelper.randomDouble();
        
        ((ServerWorld)player.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), pos
            .getX() + WyHelper.randomWithRange(-3, 3) + x, (pos
            .getY() + 1), pos
            .getZ() + WyHelper.randomWithRange(-3, 3) + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
    }

    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)MammothGuardZoanInfo.INSTANCE };
  }
}


