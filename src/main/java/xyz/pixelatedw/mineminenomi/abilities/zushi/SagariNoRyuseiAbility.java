/*    */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.zushi.SagariNoRyuseiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*    */ 
/*    */ public class SagariNoRyuseiAbility extends RepeaterAbility {
/* 16 */   public static final SagariNoRyuseiAbility INSTANCE = new SagariNoRyuseiAbility();
/*    */ 
/*    */   
/*    */   public SagariNoRyuseiAbility() {
/* 20 */     super("Sagari no Ryusei", AbilityHelper.getDevilFruitCategory());
/* 21 */     setMaxCooldown(45.0D);
/* 22 */     setMaxRepeaterCount(4, 20);
/* 23 */     setDescription("Using gravity, the user pulls a nearby meteorites down on their enemies");
/*    */     
/* 25 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 26 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 30 */     setMaxRepeaterCount((int)WyHelper.randomWithRange(1, 2), 20);
/* 31 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 32 */     WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/*    */     
/* 34 */     for (int i = 2; i < 16; i += 2) {
/* 35 */       GraviZoneAbility.gravityRing((LivingEntity)player, 4, i, false);
/*    */     }
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 43 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
/*    */     
/* 45 */     double x = (mop.getHitVec()).x;
/* 46 */     double y = (mop.getHitVec()).y;
/* 47 */     double z = (mop.getHitVec()).z;
/*    */     
/* 49 */     SagariNoRyuseiProjectile proj = new SagariNoRyuseiProjectile(player.world, (LivingEntity)player);
/*    */ 
/*    */     
/* 52 */     float size = (getRepeaterCount() == getMaxRepeaterCount()) ? (float)WyHelper.randomWithRange(24, 30) : (float)(WyHelper.randomWithRange(4, 8) * getRepeaterCount());
/*    */     
/* 54 */     proj.setSize(size);
/* 55 */     proj.setLocationAndAngles(x, y + 90.0D, z, 0.0F, 0.0F);
/* 56 */     proj.setMotion(0.0D, -1.85D, 0.0D);
/* 57 */     player.world.addEntity((Entity)proj);
/*    */     
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\SagariNoRyuseiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */