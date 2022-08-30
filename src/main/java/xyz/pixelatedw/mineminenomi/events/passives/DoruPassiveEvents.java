/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruBallAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.CandleLockModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class DoruPassiveEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class ClientEvents
/*     */   {
/*  37 */     private static final String[] COLORS = new String[] { "#c21d1f", "#8f176b", "#4d178f", "#17508d", "#158d7b", "#128d21", "#c8cb17", "#5ae163" };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     private static Color randomColor1 = chooseRandomColor();
/*  43 */     private static Color randomColor2 = chooseRandomColor();
/*     */ 
/*     */ 
/*     */     
/*     */     private static Color chooseRandomColor() {
/*  48 */       int i = (int)WyHelper.randomWithRange(0, COLORS.length - 1);
/*  49 */       String hex = COLORS[i];
/*  50 */       return WyHelper.hexToRGB(hex);
/*     */     }
/*     */     
/*  53 */     private static final SphereModel DORU_BALL = new SphereModel();
/*  54 */     private static final CandleLockModel CANDLE_LOCK = new CandleLockModel();
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/*  59 */       LivingEntity entity = event.getEntity();
/*     */       
/*  61 */       Color color = Color.WHITE;
/*  62 */       if (!entity.isPotionActive(ModEffects.CANDLE_LOCK)) {
/*     */         return;
/*     */       }
/*  65 */       if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getDuration() <= 0) {
/*     */         
/*  67 */         entity.removePotionEffect(ModEffects.CANDLE_LOCK);
/*     */         
/*     */         return;
/*     */       } 
/*  71 */       if (entity.getActivePotionEffect(ModEffects.CANDLE_LOCK).getAmplifier() == 2) {
/*  72 */         color = randomColor1;
/*     */       }
/*  74 */       event.getMatrixStack().push();
/*     */       
/*  76 */       event.getMatrixStack().translate(0.0D, -0.8D, 0.0D);
/*  77 */       event.getMatrixStack().rotate(new Quaternion(Vector3f.YN, entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * event.getPartialRenderTick() + 180.0F, true));
/*     */       
/*  79 */       CANDLE_LOCK.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityHand(ModResources.CANDLE_LOCK)), event.getLight(), 0, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */       
/*  81 */       event.getMatrixStack().pop();
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPlayerRendered(RenderPlayerEvent.Pre event) {
/*  87 */       PlayerEntity player = event.getPlayer();
/*  88 */       IAbilityData data = AbilityDataCapability.get((LivingEntity)player);
/*  89 */       Color color = Color.WHITE;
/*     */       
/*  91 */       Ability ability = data.getEquippedAbility((Ability)DoruDoruBallAbility.INSTANCE);
/*  92 */       boolean isActive = (ability != null && ability.isContinuous());
/*     */       
/*  94 */       if (isActive) {
/*     */         
/*  96 */         event.setCanceled(true);
/*  97 */         if ((event.getPlayer()).inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE))) {
/*  98 */           color = randomColor2;
/*     */         }
/* 100 */         float zoanHeight = 1.0F;
/* 101 */         ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
/* 102 */         if (info != null)
/*     */         {
/* 104 */           zoanHeight = ((EntitySize)info.getSizes().get(Pose.STANDING)).height;
/*     */         }
/*     */         
/* 107 */         DoruDoruBallAbility abl = (DoruDoruBallAbility)ability;
/* 108 */         abl.rotateAngleX += (player.getMotion()).z;
/* 109 */         abl.rotateAngleZ -= (player.getMotion()).x;
/* 110 */         event.getMatrixStack().push();
/*     */         
/* 112 */         event.getMatrixStack().translate(0.0D, (player.getEyeHeight() - 0.5F), 0.0D);
/* 113 */         float scale = 8.0F + zoanHeight;
/* 114 */         event.getMatrixStack().scale(scale, scale, scale);
/* 115 */         DORU_BALL.setRotateAngle(DORU_BALL.shape1, (float)abl.rotateAngleX, 0.0F, (float)abl.rotateAngleZ);
/* 116 */         DORU_BALL.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityBody(ModResources.CANDLE_LOCK)), event.getLight(), 0, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */         
/* 118 */         event.getMatrixStack().pop();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class CommonEvents
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 130 */       if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/* 133 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 134 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 136 */       Ability doruBallAbility = abilityProps.getEquippedAbility((Ability)DoruDoruBallAbility.INSTANCE);
/* 137 */       boolean isDoruBallActive = (doruBallAbility != null && doruBallAbility.isContinuous());
/* 138 */       if (isDoruBallActive)
/* 139 */         player.setMotion(0.0D, -5.0D, 0.0D); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\DoruPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */