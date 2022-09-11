package xyz.pixelatedw.mineminenomi.abilities.gomu;
import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.entities.zoan.GearFourthZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class GearFourthAbility extends ZoanAbility implements IBodyOverlayAbility {
  public static final GearFourthAbility INSTANCE = new GearFourthAbility();
  
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("e158d542-5644-4921-9d5f-895f0b0a164c"), (Ability)INSTANCE, "Gear Fourth Armor Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), (Ability)INSTANCE, "Gear Fourth Attack Damage Modifier", 15.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier DAMAGE_REDUCTION_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1972705a-a0b6-4e66-8a4b-f64b0232b6f2"), (Ability)INSTANCE, "Gear Fourth Resistance Damage Modifier", 0.35D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.G4_OVERLAY);
  
  public float speed = 0.0F;
  private double currentHP = 0.0D;

  
  public GearFourthAbility() {
    super("Gear Fourth", AbilityHelper.getDevilFruitCategory());
    setThreshold(50.0D);
    setDescription("The user inflates their muscle structure to tremendously increase the power of their attacks and also allows flight\n\n× Can only be used while §2" + BusoshokuHakiFullBodyHardeningAbility.INSTANCE
        .getDisplayName() + "§r is active\n\nWhile active transforms §2Gomu Gomu no Rocket§r into §2King Kong Gun§r");

    
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION_MODIFIER);

    
    needsClientSide();
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  public boolean onStartContinuityEvent(PlayerEntity player) {
    if (!GomuHelper.canActivateGear(AbilityDataCapability.get((LivingEntity)player), (Ability)INSTANCE)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_GEAR_ACTIVE, new Object[0]));
      return false;
    } 
    
    BusoshokuHakiFullBodyHardeningAbility ability = (BusoshokuHakiFullBodyHardeningAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
    boolean hakiCheck = (ability != null && ability.isContinuous());
    if (!hakiCheck) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_HAKI, new Object[0]));
      return false;
    } 
    
    setThreshold(40.0D);
    this.currentHP = player.getHealth();
    return super.onStartContinuityEvent(player);
  }

  
  public void duringContinuity(PlayerEntity player, int passiveTimer) {
    if (!player.world.isRemote) {
      
      if (passiveTimer % 2 == 0) {
        GearSecondAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY() + 1.0D, player.getPosZ(), 0.0D, 0.0D, 0.0D);
      }
      BusoshokuHakiFullBodyHardeningAbility ability = (BusoshokuHakiFullBodyHardeningAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
      boolean hakiCheck = (ability != null && ability.isContinuous());
      boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 2);
      if (isOnMaxOveruse || !hakiCheck || AbilityHelper.isNearbyKairoseki(player)) {
        endContinuity(player);
      }
      if (player.hurtTime > 0) {
        
        if (this.currentHP > player.getHealth() + WyHelper.randomWithRange(3, 6)) {
          
          int diff = Math.abs((int)(player.getHealth() - this.currentHP));
          setThresholdInTicks(Math.max((int)(this.threshold * (1.0F - diff / 20.0F)), 0));
          
          for (int i = 0; i < diff * 60; i++)
            GearSecondAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY() + 1.0D, player.getPosZ(), 
                WyHelper.randomWithRange(-2, 2), WyHelper.randomWithRange(-2, 2), WyHelper.randomWithRange(-2, 2)); 
          IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
          WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
          WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
        } 
        this.currentHP = player.getHealth();
      } 
    } 
    
    GomuGomuNoRocketAbility rocketAbility = (GomuGomuNoRocketAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)GomuGomuNoRocketAbility.INSTANCE);
    
    float maxSpeed = (rocketAbility != null && rocketAbility.isContinuous()) ? 2.2F : 1.1F;
    float acceleration = 0.01F;
    
    acceleration *= (this.speed > 0.0F) ? (1.0F - this.speed / maxSpeed) : 1.0F;
    acceleration = (player.moveForward > 0.0F && !player.collided) ? acceleration : (-maxSpeed / 10.0F);
    this.speed = MathHelper.clamp(this.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 4.0F) : 0.0F, maxSpeed);
    
    int d1 = player.onGround ? 1 : 0;
    int d2 = (player.moveForward > 0.0F) ? 1 : 0;
    
    Vec3d vec = player.getLookVec();
    double x = vec.x * this.speed * (1 - d1) * d2;
    double y = (d1 * 0.6F) + vec.y * this.speed * (1 - d1) * d2 + Math.cos((player.ticksExisted / (4.0F - this.speed * 1.25F))) / 5.0D;
    double z = vec.z * this.speed * (1 - d1) * d2;
    player.setMotion(x, y, z);
    
    double difference = DevilFruitHelper.getDifferenceToFloor(player);
    
    if (difference > 40.0D) {
      player.setMotion(player.getMotion()
          .add(0.0D, 0.25D * difference / 5.0D, 0.0D)
          .mul(1.0D, -0.15D, 1.0D));
    }
  }

  
  public boolean onEndContinuityEvent(PlayerEntity player) {
    int duration = (int)(600.0F - HakiDataCapability.get((LivingEntity)player).getTotalHakiExp());
    if (this.continueTime > getThreshold() / 10) {
      
      player.addPotionEffect(new EffectInstance(Effects.HUNGER, duration, 3, true, true));
      player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, duration, 1, true, true));
      player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, duration, 1, true, true));
      player.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, duration, 0, true, true));
    } 
    
    BusoshokuHakiFullBodyHardeningAbility ability = (BusoshokuHakiFullBodyHardeningAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
    boolean hakiCheck = (ability != null && ability.isContinuous());
    if (hakiCheck) {
      ability.endContinuity(player);
    }
    int cooldown = duration / 60 + (int)Math.round(this.continueTime / 30.0D);
    setMaxCooldown(cooldown);
    return super.onEndContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)GearFourthZoanInfo.INSTANCE;
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


