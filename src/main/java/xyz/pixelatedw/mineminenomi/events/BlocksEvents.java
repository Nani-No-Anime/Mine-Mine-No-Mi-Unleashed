/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.EnderTeleportEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.traps.TrapAbilityBlock;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.yami.TeleportBlockedEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class BlocksEvents
/*     */ {
/*  26 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new TeleportBlockedEffect();
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEnderTeleport(EnderTeleportEvent event) {
/*  31 */     LivingEntity entity = event.getEntityLiving();
/*  32 */     World world = (event.getEntityLiving()).world;
/*     */     
/*  34 */     IDevilFruit props = DevilFruitCapability.get(entity);
/*     */     
/*  36 */     if (props.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI)) {
/*     */       return;
/*     */     }
/*  39 */     for (int i = -2; i < 2; i++) {
/*     */       
/*  41 */       for (int j = -2; j < 2; j++) {
/*     */         
/*  43 */         for (int k = -2; k < 2; k++) {
/*     */           
/*  45 */           BlockPos pos = entity.getPosition().add(i, j, k);
/*  46 */           Block b = world.getBlockState(pos).getBlock();
/*  47 */           if (b instanceof xyz.pixelatedw.mineminenomi.blocks.traps.DarknessBlock) {
/*     */             
/*  49 */             event.setCanceled(true);
/*  50 */             PARTICLES.spawn(entity.world, entity.getPosX(), entity.getPosY() + entity.getEyeHeight(), entity.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  62 */     LivingEntity entity = event.getEntityLiving();
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
/*  89 */     BlockState belowBlockState = entity.world.getBlockState(new BlockPos(entity.getPosX(), entity.getPosY() + 0.15D, entity.getPosZ()));
/*     */     
/*  91 */     if (belowBlockState.getBlock() instanceof TrapAbilityBlock && !entity.world.isRemote) {
/*     */       
/*  93 */       TrapAbilityBlock block = (TrapAbilityBlock)belowBlockState.getBlock();
/*  94 */       IDevilFruit props = DevilFruitCapability.get(entity);
/*     */       
/*  96 */       if (props.hasDevilFruit(block.getDevilFruit())) {
/*     */         
/*  98 */         entity.setPosition(entity.getPosX(), entity.getPosY() + 1.0D, entity.getPosZ());
/*  99 */         entity.fallDistance = 0.0F;
/* 100 */         entity.onGround = true;
/*     */       } else {
/*     */         
/* 103 */         if (entity instanceof PlayerEntity) {
/*     */           
/* 105 */           PlayerEntity playerEntity = (PlayerEntity)entity;
/* 106 */           if (!playerEntity.isCreative()) {
/* 107 */             playerEntity.abilities.isFlying = false;
/*     */           }
/*     */         } 
/* 110 */         if (block.getPotionEffect() != null && !entity.isPotionActive(block.getPotionEffect().getPotion())) {
/* 111 */           entity.addPotionEffect(block.getPotionEffect());
/*     */         }
/* 113 */         if (TrapAbilityBlock.isEntityInsideOpaqueBlock(entity, 0))
/* 114 */           entity.attackEntityFrom(DamageSource.IN_WALL, block.getDamageDealt()); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\BlocksEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */