package xyz.pixelatedw.mineminenomi.abilities.bara;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraFestivalEntity;
import xyz.pixelatedw.mineminenomi.entities.zoan.BaraFestivalZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

import java.util.List;
import java.util.UUID;

public class BaraBaraFestivalAbility extends ZoanAbility {
  public static final BaraBaraFestivalAbility INSTANCE = new BaraBaraFestivalAbility();
  
  private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("0349c517-823a-4f20-8a95-b9a0e3787c47"), (Ability)INSTANCE, "Bara Bara Festival Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  
  private LivingEntity grabbedEntity = null;
  private BaraFestivalEntity baraFestival = null;
  private Mode activeMode = Mode.ATTACK;

  
  public BaraBaraFestivalAbility() {
    super("Bara Bara Festival", AbilityHelper.getDevilFruitCategory());
    setDescription("Splits the user's body in several small parts that constantly hurt and slow down the target in ATTACK mode or swarm around the user acting as a shield in SHIELD mode\n\n§2SHIFT-USE§r: Switches between ATTACK and SHIELD modes");
    setMaxCooldown(15.0D);
    setThreshold(8.0D);
    
    addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  public boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isSneaking()) {
      
      if (this.activeMode == Mode.ATTACK) {
        this.activeMode = Mode.SHIELD;
      } else {
        this.activeMode = Mode.ATTACK;
      } 
      player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.activeMode, new Object[0]));
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
      
      return false;
    } 
    
    if (this.activeMode == Mode.ATTACK) {
      
      RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 12.0D);
      if (mop instanceof EntityRayTraceResult) {
        
        EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
        if (entityRayTraceResult.getEntity() instanceof LivingEntity)
        {
          LivingEntity e = (LivingEntity)entityRayTraceResult.getEntity();
          if (!e.isAlive() || (DevilFruitCapability.get(e).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, e)) {
            
            endContinuity(player);
            return false;
          } 
          
          this.grabbedEntity = e;
          this.baraFestival = new BaraFestivalEntity(player.world);
          this.baraFestival.setPositionAndRotation(this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY() + 1.0D, this.grabbedEntity.getPosZ(), 0.0F, 0.0F);
          this.baraFestival.setTarget(this.grabbedEntity);
          this.baraFestival.setOwner(player.getUniqueID());
          player.world.addEntity((Entity)this.baraFestival);
        }
      
      } else {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NO_TARGET, new Object[] { getName() }));
        return false;
      }
    
    } else {
      
      this.grabbedEntity = (LivingEntity)player;
      this.baraFestival = new BaraFestivalEntity(player.world);
      this.baraFestival.setPositionAndRotation(this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY() + 1.0D, this.grabbedEntity.getPosZ(), 0.0F, 0.0F);
      this.baraFestival.setTarget(this.grabbedEntity);
      this.baraFestival.setOwner(player.getUniqueID());
      player.world.addEntity((Entity)this.baraFestival);
    } 
    
    return super.onStartContinuityEvent(player);
  }


  
  public void duringContinuityEvent(PlayerEntity player, int continuousTime) {
    if (this.activeMode == Mode.ATTACK) {
      
      if (this.grabbedEntity == null || !this.grabbedEntity.isAlive() || player.getDistanceSq((Entity)this.grabbedEntity) > 1500.0D) {
        
        endContinuity(player);
        
        return;
      } 
      if (continuousTime % 20 == 0)
      {
        this.grabbedEntity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 2.0F);
        this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 1));
      }
    
    } else {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 3.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      
      for (LivingEntity target : targets) {
        
        target.attackEntityFrom((DamageSource)AbilityDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this), 6.0F);
        Vec3d dist = target.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D).normalize();
        double power = 2.0D;
        double xSpeed = -dist.x * power;
        double zSpeed = -dist.z * power;
        target.setMotion(-xSpeed, 0.20000000298023224D, -zSpeed);
        target.velocityChanged = true;
      } 
    } 
    
    player.addPotionEffect(new EffectInstance(ModEffects.NO_HANDS, 5, 0));
  }


  
  public boolean onEndContinuityEvent(PlayerEntity player) {
    if (this.baraFestival != null)
      this.baraFestival.remove(); 
    return super.onEndContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)BaraFestivalZoanInfo.INSTANCE;
  }
  
  public enum Mode
  {
    ATTACK, SHIELD;
  }
}


