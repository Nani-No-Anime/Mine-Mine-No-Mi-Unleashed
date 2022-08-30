/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class BarrierBallAbility extends ContinuousAbility {
/* 20 */   public static final BarrierBallAbility INSTANCE = new BarrierBallAbility();
/*    */   
/* 22 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE });
/* 23 */   private List<BlockPos> posList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public BarrierBallAbility() {
/* 27 */     super("Barrier Ball", AbilityHelper.getDevilFruitCategory());
/* 28 */     setMaxCooldown(10.0D);
/* 29 */     setThreshold(15.0D);
/* 30 */     setDescription("The user creates a spherical barrier where they're pointing\n\n§2SHIFT-USE§r: Creates the barrier around the user");
/*    */     
/* 32 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 33 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 38 */     if (this.posList.isEmpty()) {
/*    */       
/* 40 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
/* 41 */       World world = player.world;
/* 42 */       if (player.isSneaking()) {
/*    */         
/* 44 */         this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ(), 5, ModBlocks.BARRIER, GRIEF_RULE));
/* 45 */         this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ(), 6, ModBlocks.BARRIER, GRIEF_RULE));
/*    */       }
/* 47 */       else if (mop != null) {
/*    */         
/* 49 */         this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)(mop.getHitVec()).x, (int)(mop.getHitVec()).y, (int)(mop.getHitVec()).z, 5, ModBlocks.BARRIER, GRIEF_RULE));
/* 50 */         this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)(mop.getHitVec()).x, (int)(mop.getHitVec()).y, (int)(mop.getHitVec()).z, 6, ModBlocks.BARRIER, GRIEF_RULE));
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onStopContinuityEvent(PlayerEntity player) {
/* 59 */     for (BlockPos pos : this.posList) {
/*    */       
/* 61 */       if (player.world.getBlockState(pos).getBlock() == ModBlocks.BARRIER)
/* 62 */         player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
/*    */     } 
/* 64 */     this.posList.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */