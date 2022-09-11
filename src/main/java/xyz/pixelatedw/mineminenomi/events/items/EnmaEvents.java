package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

@EventBusSubscriber(modid = "mineminenomi")
public class EnmaEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntityLiving()).world.isRemote) {
      return;
    }
    ItemStack stack = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.MAINHAND);
    
    if (stack.getItem() != ModWeapons.ENMA || !stack.hasTag()) {
      return;
    }
    if (stack.getTag().getBoolean("isClone")) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    float imbuingExp = hakiProps.getBusoshokuImbuingHakiExp();
    
    if (imbuingExp < 20.0F) {
      player.attackEntityFrom(DamageSource.WITHER, player.getMaxHealth());
    } else if (imbuingExp >= 20.0F && imbuingExp < 30.0F) {
      
      player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1, false, false));
      player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, false, false));
      if (!player.isPotionActive(Effects.WITHER)) {
        player.addPotionEffect(new EffectInstance(Effects.WITHER, 100, 1, false, false));
      }
    } else if (imbuingExp >= 30.0F) {
      
      if (abilityProps.hasEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE)) {
        
        BusoshokuHakiImbuingAbility abl = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
        
        if (abl.isDisabled()) {
          
          player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0, false, false));
          player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 0, false, false));
        } 
        
        if (abl.isOnCooldown() || !abl.isContinuous())
        {
          
          abl.setMaxCooldown(0.0D);
          abl.use(player);
        }
      
      } else {
        
        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 1, false, false));
        player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1, false, false));
        if (!player.isPotionActive(Effects.WITHER))
          player.addPotionEffect(new EffectInstance(Effects.WITHER, 100, 2, false, false)); 
      } 
    } 
  }
}


