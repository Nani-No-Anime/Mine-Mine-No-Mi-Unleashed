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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.zou.ZouGuardPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.ZouGuardModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ public class ZouGuardZoanInfo
/*    */   extends ZoanInfo
/*    */ {
/* 23 */   public static final ZouGuardZoanInfo INSTANCE = new ZouGuardZoanInfo();
/*    */   
/* 25 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(1.5F, 3.0F);
/* 26 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(1.5F, 2.9F);
/* 27 */   private static final EntitySize FLYING_SIZE = EntitySize.flexible(1.5F, 3.0F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public ZoanMorphModel getModel() {
/* 33 */     return (ZoanMorphModel)new ZouGuardModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
/* 40 */     float scale = 1.8F;
/* 41 */     matrixStack.scale(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 47 */     return ModAbilities.ZOU_ZOU_NO_MI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 53 */     return "guard";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 59 */     return (Ability)ZouGuardPointAbility.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 65 */     return 2.9D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 71 */     return 1.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 77 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 83 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 84 */       .put(Pose.STANDING, STANDING_SIZE)
/* 85 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 86 */       .put(Pose.FALL_FLYING, FLYING_SIZE)
/* 87 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\ZouGuardZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */