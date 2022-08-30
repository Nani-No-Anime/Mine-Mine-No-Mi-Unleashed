/*    */ package xyz.pixelatedw.mineminenomi.abilities.kuku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3i;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class GastronomorphAbility extends ChargeableAbility {
/* 23 */   public static final GastronomorphAbility INSTANCE = new GastronomorphAbility();
/*    */   
/* 25 */   private static final BakuMunchParticleEffect PARTICLES = new BakuMunchParticleEffect();
/* 26 */   public static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
/*    */   
/* 28 */   private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*    */ 
/*    */   
/*    */   public GastronomorphAbility() {
/* 32 */     super("Gastronomorph", AbilityHelper.getDevilFruitCategory());
/* 33 */     setDescription("Turns the surroundings into cake sponge blocks");
/* 34 */     setMaxChargeTime(3.0D);
/* 35 */     setMaxCooldown(15.0D);
/*    */     
/* 37 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 38 */     this.duringChargingEvent = this::duringChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int i) {
/* 43 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
/*    */     
/* 45 */     HashSet<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*    */     
/* 47 */     int finished = blockList.size() / 100;
/* 48 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/*    */       
/* 50 */       BlockPos blockPos = iterator.next();
/* 51 */       if (finished-- < 0) {
/*    */         break;
/*    */       }
/* 54 */       BlockPos pos = new BlockPos((Vec3i)blockPos);
/* 55 */       AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), ModBlocks.SPONGE_CAKE, 3, DefaultProtectionRules.CORE_FOLIAGE_ORE);
/*    */       
/* 57 */       iterator.remove();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 63 */     this.blockPlacingHelper.clearList();
/* 64 */     int range = 32;
/*    */     
/* 66 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 68 */     for (int i = -range; i < range; i++) {
/* 69 */       for (int j = -9; j < 9; j++) {
/* 70 */         for (int k = -range; k < range; k++) {
/*    */           
/* 72 */           double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || i > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/* 73 */           double posY = player.getPosY() + j;
/*    */           
/* 75 */           double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || k > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*    */           
/* 77 */           BlockPos pos = new BlockPos(posX, posY, posZ);
/*    */           
/* 79 */           if (!player.world.getBlockState(pos).isAir() && player.world.getBlockState(pos.up()).isAir())
/*    */           {
/*    */             
/* 82 */             this.blockPlacingHelper.addBlockPos(pos, i * i + j * j + k * k); } 
/*    */         } 
/*    */       } 
/* 85 */     }  return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kuku\GastronomorphAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */