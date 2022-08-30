/*    */ package xyz.pixelatedw.mineminenomi.abilities.ori;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class GreatCageAbility
/*    */   extends ContinuousAbility implements IParallelContinuousAbility {
/* 21 */   public static final GreatCageAbility INSTANCE = new GreatCageAbility();
/*    */   
/* 23 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).setBypassGriefRule();
/* 24 */   protected List<BlockPos> blockList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public GreatCageAbility() {
/* 28 */     super("Great Cage", AbilityHelper.getDevilFruitCategory());
/* 29 */     setMaxCooldown(10.0D);
/* 30 */     setDescription("Creates a big cage trapping the user and all nearby entities in it.");
/*    */     
/* 32 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 33 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 38 */     if (this.blockList.isEmpty()) {
/* 39 */       this.blockList.addAll(AbilityHelper.createEmptyCube(player.world, ((int)player.getPosX() - 1), (int)player.getPosY(), ((int)player.getPosZ() - 1), 20, 20, 20, ModBlocks.ORI_BARS, GRIEF_RULE));
/*    */     }
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onStopContinuityEvent(PlayerEntity player) {
/* 46 */     for (BlockPos pos : this.blockList) {
/*    */       
/* 48 */       Block currentBlock = player.world.getBlockState(pos).getBlock();
/* 49 */       if (currentBlock == ModBlocks.ORI_BARS)
/* 50 */         player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
/*    */     } 
/* 52 */     this.blockList.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ori\GreatCageAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */