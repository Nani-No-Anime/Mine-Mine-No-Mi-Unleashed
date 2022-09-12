package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.bari.BariBariNoPistolAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

@EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
public class BariPassiveEvents {
  private static final SphereModel BARI_SPHERE = new SphereModel();

  
  @SubscribeEvent
  public static void onEntityRendered(RenderLivingEvent.Post event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    LivingRenderer renderer = event.getRenderer();
    
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (!devilFruitProps.getDevilFruit().equals("bari_bari")) {
      return;
    }
    BariBariNoPistolAbility ability = (BariBariNoPistolAbility)abilityProps.getEquippedAbility((Ability)BariBariNoPistolAbility.INSTANCE);
    
    if (ability != null && ability.isContinuous()) {
      
      event.getMatrixStack().push();
      
      event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 180.0F, true));
      event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 180.0F, true));

      
      float ageInTicks = player.ticksExisted + event.getPartialRenderTick();
      float headYawOffset = MathHelper.interpolateAngle(event.getPartialRenderTick(), player.prevRenderYawOffset, player.renderYawOffset);
      
      WyHelper.rotateCorpse(event.getMatrixStack(), (LivingEntity)player, ageInTicks, headYawOffset, event.getPartialRenderTick());
      
      event.getMatrixStack().translate(-0.04D, -1.3D, 0.12D);
      
      ((IHasArm)renderer.getEntityModel()).translateHand(HandSide.RIGHT, event.getMatrixStack());
      event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 90.0F, true));
      event.getMatrixStack().scale(1.25F, 1.25F, 1.25F);
      event.getMatrixStack().translate(0.1D, 0.4D, -0.01D);
      
      BARI_SPHERE.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.TRANSPARENT_COLOR), event.getLight(), 0, 0.5F, 1.0F, 0.8F, 0.4F);
      
      event.getMatrixStack().pop();
    } 
  }

  
  @SubscribeEvent
  public static void onHandRendering(RenderHandEvent event) {
    if (event.getHand() != Hand.MAIN_HAND) {
      return;
    }
    if (!event.getItemStack().isEmpty()) {
      return;
    }
    Minecraft mc = Minecraft.getInstance();
    ClientPlayerEntity player = mc.player;
    
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.BARI_BARI_NO_MI)) {
      return;
    }
    BariBariNoPistolAbility ability = (BariBariNoPistolAbility)abilityProps.getEquippedAbility((Ability)BariBariNoPistolAbility.INSTANCE);
    
    if (ability != null && ability.isContinuous()) {
      
      event.setCanceled(true);
      
      EntityRendererManager renderManager = mc.getRenderManager();
      PlayerRenderer renderer = (PlayerRenderer)renderManager.getRenderer(player);
      
      event.getMatrixStack().push();
      
      float f = 1.0F;
      float f1 = MathHelper.sqrt(event.getSwingProgress());
      float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
      float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
      float f4 = -0.4F * MathHelper.sin(event.getSwingProgress() * 3.1415927F);
      event.getMatrixStack().translate((f * (f2 + 0.64000005F)), (f3 + -0.6F + event.getEquipProgress() * -0.6F), (f4 + -0.71999997F));
      event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * 45.0F, true));
      float f5 = MathHelper.sin(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F);
      float f6 = MathHelper.sin(f1 * 3.1415927F);
      event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * f6 * 70.0F, true));
      event.getMatrixStack().rotate(new Quaternion(Vector3f.ZP, f * f5 * -20.0F, true));
      
      event.getMatrixStack().translate((f * -1.0F), 3.5999999046325684D, 3.5D);
      event.getMatrixStack().rotate(new Quaternion(Vector3f.ZP, f * 120.0F, true));
      event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 200.0F, true));
      event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * -135.0F, true));
      event.getMatrixStack().translate((f * 5.6F), 0.0D, 0.0D);
      
      ClientPlayerEntity clientPlayerEntity = player;
      ((PlayerModel)renderer.getEntityModel()).bipedRightArm.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityHand(clientPlayerEntity.getLocationSkin())), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
      
      event.getMatrixStack().translate(-0.4D, 0.8D, 0.01D);
      event.getMatrixStack().scale(2.0F, 2.0F, 2.0F);
      
      BARI_SPHERE.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.TRANSPARENT_COLOR), event.getLight(), 0, 0.5F, 1.0F, 0.8F, 0.4F);
      
      event.getMatrixStack().pop();
    } 
  }
}


