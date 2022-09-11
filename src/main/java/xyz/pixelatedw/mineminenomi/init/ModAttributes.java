package xyz.pixelatedw.mineminenomi.init;

import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.packets.server.SStepHeightValuePacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;




@EventBusSubscriber(modid = "mineminenomi")
public class ModAttributes
{
  public static final IAttribute FALL_RESISTANCE = (IAttribute)(new RangedAttribute(null, "mineminenomi.fallResistance", 0.0D, -256.0D, 256.0D)).setDescription("Fall Resistance");
  public static final IAttribute JUMP_HEIGHT = (IAttribute)(new RangedAttribute(null, "mineminenomi.jumpHeight", 1.0D, -256.0D, 256.0D)).setDescription("Jump Height").setShouldWatch(true);
  public static final IAttribute REGEN_RATE = (IAttribute)(new RangedAttribute(null, "mineminenomi.regenRate", 1.0D, 0.0D, 32.0D)).setDescription("Regen Rate").setShouldWatch(true);
  public static final IAttribute STEP_HEIGHT = (IAttribute)(new RangedAttribute(null, "mineminenomi.stepHeight", 0.6D, 0.0D, 20.0D)).setDescription("Step Height").setShouldWatch(true);
  public static final IAttribute DAMAGE_REDUCTION = (IAttribute)(new RangedAttribute(null, "mineminenomi.damageReduction", 0.0D, -2.0D, 0.999D)).setDescription("Damage Reduction").setShouldWatch(true);
  public static final IAttribute ATTACK_RANGE = (IAttribute)(new RangedAttribute(null, "mineminenomi.attackRange", 0.0D, -1024.0D, 1024.0D)).setDescription("Attack Range").setShouldWatch(true);
  public static final IAttribute PUNCH_DAMAGE = (IAttribute)(new RangedAttribute(null, "mineminenomi.punchDamage", 0.0D, -1024.0D, 1024.0D)).setDescription("Punch Damage").setShouldWatch(true);
  public static final IAttribute TIME_PROGRESSION = (IAttribute)(new RangedAttribute(null, "mineminenomi.timeProgression", 1.0D, 0.0D, 1024.0D)).setDescription("Time Progression").setShouldWatch(true);
  
  private static final UUID PUNCH_DAMAGE_UUID = UUID.fromString("0ca69be5-5c86-4878-bd4a-ee118ce628d3");

  
  @SubscribeEvent
  public static void onEntityConstruct(EntityEvent.EntityConstructing entity) {
    if (entity.getEntity() instanceof LivingEntity) {
      
      ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(FALL_RESISTANCE);
      ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(JUMP_HEIGHT);
      ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(REGEN_RATE);
      ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(STEP_HEIGHT);
      ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(DAMAGE_REDUCTION);
      ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(ATTACK_RANGE);
      ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(PUNCH_DAMAGE);
      ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(TIME_PROGRESSION);
    } 
  }

  
  @SubscribeEvent
  public static void onTick(TickEvent.PlayerTickEvent e) {
    if (!e.player.world.isRemote) {
      
      IAttributeInstance attributeInstance = e.player.getAttribute(STEP_HEIGHT);
      float newStepHeight = (float)attributeInstance.getValue();
      if (newStepHeight != e.player.stepHeight)
      {
        WyNetwork.sendTo(new SStepHeightValuePacket(newStepHeight), e.player);
      }
      e.player.stepHeight = newStepHeight;
    } 
    
    double damage = e.player.getAttribute(PUNCH_DAMAGE).getValue();
    
    AttributeModifier punchDamage = (new AttributeModifier(PUNCH_DAMAGE_UUID, "hand modifier", damage, AttributeModifier.Operation.ADDITION)).setSaved(false);
    
    if (e.player.getHeldItemMainhand().isEmpty() && damage > 0.0D) {
      
      if (e.player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(PUNCH_DAMAGE_UUID) == null) {
        e.player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(punchDamage);
      }
    } else {
      e.player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(punchDamage);
    } 
  }
  
  @SubscribeEvent
  public static void onFall(LivingFallEvent e) {
    IAttributeInstance attributeInstance = e.getEntityLiving().getAttribute(FALL_RESISTANCE);
    e.setDistance((float)(e.getDistance() - attributeInstance.getValue()));
  }

  
  @SubscribeEvent
  public static void onJump(LivingEvent.LivingJumpEvent e) {
    double value = e.getEntityLiving().getAttribute(JUMP_HEIGHT).getValue();
    e.getEntityLiving().addVelocity(0.0D, 0.10000000149011612D * (value - 1.0D), 0.0D);
    if (value <= 0.0D) {
      e.getEntityLiving().setMotion(0.0D, (e.getEntityLiving().getMotion()).y, 0.0D);
    }
  }
  
  @SubscribeEvent
  public static void onEntityHurt(LivingHurtEvent e) {
    if ((e.getEntityLiving()).world.isRemote)
      return; 
    double reduction = e.getEntityLiving().getAttribute(DAMAGE_REDUCTION).getValue();
    
    int absoluteReduction = 50;
    if (reduction > 0.0D && e.getSource().isUnblockable()) {
      
      for (EffectInstance effectInstance : e.getEntityLiving().getActivePotionEffects()) {
        if (effectInstance.getPotion() instanceof xyz.pixelatedw.mineminenomi.effects.GuardingEffect)
          absoluteReduction = 50 - effectInstance.getAmplifier() * 4; 
      } 
      reduction /= absoluteReduction;
    } 
    
    e.setAmount((float)(e.getAmount() * (1.0D - reduction)));
  }

  
  @SubscribeEvent
  public static void onHeal(LivingHealEvent event) {
    float value = (float)event.getEntityLiving().getAttribute(REGEN_RATE).getValue();
    if (value != 1.0F)
      event.setAmount(event.getAmount() * value); 
  }
}


