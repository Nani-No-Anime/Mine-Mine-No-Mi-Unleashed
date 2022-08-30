/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;
/*     */ 
/*     */ public class UrsusShockProjectile
/*     */   extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*  26 */   public float multiplier = 0.0F;
/*     */   
/*     */   public UrsusShockProjectile(World world)
/*     */   {
/*  30 */     super(NikyuProjectiles.URSUS_SHOCK, world);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.dealtAOE = false; } public UrsusShockProjectile(EntityType type, World world) { super(type, world); this.dealtAOE = false; } public UrsusShockProjectile(World world, double x, double y, double z) { super(NikyuProjectiles.URSUS_SHOCK, world, x, y, z); this.dealtAOE = false; } public UrsusShockProjectile(World world, LivingEntity player) { super(NikyuProjectiles.URSUS_SHOCK, world, player); this.dealtAOE = false; setDamage(100.0F); setMaxLife(400); setArmorPiercing(); setCanGetStuckInGround(); setPhysical(false);
/*     */     setAffectedByImbuing();
/*     */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  73 */     this.onTickEvent = this::onTickEvent; } private void onBlockImpactEvent(BlockPos hit) { if (this.dealtAOE) {
/*     */       return;
/*     */     }
/*  76 */     AbilityHelper.createSphere(this.world, getPosition(), 24, 5, false, Blocks.AIR, 2, GRIEF_RULE);
/*  77 */     List<Entity> list = WyHelper.getEntitiesNear(getPosition(), this.world, 40.0D, new Class[] { Entity.class });
/*  78 */     list.remove(getThrower());
/*     */     
/*  80 */     for (Entity target : list) {
/*     */       
/*  82 */       if (target == this)
/*     */         continue; 
/*  84 */       if (target instanceof LivingEntity) {
/*     */         
/*  86 */         ((LivingEntity)target).hurtTime = target.hurtResistantTime = 0;
/*  87 */         target.attackEntityFrom(ModDamageSource.causePlayerDamage((PlayerEntity)getThrower()), 100.0F);
/*  88 */         Vec3d speed = target.getLook(1.0F).mul(-1.0D, -1.0D, -1.0D).mul(5.0D, 0.0D, 5.0D);
/*  89 */         target.setMotion(speed.x, 1.0D, speed.z);
/*  90 */         target.velocityChanged = true;
/*     */       } 
/*     */     } 
/*  93 */     this.dealtAOE = true; }
/*     */   private void onTickEvent() { if (this.dealtAOE) {
/*     */       setSize(Math.min(getSize() + 0.5F, 20.0F));
/*     */       setMotion(0.0D, 0.0D, 0.0D);
/*     */       setRotation(90.0F, 0.0F);
/*  98 */     }  } private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE }); private boolean dealtAOE; public void registerData() { super.registerData();
/*  99 */     this.dataManager.register(SIZE, Float.valueOf(0.0F)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(float size) {
/* 109 */     this.dataManager.set(SIZE, Float.valueOf(size));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 114 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\UrsusShockProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */