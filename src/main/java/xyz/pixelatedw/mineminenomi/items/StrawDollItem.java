/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.IDyeableArmorItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class StrawDollItem
/*     */   extends Item
/*     */   implements IDyeableArmorItem
/*     */ {
/*     */   public StrawDollItem() {
/*  32 */     super((new Item.Properties()).maxStackSize(1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/*  38 */     if (world.isRemote) {
/*  39 */       return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */     }
/*  41 */     ItemStack itemStack = player.getHeldItem(hand);
/*     */     
/*  43 */     if (getOwner(world, player.getPosition(), itemStack) == null) {
/*  44 */       player.inventory.deleteStack(itemStack);
/*     */     }
/*  46 */     LivingEntity owner = getOwner(world, player.getPosition(), itemStack);
/*     */     
/*  48 */     if (owner == null) {
/*     */       
/*  50 */       player.inventory.deleteStack(itemStack);
/*  51 */       return ActionResult.resultSuccess(itemStack);
/*     */     } 
/*     */     
/*  54 */     IEntityStats props = EntityStatsCapability.get(owner);
/*     */     
/*  56 */     if (props.hasStrawDoll()) {
/*     */       
/*  58 */       player.inventory.deleteStack(itemStack);
/*  59 */       return ActionResult.resultSuccess(itemStack);
/*     */     } 
/*     */     
/*  62 */     if (itemStack.getOrCreateTag() != null)
/*     */     {
/*  64 */       if (owner == player) {
/*     */         
/*  66 */         props.setStrawDoll(true);
/*  67 */         WyNetwork.sendToServer(new SSyncEntityStatsPacket(player.getEntityId(), props));
/*  68 */         player.inventory.deleteStack(itemStack);
/*     */       } 
/*     */     }
/*     */     
/*  72 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner(World world, BlockPos pos, ItemStack itemStack) {
/*  78 */     UUID uuid = itemStack.getOrCreateTag().getUniqueId("ownerUUID");
/*  79 */     return (LivingEntity)((ServerWorld)world).getEntityByUuid(uuid);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
/*  85 */     if (entityItem.world.isRemote) {
/*  86 */       return false;
/*     */     }
/*  88 */     if (itemStack.getTag() != null) {
/*     */       
/*  90 */       LivingEntity target = getOwner(entityItem.world, entityItem.getPosition(), itemStack);
/*  91 */       if (target != null) {
/*     */         
/*  93 */         boolean isBurning = entityItem.isBurning();
/*  94 */         boolean isInVoid = (entityItem.getPosition().getY() < -1);
/*     */         
/*  96 */         if (isBurning || isInVoid) {
/*     */           
/*  98 */           Iterator<ItemStack> iter = target.getHeldEquipment().iterator();
/*  99 */           while (iter.hasNext()) {
/*     */             
/* 101 */             ItemStack stack = iter.next();
/* 102 */             if (stack.getItem() == Items.TOTEM_OF_UNDYING)
/* 103 */               stack.shrink(stack.getCount()); 
/*     */           } 
/* 105 */           EntityStatsCapability.get(target).setStrawDoll(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStrawDollOwner(ItemStack itemStack, LivingEntity e) {
/* 115 */     itemStack.setTag(new CompoundNBT());
/* 116 */     itemStack.getTag().putUniqueId("ownerUUID", e.getUniqueID());
/* 117 */     float red = e.getRNG().nextFloat();
/* 118 */     float green = e.getRNG().nextFloat();
/* 119 */     float blue = e.getRNG().nextFloat();
/* 120 */     itemStack.getOrCreateChildTag("display").putInt("color", (new Color(red, green, blue)).getRGB());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\StrawDollItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */