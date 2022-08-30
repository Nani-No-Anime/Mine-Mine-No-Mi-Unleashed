/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterChargingParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BlackRoadProjectile extends AbilityProjectileEntity {
/* 18 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new DarkMatterChargingParticleEffect();
/* 19 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public BlackRoadProjectile(World world) {
/* 23 */     super(YamiProjectiles.BLACK_ROAD, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlackRoadProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlackRoadProjectile(World world, double x, double y, double z) {
/* 33 */     super(YamiProjectiles.BLACK_ROAD, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlackRoadProjectile(World world, LivingEntity player) {
/* 38 */     super(YamiProjectiles.BLACK_ROAD, world, player);
/*    */     
/* 40 */     setMaxLife(1);
/* 41 */     setDamage(10.0F);
/* 42 */     setPassThroughEntities();
/* 43 */     setPassThroughBlocks();
/*    */     
/* 45 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 50 */     BlockPos pos = null;
/* 51 */     int j = 1;
/*    */     
/* 53 */     while (pos == null) {
/*    */       
/* 55 */       BlockState state = this.world.getBlockState(getPosition().down(j));
/*    */       
/* 57 */       if (state.isSolid()) {
/*    */         
/* 59 */         pos = getPosition().down(j);
/*    */         
/*    */         break;
/*    */       } 
/* 63 */       if (j > 2) {
/*    */         break;
/*    */       }
/* 66 */       j++;
/*    */     } 
/*    */     
/* 69 */     if (pos == null) {
/*    */       return;
/*    */     }
/* 72 */     int size = 2 + 4 * (getMaxLife() - getLife()) / getMaxLife();
/*    */     
/* 74 */     AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), size, ModBlocks.DARKNESS, GRIEF_RULE);
/*    */     
/* 76 */     PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yami\BlackRoadProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */