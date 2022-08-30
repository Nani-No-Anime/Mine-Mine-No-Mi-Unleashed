/*    */ package xyz.pixelatedw.mineminenomi.abilities.horu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class GanmenSeichoHormoneAbility extends PunchAbility {
/* 17 */   public static final GanmenSeichoHormoneAbility INSTANCE = new GanmenSeichoHormoneAbility();
/*    */ 
/*    */   
/*    */   public GanmenSeichoHormoneAbility() {
/* 21 */     super("Ganmen Seicho Hormone", AbilityHelper.getDevilFruitCategory());
/* 22 */     setMaxCooldown(5.0D);
/* 23 */     setDescription("By injecting special growth hormones into a target, their head expands to enormous proportions\n\n" + TextFormatting.DARK_GREEN + "SHIFT-USE" + TextFormatting.RESET + ": The user injects themselves, boosting Death Wink");
/*    */     
/* 25 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 26 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 31 */     if (player.isSneaking()) {
/*    */       
/* 33 */       if (player.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE)) {
/*    */         
/* 35 */         player.removePotionEffect(ModEffects.GANMEN_SEICHO_HORMONE);
/* 36 */         for (ServerPlayerEntity serverPlayer : ((ServerWorld)player.world).getPlayers())
/*    */         {
/* 38 */           serverPlayer.connection.sendPacket((IPacket)new SRemoveEntityEffectPacket(player.getEntityId(), ModEffects.GANMEN_SEICHO_HORMONE));
/*    */         }
/*    */       }
/*    */       else {
/*    */         
/* 43 */         EffectInstance instance = new EffectInstance(ModEffects.GANMEN_SEICHO_HORMONE, 300, 0);
/* 44 */         player.addPotionEffect(instance);
/* 45 */         for (ServerPlayerEntity serverPlayer : ((ServerWorld)player.world).getPlayers())
/*    */         {
/* 47 */           serverPlayer.connection.sendPacket((IPacket)new SPlayEntityEffectPacket(player.getEntityId(), instance));
/*    */         }
/*    */       } 
/* 50 */       endContinuity(player);
/* 51 */       return false;
/*    */     } 
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 59 */     if (target.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE)) {
/*    */       
/* 61 */       target.removePotionEffect(ModEffects.GANMEN_SEICHO_HORMONE);
/* 62 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SRemoveEntityEffectPacket(target.getEntityId(), ModEffects.GANMEN_SEICHO_HORMONE));
/*    */     }
/*    */     else {
/*    */       
/* 66 */       EffectInstance instance = new EffectInstance(ModEffects.GANMEN_SEICHO_HORMONE, 300, 0);
/* 67 */       target.addPotionEffect(instance);
/* 68 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*    */     } 
/*    */     
/* 71 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\horu\GanmenSeichoHormoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */