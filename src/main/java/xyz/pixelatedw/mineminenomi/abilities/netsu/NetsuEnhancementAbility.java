/*    */ package xyz.pixelatedw.mineminenomi.abilities.netsu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.netsu.NetsuEnhancementParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IChangeDamageSourceAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ 
/*    */ public class NetsuEnhancementAbility extends ContinuousAbility implements IParallelContinuousAbility, IChangeDamageSourceAbility, IBodyOverlayAbility {
/* 25 */   public static final NetsuEnhancementAbility INSTANCE = new NetsuEnhancementAbility();
/*    */   
/* 27 */   private static final NetsuEnhancementParticleEffect PARTICLES = new NetsuEnhancementParticleEffect();
/* 28 */   private static final AbilityAttributeModifier MULTIPLIER = (new AbilityAttributeModifier(UUID.fromString("efa08cbd-57e5-478f-b15c-6295eb1b375e"), (Ability)INSTANCE, "Netsu Enhancement Modifier", 0.25D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/* 29 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setRenderType(AbilityOverlay.RenderType.ENERGY).setColor(WyHelper.hexToRGB("#962A2AAA"));
/*    */ 
/*    */   
/*    */   public NetsuEnhancementAbility() {
/* 33 */     super("Netsu Enhancement", AbilityHelper.getDevilFruitCategory());
/* 34 */     setThreshold(40.0D);
/* 35 */     setDescription("Increases the user's attacks and body functions through heat");
/* 36 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 37 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/* 38 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 43 */     player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier((AttributeModifier)MULTIPLIER);
/* 44 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)MULTIPLIER);
/* 45 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)MULTIPLIER);
/*    */     
/* 47 */     int cooldown = (int)Math.round(this.continueTime / 40.0D) + 15;
/* 48 */     setMaxCooldown(cooldown);
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int timer) {
/* 54 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 59 */     player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier((AttributeModifier)MULTIPLIER);
/* 60 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)MULTIPLIER);
/* 61 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)MULTIPLIER);
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getBodyOverlay() {
/* 68 */     return OVERLAY;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float damageToEntityWithSource(PlayerEntity player, LivingEntity target) {
/* 74 */     float strength = Math.min((float)player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue(), 60.0F);
/* 75 */     float keptDamage = 0.375F * (1.0F - strength / 160.0F);
/*    */     
/* 77 */     if (target.hurtResistantTime > 0 && target.hurtResistantTime <= 5) {
/* 78 */       target.hurtTime = target.hurtResistantTime = 0;
/*    */     }
/* 80 */     SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, target, 5);
/* 81 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 82 */       target.setFire(5);
/*    */     }
/* 84 */     return 8.0F + Math.min(strength * keptDamage, 15.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageSource getSourceToUse(PlayerEntity player) {
/* 89 */     return (DamageSource)(new ModDamageSource("onFire", false, true, false)).causeEntityDamageFromSource((Entity)player);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cancelsOriginalDamage() {
/* 94 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSourceChangeEnabled() {
/* 99 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\netsu\NetsuEnhancementAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */