/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GoldDenDenMushiItem
/*     */   extends Item
/*     */ {
/*     */   public GoldDenDenMushiItem() {
/*  36 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
/*  42 */     super.inventoryTick(stack, world, entity, itemSlot, isSelected);
/*     */     
/*  44 */     if (!stack.hasTag() || !stack.getTag().getBoolean("inUse")) {
/*     */       return;
/*     */     }
/*  47 */     CompoundNBT nbt = stack.getTag();
/*  48 */     nbt.putInt("countdown", nbt.getInt("countdown") - 1);
/*  49 */     int[] pos = nbt.getIntArray("coords");
/*     */     
/*  51 */     if (nbt.getInt("countdown") > 40 && nbt.getInt("countdown") < 180 && nbt.getInt("countdown") % 5 == 0)
/*     */     {
/*  53 */       for (int i = 0; i < 20; i++) {
/*     */         
/*  55 */         CannonBallProjectile projectile = new CannonBallProjectile(world, (LivingEntity)entity);
/*  56 */         projectile.setPosition(pos[0] + WyHelper.randomWithRange(-50, 50), (pos[1] + 100), pos[2] + WyHelper.randomWithRange(-50, 50));
/*  57 */         projectile.setDamage(50.0F);
/*  58 */         projectile.setMaxLife(60);
/*  59 */         world.addEntity((Entity)projectile);
/*  60 */         projectile.shoot(entity, 90.0F, 0.0F, 0.0F, 3.0F, 0.0F);
/*     */       } 
/*     */     }
/*     */     
/*  64 */     if (nbt.getInt("countdown") == 0) {
/*     */       
/*  66 */       stack.shrink(1);
/*     */       
/*  68 */       EntityType captainEntity = ModEntities.MARINE_CAPTAIN;
/*  69 */       EntityType grunt1Entity = ModEntities.MARINE_WITH_SWORD;
/*  70 */       EntityType grunt2Entity = ModEntities.MARINE_WITH_GUN;
/*     */       
/*  72 */       int nrCaptains = (int)WyHelper.randomWithRange(10, 20);
/*  73 */       int nrGrunts = (int)WyHelper.randomWithRange(100, 200);
/*     */       
/*     */       int i;
/*  76 */       for (i = 0; i < nrCaptains; i++) {
/*     */         
/*  78 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, captainEntity, new BlockPos(pos[0], pos[1], pos[2]), 50);
/*  79 */         if (spawnPos != null) {
/*  80 */           captainEntity.spawn(world, null, null, null, spawnPos, SpawnReason.EVENT, false, false);
/*     */         }
/*     */       } 
/*     */       
/*  84 */       for (i = 0; i < nrGrunts; i++) {
/*     */         
/*  86 */         EntityType gruntEntity = (i % 2 == 0) ? grunt1Entity : grunt2Entity;
/*     */         
/*  88 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, gruntEntity, new BlockPos(pos[0], pos[1], pos[2]), 50);
/*  89 */         if (spawnPos != null) {
/*  90 */           gruntEntity.spawn(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/*  98 */     if (!player.getHeldItem(hand).getOrCreateTag().getBoolean("inUse") && !world.isRemote) {
/*     */       
/* 100 */       CompoundNBT compoundNBT = player.getHeldItem(hand).getOrCreateTag();
/* 101 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */       
/* 103 */       if (!props.hasMarineRank(FactionHelper.MarineRank.ADMIRAL)) {
/*     */         
/* 105 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_REQUIREMENT, new Object[0]));
/* 106 */         return ActionResult.resultFail(player.getHeldItem(hand));
/*     */       } 
/*     */       
/* 109 */       compoundNBT.putInt("countdown", 1000);
/* 110 */       compoundNBT.putBoolean("inUse", true);
/* 111 */       compoundNBT.putIntArray("coords", new int[] { player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ() });
/* 112 */       world.getPlayers().stream().filter(target -> EntityStatsCapability.get((LivingEntity)target).isMarine()).forEach(target -> target.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_LAUNCHED, new Object[] { Long.valueOf(Math.round(player.getPosX())), Long.valueOf(Math.round(player.getPosZ())), player.getDisplayName().getFormattedText() })));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     return ActionResult.resultSuccess(player.getHeldItem(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> lore, ITooltipFlag tooltip) {
/* 124 */     super.addInformation(stack, world, lore, tooltip);
/* 125 */     lore.add(new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_INFO, new Object[0]));
/* 126 */     if (stack.getOrCreateTag().contains("countdown")) {
/*     */       
/* 128 */       int t = stack.getOrCreateTag().getInt("countdown") / 20;
/* 129 */       lore.add(new StringTextComponent("§4Countdown: " + t + " seconds§r"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\GoldDenDenMushiItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */