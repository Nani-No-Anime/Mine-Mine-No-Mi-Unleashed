package xyz.pixelatedw.mineminenomi.abilities.suna;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.biome.Biome;


public class SunaHelper
{
  public static boolean isFruitBoosted(PlayerEntity player) {
    return (player.getEntityWorld().getBiome(player.getPosition()).getCategory().equals(Biome.Category.DESERT) || player.getEntityWorld().getBiome(player.getPosition()).getCategory().equals(Biome.Category.BEACH));
  }

  
  public static void drainLiquids(LivingEntity entity, int effects, int potions, int buckets) {
    if (entity == null || !entity.isAlive()) {
      return;
    }
    int cleanBottles = 0;
    int cleanBuckets = 0;
    int cleanEffects = 0;
    
    for (EffectInstance eff : entity.getActivePotionEffects()) {
      
      if (eff == null) {
        continue;
      }
      if (effects > cleanEffects && eff.getPotion().isBeneficial() && 1 >= eff.getAmplifier() && !(eff.getPotion() instanceof xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect)) {
        
        cleanEffects++;
        Effect potion = eff.getPotion();
        entity.removeActivePotionEffect(potion);
        if (entity instanceof ServerPlayerEntity) {
          ((ServerPlayerEntity)entity).connection.sendPacket((IPacket)new SRemoveEntityEffectPacket(entity.getEntityId(), potion));
        }
      } 
    } 
    if (entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      ArrayList<Item> items = new ArrayList<>(Arrays.asList(new Item[] { Items.POTION, Items.LINGERING_POTION, Items.SPLASH_POTION }));
      
      for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
        
        if (items.contains(player.inventory.getStackInSlot(i).getItem()) && potions > cleanBottles) {
          
          player.inventory.setInventorySlotContents(i, new ItemStack((IItemProvider)Items.AIR));
          cleanBottles++;
        } else if (player.inventory.getStackInSlot(i).getItem() instanceof BucketItem && buckets > cleanBuckets) {
          
          BucketItem item = (BucketItem)player.inventory.getStackInSlot(i).getItem();
          if (item.getFluid() != Fluids.EMPTY && item.getFluid() != Fluids.LAVA) {
            
            player.inventory.setInventorySlotContents(i, new ItemStack((IItemProvider)Items.AIR));
            cleanBuckets++;
          } 
        } 
      } 
      
      if (cleanBottles > 0) {
        player.addItemStackToInventory(new ItemStack((IItemProvider)Items.GLASS_BOTTLE, cleanBottles));
      }
      if (cleanBuckets > 0)
        player.addItemStackToInventory(new ItemStack((IItemProvider)Items.BUCKET, cleanBuckets)); 
    } 
  }
}


