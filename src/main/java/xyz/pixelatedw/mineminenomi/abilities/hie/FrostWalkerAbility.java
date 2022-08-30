/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OceanPlantsProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ public class FrostWalkerAbility extends PassiveAbility {
/* 15 */   public static final FrostWalkerAbility INSTANCE = new FrostWalkerAbility();
/* 16 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)OceanPlantsProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { Blocks.WATER });
/*    */ 
/*    */   
/*    */   public FrostWalkerAbility() {
/* 20 */     super("Frost Walker", AbilityHelper.getDevilFruitCategory());
/* 21 */     setDescription("Turns all water the user walks on into ice\n\n§2SHIFT-USE§r: Disables the ability");
/*    */     
/* 23 */     hideInGUI(false);
/* 24 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringPassiveEvent(PlayerEntity player) {
/* 29 */     if (player.isSneaking() || 
/* 30 */       AbilityHelper.isNearbyKairoseki(player) || player
/* 31 */       .getRidingEntity() != null || player
/* 32 */       .getHealth() < player.getMaxHealth() / 5.0F || 
/* 33 */       !WyHelper.isBlockNearby((LivingEntity)player, 3, new Block[] { Blocks.WATER })) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 38 */     for (int x = -1; x <= 1; x++) {
/*    */       
/* 40 */       for (int y = -2; y <= 2; y++) {
/*    */         
/* 42 */         for (int z = -1; z <= 1; z++) {
/*    */           
/* 44 */           BlockPos pos = new BlockPos(player.getPosX() + x, player.getPosY() + y, player.getPosZ() + z);
/* 45 */           if (AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), Blocks.FROSTED_ICE, 3, GRIEF_RULE))
/* 46 */             player.world.getPendingBlockTicks().scheduleTick(pos, Blocks.FROSTED_ICE, MathHelper.nextInt(player.getRNG(), 20, 60)); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\FrostWalkerAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */