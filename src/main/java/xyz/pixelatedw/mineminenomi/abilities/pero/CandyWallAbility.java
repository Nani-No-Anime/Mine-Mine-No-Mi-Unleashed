/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
/*    */ import java.lang.invoke.SerializedLambda;

import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class CandyWallAbility extends Ability {
/* 12 */   public static final CandyWallAbility INSTANCE = new CandyWallAbility();
/*    */   
/* 14 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE })).setBypassGriefRule();
/*    */   
/*    */   public CandyWallAbility() {
/* 17 */     super("Candy Wall", AbilityHelper.getDevilFruitCategory());
/* 18 */     setDescription("The user creates a wall made out of candy.");
/* 19 */     setMaxCooldown(5.0D);
/*    */     
/* 21 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     Direction dir = Direction.getFacingDirections((Entity)player)[0];
/* 26 */     if (dir == Direction.NORTH)
/* 27 */       AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() - 4.0D, 3, 4, 1, ModBlocks.CANDY, GRIEF_RULE); 
/* 28 */     if (dir == Direction.SOUTH)
/* 29 */       AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() + 4.0D, 3, 4, 1, ModBlocks.CANDY, GRIEF_RULE); 
/* 30 */     if (dir == Direction.EAST)
/* 31 */       AbilityHelper.createFilledCube(player.world, player.getPosX() + 4.0D, player.getPosY(), player.getPosZ(), 1, 4, 3, ModBlocks.CANDY, GRIEF_RULE); 
/* 32 */     if (dir == Direction.WEST)
/* 33 */       AbilityHelper.createFilledCube(player.world, player.getPosX() - 4.0D, player.getPosY(), player.getPosZ(), 1, 4, 3, ModBlocks.CANDY, GRIEF_RULE); 
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyWallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */