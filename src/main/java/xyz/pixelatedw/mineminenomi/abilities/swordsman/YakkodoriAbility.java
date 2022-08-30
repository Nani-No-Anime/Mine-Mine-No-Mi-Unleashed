/*    */ package xyz.pixelatedw.mineminenomi.abilities.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class YakkodoriAbility extends Ability {
/* 16 */   public static final Ability INSTANCE = new YakkodoriAbility();
/*    */ 
/*    */   
/*    */   public YakkodoriAbility() {
/* 20 */     super("Yakkodori", AbilityHelper.getStyleCategory());
/* 21 */     setMaxCooldown(6.0D);
/* 22 */     setDescription("Launches a crescent moon-shaped slash, which destroys everything in its path");
/*    */     
/* 24 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 29 */     if (!AbilityHelper.canUseSwordsmanAbilities((LivingEntity)player)) {
/*    */       
/* 31 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
/* 32 */       return false;
/*    */     } 
/*    */     
/* 35 */     ItemStack stack = player.getHeldItemMainhand();
/* 36 */     stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */     
/* 38 */     YakkodoriProjectile proj = new YakkodoriProjectile(player.world, (LivingEntity)player);
/* 39 */     player.world.addEntity((Entity)proj);
/* 40 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/* 41 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\swordsman\YakkodoriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */