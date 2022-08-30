/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class BarrierAbility extends ContinuousAbility {
/* 20 */   public static final BarrierAbility INSTANCE = new BarrierAbility();
/*    */   
/* 22 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE })).setBypassGriefRule();
/* 23 */   private List<BlockPos> posList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public BarrierAbility() {
/* 27 */     super("Barrier", AbilityHelper.getDevilFruitCategory());
/* 28 */     setThreshold(30.0D);
/* 29 */     setDescription("The user creates an impenetrable wall in front of themselves, which shields them from attacks");
/*    */     
/* 31 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 32 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 37 */     Direction dir = Direction.getFacingDirections((Entity)player)[0];
/*    */     
/* 39 */     if (this.posList.isEmpty()) {
/*    */       
/* 41 */       if (dir == Direction.NORTH)
/* 42 */         this.posList.addAll(AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() - 4.0D, 3, 4, 1, ModBlocks.BARRIER, GRIEF_RULE)); 
/* 43 */       if (dir == Direction.SOUTH)
/* 44 */         this.posList.addAll(AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() + 4.0D, 3, 4, 1, ModBlocks.BARRIER, GRIEF_RULE)); 
/* 45 */       if (dir == Direction.EAST)
/* 46 */         this.posList.addAll(AbilityHelper.createFilledCube(player.world, player.getPosX() + 4.0D, player.getPosY(), player.getPosZ(), 1, 4, 3, ModBlocks.BARRIER, GRIEF_RULE)); 
/* 47 */       if (dir == Direction.WEST) {
/* 48 */         this.posList.addAll(AbilityHelper.createFilledCube(player.world, player.getPosX() - 4.0D, player.getPosY(), player.getPosZ(), 1, 4, 3, ModBlocks.BARRIER, GRIEF_RULE));
/*    */       }
/*    */     } 
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStopContinuityEvent(PlayerEntity player) {
/* 56 */     for (BlockPos pos : this.posList) {
/*    */       
/* 58 */       Block currentBlock = player.world.getBlockState(pos).getBlock();
/* 59 */       if (currentBlock == ModBlocks.BARRIER)
/* 60 */         player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
/*    */     } 
/* 62 */     this.posList = new ArrayList<>();
/*    */     
/* 64 */     int cooldown = 1 + (int)Math.round(this.continueTime / 50.0D);
/* 65 */     setMaxCooldown(cooldown);
/*    */     
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */