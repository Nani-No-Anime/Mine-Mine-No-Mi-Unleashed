/*    */ package xyz.pixelatedw.mineminenomi.abilities.sabi;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.sabi.RustTouchParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class RustBreakAbility extends Ability {
/* 27 */   public static final RustBreakAbility INSTANCE = new RustBreakAbility();
/*    */   
/* 29 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new RustTouchParticleEffect();
/*    */ 
/*    */   
/*    */   public RustBreakAbility() {
/* 33 */     super("Rust Break", AbilityHelper.getDevilFruitCategory());
/* 34 */     setMaxCooldown(20.0D);
/* 35 */     setDescription("Rusts iron blocks");
/*    */     
/* 37 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 42 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
/*    */     
/* 44 */     if (player.getDistanceSq(mop.getHitVec()) > 50.0D) {
/* 45 */       return false;
/*    */     }
/* 47 */     BlockPos tracePos = new BlockPos(mop.getHitVec());
/* 48 */     List<BlockPos> rustyBlocks = WyHelper.getNearbyBlocks(tracePos, (IWorld)player.world, 1, p -> player.world.getBlockState(p).getBlock().isIn(ModTags.Blocks.RUSTY), (List)ImmutableList.of(Blocks.AIR));
/*    */     
/* 50 */     for (BlockPos pos : rustyBlocks) {
/*    */       
/* 52 */       for (int i = 0; i < 55; i++) {
/*    */         
/* 54 */         double offsetX = 0.5D + WyHelper.randomDouble() / 2.0D;
/* 55 */         double offsetY = 0.5D + WyHelper.randomDouble() / 2.0D;
/* 56 */         double offsetZ = 0.5D + WyHelper.randomDouble() / 2.0D;
/*    */         
/* 58 */         double motionX = this.random.nextGaussian() * 0.02D;
/* 59 */         double motionY = this.random.nextGaussian() * 0.02D;
/* 60 */         double motionZ = this.random.nextGaussian() * 0.02D;
/*    */         
/* 62 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.RUST);
/* 63 */         data.setMotion(motionX, motionY, motionZ);
/* 64 */         data.setLife(30);
/* 65 */         data.setSize(4.0F);
/* 66 */         WyHelper.spawnParticles(data, (ServerWorld)player.world, pos.getX() + offsetX, pos.getY() + offsetY, pos.getZ() + offsetZ);
/*    */         
/* 68 */         ((ServerWorld)player.world).spawnParticle((IParticleData)ParticleTypes.POOF, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 1, motionX, motionY, motionZ, 0.05000000074505806D);
/*    */       } 
/*    */       
/* 71 */       boolean isIngot = this.random.nextBoolean();
/* 72 */       for (int j = 0; j < this.random.nextInt(3); j++) {
/*    */         
/* 74 */         ItemStack stack = isIngot ? new ItemStack((IItemProvider)Items.IRON_INGOT) : new ItemStack((IItemProvider)Items.IRON_NUGGET);
/* 75 */         ItemEntity item = new ItemEntity(player.world, pos.getX(), pos.getY(), pos.getZ(), stack);
/* 76 */         player.world.addEntity((Entity)item);
/*    */       } 
/*    */       
/* 79 */       player.world.removeBlock(pos, false);
/*    */     } 
/*    */     
/* 82 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sabi\RustBreakAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */