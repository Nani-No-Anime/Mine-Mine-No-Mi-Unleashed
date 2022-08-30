/*    */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class KenbunshokuHakiAuraAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility {
/* 21 */   public static final KenbunshokuHakiAuraAbility INSTANCE = new KenbunshokuHakiAuraAbility();
/*    */ 
/*    */   
/*    */   public KenbunshokuHakiAuraAbility() {
/* 25 */     super("Kenbunshoku Haki: Aura", AbilityHelper.getHakiCategory());
/* 26 */     setDescription("Uses Observation Haki to see the auras of all nearby creatures, differentiated by colors");
/*    */     
/* 28 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 29 */     this.duringContinuityEvent = this::duringContinuity;
/* 30 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 36 */     WyNetwork.sendTo(new SSyncHakiDataPacket(player.getEntityId(), HakiDataCapability.get((LivingEntity)player)), player);
/* 37 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), EntityStatsCapability.get((LivingEntity)player)), player);
/* 38 */     player.world.playSound(null, player.getPosition(), ModSounds.KENBUNSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 45 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 0);
/* 46 */     if (isOnMaxOveruse) {
/* 47 */       endContinuity(player);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 52 */     int cooldown = (int)WyHelper.clamp(Math.round(this.continueTime / 30.0D), 3L, 30L);
/* 53 */     setMaxCooldown(cooldown);
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HakiType getType() {
/* 60 */     return HakiType.KENBUNSHOKU;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\KenbunshokuHakiAuraAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */