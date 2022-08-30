/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sai.SaiWalkPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.SaiWalkModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ public class SaiWalkZoanInfo
/*    */   extends ZoanInfo
/*    */ {
/* 24 */   public static final SaiWalkZoanInfo INSTANCE = new SaiWalkZoanInfo();
/*    */   
/* 26 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.7F, 2.3F);
/* 27 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.7F, 2.2F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public ZoanMorphModel getModel() {
/* 33 */     return (ZoanMorphModel)new SaiWalkModel();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
/* 41 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));
/*    */     
/* 43 */     float scale = 2.0F;
/* 44 */     matrixStack.scale(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 50 */     return ModAbilities.SAI_SAI_NO_MI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 56 */     return "walk";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 62 */     return (Ability)SaiWalkPointAbility.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 68 */     return 2.1D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 74 */     return 1.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 80 */     return false;
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


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\SaiWalkZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */