/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BarrierbilityStairsProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class BarrierbilityStairsAbility extends ContinuousAbility {
/* 16 */   public static final BarrierbilityStairsAbility INSTANCE = new BarrierbilityStairsAbility();
/*    */   
/* 18 */   private List<BlockPos> posList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public BarrierbilityStairsAbility() {
/* 22 */     super("Barrierbility Stairs", AbilityHelper.getDevilFruitCategory());
/* 23 */     setDescription("By shaping the Barrier, the user can create stairs");
/*    */     
/* 25 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 26 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 31 */     if (this.posList.isEmpty()) {
/*    */       
/* 33 */       BarrierbilityStairsProjectile proj = new BarrierbilityStairsProjectile(player.world, (LivingEntity)player);
/* 34 */       player.world.addEntity((Entity)proj);
/* 35 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     } 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onStopContinuityEvent(PlayerEntity player) {
/* 43 */     for (BlockPos pos : this.posList) {
/*    */       
/* 45 */       if (player.world.getBlockState(pos).getBlock() == ModBlocks.BARRIER)
/* 46 */         player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
/*    */     } 
/* 48 */     this.posList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public void fillBlocksList(List<BlockPos> entityList) {
/* 53 */     this.posList.addAll(entityList);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierbilityStairsAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */