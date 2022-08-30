/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class CandleHouseAbility extends Ability {
/* 14 */   public static final Ability INSTANCE = new CandleHouseAbility();
/* 15 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public CandleHouseAbility() {
/* 19 */     super("Candle House", AbilityHelper.getDevilFruitCategory());
/* 20 */     setMaxCooldown(50.0D);
/* 21 */     setDescription("Creates a big house-like structure made of wax, to protect the user");
/*    */     
/* 23 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 28 */     int thiccness = 1;
/* 29 */     Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
/* 30 */     if (ability != null && ability.isContinuous()) {
/* 31 */       thiccness = 3;
/*    */     }
/* 33 */     for (int y = -3; y <= 3; y++) {
/*    */       int i;
/* 35 */       for (i = 0; i < thiccness; i++) {
/* 36 */         for (int z = -5; z < 5; z++)
/* 37 */           AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() + 6.0D - i, player.getPosY() + y, player.getPosZ() - z, ModBlocks.WAX, GRIEF_RULE); 
/* 38 */       }  for (i = 0; i < thiccness; i++) {
/* 39 */         for (int z = -5; z < 5; z++)
/* 40 */           AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() - 5.0D - i, player.getPosY() + y, player.getPosZ() - z, ModBlocks.WAX, GRIEF_RULE); 
/* 41 */       }  for (i = -5; i < 5; i++) {
/* 42 */         for (int z = 0; z < thiccness; z++)
/* 43 */           AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() - i, player.getPosY() + y, player.getPosZ() + 6.0D - z, ModBlocks.WAX, GRIEF_RULE); 
/* 44 */       }  for (i = -5; i < 5; i++) {
/* 45 */         for (int z = 0; z < thiccness; z++)
/* 46 */           AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() - i, player.getPosY() + y, player.getPosZ() - 5.0D - z, ModBlocks.WAX, GRIEF_RULE); 
/*    */       } 
/* 48 */     }  for (int x = -5; x < 5; x++) {
/* 49 */       for (int z = -5; z < 5; z++)
/* 50 */         AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() - x, player.getPosY() + 4.0D, player.getPosZ() - z, ModBlocks.WAX, GRIEF_RULE); 
/*    */     } 
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\CandleHouseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */