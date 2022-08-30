/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class BakuretsuKazanAbility extends ChargeableAbility {
/* 17 */   public static final Ability INSTANCE = (Ability)new BakuretsuKazanAbility();
/* 18 */   public static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public BakuretsuKazanAbility() {
/* 22 */     super("Bakuretsu Kazan", AbilityHelper.getDevilFruitCategory());
/* 23 */     setMaxCooldown(25.0D);
/* 24 */     setMaxChargeTime(5.0D);
/* 25 */     setCancelable();
/* 26 */     setDescription("By spreading magma to the surroundings, the user turns everything into lava");
/*    */     
/* 28 */     this.onEndChargingEvent = this::endChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean endChargingEvent(PlayerEntity player) {
/* 33 */     float time = getChargeTime() / getMaxChargeTime();
/* 34 */     float multiplier = 1.0F - time;
/* 35 */     int range = (int)(16.0F * multiplier);
/* 36 */     if (range < 5) {
/* 37 */       return false;
/*    */     }
/* 39 */     AbilityHelper.createFilledSphere(player.world, (int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ(), range, Blocks.LAVA, GRIEF_RULE);
/* 40 */     player.world.playSound(null, player.getPosition(), ModSounds.MAGU_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 42 */     setCooldown((int)(getMaxCooldown() / 20.0D * multiplier));
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\BakuretsuKazanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */