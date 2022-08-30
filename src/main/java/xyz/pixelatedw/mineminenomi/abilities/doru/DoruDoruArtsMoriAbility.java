/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.ChampFightProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.DoruDoruArtsMoriProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class DoruDoruArtsMoriAbility extends RepeaterAbility {
/* 14 */   public static final DoruDoruArtsMoriAbility INSTANCE = new DoruDoruArtsMoriAbility();
/*    */ 
/*    */   
/*    */   public DoruDoruArtsMoriAbility() {
/* 18 */     super("Doru Doru Arts: Mori", AbilityHelper.getDevilFruitCategory());
/* 19 */     setMaxCooldown(4.0D);
/* 20 */     setDescription("The user fires a harpoon made of wax at the opponent");
/*    */     
/* 22 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 23 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 28 */     Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
/* 29 */     if (ability != null && ability.isContinuous()) {
/*    */       
/* 31 */       setMaxRepeaterCount(25, 2);
/* 32 */       setMaxCooldown(12.0D);
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 38 */       setMaxRepeaterCount(0, 0);
/* 39 */       setMaxCooldown(4.0D);
/* 40 */       DoruDoruArtsMoriProjectile proj = new DoruDoruArtsMoriProjectile(player.world, (LivingEntity)player);
/* 41 */       player.world.addEntity((Entity)proj);
/* 42 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     } 
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 49 */     Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
/* 50 */     if (ability != null && ability.isContinuous()) {
/*    */       
/* 52 */       int projectileSpace = 2;
/*    */       
/* 54 */       ChampFightProjectile proj = new ChampFightProjectile(player.world, (LivingEntity)player);
/* 55 */       proj.setLocationAndAngles(player
/* 56 */           .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
/* 57 */           .getPosY() + 2.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
/* 58 */           .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
/*    */       
/* 60 */       proj.setChangeHurtTime(true);
/* 61 */       player.world.addEntity((Entity)proj);
/* 62 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     }
/*    */     else {
/*    */       
/* 66 */       DoruDoruArtsMoriProjectile proj = new DoruDoruArtsMoriProjectile(player.world, (LivingEntity)player);
/* 67 */       player.world.addEntity((Entity)proj);
/* 68 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     } 
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void enableChampionMode() {
/* 75 */     setDisplayName("Champ Fight");
/* 76 */     setCustomTexture("champ_fight");
/*    */   }
/*    */ 
/*    */   
/*    */   public void disableChampionMode() {
/* 81 */     setDisplayName("");
/* 82 */     setCustomTexture("");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruDoruArtsMoriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */