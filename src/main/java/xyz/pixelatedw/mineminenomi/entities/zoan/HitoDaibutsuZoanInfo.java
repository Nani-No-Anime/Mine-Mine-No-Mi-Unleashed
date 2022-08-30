/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu.HitoDaibutsuPointAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.HitoDaibutsuModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.GlowingModelRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ public class HitoDaibutsuZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  27 */   public static final HitoDaibutsuZoanInfo INSTANCE = new HitoDaibutsuZoanInfo();
/*     */   
/*  29 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(3.2F, 10.0F);
/*  30 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(3.2F, 9.8F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  36 */     return (IRenderFactory)new GlowingModelRenderer.Factory(this, GlowingModelRenderer.Type.DAIBUTSU);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public ZoanMorphModel getModel() {
/*  43 */     return (ZoanMorphModel)new HitoDaibutsuModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  61 */     return ModAbilities.HITO_HITO_NO_MI_DAIBUTSU;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  67 */     return "daibutsu";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  73 */     return (Ability)HitoDaibutsuPointAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  79 */     return 9.800000190734863D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  85 */     return 2.1F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(PlayerEntity player) {
/*  92 */     return 12.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 104 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 105 */       .put(Pose.STANDING, STANDING_SIZE)
/* 106 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 107 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\HitoDaibutsuZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */