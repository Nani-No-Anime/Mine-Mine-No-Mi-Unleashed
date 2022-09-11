package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoElephantGunProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetPistolProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoKongProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoPistolProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class GomuGomuNoPistolAbility extends Ability {
  public static final GomuGomuNoPistolAbility INSTANCE = new GomuGomuNoPistolAbility();
  
  private Mode mode = Mode.NORMAL;

  
  public GomuGomuNoPistolAbility() {
    super("Gomu Gomu no Pistol", AbilityHelper.getDevilFruitCategory());
    setDescription("The user stretches their arm to hit the opponent");
    setMaxCooldown(1.5D);
    
    this.onUseEvent = this::onUseEvent;
  }
  
  private boolean onUseEvent(PlayerEntity player) {
    
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    AbilityProjectileEntity projectile = null;
    float speed = 2.0F;
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    if (GomuHelper.hasGearFourthActive(props)) {
      
      GomuGomuNoKongProjectile gomuGomuNoKongProjectile = new GomuGomuNoKongProjectile(player.world, (LivingEntity)player);
      gomuGomuNoKongProjectile.setCollisionSize(2.5D);
      speed = 1.8F;
      if (this.mode != Mode.GEAR_FOURTH)
      {
        setMaxCooldown(4.0D);
        setDisplayName("Gomu Gomu no Culverin");
        this.mode = Mode.GEAR_FOURTH;
      }
               projectile = gomuGomuNoKongProjectile;
    
    } else if (GomuHelper.hasGearThirdActive(props)) {
      
      GomuGomuNoElephantGunProjectile gomuGomuNoElephantGunProjectile = new GomuGomuNoElephantGunProjectile(player.world, (LivingEntity)player);
      gomuGomuNoElephantGunProjectile.setCollisionSize(2.5D);
      speed = 1.8F;
      if (this.mode != Mode.GEAR_THIRD)
      {
        setMaxCooldown(6.0D);
        setDisplayName("Gomu Gomu no Elephant Gun");
        this.mode = Mode.GEAR_THIRD;
      }
               projectile = gomuGomuNoElephantGunProjectile;
    
    } else if (GomuHelper.hasGearSecondActive(props)) {
      
      GomuGomuNoJetPistolProjectile gomuGomuNoJetPistolProjectile = new GomuGomuNoJetPistolProjectile(player.world, (LivingEntity)player);
      speed = 2.5F;
      if (this.mode != Mode.GEAR_SECOND)
      {
        setMaxCooldown(1.0D);
        setDisplayName("Gomu Gomu no Jet Pistol");
        this.mode = Mode.GEAR_SECOND;
      }
               projectile = gomuGomuNoJetPistolProjectile;
    
    } else {
      
      GomuGomuNoPistolProjectile gomuGomuNoPistolProjectile = new GomuGomuNoPistolProjectile(player.world, (LivingEntity)player);
      if (this.mode != Mode.NORMAL) {
        
        setMaxCooldown(1.5D);
        setDisplayName("Gomu Gomu no Pistol");
        this.mode = Mode.NORMAL;
      } 
               projectile= gomuGomuNoPistolProjectile;
    } 
    
    player.world.addEntity((Entity)projectile);
    projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 1.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.GOMU_SFX, SoundCategory.PLAYERS, 0.8F, (float)MathHelper.clamp(player.getRNG().nextDouble() * 2.0D, 1.0D, 1.2999999523162842D));
    
    return true;
  }
  
  public enum Mode
  {
    NORMAL, GEAR_SECOND, GEAR_THIRD, GEAR_FOURTH;
  }
}


