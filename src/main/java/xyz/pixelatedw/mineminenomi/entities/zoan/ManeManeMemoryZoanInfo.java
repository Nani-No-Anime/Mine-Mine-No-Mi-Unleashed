/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ManeManeMemoryRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class ManeManeMemoryZoanInfo
/*    */   extends ZoanInfo {
/* 22 */   public static final ManeManeMemoryZoanInfo INSTANCE = new ManeManeMemoryZoanInfo();
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/* 28 */     boolean isSlim = false;
/* 29 */     ManeManeMemoryAbility ability = (ManeManeMemoryAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility((Ability)ManeManeMemoryAbility.INSTANCE);
/* 30 */     if (ability != null && ability.isTransformationActive((LivingEntity)entity)) {
/*    */       
/* 32 */       UUID uuid = ability.getMemory().getUUID();
/* 33 */       PlayerEntity target = entity.world.getPlayerByUuid(uuid);
/* 34 */       if (target != null && target instanceof AbstractClientPlayerEntity) {
/* 35 */         isSlim = ((AbstractClientPlayerEntity)target).getSkinType().equals("slim");
/*    */       } else {
/* 37 */         isSlim = DefaultPlayerSkin.getSkinType(uuid).equals("slim");
/*    */       } 
/* 39 */     }  return (IRenderFactory)new ManeManeMemoryRenderer.Factory(this, isSlim);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public ZoanMorphModel getModel() {
/* 50 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean shouldRenderFirstPersonHand() {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 63 */     return ModAbilities.MANE_MANE_NO_MI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 69 */     return "mane_memory";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 75 */     return (Ability)ManeManeMemoryAbility.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\ManeManeMemoryZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */