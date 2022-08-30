/*    */ package xyz.pixelatedw.mineminenomi.abilities.netsu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class NekkaiJigokuAbility
/*    */   extends ContinuousAbility implements IParallelContinuousAbility {
/* 20 */   public static final NekkaiJigokuAbility INSTANCE = new NekkaiJigokuAbility();
/*    */ 
/*    */   
/*    */   public NekkaiJigokuAbility() {
/* 24 */     super("Nekkai Jigoku", AbilityHelper.getDevilFruitCategory());
/* 25 */     setMaxCooldown(10.0D);
/* 26 */     setThreshold(30.0D);
/* 27 */     setDescription("Boils water around the user, damaging entities inside it");
/*    */     
/* 29 */     this.duringContinuityEvent = this::duringContinuity;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int i) {
/* 34 */     int radius = 30;
/*    */     
/* 36 */     List<LivingEntity> mobs = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius);
/* 37 */     List<BlockPos> blocks = WyHelper.getNearbyBlocks((LivingEntity)player, radius);
/*    */     
/* 39 */     for (LivingEntity entity : mobs) {
/*    */       
/* 41 */       if (entity.isInWater())
/*    */       {
/* 43 */         entity.attackEntityFrom(DamageSource.ON_FIRE, 2.0F);
/*    */       }
/*    */     } 
/*    */     
/* 47 */     for (BlockPos blockPos : blocks) {
/*    */       
/* 49 */       BlockPos blockUp = new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
/* 50 */       if (player.world.getBlockState(blockPos).getBlock() == Blocks.WATER && player.world.getBlockState(blockUp).getBlock() == Blocks.AIR && i % 5 == 0)
/*    */       {
/* 52 */         WyHelper.spawnParticles(ParticleTypes.BUBBLE, (ServerWorld)player.world, blockPos.getX() + WyHelper.randomDouble() / 2.0D, blockPos.getY() + 0.8D, blockPos.getZ() + WyHelper.randomDouble() / 2.0D);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\netsu\NekkaiJigokuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */