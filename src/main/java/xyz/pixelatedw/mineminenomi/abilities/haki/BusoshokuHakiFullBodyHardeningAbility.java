package xyz.pixelatedw.mineminenomi.abilities.haki;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.*;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

import java.util.UUID;

public class BusoshokuHakiFullBodyHardeningAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility, IBodyOverlayAbility {
  public static final BusoshokuHakiFullBodyHardeningAbility INSTANCE = new BusoshokuHakiFullBodyHardeningAbility();
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.BUSOSHOKU_HAKI_ARM).setColor(WyHelper.hexToRGB("#FFFFFFAA"));
  
  private final UUID armorUUID = UUID.fromString("0457f786-0a5a-4e83-9ea6-f924c259a798");
  private final UUID armorThougnessUUID = UUID.fromString("0457f786-0a5a-4e83-9ea6-f924c259a798");
  private final UUID strengthUUID = UUID.fromString("0457f786-0a5a-4e83-9ea4-f924c259a798");
  private final UUID damageReductionUUID = UUID.fromString("9121ac66-fb1c-48a7-a636-0cdc3f01d96e");
  
  private float damage;
  
  public BusoshokuHakiFullBodyHardeningAbility() {
    super("Busoshoku Haki: Full Body Hardening", AbilityHelper.getHakiCategory());
    setDescription("Covers the whole body of the user user in a layer of Armament haki, used for a balance between offense and defense");
    addInPool(new AbilityPool[] { AbilityPool.BUSOSHOKU_HAKI });
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!HakiHelper.canUseHaki(player, (Ability)this)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONE_HAKI_TYPE, new Object[] { getName() }));
      return false;
    } 
    
    if (!HakiHelper.canEnableHaki(player)) {
      return false;
    }
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    double defense = (hakiProps.getBusoshokuHardeningHakiExp() / 5.0F);
    
    player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier((AttributeModifier)getEntryArmor(defense));
    player.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier((AttributeModifier)getEntryArmor(defense));
    
    player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier((AttributeModifier)getEntryArmorThougness(defense / 4.0D));
    player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).applyModifier((AttributeModifier)getEntryArmorThougness(defense / 4.0D));
    
    player.getAttribute(ModAttributes.DAMAGE_REDUCTION).removeModifier((AttributeModifier)getEntryDamageReduction(hakiProps.getBusoshokuHardeningHakiExp() * 0.0025D));
    player.getAttribute(ModAttributes.DAMAGE_REDUCTION).applyModifier((AttributeModifier)getEntryDamageReduction(hakiProps.getBusoshokuHardeningHakiExp() * 0.0025D));
    
    this.damage = BusoshokuHakiHardeningAbility.getPunchDamage(player) * 0.75F;
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(this.damage));
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)getEntryAttackDamage(this.damage));
    
    player.world.playSound(null, player.getPosition(), ModSounds.BUSOSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 1);
    if (isOnMaxOveruse) {
      endContinuity(player);
    }
    ItemStack heldItem = player.getHeldItemMainhand();
    
    if (!heldItem.isEmpty()) {
      player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(0.0D));
    }
    else if (player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(this.strengthUUID) == null) {
      player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)getEntryAttackDamage(this.damage));
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier((AttributeModifier)getEntryArmor(0.0D));
    player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier((AttributeModifier)getEntryArmorThougness(0.0D));
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(0.0D));
    player.getAttribute(ModAttributes.DAMAGE_REDUCTION).removeModifier((AttributeModifier)getEntryDamageReduction(0.0D));
    
    int cooldown = (int)WyHelper.clamp(Math.round(this.continueTime / 30.0D), 10L, 90L);
    setMaxCooldown(cooldown);
    return true;
  }
  
  private AbilityAttributeModifier getEntryArmor(double amount) {
    return (new AbilityAttributeModifier(this.armorUUID, (Ability)INSTANCE, "Full Body Haki Armor Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
  }
  
  private AbilityAttributeModifier getEntryArmorThougness(double amount) {
    return (new AbilityAttributeModifier(this.armorThougnessUUID, (Ability)INSTANCE, "Full Body Haki Armor Toughness Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
  }
  
  private AbilityAttributeModifier getEntryDamageReduction(double amount) {
    return (new AbilityAttributeModifier(this.damageReductionUUID, (Ability)INSTANCE, "Full Body Haki Damage Reduction Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
  }
  
  private AbilityAttributeModifier getEntryAttackDamage(double amount) {
    return (new AbilityAttributeModifier(this.strengthUUID, (Ability)INSTANCE, "Full Body Haki Strength Attack Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
  }


  
  public HakiType getType() {
    return HakiType.HARDENING;
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


