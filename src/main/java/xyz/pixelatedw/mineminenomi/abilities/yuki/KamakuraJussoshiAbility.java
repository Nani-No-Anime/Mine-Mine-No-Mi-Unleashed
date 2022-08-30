/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yuki.KamakuraParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class KamakuraJussoshiAbility extends Ability {
/* 19 */   public static final KamakuraJussoshiAbility INSTANCE = new KamakuraJussoshiAbility();
/*    */   
/* 21 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { Blocks.SNOW });
/* 22 */   private static final KamakuraParticleEffect PARTICLES = new KamakuraParticleEffect();
/*    */ 
/*    */   
/*    */   public KamakuraJussoshiAbility() {
/* 26 */     super("Kamakura Jussoshi", AbilityHelper.getDevilFruitCategory());
/* 27 */     setDescription("Like 'Kamakura', but creates a multi-layered snow barrier\n\n§2SHIFT-USE§r: Creates the igloo around the user");
/* 28 */     setMaxCooldown(12.0D);
/*    */     
/* 30 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 35 */     World world = player.world;
/* 36 */     Vec3d vec3d = player.isSneaking() ? player.getPositionVector() : WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D).getHitVec();
/*    */     
/* 38 */     PARTICLES.life = 200;
/* 39 */     PARTICLES.size = 6;
/* 40 */     PARTICLES.spawn(world, vec3d.x, vec3d.y, vec3d.z, 0.0D, 0.0D, 0.0D);
/* 41 */     AbilityHelper.createEmptySphere(world, (int)vec3d.x, (int)vec3d.y, (int)vec3d.z, 4, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
/* 42 */     AbilityHelper.createEmptySphere(world, (int)vec3d.x, (int)vec3d.y, (int)vec3d.z, 6, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
/* 43 */     AbilityHelper.createEmptySphere(world, (int)vec3d.x, (int)vec3d.y, (int)vec3d.z, 8, ModBlocks.HARDENED_SNOW, GRIEF_RULE);
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\KamakuraJussoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */