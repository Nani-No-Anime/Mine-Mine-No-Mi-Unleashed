/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.saraaxolotl.AxolotlWalkPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.AxolotlWalkModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AxolotlWalkZoanInfo
/*    */   extends ZoanInfo
/*    */ {
/* 27 */   public static final AxolotlWalkZoanInfo INSTANCE = new AxolotlWalkZoanInfo();
/*    */   
/* 29 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.6F, 0.6F);
/* 30 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.6F, 0.5F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public ZoanMorphModel getModel() {
/* 36 */     return (ZoanMorphModel)new AxolotlWalkModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
/* 43 */     int scheme = Math.max(0, (int)(entity.getUniqueID().getMostSignificantBits() % 4L));
/* 44 */     return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/sara_sara_axolotl_" + getForm() + "_" + scheme + ".png");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 56 */     return ModAbilities.SARA_SARA_NO_MI_AXOLOTL;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 62 */     return "walk";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 68 */     return (Ability)AxolotlWalkPointAbility.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 74 */     return 0.5D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 80 */     return 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 86 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 87 */       .put(Pose.STANDING, STANDING_SIZE)
/* 88 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 89 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\AxolotlWalkZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */