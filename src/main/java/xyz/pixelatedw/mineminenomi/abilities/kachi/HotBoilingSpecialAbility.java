/*    */ package xyz.pixelatedw.mineminenomi.abilities.kachi;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ 
/*    */ public class HotBoilingSpecialAbility extends PunchAbility implements IPunchOverlayAbility {
/* 15 */   public static final HotBoilingSpecialAbility INSTANCE = new HotBoilingSpecialAbility();
/*    */   
/* 17 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFFFFFAA");
/*    */ 
/*    */   
/*    */   public HotBoilingSpecialAbility() {
/* 21 */     super("Hot Boiling Special", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("Hardens and heats up the fist of the user");
/* 23 */     setMaxCooldown(5.0D);
/*    */     
/* 25 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 26 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 31 */     SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, target, 20);
/* 32 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 33 */       target.setFire(20);
/*    */     }
/*    */   }
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 38 */     return 20.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getPunchOverlay(LivingEntity entity) {
/* 44 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kachi\HotBoilingSpecialAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */