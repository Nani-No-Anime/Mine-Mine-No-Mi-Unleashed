/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.DesertSpadaProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class DesertSpadaAbility extends Ability {
/* 14 */   public static final Ability INSTANCE = new DesertSpadaAbility();
/*    */ 
/*    */   
/*    */   public DesertSpadaAbility() {
/* 18 */     super("Desert Spada", AbilityHelper.getDevilFruitCategory());
/* 19 */     setMaxCooldown(8.0D);
/* 20 */     setDescription("The user forms their hand into a blade and stabs it into the ground, creating a sand blade that destroys anything on its path");
/*    */     
/* 22 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 27 */     if (!player.onGround) {
/*    */       
/* 29 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
/* 30 */       return false;
/*    */     } 
/*    */     
/* 33 */     player.world.playMovingSound(null, (Entity)player, ModSounds.DESERT_SPADA_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
/* 34 */     boolean fruitBoosted = SunaHelper.isFruitBoosted(player);
/* 35 */     DesertSpadaProjectile proj = new DesertSpadaProjectile(player.world, (LivingEntity)player);
/* 36 */     proj.setMaxLife(fruitBoosted ? 30 : 20);
/* 37 */     proj.setDamage(proj.getDamage() * (fruitBoosted ? 1.5F : 1.0F));
/* 38 */     player.world.addEntity((Entity)proj);
/* 39 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\DesertSpadaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */