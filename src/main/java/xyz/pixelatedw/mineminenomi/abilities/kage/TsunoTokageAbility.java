/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.TsunoTokagePillarEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class TsunoTokageAbility extends Ability {
/* 12 */   public static final TsunoTokageAbility INSTANCE = new TsunoTokageAbility();
/*    */ 
/*    */   
/*    */   public TsunoTokageAbility() {
/* 16 */     super("Tsuno Tokage", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("The user creates a lizard-like shadow under his opponent, which pierces them from below");
/* 18 */     setMaxCooldown(13.0D);
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 32.0D);
/*    */     
/* 27 */     double i = (mop.getHitVec()).x;
/* 28 */     double j = (mop.getHitVec()).y;
/* 29 */     double k = (mop.getHitVec()).z;
/*    */     
/* 31 */     TsunoTokagePillarEntity pillar = new TsunoTokagePillarEntity(player.world, (LivingEntity)player);
/* 32 */     pillar.rotationPitch = 90.0F;
/* 33 */     pillar.setPosition(i, j - 3.0D, k);
/* 34 */     pillar.setMotion(0.0D, 0.7D, 0.0D);
/* 35 */     player.world.addEntity((Entity)pillar);
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\TsunoTokageAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */