/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mega.MegaMegaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.MegaRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ public class MegaZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  26 */   public static final MegaZoanInfo INSTANCE = new MegaZoanInfo();
/*     */   
/*  28 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.7F, 2.4F);
/*  29 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.7F, 2.39F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/*  35 */     boolean isSlim = entity.getSkinType().equals("slim");
/*  36 */     return (IRenderFactory)new MegaRenderer.Factory(this, isSlim);
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
/*  59 */     return ModAbilities.MEGA_MEGA_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  65 */     return "mega";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  71 */     return (Ability)MegaMegaAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  77 */     return 8.45D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  83 */     return 1.25F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(PlayerEntity player) {
/*  90 */     return 8.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(PlayerEntity player) {
/* 104 */     boolean isFirstPerson = ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0);
/* 105 */     boolean shouldSit = (player.isPassenger() && player.getRidingEntity() != null && player.getRidingEntity().shouldRiderSit());
/* 106 */     if (isFirstPerson && shouldSit)
/*     */     {
/* 108 */       return 0.5D;
/*     */     }
/* 110 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
            Builder<Pose, EntitySize> builder = ImmutableMap.builder();
/* 116 */     return builder
/* 117 */       .put(Pose.STANDING, EntitySize.flexible(4.0F, 8.5F))
/* 118 */       .put(Pose.CROUCHING, EntitySize.flexible(4.0F, 7.6F))
/* 119 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\MegaZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */