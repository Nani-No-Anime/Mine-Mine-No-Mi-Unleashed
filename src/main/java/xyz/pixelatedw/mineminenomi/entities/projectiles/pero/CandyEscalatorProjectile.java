/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class CandyEscalatorProjectile extends AbilityProjectileEntity {
/* 13 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public CandyEscalatorProjectile(World world) {
/* 17 */     super(PeroProjectiles.CANDY_ESCALATOR, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyEscalatorProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyEscalatorProjectile(World world, double x, double y, double z) {
/* 27 */     super(PeroProjectiles.CANDY_ESCALATOR, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyEscalatorProjectile(World world, LivingEntity player) {
/* 32 */     super(PeroProjectiles.CANDY_ESCALATOR, world, player);
/*    */     
/* 34 */     setMaxLife(30);
/* 35 */     setPhysical(false);
/* 36 */     setPassThroughEntities();
/* 37 */     setPassThroughBlocks();
/* 38 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 43 */     AbilityHelper.createFilledCube(this.world, getPosX(), getPosY() - 2.0D, getPosZ(), 1, 1, 1, ModBlocks.CANDY, GRIEF_RULE);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pero\CandyEscalatorProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */