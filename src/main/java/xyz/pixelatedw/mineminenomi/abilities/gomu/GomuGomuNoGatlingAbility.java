package xyz.pixelatedw.mineminenomi.abilities.gomu;


import net.minecraft.client.network.play.IClientPlayNetHandler;
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
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class GomuGomuNoGatlingAbility extends RepeaterAbility {
  public static final GomuGomuNoGatlingAbility INSTANCE = new GomuGomuNoGatlingAbility();
  
  private boolean hasDataSet = false;

  
  public GomuGomuNoGatlingAbility() {
    super("Gomu Gomu no Gatling", AbilityHelper.getDevilFruitCategory());
    setDescription("Rapidly punches enemies using rubber fists");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    if (GomuHelper.hasGearFourthActive(props)) {
      
      setMaxCooldown(10.0D);
      setMaxRepeaterCount(8, 5);
      setDisplayName("Gomu Gomu no Kong Gatling");
    }
    else if (GomuHelper.hasGearThirdActive(props)) {
      
      setMaxCooldown(14.0D);
      setMaxRepeaterCount(12, 5);
      setDisplayName("Gomu Gomu no Elephant Gatling");
    }
    else if (GomuHelper.hasGearSecondActive(props)) {
      
      setMaxCooldown(5.0D);
      setMaxRepeaterCount(25, 2);
      setDisplayName("Gomu Gomu no Jet Gatling");
    }
    else {
      
      setMaxCooldown(7.0D);
      setMaxRepeaterCount(20, 3);
      setDisplayName("Gomu Gomu no Gatling");
    } 
    
    return true;
  }
  
  private boolean onUseEvent(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    AbilityProjectileEntity projectile = null;
    float speed = 3.0F;
    float projDmageReduction = 0.8F;
    int projectileSpace = 2;
    
    if (GomuHelper.hasGearFourthActive(props)) {
      
      GomuGomuNoKongProjectile gomuGomuNoKongProjectile = new GomuGomuNoKongProjectile(player.world, (LivingEntity)player);
      gomuGomuNoKongProjectile.setCollisionSize(2.5D);
      if (!this.hasDataSet) {
        
        speed = 2.2F;
        projectileSpace = 6;
        this.hasDataSet = true;
      } 
      projDmageReduction = 0.6F;
      projectile = gomuGomuNoKongProjectile;
    }
    else if (GomuHelper.hasGearThirdActive(props)) {
      
      GomuGomuNoElephantGunProjectile gomuGomuNoElephantGunProjectile = new GomuGomuNoElephantGunProjectile(player.world, (LivingEntity)player);
      gomuGomuNoElephantGunProjectile.setCollisionSize(2.5D);
      if (!this.hasDataSet) {
        
        speed = 2.4F;
        projectileSpace = 9;
        this.hasDataSet = true;
      } 
      projDmageReduction = 0.6F;
      projectile = gomuGomuNoElephantGunProjectile;
    }
    else if (GomuHelper.hasGearSecondActive(props)) {
      
      GomuGomuNoJetPistolProjectile gomuGomuNoJetPistolProjectile = new GomuGomuNoJetPistolProjectile(player.world, (LivingEntity)player);
      if (!this.hasDataSet)
      {
        speed = 3.6F;
        this.hasDataSet = true;
      }
      projectile = gomuGomuNoJetPistolProjectile;
    } else {
      
      GomuGomuNoPistolProjectile gomuGomuNoPistolProjectile = new GomuGomuNoPistolProjectile(player.world, (LivingEntity)player);
      if (!this.hasDataSet)
      {
        this.hasDataSet = true;
      }
      projectile = gomuGomuNoPistolProjectile;
    } 
    
    projectile.setDamage(projectile.getDamage() * (1.0F - projDmageReduction));
    projectile.setChangeHurtTime(true);
    projectile.setMaxLife((int)(projectile.getMaxLife() * 0.75D));
    projectile.setLocationAndAngles(player
        .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
        .getPosY() + 1.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
        .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
    
    player.world.addEntity((Entity)projectile);
    projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 3.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.GOMU_SFX, SoundCategory.PLAYERS, 0.8F, (float)MathHelper.clamp(player.getRNG().nextDouble() * 2.0D, 1.0D, 1.2999999523162842D));
    
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket<IClientPlayNetHandler>)new SAnimateHandPacket((Entity)player, 0));
    
    return true;
  }

  
  private void duringCooldownEvent(PlayerEntity player, int cooldown) {
    if (cooldown <= 1)
    {
      this.hasDataSet = false;
    }
  }
}


