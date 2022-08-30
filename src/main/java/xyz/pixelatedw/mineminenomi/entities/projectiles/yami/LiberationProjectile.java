/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class LiberationProjectile extends AbilityProjectileEntity {
/* 15 */   private final Block[] randomBlocks = new Block[] { Blocks.STONE, Blocks.SAND, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.GRAVEL, Blocks.CLAY, Blocks.COBBLESTONE };
/*    */ 
/*    */   
/*    */   public LiberationProjectile(World world) {
/* 19 */     super(YamiProjectiles.LIBERATION, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public LiberationProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public LiberationProjectile(World world, double x, double y, double z) {
/* 29 */     super(YamiProjectiles.LIBERATION, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public LiberationProjectile(World world, LivingEntity player) {
/* 34 */     super(YamiProjectiles.LIBERATION, world, player);
/*    */     
/* 36 */     setDamage(5.0F);
/*    */     
/* 38 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 43 */     Block block = this.randomBlocks[this.rand.nextInt(this.randomBlocks.length)];
/* 44 */     AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), block, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yami\LiberationProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */