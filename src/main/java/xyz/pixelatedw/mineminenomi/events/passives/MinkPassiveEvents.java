/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Matrix4f;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.EleclawAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalShowerAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalTempestaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.SulongAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.SpringLegsZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.MinkBunnyPartialRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.MinkDogPartialRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.MinkLionPartialRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinkPassiveEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class CommonEvents
/*     */   {
/*  69 */     private static final AttributeModifier SPEED_MODIFIER = (new AttributeModifier(UUID.fromString("d70f4c72-ba2e-4aaf-8461-8c04d5053367"), "Mink Speed Multiplier", 0.2D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/*  70 */     private static final AttributeModifier JUMP_MODIFIER = (new AttributeModifier(UUID.fromString("592e8290-5c83-4467-a3ec-0ae748d9cdc4"), "Mink Jump Boost Addition", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  71 */     private static final AttributeModifier JUMP_RESISTANCE_MODIFIER = (new AttributeModifier(UUID.fromString("d8b7e977-414a-4ca7-b538-063440e503b0"), "Mink Jump Resistance", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  76 */       if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/*  79 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  80 */       World world = player.world;
/*     */       
/*  82 */       if (world.isRemote) {
/*     */         return;
/*     */       }
/*  85 */       IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
/*     */       
/*  87 */       if (statsProps.isMink()) {
/*     */         
/*  89 */         BlockPos position = player.getPosition();
/*  90 */         float temperature = Math.round(world.getBiome(position).getTemperature(position) * 10.0F) / 10.0F;
/*     */         
/*  92 */         if (world.getBiome(position).getDefaultTemperature() - 0.1F > temperature)
/*     */         {
/*  94 */           temperature = world.getBiome(position).getDefaultTemperature() - 0.1F;
/*     */         }
/*     */         
/*  97 */         float check = 0.8F;
/*  98 */         if (statsProps.isLionMink()) {
/*  99 */           check = (float)(check + 0.2D);
/*     */         }
/* 101 */         boolean temperatureCheck = (check > temperature);
/*     */         
/* 103 */         if (temperatureCheck && world.getBiome(position).getCategory() != Biome.Category.OCEAN) {
/*     */           
/* 105 */           if (!player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER)) {
/* 106 */             player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(SPEED_MODIFIER);
/*     */           }
/* 108 */           if (!player.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(JUMP_MODIFIER)) {
/* 109 */             player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(JUMP_MODIFIER);
/*     */           }
/* 111 */           if (!player.getAttribute(ModAttributes.FALL_RESISTANCE).hasModifier(JUMP_RESISTANCE_MODIFIER)) {
/* 112 */             player.getAttribute(ModAttributes.FALL_RESISTANCE).applyModifier(JUMP_RESISTANCE_MODIFIER);
/*     */           }
/*     */         } else {
/* 115 */           removeMinkAttributes(player);
/* 116 */           player.addExhaustion(0.0025F * (1.0F + temperature));
/* 117 */           boolean debuffConditions = (temperature > check + 0.2F && (!world.getDimension().isSurfaceWorld() || world.isDaytime()));
/*     */           
/* 119 */           if (debuffConditions) {
/*     */             
/* 121 */             int amplifier = (temperature == 2.0F) ? 1 : 0;
/* 122 */             player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20 * Math.round(temperature), amplifier, false, false, false));
/* 123 */             if (amplifier == 1)
/* 124 */               player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10 * Math.round(temperature), 0, false, false, false)); 
/*     */           } 
/*     */         } 
/*     */       } else {
/* 128 */         removeMinkAttributes(player);
/*     */       } 
/*     */     }
/*     */     public static void removeMinkAttributes(PlayerEntity player) {
/* 132 */       if (player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER)) {
/* 133 */         player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER);
/*     */       }
/* 135 */       if (player.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(JUMP_MODIFIER)) {
/* 136 */         player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(JUMP_MODIFIER);
/*     */       }
/* 138 */       if (player.getAttribute(ModAttributes.FALL_RESISTANCE).hasModifier(JUMP_RESISTANCE_MODIFIER))
/* 139 */         player.getAttribute(ModAttributes.FALL_RESISTANCE).removeModifier(JUMP_RESISTANCE_MODIFIER); 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class ClientEvents
/*     */   {
/* 146 */     public static final Color LIGHTNING_COLOR = new Color(0, 199, 255, 255);
/* 147 */     private static ArrayList<Long> lightningValues1 = new ArrayList<>();
/* 148 */     private static ArrayList<Long> lightningValues2 = new ArrayList<>();
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 153 */       if (!(event.getEntity() instanceof AbstractClientPlayerEntity)) {
/*     */         return;
/*     */       }
/* 156 */       AbstractClientPlayerEntity entity = (AbstractClientPlayerEntity)event.getEntity();
/* 157 */       IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)entity);
/* 158 */       IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)entity);
/*     */       
/* 160 */       boolean isMink = entityProps.isMink();
/* 161 */       boolean hasPartialMorph = (WyHelper.isNullOrEmpty(dfProps.getZoanPoint()) || PhoenixAssaultZoanInfo.INSTANCE.isActive((LivingEntity)entity) || SpringLegsZoanInfo.INSTANCE.isActive((LivingEntity)entity));
/* 162 */       boolean isInvisible = entity.isPotionActive(Effects.INVISIBILITY);
/*     */       
/* 164 */       if (isMink && hasPartialMorph && !isInvisible) {
/*     */         
/* 166 */         EntityRenderer renderer = null;
/* 167 */         if (entityProps.isBunnyMink())
/* 168 */           renderer = (new MinkBunnyPartialRenderer.Factory()).createRenderFor(Minecraft.getInstance().getRenderManager()); 
/* 169 */         if (entityProps.isDogMink())
/* 170 */           renderer = (new MinkDogPartialRenderer.Factory()).createRenderFor(Minecraft.getInstance().getRenderManager()); 
/* 171 */         if (entityProps.isLionMink()) {
/* 172 */           renderer = (new MinkLionPartialRenderer.Factory()).createRenderFor(Minecraft.getInstance().getRenderManager());
/*     */         }
/* 174 */         if (renderer != null) {
/* 175 */           renderer.render((Entity)entity, entity.rotationYaw, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight());
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPlayerRendered(RenderPlayerEvent.Post event) {
/* 182 */       PlayerEntity player = event.getPlayer();
/* 183 */       IAbilityData data = AbilityDataCapability.get((LivingEntity)player);
/* 184 */       MatrixStack matrix = event.getMatrixStack();
/*     */       
/* 186 */       Ability eleClawAbility = data.getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/* 187 */       boolean eleClawEnabled = (eleClawAbility != null && eleClawAbility.isContinuous());
/*     */       
/* 189 */       Ability ability = data.getEquippedAbility((Ability)SulongAbility.INSTANCE);
/* 190 */       boolean sulongEnabled = (ability != null && ability.isContinuous());
/*     */       
/* 192 */       ElectricalTempestaAbility tempestaAbility = (ElectricalTempestaAbility)data.getEquippedAbility((Ability)ElectricalTempestaAbility.INSTANCE);
/* 193 */       boolean tempestaEnabled = (tempestaAbility != null && tempestaAbility.isCharging());
/*     */       
/* 195 */       ElectricalShowerAbility showerAbility = (ElectricalShowerAbility)data.getEquippedAbility((Ability)ElectricalShowerAbility.INSTANCE);
/* 196 */       boolean ShowerEnabled = (showerAbility != null && showerAbility.isCharging());
/*     */       
/* 198 */       if (eleClawEnabled && !tempestaEnabled && !ShowerEnabled) {
/*     */         
/* 200 */         int lightningAmount = 5 + (sulongEnabled ? 3 : 0);
/* 201 */         matrix.push();
/* 202 */         matrix.translate(0.0D, 1.5D, 0.0D);
/* 203 */         renderElectro(matrix, event.getRenderer(), event.getBuffers(), player, event.getPartialRenderTick(), 0.01F, lightningAmount);
/* 204 */         matrix.pop();
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void renderElectro(MatrixStack matrix, PlayerRenderer renderer, IRenderTypeBuffer buffer, PlayerEntity player, float partialRenderTick, float scale, int lightningAmount) {
/* 209 */       for (int i = 0; i < lightningAmount; i++) {
/* 210 */         for (int z = 1; z < 5; z++) {
/*     */           
/* 212 */           matrix.push();
/*     */           
/* 214 */           Color finalColor = (i == 1 && Math.random() > 0.6D && !Minecraft.getInstance().isGamePaused()) ? Color.white : LIGHTNING_COLOR;
/* 215 */           matrix.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/* 216 */           matrix.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/* 217 */           float ageInTicks = player.ticksExisted + partialRenderTick;
/* 218 */           float headYawOffset = MathHelper.interpolateAngle(partialRenderTick, player.prevRenderYawOffset, player.renderYawOffset);
/* 219 */           WyHelper.rotateCorpse(matrix, (LivingEntity)player, ageInTicks, headYawOffset, partialRenderTick);
/* 220 */           ((IHasArm)renderer.getEntityModel()).translateHand(player.getPrimaryHand(), matrix);
/*     */           
/* 222 */           matrix.scale(scale * 0.8F, scale * 1.4F, scale * 0.75F);
/* 223 */           matrix.translate(((player.getPrimaryHand() == HandSide.LEFT) ? 0 : -1), 0.0D, 0.0D);
/* 224 */           drawMinkLightning(getRandomLightningLong(lightningValues1, (Entity)player, 2, i + z - 1), matrix, buffer, 3, 6.0F, 8, finalColor, 255);
/*     */           
/* 226 */           matrix.pop();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     public static long getRandomLightningLong(ArrayList<Long> oldValues, Entity entity, int ticks, int size) {
/* 231 */       boolean validValue = (oldValues.size() > size);
/* 232 */       if (Minecraft.getInstance().isGamePaused() || entity == null) {
/* 233 */         return validValue ? ((Long)oldValues.get(size)).longValue() : 0L;
/*     */       }
/* 235 */       if (entity.ticksExisted % ticks == 0) {
/* 236 */         long value = entity.world.rand.nextLong();
/* 237 */         if (validValue) { oldValues.set(size, Long.valueOf(value)); }
/* 238 */         else { oldValues.add(Long.valueOf(value)); }
/* 239 */          return value;
/*     */       } 
/*     */       
/* 242 */       return validValue ? ((Long)oldValues.get(size)).longValue() : 0L;
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onHandRendering(RenderHandEvent event) {
/* 247 */       if (event.getHand() != Hand.MAIN_HAND || !event.getItemStack().isEmpty()) {
/*     */         return;
/*     */       }
/* 250 */       Minecraft mc = Minecraft.getInstance();
/* 251 */       ClientPlayerEntity player = mc.player;
/*     */       
/* 253 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 255 */       Ability eleClawAbility = abilityProps.getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/* 256 */       boolean eleClawEnabled = (eleClawAbility != null && eleClawAbility.isContinuous());
/*     */       
/* 258 */       ElectricalTempestaAbility tempestaAbility = (ElectricalTempestaAbility)abilityProps.getEquippedAbility((Ability)ElectricalTempestaAbility.INSTANCE);
/* 259 */       boolean tempestaEnabled = (tempestaAbility != null && tempestaAbility.isCharging());
/*     */       
/* 261 */       ElectricalShowerAbility showerAbility = (ElectricalShowerAbility)abilityProps.getEquippedAbility((Ability)ElectricalShowerAbility.INSTANCE);
/* 262 */       boolean showerEnabled = (showerAbility != null && showerAbility.isCharging());
/*     */       
/* 264 */       if (eleClawEnabled && !tempestaEnabled && !showerEnabled) {
/*     */         
/* 266 */         int lightningAmount = 5;
/*     */ 
/*     */         
/* 269 */         boolean flag = (player.getPrimaryHand() != HandSide.LEFT);
/*     */         
/* 271 */         AbilityOverlay overlay = AbilityHelper.getCurrentOverlay((PlayerEntity)player);
/* 272 */         MorphHelper.renderLimbFirstPerson(event.getMatrixStack(), event.getBuffers(), event.getLight(), event.getEquipProgress(), event.getSwingProgress(), player.getPrimaryHand(), overlay, null);
/*     */         
/* 274 */         event.getMatrixStack().push();
/*     */         
/* 276 */         float f = flag ? 1.0F : -1.0F;
/* 277 */         float f1 = MathHelper.sqrt(event.getSwingProgress());
/* 278 */         float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
/* 279 */         float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
/* 280 */         float f4 = -0.4F * MathHelper.sin(event.getSwingProgress() * 3.1415927F);
/* 281 */         event.getMatrixStack().translate((f * (f2 + 0.64000005F)), (f3 + -0.6F + event.getEquipProgress() * -0.6F), (f4 + -0.71999997F));
/* 282 */         event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * 45.0F, true));
/* 283 */         float f5 = MathHelper.sin(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F);
/* 284 */         float f6 = MathHelper.sin(f1 * 3.1415927F);
/* 285 */         event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * f6 * 70.0F, true));
/* 286 */         event.getMatrixStack().rotate(new Quaternion(Vector3f.ZP, f * f5 * -20.0F, true));
/*     */         
/* 288 */         event.getMatrixStack().translate((f * -1.0F), 3.5999999046325684D, 3.5D);
/* 289 */         event.getMatrixStack().rotate(new Quaternion(Vector3f.ZP, f * 120.0F, true));
/* 290 */         event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 200.0F, true));
/* 291 */         event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * -135.0F, true));
/* 292 */         event.getMatrixStack().translate((f * 5.6F), 0.0D, 0.0D);
/*     */         
/* 294 */         for (int i = 0; i < lightningAmount; i++) {
/* 295 */           for (int z = 1; z < 5; z++) {
/* 296 */             Color finalColor = (i == 1 && Math.random() > 0.6D && !Minecraft.getInstance().isGamePaused()) ? Color.white : LIGHTNING_COLOR;
/* 297 */             event.getMatrixStack().push();
/* 298 */             event.getMatrixStack().scale(0.01F, 0.035F, 0.01F);
/* 299 */             event.getMatrixStack().translate(flag ? -40.0D : 40.0D, -24.0D, 0.0D);
/* 300 */             drawMinkLightning(getRandomLightningLong(lightningValues2, (Entity)player, (Math.random() > 0.5D) ? 2 : 3, i + z - 1), event.getMatrixStack(), event.getBuffers(), 4, 6.0F, 6, finalColor, 255);
/* 301 */             event.getMatrixStack().pop();
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 306 */         event.getMatrixStack().pop();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public static void drawMinkLightning(long seed, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, float length, int size, Color color, int offset) {
/* 312 */       drawMinkLightning(seed, matrixStackIn, bufferIn, packedLightIn, length, size, color, offset, 5);
/*     */     }
/*     */ 
/*     */     
/*     */     public static void drawMinkLightning(long seed, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, float length, int size, Color color, int offset, int layers) {
/* 317 */       float[] afloat = new float[256], afloat1 = new float[256];
/* 318 */       float f = 0.0F, f1 = 0.0F;
/* 319 */       IVertexBuilder builder = bufferIn.getBuffer(ModRenderTypes.ENERGY);
/* 320 */       Matrix4f matrix = matrixStackIn.getLast().getMatrix();
/* 321 */       Random randPrev = new Random(seed), rand = new Random(seed);
/* 322 */       offset = Math.min(offset, 255);
/*     */       
/*     */       int i;
/* 325 */       for (i = offset; i >= 0; i--) {
/* 326 */         afloat[i] = f;
/* 327 */         afloat1[i] = f1;
/* 328 */         f += (randPrev.nextInt(11) - 5);
/* 329 */         f1 += (randPrev.nextInt(11) - 5);
/*     */       } 
/*     */       
/* 332 */       for (i = 0; i < 3; i++) {
/* 333 */         int l = 7;
/* 334 */         int i1 = 0;
/* 335 */         if (i > 0) {
/* 336 */           l = 7 - i;
/* 337 */           i1 = l - 2;
/*     */         } 
/*     */         
/* 340 */         float f2 = afloat[l] - f;
/* 341 */         float f3 = afloat1[l] - f1;
/*     */         
/* 343 */         for (int j1 = l; j1 >= i1; j1--) {
/* 344 */           float f4 = f2;
/* 345 */           float f5 = f3;
/* 346 */           f2 += (rand.nextInt(11) - 5);
/* 347 */           f3 += (rand.nextInt(11) - 5);
/*     */           
/* 349 */           Color color1 = color;
/* 350 */           for (int j = 1; j <= layers; j++) {
/* 351 */             float f6 = 0.1F + size * 0.015F * j;
/* 352 */             color = (Math.round(layers / 3.0F) > j) ? new Color(255, 255, 255, color1.getAlpha()) : color1;
/* 353 */             drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, false, false, true, false, packedLightIn, color);
/* 354 */             drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, true, false, true, true, packedLightIn, color);
/* 355 */             drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, true, true, false, true, packedLightIn, color);
/* 356 */             drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, false, true, false, false, packedLightIn, color);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private static void drawLightningQuad(Matrix4f matrix4f, IVertexBuilder builder, float x, float z, int y, float endY, float x2, float z2, float additional, boolean a, boolean c, boolean b, boolean d, int packedLight, Color color) {
/* 363 */       builder.pos(matrix4f, x + (a ? additional : -additional), y * endY, z + (c ? additional : -additional)).color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).lightmap(packedLight).endVertex();
/* 364 */       builder.pos(matrix4f, x2 + (a ? additional : -additional), (y + 1) * endY, z2 + (c ? additional : -additional)).color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).lightmap(packedLight).endVertex();
/* 365 */       builder.pos(matrix4f, x2 + (b ? additional : -additional), (y + 1) * endY, z2 + (d ? additional : -additional)).color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).lightmap(packedLight).endVertex();
/* 366 */       builder.pos(matrix4f, x + (b ? additional : -additional), y * endY, z + (d ? additional : -additional)).color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).lightmap(packedLight).endVertex();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\MinkPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */