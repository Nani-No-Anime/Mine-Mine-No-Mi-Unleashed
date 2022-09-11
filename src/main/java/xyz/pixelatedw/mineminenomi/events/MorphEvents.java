package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.client.CSyncZoanPacket;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;











public class MorphEvents
{
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class Client
  {
    @SubscribeEvent
    public static void onEntityRendered(RenderLivingEvent.Pre<?,?> event) {
      if (!(event.getEntity() instanceof AbstractClientPlayerEntity)) {
        return;
      }
      AbstractClientPlayerEntity entity = (AbstractClientPlayerEntity)event.getEntity();
      IDevilFruit props = DevilFruitCapability.get((LivingEntity)entity);
      
      if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
        
        if ((event.getEntity()).hurtTime > 0);



        
        ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)entity);
        if (info != null) {
          
          event.setCanceled(true);
          ZoanMorphRenderer<?,?> render = (ZoanMorphRenderer<?,?>)info.getRendererFactory(entity).createRenderFor(Minecraft.getInstance().getRenderManager());
          render.render(entity, entity.rotationYaw, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight());
        } 
      } 
    }

    
    @SubscribeEvent
    public static void onEntityConstructing(EntityJoinWorldEvent event) {
      if (event.getEntity() instanceof PlayerEntity) {
        
        ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
        PlayerEntity player = (PlayerEntity)event.getEntity();





        
        if (player.world.isRemote && clientPlayerEntity != player)
        {
          WyNetwork.sendToServer(new CSyncZoanPacket(player.getEntityId()));
        }
      } 
    }

    
    @SubscribeEvent
    public static void onHandRendering(RenderHandEvent event) {
      if (event.getHand() != Hand.MAIN_HAND) {
        return;
      }
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      if (clientPlayerEntity == null) {
        return;
      }
      IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
      
      boolean renderHand = false;
      boolean hasEmptyHand = clientPlayerEntity.getHeldItemMainhand().isEmpty();
      
      ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)clientPlayerEntity);
      AbilityOverlay overlay = AbilityHelper.getCurrentOverlay((PlayerEntity)clientPlayerEntity);
      if (info != null) {
        renderHand = true;
      }
      boolean isZoan = (hasEmptyHand && renderHand);
      boolean isBlackLeg = (props.isBlackLeg() && hasEmptyHand);
      boolean isOverlay = (hasEmptyHand && overlay != null);
      
      if (isZoan || isOverlay || isBlackLeg) {
        
        event.setCanceled(true);
        MorphHelper.renderLimbFirstPerson(event.getMatrixStack(), event.getBuffers(), event.getLight(), event.getEquipProgress(), event.getSwingProgress(), clientPlayerEntity.getPrimaryHand(), overlay, info);
      } 
    }
  }

  
  @EventBusSubscriber(modid = "mineminenomi")
  public static class Common
  {
    @SubscribeEvent
    public static void onZoanSizeChange(EntityEvent.EyeHeight event) {
      if (!(event.getEntity() instanceof PlayerEntity)) {
        return;
      }
      PlayerEntity player = (PlayerEntity)event.getEntity();
      IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
      
      Pose pose = player.getPose();
      float eyeHeight = (player.getSize(pose)).height * 0.9F;
      
      ZoanInfo info = null;
      
      if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
        
        info = MorphHelper.getZoanInfo((LivingEntity)player);
        
        if (info == null) {
          return;
        }
        if (info.getEyeHeight() <= 0.0D) {
          return;
        }
        if (info != null) {
          
          eyeHeight = (float)(1.62D * info.getEyeHeight() / 1.75D);
          if (eyeHeight < 0.22F) {
            eyeHeight = 0.22F;
          }
        } 
      } 
      if (info != null && player.isSneaking()) {
        
        eyeHeight -= 0.3F;
        eyeHeight = (float)Math.max(0.3D, eyeHeight);
      } 
      
      event.setNewHeight(eyeHeight);
    }

    
    @SubscribeEvent
    public static void onZoanMounts(EntityMountEvent event) {
      if (event.getEntityBeingMounted() == null) {
        return;
      }
      if (event.getEntityMounting() instanceof PlayerEntity) {
        
        PlayerEntity player = (PlayerEntity)event.getEntityMounting();
        ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
        
        if (info != null && !info.canMount()) {
          
          player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_MOUNT_ZOANN, new Object[0]));
          event.setCanceled(true);
        } 
      } 
    }
  }
}


