/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraftforge.client.event.RenderBlockOverlayEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sui.FreeSwimmingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class SuiPassiveEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  30 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  33 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  34 */     IAbilityData AbilityProps = AbilityDataCapability.get((LivingEntity)player);
/*  35 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  37 */     if (!props.hasDevilFruit(ModAbilities.SUI_SUI_NO_MI)) {
/*     */       return;
/*     */     }
/*  40 */     boolean flying = player.abilities.isFlying;
/*  41 */     boolean creative = player.isCreative();
/*  42 */     boolean isOnGround = player.onGround;
/*  43 */     BlockPos pos = player.getPosition();
/*  44 */     boolean isMidAir = (player.world.getBlockState(pos.up()).isAir() && player.world.getBlockState(pos.down(2)).isAir());
/*     */     
/*  46 */     FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityProps.getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
/*     */     
/*  48 */     if (ability != null)
/*     */     {
/*  50 */       if (ability.isContinuous()) {
/*     */         
/*  52 */         if (player.isInWater()) {
/*     */           
/*  54 */           ability.isSwimming = false;
/*     */         }
/*     */         else {
/*     */           
/*  58 */           if (isMidAir) {
/*  59 */             ability.stopContinuity(player);
/*     */           }
/*  61 */           if (isOnGround && player.isSprinting() && !flying)
/*     */           {
/*  63 */             AbilityHelper.setPose((LivingEntity)player, Pose.SWIMMING);
/*     */           }
/*     */           
/*  66 */           player.setSwimming(true);
/*  67 */           boolean swimming = (player.getPose() == Pose.SWIMMING);
/*  68 */           Vec3d playerMotion = player.getMotion();
/*     */           
/*  70 */           if (!player.world.isRemote) {
/*  71 */             ability.isSwimming = swimming;
/*     */           }
/*  73 */           if (swimming)
/*     */           {
/*  75 */             player.noClip = true;
/*  76 */             player.setMotion(playerMotion.mul(1.7D, 0.75D, 1.7D));
/*  77 */             playerMotion = player.getMotion();
/*     */             
/*  79 */             if (!isEntityInsideOpaqueBlock((LivingEntity)player))
/*     */             {
/*  81 */               double fall = -0.15D;
/*     */               
/*  83 */               if (AbilityHelper.isJumping((LivingEntity)player)) {
/*  84 */                 fall -= 0.065D;
/*  85 */               } else if (player.isSneaking()) {
/*  86 */                 fall += 0.04D;
/*     */               } 
/*  88 */               player.setMotion(playerMotion.x, fall, playerMotion.z);
/*     */             
/*     */             }
/*     */             else
/*     */             {
/*  93 */               if (player.getActivePotionEffect(Effects.WATER_BREATHING) == null) {
/*     */                 
/*  95 */                 int airLeft = player.getAir();
/*  96 */                 player.setAir(airLeft - 5);
/*  97 */                 if (airLeft <= 0)
/*     */                 {
/*  99 */                   if (player.ticksExisted % 10 == 0)
/*     */                   {
/* 101 */                     player.attackEntityFrom(DamageSource.DROWN, 2.0F);
/*     */                   }
/*     */                 }
/*     */               } 
/*     */               
/* 106 */               if (playerMotion.y < 0.0D)
/*     */               {
/* 108 */                 player.setMotion(playerMotion.mul(1.0D, 0.8D, 1.0D));
/*     */               }
/*     */               
/* 111 */               Vec3d Vector3d1 = player.getMotion();
/* 112 */               double d3 = (player.getLookVec()).y;
/* 113 */               double d4 = (d3 < -0.2D) ? 0.1D : 0.09D;
/* 114 */               double upAlready = (d3 - Vector3d1.y) * d4;
/*     */               
/* 116 */               player.setMotion(Vector3d1.add(0.0D, (d3 - Vector3d1.y) * d4, 0.0D));
/*     */               
/* 118 */               if (player.isSneaking())
/*     */               {
/* 120 */                 player.setMotion(Vector3d1.add(0.0D, creative ? -0.18D : -0.08D, 0.0D));
/*     */               }
/*     */               
/* 123 */               if (AbilityHelper.isJumping((LivingEntity)player))
/*     */               {
/* 125 */                 player.setMotion(player.getMotion().add(0.0D, Math.max(upAlready, 0.1D), 0.0D));
/*     */               }
/*     */               
/* 128 */               if (0.0D >= player.getPosY())
/*     */               {
/* 130 */                 player.setMotion(player.getMotion().add(0.0D, 3.0D, 0.0D));
/*     */               }
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 139 */         ability.isSwimming = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
/* 147 */     if (event.getPlayer() == null) {
/*     */       return;
/*     */     }
/* 150 */     PlayerEntity player = event.getPlayer();
/* 151 */     IAbilityData AbilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 152 */     FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityProps.getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
/*     */     
/* 154 */     if (ability != null && ability.isContinuous())
/*     */     {
/* 156 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isEntityInsideOpaqueBlock(LivingEntity e) {
/* 162 */     BlockPos.Mutable blockPos = new BlockPos.Mutable();
/* 163 */     for (int i = 0; i < 8; i++) {
/*     */       
/* 165 */       int j = MathHelper.floor(e.getPosY() + ((((i >> 0) % 2) - 0.5F) * 0.1F) + e.getEyeHeight());
/* 166 */       int k = MathHelper.floor(e.getPosX() + ((((i >> 1) % 2) - 0.5F) * e.getHeight() * 0.8F));
/* 167 */       int l = MathHelper.floor(e.getPosZ() + ((((i >> 2) % 2) - 0.5F) * e.getWidth() * 0.8F));
/* 168 */       if (blockPos.getX() != k || blockPos.getY() != j || blockPos.getZ() != l) {
/*     */         
/* 170 */         blockPos.setPos(k, j, l);
/* 171 */         if (e.world.getBlockState((BlockPos)blockPos).isSuffocating((IBlockReader)e.world, (BlockPos)blockPos))
/*     */         {
/* 173 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 177 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\SuiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */