package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


@EventBusSubscriber(modid = "mineminenomi")
public class EnchantmentsEvents
{
  @SubscribeEvent
  public static void onLivingAttack(AttackEntityEvent event) {
    if (!(event.getPlayer()).world.isRemote && event.getTarget() instanceof LivingEntity) {
      
      PlayerEntity player = event.getPlayer();
      ItemStack heldItem = player.getHeldItemMainhand();
      
      if (heldItem != null && heldItem.isEnchanted()) {
        
        LivingEntity target = (LivingEntity)event.getTarget();
        
        int impactDialLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_IMPACT, heldItem);
        if (impactDialLevel > 0 && target.hurtTime == 0) {
          
          heldItem.damageItem((int)(WyHelper.randomWithRange(1, 3) * impactDialLevel), target, entity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));

          
          ExplosionAbility explosion = new ExplosionAbility((Entity)player, player.world, target.getPosX(), target.getPosY() + 1.0D, target.getPosZ(), impactDialLevel);
          explosion.setDamageOwner(false);
          explosion.setDestroyBlocks(false);
          explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(impactDialLevel));
          explosion.doExplosion();
        } 
        
        int flashDialLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_FLASH, heldItem);
        if (flashDialLevel > 0)
        {
          target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200 * flashDialLevel, flashDialLevel));
        }
      } 
    } 
  }
}


