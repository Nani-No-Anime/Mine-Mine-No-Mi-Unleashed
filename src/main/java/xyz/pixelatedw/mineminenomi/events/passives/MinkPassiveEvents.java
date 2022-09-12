package xyz.pixelatedw.mineminenomi.events.passives;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.electro.EleclawAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalShowerAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalTempestaAbility;
import xyz.pixelatedw.mineminenomi.abilities.electro.SulongAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.SpringLegsZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.MinkBunnyPartialRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.MinkDogPartialRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.MinkLionPartialRenderer;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;



public class MinkPassiveEvents
{
  @EventBusSubscriber(modid = "mineminenomi")
  public static class CommonEvents
  {
    private static final AttributeModifier SPEED_MODIFIER = (new AttributeModifier(UUID.fromString("d70f4c72-ba2e-4aaf-8461-8c04d5053367"), "Mink Speed Multiplier", 0.2D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
    private static final AttributeModifier JUMP_MODIFIER = (new AttributeModifier(UUID.fromString("592e8290-5c83-4467-a3ec-0ae748d9cdc4"), "Mink Jump Boost Addition", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
    private static final AttributeModifier JUMP_RESISTANCE_MODIFIER = (new AttributeModifier(UUID.fromString("d8b7e977-414a-4ca7-b538-063440e503b0"), "Mink Jump Resistance", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

    
    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
      if (!(event.getEntityLiving() instanceof PlayerEntity)) {
        return;
      }
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      World world = player.world;
      
      if (world.isRemote) {
        return;
      }
      IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
      
      if (statsProps.isMink()) {
        
        BlockPos position = player.getPosition();
        float temperature = Math.round(world.getBiome(position).getTemperature(position) * 10.0F) / 10.0F;
        
        if (world.getBiome(position).getDefaultTemperature() - 0.1F > temperature)
        {
          temperature = world.getBiome(position).getDefaultTemperature() - 0.1F;
        }
        
        float check = 0.8F;
        if (statsProps.isLionMink()) {
          check = (float)(check + 0.2D);
        }
        boolean temperatureCheck = (check > temperature);
        
        if (temperatureCheck && world.getBiome(position).getCategory() != Biome.Category.OCEAN) {
          
          if (!player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER)) {
            player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(SPEED_MODIFIER);
          }
          if (!player.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(JUMP_MODIFIER)) {
            player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(JUMP_MODIFIER);
          }
          if (!player.getAttribute(ModAttributes.FALL_RESISTANCE).hasModifier(JUMP_RESISTANCE_MODIFIER)) {
            player.getAttribute(ModAttributes.FALL_RESISTANCE).applyModifier(JUMP_RESISTANCE_MODIFIER);
          }
        } else {
          removeMinkAttributes(player);
          player.addExhaustion(0.0025F * (1.0F + temperature));
          boolean debuffConditions = (temperature > check + 0.2F && (!world.getDimension().isSurfaceWorld() || world.isDaytime()));
          
          if (debuffConditions) {
            
            int amplifier = (temperature == 2.0F) ? 1 : 0;
            player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20 * Math.round(temperature), amplifier, false, false, false));
            if (amplifier == 1)
              player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10 * Math.round(temperature), 0, false, false, false)); 
          } 
        } 
      } else {
        removeMinkAttributes(player);
      } 
    }
    public static void removeMinkAttributes(PlayerEntity player) {
      if (player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER)) {
        player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER);
      }
      if (player.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(JUMP_MODIFIER)) {
        player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(JUMP_MODIFIER);
      }
      if (player.getAttribute(ModAttributes.FALL_RESISTANCE).hasModifier(JUMP_RESISTANCE_MODIFIER))
        player.getAttribute(ModAttributes.FALL_RESISTANCE).removeModifier(JUMP_RESISTANCE_MODIFIER); 
    }
  }
  
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class ClientEvents
  {
    public static final Color LIGHTNING_COLOR = new Color(0, 199, 255, 255);
    private static ArrayList<Long> lightningValues1 = new ArrayList<>();
    private static ArrayList<Long> lightningValues2 = new ArrayList<>();

    
    @SubscribeEvent
    public static void onEntityRendered(RenderLivingEvent.Pre event) {
      if (!(event.getEntity() instanceof AbstractClientPlayerEntity)) {
        return;
      }
      AbstractClientPlayerEntity entity = (AbstractClientPlayerEntity)event.getEntity();
      IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)entity);
      IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)entity);
      
      boolean isMink = entityProps.isMink();
      boolean hasPartialMorph = (WyHelper.isNullOrEmpty(dfProps.getZoanPoint()) || PhoenixAssaultZoanInfo.INSTANCE.isActive((LivingEntity)entity) || SpringLegsZoanInfo.INSTANCE.isActive((LivingEntity)entity));
      boolean isInvisible = entity.isPotionActive(Effects.INVISIBILITY);
      
      if (isMink && hasPartialMorph && !isInvisible) {
        
        EntityRenderer renderer = null;
        if (entityProps.isBunnyMink())
          renderer = (new MinkBunnyPartialRenderer.Factory()).createRenderFor(Minecraft.getInstance().getRenderManager()); 
        if (entityProps.isDogMink())
          renderer = (new MinkDogPartialRenderer.Factory()).createRenderFor(Minecraft.getInstance().getRenderManager()); 
        if (entityProps.isLionMink()) {
          renderer = (new MinkLionPartialRenderer.Factory()).createRenderFor(Minecraft.getInstance().getRenderManager());
        }
        if (renderer != null) {
          renderer.render((Entity)entity, entity.rotationYaw, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight());
        }
      } 
    }
    
    @SubscribeEvent
    public static void onPlayerRendered(RenderPlayerEvent.Post event) {
      PlayerEntity player = event.getPlayer();
      IAbilityData data = AbilityDataCapability.get((LivingEntity)player);
      MatrixStack matrix = event.getMatrixStack();
      
      Ability eleClawAbility = data.getEquippedAbility((Ability)EleclawAbility.INSTANCE);
      boolean eleClawEnabled = (eleClawAbility != null && eleClawAbility.isContinuous());
      
      Ability ability = data.getEquippedAbility((Ability)SulongAbility.INSTANCE);
      boolean sulongEnabled = (ability != null && ability.isContinuous());
      
      ElectricalTempestaAbility tempestaAbility = (ElectricalTempestaAbility)data.getEquippedAbility((Ability)ElectricalTempestaAbility.INSTANCE);
      boolean tempestaEnabled = (tempestaAbility != null && tempestaAbility.isCharging());
      
      ElectricalShowerAbility showerAbility = (ElectricalShowerAbility)data.getEquippedAbility((Ability)ElectricalShowerAbility.INSTANCE);
      boolean ShowerEnabled = (showerAbility != null && showerAbility.isCharging());
      
      if (eleClawEnabled && !tempestaEnabled && !ShowerEnabled) {
        
        int lightningAmount = 5 + (sulongEnabled ? 3 : 0);
        matrix.push();
        matrix.translate(0.0D, 1.5D, 0.0D);
        renderElectro(matrix, event.getRenderer(), event.getBuffers(), player, event.getPartialRenderTick(), 0.01F, lightningAmount);
        matrix.pop();
      } 
    }
    
    public static void renderElectro(MatrixStack matrix, PlayerRenderer renderer, IRenderTypeBuffer buffer, PlayerEntity player, float partialRenderTick, float scale, int lightningAmount) {
      for (int i = 0; i < lightningAmount; i++) {
        for (int z = 1; z < 5; z++) {
          
          matrix.push();
          
          Color finalColor = (i == 1 && Math.random() > 0.6D && !Minecraft.getInstance().isGamePaused()) ? Color.white : LIGHTNING_COLOR;
          matrix.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
          matrix.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
          float ageInTicks = player.ticksExisted + partialRenderTick;
          float headYawOffset = MathHelper.interpolateAngle(partialRenderTick, player.prevRenderYawOffset, player.renderYawOffset);
          WyHelper.rotateCorpse(matrix, (LivingEntity)player, ageInTicks, headYawOffset, partialRenderTick);
          ((IHasArm)renderer.getEntityModel()).translateHand(player.getPrimaryHand(), matrix);
          
          matrix.scale(scale * 0.8F, scale * 1.4F, scale * 0.75F);
          matrix.translate(((player.getPrimaryHand() == HandSide.LEFT) ? 0 : -1), 0.0D, 0.0D);
          drawMinkLightning(getRandomLightningLong(lightningValues1, (Entity)player, 2, i + z - 1), matrix, buffer, 3, 6.0F, 8, finalColor, 255);
          
          matrix.pop();
        } 
      } 
    }
    public static long getRandomLightningLong(ArrayList<Long> oldValues, Entity entity, int ticks, int size) {
      boolean validValue = (oldValues.size() > size);
      if (Minecraft.getInstance().isGamePaused() || entity == null) {
        return validValue ? ((Long)oldValues.get(size)).longValue() : 0L;
      }
      if (entity.ticksExisted % ticks == 0) {
        long value = entity.world.rand.nextLong();
        if (validValue) { oldValues.set(size, Long.valueOf(value)); }
        else { oldValues.add(Long.valueOf(value)); }
         return value;
      } 
      
      return validValue ? ((Long)oldValues.get(size)).longValue() : 0L;
    }
    
    @SubscribeEvent
    public static void onHandRendering(RenderHandEvent event) {
      if (event.getHand() != Hand.MAIN_HAND || !event.getItemStack().isEmpty()) {
        return;
      }
      Minecraft mc = Minecraft.getInstance();
      ClientPlayerEntity player = mc.player;
      
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
      
      Ability eleClawAbility = abilityProps.getEquippedAbility((Ability)EleclawAbility.INSTANCE);
      boolean eleClawEnabled = (eleClawAbility != null && eleClawAbility.isContinuous());
      
      ElectricalTempestaAbility tempestaAbility = (ElectricalTempestaAbility)abilityProps.getEquippedAbility((Ability)ElectricalTempestaAbility.INSTANCE);
      boolean tempestaEnabled = (tempestaAbility != null && tempestaAbility.isCharging());
      
      ElectricalShowerAbility showerAbility = (ElectricalShowerAbility)abilityProps.getEquippedAbility((Ability)ElectricalShowerAbility.INSTANCE);
      boolean showerEnabled = (showerAbility != null && showerAbility.isCharging());
      
      if (eleClawEnabled && !tempestaEnabled && !showerEnabled) {
        
        int lightningAmount = 5;

        
        boolean flag = (player.getPrimaryHand() != HandSide.LEFT);
        
        AbilityOverlay overlay = AbilityHelper.getCurrentOverlay((PlayerEntity)player);
        MorphHelper.renderLimbFirstPerson(event.getMatrixStack(), event.getBuffers(), event.getLight(), event.getEquipProgress(), event.getSwingProgress(), player.getPrimaryHand(), overlay, null);
        
        event.getMatrixStack().push();
        
        float f = flag ? 1.0F : -1.0F;
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
        
        for (int i = 0; i < lightningAmount; i++) {
          for (int z = 1; z < 5; z++) {
            Color finalColor = (i == 1 && Math.random() > 0.6D && !Minecraft.getInstance().isGamePaused()) ? Color.white : LIGHTNING_COLOR;
            event.getMatrixStack().push();
            event.getMatrixStack().scale(0.01F, 0.035F, 0.01F);
            event.getMatrixStack().translate(flag ? -40.0D : 40.0D, -24.0D, 0.0D);
            drawMinkLightning(getRandomLightningLong(lightningValues2, (Entity)player, (Math.random() > 0.5D) ? 2 : 3, i + z - 1), event.getMatrixStack(), event.getBuffers(), 4, 6.0F, 6, finalColor, 255);
            event.getMatrixStack().pop();
          } 
        } 

        
        event.getMatrixStack().pop();
      } 
    }

    
    public static void drawMinkLightning(long seed, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, float length, int size, Color color, int offset) {
      drawMinkLightning(seed, matrixStackIn, bufferIn, packedLightIn, length, size, color, offset, 5);
    }

    
    public static void drawMinkLightning(long seed, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, float length, int size, Color color, int offset, int layers) {
      float[] afloat = new float[256], afloat1 = new float[256];
      float f = 0.0F, f1 = 0.0F;
      IVertexBuilder builder = bufferIn.getBuffer(ModRenderTypes.ENERGY);
      Matrix4f matrix = matrixStackIn.getLast().getMatrix();
      Random randPrev = new Random(seed), rand = new Random(seed);
      offset = Math.min(offset, 255);
      
      int i;
      for (i = offset; i >= 0; i--) {
        afloat[i] = f;
        afloat1[i] = f1;
        f += (randPrev.nextInt(11) - 5);
        f1 += (randPrev.nextInt(11) - 5);
      } 
      
      for (i = 0; i < 3; i++) {
        int l = 7;
        int i1 = 0;
        if (i > 0) {
          l = 7 - i;
          i1 = l - 2;
        } 
        
        float f2 = afloat[l] - f;
        float f3 = afloat1[l] - f1;
        
        for (int j1 = l; j1 >= i1; j1--) {
          float f4 = f2;
          float f5 = f3;
          f2 += (rand.nextInt(11) - 5);
          f3 += (rand.nextInt(11) - 5);
          
          Color color1 = color;
          for (int j = 1; j <= layers; j++) {
            float f6 = 0.1F + size * 0.015F * j;
            color = (Math.round(layers / 3.0F) > j) ? new Color(255, 255, 255, color1.getAlpha()) : color1;
            drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, false, false, true, false, packedLightIn, color);
            drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, true, false, true, true, packedLightIn, color);
            drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, true, true, false, true, packedLightIn, color);
            drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, false, true, false, false, packedLightIn, color);
          } 
        } 
      } 
    }
    
    private static void drawLightningQuad(Matrix4f matrix4f, IVertexBuilder builder, float x, float z, int y, float endY, float x2, float z2, float additional, boolean a, boolean c, boolean b, boolean d, int packedLight, Color color) {
      builder.pos(matrix4f, x + (a ? additional : -additional), y * endY, z + (c ? additional : -additional)).color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).lightmap(packedLight).endVertex();
      builder.pos(matrix4f, x2 + (a ? additional : -additional), (y + 1) * endY, z2 + (c ? additional : -additional)).color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).lightmap(packedLight).endVertex();
      builder.pos(matrix4f, x2 + (b ? additional : -additional), (y + 1) * endY, z2 + (d ? additional : -additional)).color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).lightmap(packedLight).endVertex();
      builder.pos(matrix4f, x + (b ? additional : -additional), y * endY, z + (d ? additional : -additional)).color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).lightmap(packedLight).endVertex();
    }
  }
}


