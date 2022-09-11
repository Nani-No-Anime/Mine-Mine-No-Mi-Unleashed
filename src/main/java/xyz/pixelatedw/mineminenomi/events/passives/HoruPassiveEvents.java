package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;



@EventBusSubscriber(modid = "mineminenomi")
public class HoruPassiveEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    LivingEntity entity = event.getEntityLiving();
    
    if (!entity.isAlive()) {
      return;
    }
    if (entity.isPotionActive(ModEffects.CHIYU_HORMONE)) {
      
      EffectInstance effect = entity.getActivePotionEffect(ModEffects.CHIYU_HORMONE);
      
      if (effect.getDuration() <= 2) {
        entity.addPotionEffect(new EffectInstance(Effects.HUNGER, 200, 1));
      }
    } 
    if (entity.isPotionActive(ModEffects.TENSION_HORMONE)) {
      
      EffectInstance effect = entity.getActivePotionEffect(ModEffects.TENSION_HORMONE);
      
      if (effect.getDuration() <= 2) {
        entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 400, 1));
      }
    } 
  }
  
  @SubscribeEvent
  @OnlyIn(Dist.CLIENT)
  public static void onEntityRendered(RenderLivingEvent.Post event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity entity = (PlayerEntity)event.getEntity();
    PlayerRenderer renderer = (PlayerRenderer)event.getRenderer();
    
    AbstractClientPlayerEntity abstractOwner = (AbstractClientPlayerEntity)entity;
    BipedModel model = (BipedModel)renderer.getEntityModel();
    
    if (!entity.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE)) {
      return;
    }
    event.getMatrixStack().push();
    
    event.getMatrixStack().translate(0.0D, 1.3D, 0.0D);
    
    if (entity.isSneaking()) {
      event.getMatrixStack().translate(0.0D, 0.6D, 0.0D);
    }
    event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 180.0F, true));
    event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 180.0F, true));
    
    float ageInTicks = entity.ticksExisted + event.getPartialRenderTick();
    float headYawOffset = MathHelper.interpolateAngle(event.getPartialRenderTick(), entity.prevRenderYawOffset, entity.renderYawOffset);
    WyHelper.rotateCorpse(event.getMatrixStack(), (LivingEntity)entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
    
    event.getMatrixStack().scale(3.5F, 3.5F, 3.5F);
    
    model.bipedHead.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityHand(renderer.getEntityTexture(abstractOwner))), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
    
    event.getMatrixStack().pop();
  }
}


