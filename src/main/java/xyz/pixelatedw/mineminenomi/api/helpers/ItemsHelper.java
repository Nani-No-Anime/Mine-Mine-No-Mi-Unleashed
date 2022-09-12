package xyz.pixelatedw.mineminenomi.api.helpers;

import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTUtil;

import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class ItemsHelper
{
  private static final String[] WANTED_POSTER_BACKGROUNDS = new String[] { "forest1", "forest2", "jungle1", "jungle2", "hills1", "hills2", "hills3", "plains1", "plains2", "plains3", "taiga1", "taiga2" };

  
  public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

  
  public static void itemBreakParticles(LivingEntity entity, int count, ItemStack stack) {
    for (int i = 0; i < count; i++) {
      
      Vec3d vec3d = new Vec3d((entity.getRNG().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
      vec3d = vec3d.rotatePitch(-entity.rotationPitch * 0.017453292F);
      vec3d = vec3d.rotateYaw(-entity.rotationYaw * 0.017453292F);
      double d0 = -entity.getRNG().nextFloat() * 0.6D - 0.3D;
      Vec3d vec3d1 = new Vec3d((entity.getRNG().nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
      vec3d1 = vec3d1.rotatePitch(-entity.rotationPitch * 0.017453292F);
      vec3d1 = vec3d1.rotateYaw(-entity.rotationYaw * 0.017453292F);
      vec3d1 = vec3d1.add(entity.getPosX(), entity.getPosYEye(), entity.getPosZ());
      if (entity.world instanceof ServerWorld) {
        ((ServerWorld)entity.world).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, stack), vec3d1.x, vec3d1.y, vec3d1.z, 1, vec3d.x, vec3d.y + 0.05D, vec3d.z, 0.2D);
      } else {
        entity.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, stack), vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z);
      } 
    } 
  }


  
  public static void updateEncyclopediae(PlayerEntity player, String fruit, DFEncyclopediaEntry entry) {
    ArrayList<ItemStack> slots = new ArrayList<>();
    slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
    slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
    slots.stream()
      .filter(stack -> (stack.getItem() == ModItems.DEVIL_FRUIT_ENCYCLOPEDIA))
      .forEach(stack -> DFEncyclopediaItem.addFruitClues(stack, fruit, entry));
  }



  
  public static void dropWantedPosters(World world, int posX, int posY, int posZ) {
    ExtendedWorldData worldData = ExtendedWorldData.get(world);
    
    if (worldData.getAllBounties().size() <= 0) {
      return;
    }
    List<Map.Entry<String, Long>> bountiesInPackage = new ArrayList<>();
    
    Predicate<PlayerEntity> hasBounty = player -> (player != null && player.isAlive() && BountyHelper.canGainBounty(player) && worldData.getBounty(player.getUniqueID().toString().toLowerCase()) != 0L);
    
    WyHelper.getEntitiesNear(new BlockPos(posX, posY, posZ), world, 10.0D, PlayerEntity.class).stream().filter(hasBounty).limit(5L).forEach(player -> {
          AbstractMap.SimpleEntry<String, Long> se = new AbstractMap.SimpleEntry<>(player.getUniqueID().toString(), Long.valueOf(worldData.getBounty(player.getUniqueID().toString().toLowerCase())));
          
          bountiesInPackage.add(se);
        });
    
    if (bountiesInPackage.size() < 5) {
      bountiesInPackage.addAll((Collection<? extends Map.Entry<String, Long>>)worldData.getAllBounties().entrySet().stream().filter(entry -> !bountiesInPackage.contains(entry)).limit(3L).collect(Collectors.toList()));
    }
    
    bountiesInPackage.stream().forEach(entry -> {
          ItemStack stack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER);
          CompoundNBT data = setWantedData(world, (String)entry.getKey(), ((Long)entry.getValue()).longValue());
          if (!data.isEmpty()) {
            stack.setTag(data);
            world.addEntity((Entity)new ItemEntity(world, posX, (posY + 1), posZ, stack));
          } 
        });
  }



  
  public static CompoundNBT setWantedData(World world, String id, long bounty) {
    CompoundNBT data = new CompoundNBT();
    
    PlayerEntity player = world.getPlayerByUuid(UUID.fromString(id));
    
    if (player == null) {
      return data;
    }
    data.putString("UUID", id);
    data.putString("Name", player.getName().getFormattedText());
    data.putLong("Bounty", bounty);
    data.putString("Faction", EntityStatsCapability.get((LivingEntity)player).getFaction());
    int randomBg = (int)WyHelper.randomWithRange(0, WANTED_POSTER_BACKGROUNDS.length - 1);
    data.putString("Background", WANTED_POSTER_BACKGROUNDS[randomBg]);
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = format.format(new Date());
    
    data.putString("Date", dateString);
    
    CompoundNBT compoundnbt = new CompoundNBT();
    NBTUtil.writeGameProfile(compoundnbt, player.getGameProfile());
    data.put("Owner", (INBT)compoundnbt);
    
    return data;
  }

  
  public static boolean isBow(ItemStack itemStack) {
    if (itemStack.isEmpty()) {
      return false;
    }
    if (itemStack.getUseAction() == UseAction.BOW) {
      return true;
    }
    if (itemStack.getItem() instanceof net.minecraft.item.ShootableItem) {
      return true;
    }
    return false;
  }

  
  public static boolean isSword(@Nullable ItemStack itemStack) {
    if (itemStack == null || itemStack.isEmpty()) {
      return false;
    }
    if (itemStack.getItem() instanceof CoreSwordItem && !((CoreSwordItem)itemStack.getItem()).isBlunt()) {
      return true;
    }
    if (itemStack.getItem() instanceof net.minecraft.item.SwordItem) {
      return true;
    }
    return false;
  }

  
  public static boolean isClimaTact(ItemStack itemStack) {
    if (itemStack.isEmpty()) {
      return false;
    }
    if (itemStack.getItem() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem) {
      return true;
    }
    return false;
  }

  
  public static boolean hasKairosekiItem(LivingEntity entity) {
    Iterable<ItemStack> iter = entity.getEquipmentAndArmor();
    if (entity instanceof PlayerEntity) {
      iter = Iterables.concat((Iterable)((PlayerEntity)entity).inventory.mainInventory, entity.getEquipmentAndArmor());
    }
    for (ItemStack stack : iter) {
      
      if (!stack.isEmpty() && ModTags.Items.KAIROSEKI.contains(stack.getItem()))
        return true; 
    } 
    return false;
  }

  
  public static boolean isKairosekiWeapon(ItemStack heldItem) {
    if (heldItem != null)
      return ((heldItem.isEnchanted() && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, heldItem) > 0) || heldItem.getItem() == ModWeapons.JITTE); 
    return false;
  }

  
  public static float getSniperInaccuracy(float inaccuracy, PlayerEntity player) {
    if (EntityStatsCapability.get((LivingEntity)player).isSniper())
    {
      if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModArmors.SNIPER_GOGGLES) {
        
        IAbilityData aprops = AbilityDataCapability.get((LivingEntity)player);
        ZoomAbility ability = (ZoomAbility)aprops.getEquippedAbility((Ability)ZoomAbility.INSTANCE);
        boolean isActive = (ability != null && ability.isContinuous());
        
        if (isActive) {
          return inaccuracy / 4.0F;
        }
      } else {
        return inaccuracy / 2.0F;
      } 
    }
    return inaccuracy;
  }

  
  public static float getItemDamage(ItemStack stack) {
    Multimap<String, AttributeModifier> multimap = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
    double modifier = EnchantmentHelper.getModifierForCreature(stack, CreatureAttribute.UNDEFINED);
    
    for (Map.Entry<String, AttributeModifier> entry : (Iterable<Map.Entry<String, AttributeModifier>>)multimap.entries()) {
      
      AttributeModifier attr = entry.getValue();
      if (attr.getID().equals(ATTACK_DAMAGE_MODIFIER)) {
        
        double damage = attr.getAmount() + modifier + 1.0D;
        return (float)damage;
      } 
    } 
    
    return -1.0F;
  }
}


