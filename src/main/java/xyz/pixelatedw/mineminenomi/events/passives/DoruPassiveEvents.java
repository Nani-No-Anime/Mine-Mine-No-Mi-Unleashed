package xyz.pixelatedw.mineminenomi.events.passives;

import java.awt.Color;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruBallAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.models.abilities.CandleLockModel;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class DoruPassiveEvents
{
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class ClientEvents
  {
    private static final String[] COLORS = new String[] { "#c21d1f", "#8f176b", "#4d178f", "#17508d", "#158d7b", "#128d21", "#c8cb17", "#5ae163" };



    
    private static Color randomColor1 = chooseRandomColor();
    private static Color randomColor2 = chooseRandomColor();


    
    private static Color chooseRandomColor() {
      int i = (int)WyHelper.randomWithRange(0, COLORS.length - 1);
      String hex = COLORS[i];
      return WyHelper.hexToRGB(hex);
    }
    
    private static final SphereModel DORU_BALL = new SphereModel();
    private static final CandleLockModel CANDLE_LOCK = new CandleLockModel();

    
    @SubscribeEvent
    public static void onEntityRendered(RenderLivingEvent.Pre event) {
      LivingEntity entity = event.getEntity();
      
      Color color = Color.WHITE;
      if (!entity.isPotionActive(ModEffects.CANDLE_LOCK)) {
        return;
      }
      if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getDuration() <= 0) {
        
        entity.removePotionEffect(ModEffects.CANDLE_LOCK);
        
        return;
      } 
      if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getAmplifier() == 2) {
        color = randomColor1;
      }
      event.getMatrixStack().push();
      
      event.getMatrixStack().translate(0.0D, -0.8D, 0.0D);
      event.getMatrixStack().rotate(new Quaternion(Vector3f.YN, entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * event.getPartialRenderTick() + 180.0F, true));
      
      CANDLE_LOCK.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityHand(ModResources.CANDLE_LOCK)), event.getLight(), 0, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
      
      event.getMatrixStack().pop();
    }

    
    @SubscribeEvent
    public static void onPlayerRendered(RenderPlayerEvent.Pre event) {
      PlayerEntity player = event.getPlayer();
      IAbilityData data = AbilityDataCapability.get((LivingEntity)player);
      Color color = Color.WHITE;
      
      Ability ability = data.getEquippedAbility((Ability)DoruDoruBallAbility.INSTANCE);
      boolean isActive = (ability != null && ability.isContinuous());
      
      if (isActive) {
        
        event.setCanceled(true);
        if ((event.getPlayer()).inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE))) {
          color = randomColor2;
        }
        float zoanHeight = 1.0F;
        ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
        if (info != null)
        {
          zoanHeight = ((EntitySize)info.getSizes().get(Pose.STANDING)).height;
        }
        
        DoruDoruBallAbility abl = (DoruDoruBallAbility)ability;
        abl.rotateAngleX += (player.getMotion()).z;
        abl.rotateAngleZ -= (player.getMotion()).x;
        event.getMatrixStack().push();
        
        event.getMatrixStack().translate(0.0D, (player.getEyeHeight() - 0.5F), 0.0D);
        float scale = 8.0F + zoanHeight;
        event.getMatrixStack().scale(scale, scale, scale);
        DORU_BALL.setRotateAngle(DORU_BALL.shape1, (float)abl.rotateAngleX, 0.0F, (float)abl.rotateAngleZ);
        DORU_BALL.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityBody(ModResources.CANDLE_LOCK)), event.getLight(), 0, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
        
        event.getMatrixStack().pop();
      } 
    }
  }


  
  @EventBusSubscriber(modid = "mineminenomi")
  public static class CommonEvents
  {
    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
      if (!(event.getEntityLiving() instanceof PlayerEntity)) {
        return;
      }
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
      
      Ability doruBallAbility = abilityProps.getEquippedAbility((Ability)DoruDoruBallAbility.INSTANCE);
      boolean isDoruBallActive = (doruBallAbility != null && doruBallAbility.isContinuous());
      if (isDoruBallActive)
        player.setMotion(0.0D, -5.0D, 0.0D); 
    }
  }
}


