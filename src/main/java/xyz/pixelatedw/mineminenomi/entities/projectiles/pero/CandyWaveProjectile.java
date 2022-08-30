/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.pero.CandyWaveParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class CandyWaveProjectile extends AbilityProjectileEntity {
/* 20 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new CandyWaveParticleEffect();
/* 21 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public CandyWaveProjectile(World world) {
/* 25 */     super(PeroProjectiles.CANDY_WAVE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyWaveProjectile(EntityType type, World world) {
/* 30 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyWaveProjectile(World world, double x, double y, double z) {
/* 35 */     super(PeroProjectiles.CANDY_WAVE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyWaveProjectile(World world, LivingEntity player) {
/* 40 */     super(PeroProjectiles.CANDY_WAVE, world, player);
/*    */     
/* 42 */     setDamage(24.0F);
/* 43 */     setMaxLife(12);
/* 44 */     setPhysical(false);
/* 45 */     setAffectedByImbuing();
/* 46 */     setPassThroughEntities();
/* 47 */     setPassThroughBlocks();
/* 48 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.CANDY_STUCK, 100, 0, false, false, false) });
/*    */ 
/*    */ 
/*    */     
/* 52 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 57 */     BlockPos pos = null;
/* 58 */     int j = 1;
/*    */     
/* 60 */     while (pos == null) {
/*    */       
/* 62 */       BlockState state = this.world.getBlockState(getPosition().down(j));
/*    */       
/* 64 */       if (state.isSolid()) {
/*    */         
/* 66 */         pos = getPosition().down(j);
/*    */         
/*    */         break;
/*    */       } 
/* 70 */       if (j > 3) {
/*    */         break;
/*    */       }
/* 73 */       j++;
/*    */     } 
/*    */     
/* 76 */     if (pos == null) {
/*    */       return;
/*    */     }
/* 79 */     int size = 2 + 4 * (getMaxLife() - getLife()) / getMaxLife();
/*    */     
/* 81 */     AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), size, ModBlocks.CANDY, GRIEF_RULE);
/*    */     
/* 83 */     PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pero\CandyWaveProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */