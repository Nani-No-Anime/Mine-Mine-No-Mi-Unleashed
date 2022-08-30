/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kage.ShadowsAsgardAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.karu.IngaZarashiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ModifiedPlayerRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class ShadowsAsgardZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  28 */   public static final ShadowsAsgardZoanInfo INSTANCE = new ShadowsAsgardZoanInfo();
/*  29 */   private float shadows = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/*  35 */     this.shadows = ((ShadowsAsgardAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility((Ability)ShadowsAsgardAbility.INSTANCE)).getShadows();
/*  36 */     boolean isSlim = entity.getSkinType().equals("slim");
/*  37 */     return (IRenderFactory)new ModifiedPlayerRenderer.Factory(this, isSlim, (1.0F + this.shadows / 60.0F));
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
/*  48 */     return null;
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
/*  60 */     return ModAbilities.KARU_KARU_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  66 */     return "karu";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  72 */     return (Ability)IngaZarashiAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  78 */     return 1.85D + (this.shadows / 30.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  84 */     return 0.5F + this.shadows / 50.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(PlayerEntity player) {
/*  92 */     boolean isFirstPerson = ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0);
/*  93 */     boolean shouldSit = (player.isPassenger() && player.getRidingEntity() != null && player.getRidingEntity().shouldRiderSit());
/*  94 */     if (isFirstPerson && shouldSit)
/*     */     {
/*  96 */       return 0.5D;
/*     */     }
/*  98 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 104 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 105 */       .put(Pose.STANDING, EntitySize.flexible(0.6F + this.shadows / 60.0F, 1.8F + this.shadows / 30.0F))
/* 106 */       .put(Pose.CROUCHING, EntitySize.flexible(0.6F + this.shadows / 60.0F, 1.6F + this.shadows / 30.0F))
/* 107 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\ShadowsAsgardZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */