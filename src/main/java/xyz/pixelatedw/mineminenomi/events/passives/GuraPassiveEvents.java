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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gura.ShingenNoIchigekiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */ public class GuraPassiveEvents {
/*  32 */   private static final SphereModel GURA_SPHERE = new SphereModel();
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRendered(RenderLivingEvent.Post event) {
/*  37 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  40 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*  41 */     LivingRenderer renderer = event.getRenderer();
/*     */     
/*  43 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  45 */     ShingenNoIchigekiAbility ability = (ShingenNoIchigekiAbility)abilityProps.getEquippedAbility((Ability)ShingenNoIchigekiAbility.INSTANCE);
/*     */     
/*  47 */     if (ability != null && ability.isContinuous()) {
/*     */       
/*  49 */       event.getMatrixStack().push();
/*     */       
/*  51 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/*  52 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/*     */       
/*  54 */       float ageInTicks = player.ticksExisted + event.getPartialRenderTick();
/*  55 */       float headYawOffset = MathHelper.interpolateAngle(event.getPartialRenderTick(), player.prevRenderYawOffset, player.renderYawOffset);
/*     */       
/*  57 */       WyHelper.rotateCorpse(event.getMatrixStack(), (LivingEntity)player, ageInTicks, headYawOffset, event.getPartialRenderTick());
/*     */       
/*  59 */       event.getMatrixStack().translate(-0.04D, -1.3D, 0.12D);
/*     */       
/*  61 */       ((IHasArm)renderer.getEntityModel()).translateHand(HandSide.RIGHT, event.getMatrixStack());
/*  62 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 90.0F, true));
/*  63 */       event.getMatrixStack().scale(1.1F, 1.1F, 1.1F);
/*  64 */       event.getMatrixStack().translate(0.1D, 0.35D, -0.01D);
/*     */       
/*  66 */       GURA_SPHERE.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.ENERGY), 10000, 0, 0.4F, 0.4F, 0.4F, 0.7F);
/*     */       
/*  68 */       event.getMatrixStack().pop();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHandRendering(RenderHandEvent event) {
/*  75 */     if (event.getHand() != Hand.MAIN_HAND) {
/*     */       return;
/*     */     }
/*  78 */     if (!event.getItemStack().isEmpty()) {
/*     */       return;
/*     */     }
/*  81 */     Minecraft mc = Minecraft.getInstance();
/*  82 */     ClientPlayerEntity player = mc.player;
/*     */     
/*  84 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  86 */     ShingenNoIchigekiAbility ability = (ShingenNoIchigekiAbility)abilityProps.getEquippedAbility((Ability)ShingenNoIchigekiAbility.INSTANCE);
/*     */     
/*  88 */     if (ability != null && ability.isContinuous()) {
/*     */       
/*  90 */       event.setCanceled(true);
/*     */       
/*  92 */       EntityRendererManager renderManager = mc.getRenderManager();
/*  93 */       PlayerRenderer renderer = (PlayerRenderer)renderManager.getRenderer(player);
/*     */       
/*  95 */       event.getMatrixStack().push();
/*     */       
/*  97 */       float f = 1.0F;
/*  98 */       float f1 = MathHelper.sqrt(event.getSwingProgress());
/*  99 */       float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
/* 100 */       float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
/* 101 */       float f4 = -0.4F * MathHelper.sin(event.getSwingProgress() * 3.1415927F);
/* 102 */       event.getMatrixStack().translate((f * (f2 + 0.64000005F)), (f3 + -0.6F + event.getEquipProgress() * -0.6F), (f4 + -0.71999997F));
/* 103 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * 45.0F, true));
/* 104 */       float f5 = MathHelper.sin(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F);
/* 105 */       float f6 = MathHelper.sin(f1 * 3.1415927F);
/* 106 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * f6 * 70.0F, true));
/* 107 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.ZP, f * f5 * -20.0F, true));
/*     */       
/* 109 */       event.getMatrixStack().translate((f * -1.0F), 3.5999999046325684D, 3.5D);
/* 110 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.ZP, f * 120.0F, true));
/* 111 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 200.0F, true));
/* 112 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, f * -135.0F, true));
/* 113 */       event.getMatrixStack().translate((f * 5.6F), 0.0D, 0.0D);
/*     */       
/* 115 */       ClientPlayerEntity clientPlayerEntity = player;
/* 116 */       ((PlayerModel)renderer.getEntityModel()).bipedRightArm.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityHand(clientPlayerEntity.getLocationSkin())), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 118 */       event.getMatrixStack().translate(-0.4D, 0.8D, 0.01D);
/* 119 */       event.getMatrixStack().scale(1.5F, 1.5F, 1.5F);
/*     */       
/* 121 */       GURA_SPHERE.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.ENERGY), 10000, 0, 0.4F, 0.4F, 0.4F, 0.7F);
/*     */       
/* 123 */       event.getMatrixStack().pop();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\GuraPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */