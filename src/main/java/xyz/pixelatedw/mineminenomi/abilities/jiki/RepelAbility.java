package xyz.pixelatedw.mineminenomi.abilities.jiki;


import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.PunkGibsonProjectile;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class RepelAbility extends ContinuousAbility {
  public static final RepelAbility INSTANCE = new RepelAbility();
  
  private static final int RADIUS = 5;

  
  public RepelAbility() {
    super("Repel", AbilityHelper.getDevilFruitCategory());
    setDescription("§2Range:§r 5 blocks\nRepels all metallic objects or projectiles near the user");

    
    setThreshold(5.0D);
    setMaxCooldown(5.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    PunkGibsonAbility ability = (PunkGibsonAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)PunkGibsonAbility.INSTANCE);
    boolean isGibsonActive = (ability != null && ability.isContinuous());
    
    if (isGibsonActive) {
      
      PunkGibsonProjectile proj = new PunkGibsonProjectile(player.world, (LivingEntity)player, ability.getMagneticItems());
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 1.0F);
      ability.stopItemDrops();
      ability.endContinuity(player);
      endContinuity(player);
      return false;
    } 
    
    return true;
  }

  
  public void duringContinuityEvent(PlayerEntity player, int continuousTime) {
    List<Entity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5.0D, new Class[] { Entity.class });
    targets.remove(player);
    for (Entity target : targets) {
      
      boolean flag = false;
      
      if (target.getType().isContained(ModTags.Entities.MAGNETIC)) {
        flag = true;
      }
      if (target instanceof LivingEntity && (getIronArmorCoverPercentage((LivingEntity)target) >= 0.5F || EntityStatsCapability.get((LivingEntity)target).isCyborg())) {
        flag = true;
      }
      if (flag) {
        
        Vec3d dist = target.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D);
        double speedReduction = 5.0D;
        double minSpeed = 3.0D;
        double xSpeed = Math.min(minSpeed, -dist.x / speedReduction);
        double ySpeed = Math.min(minSpeed, -dist.y / speedReduction);
        double zSpeed = Math.min(minSpeed, -dist.z / speedReduction);
        target.setMotion(-xSpeed, ySpeed, -zSpeed);
        target.velocityChanged = true;
      } 
    } 

    
    List<ItemEntity> items = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5.0D, new Class[] { ItemEntity.class });
    for (ItemEntity item : items) {
      
      if (item.getItem().isEmpty() || !item.getItem().getItem().isIn(ModTags.Items.MAGNETIC))
        continue; 
      Vec3d vec = item.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D);
      double speedReduction = 8.0D;
      double speed = 2.0D;
      double xSpeed = Math.min(speed, -vec.x / speedReduction);
      double zSpeed = Math.min(speed, -vec.z / speedReduction);
      item.setMotion(-xSpeed, 0.1D, -zSpeed);
      item.velocityChanged = true;
    } 
  }

  
  public float getIronArmorCoverPercentage(LivingEntity target) {
    Iterable<ItemStack> iterable = target.getArmorInventoryList();
    int i = 0;
    int j = 0;
    
    for (ItemStack itemstack : iterable) {
      
      if (!itemstack.isEmpty() && itemstack.getItem().isIn(ModTags.Items.MAGNETIC))
      {
        j++;
      }
      
      i++;
    } 
    
    return (i > 0) ? (j / i) : 0.0F;
  }
}


