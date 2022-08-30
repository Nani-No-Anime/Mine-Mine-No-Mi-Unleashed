/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bari.BariBariNoPistolAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */ public class BariPassiveEvents {
/*  35 */   private static final SphereModel BARI_SPHERE = new SphereModel();
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRendered(RenderLivingEvent.Post event) {
/*  40 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  43 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*  44 */     LivingRenderer renderer = event.getRenderer();
/*     */     
/*  46 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  47 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  49 */     if (!devilFruitProps.getDevilFruit().equals("bari_bari")) {
/*     */       return;
/*     */     }
/*  52 */     BariBariNoPistolAbility ability = (BariBariNoPistolAbility)abilityProps.getEquippedAbility((Ability)BariBariNoPistolAbility.INSTANCE);
/*     */     
/*  54 */     if (ability != null && ability.isContinuous()) {
/*     */       
/*  56 */       event.getMatrixStack().push();
/*     */       
/*  58 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/*  59 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/*     */ 
/*     */       
/*  62 */       float ageInTicks = player.ticksExisted + event.getPartialRenderTick();
/*  63 */       float headYawOffset = MathHelper.interpolateAngle(event.getPartialRenderTick(), player.prevRenderYawOffset, player.renderYawOffset);
/*     */       
/*  65 */       WyHelper.rotateCorpse(event.getMatrixStack(), (LivingEntity)player, ageInTicks, headYawOffset, event.getPartialRenderTick());
/*     */       
/*  67 */       event.getMatrixStack().translate(-0.04D, -1.3D, 0.12D);
/*     */       
/*  69 */       ((IHasArm)renderer.getEntityModel()).translateHand(HandSide.RIGHT, event.getMatrixStack());
/*  70 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 90.0F, true));
/*  71 */       event.getMatrixStack().scale(1.25F, 1.25F, 1.25F);
/*  72 */       event.getMatrixStack().translate(0.1D, 0.4D, -0.01D);
/*     */       
/*  74 */       BARI_SPHERE.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.TRANSPARENT_COLOR), event.getLight(), 0, 0.5F, 1.0F, 0.8F, 0.4F);
/*     */       
/*  76 */       event.getMatrixStack().pop();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHandRendering(RenderHandEvent event) {
/*  83 */     if (event.getHand() != Hand.MAIN_HAND) {
/*     */       return;
/*     */     }
/*  86 */     if (!event.getItemStack().isEmpty()) {
/*     */       return;
/*     */     }
/*  89 */     Minecraft mc = Minecraft.getInstance();
/*  90 */     ClientPlayerEntity player = mc.player;
/*     */     
/*  92 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  93 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  95 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.BARI_BARI_NO_MI)) {
/*     */       return;
/*     */     }
/*  98 */     BariBariNoPistolAbility ability = (BariBariNoPistolAbility)abilityProps.getEquippedAbility((Ability)BariBariNoPistolAbility.INSTANCE);
/*     */     
/* 100 */     if (ability != null && ability.isContinuous()) {
/*     */       
/* 102 */       event.setCanceled(true);
/*     */       
/* 104 */       EntityRendererManager renderManager = mc.getRenderManager();
/* 105 */       PlayerRenderer renderer = (PlayerRenderer)renderManager.getRenderer(player);
/*     */       
/* 107 */       event.getMatrixStack().push();
/*     */       
/* 109 */       float f = 1.0F;
/* 110 */       float f1 = MathHelper.sqrt(event.getSwingProgress());
/* 111 */       float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
/* 112 */       float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
/* 113 */       float f4 = -0.4F * MathHelper.sin(event.getSwingProgress() * 3.1415927F);
/* 114 */       event.getMatrixStack().translate((f * (f2 + 0.64000005F)), (f3 + -0.6F + event.getEquipProgress() * -0.6F), (f4 + -0.71999997F));
/* 115 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * 45.0F, true));
/* 116 */       float f5 = MathHelper.sin(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F);
/* 117 */       float f6 = MathHelper.sin(f1 * 3.1415927F);
/* 118 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * f6 * 70.0F, true));
/* 119 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.ZP, f * f5 * -20.0F, true));
/*     */       
/* 121 */       event.getMatrixStack().translate((f * -1.0F), 3.5999999046325684D, 3.5D);
/* 122 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.ZP, f * 120.0F, true));
/* 123 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 200.0F, true));
/* 124 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * -135.0F, true));
/* 125 */       event.getMatrixStack().translate((f * 5.6F), 0.0D, 0.0D);
/*     */       
/* 127 */       ClientPlayerEntity clientPlayerEntity = player;
/* 128 */       ((PlayerModel)renderer.getEntityModel()).bipedRightArm.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityHand(clientPlayerEntity.getLocationSkin())), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 130 */       event.getMatrixStack().translate(-0.4D, 0.8D, 0.01D);
/* 131 */       event.getMatrixStack().scale(2.0F, 2.0F, 2.0F);
/*     */       
/* 133 */       BARI_SPHERE.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.TRANSPARENT_COLOR), event.getLight(), 0, 0.5F, 1.0F, 0.8F, 0.4F);
/*     */       
/* 135 */       event.getMatrixStack().pop();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\BariPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */