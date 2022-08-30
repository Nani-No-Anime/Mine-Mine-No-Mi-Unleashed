/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;
/*    */ 
/*    */ public class DarkMatterProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/* 21 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
/* 22 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new DarkMatterParticleEffect();
/*    */ 
/*    */   
/*    */   public DarkMatterProjectile(World world) {
/* 26 */     super(YamiProjectiles.DARK_MATTER, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DarkMatterProjectile(EntityType type, World world) {
/* 31 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DarkMatterProjectile(World world, double x, double y, double z) {
/* 36 */     super(YamiProjectiles.DARK_MATTER, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public DarkMatterProjectile(World world, LivingEntity player) {
/* 41 */     super(YamiProjectiles.DARK_MATTER, world, player);
/*    */     
/* 43 */     setDamage(15.0F);
/* 44 */     setMaxLife(20);
/*    */     
/* 46 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 51 */     AbilityHelper.createFilledSphere(this.world, hit.getX(), hit.getY(), hit.getZ(), 3, ModBlocks.DARKNESS, GRIEF_RULE);
/* 52 */     PARTICLES.spawn(this.world, hit.getX(), hit.getY(), hit.getZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerData() {
/* 58 */     super.registerData();
/* 59 */     this.dataManager.register(SIZE, Float.valueOf(0.0F));
/*    */   }
/*    */ 
/*    */   
/*    */   public void increaseSize() {
/* 64 */     this.dataManager.set(SIZE, Float.valueOf(Math.min(((Float)this.dataManager.get(SIZE)).floatValue() + 0.1F, 17.5F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSize(float size) {
/* 70 */     this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 17.5F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getSize() {
/* 76 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yami\DarkMatterProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */