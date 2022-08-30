/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kage.KageGiriAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class ScissorsItem extends CoreSwordItem {
/*     */   public ScissorsItem() {
/*  26 */     super(5, -2.8F, 500);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
/*  32 */     if (!(attacker instanceof PlayerEntity)) {
/*  33 */       return super.hitEntity(itemStack, target, attacker);
/*     */     }
/*  35 */     KageGiriAbility passive = (KageGiriAbility)AbilityDataCapability.get(attacker).getUnlockedAbility(KageGiriAbility.INSTANCE);
/*  36 */     boolean hasPassive = (passive != null && !passive.isPaused());
/*     */     
/*  38 */     IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */     
/*  40 */     if (!hasPassive || itemStack == null || itemStack.getItem() != ModWeapons.SCISSORS || !targetProps.hasShadow()) {
/*  41 */       return super.hitEntity(itemStack, target, attacker);
/*     */     }
/*  43 */     targetProps.setShadow(false);
/*  44 */     ((PlayerEntity)attacker).inventory.addItemStackToInventory(new ItemStack((IItemProvider)ModItems.SHADOW));
/*  45 */     WyNetwork.sendToAllAround(new SSyncEntityStatsPacket(target.getEntityId(), targetProps), (Entity)target);
/*     */     
/*  47 */     return super.hitEntity(itemStack, target, attacker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entityLiving) {
/*  55 */     if (!world.isRemote)
/*     */     {
/*  57 */       stack.damageItem(1, entityLiving, entity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     Block block = state.getBlock();
/*  64 */     return (!state.isIn(BlockTags.LEAVES) && block != Blocks.COBWEB && block != Blocks.GRASS && block != Blocks.FERN && block != Blocks.DEAD_BUSH && block != Blocks.VINE && block != Blocks.TRIPWIRE && !block.isIn(BlockTags.WOOL)) ? super.onBlockDestroyed(stack, world, state, pos, entityLiving) : true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHarvestBlock(BlockState blockIn) {
/*  70 */     Block block = blockIn.getBlock();
/*  71 */     return (block == Blocks.COBWEB || block == Blocks.REDSTONE_WIRE || block == Blocks.TRIPWIRE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDestroySpeed(ItemStack stack, BlockState state) {
/*  77 */     Block block = state.getBlock();
/*  78 */     if (block != Blocks.COBWEB && !state.isIn(BlockTags.LEAVES)) {
/*  79 */       return block.isIn(BlockTags.WOOL) ? 5.0F : super.getDestroySpeed(stack, state);
/*     */     }
/*  81 */     return 15.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
/*  87 */     if (entity.world.isRemote)
/*  88 */       return false; 
/*  89 */     if (entity instanceof IShearable) {
/*     */       
/*  91 */       IShearable target = (IShearable)entity;
/*  92 */       BlockPos pos = new BlockPos(entity.getPosX(), entity.getPosY(), entity.getPosZ());
/*  93 */       if (target.isShearable(stack, (IWorldReader)entity.world, pos)) {
/*     */         
/*  95 */         List<ItemStack> drops = target.onSheared(stack, entity.world, pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));
/*  96 */         Random rand = new Random();
/*  97 */         drops.forEach(d -> {
/*     */               ItemEntity ent = entity.entityDropItem(d, 1.0F);
/*     */               
/*     */               ent.setMotion(ent.getMotion().add(((rand.nextFloat() - rand.nextFloat()) * 0.1F), (rand.nextFloat() * 0.05F), ((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
/*     */             });
/* 102 */         stack.damageItem(1, entity, e -> e.sendBreakAnimation(hand));
/*     */       } 
/* 104 */       return true;
/*     */     } 
/* 106 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\ScissorsItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */