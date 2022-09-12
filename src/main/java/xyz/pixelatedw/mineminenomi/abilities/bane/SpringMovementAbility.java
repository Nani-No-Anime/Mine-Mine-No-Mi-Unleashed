package xyz.pixelatedw.mineminenomi.abilities.bane;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.UUID;

public class SpringMovementAbility extends PassiveAbility {
  public static final SpringMovementAbility INSTANCE = new SpringMovementAbility();
  
  public static final UUID SPRING_POWER_UUID = UUID.fromString("a44a9644-369a-4e18-88d9-323727d3d85b");
  
  private boolean startedFalling = false;

  
  public SpringMovementAbility() {
    super("Spring Movement", AbilityHelper.getDevilFruitCategory());
    
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  public void duringPassiveEvent(PlayerEntity user) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)user);
    SpringHopperAbility springHopper = (SpringHopperAbility)props.getEquippedAbility(SpringHopperAbility.INSTANCE);
    SpringSnipeAbility springSnipe = (SpringSnipeAbility)props.getEquippedAbility(SpringSnipeAbility.INSTANCE);
    
    if (isPaused()) {
      
      if (user.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(getAtribute(0)))
        user.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(getAtribute(0)); 
      springHopper.jumpPower = 0;
      
      return;
    } 
    if (springSnipe != null && springSnipe.isCharging()) {
      
      user.setMotion(0.0D, 0.0D, 0.0D);
      
      return;
    } 
    if (springHopper == null) {
      return;
    }
    if (springHopper.isContinuous()) {
      
      if (user.collidedHorizontally && springHopper.jumpPower > 2) {
        
        Vec3d speed = user.getLook(1.0F).mul(-2.0D, -2.0D, -2.0D);
        user.setMotion(speed.x, speed.y, speed.z);
        if (springHopper.jumpPower < 9)
          springHopper.jumpPower++; 
        user.world.playSound(user, user.getPosition(), ModSounds.SPRING_SFX, SoundCategory.PLAYERS, 0.3F, (float)MathHelper.clamp(user.getRNG().nextDouble() + 0.30000001192092896D, 0.800000011920929D, 1.5D));
      } 
      
      if (user.onGround) {
        
        this.startedFalling = true;
        
        if (AbilityHelper.isJumping((LivingEntity)user)) {
          
          if (springHopper.jumpPower > 3) {
            
            Vec3d speed = WyHelper.propulsion((LivingEntity)user, 0.25D + springHopper.jumpPower * 0.25D, 0.25D + springHopper.jumpPower * 0.25D);
            user.setMotion(speed.x, (user.getMotion()).y, speed.z);
          } 
          
          if (springHopper.jumpPower < 9 && springHopper.canIncreaseJumpPower) {
            springHopper.jumpPower++;
          }
          if (user.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() != springHopper.jumpPower * 1.5D) {
            
            user.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(getAtribute(springHopper.jumpPower));
            user.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(getAtribute(springHopper.jumpPower));
          } 
          
          user.world.playSound(user, user.getPosition(), ModSounds.SPRING_SFX, SoundCategory.PLAYERS, 0.3F, (float)MathHelper.clamp(user.getRNG().nextDouble() + 0.30000001192092896D, 0.800000011920929D, 1.5D));
          springHopper.canIncreaseJumpPower = true;
        } else {
          
          springHopper.jumpPower = 0;
        } 
      } else {
        
        if (user.collidedVertically) {
          springHopper.jumpPower--;
        }
        if (0.0D > (user.getMotion()).y) {
          
          if (this.startedFalling) {
            
            springHopper.canIncreaseJumpPower = (DevilFruitHelper.getDifferenceToFloor(user) > springHopper.jumpPower);
            this.startedFalling = false;
          } 
          
          if (springHopper.jumpPower > 3) {
            user.setMotion(user.getMotion().mul(1.15D, 1.15D, 1.15D));
          }
        } 
      } 
    } else {
      
      if (user.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(getAtribute(0)))
        user.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(getAtribute(0)); 
      springHopper.jumpPower = 0;
    } 
  }

  
  public AttributeModifier getAtribute(int jumpPower) {
    return (AttributeModifier)(new AbilityAttributeModifier(SPRING_POWER_UUID, (Ability)INSTANCE, "Spring Movement Modifier", jumpPower * 1.5D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  }
}


