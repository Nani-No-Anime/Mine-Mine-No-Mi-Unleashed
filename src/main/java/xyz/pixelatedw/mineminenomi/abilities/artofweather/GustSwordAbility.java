/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.GustSwordProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */
/*    */ public class GustSwordAbility extends RepeaterAbility {
/* 14 */   public static final GustSwordAbility INSTANCE = new GustSwordAbility();
/*    */ 
/*    */   
/*    */   public GustSwordAbility() {
/* 18 */     super("Gust Sword", AbilityHelper.getStyleCategory());
/* 19 */     setDescription("Fires a concentrated wind blast forward");
/* 20 */     setMaxCooldown(10.0D);
/* 21 */     setMaxRepeaterCount(6, 3);
/*    */     
/* 23 */     this.onStartContinuityEvent = this::onStartContinuity;
/* 24 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuity(PlayerEntity player) {
/* 29 */     if (!ItemsHelper.isClimaTact(player.getHeldItemMainhand())) {
/*    */       
/* 31 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT, new Object[0]));
/* 32 */       return false;
/*    */     } 
/*    */     
/* 35 */     ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
/* 36 */     if (climaTact.getLevel() < 3) {
/*    */       
/* 38 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT, new Object[0]));
/* 39 */       return false;
/*    */     } 
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 47 */     GustSwordProjectile proj = new GustSwordProjectile(player.world, (LivingEntity)player);
/* 48 */     player.world.addEntity((Entity)proj);
/* 49 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 3.0F);
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\GustSwordAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */