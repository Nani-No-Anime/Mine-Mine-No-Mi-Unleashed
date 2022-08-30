/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.MiniRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ public class MiniZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  26 */   public static final MiniZoanInfo INSTANCE = new MiniZoanInfo();
/*     */   
/*  28 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.2F, 0.4F);
/*  29 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.2F, 0.39F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/*  35 */     boolean isSlim = entity.getSkinType().equals("slim");
/*  36 */     return (IRenderFactory)new MiniRenderer.Factory(this, isSlim);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public ZoanMorphModel getModel() {
/*  47 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  59 */     return ModAbilities.MINI_MINI_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  65 */     return "mini";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  71 */     return (Ability)MiniMiniAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  77 */     return 0.4D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  83 */     return 0.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(PlayerEntity player) {
/*  91 */     boolean isFirstPerson = ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0);
/*  92 */     boolean shouldSit = (player.isPassenger() && player.getRidingEntity() != null && player.getRidingEntity().shouldRiderSit());
/*  93 */     if (isFirstPerson && shouldSit)
/*     */     {
/*  95 */       return 0.5D;
/*     */     }
/*  97 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 103 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 104 */       .put(Pose.STANDING, STANDING_SIZE)
/* 105 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 106 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\MiniZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */