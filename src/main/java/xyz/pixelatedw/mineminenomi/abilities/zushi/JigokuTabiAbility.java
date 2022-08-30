/*    */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class JigokuTabiAbility extends ContinuousAbility {
/* 21 */   public static final JigokuTabiAbility INSTANCE = new JigokuTabiAbility();
/*    */ 
/*    */   
/*    */   public JigokuTabiAbility() {
/* 25 */     super("Jigoku Tabi", AbilityHelper.getDevilFruitCategory());
/* 26 */     setDescription("Causes a powerful downward force of gravity, sending the enemies down in a crater");
/* 27 */     setMaxCooldown(20.0D);
/* 28 */     setThreshold(12.0D);
/*    */     
/* 30 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 31 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int activeTimer) {
/* 36 */     int range = 24;
/* 37 */     GraviZoneAbility.gravityRing((LivingEntity)player, range, 0, true);
/*    */     
/* 39 */     if (++activeTimer % 20 == 0) {
/*    */       
/* 41 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 42 */       targets.remove(player);
/*    */       
/* 44 */       for (LivingEntity entity : targets) {
/*    */         
/* 46 */         entity.setMotion(0.0D, (entity.getMotion()).y - 4.0D, 0.0D);
/* 47 */         entity.velocityChanged = true;
/* 48 */         EffectInstance instance = new EffectInstance(Effects.SLOWNESS, 25, 5, false, false);
/* 49 */         entity.addPotionEffect(instance);
/* 50 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(entity.getEntityId(), instance));
/* 51 */         entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
/* 52 */         GraviZoneAbility.gravityRing(entity, 3, 2, false);
/* 53 */         for (int x = -2; x < 2; x++) {
/* 54 */           for (int z = -2; z < 2; z++) {
/*    */             
/* 56 */             int posX = (int)entity.getPosX() + x;
/* 57 */             int posY = (int)entity.getPosY() - 1;
/* 58 */             int posZ = (int)entity.getPosZ() + z;
/* 59 */             AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 67 */     int cooldown = (int)Math.round(this.continueTime / 20.0D) + 2;
/* 68 */     setMaxCooldown(cooldown);
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\JigokuTabiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */