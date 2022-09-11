package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;


@EventBusSubscriber(modid = "mineminenomi")
public class HandcuffsEvents
{
  @SubscribeEvent
  public static void onEntityDeath(LivingDeathEvent event) {
    LivingEntity entity = event.getEntityLiving();
    if (entity.isPotionActive(ModEffects.HANDCUFFED)) {
      entity.entityDropItem(new ItemStack((IItemProvider)ModItems.NORMAL_HANDCUFFS));
    } else if (entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
      entity.entityDropItem(new ItemStack((IItemProvider)ModItems.KAIROSEKI_HANDCUFFS));
    } 
  }
  @SubscribeEvent
  public static void onEntityHurt(LivingHurtEvent event) {
    EffectInstance instance;
    Entity source = event.getSource().getTrueSource();
    
    if (!(source instanceof PlayerEntity) || event.getAmount() <= 0.0F || event.getEntityLiving().getHealth() - event.getAmount() > 0.0F) {
      return;
    }
    PlayerEntity playerSource = (PlayerEntity)source;
    
    ItemStack offhand = playerSource.getHeldItemOffhand();
    
    if (offhand.isEmpty() || !(offhand.getItem() instanceof xyz.pixelatedw.mineminenomi.items.HandcuffsItem)) {
      return;
    }
    
    int diff = 600 + EntityStatsCapability.get((LivingEntity)playerSource).getDoriki() - EntityStatsCapability.get(event.getEntityLiving()).getDoriki();
    
    event.getEntityLiving().setHealth(2.0F);
    
    if (offhand.getItem() == ModItems.NORMAL_HANDCUFFS) {
      instance = new EffectInstance(ModEffects.HANDCUFFED, diff * 2, 0, false, true);
    } else if (offhand.getItem() == ModItems.KAIROSEKI_HANDCUFFS) {
      instance = new EffectInstance(ModEffects.HANDCUFFED_KAIROSEKI, diff * 2, 0, false, true);
    } else {
      instance = new EffectInstance(ModEffects.HANDCUFFED, diff * 2, 0, false, true);
    } 
    event.getEntityLiving().addPotionEffect(instance);
    event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int)(diff * 1.2D), 1, false, false));
    event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.RESISTANCE, 5, 4, false, false));
    if (playerSource instanceof ServerPlayerEntity)
      ((ServerPlayerEntity)playerSource).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(event.getEntityLiving().getEntityId(), instance)); 
    offhand.shrink(1);
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
    if (!(event.getTarget() instanceof LivingEntity)) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    
    if (player.getHeldItemMainhand().getItem() != ModItems.KEY) {
      return;
    }
    LivingEntity target = (LivingEntity)event.getTarget();
    
    if (target.isPotionActive(ModEffects.HANDCUFFED) || target.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
      
      ItemStack keyStack = player.getHeldItemMainhand();
      keyStack.shrink(1);
      target.removeActivePotionEffect(ModEffects.HANDCUFFED);
      target.removeActivePotionEffect(ModEffects.HANDCUFFED_KAIROSEKI);
    } 
  }
}


