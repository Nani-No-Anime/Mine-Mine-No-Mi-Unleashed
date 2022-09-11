package xyz.pixelatedw.mineminenomi.abilities.haki;
import java.awt.Color;
import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class BusoshokuHakiHardeningAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility, IPunchOverlayAbility {
  public static final BusoshokuHakiHardeningAbility INSTANCE = new BusoshokuHakiHardeningAbility();
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.BUSOSHOKU_HAKI_ARM).setColor(new Color(255, 255, 255, 191));
  private static final UUID STRENGTH_UUID = UUID.fromString("0457f786-0a5a-4e83-9ea4-f924c259a798");
  
  private float damage = 0.0F;

  
  public BusoshokuHakiHardeningAbility() {
    super("Busoshoku Haki: Hardening", AbilityHelper.getHakiCategory());
    setDescription("Covers the fist of the user in Armament haki, making their physical attacks more powerful and being able to damage Logia users");
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
    this.damage = getPunchDamage(player);
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(this.damage));
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)getEntryAttackDamage(this.damage));
    
    player.world.playSound(null, player.getPosition(), ModSounds.BUSOSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  public static float getPunchDamage(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    
    float dorikiMultiplier = (props.getDoriki() / CommonConfig.INSTANCE.getDorikiLimit());
    float hakiMultiplier = hakiProps.getBusoshokuHardeningHakiExp() / CommonConfig.INSTANCE.getHakiExpLimit();
    
    return dorikiMultiplier * 4.0F + hakiMultiplier * 16.0F;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 0);
    if (isOnMaxOveruse) {
      endContinuity(player);
    }
    ItemStack heldItem = player.getHeldItemMainhand();
    
    if (!heldItem.isEmpty()) {
      player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(0.0D));
    }
    else if (player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(STRENGTH_UUID) == null) {
      player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)getEntryAttackDamage(this.damage));
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(0.0D));
    int cooldown = (int)WyHelper.clamp(Math.round(this.continueTime / 30.0D), 5L, 60L);
    setMaxCooldown(cooldown);
    return true;
  }

  
  private AbilityAttributeModifier getEntryAttackDamage(double amount) {
    return (new AbilityAttributeModifier(STRENGTH_UUID, (Ability)INSTANCE, "Hardening Haki Strength Attack Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
  }


  
  public HakiType getType() {
    return HakiType.HARDENING;
  }


  
  public AbilityOverlay getPunchOverlay(LivingEntity player) {
    return OVERLAY;
  }
}


