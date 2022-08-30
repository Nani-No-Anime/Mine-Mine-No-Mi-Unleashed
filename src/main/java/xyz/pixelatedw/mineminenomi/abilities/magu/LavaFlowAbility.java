/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class LavaFlowAbility extends ContinuousAbility {
/* 18 */   public static final Ability INSTANCE = (Ability)new LavaFlowAbility();
/* 19 */   public static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
/*    */   
/* 21 */   private int originY = -1;
/*    */ 
/*    */   
/*    */   public LavaFlowAbility() {
/* 25 */     super("Lava Flow", AbilityHelper.getDevilFruitCategory());
/* 26 */     setMaxCooldown(15.0D);
/* 27 */     setThreshold(5.0D);
/* 28 */     setDescription("The user covers their legs into lava creating a path while walking trough it");
/*    */     
/* 30 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 31 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 36 */     this.originY = player.getPosition().getY() - 5;
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 42 */     if (this.originY < 0) {
/*    */       return;
/*    */     }
/* 45 */     BlockPos pos = player.getPosition().down();
/* 46 */     BlockState state = player.world.getBlockState(pos);
/*    */     
/* 48 */     boolean isBlockBelowOrigin = (pos.getY() < this.originY);
/* 49 */     boolean areEyesInLava = player.areEyesInFluid(FluidTags.LAVA);
/* 50 */     boolean canPlaceBlocks = (GRIEF_RULE.check(player.world, pos, state) && !areEyesInLava);
/*    */     
/* 52 */     if (canPlaceBlocks) {
/* 53 */       AbilityHelper.createFilledSphere(player.world, (int)player.getPosX(), (int)player.getPosY() - 2, (int)player.getPosZ(), 3, Blocks.LAVA, GRIEF_RULE);
/* 54 */     } else if (areEyesInLava && !isBlockBelowOrigin) {
/* 55 */       AbilityHelper.createFilledSphere(player.world, (int)player.getPosX(), (int)player.getPosY() + 1, (int)player.getPosZ(), 3, Blocks.LAVA, GRIEF_RULE);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\LavaFlowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */