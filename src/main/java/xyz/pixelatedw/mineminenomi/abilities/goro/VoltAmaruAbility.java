package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningBallEntity;
import xyz.pixelatedw.mineminenomi.entities.zoan.VoltAmaruZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IChangeDamageSourceAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.UUID;

public class VoltAmaruAbility extends ZoanAbility implements IBodyOverlayAbility, IChangeDamageSourceAbility {
  public static final VoltAmaruAbility INSTANCE = new VoltAmaruAbility();
  
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1f8cc62b-ad78-4b25-b19c-76d23dd27517"), (Ability)INSTANCE, "Volt Amaru Reach Modifier", 4.8D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("84033b39-98f7-4312-acc5-92809e9732b1"), (Ability)INSTANCE, "Volt Amaru Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier DAMAGE_REDUCTION = (new AbilityAttributeModifier(UUID.fromString("8a048300-6f4b-11eb-9439-0242ac130002"), (Ability)INSTANCE, "Volt Amaru Damage Reduction Modifier", 0.25D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier HEALTH_BOOST = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Volt Amaru Health Modifier", 20.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Volt Amaru Strength Modifier", 12.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Volt Amaru Attack Speed Modifier", 1.0D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), (Ability)INSTANCE, "Volt Amaru Jump Modifier", 5.0D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setColor("#09C7FF").setRenderType(AbilityOverlay.RenderType.ENERGY);
  
  public static final ParticleEffect PARTICLES = (ParticleEffect)new HaoshokuHakiParticleEffect(2);
  
  public float speed = 0.0F;

  
  public VoltAmaruAbility() {
    super("Volt Amaru", AbilityHelper.getDevilFruitCategory());
    setThreshold(20.0D);
    setMaxCooldown(50.0D);
    setDescription("Transforms the user into a powerful, lightning giant massively boosting physical attributes and lightning attacks\n\n× Can only be used while §2" + (new TranslationTextComponent("item.mineminenomi.tomoe_drums", new Object[0])).getFormattedText() + "§r is equipped");
    
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION);
    addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_BOOST);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
    
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
    
    needsClientSide();
  }


  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    boolean hasTomoeDrums = (((ItemStack)player.inventory.armorInventory.get(2)).getItem() == ModArmors.TOMOE_DRUMS);
    
    if (!hasTomoeDrums || ((ItemStack)player.inventory.armorInventory.get(2)).getDamage() > 990) {
      return false;
    }
    ItemStack tomoe = (ItemStack)player.inventory.armorInventory.get(2);
    int damage = (tomoe.getDamage() + 100 < tomoe.getMaxDamage()) ? 100 : 99;
    tomoe.damageItem(damage, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    ElThorAbility elThor = (ElThorAbility)abilityProps.getEquippedAbility((Ability)ElThorAbility.INSTANCE);
    if (elThor != null) {
      
      elThor.enableVoltMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)elThor), (LivingEntity)player);
    } 
    
    LightningBallEntity ball = new LightningBallEntity((Entity)player, player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
    ball.setSize(4.0F);
    ball.setLightningLength(10.0F);
    ball.setAliveTicks(20);
    player.world.addEntity((Entity)ball);
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 1, false, false));
    
    return super.onStartContinuityEvent(player);
  }


  
  public void duringContinuityEvent(PlayerEntity player, int i) {
    super.duringContinuityEvent(player, i);
    
    boolean hasTomoeDrums = (((ItemStack)player.inventory.armorInventory.get(2)).getItem() == ModArmors.TOMOE_DRUMS);
    if (!hasTomoeDrums) {
      endContinuity(player);
    }
    if (i >= 20) {
      
      if (i == 21 || i % 100 == 0) {
        
        if (player.world.isRemote) {
          return;
        }
        MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
        WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
        player.recalculateSize();
      } 
      
      float maxSpeed = player.isSprinting() ? 2.2F : 1.1F;
      float acceleration = 0.015F;
      
      acceleration *= (this.speed > 0.0F) ? (1.0F - this.speed / maxSpeed) : 1.0F;
      acceleration = (player.moveForward > 0.0F && !player.collided) ? acceleration : (-maxSpeed / 10.0F);
      this.speed = MathHelper.clamp(this.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 3.0F) : 0.0F, maxSpeed);
      
      int d1 = player.onGround ? 1 : 0;
      int d2 = (player.moveForward > 0.0F) ? 1 : 0;
      
      Vec3d vec = player.getLookVec();
      double x = vec.x * this.speed * (1 - d1) * d2;
      double y = (d1 * 0.6F) + vec.y * this.speed * (1 - d1) * d2;
      double z = vec.z * this.speed * (1 - d1) * d2;
      player.setMotion(x, y, z);
      
      double difference = DevilFruitHelper.getDifferenceToFloor(player);
      
      if (difference < 5.0D) {
        
        player.setMotion(player.getMotion().add(0.0D, 1.0D, 0.0D).mul(1.0D, 0.25D, 1.0D));
      }
      else if (difference > 36.0D) {
        
        player.setMotion(player.getMotion().add(0.0D, 1.0D, 0.0D).mul(1.0D, -0.35D, 1.0D));
      } 
    } 
  }


  
  public boolean onEndContinuityEvent(PlayerEntity player) {
    super.onEndContinuityEvent(player);
    
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    ElThorAbility elThor = (ElThorAbility)abilityProps.getEquippedAbility((Ability)ElThorAbility.INSTANCE);
    if (elThor != null) {
      
      elThor.disableVoltMode();
      
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)elThor), (LivingEntity)player);
    } 
    
    return true;
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)VoltAmaruZoanInfo.INSTANCE;
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }


  
  public float damageToEntityWithSource(PlayerEntity player, LivingEntity entity) {
    if (player.hurtResistantTime > 0 && player.hurtResistantTime <= 10) {
      player.hurtTime = player.hurtResistantTime = 0;
    }
    if (player.getHeldItemMainhand().getItem() instanceof CoreSwordItem) {
      
      CoreSwordItem weapon = (CoreSwordItem)player.getHeldItemMainhand().getItem();
      if (weapon.conductivity > 0.5D) {
        
        entity.setFire(5);
        entity.hurtTime = entity.hurtResistantTime = 0;
        entity.attackEntityFrom((DamageSource)(new ModDamageSource("onFire", false, true, false)).causeEntityDamageFromSource((Entity)player), (float)(weapon.conductivity * 3.0D));
        entity.hurtTime = entity.hurtResistantTime = 0;
      } 
    } 
    
    return (float)(1.0D + player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue());
  }


  
  public DamageSource getSourceToUse(PlayerEntity player) {
    return (DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player);
  }


  
  public boolean cancelsOriginalDamage() {
    return true;
  }


  
  public boolean isSourceChangeEnabled() {
    return isContinuous();
  }


  
  public boolean isTransformationActive(LivingEntity entity) {
    return (isContinuous() && this.continueTime >= 20);
  }
}


