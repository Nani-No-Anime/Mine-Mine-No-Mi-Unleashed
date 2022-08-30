/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.BreezeBreathBombProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ public class GunItem
/*     */   extends Item
/*     */ {
/*  40 */   private int maxGunpowder = 3;
/*  41 */   private int bulletSpeed = 2;
/*  42 */   private int bulletAccuracy = 2;
/*  43 */   private int shotCooldown = 10;
/*  44 */   private int reloadCooldown = 20;
/*  45 */   private float damageMultiplier = 1.0F;
/*     */   public Predicate<ItemStack> bulletCheck;
/*     */   
/*     */   static {
/*  49 */     GUN_AMMO = (itemStack -> 
/*     */       
/*  51 */       (itemStack.getItem() == ModItems.BULLET || itemStack.getItem() == ModItems.KAIROSEKI_BULLET));
/*     */ 
/*     */     
/*  54 */     BAZOOKA_AMMO = (itemStack -> (itemStack.getItem() == ModItems.CANNON_BALL));
/*     */   }
/*     */   
/*     */   public static final Predicate<ItemStack> GUN_AMMO;
/*     */   public static final Predicate<ItemStack> BAZOOKA_AMMO;
/*     */   
/*     */   public GunItem(int maxDamage) {
/*  61 */     super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage));
/*  62 */     this.bulletCheck = GUN_AMMO;
/*     */   }
/*     */ 
/*     */   
/*     */   public GunItem(int maxDamage, Predicate<ItemStack> bulletPredicate) {
/*  67 */     super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage));
/*  68 */     this.bulletCheck = bulletPredicate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/*  74 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*  75 */     BreezeBreathBombAbility ability = (BreezeBreathBombAbility)abilityProps.getEquippedAbility((Ability)BreezeBreathBombAbility.INSTANCE);
/*  76 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*  77 */     boolean hasBullets = (findBulletStack(player) != null);
/*  78 */     Item bulletType = (findBulletStack(player) != null) ? findBulletStack(player).getItem() : null;
/*  79 */     ItemStack heldItemStack = player.getHeldItem(hand);
/*  80 */     boolean flag = (player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, heldItemStack) > 0);
/*     */     
/*  82 */     if (hasAbility) {
/*     */       
/*  84 */       player.setActiveHand(hand);
/*  85 */       return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
/*     */     } 
/*     */     
/*  88 */     if (flag) {
/*     */       
/*  90 */       player.setActiveHand(hand);
/*  91 */       return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
/*     */     } 
/*     */     
/*  94 */     if (!hasBullets || bulletType == null) {
/*  95 */       return new ActionResult(ActionResultType.FAIL, heldItemStack);
/*     */     }
/*  97 */     boolean hasGunPowder = (getLoadedGunPowder(heldItemStack) > 0);
/*     */     
/*  99 */     if (!hasGunPowder)
/*     */     {
/* 101 */       for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */         
/* 103 */         ItemStack stack = player.inventory.getStackInSlot(i);
/* 104 */         if (stack.getItem() == Items.GUNPOWDER) {
/*     */           
/* 106 */           int count = this.maxGunpowder;
/* 107 */           if (stack.getCount() < count) {
/* 108 */             count = stack.getCount();
/*     */           }
/* 110 */           setLoadedGunPowder(heldItemStack, count);
/* 111 */           player.inventory.decrStackSize(i, count);
/* 112 */           hasGunPowder = true;
/* 113 */           player.getCooldownTracker().setCooldown(this, this.reloadCooldown);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 119 */     if (!hasGunPowder) {
/* 120 */       return new ActionResult(ActionResultType.FAIL, heldItemStack);
/*     */     }
/* 122 */     player.setActiveHand(hand);
/* 123 */     return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
/* 129 */     if (!(entityLiving instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 132 */     PlayerEntity player = (PlayerEntity)entityLiving;
/* 133 */     Item bulletType = (findBulletStack(player) != null) ? findBulletStack(player).getItem() : null;
/* 134 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 135 */     BreezeBreathBombAbility ability = (BreezeBreathBombAbility)abilityProps.getEquippedAbility((Ability)BreezeBreathBombAbility.INSTANCE);
/* 136 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*     */     
/* 138 */     if (!player.world.isRemote && hasAbility) {
/*     */       
/* 140 */       ability.endContinuity(player);
/* 141 */       BreezeBreathBombProjectile proj = new BreezeBreathBombProjectile(player.world, (LivingEntity)player);
/* 142 */       player.world.addEntity((Entity)proj);
/* 143 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*     */       
/*     */       return;
/*     */     } 
/* 147 */     int powder = getLoadedGunPowder(itemStack);
/* 148 */     if (!world.isRemote) {
/*     */       AbilityProjectileEntity proj=null;
/* 150 */       boolean flag = (player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0);
/* 151 */       int m = getUseDuration(itemStack) - timeLeft;
/* 152 */       m = ForgeEventFactory.onArrowLoose(itemStack, world, player, m, (!itemStack.isEmpty() || flag));
/* 153 */       if (m < 0) {
/*     */         return;
/*     */       }
/*     */       
/* 158 */       if (bulletType == ModItems.BULLET) {
/* 159 */         proj = new NormalBulletProjectile(player.world, (LivingEntity)player);

/* 160 */       } else if (bulletType == ModItems.KAIROSEKI_BULLET) {
/* 161 */         proj= new KairosekiBulletProjectile(player.world, (LivingEntity)player);
/* 162 */       } else if (bulletType == ModItems.CANNON_BALL) {
/* 163 */         proj = new CannonBallProjectile(player.world, (LivingEntity)player);
/*     */       } 
/* 165 */       int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, itemStack);
/* 166 */       if (j > 0) {
/* 167 */         proj.setDamage((float)(proj.getDamage() + j * 0.5D + 0.5D));
/*     */       }
/* 169 */       int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, itemStack);
/* 170 */       if (k > 0) {
/* 171 */         proj.setKnockbackStrength(k);
/*     */       }
/* 173 */       if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0) {
/* 174 */         proj.setFire(3);
/*     */       }
/* 176 */       proj.setDamage(Math.round(proj.getDamage() * this.damageMultiplier));
/* 177 */       player.world.addEntity(proj);
/* 178 */       proj.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, this.bulletSpeed, this.bulletAccuracy);
/* 179 */       itemStack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(player.getActiveHand()));
/*     */ 
/*     */ 
/*     */       
/* 183 */       player.addStat(Stats.ITEM_USED.get(this));
/*     */     } 
/*     */     
/* 186 */     player.getCooldownTracker().setCooldown(this, this.shotCooldown);
/* 187 */     setLoadedGunPowder(itemStack, Math.max(0, --powder));
/* 188 */     for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */       
/* 190 */       ItemStack stack = player.inventory.getStackInSlot(i);
/* 191 */       if (this.bulletCheck.test(stack)) {
/*     */         
/* 193 */         player.inventory.decrStackSize(i, 1);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
/* 202 */     if (!itemStack.hasTag()) {
/*     */       
/* 204 */       itemStack.setTag(new CompoundNBT());
/* 205 */       itemStack.getTag().putInt("gunPowder", 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLoadedGunPowder(ItemStack itemStack, int powder) {
/* 211 */     itemStack.getTag().putInt("gunPowder", powder);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedGunPowder(ItemStack itemStack) {
/* 216 */     return itemStack.getTag().getInt("gunPowder");
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack findBulletStack(PlayerEntity player) {
/* 221 */     for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */       
/* 223 */       ItemStack stack = player.inventory.getStackInSlot(i);
/* 224 */       if (this.bulletCheck.test(stack))
/*     */       {
/* 226 */         return stack;
/*     */       }
/*     */     } 
/*     */     
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public GunItem setGunpowderLimit(int limit) {
/* 235 */     this.maxGunpowder = limit;
/* 236 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GunItem setDamageMultiplier(float damage) {
/* 241 */     this.damageMultiplier = damage;
/* 242 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GunItem setShotCooldown(int cd) {
/* 247 */     this.shotCooldown = cd;
/* 248 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GunItem setReloadCooldown(int cd) {
/* 253 */     this.reloadCooldown = cd;
/* 254 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GunItem setBulletAccuracy(int acc) {
/* 259 */     this.bulletAccuracy = acc;
/* 260 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GunItem setBulletSpeed(int speed) {
/* 265 */     this.bulletSpeed = speed;
/* 266 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUseDuration(ItemStack stack) {
/* 272 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UseAction getUseAction(ItemStack stack) {
/* 278 */     return UseAction.BOW;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/* 284 */     if (itemStack.hasTag())
/*     */     {
/* 286 */       list.add(new StringTextComponent("Gun Powder : " + itemStack.getTag().getInt("gunPowder")));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\GunItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */