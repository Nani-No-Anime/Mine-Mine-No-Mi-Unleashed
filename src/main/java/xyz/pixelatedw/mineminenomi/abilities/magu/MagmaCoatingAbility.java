/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class MagmaCoatingAbility
/*    */   extends PunchAbility implements IPunchOverlayAbility, IParallelContinuousAbility {
/* 17 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(160, 0, 0));
/*    */   
/* 19 */   public static final Ability INSTANCE = (Ability)new MagmaCoatingAbility();
/*    */ 
/*    */   
/*    */   public MagmaCoatingAbility() {
/* 23 */     super("Magma Coating", AbilityHelper.getDevilFruitCategory());
/* 24 */     setThreshold(30.0D);
/* 25 */     setMaxCooldown(10.0D);
/* 26 */     setDescription("The user coats their arm with magma");
/* 27 */     setStoppingAfterHit(false);
/*    */     
/* 29 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 34 */     return 25.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getPunchOverlay(LivingEntity entity) {
/* 40 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\MagmaCoatingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */