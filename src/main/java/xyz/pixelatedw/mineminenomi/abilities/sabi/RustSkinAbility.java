package xyz.pixelatedw.mineminenomi.abilities.sabi;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.DamagedPassiveAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
import xyz.pixelatedw.mineminenomi.particles.effects.common.LogiaParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class RustSkinAbility
  extends DamagedPassiveAbility {
  public static final RustSkinAbility INSTANCE = new RustSkinAbility();

  
  public RustSkinAbility() {
    super("Rust Skin", AbilityHelper.getDevilFruitCategory());
    setDescription("Makes the user immune to attacks received from iron based items, damaging them in the processes");
    hideInGUI(false);
    this.onDamagedEvent = this::onDamagedEvent;
  }

  
  public boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource) {
    LivingEntity attacker = (LivingEntity)damageSource.getTrueSource();
    
    ItemStack mainhandGear = attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
    ItemStack offhandGear = attacker.getItemStackFromSlot(EquipmentSlotType.OFFHAND);
    
    ItemStack toDamage = null;
    
    IAbilityData abilityProps = AbilityDataCapability.get(attacker);
    BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (isActive) {
      return true;
    }
    boolean isMainhand = (mainhandGear.getItem().isIn(ModTags.Items.RUSTY) && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, mainhandGear) <= 0);
    boolean isOffhand = (offhandGear.getItem().isIn(ModTags.Items.RUSTY) && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, offhandGear) <= 0);
    if (isMainhand || isOffhand)
    {
      toDamage = mainhandGear;
    }
    
    Item mainhandItem = mainhandGear.getItem();
    Item offhandItem = offhandGear.getItem();
    
    if (mainhandItem instanceof CoreSwordItem && !(mainhandItem instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem)) {
      
      boolean immunity = ((CoreSwordItem)mainhandItem).isRustImmune();
      if (!immunity) {
        toDamage = mainhandGear;
      }
    } 
    if (offhandItem instanceof CoreSwordItem && !(offhandItem instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem)) {
      
      boolean immunity = ((CoreSwordItem)offhandItem).isRustImmune();
      if (!immunity) {
        toDamage = offhandGear;
      }
    } 
    if (toDamage != null) {
      
      if (toDamage.isDamageable()) {
        toDamage.damageItem(toDamage.getMaxDamage() / 4 + 1, entity, e -> e.sendBreakAnimation(EquipmentSlotType.MAINHAND));
      } else {
        toDamage.shrink(1 + this.random.nextInt(8));
      }  LogiaParticleEffect logiaParticleEffect = new LogiaParticleEffect(ModParticleTypes.RUST);
      logiaParticleEffect.spawn(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0.0D, 0.0D, 0.0D);
      return false;
    } 
    
    return true;
  }
}


