package xyz.pixelatedw.mineminenomi.abilities.jiki;

import com.google.common.collect.Iterables;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.particles.effects.jiki.AttractParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AttractAbility extends Ability {
  public static final AttractAbility INSTANCE = new AttractAbility();
  
  public static final AttractParticleEffect PARTICLES = new AttractParticleEffect();
  
  private static final int RADIUS = 40;

  
  public AttractAbility() {
    super("Attract", AbilityHelper.getDevilFruitCategory());
    setDescription("§2Range:§r 40 blocks\nAttracts all nearby magnetic objects from the ground or enemy inventories");

    
    setMaxCooldown(3.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  public boolean onUseEvent(PlayerEntity player) {
    List<Entity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { MobEntity.class, PlayerEntity.class });
    targets.remove(player);
    
    for (Entity target : targets) {
      
      Iterable<ItemStack> iter = target.getEquipmentAndArmor();
      if (target instanceof PlayerEntity) {
        
        PlayerInventory playerInv = ((PlayerEntity)target).inventory;
        iter = Iterables.concat((Iterable)playerInv.mainInventory, (Iterable)playerInv.armorInventory, (Iterable)playerInv.offHandInventory);
      } 
      
      for (ItemStack stack : iter) {
        
        if (!stack.isEmpty() && stack.getItem().isIn(ModTags.Items.MAGNETIC)) {
          
          if (target instanceof MobEntity) {
            
            EquipmentSlotType slot = MobEntity.getSlotForItemStack(stack);
            ((MobEntity)target).setItemStackToSlot(slot, ItemStack.EMPTY);
          } 
          
          if (target instanceof PlayerEntity)
          {
            ((PlayerEntity)target).inventory.deleteStack(stack);
          }
          
          ItemEntity item = target.entityDropItem(stack, 1.0F);
          item.setPickupDelay(30);
        } 
      } 
    } 
    
    List<ItemEntity> items = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40.0D, new Class[] { ItemEntity.class });
    
    for (ItemEntity item : items) {
      
      if (item.getItem().isEmpty() || !item.getItem().getItem().isIn(ModTags.Items.MAGNETIC))
        continue; 
      Vec3d vec = item.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -3.0D, 0.0D);
      double speedReduction = 8.0D;
      double xSpeed = -vec.x / speedReduction;
      double ySpeed = -vec.y / speedReduction;
      double zSpeed = -vec.z / speedReduction;
      item.setMotion(xSpeed, ySpeed, zSpeed);
      item.velocityChanged = true;
      double dist = Math.sqrt(player.getPosition().distanceSq((Vec3i)item.getPosition()));
      int delay = (int)Math.max(5.0D, dist / 3.0D);
      item.setPickupDelay(delay);
      
      PARTICLES.spawn(player.world, item.getPosX(), item.getPosY(), item.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
    
    return true;
  }
}


