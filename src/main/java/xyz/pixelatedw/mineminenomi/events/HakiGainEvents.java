/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class HakiGainEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  45 */     if (event.getEntityLiving() instanceof PlayerEntity && !(event.getEntityLiving()).world.isRemote) {
/*     */       
/*  47 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  48 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  49 */       IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*  50 */       float hakiMultiplier = (float)CommonConfig.INSTANCE.getHakiExpMultiplier();
/*     */       
/*  52 */       player.world.getProfiler().startSection("hakiExpGain");
/*     */       
/*  54 */       KenbunshokuHakiAuraAbility ability = (KenbunshokuHakiAuraAbility)props.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
/*  55 */       if (ability != null && ability.isContinuous() && hakiProps.getKenbunshokuHakiExp() >= 60.0F && player.ticksExisted % 600 == 0 && player.getHealth() < WyHelper.percentage(20.0D, player.getMaxHealth())) {
/*     */         
/*  57 */         float finalHakiExp = 0.025F * hakiMultiplier;
/*  58 */         hakiProps.alterKenbunshokuHakiExp(finalHakiExp);
/*  59 */         HakiExpEvent e = new HakiExpEvent(player, finalHakiExp, HakiType.KENBUNSHOKU);
/*  60 */         MinecraftForge.EVENT_BUS.post((Event)e);
/*     */       } 
/*     */       
/*  63 */       if (CommonConfig.INSTANCE.isHaoshokuUnlockLogicExpBased() && player.ticksExisted % 1200 == 0 && !props.hasUnlockedAbility((Ability)HaoshokuHakiAbility.INSTANCE)) {
/*     */         
/*  65 */         float totalPossible = (CommonConfig.INSTANCE.getHakiExpLimit() * 3);
/*     */         
/*  67 */         float totalExp = HakiHelper.getTotalHakiExp((LivingEntity)player);
/*  68 */         float totalCheck = (float)(150.0D + WyHelper.randomWithRange(-50, 120));
/*  69 */         totalCheck = MathHelper.clamp(totalCheck, 0.0F, totalPossible);
/*     */         
/*  71 */         if (totalExp >= totalCheck) {
/*  72 */           giveHakiAbility(player, (Ability)HaoshokuHakiAbility.INSTANCE);
/*     */         }
/*     */       } 
/*  75 */       player.world.getProfiler().endSection();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttack(LivingHurtEvent event) {
/*  82 */     if (event.getAmount() < 1.0F || !(event.getEntityLiving() instanceof PlayerEntity) || !(event.getSource().getTrueSource() instanceof LivingEntity) || (event.getEntityLiving()).world.isRemote || event.getSource().isExplosion()) {
/*     */       return;
/*     */     }
/*  85 */     if (event.getSource().getImmediateSource() instanceof AbilityProjectileEntity) {
/*     */       
/*  87 */       AbilityProjectileEntity entity = (AbilityProjectileEntity)event.getSource().getImmediateSource();
/*  88 */       if (!entity.isPhysical()) {
/*     */         return;
/*     */       }
/*     */     } 
/*  92 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  93 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*  94 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  96 */     player.world.getProfiler().startSection("hakiExpGain");
/*     */     
/*  98 */     float hakiMultiplier = (float)CommonConfig.INSTANCE.getHakiExpMultiplier();
/*     */     
/* 100 */     KenbunshokuHakiAuraAbility ability = (KenbunshokuHakiAuraAbility)abilityProps.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
/* 101 */     if ((ability != null && ability.isContinuous()) || hakiProps.getKenbunshokuHakiExp() <= 10.0D + HakiHelper.getKenbunshokuAuraExpNeeded(player)) {
/*     */       
/* 103 */       float exp = event.getAmount() / (20.0F + 100.0F * hakiProps.getKenbunshokuHakiExp() / 100.0F);
/* 104 */       if (exp <= 0.0F) {
/* 105 */         exp = 1.0E-5F;
/*     */       }
/* 107 */       float finalHakiExp = exp * hakiMultiplier;
/* 108 */       hakiProps.alterKenbunshokuHakiExp(finalHakiExp);
/* 109 */       HakiExpEvent e = new HakiExpEvent(player, finalHakiExp, HakiType.KENBUNSHOKU);
/* 110 */       MinecraftForge.EVENT_BUS.post((Event)e);
/*     */     } 
/*     */     
/* 113 */     player.world.getProfiler().endSection();
/*     */   }
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
/*     */   @SubscribeEvent
/*     */   public static void onEntityDeath(LivingDeathEvent event) {
/* 159 */     if (!(event.getSource().getTrueSource() instanceof PlayerEntity) || (event.getSource().getTrueSource()).world.isRemote) {
/*     */       return;
/*     */     }
/* 162 */     if (event.getSource().getImmediateSource() instanceof AbilityProjectileEntity) {
/*     */       
/* 164 */       AbilityProjectileEntity entity = (AbilityProjectileEntity)event.getSource().getImmediateSource();
/* 165 */       if (!entity.isPhysical()) {
/*     */         return;
/*     */       }
/*     */     } 
/* 169 */     PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
/* 170 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/* 171 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 172 */     ItemStack heldStack = player.getHeldItem(player.getActiveHand());
/* 173 */     Random rand = player.getRNG();
/* 174 */     rand.setSeed(player.getUniqueID().getMostSignificantBits());
/*     */     
/* 176 */     player.world.getProfiler().startSection("hakiExpGain");
/*     */     
/* 178 */     float hakiMultiplier = (float)CommonConfig.INSTANCE.getHakiExpMultiplier();
/* 179 */     IAttributeInstance attrAtk = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
/* 180 */     IAttributeInstance attrHP = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
/* 181 */     double atk = (attrAtk != null) ? attrAtk.getBaseValue() : 0.0D;
/* 182 */     double hp = (attrHP != null) ? attrHP.getBaseValue() : 0.0D;
/*     */ 
/*     */     
/* 185 */     float expValue = (event.getEntityLiving() instanceof xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity) ? ((float)(atk + hp) * 1.5F) : Math.min((float)(atk + hp), 50.0F);
/*     */     
/* 187 */     if (!heldStack.isEmpty()) {
/*     */       
/* 189 */       if (expValue < hakiProps.getBusoshokuImbuingHakiExp()) {
/*     */         return;
/*     */       }
/* 192 */       BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/* 193 */       if ((ability != null && ability.isContinuous()) || hakiProps.getBusoshokuImbuingHakiExp() <= 10.0D + HakiHelper.getBusoshokuImbuingExpNeeded(player))
/*     */       {
/* 195 */         float exp = expValue / (1200.0F + 3600.0F * hakiProps.getBusoshokuImbuingHakiExp() / 100.0F);
/* 196 */         if (exp <= 0.0F) {
/* 197 */           exp = 1.0E-4F;
/*     */         }
/* 199 */         float finalHakiExp = exp * hakiMultiplier;
/*     */         
/* 201 */         if (!HakiHelper.canItemGainImbuing(heldStack)) {
/* 202 */           finalHakiExp /= 4.0F;
/*     */         }
/* 204 */         hakiProps.alterBusoshokuImbuingHakiExp(exp * hakiMultiplier);
/* 205 */         HakiExpEvent e = new HakiExpEvent(player, finalHakiExp, HakiType.HARDENING);
/* 206 */         MinecraftForge.EVENT_BUS.post((Event)e);
/*     */       }
/*     */     
/* 209 */     } else if (heldStack.isEmpty()) {
/*     */       
/* 211 */       if (expValue < hakiProps.getBusoshokuHardeningHakiExp()) {
/*     */         return;
/*     */       }
/* 214 */       BusoshokuHakiHardeningAbility ability = (BusoshokuHakiHardeningAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
/* 215 */       if ((ability != null && ability.isContinuous()) || hakiProps.getBusoshokuHardeningHakiExp() <= 10.0D + HakiHelper.getBusoshokuHardeningExpNeeded(player)) {
/*     */         
/* 217 */         float exp = expValue / (900.0F + 2700.0F * hakiProps.getBusoshokuHardeningHakiExp() / 100.0F);
/* 218 */         if (exp <= 0.0F) {
/* 219 */           exp = 1.0E-4F;
/*     */         }
/* 221 */         float finalHakiExp = exp * hakiMultiplier;
/* 222 */         hakiProps.alterBusoshokuHardeningHakiExp(finalHakiExp);
/* 223 */         HakiExpEvent e = new HakiExpEvent(player, finalHakiExp, HakiType.HARDENING);
/* 224 */         MinecraftForge.EVENT_BUS.post((Event)e);
/*     */       } 
/*     */     } 
/*     */     
/* 228 */     player.world.getProfiler().endSection();
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerLoggedIn(EntityJoinWorldEvent event) {
/* 234 */     if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).isRemote && CommonConfig.INSTANCE.isHaoshokuUnlockLogicChanceBased()) {
/*     */       
/* 236 */       PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */ 
/*     */       
/* 239 */       if (HakiHelper.isHaoshokuBorn(player)) {
/* 240 */         giveHakiAbility(player, (Ability)HaoshokuHakiAbility.INSTANCE);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void giveHakiAbility(PlayerEntity player, Ability ability) {
/* 246 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 247 */     if (!props.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability)) {
/*     */       
/* 249 */       props.addUnlockedAbility(ability);
/* 250 */       if (!player.world.isRemote)
/* 251 */         WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\HakiGainEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */