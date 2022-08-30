/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.ImmutableMap.Builder;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.saraaxolotl.AxolotlHeavyPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.AxolotlHeavyModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AxolotlHeavyZoanInfo
/*    */   extends ZoanInfo
/*    */ {
/* 27 */   public static final AxolotlHeavyZoanInfo INSTANCE = new AxolotlHeavyZoanInfo();
/*    */   
/* 29 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.5F, 4.0F);
/* 30 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.5F, 3.8F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public ZoanMorphModel getModel() {
/* 36 */     return (ZoanMorphModel)new AxolotlHeavyModel();
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
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
/* 51 */     float scale = 1.6F;
/* 52 */     matrixStack.scale(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 58 */     return ModAbilities.SARA_SARA_NO_MI_AXOLOTL;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 64 */     return "heavy";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 70 */     return (Ability)AxolotlHeavyPointAbility.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 76 */     return 3.7D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 82 */     return 1.8F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 88 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 94 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 95 */       .put(Pose.STANDING, STANDING_SIZE)
/* 96 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 97 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\AxolotlHeavyZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */