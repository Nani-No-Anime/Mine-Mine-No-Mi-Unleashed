/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.events.HakiGainEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HakiHelper
/*     */ {
/*     */   enum HakiRank
/*     */   {
/*  44 */     BEGINNER,
/*  45 */     INITIATE,
/*  46 */     ADEPT,
/*  47 */     PROFICIENT,
/*  48 */     MASTER;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isHaoshokuBorn(PlayerEntity player) {
/*  53 */     boolean isKing = false;
/*     */     
/*  55 */     String[] bits = ("" + player.getUniqueID().getMostSignificantBits()).split("");
/*  56 */     int playerBitsSum = 0;
/*  57 */     for (String bit : bits) {
/*     */       
/*  59 */       if (!bit.equalsIgnoreCase("-"))
/*     */       {
/*  61 */         playerBitsSum += Integer.parseInt(bit); } 
/*     */     } 
/*  63 */     playerBitsSum = MathHelper.clamp(playerBitsSum & 0xA, 0, 10);
/*     */     
/*  65 */     if (CommonConfig.INSTANCE.getHaoshokuUnlockLogic() == CommonConfig.HaoshokuUnlockLogic.TRUE_RANDOM) {
/*     */       
/*  67 */       String[] seedBits = String.valueOf(player.world.getSeed()).split("");
/*  68 */       int worldBitsSum = 0;
/*  69 */       for (String bit : seedBits) {
/*     */         
/*  71 */         if (!bit.equalsIgnoreCase("-"))
/*     */         {
/*  73 */           worldBitsSum += Integer.parseInt(bit); } 
/*     */       } 
/*  75 */       worldBitsSum = MathHelper.clamp(worldBitsSum & 0xA, 0, 10);
/*  76 */       isKing = (playerBitsSum == worldBitsSum);
/*     */     }
/*     */     else {
/*     */       
/*  80 */       isKing = (playerBitsSum <= 1);
/*     */     } 
/*     */     
/*  83 */     return isKing;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canItemGainImbuing(@Nullable ItemStack itemStack) {
/*  88 */     if (itemStack == null || itemStack.isEmpty()) {
/*  89 */       return false;
/*     */     }
/*  91 */     if (ItemsHelper.isSword(itemStack)) {
/*  92 */       return true;
/*     */     }
/*  94 */     boolean hasDamageAttribute = (itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).size() > 0);
/*     */     
/*  96 */     return hasDamageAttribute;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBusoshokuHardeningExpNeeded(PlayerEntity player) {
/* 101 */     Random rand = player.getRNG();
/* 102 */     rand.setSeed(player.getUniqueID().getMostSignificantBits());
/* 103 */     return 30.0D + WyHelper.randomWithRange(rand, -2, 25);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBusoshokuFullBodyExpNeeded(PlayerEntity player) {
/* 108 */     Random rand = player.getRNG();
/* 109 */     rand.setSeed(player.getUniqueID().getMostSignificantBits());
/* 110 */     return 50.0D + WyHelper.randomWithRange(rand, 0, 20);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBusoshokuImbuingExpNeeded(PlayerEntity player) {
/* 115 */     Random rand = player.getRNG();
/* 116 */     rand.setSeed(player.getUniqueID().getMostSignificantBits());
/* 117 */     return 30.0D + WyHelper.randomWithRange(rand, -5, 20);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getKenbunshokuAuraExpNeeded(PlayerEntity player) {
/* 122 */     Random rand = player.getRNG();
/* 123 */     rand.setSeed(player.getUniqueID().getMostSignificantBits());
/* 124 */     return 30.0D + WyHelper.randomWithRange(rand, -5, 20);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getKenbunshokuFutureSightExpNeeded(PlayerEntity player) {
/* 129 */     Random rand = player.getRNG();
/* 130 */     rand.setSeed(player.getUniqueID().getMostSignificantBits());
/* 131 */     return 50.0D + WyHelper.randomWithRange(rand, 0, 30);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getHakiRank(HakiType type, PlayerEntity player) {
/* 136 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/* 137 */     IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
/* 138 */     HakiRank rank = HakiRank.values()[0];
/*     */     
/* 140 */     float exp = 0.0F;
/* 141 */     boolean check = false;
/*     */     
/* 143 */     if (type == HakiType.HARDENING) {
/*     */       
/* 145 */       exp = hakiData.getBusoshokuHardeningHakiExp();
/* 146 */       check = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
/*     */     }
/* 148 */     else if (type == HakiType.IMBUING) {
/*     */       
/* 150 */       exp = hakiData.getBusoshokuImbuingHakiExp();
/* 151 */       check = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/*     */     }
/* 153 */     else if (type == HakiType.KENBUNSHOKU) {
/*     */       
/* 155 */       exp = hakiData.getKenbunshokuHakiExp();
/* 156 */       check = abilityData.hasUnlockedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
/*     */     } 
/*     */     
/* 159 */     if (exp >= 0.0F && exp < 15.0F) {
/* 160 */       rank = HakiRank.BEGINNER;
/* 161 */     } else if (exp >= 15.0F && check) {
/*     */       
/* 163 */       if (exp >= 15.0F && exp < 30.0F) {
/* 164 */         rank = HakiRank.INITIATE;
/* 165 */       } else if (exp >= 30.0F && exp < 50.0F) {
/* 166 */         rank = HakiRank.ADEPT;
/* 167 */       } else if (exp >= 50.0F && exp < 90.0F) {
/* 168 */         rank = HakiRank.PROFICIENT;
/* 169 */       } else if (exp >= 90.0F) {
/* 170 */         rank = HakiRank.MASTER;
/*     */       } 
/*     */     } 
/* 173 */     return (new TranslationTextComponent(ModI18n.TRAINER_HAKI_RANK, new Object[] { rank })).getFormattedText();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static String getHakiProgress(HakiType type, PlayerEntity player) {
/* 179 */     IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
/* 180 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 182 */     if (type == HakiType.HARDENING) {
/*     */       
/* 184 */       float f = hakiData.getBusoshokuHardeningHakiExp();
/* 185 */       boolean hasHardening = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
/* 186 */       boolean hasFullbody = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/*     */       
/* 188 */       if (!hasHardening) {
/*     */         
/* 190 */         String translatedAbility = (new TranslationTextComponent(BusoshokuHakiHardeningAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
/* 191 */         int avg = (int)(40.0F - f);
/* 192 */         if (avg > 0) {
/* 193 */           return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
/*     */         }
/* 195 */         return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
/*     */       } 
/* 197 */       if (!hasFullbody) {
/*     */         
/* 199 */         String translatedAbility = (new TranslationTextComponent(BusoshokuHakiFullBodyHardeningAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
/* 200 */         int avg = (int)(60.0F - f);
/* 201 */         if (avg > 0) {
/* 202 */           return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
/*     */         }
/* 204 */         return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
/*     */       } 
/*     */       
/* 207 */       return (new TranslationTextComponent(ModI18n.TRAINER_FINISHED_HARDENING_TRAINING, new Object[0])).getFormattedText();
/*     */     } 
/* 209 */     if (type == HakiType.IMBUING) {
/*     */       
/* 211 */       float f = hakiData.getBusoshokuImbuingHakiExp();
/* 212 */       boolean hasImbuing = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/*     */       
/* 214 */       if (!hasImbuing) {
/*     */         
/* 216 */         String translatedAbility = (new TranslationTextComponent(BusoshokuHakiImbuingAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
/* 217 */         int avg = (int)(40.0F - f);
/* 218 */         if (avg > 0) {
/* 219 */           return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
/*     */         }
/* 221 */         return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
/*     */       } 
/*     */       
/* 224 */       return (new TranslationTextComponent(ModI18n.TRAINER_FINISHED_IMBUING_TRAINING, new Object[0])).getFormattedText();
/*     */     } 
/*     */ 
/*     */     
/* 228 */     float exp = hakiData.getKenbunshokuHakiExp();
/* 229 */     boolean hasAura = abilityData.hasUnlockedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
/* 230 */     boolean hasFutureSight = abilityData.hasUnlockedAbility((Ability)KenbunshokuHakiFutureSightAbility.INSTANCE);
/*     */     
/* 232 */     if (!hasAura) {
/*     */       
/* 234 */       String translatedAbility = (new TranslationTextComponent(KenbunshokuHakiAuraAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
/* 235 */       int avg = (int)(40.0F - exp);
/* 236 */       if (avg > 0) {
/* 237 */         return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
/*     */       }
/* 239 */       return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
/*     */     } 
/* 241 */     if (!hasFutureSight) {
/*     */       
/* 243 */       String translatedAbility = (new TranslationTextComponent(KenbunshokuHakiFutureSightAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
/* 244 */       int avg = (int)(75.0F - exp);
/* 245 */       if (avg > 0) {
/* 246 */         return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
/*     */       }
/* 248 */       return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
/*     */     } 
/*     */     
/* 251 */     return (new TranslationTextComponent(ModI18n.TRAINER_FINISHED_OBSERVATION_TRAINING, new Object[0])).getFormattedText();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkForUnlocks(PlayerEntity player) {
/* 257 */     IHakiData props = HakiDataCapability.get((LivingEntity)player);
/* 258 */     IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/* 260 */     if (statsProps.getDoriki() > 4000 && props.getBusoshokuHardeningHakiExp() > getBusoshokuHardeningExpNeeded(player)) {
/*     */       
/* 262 */       HakiGainEvents.giveHakiAbility(player, (Ability)BusoshokuHakiHardeningAbility.INSTANCE);
/*     */       
/* 264 */       if (statsProps.getDoriki() > 5000 && props.getBusoshokuHardeningHakiExp() > getBusoshokuFullBodyExpNeeded(player)) {
/* 265 */         HakiGainEvents.giveHakiAbility(player, (Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/*     */       }
/*     */     } 
/* 268 */     if (statsProps.getDoriki() > 4500 && props.getBusoshokuImbuingHakiExp() > getBusoshokuImbuingExpNeeded(player)) {
/* 269 */       HakiGainEvents.giveHakiAbility(player, (Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/*     */     }
/* 271 */     if (statsProps.getDoriki() > 1500 && props.getKenbunshokuHakiExp() > getKenbunshokuAuraExpNeeded(player)) {
/*     */       
/* 273 */       HakiGainEvents.giveHakiAbility(player, (Ability)KenbunshokuHakiAuraAbility.INSTANCE);
/* 274 */       if (statsProps.getDoriki() > 6000 && props.getKenbunshokuHakiExp() > getKenbunshokuFutureSightExpNeeded(player)) {
/* 275 */         HakiGainEvents.giveHakiAbility(player, (Ability)KenbunshokuHakiFutureSightAbility.INSTANCE);
/*     */       }
/*     */     } 
/* 278 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncHakiDataPacket(player.getEntityId(), props), (LivingEntity)player);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canUseHaki(PlayerEntity player, Ability ability) {
/* 283 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 285 */     for (Ability abl : props.getEquippedAbilities(AbilityHelper.getHakiCategory())) {
/*     */       
/* 287 */       if (abl != null && abl instanceof ContinuousAbility) {
/*     */         
/* 289 */         ContinuousAbility hakiAbility = (ContinuousAbility)abl;
/*     */         
/* 291 */         if (hakiAbility.isContinuous()) {
/*     */ 
/*     */           
/* 294 */           if (getHakiType((Ability)hakiAbility) == HakiType.HARDENING && getHakiType(ability) == HakiType.HARDENING) {
/* 295 */             return false;
/*     */           }
/* 297 */           if (getHakiType((Ability)hakiAbility) == HakiType.IMBUING && getHakiType(ability) == HakiType.IMBUING) {
/* 298 */             return false;
/*     */           }
/* 300 */           if (getHakiType((Ability)hakiAbility) == HakiType.KENBUNSHOKU && getHakiType(ability) == HakiType.KENBUNSHOKU)
/* 301 */             return false; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 305 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasAnyHakiEnabled(LivingEntity entity) {
/* 310 */     IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
/* 311 */     Ability busoHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
/* 312 */     boolean hasBusoHakiActive = (busoHaki != null && busoHaki.isContinuous());
/*     */     
/* 314 */     Ability fullBodyBusoHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/* 315 */     boolean hasFullBodyBusoHakiActive = (fullBodyBusoHaki != null && fullBodyBusoHaki.isContinuous());
/*     */     
/* 317 */     Ability imbuingHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/* 318 */     ItemStack heldItem = entity.getHeldItemMainhand();
/* 319 */     boolean hasImbuingBusoHakiActive = (!heldItem.isEmpty() && imbuingHaki != null && imbuingHaki.isContinuous());
/*     */     
/* 321 */     boolean hasNPCBusoActive = (entity instanceof OPEntity && ((OPEntity)entity).hasBusoHaki());
/*     */     
/* 323 */     return (hasBusoHakiActive || hasFullBodyBusoHakiActive || hasNPCBusoActive || hasImbuingBusoHakiActive);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasHardeningActive(LivingEntity entity) {
/* 328 */     if (entity instanceof PlayerEntity) {
/*     */       
/* 330 */       IAbilityData props = AbilityDataCapability.get(entity);
/*     */       
/* 332 */       Ability busoHaki = props.getEquippedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
/* 333 */       boolean hasBusoHakiActive = (busoHaki != null && busoHaki.isContinuous());
/*     */       
/* 335 */       Ability fullBodyBusoHaki = props.getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/* 336 */       boolean hasFullBodyBusoHakiActive = (fullBodyBusoHaki != null && fullBodyBusoHaki.isContinuous());
/*     */       
/* 338 */       return (hasBusoHakiActive || hasFullBodyBusoHakiActive);
/*     */     } 
/* 340 */     if (entity instanceof OPEntity) {
/*     */       
/* 342 */       boolean hasNPCBusoActive = ((OPEntity)entity).hasBusoHaki();
/*     */       
/* 344 */       return hasNPCBusoActive;
/*     */     } 
/*     */ 
/*     */     
/* 348 */     IAttributeInstance attrAtk = entity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
/* 349 */     double atk = (attrAtk != null) ? attrAtk.getBaseValue() : 0.0D;
/*     */     
/* 351 */     boolean hasDoriki = (EntityStatsCapability.get(entity).getDoriki() >= 5000);
/* 352 */     boolean hasPseudoHaki = (atk >= ((entity.world.getDifficulty() == Difficulty.HARD) ? 5 : 7));
/*     */     
/* 354 */     if (hasPseudoHaki || hasDoriki) {
/* 355 */       return true;
/*     */     }
/*     */     
/* 358 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasImbuingActive(LivingEntity entity) {
/* 363 */     if (entity instanceof PlayerEntity) {
/*     */       
/* 365 */       IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
/* 366 */       BusoshokuHakiImbuingAbility imbuingHaki = (BusoshokuHakiImbuingAbility)attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/* 367 */       boolean hasImbuingBusoHakiActive = (imbuingHaki != null && imbuingHaki.isContinuous());
/*     */       
/* 369 */       return hasImbuingBusoHakiActive;
/*     */     } 
/* 371 */     if (entity instanceof OPEntity) {
/*     */       
/* 373 */       boolean hasNPCBusoActive = ((OPEntity)entity).hasBusoHaki();
/*     */       
/* 375 */       return hasNPCBusoActive;
/*     */     } 
/*     */     
/* 378 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static HakiType getHakiType(Ability ability) {
/* 383 */     if (ability instanceof IHakiAbility) {
/* 384 */       return ((IHakiAbility)ability).getType();
/*     */     }
/* 386 */     return HakiType.HARDENING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static float getTotalHakiExp(LivingEntity player) {
/* 393 */     return HakiDataCapability.get(player).getTotalHakiExp();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static float getBusoHardeningHakiExp(LivingEntity player) {
/* 400 */     return HakiDataCapability.get(player).getBusoshokuHardeningHakiExp();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean checkForHakiOveruse(PlayerEntity player, int overuseBonus) {
/* 405 */     if (player.world.isRemote) {
/* 406 */       return false;
/*     */     }
/* 408 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*     */     
/* 410 */     if (overuseBonus != -1) {
/* 411 */       hakiProps.alterHakiOveruse(1 + overuseBonus);
/*     */     }
/* 413 */     return !canEnableHaki(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canEnableHaki(PlayerEntity player) {
/* 418 */     return (HakiDataCapability.get((LivingEntity)player).getHakiOveruse() < getMaxOveruse(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getMaxOveruse(PlayerEntity player) {
/* 423 */     return 2200.0F + getTotalHakiExp((LivingEntity)player) * 32.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\HakiHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */