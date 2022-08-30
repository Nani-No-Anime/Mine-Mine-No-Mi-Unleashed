/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class HeartItem
/*     */   extends Item
/*     */ {
/*  32 */   private static final DamageSource DAMAGE_SOURCE = (new ModDamageSource("magic")).setInternalDamage().setDamageBypassesArmor().setMagicDamage().setDamageAllowedInCreativeMode().setDamageIsAbsolute();
/*     */ 
/*     */   
/*     */   public HeartItem() {
/*  36 */     super((new Item.Properties()).maxStackSize(1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/*  42 */     if (world.isRemote) {
/*  43 */       return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */     }
/*  45 */     ItemStack itemStack = player.getHeldItem(hand);
/*     */     
/*  47 */     LivingEntity owner = getOwner(world, player.getPosition(), itemStack);
/*     */     
/*  49 */     if (owner == null) {
/*     */       
/*  51 */       player.inventory.deleteStack(itemStack);
/*  52 */       return ActionResult.resultSuccess(itemStack);
/*     */     } 
/*     */     
/*  55 */     IEntityStats props = EntityStatsCapability.get(owner);
/*     */     
/*  57 */     if (props.hasHeart()) {
/*     */       
/*  59 */       player.inventory.deleteStack(itemStack);
/*  60 */       return ActionResult.resultSuccess(itemStack);
/*     */     } 
/*     */     
/*  63 */     if (itemStack.getOrCreateTag() != null)
/*     */     {
/*  65 */       if (owner == player) {
/*     */         
/*  67 */         props.setHeart(true);
/*  68 */         WyNetwork.sendToServer(new SSyncEntityStatsPacket(player.getEntityId(), props));
/*  69 */         player.inventory.deleteStack(itemStack);
/*     */       }
/*     */       else {
/*     */         
/*  73 */         owner.attackEntityFrom(DAMAGE_SOURCE, 5.0F);
/*  74 */         owner.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 1));
/*  75 */         owner.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
/*  76 */         if (owner.getHealth() <= 0.0F) {
/*  77 */           player.inventory.deleteStack(itemStack);
/*     */         }
/*     */       } 
/*     */     }
/*  81 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner(World world, BlockPos pos, ItemStack itemStack) {
/*  87 */     UUID uuid = itemStack.getOrCreateTag().getUniqueId("ownerUUID");
/*  88 */     return (LivingEntity)((ServerWorld)world).getEntityByUuid(uuid);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
/*  94 */     if (entityItem.world.isRemote) {
/*  95 */       return false;
/*     */     }
/*  97 */     if (itemStack.getTag() != null) {
/*     */       
/*  99 */       LivingEntity target = getOwner(entityItem.world, entityItem.getPosition(), itemStack);
/* 100 */       if (target != null) {
/*     */         
/* 102 */         boolean isBurning = entityItem.isBurning();
/* 103 */         boolean isInVoid = (entityItem.getPosition().getY() < -1);
/*     */         
/* 105 */         if (isBurning || isInVoid) {
/*     */           
/* 107 */           Iterator<ItemStack> iter = target.getHeldEquipment().iterator();
/* 108 */           while (iter.hasNext()) {
/*     */             
/* 110 */             ItemStack stack = iter.next();
/* 111 */             if (stack.getItem() == Items.TOTEM_OF_UNDYING)
/* 112 */               stack.shrink(stack.getCount()); 
/*     */           } 
/* 114 */           target.attackEntityFrom(DAMAGE_SOURCE, target.getMaxHealth() + target.getAbsorptionAmount() + 1.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeartOwner(ItemStack itemStack, LivingEntity owner) {
/* 124 */     itemStack.setTag(new CompoundNBT());
/* 125 */     itemStack.getTag().putUniqueId("ownerUUID", owner.getUniqueID());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\HeartItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */