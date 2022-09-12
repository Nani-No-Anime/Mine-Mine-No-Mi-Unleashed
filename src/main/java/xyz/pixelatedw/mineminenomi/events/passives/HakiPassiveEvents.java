package xyz.pixelatedw.mineminenomi.events.passives;

import com.google.common.base.Strings;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
import xyz.pixelatedw.mineminenomi.api.events.RenderMorphEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;



@EventBusSubscriber(modid = "mineminenomi")
public class HakiPassiveEvents
{
  @SubscribeEvent
  public static void onItemChange(LivingEquipmentChangeEvent event) {
    LivingEntity entity = event.getEntityLiving();
    ItemStack stack = event.getTo();
    BusoshokuHakiImbuingAbility.tryApplyingImbuingBonus(entity, stack);
  }

  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public static void onEntityAttackEvent(LivingAttackEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntity()).world.isRemote) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    int hakiOveruse = 10 + (int)(HakiHelper.getMaxOveruse(player) / 1180.0F);
    
    KenbunshokuHakiFutureSightAbility futureSight = (KenbunshokuHakiFutureSightAbility)abilityProps.getEquippedAbility((Ability)KenbunshokuHakiFutureSightAbility.INSTANCE);
    boolean isActive = (futureSight != null && futureSight.isContinuous());
    
    if (!isActive) {
      return;
    }
    float amount = event.getAmount();
    DamageSource damageSource = event.getSource();
    ArrayList<DamageSource> damageableSources = new ArrayList<>(Arrays.asList(new DamageSource[] { DamageSource.LAVA, DamageSource.IN_WALL, DamageSource.CACTUS, DamageSource.SWEET_BERRY_BUSH, DamageSource.STARVE, DamageSource.ANVIL, DamageSource.FLY_INTO_WALL, DamageSource.FALL, DamageSource.FALLING_BLOCK, DamageSource.OUT_OF_WORLD, DamageSource.WITHER, DamageSource.MAGIC, DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.LIGHTNING_BOLT }));
    
    boolean isLogia = DevilFruitCapability.get((LivingEntity)player).isLogia();
    
    LogiaInvulnerabilityAbility invulnerabilityInstance = null;
    if (isLogia) {
      
      List<Ability> unlockedAbilities = abilityProps.getUnlockedAbilities(AbilityHelper.getDevilFruitCategory());
      for (Ability ability : unlockedAbilities) {
        
        if (ability == null) {
          continue;
        }
        if (ability instanceof LogiaInvulnerabilityAbility) {
          
          invulnerabilityInstance = (LogiaInvulnerabilityAbility)ability;
          if (!invulnerabilityInstance.onDamagedEvent((LivingEntity)player, damageSource)) {
            
            event.setCanceled(true);
            return;
          } 
          damageableSources.removeAll(invulnerabilityInstance.immunitySources);
        } 
      } 
      
      hakiOveruse /= 3;
    
    }
    else if (damageSource.isExplosion()) {
      return;
    } 
    
    if (player.hurtResistantTime > 0 && !damageableSources.contains(damageSource) && !damageSource.getDamageType().equals("special") && !isLogia) {
      
      hakiProps.alterHakiOveruse(hakiOveruse * 4);
      event.setCanceled(true);
      
      return;
    } 
    if (!damageSource.isUnblockable())
      amount = CombatRules.getDamageAfterAbsorb(amount, player.getTotalArmorValue(), (float)player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getValue()); 
    int absorbed = EnchantmentHelper.getEnchantmentModifierDamage(player.getArmorInventoryList(), damageSource);
    if (absorbed > 0) amount = CombatRules.getDamageAfterMagicAbsorb(amount, absorbed);
    
    if (0.0F > amount) {
      
      event.setCanceled(true);
      
      return;
    } 
    boolean baseCondition = damageableSources.stream().noneMatch(s -> damageSource.getDamageType().equals(s.getDamageType()));
    boolean notInternal = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isInternalDamage());
    if (baseCondition && !notInternal) {
      
      futureSight.reduceProtection(amount);
      hakiProps.alterHakiOveruse(hakiOveruse * 4 + (int)(amount * hakiOveruse));
      Objects.requireNonNull(player); player.hurtResistantTime = player.hurtTime = 20;
      if (invulnerabilityInstance != null)
        invulnerabilityInstance.particleEffect.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D); 
      event.setCanceled(true);
    } 
  }

  
  @SubscribeEvent
  public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity) {
      
      PlayerEntity playerEntity = (PlayerEntity)event.getEntityLiving();
      ItemStack heldItem = playerEntity.getHeldItemMainhand();
      
      if (!heldItem.isEmpty() && heldItem.isDamageable() && !(heldItem.getItem()).properties.containsKey(new ResourceLocation("haki")))
      {
        if (HakiHelper.hasImbuingActive((LivingEntity)playerEntity) && !heldItem.getOrCreateTag().getBoolean("imbuingHakiActive")) {
          heldItem.getOrCreateTag().putBoolean("imbuingHakiActive", true);
        } else if (heldItem.getOrCreateTag().contains("imbuingHakiActive") && !HakiHelper.hasImbuingActive((LivingEntity)playerEntity) && heldItem.getOrCreateTag().getBoolean("imbuingHakiActive")) {
          heldItem.getOrCreateTag().remove("imbuingHakiActive");
        } 
      }
    } 
  }

  
  @SubscribeEvent
  public static void onItemTossed(ItemTossEvent event) {
    ItemStack stack = event.getEntityItem().getItem();
    if (stack != null && !stack.isEmpty() && stack.hasTag() && stack.getTag().getBoolean("imbuingHakiActive")) {
      stack.getOrCreateTag().remove("imbuingHakiActive");
    }
  }
  
  @SubscribeEvent
  public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END) {
      return;
    }
    PlayerEntity player = event.player;
    
    if (player.world.isRemote) {
      return;
    }
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    float maxOveruse = HakiHelper.getMaxOveruse(player);
    float hakiOveruse = hakiProps.getHakiOveruse();
    
    if (hakiOveruse >= maxOveruse * 0.9D) {
      
      player.addPotionEffect(new EffectInstance(ModEffects.HAKI_OVERUSE, 40, 0));
      player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 40, 0, false, false));
      player.addPotionEffect(new EffectInstance(Effects.HUNGER, 40, 1, false, false));
      if (hakiOveruse >= maxOveruse * 0.95D) {
        
        player.addPotionEffect(new EffectInstance(ModEffects.HAKI_OVERUSE, 80, 1));
        player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 80, 1, false, false));
        if (hakiOveruse >= maxOveruse) {
          
          player.addPotionEffect(new EffectInstance(ModEffects.HAKI_OVERUSE, 100, 2));
          player.addPotionEffect(new EffectInstance(Effects.HUNGER, 100, 2, false, false));
          player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 2, false, false));
          player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 2, false, false));
          player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false));
        } 
      } 
    } 
    
    IAbilityData attackerAbilityProps = AbilityDataCapability.get((LivingEntity)player);
    Ability auraHaki = attackerAbilityProps.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
    boolean hasAuraHakiActive = (auraHaki != null && auraHaki.isContinuous());
    Ability fsHaki = attackerAbilityProps.getEquippedAbility((Ability)KenbunshokuHakiFutureSightAbility.INSTANCE);
    boolean hasfsHakiActive = (fsHaki != null && fsHaki.isContinuous());
    
    boolean hasBusoActive = HakiHelper.hasAnyHakiEnabled((LivingEntity)player);
    boolean hasKenActive = (hasAuraHakiActive || hasfsHakiActive);
    
    Ability imbuingHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
    boolean hasImbuingHakiActive = (imbuingHaki != null && imbuingHaki.isContinuous());
    
    if (hasBusoActive || hasKenActive || hasImbuingHakiActive) {
      return;
    }
    int ticksToHeal = HakiHelper.hasAnyHakiEnabled((LivingEntity)player) ? 300 : 20;
    if (player.ticksExisted % ticksToHeal != 0) {
      return;
    }
    int overuseHeal = -Math.max(5, (int)(HakiHelper.getTotalHakiExp((LivingEntity)player) / 30.0F)) * 3;
    hakiProps.alterHakiOveruse(overuseHeal);
  }



  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onZoanRendered(RenderMorphEvent.Pre event) {
    if (event.getEntity() == null) {
      return;
    }
    LivingEntity entity = event.getEntityLiving();
    
    if (entity.isPotionActive(ModEffects.UNCONSCIOUS)) {
      
      if (entity.getActivePotionEffect(ModEffects.UNCONSCIOUS).getDuration() <= 0) {
        entity.removePotionEffect(ModEffects.UNCONSCIOUS);
      }
      rotateEntityModel(entity, event.getMatrixStack(), 1.0F);
    } 
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onEntityRendered(RenderLivingEvent.Pre event) {
    if (event.getEntity() == null) {
      return;
    }
    LivingEntity entity = event.getEntity();
    
    if (entity.isPotionActive(ModEffects.UNCONSCIOUS) && Strings.isNullOrEmpty(DevilFruitCapability.get(entity).getZoanPoint())) {
      
      if (entity.getActivePotionEffect(ModEffects.UNCONSCIOUS).getDuration() <= 0) {
        entity.removePotionEffect(ModEffects.UNCONSCIOUS);
      }
      float angle = (entity.getEntityId() % 8);
      rotateEntityModel(entity, event.getMatrixStack(), angle);
    } 
  }

  
  @OnlyIn(Dist.CLIENT)
  private static void rotateEntityModel(LivingEntity entity, MatrixStack matrixStack, float angle) {
    if (angle == 0.0F) {
      
      matrixStack.rotate(new Quaternion(Vector3f.XP, 90.0F, true));
      entity.rotationYawHead = 0.0F;
      entity.prevRotationYawHead = 0.0F;
    }
    else if (angle == 1.0F) {
      
      matrixStack.rotate(new Quaternion(Vector3f.YP, 45.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.ZP, 90.0F, true));
      entity.rotationYawHead = 45.0F;
      entity.prevRotationYawHead = 45.0F;
    }
    else if (angle == 2.0F) {
      
      matrixStack.rotate(new Quaternion(Vector3f.ZP, 90.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.YN, 90.0F, true));
      entity.rotationYawHead = 90.0F;
      entity.prevRotationYawHead = 90.0F;
    }
    else if (angle == 3.0F) {
      
      matrixStack.rotate(new Quaternion(Vector3f.YP, 45.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.XN, 90.0F, true));
      entity.rotationYawHead = 90.0F;
      entity.prevRotationYawHead = 90.0F;
    }
    else if (angle == 4.0F) {
      
      matrixStack.rotate(new Quaternion(Vector3f.XN, 90.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
      entity.rotationYawHead = 90.0F;
      entity.prevRotationYawHead = 90.0F;
    }
    else if (angle == 5.0F) {
      
      matrixStack.rotate(new Quaternion(Vector3f.YP, 45.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.ZN, 90.0F, true));
      entity.rotationYawHead = 90.0F;
      entity.prevRotationYawHead = 90.0F;
    }
    else if (angle == 6.0F) {
      
      matrixStack.rotate(new Quaternion(Vector3f.ZN, 90.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.YP, 90.0F, true));
      entity.rotationYawHead = 360.0F;
      entity.prevRotationYawHead = 360.0F;
    }
    else if (angle == 7.0F) {
      
      matrixStack.rotate(new Quaternion(Vector3f.YN, 45.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.ZN, 90.0F, true));
      entity.rotationYawHead = 360.0F;
      entity.prevRotationYawHead = 360.0F;
    } 
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onFogRendered(EntityViewRenderEvent.FogDensity event) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    IAbilityData props = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
    
    Ability ability = props.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (isActive && clientPlayerEntity.isPotionActive(Effects.BLINDNESS)) {
      
      event.setCanceled(true);
      event.setDensity(1.0E-4F);
    } 
  }
}


