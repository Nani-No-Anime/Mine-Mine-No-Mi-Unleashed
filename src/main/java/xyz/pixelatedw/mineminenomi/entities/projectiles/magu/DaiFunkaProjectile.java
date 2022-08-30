/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class DaiFunkaProjectile extends AbilityProjectileEntity {
/*  23 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
/*     */   
/*     */   private boolean changeLifeTime = true;
/*     */   
/*     */   public DaiFunkaProjectile(World world) {
/*  28 */     super(MaguProjectiles.DAI_FUNKA, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public DaiFunkaProjectile(EntityType type, World world) {
/*  33 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public DaiFunkaProjectile(World world, double x, double y, double z) {
/*  38 */     super(MaguProjectiles.DAI_FUNKA, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public DaiFunkaProjectile(World world, LivingEntity player) {
/*  43 */     super(MaguProjectiles.DAI_FUNKA, world, player);
/*     */     
/*  45 */     setDamage(55.0F);
/*  46 */     setMaxLife(35);
/*  47 */     setChangeHurtTime(true);
/*  48 */     setAffectedByHardening();
/*     */     
/*  50 */     setPassThroughEntities();
/*  51 */     setCanGetStuckInGround();
/*     */     
/*  53 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  54 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  55 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/*  60 */     SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 15);
/*  61 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/*  62 */       hitEntity.setFire(15);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  67 */     AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 3, Blocks.LAVA, GRIEF_RULE);
/*  68 */     if (this.changeLifeTime) {
/*     */       
/*  70 */       setLife(3);
/*  71 */       this.changeLifeTime = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  77 */     if (!this.world.isRemote) {
/*     */       int i;
/*  79 */       for (i = 0; i < 2; i++) {
/*     */         
/*  81 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  82 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  83 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  85 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/*  86 */         data.setLife(3);
/*  87 */         data.setSize(2.3F);
/*  88 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */       
/*  91 */       for (i = 0; i < 2; i++) {
/*     */         
/*  93 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  94 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  95 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  97 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MAGU);
/*  98 */         data.setLife(3);
/*  99 */         data.setSize(2.3F);
/* 100 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\magu\DaiFunkaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */