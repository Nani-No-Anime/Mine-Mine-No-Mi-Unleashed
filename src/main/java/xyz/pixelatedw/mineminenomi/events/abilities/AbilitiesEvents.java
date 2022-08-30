/*     */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.living.PotionEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowLooseEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedPassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.HurtPassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IDeathAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PotionPassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IChangeDamageSourceAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class AbilitiesEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  62 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/*     */       
/*  64 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  65 */       IAbilityData ablProps = AbilityDataCapability.get((LivingEntity)player);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       player.world.getProfiler().startSection("abilityCooldown");
/*  88 */       for (Ability ability : ablProps.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */         
/*  90 */         if (ability == null) {
/*     */           continue;
/*     */         }
/*     */         
/*     */         try {
/*  95 */           if (ability instanceof PassiveAbility) {
/*  96 */             ((PassiveAbility)ablProps.getUnlockedAbility(ability)).tick(player);
/*     */           }
/*  98 */           if (ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility) {
/*  99 */             ablProps.getUnlockedAbility(ability).cooldown(player);
/*     */           }
/* 101 */         } catch (Exception e) {
/*     */           
/* 103 */           e.printStackTrace();
/* 104 */           ability.startCooldown(player);
/*     */         } 
/*     */       } 
/* 107 */       player.world.getProfiler().endSection();
/*     */       
/* 109 */       player.world.getProfiler().startSection("abilityTick");
/* 110 */       for (Ability ability : ablProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */         
/* 112 */         if (ability != null)
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 117 */             if (ability instanceof ChargeableAbility && ability.isCharging()) {
/* 118 */               ((ChargeableAbility)ablProps.getEquippedAbility(ability)).charging(player);
/*     */             }
/* 120 */             if (ability instanceof ContinuousAbility && ability.isContinuous()) {
/* 121 */               ((ContinuousAbility)ablProps.getEquippedAbility(ability)).tick(player);
/*     */             }
/* 123 */             if (ability.isDisabled()) {
/* 124 */               ablProps.getEquippedAbility(ability).disableTick(player);
/*     */             }
/* 126 */             if (ability.isOnCooldown()) {
/* 127 */               ablProps.getEquippedAbility(ability).cooldown(player);
/*     */             }
/* 129 */           } catch (Exception e) {
/*     */             
/* 131 */             e.printStackTrace();
/* 132 */             ability.startCooldown(player);
/*     */           }  
/*     */       } 
/* 135 */       player.world.getProfiler().endSection();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerDies(LivingDeathEvent event) {
/* 142 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/*     */       
/* 144 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 145 */       IAbilityData ablProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 147 */       for (Ability ability : ablProps.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */         
/* 149 */         if (ability == null) {
/*     */           continue;
/*     */         }
/* 152 */         if (ability instanceof PassiveAbility && ability instanceof IDeathAbility) {
/*     */           
/* 154 */           boolean flag = ((IDeathAbility)ability).onUserDeath((LivingEntity)player, event.getSource());
/* 155 */           event.setCanceled(!flag);
/*     */         } 
/*     */       } 
/*     */       
/* 159 */       for (Ability ability : ablProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */         
/* 161 */         if (ability != null) {
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 166 */             if (ability.getState() == Ability.State.CONTINUOUS) {
/*     */               
/* 168 */               if (ability instanceof ContinuousAbility) {
/*     */                 
/* 170 */                 ((ContinuousAbility)ability).endContinuity(player);
/* 171 */                 if (ability instanceof IDeathAbility) {
/*     */                   
/* 173 */                   boolean flag = ((IDeathAbility)ability).onUserDeath((LivingEntity)player, event.getSource());
/* 174 */                   event.setCanceled(!flag);
/*     */                 } 
/*     */               } 
/*     */               
/* 178 */               if (ability instanceof RepeaterAbility) {
/* 179 */                 ((RepeaterAbility)ability).setRepeaterCount(((RepeaterAbility)ability).getMaxRepeaterCount());
/*     */               }
/* 181 */             } else if (ability instanceof ChargeableAbility && ability.getState() == Ability.State.CHARGING) {
/*     */               
/* 183 */               ((ChargeableAbility)ability).setChargeTime(((ChargeableAbility)ability).getMaxChargeTime() / 20);
/* 184 */               ability.startCooldown(player);
/*     */             } else {
/*     */               
/* 187 */               ability.startCooldown(player);
/*     */             } 
/* 189 */           } catch (Exception e) {
/*     */             
/* 191 */             e.printStackTrace();
/* 192 */             ability.startCooldown(player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(LivingHurtEvent event) {
/* 201 */     if (event.getEntityLiving() != null && !(event.getEntityLiving()).world.isRemote) {
/*     */       
/* 203 */       LivingEntity entity = event.getEntityLiving();
/* 204 */       Entity attacker = event.getSource().getImmediateSource();
/* 205 */       IAbilityData ablProps = AbilityDataCapability.get(entity);
/*     */       
/* 207 */       for (Ability ability : ablProps.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */         
/* 209 */         if (ability == null) {
/*     */           continue;
/*     */         }
/*     */         
/*     */         try {
/* 214 */           if (ability instanceof HurtPassiveAbility)
/*     */           {
/* 216 */             HurtPassiveAbility hurtAbility = (HurtPassiveAbility)ablProps.getUnlockedAbility(ability);
/* 217 */             boolean result = hurtAbility.hurt(entity, event.getSource().getTrueSource(), event.getAmount());
/* 218 */             event.setAmount(hurtAbility.getAmount());
/* 219 */             event.setCanceled(!result);
/*     */           }
/*     */         
/* 222 */         } catch (Exception e) {
/*     */           
/* 224 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       
/* 228 */       if (attacker instanceof PlayerEntity) {
/*     */         
/* 230 */         PlayerEntity player = (PlayerEntity)attacker;
/*     */         
/* 232 */         for (Ability ability : AbilityDataCapability.get((LivingEntity)player).getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */           
/* 234 */           if (ability != null)
/*     */           {
/*     */             
/* 237 */             if (ability.getCategory() != AbilityHelper.getDevilFruitCategory() || !AbilityHelper.isAffectedByWater(entity))
/*     */             {
/*     */               
/* 240 */               if (ability instanceof PunchAbility && (event.getSource()).damageType.equalsIgnoreCase("ability") && ability.isContinuous()) {
/*     */                 
/* 242 */                 Ability source = ((AbilityDamageSource)event.getSource()).getAbilitySource();
/* 243 */                 if (source != null)
/*     */                 {
/* 245 */                   ((PunchAbility)ability).hitEffect(player, entity); } 
/*     */               } 
/*     */             }
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(LivingAttackEvent event) {
/* 256 */     if (event.getEntityLiving() != null && !(event.getEntityLiving()).world.isRemote) {
/*     */       
/* 258 */       LivingEntity entity = event.getEntityLiving();
/* 259 */       IAbilityData ablProps = AbilityDataCapability.get(entity);
/*     */       
/* 261 */       if (!CommonConfig.INSTANCE.isAbilityInvulnerabilityEnabled()) {
/*     */         return;
/*     */       }
/*     */       
/* 265 */       if (event.getSource() == ModDamageSource.DEVILS_CURSE) {
/*     */         return;
/*     */       }
/* 268 */       for (Ability ability : ablProps.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */         
/* 270 */         if (ability == null) {
/*     */           continue;
/*     */         }
/* 273 */         if (ability.getCategory() == AbilityHelper.getDevilFruitCategory() && AbilityHelper.isAffectedByWater(entity)) {
/*     */           continue;
/*     */         }
/*     */         
/*     */         try {
/* 278 */           if (ability instanceof IOnDamageAbility && IOnDamageAbility.IS_ACTIVE.test(ability)) {
/*     */             
/* 280 */             boolean result = ((IOnDamageAbility)ability).onDamage(entity, event.getSource(), event.getAmount());
/* 281 */             event.setCanceled(!result);
/*     */           } 
/* 283 */           if (ability instanceof DamagedPassiveAbility)
/*     */           {
/* 285 */             boolean result = ((DamagedPassiveAbility)ablProps.getUnlockedAbility(ability)).damage(entity, event.getSource());
/* 286 */             event.setCanceled(!result);
/*     */           }
/*     */         
/* 289 */         } catch (Exception e) {
/*     */           
/* 291 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       
/* 295 */       for (Ability ability : ablProps.getEquippedAbilities()) {
/*     */         
/* 297 */         if (ability != null) {
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 302 */             if (ability instanceof IOnDamageAbility && IOnDamageAbility.IS_ACTIVE.test(ability)) {
/*     */               
/* 304 */               boolean result = ((IOnDamageAbility)ability).onDamage(entity, event.getSource(), event.getAmount());
/* 305 */               event.setCanceled(!result);
/*     */             } 
/*     */             
/* 308 */             if (ability instanceof DamagedContinuousAbility && ability.isContinuous())
/*     */             {
/* 310 */               if (event.getSource() instanceof ModDamageSource && !((ModDamageSource)event.getSource()).isInternalDamage()) {
/*     */                 
/* 312 */                 boolean result = ((DamagedContinuousAbility)ablProps.getUnlockedAbility(ability)).damage(entity, event.getSource(), event.getAmount());
/* 313 */                 event.setCanceled(!result);
/*     */               } 
/*     */             }
/*     */             
/* 317 */             if (ability instanceof IFallDamageBlockingAbility && event.getSource() == DamageSource.FALL) {
/*     */               
/* 319 */               boolean blockFallDamage = !((IFallDamageBlockingAbility)ability).hasFallDamage();
/* 320 */               if (blockFallDamage)
/*     */               {
/* 322 */                 entity.fallDistance = 0.0F;
/* 323 */                 ((IFallDamageBlockingAbility)ability).resetFallDamage(entity);
/* 324 */                 event.setCanceled(true);
/*     */               }
/*     */             
/*     */             } 
/* 328 */           } catch (Exception e) {
/*     */             
/* 330 */             e.printStackTrace();
/*     */           } 
/*     */         }
/*     */       } 
/* 334 */       if (event.getSource().getImmediateSource() instanceof PlayerEntity && (event.getSource().getDamageType().equals("player") || event.getSource().getDamageType().equals("mob")) && event.getAmount() > 0.0F) {
/*     */         
/* 336 */         PlayerEntity attacker = (PlayerEntity)event.getSource().getImmediateSource();
/* 337 */         ablProps = AbilityDataCapability.get((LivingEntity)attacker);
/*     */         
/* 339 */         Arrays.<Ability>stream(ablProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)).filter(Objects::nonNull).forEach(ability -> {
/*     */               try {
/*     */                 if (ability instanceof IChangeDamageSourceAbility) {
/*     */                   IChangeDamageSourceAbility abl = (IChangeDamageSourceAbility)ability;
/*     */                   
/*     */                   if (abl.isSourceChangeEnabled()) {
/*     */                     boolean sameGroup = FactionHelper.getSameGroupPredicate((LivingEntity)attacker).test(entity);
/*     */                     
/*     */                     if (sameGroup) {
/*     */                       return;
/*     */                     }
/*     */                     
/*     */                     double strength = attacker.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/*     */                     
/*     */                     if (strength == 0.0D) {
/*     */                       return;
/*     */                     }
/*     */                     
/*     */                     float damage = (float)(abl.damageToEntityWithSource(attacker, entity) * event.getAmount() / strength);
/*     */                     
/*     */                     DamageSource source = abl.getSourceToUse(attacker);
/*     */                     
/*     */                     boolean damaged = true;
/*     */                     
/*     */                     if (entity.hurtResistantTime == 0 || abl.cancelsOriginalDamage()) {
/*     */                       damaged = entity.attackEntityFrom(source, damage);
/*     */                       entity.hurtTime = entity.hurtResistantTime = 0;
/*     */                     } 
/*     */                     if (!damaged || abl.cancelsOriginalDamage()) {
/*     */                       event.setCanceled(true);
/*     */                     }
/*     */                   } 
/*     */                 } 
/* 372 */               } catch (Exception e) {
/*     */                 e.printStackTrace();
/*     */               } 
/*     */             });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */   public static void onAttackedByPlayer(AttackEntityEvent event) {
/* 385 */     if (!(event.getPlayer()).world.isRemote && event.getTarget() instanceof LivingEntity) {
/*     */       
/* 387 */       PlayerEntity player = event.getPlayer();
/* 388 */       ItemStack heldItem = player.getHeldItemMainhand();
/*     */       
/* 390 */       if (!heldItem.isEmpty()) {
/*     */         return;
/*     */       }
/* 393 */       IEntityStats statProps = EntityStatsCapability.get((LivingEntity)player);
/* 394 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 395 */       LivingEntity target = (LivingEntity)event.getTarget();
/*     */       
/* 397 */       boolean isTargetLogia = DevilFruitCapability.get(target).isLogia();
/* 398 */       boolean isHardeningActive = HakiHelper.hasHardeningActive((LivingEntity)player);
/* 399 */       boolean isTargetSpirit = OutOfBodyAbilitiesEvents.isSpirit(target);
/*     */       
/* 401 */       if (isTargetSpirit) {
/*     */         return;
/*     */       }
/* 404 */       if (isTargetLogia && !isHardeningActive) {
/*     */         return;
/*     */       }
/* 407 */       for (Ability ability : props.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */         
/* 409 */         if (ability != null) {
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 414 */             if (ability instanceof PunchAbility && ability.isContinuous())
/*     */             {
/*     */ 
/*     */               
/* 418 */               float damage = ((PunchAbility)ability).hitEntity(player, target);
/*     */               
/* 420 */               if (damage <= 0.0F) {
/*     */                 
/* 422 */                 event.setCanceled(true);
/*     */                 
/*     */                 return;
/*     */               } 
/* 426 */               float strength = (float)player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/* 427 */               float finalDamage = (damage + strength) * (float)EntityStatsCapability.get((LivingEntity)player).getDamageMultiplier();
/* 428 */               WyDebug.debug("Hardening Haki Punch Damage: " + finalDamage);
/* 429 */               target.attackEntityFrom(((PunchAbility)ability).getPunchDamageSource(player), finalDamage);
/*     */             }
/*     */           
/* 432 */           } catch (Exception e) {
/*     */             
/* 434 */             e.printStackTrace();
/* 435 */             ability.startCooldown(player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayarLogsOut(PlayerEvent.PlayerLoggedOutEvent event) {
/* 444 */     if ((event.getPlayer()).world.isRemote) {
/*     */       return;
/*     */     }
/* 447 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 449 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 451 */     for (Ability ability : props.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */       
/* 453 */       if (ability != null) {
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 458 */           if (ability instanceof ChargeableAbility && ability.isCharging()) {
/* 459 */             ((ChargeableAbility)ability).stopCharging(player);
/*     */           }
/* 461 */           if (ability instanceof ContinuousAbility && ability.isContinuous()) {
/* 462 */             ((ContinuousAbility)ability).stopContinuity(player);
/*     */           }
/* 464 */         } catch (Exception e) {
/*     */           
/* 466 */           e.printStackTrace();
/* 467 */           ability.startCooldown(player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPotionEvent(PotionEvent.PotionApplicableEvent event) {
/* 475 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 478 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */     
/* 480 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 482 */     for (Ability ability : props.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */       
/* 484 */       if (ability == null) {
/*     */         continue;
/*     */       }
/*     */       
/*     */       try {
/* 489 */         if (ability instanceof PotionPassiveAbility)
/*     */         {
/* 491 */           boolean applied = ((PotionPassiveAbility)props.getUnlockedAbility(ability)).check(player, event.getPotionEffect());
/* 492 */           if (applied) {
/* 493 */             event.setResult(Event.Result.ALLOW); continue;
/*     */           } 
/* 495 */           event.setResult(Event.Result.DENY);
/*     */         }
/*     */       
/* 498 */       } catch (Exception e) {
/*     */         
/* 500 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityShootProjectile(ArrowLooseEvent event) {
/* 508 */     if (event.getPlayer() != null) {
/*     */       
/* 510 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)event.getPlayer());
/*     */       
/* 512 */       for (Ability abl : props.getEquippedAbilities()) {
/*     */         
/* 514 */         if (abl instanceof ISniperAbility && props.hasEquippedAbility(abl) && props.getEquippedAbility(abl).isContinuous()) {
/*     */           
/* 516 */           ((ISniperAbility)props.getEquippedAbility(abl)).shoot(event.getPlayer());
/* 517 */           props.getEquippedAbility(abl).use(event.getPlayer());
/* 518 */           event.setCanceled(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilitiesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */