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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.FreshFireProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class FreshFireAbility extends RepeaterAbility {
/* 17 */   public static final Ability INSTANCE = (Ability)new FreshFireAbility();
/*    */   
/*    */   private static final int COLA_REQUIRED = 2;
/*    */ 
/*    */   
/*    */   public FreshFireAbility() {
/* 23 */     super("Fresh Fire", AbilityHelper.getRacialCategory());
/* 24 */     setMaxCooldown(5.0D);
/* 25 */     setMaxRepeaterCount(10, 3);
/* 26 */     setDescription("The user heats up and breathes fire like a flamethrower at the opponent\nConsumes §2" + (2 * this.maxRepeaterCount) + "§r cola");
/*    */     
/* 28 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 29 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 34 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 36 */     if (props.getCola() - 2 < 0) {
/*    */       
/* 38 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
/* 39 */       return false;
/*    */     } 
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 47 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 49 */     FreshFireProjectile proj = new FreshFireProjectile(player.world, (LivingEntity)player);
/* 50 */     player.world.addEntity((Entity)proj);
/* 51 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 10.0F);
/*    */     
/* 53 */     props.alterCola(-2);
/* 54 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\FreshFireAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */