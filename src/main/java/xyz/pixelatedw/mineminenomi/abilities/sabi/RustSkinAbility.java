/*    */ package xyz.pixelatedw.mineminenomi.abilities.sabi;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedPassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.LogiaParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class RustSkinAbility
/*    */   extends DamagedPassiveAbility {
/* 24 */   public static final RustSkinAbility INSTANCE = new RustSkinAbility();
/*    */ 
/*    */   
/*    */   public RustSkinAbility() {
/* 28 */     super("Rust Skin", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("Makes the user immune to attacks received from iron based items, damaging them in the processes");
/* 30 */     hideInGUI(false);
/* 31 */     this.onDamagedEvent = this::onDamagedEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource) {
/* 36 */     LivingEntity attacker = (LivingEntity)damageSource.getTrueSource();
/*    */     
/* 38 */     ItemStack mainhandGear = attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
/* 39 */     ItemStack offhandGear = attacker.getItemStackFromSlot(EquipmentSlotType.OFFHAND);
/*    */     
/* 41 */     ItemStack toDamage = null;
/*    */     
/* 43 */     IAbilityData abilityProps = AbilityDataCapability.get(attacker);
/* 44 */     BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/* 45 */     boolean isActive = (ability != null && ability.isContinuous());
/*    */     
/* 47 */     if (isActive) {
/* 48 */       return true;
/*    */     }
/* 50 */     boolean isMainhand = (mainhandGear.getItem().isIn(ModTags.Items.RUSTY) && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, mainhandGear) <= 0);
/* 51 */     boolean isOffhand = (offhandGear.getItem().isIn(ModTags.Items.RUSTY) && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, offhandGear) <= 0);
/* 52 */     if (isMainhand || isOffhand)
/*    */     {
/* 54 */       toDamage = mainhandGear;
/*    */     }
/*    */     
/* 57 */     Item mainhandItem = mainhandGear.getItem();
/* 58 */     Item offhandItem = offhandGear.getItem();
/*    */     
/* 60 */     if (mainhandItem instanceof CoreSwordItem && !(mainhandItem instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem)) {
/*    */       
/* 62 */       boolean immunity = ((CoreSwordItem)mainhandItem).isRustImmune();
/* 63 */       if (!immunity) {
/* 64 */         toDamage = mainhandGear;
/*    */       }
/*    */     } 
/* 67 */     if (offhandItem instanceof CoreSwordItem && !(offhandItem instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem)) {
/*    */       
/* 69 */       boolean immunity = ((CoreSwordItem)offhandItem).isRustImmune();
/* 70 */       if (!immunity) {
/* 71 */         toDamage = offhandGear;
/*    */       }
/*    */     } 
/* 74 */     if (toDamage != null) {
/*    */       
/* 76 */       if (toDamage.isDamageable()) {
/* 77 */         toDamage.damageItem(toDamage.getMaxDamage() / 4 + 1, entity, e -> e.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */       } else {
/* 79 */         toDamage.shrink(1 + this.random.nextInt(8));
/* 80 */       }  LogiaParticleEffect logiaParticleEffect = new LogiaParticleEffect(ModParticleTypes.RUST);
/* 81 */       logiaParticleEffect.spawn(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 82 */       return false;
/*    */     } 
/*    */     
/* 85 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sabi\RustSkinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */