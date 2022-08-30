/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.Quaternion;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class HoruPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 30 */     LivingEntity entity = event.getEntityLiving();
/*    */     
/* 32 */     if (!entity.isAlive()) {
/*    */       return;
/*    */     }
/* 35 */     if (entity.isPotionActive(ModEffects.CHIYU_HORMONE)) {
/*    */       
/* 37 */       EffectInstance effect = entity.getActivePotionEffect(ModEffects.CHIYU_HORMONE);
/*    */       
/* 39 */       if (effect.getDuration() <= 2) {
/* 40 */         entity.addPotionEffect(new EffectInstance(Effects.HUNGER, 200, 1));
/*    */       }
/*    */     } 
/* 43 */     if (entity.isPotionActive(ModEffects.TENSION_HORMONE)) {
/*    */       
/* 45 */       EffectInstance effect = entity.getActivePotionEffect(ModEffects.TENSION_HORMONE);
/*    */       
/* 47 */       if (effect.getDuration() <= 2) {
/* 48 */         entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 400, 1));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static void onEntityRendered(RenderLivingEvent.Post event) {
/* 56 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 59 */     PlayerEntity entity = (PlayerEntity)event.getEntity();
/* 60 */     PlayerRenderer renderer = (PlayerRenderer)event.getRenderer();
/*    */     
/* 62 */     AbstractClientPlayerEntity abstractOwner = (AbstractClientPlayerEntity)entity;
/* 63 */     BipedModel model = (BipedModel)renderer.getEntityModel();
/*    */     
/* 65 */     if (!entity.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE)) {
/*    */       return;
/*    */     }
/* 68 */     event.getMatrixStack().push();
/*    */     
/* 70 */     event.getMatrixStack().translate(0.0D, 1.3D, 0.0D);
/*    */     
/* 72 */     if (entity.isSneaking()) {
/* 73 */       event.getMatrixStack().translate(0.0D, 0.6D, 0.0D);
/*    */     }
/* 75 */     event.getMatrixStack().rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/* 76 */     event.getMatrixStack().rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/*    */     
/* 78 */     float ageInTicks = entity.ticksExisted + event.getPartialRenderTick();
/* 79 */     float headYawOffset = MathHelper.interpolateAngle(event.getPartialRenderTick(), entity.prevRenderYawOffset, entity.renderYawOffset);
/* 80 */     WyHelper.rotateCorpse(event.getMatrixStack(), (LivingEntity)entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
/*    */     
/* 82 */     event.getMatrixStack().scale(3.5F, 3.5F, 3.5F);
/*    */     
/* 84 */     model.bipedHead.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityHand(renderer.getEntityTexture(abstractOwner))), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 86 */     event.getMatrixStack().pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\HoruPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */