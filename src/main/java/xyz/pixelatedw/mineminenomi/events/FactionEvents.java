/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileHitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class FactionEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttack(LivingHurtEvent event) {
/*  31 */     if (CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event.getSource().getTrueSource() instanceof PlayerEntity) {
/*     */       
/*  33 */       PlayerEntity attacker = (PlayerEntity)event.getSource().getTrueSource();
/*  34 */       LivingEntity target = event.getEntityLiving();
/*     */       
/*  36 */       boolean sameGroup = FactionHelper.getSameGroupPredicate((LivingEntity)attacker).test(target);
/*  37 */       if (sameGroup) {
/*     */         
/*  39 */         event.setCanceled(true);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onProjectileHit(ProjectileHitEvent event) {
/*  48 */     if (CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event.getProjectile().getThrower() instanceof PlayerEntity && event.getHit().getType() == RayTraceResult.Type.ENTITY) {
/*     */       
/*  50 */       PlayerEntity attacker = (PlayerEntity)event.getProjectile().getThrower();
/*  51 */       EntityRayTraceResult hitResult = (EntityRayTraceResult)event.getHit();
/*     */       
/*  53 */       if (hitResult.getEntity() instanceof LivingEntity) {
/*     */         
/*  55 */         LivingEntity target = (LivingEntity)hitResult.getEntity();
/*     */         
/*  57 */         boolean sameGroup = FactionHelper.getSameGroupPredicate((LivingEntity)attacker).test(target);
/*  58 */         if (sameGroup) {
/*     */           
/*  60 */           event.setCanceled(true);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onSetFire(SetOnFireEvent event) {
/*  70 */     if (CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event.getAttacker() instanceof PlayerEntity) {
/*     */       
/*  72 */       PlayerEntity attacker = (PlayerEntity)event.getAttacker();
/*  73 */       LivingEntity target = event.getEntityLiving();
/*     */       
/*  75 */       boolean sameGroup = FactionHelper.getSameGroupPredicate((LivingEntity)attacker).test(target);
/*  76 */       if (sameGroup) {
/*     */         
/*  78 */         event.setCanceled(true);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onTargetSet(LivingSetAttackTargetEvent event) {
/*  87 */     if (CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event.getTarget() instanceof PlayerEntity) {
/*     */       
/*  89 */       boolean sameGroup = FactionHelper.getSameGroupPredicate(event.getTarget()).test(event.getEntityLiving());
/*  90 */       if (sameGroup) {
/*     */         
/*  92 */         if (event.getEntityLiving() instanceof MobEntity)
/*  93 */           ((MobEntity)event.getEntityLiving()).setAttackTarget(null); 
/*  94 */         event.getEntityLiving().setRevengeTarget(null);
/*  95 */         event.getEntityLiving().setLastAttackedEntity(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerJoinsWorld(EntityJoinWorldEvent event) {
/* 103 */     if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).isRemote && (event.getWorld()).dimension.getType() == DimensionType.OVERWORLD)
/*     */     {
/* 105 */       FactionHelper.validateFaction((PlayerEntity)event.getEntity());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onStatsChoose(SetPlayerDetailsEvent event) {
/* 112 */     FactionHelper.validateFaction(event.getPlayer());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\FactionEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */