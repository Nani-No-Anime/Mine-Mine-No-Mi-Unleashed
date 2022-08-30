/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.state.IProperty;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.SkyBlockBlock;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class MilkyDialProjectile extends AbilityProjectileEntity {
/*    */   public MilkyDialProjectile(World world) {
/* 18 */     super(ExtraProjectiles.MILKY_DIAL_PROJECTILE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MilkyDialProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MilkyDialProjectile(World world, double x, double y, double z) {
/* 28 */     super(ExtraProjectiles.MILKY_DIAL_PROJECTILE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public MilkyDialProjectile(World world, LivingEntity player) {
/* 33 */     super(ExtraProjectiles.MILKY_DIAL_PROJECTILE, world, player);
/*    */     
/* 35 */     setMaxLife(40);
/* 36 */     setPhysical(false);
/* 37 */     setPassThroughBlocks();
/*    */     
/* 39 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 44 */     for (BlockPos blockpos : BlockPos.getAllInBoxMutable(getPosition().add(-1, -1, -1), getPosition().add(1, 0, 1))) {
/*    */       
/* 46 */       BlockState state = (BlockState)ModBlocks.SKY_BLOCK.getDefaultState().with((IProperty)SkyBlockBlock.TYPE, Integer.valueOf(this.rand.nextInt(4)));
/* 47 */       AbilityHelper.placeBlockIfAllowed(this.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), state, 3, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\MilkyDialProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */