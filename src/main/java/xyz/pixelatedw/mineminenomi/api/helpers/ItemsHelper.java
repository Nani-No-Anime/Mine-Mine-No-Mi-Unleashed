/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.CreatureAttribute;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ItemParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class ItemsHelper
/*     */ {
/*  56 */   private static final String[] WANTED_POSTER_BACKGROUNDS = new String[] { "forest1", "forest2", "jungle1", "jungle2", "hills1", "hills2", "hills3", "plains1", "plains2", "plains3", "taiga1", "taiga2" };
/*     */ 
/*     */   
/*  59 */   public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
/*     */ 
/*     */   
/*     */   public static void itemBreakParticles(LivingEntity entity, int count, ItemStack stack) {
/*  63 */     for (int i = 0; i < count; i++) {
/*     */       
/*  65 */       Vec3d vec3d = new Vec3d((entity.getRNG().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/*  66 */       vec3d = vec3d.rotatePitch(-entity.rotationPitch * 0.017453292F);
/*  67 */       vec3d = vec3d.rotateYaw(-entity.rotationYaw * 0.017453292F);
/*  68 */       double d0 = -entity.getRNG().nextFloat() * 0.6D - 0.3D;
/*  69 */       Vec3d vec3d1 = new Vec3d((entity.getRNG().nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
/*  70 */       vec3d1 = vec3d1.rotatePitch(-entity.rotationPitch * 0.017453292F);
/*  71 */       vec3d1 = vec3d1.rotateYaw(-entity.rotationYaw * 0.017453292F);
/*  72 */       vec3d1 = vec3d1.add(entity.getPosX(), entity.getPosYEye(), entity.getPosZ());
/*  73 */       if (entity.world instanceof ServerWorld) {
/*  74 */         ((ServerWorld)entity.world).spawnParticle((IParticleData)new ItemParticleData(ParticleTypes.ITEM, stack), vec3d1.x, vec3d1.y, vec3d1.z, 1, vec3d.x, vec3d.y + 0.05D, vec3d.z, 0.2D);
/*     */       } else {
/*  76 */         entity.world.addParticle((IParticleData)new ItemParticleData(ParticleTypes.ITEM, stack), vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateEncyclopediae(PlayerEntity player, String fruit, DFEncyclopediaEntry entry) {
/*  84 */     ArrayList<ItemStack> slots = new ArrayList<>();
/*  85 */     slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
/*  86 */     slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
/*  87 */     slots.stream()
/*  88 */       .filter(stack -> (stack.getItem() == ModItems.DEVIL_FRUIT_ENCYCLOPEDIA))
/*  89 */       .forEach(stack -> DFEncyclopediaItem.addFruitClues(stack, fruit, entry));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dropWantedPosters(World world, int posX, int posY, int posZ) {
/*  96 */     ExtendedWorldData worldData = ExtendedWorldData.get(world);
/*     */     
/*  98 */     if (worldData.getAllBounties().size() <= 0) {
/*     */       return;
/*     */     }
/* 101 */     List<Map.Entry<String, Long>> bountiesInPackage = new ArrayList<>();
/*     */     
/* 103 */     Predicate<PlayerEntity> hasBounty = player -> (player != null && player.isAlive() && BountyHelper.canGainBounty(player) && worldData.getBounty(player.getUniqueID().toString().toLowerCase()) != 0L);
/*     */     
/* 105 */     WyHelper.getEntitiesNear(new BlockPos(posX, posY, posZ), world, 10.0D, PlayerEntity.class).stream().filter(hasBounty).limit(5L).forEach(player -> {
/*     */           AbstractMap.SimpleEntry<String, Long> se = new AbstractMap.SimpleEntry<>(player.getUniqueID().toString(), Long.valueOf(worldData.getBounty(player.getUniqueID().toString().toLowerCase())));
/*     */           
/*     */           bountiesInPackage.add(se);
/*     */         });
/*     */     
/* 111 */     if (bountiesInPackage.size() < 5) {
/* 112 */       bountiesInPackage.addAll((Collection<? extends Map.Entry<String, Long>>)worldData.getAllBounties().entrySet().stream().filter(entry -> !bountiesInPackage.contains(entry)).limit(3L).collect(Collectors.toList()));
/*     */     }
/*     */     
/* 115 */     bountiesInPackage.stream().forEach(entry -> {
/*     */           ItemStack stack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER);
/*     */           CompoundNBT data = setWantedData(world, (String)entry.getKey(), ((Long)entry.getValue()).longValue());
/*     */           if (!data.isEmpty()) {
/*     */             stack.setTag(data);
/*     */             world.addEntity((Entity)new ItemEntity(world, posX, (posY + 1), posZ, stack));
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompoundNBT setWantedData(World world, String id, long bounty) {
/* 129 */     CompoundNBT data = new CompoundNBT();
/*     */     
/* 131 */     PlayerEntity player = world.getPlayerByUuid(UUID.fromString(id));
/*     */     
/* 133 */     if (player == null) {
/* 134 */       return data;
/*     */     }
/* 136 */     data.putString("UUID", id);
/* 137 */     data.putString("Name", player.getName().getFormattedText());
/* 138 */     data.putLong("Bounty", bounty);
/* 139 */     data.putString("Faction", EntityStatsCapability.get((LivingEntity)player).getFaction());
/* 140 */     int randomBg = (int)WyHelper.randomWithRange(0, WANTED_POSTER_BACKGROUNDS.length - 1);
/* 141 */     data.putString("Background", WANTED_POSTER_BACKGROUNDS[randomBg]);
/*     */     
/* 143 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 144 */     String dateString = format.format(new Date());
/*     */     
/* 146 */     data.putString("Date", dateString);
/*     */     
/* 148 */     CompoundNBT compoundnbt = new CompoundNBT();
/* 149 */     NBTUtil.writeGameProfile(compoundnbt, player.getGameProfile());
/* 150 */     data.put("Owner", (INBT)compoundnbt);
/*     */     
/* 152 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBow(ItemStack itemStack) {
/* 157 */     if (itemStack.isEmpty()) {
/* 158 */       return false;
/*     */     }
/* 160 */     if (itemStack.getUseAction() == UseAction.BOW) {
/* 161 */       return true;
/*     */     }
/* 163 */     if (itemStack.getItem() instanceof net.minecraft.item.ShootableItem) {
/* 164 */       return true;
/*     */     }
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSword(@Nullable ItemStack itemStack) {
/* 171 */     if (itemStack == null || itemStack.isEmpty()) {
/* 172 */       return false;
/*     */     }
/* 174 */     if (itemStack.getItem() instanceof CoreSwordItem && !((CoreSwordItem)itemStack.getItem()).isBlunt()) {
/* 175 */       return true;
/*     */     }
/* 177 */     if (itemStack.getItem() instanceof net.minecraft.item.SwordItem) {
/* 178 */       return true;
/*     */     }
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isClimaTact(ItemStack itemStack) {
/* 185 */     if (itemStack.isEmpty()) {
/* 186 */       return false;
/*     */     }
/* 188 */     if (itemStack.getItem() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem) {
/* 189 */       return true;
/*     */     }
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasKairosekiItem(LivingEntity entity) {
/* 196 */     Iterable<ItemStack> iter = entity.getEquipmentAndArmor();
/* 197 */     if (entity instanceof PlayerEntity) {
/* 198 */       iter = Iterables.concat((Iterable)((PlayerEntity)entity).inventory.mainInventory, entity.getEquipmentAndArmor());
/*     */     }
/* 200 */     for (ItemStack stack : iter) {
/*     */       
/* 202 */       if (!stack.isEmpty() && ModTags.Items.KAIROSEKI.contains(stack.getItem()))
/* 203 */         return true; 
/*     */     } 
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isKairosekiWeapon(ItemStack heldItem) {
/* 210 */     if (heldItem != null)
/* 211 */       return ((heldItem.isEnchanted() && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, heldItem) > 0) || heldItem.getItem() == ModWeapons.JITTE); 
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getSniperInaccuracy(float inaccuracy, PlayerEntity player) {
/* 217 */     if (EntityStatsCapability.get((LivingEntity)player).isSniper())
/*     */     {
/* 219 */       if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModArmors.SNIPER_GOGGLES) {
/*     */         
/* 221 */         IAbilityData aprops = AbilityDataCapability.get((LivingEntity)player);
/* 222 */         ZoomAbility ability = (ZoomAbility)aprops.getEquippedAbility((Ability)ZoomAbility.INSTANCE);
/* 223 */         boolean isActive = (ability != null && ability.isContinuous());
/*     */         
/* 225 */         if (isActive) {
/* 226 */           return inaccuracy / 4.0F;
/*     */         }
/*     */       } else {
/* 229 */         return inaccuracy / 2.0F;
/*     */       } 
/*     */     }
/* 232 */     return inaccuracy;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getItemDamage(ItemStack stack) {
/* 237 */     Multimap<String, AttributeModifier> multimap = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
/* 238 */     double modifier = EnchantmentHelper.getModifierForCreature(stack, CreatureAttribute.UNDEFINED);
/*     */     
/* 240 */     for (Map.Entry<String, AttributeModifier> entry : (Iterable<Map.Entry<String, AttributeModifier>>)multimap.entries()) {
/*     */       
/* 242 */       AttributeModifier attr = entry.getValue();
/* 243 */       if (attr.getID().equals(ATTACK_DAMAGE_MODIFIER)) {
/*     */         
/* 245 */         double damage = attr.getAmount() + modifier + 1.0D;
/* 246 */         return (float)damage;
/*     */       } 
/*     */     } 
/*     */     
/* 250 */     return -1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\ItemsHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */