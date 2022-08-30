/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.ShockwaveParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class ShockwaveProjectile extends AbilityProjectileEntity {
/* 17 */   private static final ShockwaveParticleEffect PARTICLES = new ShockwaveParticleEffect();
/* 18 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });
/*    */   
/*    */   private boolean canBreakBlocks = false;
/*    */   public int power;
/*    */   
/*    */   public ShockwaveProjectile(World world) {
/* 24 */     super(ExtraProjectiles.SHOCKWAVE, world);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     this.power = 8; } public ShockwaveProjectile(EntityType type, World world) { super(type, world); this.power = 8; } public ShockwaveProjectile(World world, double x, double y, double z) { super(ExtraProjectiles.SHOCKWAVE, world, x, y, z); this.power = 8; } public ShockwaveProjectile(World world, LivingEntity entity) {
/*    */     this(world, entity, false);
/*    */   } public ShockwaveProjectile(World world, LivingEntity entity, boolean canBreakBlocks) {
/* 45 */     super(ExtraProjectiles.SHOCKWAVE, world, entity);
/*    */     this.power = 8;
/* 47 */     setDamage(this.power);
/* 48 */     setCollisionSize(2.0D);
/* 49 */     setPhysical(false);
/* 50 */     setMaxLife(10);
/* 51 */     setPassThroughEntities();
/* 52 */     this.onTickEvent = this::onTickEvent;
/* 53 */     this.canBreakBlocks = canBreakBlocks;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 58 */     BlockPos pos = null;
/* 59 */     int j = 1;
/*    */     
/* 61 */     while (pos == null) {
/*    */       
/* 63 */       BlockState state = this.world.getBlockState(getPosition().down(j));
/*    */       
/* 65 */       if (state.isSolid()) {
/*    */         
/* 67 */         pos = getPosition().down(j);
/*    */         
/*    */         break;
/*    */       } 
/* 71 */       if (j > 5) {
/*    */         break;
/*    */       }
/* 74 */       j++;
/*    */     } 
/*    */     
/* 77 */     if (pos == null) {
/*    */       return;
/*    */     }
/* 80 */     if (this.canBreakBlocks) {
/* 81 */       AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), this.power / 8, Blocks.AIR, GRIEF_RULE);
/*    */     }
/* 83 */     PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\ShockwaveProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */