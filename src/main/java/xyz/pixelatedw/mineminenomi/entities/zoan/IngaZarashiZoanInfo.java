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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.karu.IngaZarashiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.karu.KarmaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ModifiedPlayerRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class IngaZarashiZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  28 */   public static final IngaZarashiZoanInfo INSTANCE = new IngaZarashiZoanInfo();
/*  29 */   private float karma = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/*  35 */     KarmaAbility abl = (KarmaAbility)AbilityDataCapability.get((LivingEntity)entity).getUnlockedAbility((Ability)KarmaAbility.INSTANCE);
/*  36 */     if (abl != null)
/*  37 */       this.karma = abl.getKarma(); 
/*  38 */     boolean isSlim = entity.getSkinType().equals("slim");
/*  39 */     return (IRenderFactory)new ModifiedPlayerRenderer.Factory(this, isSlim, (1.0F + this.karma / 60.0F));
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
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonHand() {
/*  57 */     return true;
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
/*  69 */     return ModAbilities.KARU_KARU_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  75 */     return "karu";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  81 */     return (Ability)IngaZarashiAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  87 */     return 1.85D + (this.karma / 30.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  93 */     return 0.5F + this.karma / 50.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(PlayerEntity player) {
/* 101 */     boolean isFirstPerson = ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0);
/* 102 */     boolean shouldSit = (player.isPassenger() && player.getRidingEntity() != null && player.getRidingEntity().shouldRiderSit());
/* 103 */     if (isFirstPerson && shouldSit)
/*     */     {
/* 105 */       return 0.5D;
/*     */     }
/* 107 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 113 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 114 */       .put(Pose.STANDING, EntitySize.flexible(0.6F + this.karma / 60.0F, 1.8F + this.karma / 30.0F))
/* 115 */       .put(Pose.CROUCHING, EntitySize.flexible(0.6F + this.karma / 60.0F, 1.6F + this.karma / 30.0F))
/* 116 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\IngaZarashiZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */