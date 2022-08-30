/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.PunkCornaDioRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ public class PunkCornaDioZoanInfo
/*    */   extends ZoanInfo
/*    */ {
/* 20 */   public static final PunkCornaDioZoanInfo INSTANCE = new PunkCornaDioZoanInfo();
/*    */   
/* 22 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(3.0F, 2.1F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/* 28 */     return (IRenderFactory)new PunkCornaDioRenderer.Factory(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 34 */     return "punk_corna_dio";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanMorphModel getModel() {
/* 46 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 52 */     return 2.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 58 */     return 1.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 70 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 71 */       .put(Pose.STANDING, STANDING_SIZE)
/* 72 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\PunkCornaDioZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */