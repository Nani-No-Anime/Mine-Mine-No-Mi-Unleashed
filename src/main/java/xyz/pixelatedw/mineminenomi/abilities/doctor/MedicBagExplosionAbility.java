/*    */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.doctor.MedicBagExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class MedicBagExplosionAbility extends Ability {
/* 23 */   public static final MedicBagExplosionAbility INSTANCE = new MedicBagExplosionAbility();
/*    */   
/* 25 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new MedicBagExplosionParticleEffect();
/*    */ 
/*    */   
/*    */   public MedicBagExplosionAbility() {
/* 29 */     super("Medic Bag Explosion", AbilityHelper.getStyleCategory());
/* 30 */     setMaxCooldown(40.0D);
/* 31 */     setDescription("By sacrificing the medic bag's durability the user can fully heal themselves while applying debuffs to nearby enemies");
/*    */     
/* 33 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 38 */     boolean isHandEmpty = player.getHeldItemMainhand().isEmpty();
/*    */     
/* 40 */     ItemStack medicBag = !isHandEmpty ? player.getHeldItemMainhand() : (ItemStack)player.inventory.armorInventory.get(2);
/* 41 */     boolean hasMedicBag = (medicBag.getItem() == ModArmors.MEDIC_BAG);
/*    */     
/* 43 */     if (!hasMedicBag) {
/*    */       
/* 45 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
/* 46 */       return false;
/*    */     } 
/*    */     
/* 49 */     player.heal(player.getMaxHealth() / 2.0F);
/*    */     
/* 51 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 52 */     targets.remove(player);
/* 53 */     for (LivingEntity entity : targets) {
/*    */       
/* 55 */       int effect = (int)WyHelper.randomWithRange(0, 6);
/*    */       
/* 57 */       switch (effect) {
/*    */         
/*    */         case 0:
/* 60 */           entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1));
/*    */         case 1:
/* 62 */           entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 1));
/*    */         case 2:
/* 64 */           entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 1));
/*    */         case 3:
/* 66 */           entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1));
/*    */         case 4:
/* 68 */           entity.addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
/*    */         case 5:
/* 70 */           entity.addPotionEffect(new EffectInstance(Effects.WITHER, 200, 1));
/*    */         case 6:
/* 72 */           entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
/*    */       } 
/*    */     
/*    */     } 
/* 76 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 78 */     medicBag.damageItem(250, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */     
/* 80 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\MedicBagExplosionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */