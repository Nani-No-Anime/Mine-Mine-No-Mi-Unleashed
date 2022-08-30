/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.CombatRules;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.RenderMorphEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class HakiPassiveEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onItemChange(LivingEquipmentChangeEvent event) {
/*  58 */     LivingEntity entity = event.getEntityLiving();
/*  59 */     ItemStack stack = event.getTo();
/*  60 */     BusoshokuHakiImbuingAbility.tryApplyingImbuingBonus(entity, stack);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public static void onEntityAttackEvent(LivingAttackEvent event) {
/*  66 */     if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntity()).world.isRemote) {
/*     */       return;
/*     */     }
/*  69 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  70 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*  71 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*  72 */     int hakiOveruse = 10 + (int)(HakiHelper.getMaxOveruse(player) / 1180.0F);
/*     */     
/*  74 */     KenbunshokuHakiFutureSightAbility futureSight = (KenbunshokuHakiFutureSightAbility)abilityProps.getEquippedAbility((Ability)KenbunshokuHakiFutureSightAbility.INSTANCE);
/*  75 */     boolean isActive = (futureSight != null && futureSight.isContinuous());
/*     */     
/*  77 */     if (!isActive) {
/*     */       return;
/*     */     }
/*  80 */     float amount = event.getAmount();
/*  81 */     DamageSource damageSource = event.getSource();
/*  82 */     ArrayList<DamageSource> damageableSources = new ArrayList<>(Arrays.asList(new DamageSource[] { DamageSource.LAVA, DamageSource.IN_WALL, DamageSource.CACTUS, DamageSource.SWEET_BERRY_BUSH, DamageSource.STARVE, DamageSource.ANVIL, DamageSource.FLY_INTO_WALL, DamageSource.FALL, DamageSource.FALLING_BLOCK, DamageSource.OUT_OF_WORLD, DamageSource.WITHER, DamageSource.MAGIC, DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.LIGHTNING_BOLT }));
/*     */     
/*  84 */     boolean isLogia = DevilFruitCapability.get((LivingEntity)player).isLogia();
/*     */     
/*  86 */     LogiaInvulnerabilityAbility invulnerabilityInstance = null;
/*  87 */     if (isLogia) {
/*     */       
/*  89 */       List<Ability> unlockedAbilities = abilityProps.getUnlockedAbilities(AbilityHelper.getDevilFruitCategory());
/*  90 */       for (Ability ability : unlockedAbilities) {
/*     */         
/*  92 */         if (ability == null) {
/*     */           continue;
/*     */         }
/*  95 */         if (ability instanceof LogiaInvulnerabilityAbility) {
/*     */           
/*  97 */           invulnerabilityInstance = (LogiaInvulnerabilityAbility)ability;
/*  98 */           if (!invulnerabilityInstance.onDamagedEvent((LivingEntity)player, damageSource)) {
/*     */             
/* 100 */             event.setCanceled(true);
/*     */             return;
/*     */           } 
/* 103 */           damageableSources.removeAll(invulnerabilityInstance.immunitySources);
/*     */         } 
/*     */       } 
/*     */       
/* 107 */       hakiOveruse /= 3;
/*     */     
/*     */     }
/* 110 */     else if (damageSource.isExplosion()) {
/*     */       return;
/*     */     } 
/*     */     
/* 114 */     if (player.hurtResistantTime > 0 && !damageableSources.contains(damageSource) && !damageSource.getDamageType().equals("special") && !isLogia) {
/*     */       
/* 116 */       hakiProps.alterHakiOveruse(hakiOveruse * 4);
/* 117 */       event.setCanceled(true);
/*     */       
/*     */       return;
/*     */     } 
/* 121 */     if (!damageSource.isUnblockable())
/* 122 */       amount = CombatRules.getDamageAfterAbsorb(amount, player.getTotalArmorValue(), (float)player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getValue()); 
/* 123 */     int absorbed = EnchantmentHelper.getEnchantmentModifierDamage(player.getArmorInventoryList(), damageSource);
/* 124 */     if (absorbed > 0) amount = CombatRules.getDamageAfterMagicAbsorb(amount, absorbed);
/*     */     
/* 126 */     if (0.0F > amount) {
/*     */       
/* 128 */       event.setCanceled(true);
/*     */       
/*     */       return;
/*     */     } 
/* 132 */     boolean baseCondition = damageableSources.stream().noneMatch(s -> damageSource.getDamageType().equals(s.getDamageType()));
/* 133 */     boolean notInternal = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isInternalDamage());
/* 134 */     if (baseCondition && !notInternal) {
/*     */       
/* 136 */       futureSight.reduceProtection(amount);
/* 137 */       hakiProps.alterHakiOveruse(hakiOveruse * 4 + (int)(amount * hakiOveruse));
/* 138 */       Objects.requireNonNull(player); player.hurtResistantTime = player.hurtTime = 20;
/* 139 */       if (invulnerabilityInstance != null)
/* 140 */         invulnerabilityInstance.particleEffect.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D); 
/* 141 */       event.setCanceled(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/* 148 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/*     */       
/* 150 */       PlayerEntity playerEntity = (PlayerEntity)event.getEntityLiving();
/* 151 */       ItemStack heldItem = playerEntity.getHeldItemMainhand();
/*     */       
/* 153 */       if (!heldItem.isEmpty() && heldItem.isDamageable() && !(heldItem.getItem()).properties.containsKey(new ResourceLocation("haki")))
/*     */       {
/* 155 */         if (HakiHelper.hasImbuingActive((LivingEntity)playerEntity) && !heldItem.getOrCreateTag().getBoolean("imbuingHakiActive")) {
/* 156 */           heldItem.getOrCreateTag().putBoolean("imbuingHakiActive", true);
/* 157 */         } else if (heldItem.getOrCreateTag().contains("imbuingHakiActive") && !HakiHelper.hasImbuingActive((LivingEntity)playerEntity) && heldItem.getOrCreateTag().getBoolean("imbuingHakiActive")) {
/* 158 */           heldItem.getOrCreateTag().remove("imbuingHakiActive");
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemTossed(ItemTossEvent event) {
/* 167 */     ItemStack stack = event.getEntityItem().getItem();
/* 168 */     if (stack != null && !stack.isEmpty() && stack.hasTag() && stack.getTag().getBoolean("imbuingHakiActive")) {
/* 169 */       stack.getOrCreateTag().remove("imbuingHakiActive");
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
/* 175 */     if (event.phase != TickEvent.Phase.END) {
/*     */       return;
/*     */     }
/* 178 */     PlayerEntity player = event.player;
/*     */     
/* 180 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/* 183 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/* 184 */     float maxOveruse = HakiHelper.getMaxOveruse(player);
/* 185 */     float hakiOveruse = hakiProps.getHakiOveruse();
/*     */     
/* 187 */     if (hakiOveruse >= maxOveruse * 0.9D) {
/*     */       
/* 189 */       player.addPotionEffect(new EffectInstance(ModEffects.HAKI_OVERUSE, 40, 0));
/* 190 */       player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 40, 0, false, false));
/* 191 */       player.addPotionEffect(new EffectInstance(Effects.HUNGER, 40, 1, false, false));
/* 192 */       if (hakiOveruse >= maxOveruse * 0.95D) {
/*     */         
/* 194 */         player.addPotionEffect(new EffectInstance(ModEffects.HAKI_OVERUSE, 80, 1));
/* 195 */         player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 80, 1, false, false));
/* 196 */         if (hakiOveruse >= maxOveruse) {
/*     */           
/* 198 */           player.addPotionEffect(new EffectInstance(ModEffects.HAKI_OVERUSE, 100, 2));
/* 199 */           player.addPotionEffect(new EffectInstance(Effects.HUNGER, 100, 2, false, false));
/* 200 */           player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 2, false, false));
/* 201 */           player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 2, false, false));
/* 202 */           player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     IAbilityData attackerAbilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 208 */     Ability auraHaki = attackerAbilityProps.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
/* 209 */     boolean hasAuraHakiActive = (auraHaki != null && auraHaki.isContinuous());
/* 210 */     Ability fsHaki = attackerAbilityProps.getEquippedAbility((Ability)KenbunshokuHakiFutureSightAbility.INSTANCE);
/* 211 */     boolean hasfsHakiActive = (fsHaki != null && fsHaki.isContinuous());
/*     */     
/* 213 */     boolean hasBusoActive = HakiHelper.hasAnyHakiEnabled((LivingEntity)player);
/* 214 */     boolean hasKenActive = (hasAuraHakiActive || hasfsHakiActive);
/*     */     
/* 216 */     Ability imbuingHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/* 217 */     boolean hasImbuingHakiActive = (imbuingHaki != null && imbuingHaki.isContinuous());
/*     */     
/* 219 */     if (hasBusoActive || hasKenActive || hasImbuingHakiActive) {
/*     */       return;
/*     */     }
/* 222 */     int ticksToHeal = HakiHelper.hasAnyHakiEnabled((LivingEntity)player) ? 300 : 20;
/* 223 */     if (player.ticksExisted % ticksToHeal != 0) {
/*     */       return;
/*     */     }
/* 226 */     int overuseHeal = -Math.max(5, (int)(HakiHelper.getTotalHakiExp((LivingEntity)player) / 30.0F)) * 3;
/* 227 */     hakiProps.alterHakiOveruse(overuseHeal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onZoanRendered(RenderMorphEvent.Pre event) {
/* 236 */     if (event.getEntity() == null) {
/*     */       return;
/*     */     }
/* 239 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/* 241 */     if (entity.isPotionActive(ModEffects.UNCONSCIOUS)) {
/*     */       
/* 243 */       if (entity.getActivePotionEffect(ModEffects.UNCONSCIOUS).getDuration() <= 0) {
/* 244 */         entity.removePotionEffect(ModEffects.UNCONSCIOUS);
/*     */       }
/* 246 */       rotateEntityModel(entity, event.getMatrixStack(), 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 254 */     if (event.getEntity() == null) {
/*     */       return;
/*     */     }
/* 257 */     LivingEntity entity = event.getEntity();
/*     */     
/* 259 */     if (entity.isPotionActive(ModEffects.UNCONSCIOUS) && Strings.isNullOrEmpty(DevilFruitCapability.get(entity).getZoanPoint())) {
/*     */       
/* 261 */       if (entity.getActivePotionEffect(ModEffects.UNCONSCIOUS).getDuration() <= 0) {
/* 262 */         entity.removePotionEffect(ModEffects.UNCONSCIOUS);
/*     */       }
/* 264 */       float angle = (entity.getEntityId() % 8);
/* 265 */       rotateEntityModel(entity, event.getMatrixStack(), angle);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private static void rotateEntityModel(LivingEntity entity, MatrixStack matrixStack, float angle) {
/* 272 */     if (angle == 0.0F) {
/*     */       
/* 274 */       matrixStack.rotate(new Quaternion(Vector3f.XP, 90.0F, true));
/* 275 */       entity.rotationYawHead = 0.0F;
/* 276 */       entity.prevRotationYawHead = 0.0F;
/*     */     }
/* 278 */     else if (angle == 1.0F) {
/*     */       
/* 280 */       matrixStack.rotate(new Quaternion(Vector3f.YP, 45.0F, true));
/* 281 */       matrixStack.rotate(new Quaternion(Vector3f.ZP, 90.0F, true));
/* 282 */       entity.rotationYawHead = 45.0F;
/* 283 */       entity.prevRotationYawHead = 45.0F;
/*     */     }
/* 285 */     else if (angle == 2.0F) {
/*     */       
/* 287 */       matrixStack.rotate(new Quaternion(Vector3f.ZP, 90.0F, true));
/* 288 */       matrixStack.rotate(new Quaternion(Vector3f.YN, 90.0F, true));
/* 289 */       entity.rotationYawHead = 90.0F;
/* 290 */       entity.prevRotationYawHead = 90.0F;
/*     */     }
/* 292 */     else if (angle == 3.0F) {
/*     */       
/* 294 */       matrixStack.rotate(new Quaternion(Vector3f.YP, 45.0F, true));
/* 295 */       matrixStack.rotate(new Quaternion(Vector3f.XN, 90.0F, true));
/* 296 */       entity.rotationYawHead = 90.0F;
/* 297 */       entity.prevRotationYawHead = 90.0F;
/*     */     }
/* 299 */     else if (angle == 4.0F) {
/*     */       
/* 301 */       matrixStack.rotate(new Quaternion(Vector3f.XN, 90.0F, true));
/* 302 */       matrixStack.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/* 303 */       entity.rotationYawHead = 90.0F;
/* 304 */       entity.prevRotationYawHead = 90.0F;
/*     */     }
/* 306 */     else if (angle == 5.0F) {
/*     */       
/* 308 */       matrixStack.rotate(new Quaternion(Vector3f.YP, 45.0F, true));
/* 309 */       matrixStack.rotate(new Quaternion(Vector3f.ZN, 90.0F, true));
/* 310 */       entity.rotationYawHead = 90.0F;
/* 311 */       entity.prevRotationYawHead = 90.0F;
/*     */     }
/* 313 */     else if (angle == 6.0F) {
/*     */       
/* 315 */       matrixStack.rotate(new Quaternion(Vector3f.ZN, 90.0F, true));
/* 316 */       matrixStack.rotate(new Quaternion(Vector3f.YP, 90.0F, true));
/* 317 */       entity.rotationYawHead = 360.0F;
/* 318 */       entity.prevRotationYawHead = 360.0F;
/*     */     }
/* 320 */     else if (angle == 7.0F) {
/*     */       
/* 322 */       matrixStack.rotate(new Quaternion(Vector3f.YN, 45.0F, true));
/* 323 */       matrixStack.rotate(new Quaternion(Vector3f.ZN, 90.0F, true));
/* 324 */       entity.rotationYawHead = 360.0F;
/* 325 */       entity.prevRotationYawHead = 360.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onFogRendered(EntityViewRenderEvent.FogDensity event) {
/* 333 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 334 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*     */     
/* 336 */     Ability ability = props.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
/* 337 */     boolean isActive = (ability != null && ability.isContinuous());
/*     */     
/* 339 */     if (isActive && clientPlayerEntity.isPotionActive(Effects.BLINDNESS)) {
/*     */       
/* 341 */       event.setCanceled(true);
/* 342 */       event.setDensity(1.0E-4F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\HakiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */