/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.CoupDeVentProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class CoupDeVentAbility extends Ability {
/* 16 */   public static final Ability INSTANCE = new CoupDeVentAbility();
/*    */   
/*    */   private static final int COLA_REQUIRED = 30;
/*    */ 
/*    */   
/*    */   public CoupDeVentAbility() {
/* 22 */     super("Coup de Vent", AbilityHelper.getRacialCategory());
/* 23 */     setMaxCooldown(12.0D);
/* 24 */     setDescription("Launches a powerful blast of compressed air that blows the opponent away\nConsumes §230§r cola");
/*    */     
/* 26 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 31 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 33 */     if (props.getCola() - 30 < 0) {
/*    */       
/* 35 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
/* 36 */       return false;
/*    */     } 
/*    */     
/* 39 */     CoupDeVentProjectile proj = new CoupDeVentProjectile(player.world, (LivingEntity)player);
/* 40 */     player.world.addEntity((Entity)proj);
/* 41 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/*    */     
/* 43 */     props.alterCola(-30);
/* 44 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\CoupDeVentAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */