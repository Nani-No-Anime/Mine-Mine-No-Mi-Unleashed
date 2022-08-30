/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HidarumaProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.MeraProjectiles;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class HidarumaAbility extends Ability {
/* 20 */   public static final Ability INSTANCE = new HidarumaAbility();
/*    */   
/*    */   private static final int MIN_FIREFLIES = 6;
/*    */   
/*    */   private static final int MAX_FIREFLIES = 9;
/*    */   
/*    */   public HidarumaAbility() {
/* 27 */     super("Hidaruma", AbilityHelper.getDevilFruitCategory());
/* 28 */     setMaxCooldown(10.0D);
/* 29 */     setDescription("Creates small green fireballs that set the target on fire");
/*    */     
/* 31 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 36 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 100.0D);
/*    */     
/* 38 */     double i = (mop.getHitVec()).x;
/* 39 */     double j = (mop.getHitVec()).y;
/* 40 */     double k = (mop.getHitVec()).z;
/* 41 */     BlockPos originPos = new BlockPos(i, j, k);
/* 42 */     Optional<LivingEntity> target = null;
/*    */     
/* 44 */     List<LivingEntity> list = WyHelper.getEntitiesNear(originPos, player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 45 */     list.remove(player);
/* 46 */     list.sort(MobsHelper.ENTITY_THREAT);
/*    */     
/* 48 */     if (list.size() > 0) {
/*    */       
/* 50 */       target = list.stream().findAny();
/* 51 */       originPos = ((LivingEntity)target.get()).getPosition();
/*    */     } 
/*    */     
/* 54 */     int random = (int)WyHelper.randomWithRange(6, 9);
/*    */     
/* 56 */     for (int n = 0; n < random; n++) {
/*    */       
/* 58 */       BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(player.world, MeraProjectiles.HIDARUMA, originPos, 10, 7);
/* 59 */       if (spawnPos != null) {
/*    */         
/* 61 */         HidarumaProjectile proj = new HidarumaProjectile(player.world, (LivingEntity)player);
/* 62 */         proj.setTarget(target);
/* 63 */         proj.setPosition(spawnPos.getX(), (spawnPos.getY() + 1), spawnPos.getZ());
/* 64 */         player.world.addEntity((Entity)proj);
/*    */       } 
/*    */     } 
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HidarumaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */