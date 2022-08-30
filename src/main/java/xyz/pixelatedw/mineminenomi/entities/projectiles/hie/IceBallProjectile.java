/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class IceBallProjectile extends AbilityProjectileEntity {
/* 24 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public IceBallProjectile(World world) {
/* 28 */     super(HieProjectiles.ICE_BALL, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBallProjectile(EntityType type, World world) {
/* 33 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBallProjectile(World world, double x, double y, double z) {
/* 38 */     super(HieProjectiles.ICE_BALL, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBallProjectile(World world, LivingEntity player) {
/* 43 */     super(HieProjectiles.ICE_BALL, world, player);
/*    */     
/* 45 */     setDamage(25.0F);
/* 46 */     setMaxLife(32);
/* 47 */     setPhysical(false);
/* 48 */     setAffectedByImbuing();
/*    */     
/* 50 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 51 */     this.onTickEvent = this::onTickEvent;
/*    */     
/* 53 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.FROZEN, 200, 0), new EffectInstance(Effects.SLOWNESS, 100, 0), new EffectInstance(Effects.MINING_FATIGUE, 100, 0) });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 62 */     AbilityHelper.createEmptySphere(this.world, hit.getX(), hit.getY(), hit.getZ(), 6, Blocks.BLUE_ICE, GRIEF_RULE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 67 */     if (!this.world.isRemote)
/*    */     {
/* 69 */       for (int i = 0; i < 10; i++) {
/*    */         
/* 71 */         double offsetX = WyHelper.randomDouble() / 1.5D;
/* 72 */         double offsetY = WyHelper.randomDouble() / 1.5D;
/* 73 */         double offsetZ = WyHelper.randomDouble() / 1.5D;
/*    */         
/* 75 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
/* 76 */         data.setLife(3);
/* 77 */         data.setSize(1.5F);
/* 78 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\IceBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */