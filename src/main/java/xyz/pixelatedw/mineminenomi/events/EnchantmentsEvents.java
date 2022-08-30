/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class EnchantmentsEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onLivingAttack(AttackEntityEvent event) {
/* 26 */     if (!(event.getPlayer()).world.isRemote && event.getTarget() instanceof LivingEntity) {
/*    */       
/* 28 */       PlayerEntity player = event.getPlayer();
/* 29 */       ItemStack heldItem = player.getHeldItemMainhand();
/*    */       
/* 31 */       if (heldItem != null && heldItem.isEnchanted()) {
/*    */         
/* 33 */         LivingEntity target = (LivingEntity)event.getTarget();
/*    */         
/* 35 */         int impactDialLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_IMPACT, heldItem);
/* 36 */         if (impactDialLevel > 0 && target.hurtTime == 0) {
/*    */           
/* 38 */           heldItem.damageItem((int)(WyHelper.randomWithRange(1, 3) * impactDialLevel), target, entity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */ 
/*    */           
/* 41 */           ExplosionAbility explosion = new ExplosionAbility((Entity)player, player.world, target.getPosX(), target.getPosY() + 1.0D, target.getPosZ(), impactDialLevel);
/* 42 */           explosion.setDamageOwner(false);
/* 43 */           explosion.setDestroyBlocks(false);
/* 44 */           explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(impactDialLevel));
/* 45 */           explosion.doExplosion();
/*    */         } 
/*    */         
/* 48 */         int flashDialLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_FLASH, heldItem);
/* 49 */         if (flashDialLevel > 0)
/*    */         {
/* 51 */           target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200 * flashDialLevel, flashDialLevel));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\EnchantmentsEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */