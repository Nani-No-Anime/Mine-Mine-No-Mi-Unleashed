/*    */ package xyz.pixelatedw.mineminenomi.abilities.baku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class BakuFactoryAbility
/*    */   extends ContinuousAbility {
/* 13 */   public static final BakuFactoryAbility INSTANCE = new BakuFactoryAbility();
/*    */   
/*    */   private BlockState previousBlock;
/*    */ 
/*    */   
/*    */   public BakuFactoryAbility() {
/* 19 */     super("Baku Factory", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Allows the user to craft items and blocks without the need for a Crafting Table.");
/*    */     
/* 22 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 23 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 24 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 29 */     BlockPos pos = player.getPosition();
/* 30 */     this.previousBlock = player.world.getBlockState(pos);
/* 31 */     player.world.setBlockState(pos, Blocks.CRAFTING_TABLE.getDefaultState());
/* 32 */     player.openContainer(player.world.getBlockState(pos).getContainer(player.world, pos));
/* 33 */     player.world.setBlockState(pos, this.previousBlock);
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int time) {
/* 39 */     if (!(player.openContainer instanceof net.minecraft.inventory.container.WorkbenchContainer)) {
/* 40 */       endContinuity(player);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\baku\BakuFactoryAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */