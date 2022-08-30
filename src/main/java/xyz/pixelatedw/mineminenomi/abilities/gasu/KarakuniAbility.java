/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class KarakuniAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 24 */   public static final KarakuniAbility INSTANCE = new KarakuniAbility();
/*    */ 
/*    */   
/*    */   public KarakuniAbility() {
/* 28 */     super("Karakuni", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("Removes the oxygen around the user, suffocating and weakening everyone in the vicinity");
/* 30 */     setMaxCooldown(30.0D);
/* 31 */     setThreshold(5.0D);
/*    */     
/* 33 */     this.duringContinuityEvent = this::duringContinuity;
/*    */   }
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int i) {
/* 37 */     World world = player.world;
/* 38 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/* 39 */     int radius = ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player) ? 12 : 9;
/*    */ 
/*    */     
/* 42 */     if (i % 2 == 0) {
/*    */       
/* 44 */       List<LivingEntity> entities = WyHelper.getEntitiesNear(player.getPosition(), world, radius);
/* 45 */       entities.remove(player);
/* 46 */       List<BlockPos> blocks = WyHelper.getNearbyBlocks((LivingEntity)player, radius);
/*    */       
/* 48 */       for (LivingEntity entity : entities) {
/*    */         
/* 50 */         if (entity.canBreatheUnderwater()) {
/*    */           continue;
/*    */         }
/* 53 */         entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 500, 2, false, false));
/* 54 */         entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 500, 1, false, false));
/* 55 */         entity.setAir(entity.getAir() - 50);
/* 56 */         int airLeft = entity.getAir();
/* 57 */         if (airLeft <= 0)
/*    */         {
/* 59 */           if (entity.getHealth() > 8.0F) {
/* 60 */             entity.attackEntityFrom(DamageSource.DROWN, 8.0F);
/*    */           } else {
/*    */             
/* 63 */             EffectInstance effect = new EffectInstance(ModEffects.UNCONSCIOUS, 200, 1);
/* 64 */             entity.addPotionEffect(effect);
/* 65 */             if (player instanceof ServerPlayerEntity) {
/* 66 */               ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(entity.getEntityId(), effect));
/*    */             }
/*    */           } 
/*    */         }
/* 70 */         if (entity.isBurning()) {
/* 71 */           entity.extinguish();
/*    */         }
/*    */       } 
/* 74 */       if (player.isBurning()) {
/* 75 */         player.extinguish();
/*    */       }
/* 77 */       for (BlockPos blockPos : blocks) {
/*    */         
/* 79 */         BlockPos blockUp = new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
/* 80 */         if (world.getBlockState(blockPos).getBlock() == Blocks.FIRE && world.getBlockState(blockUp).getBlock() == Blocks.AIR) {
/*    */           
/* 82 */           world.playEvent(player, 1009, blockPos, 0);
/* 83 */           world.removeBlock(blockPos, false);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\KarakuniAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */