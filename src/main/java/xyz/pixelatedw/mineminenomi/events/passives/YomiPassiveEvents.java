/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.Matrix4f;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.particles.BlockParticleData;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHealEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.YomiTriggerEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.YomiModel;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class YomiPassiveEvents
/*     */ {
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onRenderOverlay(RenderGameOverlayEvent event) {
/*  71 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*  72 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/*     */     
/*  74 */     if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD && props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && YomiZoanInfo.INSTANCE.isActive((LivingEntity)clientPlayerEntity)) {
/*  75 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onClonePlayer(PlayerEvent.Clone event) {
/*  81 */     if (event.isWasDeath()) {
/*     */       
/*  83 */       IDevilFruit oldPlayerProps = DevilFruitCapability.get((LivingEntity)event.getOriginal());
/*  84 */       IDevilFruit newPlayerProps = DevilFruitCapability.get((LivingEntity)event.getPlayer());
/*     */       
/*  86 */       YomiTriggerEvent yomiEvent = new YomiTriggerEvent(event.getPlayer(), oldPlayerProps, newPlayerProps);
/*  87 */       if (MinecraftForge.EVENT_BUS.post((Event)yomiEvent)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  95 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  98 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */     
/* 100 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/* 103 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 105 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || !YomiZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 108 */     player.getFoodStats().addStats(9999, 9999.0F);
/*     */     
/* 110 */     player.addPotionEffect(new EffectInstance(Effects.SPEED, 10, player.isSprinting() ? 4 : 0, false, false));
/*     */     
/* 112 */     if (player.isBurning()) {
/* 113 */       player.extinguish();
/*     */     }
/* 115 */     if (player.ticksExisted % 500 == 0) {
/*     */       
/* 117 */       WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
/* 118 */       if (WyHelper.getEntitiesNear(player.getPosition(), player.world, 100.0D, new Class[] { PlayerEntity.class }).size() > 0) {
/* 119 */         WyNetwork.sendToAllAround(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), (Entity)player);
/*     */       }
/*     */     } 
/* 122 */     if (player.world.getBlockState(player.getPosition().down()).getFluidState().isSource() && player.isSprinting()) {
/*     */       
/* 124 */       player.onGround = true;
/* 125 */       if ((player.getMotion()).y <= 0.0D && (player.getMotion()).y < 0.009D) {
/*     */         
/* 127 */         Vec3d speed = WyHelper.propulsion((LivingEntity)player, 0.4D, 0.4D);
/* 128 */         double ySpeed = 0.0D - (player.getMotion()).y;
/* 129 */         if (ySpeed > 0.008D)
/* 130 */           ySpeed = 0.008D; 
/* 131 */         player.setMotion(speed.x, ySpeed, speed.z);
/* 132 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 133 */         player.fallDistance = 0.0F;
/*     */       } 
/*     */       
/* 136 */       BlockState blockState = player.world.getBlockState(player.getPosition().down());
/* 137 */       for (int i = 0; i < 10; i++) {
/*     */         
/* 139 */         double newPosX = player.getPosX() + WyHelper.randomDouble();
/* 140 */         double newPosY = player.getPosY();
/* 141 */         double newPosZ = player.getPosZ() + WyHelper.randomDouble();
/*     */         
/* 143 */         ((ServerWorld)player.world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), newPosX, newPosY, newPosZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHeal(LivingHealEvent event) {
/* 151 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 154 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 155 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 157 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || !YomiZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 160 */     event.setAmount(event.getAmount());
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent(priority = EventPriority.HIGH)
/*     */   public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 167 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 170 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */     
/* 172 */     if (!isSpirit(player)) {
/*     */       return;
/*     */     }
/* 175 */     ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
/* 176 */     if (info == null) {
/*     */       return;
/*     */     }
/* 179 */     ZoanMorphRenderer render = (ZoanMorphRenderer)info.getRendererFactory().createRenderFor(Minecraft.getInstance().getRenderManager());
/* 180 */     IVertexBuilder vertex = event.getBuffers().getBuffer(RenderType.getEntityTranslucent(render.getEntityTexture((AbstractClientPlayerEntity)player)));
/* 181 */     event.setCanceled(true);
/*     */     
/* 183 */     event.getMatrixStack().push();
/*     */     
/* 185 */     event.getMatrixStack().translate(0.0D, 1.5D, 0.0D);
/* 186 */     event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/* 187 */     event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/* 188 */     event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * event.getPartialRenderTick() - 180.0F, true));
/* 189 */     event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * event.getPartialRenderTick(), true));
/*     */     
/* 191 */     ((YomiModel)render.getEntityModel()).bipedHead.render(event.getMatrixStack(), vertex, event.getLight(), OverlayTexture.NO_OVERLAY, 0.3F, 0.9F, 0.5F, 0.6F);
/*     */     
/* 193 */     event.getMatrixStack().push();
/*     */     
/* 195 */     IVertexBuilder vertexBuilder = event.getBuffers().getBuffer(RenderType.getLightning());
/* 196 */     event.getMatrixStack().translate(0.0D, -0.35D, 0.0D);
/* 197 */     float randMovement = ((player.ticksExisted / 200) + event.getPartialRenderTick()) / 500.0F;
/* 198 */     for (int i = 0; i < 100; i++) {
/*     */       
/* 200 */       event.getMatrixStack().rotate(Vector3f.XP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
/* 201 */       event.getMatrixStack().rotate(Vector3f.YP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
/* 202 */       event.getMatrixStack().rotate(Vector3f.ZP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
/* 203 */       event.getMatrixStack().rotate(Vector3f.XP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
/* 204 */       event.getMatrixStack().rotate(Vector3f.YP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
/* 205 */       event.getMatrixStack().rotate(Vector3f.ZP.rotationDegrees(player.getRNG().nextFloat() * 360.0F + randMovement * 90.0F));
/* 206 */       float f3 = 0.6F * player.getRNG().nextFloat();
/* 207 */       float f4 = 0.6F * player.getRNG().nextFloat();
/* 208 */       Matrix4f matrix4f = event.getMatrixStack().getLast().getMatrix();
/*     */       
/* 210 */       int alpha = 5;
/* 211 */       Color primaryColor = new Color(0, 255, 0, alpha);
/* 212 */       Color secondaryColor = new Color(0, 255, 50, alpha);
/*     */       
/* 214 */       RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
/* 215 */       RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 216 */       RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 217 */       RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
/* 218 */       RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 219 */       RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 220 */       RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
/* 221 */       RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 222 */       RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/*     */     } 
/*     */     
/* 225 */     event.getMatrixStack().pop();
/*     */     
/* 227 */     event.getMatrixStack().pop();
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttack(LivingHurtEvent event) {
/* 233 */     if (!(event.getSource().getImmediateSource() instanceof LivingEntity) || !(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 236 */     LivingEntity attacker = (LivingEntity)event.getSource().getImmediateSource();
/* 237 */     PlayerEntity attacked = (PlayerEntity)event.getEntityLiving();
/* 238 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacked);
/* 239 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)attacked);
/*     */     
/* 241 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || attacker == null) {
/*     */       return;
/*     */     }
/* 244 */     Ability ability = abilityProps.getEquippedAbility((Ability)SoulParadeAbility.INSTANCE);
/* 245 */     boolean isActive = (ability != null && ability.isContinuous());
/*     */     
/* 247 */     if (YomiZoanInfo.INSTANCE.isActive((LivingEntity)attacked) && isActive) {
/*     */       
/* 249 */       attacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 70, 1));
/* 250 */       attacker.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 70, 1));
/* 251 */       EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 70, 0);
/* 252 */       attacker.addPotionEffect(instance);
/* 253 */       ((ServerPlayerEntity)attacked).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(attacker.getEntityId(), instance));
/* 254 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)attacked, attacked.world, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), 2.0F);
/* 255 */       explosion.setDamageOwner(false);
/* 256 */       explosion.setDestroyBlocks(false);
/* 257 */       explosion.setSmokeParticles(SoulParadeAbility.PARTICLES);
/* 258 */       explosion.doExplosion();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDrinkMilk(LivingEntityUseItemEvent.Finish event) {
/* 265 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 268 */     IDevilFruit devilFruitProps = DevilFruitCapability.get(event.getEntityLiving());
/*     */     
/* 270 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI)) {
/*     */       return;
/*     */     }
/* 273 */     if (event.getItem().getItem() == Items.MILK_BUCKET && YomiZoanInfo.INSTANCE.isActive(event.getEntityLiving())) {
/* 274 */       event.getEntityLiving().heal(8.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isSpirit(PlayerEntity player) {
/* 279 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 281 */     if (player.isCreative() || player.isSpectator()) {
/* 282 */       return false;
/*     */     }
/* 284 */     Ability ability = abilityProps.getEquippedAbility((Ability)YomiNoReikiAbility.INSTANCE);
/*     */     
/* 286 */     return (ability != null && ability.isContinuous());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\YomiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */