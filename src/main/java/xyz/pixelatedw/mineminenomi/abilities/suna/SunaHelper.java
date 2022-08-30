/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.fluid.Fluids;
/*    */ import net.minecraft.item.BucketItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ 
/*    */ 
/*    */ public class SunaHelper
/*    */ {
/*    */   public static boolean isFruitBoosted(PlayerEntity player) {
/* 24 */     return (player.getEntityWorld().getBiome(player.getPosition()).getCategory().equals(Biome.Category.DESERT) || player.getEntityWorld().getBiome(player.getPosition()).getCategory().equals(Biome.Category.BEACH));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void drainLiquids(LivingEntity entity, int effects, int potions, int buckets) {
/* 29 */     if (entity == null || !entity.isAlive()) {
/*    */       return;
/*    */     }
/* 32 */     int cleanBottles = 0;
/* 33 */     int cleanBuckets = 0;
/* 34 */     int cleanEffects = 0;
/*    */     
/* 36 */     for (EffectInstance eff : entity.getActivePotionEffects()) {
/*    */       
/* 38 */       if (eff == null) {
/*    */         continue;
/*    */       }
/* 41 */       if (effects > cleanEffects && eff.getPotion().isBeneficial() && 1 >= eff.getAmplifier() && !(eff.getPotion() instanceof xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect)) {
/*    */         
/* 43 */         cleanEffects++;
/* 44 */         Effect potion = eff.getPotion();
/* 45 */         entity.removeActivePotionEffect(potion);
/* 46 */         if (entity instanceof ServerPlayerEntity) {
/* 47 */           ((ServerPlayerEntity)entity).connection.sendPacket((IPacket)new SRemoveEntityEffectPacket(entity.getEntityId(), potion));
/*    */         }
/*    */       } 
/*    */     } 
/* 51 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 53 */       PlayerEntity player = (PlayerEntity)entity;
/* 54 */       ArrayList<Item> items = new ArrayList<>(Arrays.asList(new Item[] { Items.POTION, Items.LINGERING_POTION, Items.SPLASH_POTION }));
/*    */       
/* 56 */       for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*    */         
/* 58 */         if (items.contains(player.inventory.getStackInSlot(i).getItem()) && potions > cleanBottles) {
/*    */           
/* 60 */           player.inventory.setInventorySlotContents(i, new ItemStack((IItemProvider)Items.AIR));
/* 61 */           cleanBottles++;
/* 62 */         } else if (player.inventory.getStackInSlot(i).getItem() instanceof BucketItem && buckets > cleanBuckets) {
/*    */           
/* 64 */           BucketItem item = (BucketItem)player.inventory.getStackInSlot(i).getItem();
/* 65 */           if (item.getFluid() != Fluids.EMPTY && item.getFluid() != Fluids.LAVA) {
/*    */             
/* 67 */             player.inventory.setInventorySlotContents(i, new ItemStack((IItemProvider)Items.AIR));
/* 68 */             cleanBuckets++;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 73 */       if (cleanBottles > 0) {
/* 74 */         player.addItemStackToInventory(new ItemStack((IItemProvider)Items.GLASS_BOTTLE, cleanBottles));
/*    */       }
/* 76 */       if (cleanBuckets > 0)
/* 77 */         player.addItemStackToInventory(new ItemStack((IItemProvider)Items.BUCKET, cleanBuckets)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SunaHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */