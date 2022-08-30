/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*     */ import net.minecraftforge.client.event.RenderBlockOverlayEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class MaguPassiveEvents
/*     */ {
/*  36 */   public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.MAGU, "Magu", MaguPassiveEvents::maguDamage, new DamageSource[] { DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.LAVA, DamageSource.HOT_FLOOR });
/*     */   
/*     */   public static boolean maguDamage(LivingEntity target, LivingEntity attacker) {
/*  39 */     if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled()) {
/*     */       
/*  41 */       SetOnFireEvent event = new SetOnFireEvent(attacker, target, 8);
/*  42 */       if (!MinecraftForge.EVENT_BUS.post(event)) {
/*  43 */         attacker.setFire(5);
/*     */       }
/*  45 */       ItemStack stack = attacker.getHeldItemMainhand();
/*     */       
/*  47 */       if (stack.getItem() instanceof xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem)
/*     */       {
/*  49 */         stack.damageItem(stack.getMaxDamage() / 10 + 1, target, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  55 */       attacker.attackEntityFrom(DamageSource.LAVA, 10.0F);
/*     */     } 
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  63 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  66 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  67 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  69 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.MAGU_MAGU_NO_MI)) {
/*     */       return;
/*     */     }
/*  72 */     if (player.isInLava() && !player.abilities.isFlying) {
/*  73 */       float a = (player.moveForward > 0.0F) ? 2.0F : 0.5F;
/*  74 */       float y = (player.isSneaking() && player.getPosY() - player.prevPosY <= 0.0D) ? 2.0F : 0.0F;
/*  75 */       Vec3d vec3d = player.getMotion().mul(a, y, a);
/*     */       
/*  77 */       if (AbilityHelper.isJumping((LivingEntity)player)) {
/*  78 */         player.setMotion(vec3d.add(0.0D, 0.4000000059604645D, 0.0D));
/*     */       } else {
/*  80 */         player.setMotion(vec3d);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*  85 */   private static final ArrayList<String> fireFruits = new ArrayList<>(Arrays.asList(new String[] { "magu_magu", "mera_mera", "goro_goro" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
/*  95 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*  96 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/*     */     
/*  98 */     if (!fireFruits.contains(devilFruitProps.getDevilFruit())) {
/*     */       return;
/*     */     }
/* 101 */     if (event.getOverlayType().equals(RenderBlockOverlayEvent.OverlayType.FIRE)) {
/* 102 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onEntityInLava(EntityViewRenderEvent.FogDensity event) {
/* 109 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 110 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/*     */     
/* 112 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.MAGU_MAGU_NO_MI)) {
/*     */       return;
/*     */     }
/* 115 */     if (clientPlayerEntity.areEyesInFluid(FluidTags.LAVA)) {
/*     */       
/* 117 */       event.setCanceled(true);
/* 118 */       event.setDensity(0.025F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\MaguPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */