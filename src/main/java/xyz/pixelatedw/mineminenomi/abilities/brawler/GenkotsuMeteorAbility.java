package xyz.pixelatedw.mineminenomi.abilities.brawler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class GenkotsuMeteorAbility extends RepeaterAbility {
  public static final GenkotsuMeteorAbility INSTANCE = new GenkotsuMeteorAbility();
  private MODE activeMode = MODE.NORMAL;

  
  public GenkotsuMeteorAbility() {
    super("Genkotsu Meteor", AbilityHelper.getStyleCategory());
    setMaxCooldown(6.0D);
    setMaxRepeaterCount(1, 1);
    setDescription("Throws a cannon ball from the user's hand or multiple cannon balls in Ryuseigun mode\n\n§2SHIFT-USE§r: Switches between NORMAL and RYUSEIGUN modes");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isSneaking()) {
      
      if (this.activeMode == MODE.NORMAL) {
        
        setMaxCooldown(10.0D);
        setMaxRepeaterCount(10, 4);
        this.activeMode = MODE.RYUSEIGUN;
      }
      else {
        
        setMaxCooldown(3.0D);
        setMaxRepeaterCount(1, 1);
        this.activeMode = MODE.NORMAL;
      } 
      
      player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.activeMode, new Object[0]));
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
      return false;
    } 
    
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!player.getHeldItemMainhand().getItem().equals(ModItems.CANNON_BALL)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CANNONBALLS, new Object[0]));
      endContinuity(player);
      return false;
    } 
    
    player.getHeldItemMainhand().shrink(1);
    
    CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(player.world, (LivingEntity)player);
    cannonBallProjectile.setDamage(9.0F);
    ((AbilityProjectileEntity)cannonBallProjectile).onBlockImpactEvent = (hit -> {

                 //cant find symbol fix
                 AbilityProjectileEntity proj = cannonBallProjectile;
        if (proj.ticksExisted < 2) {
          return;
        }
        
        ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)proj.getThrower(), player.world, hit.getX(), hit.getY(), hit.getZ(), 1.0F);
        
        explosion.setStaticDamage(5.0F);
        explosion.setDestroyBlocks(false);
        explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
        explosion.doExplosion();
      });
    player.world.addEntity((Entity)cannonBallProjectile);
    cannonBallProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 2.0F);
    
    if (this.activeMode == MODE.NORMAL) {
      
      endContinuity(player);
      return false;
    } 
    
    return true;
  }
  
  public enum MODE
  {
    NORMAL, RYUSEIGUN;
  }
}


