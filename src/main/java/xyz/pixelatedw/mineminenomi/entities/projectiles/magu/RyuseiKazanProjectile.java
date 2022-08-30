/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class RyuseiKazanProjectile extends AbilityProjectileEntity {
/* 23 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public RyuseiKazanProjectile(World world) {
/* 27 */     super(MaguProjectiles.BAKURETSU_KAZAN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RyuseiKazanProjectile(EntityType type, World world) {
/* 32 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RyuseiKazanProjectile(World world, double x, double y, double z) {
/* 37 */     super(MaguProjectiles.BAKURETSU_KAZAN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public RyuseiKazanProjectile(World world, LivingEntity player) {
/* 42 */     super(MaguProjectiles.BAKURETSU_KAZAN, world, player);
/*    */     
/* 44 */     setDamage(20.0F);
/* 45 */     setGravity(0.01F);
/* 46 */     setChangeHurtTime(true);
/* 47 */     setAffectedByHardening();
/*    */     
/* 49 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 50 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 51 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 55 */     SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 8);
/* 56 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 57 */       hitEntity.setFire(8);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 62 */     AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 2, Blocks.LAVA, GRIEF_RULE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 67 */     if (!this.world.isRemote) {
/*    */       int i;
/* 69 */       for (i = 0; i < 2; i++) {
/*    */         
/* 71 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 72 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 73 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 75 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/* 76 */         data.setLife(3);
/* 77 */         data.setSize(2.3F);
/* 78 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */       
/* 81 */       for (i = 0; i < 2; i++) {
/*    */         
/* 83 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 84 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 85 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 87 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MAGU);
/* 88 */         data.setLife(3);
/* 89 */         data.setSize(2.3F);
/* 90 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\magu\RyuseiKazanProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */