/*     */ package xyz.pixelatedw.mineminenomi.events.devilfruits;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreCriteria;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class DFWeaknessesEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
/*  39 */     PlayerEntity player = event.getPlayer();
/*  40 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  42 */     if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
/*  43 */       event.setNewSpeed(event.getOriginalSpeed() / 15.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
/*  49 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/*     */       return;
/*     */     }
/*  52 */     PlayerEntity player = event.getPlayer();
/*  53 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  55 */     if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
/*  56 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRightClickEmpty(PlayerInteractEvent.RightClickItem event) {
/*  62 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled() || event.getItemStack().getItem().equals(ModItems.BUBBLY_CORAL)) {
/*     */       return;
/*     */     }
/*  65 */     PlayerEntity player = event.getPlayer();
/*  66 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  68 */     if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
/*  69 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
/*  75 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/*     */       return;
/*     */     }
/*  78 */     PlayerEntity player = event.getPlayer();
/*  79 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  81 */     if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
/*  82 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
/*  88 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/*     */       return;
/*     */     }
/*  91 */     PlayerEntity player = event.getPlayer();
/*  92 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  94 */     if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
/*  95 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
/* 101 */     if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled() || !(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 104 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/* 105 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 107 */     if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
/* 108 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 114 */     if (event.getEntityLiving() != null) {
/*     */       
/* 116 */       LivingEntity entity = event.getEntityLiving();
/* 117 */       IDevilFruit props = DevilFruitCapability.get(entity);
/* 118 */       if (!props.hasDevilFruit()) {
/*     */         return;
/*     */       }
/* 121 */       if (AbilityHelper.isAffectedByWater(entity) && !entity.isPotionActive(ModEffects.BUBBLY_CORAL))
/*     */       {
/* 123 */         if (entity instanceof PlayerEntity && !((PlayerEntity)entity).abilities.isCreativeMode) {
/*     */           
/* 125 */           if (entity.isActualySwimming()) {
/* 126 */             entity.setMotion((entity.getMotion()).x, (entity.getMotion()).y - 0.1D, (entity.getMotion()).z);
/*     */           } else {
/* 128 */             entity.setMotion((entity.getMotion()).x, (entity.getMotion()).y - 0.04D, (entity.getMotion()).z);
/*     */           } 
/* 130 */           if (CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/* 131 */             ((PlayerEntity)entity).addExhaustion(0.015F);
/*     */           }
/* 133 */         } else if (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity) {
/* 134 */           entity.setMotion((entity.getMotion()).x, (entity.getMotion()).y - 0.04D, (entity.getMotion()).z);
/*     */         } 
/*     */       }
/* 137 */       if (event.getEntityLiving() instanceof PlayerEntity) {
/*     */         
/* 139 */         PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */         
/* 141 */         player.world.getProfiler().startSection("playerDevilFruitWeakness");
/*     */         
/* 143 */         boolean hasWaterWeakness = (AbilityHelper.isNearbyKairoseki(player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL));
/*     */         
/* 145 */         if (!player.world.isRemote && CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
/*     */           
/* 147 */           boolean hasExtraWaterTime = scoreCheck(player, hasWaterWeakness);
/* 148 */           if (hasWaterWeakness && hasExtraWaterTime) {
/*     */             return;
/*     */           }
/*     */         } 
/* 152 */         if (!player.world.isRemote)
/*     */         {
/* 154 */           if (hasWaterWeakness) {
/*     */             
/* 156 */             player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 20, 0));
/* 157 */             player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 0));
/* 158 */             player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 0));
/* 159 */             player.addExhaustion(0.05F);
/* 160 */             AbilityHelper.disableAbilities(player, 20, ability -> true);
/*     */           }
/*     */           else {
/*     */             
/* 164 */             AbilityHelper.enableAbilities(player, ability -> true);
/*     */           } 
/*     */         }
/*     */         
/* 168 */         player.world.getProfiler().endSection();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean scoreCheck(PlayerEntity player, boolean value) {
/* 175 */     Scoreboard scoreboard = player.world.getScoreboard();
/* 176 */     if (!scoreboard.func_197897_d().contains("watertime_mmnm"))
/* 177 */       scoreboard.addObjective("watertime_mmnm", ScoreCriteria.DUMMY, (ITextComponent)new StringTextComponent("waterTime"), ScoreCriteria.RenderType.INTEGER); 
/* 178 */     Score score = scoreboard.getOrCreateScore(player.getUniqueID().toString(), Objects.<ScoreObjective>requireNonNull(scoreboard.getObjective("watertime_mmnm")));
/*     */     
/* 180 */     if (value) {
/* 181 */       List<BlockPos> blockPosList = WyHelper.getNearbyBlocks((LivingEntity)player, 1);
/* 182 */       float size = (float)blockPosList.stream().map(pos -> player.world.getBlockState(pos).getBlock()).filter(block -> block.equals(Blocks.WATER)).count() / 4.0F;
/* 183 */       if (0.0F >= size)
/* 184 */         size = 1.0F; 
/* 185 */       score.increaseScore(Math.round(size));
/* 186 */       return (score.getScorePoints() <= 60);
/*     */     } 
/*     */     
/* 189 */     score.reset();
/* 190 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\devilfruits\DFWeaknessesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */