package xyz.pixelatedw.mineminenomi.items.weapons;

import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.BreezeBreathBombProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


public class GunItem
  extends Item
{
  private int maxGunpowder = 3;
  private int bulletSpeed = 2;
  private int bulletAccuracy = 2;
  private int shotCooldown = 10;
  private int reloadCooldown = 20;
  private float damageMultiplier = 1.0F;
  public Predicate<ItemStack> bulletCheck;
  
  static {
    GUN_AMMO = (itemStack -> 
      
      (itemStack.getItem() == ModItems.BULLET || itemStack.getItem() == ModItems.KAIROSEKI_BULLET));

    
    BAZOOKA_AMMO = (itemStack -> (itemStack.getItem() == ModItems.CANNON_BALL));
  }
  
  public static final Predicate<ItemStack> GUN_AMMO;
  public static final Predicate<ItemStack> BAZOOKA_AMMO;
  
  public GunItem(int maxDamage) {
    super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage));
    this.bulletCheck = GUN_AMMO;
  }

  
  public GunItem(int maxDamage, Predicate<ItemStack> bulletPredicate) {
    super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage));
    this.bulletCheck = bulletPredicate;
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    BreezeBreathBombAbility ability = (BreezeBreathBombAbility)abilityProps.getEquippedAbility((Ability)BreezeBreathBombAbility.INSTANCE);
    boolean hasAbility = (ability != null && ability.isContinuous());
    boolean hasBullets = (findBulletStack(player) != null);
    Item bulletType = (findBulletStack(player) != null) ? findBulletStack(player).getItem() : null;
    ItemStack heldItemStack = player.getHeldItem(hand);
    boolean flag = (player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, heldItemStack) > 0);
    
    if (hasAbility) {
      
      player.setActiveHand(hand);
      return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
    } 
    
    if (flag) {
      
      player.setActiveHand(hand);
      return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
    } 
    
    if (!hasBullets || bulletType == null) {
      return new ActionResult(ActionResultType.FAIL, heldItemStack);
    }
    boolean hasGunPowder = (getLoadedGunPowder(heldItemStack) > 0);
    
    if (!hasGunPowder)
    {
      for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
        
        ItemStack stack = player.inventory.getStackInSlot(i);
        if (stack.getItem() == Items.GUNPOWDER) {
          
          int count = this.maxGunpowder;
          if (stack.getCount() < count) {
            count = stack.getCount();
          }
          setLoadedGunPowder(heldItemStack, count);
          player.inventory.decrStackSize(i, count);
          hasGunPowder = true;
          player.getCooldownTracker().setCooldown(this, this.reloadCooldown);
          
          break;
        } 
      } 
    }
    if (!hasGunPowder) {
      return new ActionResult(ActionResultType.FAIL, heldItemStack);
    }
    player.setActiveHand(hand);
    return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
  }


  
  public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
    if (!(entityLiving instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)entityLiving;
    Item bulletType = (findBulletStack(player) != null) ? findBulletStack(player).getItem() : null;
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    BreezeBreathBombAbility ability = (BreezeBreathBombAbility)abilityProps.getEquippedAbility((Ability)BreezeBreathBombAbility.INSTANCE);
    boolean hasAbility = (ability != null && ability.isContinuous());
    
    if (!player.world.isRemote && hasAbility) {
      
      ability.endContinuity(player);
      BreezeBreathBombProjectile proj = new BreezeBreathBombProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
      
      return;
    } 
    int powder = getLoadedGunPowder(itemStack);
    if (!world.isRemote) {
      AbilityProjectileEntity proj=null;
      boolean flag = (player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0);
      int m = getUseDuration(itemStack) - timeLeft;
      m = ForgeEventFactory.onArrowLoose(itemStack, world, player, m, (!itemStack.isEmpty() || flag));
      if (m < 0) {
        return;
      }
      
      if (bulletType == ModItems.BULLET) {
        proj = new NormalBulletProjectile(player.world, (LivingEntity)player);

      } else if (bulletType == ModItems.KAIROSEKI_BULLET) {
        proj= new KairosekiBulletProjectile(player.world, (LivingEntity)player);
      } else if (bulletType == ModItems.CANNON_BALL) {
        proj = new CannonBallProjectile(player.world, (LivingEntity)player);
      } 
      int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, itemStack);
      if (j > 0) {
        proj.setDamage((float)(proj.getDamage() + j * 0.5D + 0.5D));
      }
      int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, itemStack);
      if (k > 0) {
        proj.setKnockbackStrength(k);
      }
      if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0) {
        proj.setFire(3);
      }
      proj.setDamage(Math.round(proj.getDamage() * this.damageMultiplier));
      player.world.addEntity(proj);
      proj.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, this.bulletSpeed, this.bulletAccuracy);
      itemStack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(player.getActiveHand()));


      
      player.addStat(Stats.ITEM_USED.get(this));
    } 
    
    player.getCooldownTracker().setCooldown(this, this.shotCooldown);
    setLoadedGunPowder(itemStack, Math.max(0, --powder));
    for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
      
      ItemStack stack = player.inventory.getStackInSlot(i);
      if (this.bulletCheck.test(stack)) {
        
        player.inventory.decrStackSize(i, 1);
        break;
      } 
    } 
  }


  
  public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
    if (!itemStack.hasTag()) {
      
      itemStack.setTag(new CompoundNBT());
      itemStack.getTag().putInt("gunPowder", 0);
    } 
  }

  
  public void setLoadedGunPowder(ItemStack itemStack, int powder) {
    itemStack.getTag().putInt("gunPowder", powder);
  }

  
  public int getLoadedGunPowder(ItemStack itemStack) {
    return itemStack.getTag().getInt("gunPowder");
  }

  
  public ItemStack findBulletStack(PlayerEntity player) {
    for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
      
      ItemStack stack = player.inventory.getStackInSlot(i);
      if (this.bulletCheck.test(stack))
      {
        return stack;
      }
    } 
    
    return null;
  }

  
  public GunItem setGunpowderLimit(int limit) {
    this.maxGunpowder = limit;
    return this;
  }

  
  public GunItem setDamageMultiplier(float damage) {
    this.damageMultiplier = damage;
    return this;
  }

  
  public GunItem setShotCooldown(int cd) {
    this.shotCooldown = cd;
    return this;
  }

  
  public GunItem setReloadCooldown(int cd) {
    this.reloadCooldown = cd;
    return this;
  }

  
  public GunItem setBulletAccuracy(int acc) {
    this.bulletAccuracy = acc;
    return this;
  }

  
  public GunItem setBulletSpeed(int speed) {
    this.bulletSpeed = speed;
    return this;
  }


  
  public int getUseDuration(ItemStack stack) {
    return 72000;
  }


  
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.BOW;
  }


  
  public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
    if (itemStack.hasTag())
    {
      list.add(new StringTextComponent("Gun Powder : " + itemStack.getTag().getInt("gunPowder")));
    }
  }
}


