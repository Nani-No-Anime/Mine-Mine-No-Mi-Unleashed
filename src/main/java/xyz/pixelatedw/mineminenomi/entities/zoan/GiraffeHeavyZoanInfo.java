/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeHeavyPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.GiraffeHeavyModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ public class GiraffeHeavyZoanInfo
/*    */   extends ZoanInfo
/*    */ {
/* 23 */   public static final GiraffeHeavyZoanInfo INSTANCE = new GiraffeHeavyZoanInfo();
/*    */   
/* 25 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.3F, 4.8F);
/* 26 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.3F, 4.7F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public ZoanMorphModel getModel() {
/* 32 */     return (ZoanMorphModel)new GiraffeHeavyModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
/* 39 */     float scale = 1.8F;
/* 40 */     matrixStack.scale(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 46 */     return ModAbilities.USHI_USHI_NO_MI_GIRAFFE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 52 */     return "heavy";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 58 */     return (Ability)GiraffeHeavyPointAbility.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 64 */     return 4.9D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 70 */     return 0.9F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean hasCulling() {
/* 77 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 83 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 84 */       .put(Pose.STANDING, STANDING_SIZE)
/* 85 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 86 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\GiraffeHeavyZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */