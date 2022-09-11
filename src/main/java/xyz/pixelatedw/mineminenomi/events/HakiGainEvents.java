package xyz.pixelatedw.mineminenomi.events;

import java.util.Random;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;




@EventBusSubscriber(modid = "mineminenomi")
public class HakiGainEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity && !(event.getEntityLiving()).world.isRemote) {
      
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
      float hakiMultiplier = (float)CommonConfig.INSTANCE.getHakiExpMultiplier();
      
      player.world.getProfiler().startSection("hakiExpGain");
      
      KenbunshokuHakiAuraAbility ability = (KenbunshokuHakiAuraAbility)props.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
      if (ability != null && ability.isContinuous() && hakiProps.getKenbunshokuHakiExp() >= 60.0F && player.ticksExisted % 600 == 0 && player.getHealth() < WyHelper.percentage(20.0D, player.getMaxHealth())) {
        
        float finalHakiExp = 0.025F * hakiMultiplier;
        hakiProps.alterKenbunshokuHakiExp(finalHakiExp);
        HakiExpEvent e = new HakiExpEvent(player, finalHakiExp, HakiType.KENBUNSHOKU);
        MinecraftForge.EVENT_BUS.post((Event)e);
      } 
      
      if (CommonConfig.INSTANCE.isHaoshokuUnlockLogicExpBased() && player.ticksExisted % 1200 == 0 && !props.hasUnlockedAbility((Ability)HaoshokuHakiAbility.INSTANCE)) {
        
        float totalPossible = (CommonConfig.INSTANCE.getHakiExpLimit() * 3);
        
        float totalExp = HakiHelper.getTotalHakiExp((LivingEntity)player);
        float totalCheck = (float)(150.0D + WyHelper.randomWithRange(-50, 120));
        totalCheck = MathHelper.clamp(totalCheck, 0.0F, totalPossible);
        
        if (totalExp >= totalCheck) {
          giveHakiAbility(player, (Ability)HaoshokuHakiAbility.INSTANCE);
        }
      } 
      player.world.getProfiler().endSection();
    } 
  }

  
  @SubscribeEvent
  public static void onEntityAttack(LivingHurtEvent event) {
    if (event.getAmount() < 1.0F || !(event.getEntityLiving() instanceof PlayerEntity) || !(event.getSource().getTrueSource() instanceof LivingEntity) || (event.getEntityLiving()).world.isRemote || event.getSource().isExplosion()) {
      return;
    }
    if (event.getSource().getImmediateSource() instanceof AbilityProjectileEntity) {
      
      AbilityProjectileEntity entity = (AbilityProjectileEntity)event.getSource().getImmediateSource();
      if (!entity.isPhysical()) {
        return;
      }
    } 
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    player.world.getProfiler().startSection("hakiExpGain");
    
    float hakiMultiplier = (float)CommonConfig.INSTANCE.getHakiExpMultiplier();
    
    KenbunshokuHakiAuraAbility ability = (KenbunshokuHakiAuraAbility)abilityProps.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
    if ((ability != null && ability.isContinuous()) || hakiProps.getKenbunshokuHakiExp() <= 10.0D + HakiHelper.getKenbunshokuAuraExpNeeded(player)) {
      
      float exp = event.getAmount() / (20.0F + 100.0F * hakiProps.getKenbunshokuHakiExp() / 100.0F);
      if (exp <= 0.0F) {
        exp = 1.0E-5F;
      }
      float finalHakiExp = exp * hakiMultiplier;
      hakiProps.alterKenbunshokuHakiExp(finalHakiExp);
      HakiExpEvent e = new HakiExpEvent(player, finalHakiExp, HakiType.KENBUNSHOKU);
      MinecraftForge.EVENT_BUS.post((Event)e);
    } 
    
    player.world.getProfiler().endSection();
  }









































  
  @SubscribeEvent
  public static void onEntityDeath(LivingDeathEvent event) {
    if (!(event.getSource().getTrueSource() instanceof PlayerEntity) || (event.getSource().getTrueSource()).world.isRemote) {
      return;
    }
    if (event.getSource().getImmediateSource() instanceof AbilityProjectileEntity) {
      
      AbilityProjectileEntity entity = (AbilityProjectileEntity)event.getSource().getImmediateSource();
      if (!entity.isPhysical()) {
        return;
      }
    } 
    PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    ItemStack heldStack = player.getHeldItem(player.getActiveHand());
    Random rand = player.getRNG();
    rand.setSeed(player.getUniqueID().getMostSignificantBits());
    
    player.world.getProfiler().startSection("hakiExpGain");
    
    float hakiMultiplier = (float)CommonConfig.INSTANCE.getHakiExpMultiplier();
    IAttributeInstance attrAtk = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
    IAttributeInstance attrHP = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
    double atk = (attrAtk != null) ? attrAtk.getBaseValue() : 0.0D;
    double hp = (attrHP != null) ? attrHP.getBaseValue() : 0.0D;

    
    float expValue = (event.getEntityLiving() instanceof xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity) ? ((float)(atk + hp) * 1.5F) : Math.min((float)(atk + hp), 50.0F);
    
    if (!heldStack.isEmpty()) {
      
      if (expValue < hakiProps.getBusoshokuImbuingHakiExp()) {
        return;
      }
      BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
      if ((ability != null && ability.isContinuous()) || hakiProps.getBusoshokuImbuingHakiExp() <= 10.0D + HakiHelper.getBusoshokuImbuingExpNeeded(player))
      {
        float exp = expValue / (1200.0F + 3600.0F * hakiProps.getBusoshokuImbuingHakiExp() / 100.0F);
        if (exp <= 0.0F) {
          exp = 1.0E-4F;
        }
        float finalHakiExp = exp * hakiMultiplier;
        
        if (!HakiHelper.canItemGainImbuing(heldStack)) {
          finalHakiExp /= 4.0F;
        }
        hakiProps.alterBusoshokuImbuingHakiExp(exp * hakiMultiplier);
        HakiExpEvent e = new HakiExpEvent(player, finalHakiExp, HakiType.HARDENING);
        MinecraftForge.EVENT_BUS.post((Event)e);
      }
    
    } else if (heldStack.isEmpty()) {
      
      if (expValue < hakiProps.getBusoshokuHardeningHakiExp()) {
        return;
      }
      BusoshokuHakiHardeningAbility ability = (BusoshokuHakiHardeningAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
      if ((ability != null && ability.isContinuous()) || hakiProps.getBusoshokuHardeningHakiExp() <= 10.0D + HakiHelper.getBusoshokuHardeningExpNeeded(player)) {
        
        float exp = expValue / (900.0F + 2700.0F * hakiProps.getBusoshokuHardeningHakiExp() / 100.0F);
        if (exp <= 0.0F) {
          exp = 1.0E-4F;
        }
        float finalHakiExp = exp * hakiMultiplier;
        hakiProps.alterBusoshokuHardeningHakiExp(finalHakiExp);
        HakiExpEvent e = new HakiExpEvent(player, finalHakiExp, HakiType.HARDENING);
        MinecraftForge.EVENT_BUS.post((Event)e);
      } 
    } 
    
    player.world.getProfiler().endSection();
  }

  
  @SubscribeEvent
  public static void onPlayerLoggedIn(EntityJoinWorldEvent event) {
    if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).isRemote && CommonConfig.INSTANCE.isHaoshokuUnlockLogicChanceBased()) {
      
      PlayerEntity player = (PlayerEntity)event.getEntity();

      
      if (HakiHelper.isHaoshokuBorn(player)) {
        giveHakiAbility(player, (Ability)HaoshokuHakiAbility.INSTANCE);
      }
    } 
  }
  
  public static void giveHakiAbility(PlayerEntity player, Ability ability) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    if (!props.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability)) {
      
      props.addUnlockedAbility(ability);
      if (!player.world.isRemote)
        WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player); 
    } 
  }
}


