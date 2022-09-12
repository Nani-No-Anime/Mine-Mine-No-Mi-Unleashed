package xyz.pixelatedw.mineminenomi.abilities.wara;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.HurtPassiveAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.StrawDollItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class LifeMinusAbility extends HurtPassiveAbility {
  public static final LifeMinusAbility INSTANCE = new LifeMinusAbility();

  
  public LifeMinusAbility() {
    super("Life Minus", AbilityHelper.getDevilFruitCategory());
    setDescription("Transfers the damage taken to a Straw Doll in your inventory");
    hideInGUI(false);
    
    this.onHurtEvent = this::onHurtEvent;
  }

  
  private boolean onHurtEvent(LivingEntity entity, Entity source) {
    if (!(entity instanceof PlayerEntity)) {
      return true;
    }
    boolean canUseDoll = (!AbilityHelper.isAffectedByWater(entity) && !entity.isPotionActive(ModEffects.ABILITY_OFF) && !DevilFruitHelper.kairosekiChecks(entity));
    if (!canUseDoll) {
      return true;
    }
    PlayerEntity attacked = (PlayerEntity)entity;
    
    for (int i = 0; i < attacked.inventory.mainInventory.size(); i++) {
      
      ItemStack stack = attacked.inventory.getStackInSlot(i);
      
      if (stack.getItem() == ModItems.STRAW_DOLL) {
        
        LivingEntity strawDollOwner = ((StrawDollItem)stack.getItem()).getOwner(attacked.world, attacked.getPosition(), stack);
        if (attacked == strawDollOwner) {
          return true;
        }
        if (strawDollOwner == null) {
          
          attacked.inventory.deleteStack(stack);
        }
        else {
          
          boolean attack = strawDollOwner.attackEntityFrom(AbilityDamageSource.causeAbilityDamage((LivingEntity)attacked, (Ability)this).setInternalDamage().setDamageIsAbsolute().setDamageBypassesArmor(), getAmount());
          
          if (attack && strawDollOwner.getHealth() <= 0.0F) {
            
            spawnParticles((ServerWorld)attacked.world, attacked.getPosX(), attacked.getPosY(), attacked.getPosZ());
            spawnParticles((ServerWorld)strawDollOwner.world, strawDollOwner.getPosX(), strawDollOwner.getPosY(), strawDollOwner.getPosZ());
            attacked.inventory.deleteStack(stack);
          } 
          return false;
        } 
      } 
    } 
    return true;
  }

  
  private void spawnParticles(ServerWorld world, double posX, double posY, double posZ) {
    for (int i = 0; i < 5; i++) {
      
      double offsetX = WyHelper.randomDouble() / 2.0D;
      double offsetY = WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble() / 2.0D;
      world.spawnParticle(ParticleTypes.DRAGON_BREATH, posX + offsetX, posY + offsetY, posZ + offsetZ, 25, 0.0D, 0.0D, 0.0D, 0.1D);
    } 
  }
}


