/*    */ package xyz.pixelatedw.mineminenomi.abilities.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.swordsman.OTatsumakiParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.swordsman.BodyRotateWideArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class OTatsumakiAbility extends Ability implements IAnimatedAbility {
/* 26 */   public static final Ability INSTANCE = new OTatsumakiAbility();
/* 27 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new OTatsumakiParticleEffect();
/*    */ 
/*    */   
/*    */   public OTatsumakiAbility() {
/* 31 */     super("O Tatsumaki", AbilityHelper.getStyleCategory());
/* 32 */     setMaxCooldown(12.0D);
/* 33 */     setDescription("By spinning, the user creates a small tornado, which slashes and weakens nearby opponents");
/*    */     
/* 35 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 40 */     if (!AbilityHelper.canUseSwordsmanAbilities((LivingEntity)player)) {
/*    */       
/* 42 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
/* 43 */       return false;
/*    */     } 
/*    */     
/* 46 */     ItemStack stack = player.getHeldItemMainhand();
/* 47 */     stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */     
/* 49 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 2.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class );
/* 50 */     list.remove(player);
/*    */     
/* 52 */     list.forEach(entity -> {
/*    */           entity.attackEntityFrom((DamageSource)(new ModEntityDamageSource("player", (Entity)player)).markDamageAsSlash(), 15.0F);
/*    */           
/*    */           WyHelper.spawnParticles(ParticleTypes.SWEEP_ATTACK, (ServerWorld)player.world, entity.getPosX(), entity.getPosY() + entity.getEyeHeight(), entity.getPosZ());
/*    */         });
/*    */     
/* 58 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 60 */     getAnimation().start();
/*    */     
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TimeAnimation getAnimation() {
/* 68 */     return (TimeAnimation)BodyRotateWideArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 74 */     return (isOnCooldown() && getCooldown() > WyHelper.percentage(90.0D, getMaxCooldown()));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\swordsman\OTatsumakiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */