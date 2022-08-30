/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingFallEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHealEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SStepHeightValuePacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class ModAttributes
/*     */ {
/*  28 */   public static final IAttribute FALL_RESISTANCE = (IAttribute)(new RangedAttribute(null, "mineminenomi.fallResistance", 0.0D, -256.0D, 256.0D)).setDescription("Fall Resistance");
/*  29 */   public static final IAttribute JUMP_HEIGHT = (IAttribute)(new RangedAttribute(null, "mineminenomi.jumpHeight", 1.0D, -256.0D, 256.0D)).setDescription("Jump Height").setShouldWatch(true);
/*  30 */   public static final IAttribute REGEN_RATE = (IAttribute)(new RangedAttribute(null, "mineminenomi.regenRate", 1.0D, 0.0D, 32.0D)).setDescription("Regen Rate").setShouldWatch(true);
/*  31 */   public static final IAttribute STEP_HEIGHT = (IAttribute)(new RangedAttribute(null, "mineminenomi.stepHeight", 0.6D, 0.0D, 20.0D)).setDescription("Step Height").setShouldWatch(true);
/*  32 */   public static final IAttribute DAMAGE_REDUCTION = (IAttribute)(new RangedAttribute(null, "mineminenomi.damageReduction", 0.0D, -2.0D, 0.999D)).setDescription("Damage Reduction").setShouldWatch(true);
/*  33 */   public static final IAttribute ATTACK_RANGE = (IAttribute)(new RangedAttribute(null, "mineminenomi.attackRange", 0.0D, -1024.0D, 1024.0D)).setDescription("Attack Range").setShouldWatch(true);
/*  34 */   public static final IAttribute PUNCH_DAMAGE = (IAttribute)(new RangedAttribute(null, "mineminenomi.punchDamage", 0.0D, -1024.0D, 1024.0D)).setDescription("Punch Damage").setShouldWatch(true);
/*  35 */   public static final IAttribute TIME_PROGRESSION = (IAttribute)(new RangedAttribute(null, "mineminenomi.timeProgression", 1.0D, 0.0D, 1024.0D)).setDescription("Time Progression").setShouldWatch(true);
/*     */   
/*  37 */   private static final UUID PUNCH_DAMAGE_UUID = UUID.fromString("0ca69be5-5c86-4878-bd4a-ee118ce628d3");
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityConstruct(EntityEvent.EntityConstructing entity) {
/*  42 */     if (entity.getEntity() instanceof LivingEntity) {
/*     */       
/*  44 */       ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(FALL_RESISTANCE);
/*  45 */       ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(JUMP_HEIGHT);
/*  46 */       ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(REGEN_RATE);
/*  47 */       ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(STEP_HEIGHT);
/*  48 */       ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(DAMAGE_REDUCTION);
/*  49 */       ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(ATTACK_RANGE);
/*  50 */       ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(PUNCH_DAMAGE);
/*  51 */       ((LivingEntity)entity.getEntity()).getAttributes().registerAttribute(TIME_PROGRESSION);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onTick(TickEvent.PlayerTickEvent e) {
/*  58 */     if (!e.player.world.isRemote) {
/*     */       
/*  60 */       IAttributeInstance attributeInstance = e.player.getAttribute(STEP_HEIGHT);
/*  61 */       float newStepHeight = (float)attributeInstance.getValue();
/*  62 */       if (newStepHeight != e.player.stepHeight)
/*     */       {
/*  64 */         WyNetwork.sendTo(new SStepHeightValuePacket(newStepHeight), e.player);
/*     */       }
/*  66 */       e.player.stepHeight = newStepHeight;
/*     */     } 
/*     */     
/*  69 */     double damage = e.player.getAttribute(PUNCH_DAMAGE).getValue();
/*     */     
/*  71 */     AttributeModifier punchDamage = (new AttributeModifier(PUNCH_DAMAGE_UUID, "hand modifier", damage, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */     
/*  73 */     if (e.player.getHeldItemMainhand().isEmpty() && damage > 0.0D) {
/*     */       
/*  75 */       if (e.player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(PUNCH_DAMAGE_UUID) == null) {
/*  76 */         e.player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(punchDamage);
/*     */       }
/*     */     } else {
/*  79 */       e.player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(punchDamage);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onFall(LivingFallEvent e) {
/*  85 */     IAttributeInstance attributeInstance = e.getEntityLiving().getAttribute(FALL_RESISTANCE);
/*  86 */     e.setDistance((float)(e.getDistance() - attributeInstance.getValue()));
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onJump(LivingEvent.LivingJumpEvent e) {
/*  92 */     double value = e.getEntityLiving().getAttribute(JUMP_HEIGHT).getValue();
/*  93 */     e.getEntityLiving().addVelocity(0.0D, 0.10000000149011612D * (value - 1.0D), 0.0D);
/*  94 */     if (value <= 0.0D) {
/*  95 */       e.getEntityLiving().setMotion(0.0D, (e.getEntityLiving().getMotion()).y, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityHurt(LivingHurtEvent e) {
/* 101 */     if ((e.getEntityLiving()).world.isRemote)
/*     */       return; 
/* 103 */     double reduction = e.getEntityLiving().getAttribute(DAMAGE_REDUCTION).getValue();
/*     */     
/* 105 */     int absoluteReduction = 50;
/* 106 */     if (reduction > 0.0D && e.getSource().isUnblockable()) {
/*     */       
/* 108 */       for (EffectInstance effectInstance : e.getEntityLiving().getActivePotionEffects()) {
/* 109 */         if (effectInstance.getPotion() instanceof xyz.pixelatedw.mineminenomi.effects.GuardingEffect)
/* 110 */           absoluteReduction = 50 - effectInstance.getAmplifier() * 4; 
/*     */       } 
/* 112 */       reduction /= absoluteReduction;
/*     */     } 
/*     */     
/* 115 */     e.setAmount((float)(e.getAmount() * (1.0D - reduction)));
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHeal(LivingHealEvent event) {
/* 121 */     float value = (float)event.getEntityLiving().getAttribute(REGEN_RATE).getValue();
/* 122 */     if (value != 1.0F)
/* 123 */       event.setAmount(event.getAmount() * value); 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */