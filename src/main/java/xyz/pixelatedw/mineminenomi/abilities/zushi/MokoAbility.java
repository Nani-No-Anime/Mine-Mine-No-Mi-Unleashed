/*    */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.zushi.MokoProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class MokoAbility extends RepeaterAbility {
/* 13 */   public static final MokoAbility INSTANCE = new MokoAbility();
/*    */ 
/*    */   
/*    */   public MokoAbility() {
/* 17 */     super("Moko", AbilityHelper.getDevilFruitCategory());
/* 18 */     setDescription("Sends a wave of gravitational waves towards the enemies, slowing them down");
/* 19 */     setMaxCooldown(14.0D);
/* 20 */     setMaxRepeaterCount(4, 6);
/*    */     
/* 22 */     this.onStartContinuityEvent = this::onStartContinuity;
/* 23 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuity(PlayerEntity player) {
/* 28 */     if (!ItemsHelper.isSword(player.getHeldItemMainhand())) {
/*    */       
/* 30 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
/* 31 */       return false;
/*    */     } 
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 38 */     MokoProjectile proj = new MokoProjectile(player.world, (LivingEntity)player);
/* 39 */     player.world.addEntity((Entity)proj);
/* 40 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 2.0F);
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\MokoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */