/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.hana.BloomParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class MilFleurAbility extends ContinuousAbility {
/* 17 */   public static final MilFleurAbility INSTANCE = new MilFleurAbility();
/* 18 */   public static final BloomParticleEffect PARTICLES = new BloomParticleEffect();
/*    */ 
/*    */   
/*    */   public MilFleurAbility() {
/* 22 */     super("Mil Fleur", AbilityHelper.getDevilFruitCategory());
/* 23 */     setDescription("While active all the other abilities of this fruit will transform, either allowing for area of effects or bigger and better versions of themselves.");
/* 24 */     setThreshold(30.0D);
/*    */     
/* 26 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 27 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 32 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 34 */     DosFleurClutchAbility clutchAbility = (DosFleurClutchAbility)abilityProps.getEquippedAbility(DosFleurClutchAbility.INSTANCE);
/* 35 */     if (clutchAbility != null) {
/*    */       
/* 37 */       clutchAbility.enableMilFleurMode();
/* 38 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, clutchAbility), (LivingEntity)player);
/*    */     } 
/*    */     
/* 41 */     SeisFleurSlapAbility slapAbility = (SeisFleurSlapAbility)abilityProps.getEquippedAbility(SeisFleurSlapAbility.INSTANCE);
/* 42 */     if (slapAbility != null) {
/*    */       
/* 44 */       slapAbility.enableMilFleurMode();
/* 45 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, slapAbility), (LivingEntity)player);
/*    */     } 
/*    */     
/* 48 */     SeisFleurTwistAbility twistAbility = (SeisFleurTwistAbility)abilityProps.getEquippedAbility(SeisFleurTwistAbility.INSTANCE);
/* 49 */     if (twistAbility != null) {
/*    */       
/* 51 */       twistAbility.enableMilFleurMode();
/* 52 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, twistAbility), (LivingEntity)player);
/*    */     } 
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 60 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 62 */     DosFleurClutchAbility clutchAbility = (DosFleurClutchAbility)abilityProps.getEquippedAbility(DosFleurClutchAbility.INSTANCE);
/* 63 */     if (clutchAbility != null) {
/*    */       
/* 65 */       clutchAbility.disableMilFleurMode();
/* 66 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, clutchAbility), (LivingEntity)player);
/*    */     } 
/*    */     
/* 69 */     SeisFleurSlapAbility slapAbility = (SeisFleurSlapAbility)abilityProps.getEquippedAbility(SeisFleurSlapAbility.INSTANCE);
/* 70 */     if (slapAbility != null) {
/*    */       
/* 72 */       slapAbility.disableMilFleurMode();
/* 73 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, slapAbility), (LivingEntity)player);
/*    */     } 
/*    */     
/* 76 */     SeisFleurTwistAbility twistAbility = (SeisFleurTwistAbility)abilityProps.getEquippedAbility(SeisFleurTwistAbility.INSTANCE);
/* 77 */     if (twistAbility != null) {
/*    */       
/* 79 */       twistAbility.disableMilFleurMode();
/* 80 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, twistAbility), (LivingEntity)player);
/*    */     } 
/*    */     
/* 83 */     int cooldown = (int)Math.round(this.continueTime / 20.0D);
/* 84 */     setMaxCooldown(cooldown);
/* 85 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void spawnBlossomEffect(LivingEntity owner) {
/* 90 */     PARTICLES.spawn(owner.world, owner.getPosX(), owner.getPosY(), owner.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 91 */     owner.world.playSound(null, owner.getPosition(), ModSounds.HANA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\MilFleurAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */