/*    */ package xyz.pixelatedw.mineminenomi.abilities.ori;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ori.AwaseBaoriProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class AwaseBaoriAbility extends ContinuousAbility {
/* 16 */   public static final AwaseBaoriAbility INSTANCE = new AwaseBaoriAbility();
/*    */   
/*    */   private AwaseBaoriProjectile proj;
/* 19 */   private List<BlockPos> placedBlocks = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public AwaseBaoriAbility() {
/* 23 */     super("Awase Baori", AbilityHelper.getDevilFruitCategory());
/* 24 */     setDescription("Launches a short range projectile that creates a small cage around the hit target.");
/* 25 */     setMaxCooldown(10.0D);
/* 26 */     setThreshold(5.0D);
/*    */     
/* 28 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 29 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 30 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 35 */     this.proj = new AwaseBaoriProjectile(player.world, (LivingEntity)player);
/* 36 */     player.world.addEntity((Entity)this.proj);
/* 37 */     this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int timer) {
/* 44 */     if (this.placedBlocks.isEmpty()) {
/*    */       
/* 46 */       if (this.proj != null && this.proj.getBlocks() != null) {
/*    */         
/* 48 */         this.placedBlocks = this.proj.getBlocks();
/*    */         
/*    */         return;
/*    */       } 
/* 52 */       if (this.proj == null || !this.proj.isAlive()) {
/*    */         
/* 54 */         endContinuity(player);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void onStopContinuityEvent(PlayerEntity player) {
/* 62 */     for (BlockPos pos : this.placedBlocks) {
/*    */       
/* 64 */       if (player.world.getBlockState(pos).getBlock() == ModBlocks.ORI_BARS)
/* 65 */         player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
/*    */     } 
/* 67 */     this.placedBlocks.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ori\AwaseBaoriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */