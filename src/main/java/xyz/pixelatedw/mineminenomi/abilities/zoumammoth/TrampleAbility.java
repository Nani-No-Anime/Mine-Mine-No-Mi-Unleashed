package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
import com.google.common.collect.ImmutableList;

import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class TrampleAbility extends PassiveAbility implements IFormRequiredAbility {
  public static final TrampleAbility INSTANCE = new TrampleAbility();
  
  public float speed = 0.0F;

  
  public TrampleAbility() {
    super("Trample", AbilityHelper.getDevilFruitCategory());
    setDescription("Running speed increases with acceleration trampling any nearby entity.");
    hideInGUI(false);
    
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  public void duringPassiveEvent(PlayerEntity player) {
    if (player.abilities.isFlying) {
      return;
    }
    if (!player.isSprinting()) {
      
      this.speed = 0.0F;
    }
    else {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 3.5D);
      targets.remove(player);

      
      AncientStompAbility ability = (AncientStompAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)AncientStompAbility.INSTANCE);
      float maxSpeed = (ability != null && ability.isContinuous()) ? 0.1F : 0.45F;
      float acceleration = (ability != null && ability.isContinuous()) ? 0.001F : 0.004F;
      
      acceleration *= (this.speed > 0.0F) ? (1.0F - this.speed / maxSpeed) : 1.0F;
      if (player.moveForward <= 0.0F || player.collidedHorizontally)
        acceleration = -maxSpeed / 10.0F; 
      this.speed = MathHelper.clamp(this.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 20.0F) : 0.0F, maxSpeed);
      
      int d2 = (player.moveForward > 0.0F) ? 1 : 0;
      
      Vec3d vec = player.getLookVec();
      double x = vec.x * this.speed * d2;
      double z = vec.z * this.speed * d2;
      player.setMotion(x, (player.getMotion()).y, z);
      
      if (!player.world.isRemote) {
        
        List<BlockPos> blocks = WyHelper.getNearbyBlocks(player.getPosition(), (IWorld)player.world, 5, pos -> FoliageBlockProtectionRule.INSTANCE.isPresent(player.world.getBlockState(pos)), (List)ImmutableList.of(Blocks.AIR));
        
        for (BlockPos pos : blocks) {
          
          BlockState blockState = player.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
          for (int i = 0; i < 150; i++) {
            
            double offsetX = WyHelper.randomDouble();
            double offsetY = WyHelper.randomDouble();
            double offsetZ = WyHelper.randomDouble();
            
            ((ServerWorld)player.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), pos
                
                .getX() + offsetX, pos
                .getY() + offsetY, pos
                .getZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
          } 
          
          AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), Blocks.AIR, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE);
        } 
        
        for (LivingEntity target : targets) {
          
          Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
          target.attackEntityFrom(DamageSource.causePlayerDamage(player), 6.0F);
          target.setMotion(speed.x, 0.2D, speed.z);
        } 
      } 
    } 
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)MammothGuardZoanInfo.INSTANCE };
  }
}


