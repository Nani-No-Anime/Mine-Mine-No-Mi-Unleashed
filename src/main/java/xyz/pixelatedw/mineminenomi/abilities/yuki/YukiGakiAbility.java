/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yuki.YukiGakiParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class YukiGakiAbility extends Ability {
/* 16 */   public static final YukiGakiAbility INSTANCE = new YukiGakiAbility();
/*    */   
/* 18 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
/* 19 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new YukiGakiParticleEffect();
/*    */ 
/*    */   
/*    */   public YukiGakiAbility() {
/* 23 */     super("Yuki Gaki", AbilityHelper.getDevilFruitCategory());
/* 24 */     setMaxCooldown(5.0D);
/* 25 */     setDescription("Creates a wall made of hardened snow to protect the user");
/*    */     
/* 27 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 32 */     int posZ = 0;
/* 33 */     int posX = 0;
/*    */     
/* 35 */     Direction facingDirection = Direction.getFacingDirections((Entity)player)[0];
/* 36 */     if (facingDirection == Direction.NORTH) { posZ = -4; }
/* 37 */     else if (facingDirection == Direction.SOUTH) { posZ = 4; }
/* 38 */     else if (facingDirection == Direction.EAST) { posX = 4; }
/* 39 */     else if (facingDirection == Direction.WEST) { posX = -4; }
/*    */     
/* 41 */     AbilityHelper.createFilledCube(player.world, player.getPosX() + posX, player.getPosY(), player.getPosZ() + posZ, (posX == 0) ? 3 : 1, 4, (posZ == 0) ? 3 : 1, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
/* 42 */     PARTICLES.spawn(player.world, player.getPosX() + posX, player.getPosY(), player.getPosZ() + posZ, 0.0D, 0.0D, 0.0D);
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\YukiGakiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */