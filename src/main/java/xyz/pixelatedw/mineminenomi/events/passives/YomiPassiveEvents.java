package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.awt.Color;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility;
import xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.events.YomiTriggerEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.YomiModel;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

@EventBusSubscriber(modid = "mineminenomi")
public class YomiPassiveEvents
{
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onRenderOverlay(RenderGameOverlayEvent event) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
    
    if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD && props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && YomiZoanInfo.INSTANCE.isActive((LivingEntity)clientPlayerEntity)) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public static void onClonePlayer(PlayerEvent.Clone event) {
    if (event.isWasDeath()) {
      
      IDevilFruit oldPlayerProps = DevilFruitCapability.get((LivingEntity)event.getOriginal());
      IDevilFruit newPlayerProps = DevilFruitCapability.get((LivingEntity)event.getPlayer());
      
      YomiTriggerEvent yomiEvent = new YomiTriggerEvent(event.getPlayer(), oldPlayerProps, newPlayerProps);
      if (MinecraftForge.EVENT_BUS.post((Event)yomiEvent)) {
        return;
      }
    } 
  }
  
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    
    if (player.world.isRemote) {
      return;
    }
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || !YomiZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      return;
    }
    player.getFoodStats().addStats(9999, 9999.0F);
    
    player.addPotionEffect(new EffectInstance(Effects.SPEED, 10, player.isSprinting() ? 4 : 0, false, false));
    
    if (player.isBurning()) {
      player.extinguish();
    }
    if (player.ticksExisted % 500 == 0) {
      
      WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
      if (WyHelper.getEntitiesNear(player.getPosition(), player.world, 100.0D, new Class[] { PlayerEntity.class }).size() > 0) {
        WyNetwork.sendToAllAround(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), (Entity)player);
      }
    } 
    if (player.world.getBlockState(player.getPosition().down()).getFluidState().isSource() && player.isSprinting()) {
      
      player.onGround = true;
      if ((player.getMotion()).y <= 0.0D && (player.getMotion()).y < 0.009D) {
        
        Vec3d speed = WyHelper.propulsion((LivingEntity)player, 0.4D, 0.4D);
        double ySpeed = 0.0D - (player.getMotion()).y;
        if (ySpeed > 0.008D)
          ySpeed = 0.008D; 
        player.setMotion(speed.x, ySpeed, speed.z);
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
        player.fallDistance = 0.0F;
      } 
      
      BlockState blockState = player.world.getBlockState(player.getPosition().down());
      for (int i = 0; i < 10; i++) {
        
        double newPosX = player.getPosX() + WyHelper.randomDouble();
        double newPosY = player.getPosY();
        double newPosZ = player.getPosZ() + WyHelper.randomDouble();
        
        ((ServerWorld)player.world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), newPosX, newPosY, newPosZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onHeal(LivingHealEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || !YomiZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      return;
    }
    event.setAmount(event.getAmount());
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent(priority = EventPriority.HIGH)
  public static void onEntityRendered(RenderLivingEvent.Pre event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    
    if (!isSpirit(player)) {
      return;
    }
    ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
    if (info == null) {
      return;
    }
    ZoanMorphRenderer render = (ZoanMorphRenderer)info.getRendererFactory().createRenderFor(Minecraft.getInstance().getRenderManager());
    IVertexBuilder vertex = event.getBuffers().getBuffer(RenderType.getEntityTranslucent(render.getEntityTexture((AbstractClientPlayerEntity)player)));
    event.setCanceled(true);
    
    event.getMatrixStack().push();
    
    event.getMatrixStack().translate(0.0D, 1.5D, 0.0D);
    event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 180.0F, true));
    event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 180.0F, true));
    event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * event.getPartialRenderTick() - 180.0F, true));
    event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * event.getPartialRenderTick(), true));
    
    ((YomiModel)render.getEntityModel()).bipedHead.render(event.getMatrixStack(), vertex, event.getLight(), OverlayTexture.NO_OVERLAY, 0.3F, 0.9F, 0.5F, 0.6F);
    
    event.getMatrixStack().push();
    
    IVertexBuilder vertexBuilder = event.getBuffers().getBuffer(RenderType.getLightning());
    event.getMatrixStack().translate(0.0D, -0.35D, 0.0D);
    float randMovement = ((player.ticksExisted / 200) + event.getPartialRenderTick()) / 500.0F;
    for (int i = 0; i < 100; i++) {
      
      event.getMatrixStack().rotate(Vector3f.XP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
      event.getMatrixStack().rotate(Vector3f.YP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
      event.getMatrixStack().rotate(Vector3f.ZP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
      event.getMatrixStack().rotate(Vector3f.XP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
      event.getMatrixStack().rotate(Vector3f.YP.rotationDegrees(player.getRNG().nextFloat() * 360.0F));
      event.getMatrixStack().rotate(Vector3f.ZP.rotationDegrees(player.getRNG().nextFloat() * 360.0F + randMovement * 90.0F));
      float f3 = 0.6F * player.getRNG().nextFloat();
      float f4 = 0.6F * player.getRNG().nextFloat();
      Matrix4f matrix4f = event.getMatrixStack().getLast().getMatrix();
      
      int alpha = 5;
      Color primaryColor = new Color(0, 255, 0, alpha);
      Color secondaryColor = new Color(0, 255, 50, alpha);
      
      RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
      RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, secondaryColor);
      RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, secondaryColor);
      RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
      RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, secondaryColor);
      RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, secondaryColor);
      RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
      RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, secondaryColor);
      RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, secondaryColor);
    } 
    
    event.getMatrixStack().pop();
    
    event.getMatrixStack().pop();
  }

  
  @SubscribeEvent
  public static void onEntityAttack(LivingHurtEvent event) {
    if (!(event.getSource().getImmediateSource() instanceof LivingEntity) || !(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    LivingEntity attacker = (LivingEntity)event.getSource().getImmediateSource();
    PlayerEntity attacked = (PlayerEntity)event.getEntityLiving();
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacked);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)attacked);
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || attacker == null) {
      return;
    }
    Ability ability = abilityProps.getEquippedAbility((Ability)SoulParadeAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (YomiZoanInfo.INSTANCE.isActive((LivingEntity)attacked) && isActive) {
      
      attacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 70, 1));
      attacker.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 70, 1));
      EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 70, 0);
      attacker.addPotionEffect(instance);
      ((ServerPlayerEntity)attacked).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(attacker.getEntityId(), instance));
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)attacked, attacked.world, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), 2.0F);
      explosion.setDamageOwner(false);
      explosion.setDestroyBlocks(false);
      explosion.setSmokeParticles(SoulParadeAbility.PARTICLES);
      explosion.doExplosion();
    } 
  }

  
  @SubscribeEvent
  public static void onDrinkMilk(LivingEntityUseItemEvent.Finish event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    IDevilFruit devilFruitProps = DevilFruitCapability.get(event.getEntityLiving());
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI)) {
      return;
    }
    if (event.getItem().getItem() == Items.MILK_BUCKET && YomiZoanInfo.INSTANCE.isActive(event.getEntityLiving())) {
      event.getEntityLiving().heal(8.0F);
    }
  }
  
  private static boolean isSpirit(PlayerEntity player) {
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (player.isCreative() || player.isSpectator()) {
      return false;
    }
    Ability ability = abilityProps.getEquippedAbility((Ability)YomiNoReikiAbility.INSTANCE);
    
    return (ability != null && ability.isContinuous());
  }
}


