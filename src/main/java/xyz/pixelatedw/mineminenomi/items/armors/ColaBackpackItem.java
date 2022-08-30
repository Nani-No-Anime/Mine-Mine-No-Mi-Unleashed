/*     */ package xyz.pixelatedw.mineminenomi.items.armors;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ArmorItem;
/*     */ import net.minecraft.item.IArmorMaterial;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.models.armors.ColaBackpackModel;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColaBackpackItem
/*     */   extends ArmorItem
/*     */ {
/*     */   private static final int COLA_REFILL_TICKS = 20;
/*     */   
/*     */   public ColaBackpackItem() {
/*  35 */     super((IArmorMaterial)ModArmors.COLA_BACKPACK_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModCreativeTabs.EQUIPMENT));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @Nullable
/*     */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/*  43 */     return (A)new ColaBackpackModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/*  52 */     return String.format("%s:textures/models/armor/cola_backpack.png", new Object[] { "mineminenomi" });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
/*  58 */     if (world.isRemote) {
/*     */       return;
/*     */     }
/*  61 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/*  63 */     if (props.isCyborg())
/*     */     {
/*  65 */       if (player.ticksExisted % 20 == 0) {
/*     */         
/*  67 */         int colaSlot = getColaSlot(player);
/*  68 */         int ultraColaSlot = getUltraColaSlot(player);
/*  69 */         if (colaSlot != -1 && props.getMaxCola() > props.getCola()) {
/*     */           
/*  71 */           props.alterCola(25);
/*  72 */           player.inventory.decrStackSize(colaSlot, 1);
/*  73 */           WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/*     */         }
/*  75 */         else if (ultraColaSlot != -1 && props.getMaxCola() > props.getCola()) {
/*     */           
/*  77 */           props.alterCola(50);
/*  78 */           player.inventory.decrStackSize(ultraColaSlot, 1);
/*  79 */           WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColaSlot(PlayerEntity player) {
/*  87 */     if (!player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLA))) {
/*  88 */       return -1;
/*     */     }
/*  90 */     for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */       
/*  92 */       ItemStack stack = player.inventory.getStackInSlot(i);
/*  93 */       if (!stack.isEmpty() && stack.getItem() == ModItems.COLA) {
/*  94 */         return i;
/*     */       }
/*     */     } 
/*  97 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUltraColaSlot(PlayerEntity player) {
/* 102 */     if (!player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.ULTRA_COLA))) {
/* 103 */       return -1;
/*     */     }
/* 105 */     for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */       
/* 107 */       ItemStack stack = player.inventory.getStackInSlot(i);
/* 108 */       if (!stack.isEmpty() && stack.getItem() == ModItems.ULTRA_COLA) {
/* 109 */         return i;
/*     */       }
/*     */     } 
/* 112 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\armors\ColaBackpackItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */