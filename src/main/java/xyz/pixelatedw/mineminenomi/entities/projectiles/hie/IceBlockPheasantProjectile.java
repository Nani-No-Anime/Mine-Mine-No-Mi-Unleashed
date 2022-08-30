/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class IceBlockPheasantProjectile extends AbilityProjectileEntity {
/* 25 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public IceBlockPheasantProjectile(World world) {
/* 29 */     super(HieProjectiles.ICE_BLOCK_PHEASANT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockPheasantProjectile(EntityType type, World world) {
/* 34 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockPheasantProjectile(World world, double x, double y, double z) {
/* 39 */     super(HieProjectiles.ICE_BLOCK_PHEASANT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockPheasantProjectile(World world, LivingEntity player) {
/* 44 */     super(HieProjectiles.ICE_BLOCK_PHEASANT, world, player);
/*    */     
/* 46 */     setDamage(45.0F);
/* 47 */     setArmorPiercing();
/* 48 */     setPassThroughEntities();
/* 49 */     setPhysical(false);
/* 50 */     setAffectedByImbuing();
/* 51 */     setMaxLife(60);
/* 52 */     this.onTickEvent = this::onTickEvent;
/*    */     
/* 54 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.FROZEN, 300, 0) });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 61 */     BlockPos pos = null;
/* 62 */     int j = 1;
/*    */     
/* 64 */     while (pos == null) {
/*    */       
/* 66 */       BlockState state = this.world.getBlockState(getPosition().down(j));
/*    */       
/* 68 */       if (state.isSolid()) {
/*    */         
/* 70 */         pos = getPosition().down(j);
/*    */         
/*    */         break;
/*    */       } 
/* 74 */       if (j > 2) {
/*    */         break;
/*    */       }
/* 77 */       j++;
/*    */     } 
/*    */     
/* 80 */     if (pos == null) {
/*    */       return;
/*    */     }
/* 83 */     int size = 2 + 4 * (getMaxLife() - getLife()) / getMaxLife();
/* 84 */     AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), size, Blocks.BLUE_ICE, GRIEF_RULE);
/*    */     
/* 86 */     if (!this.world.isRemote)
/*    */     {
/* 88 */       for (int i = 0; i < 5; i++) {
/*    */         
/* 90 */         double offsetX = WyHelper.randomDouble();
/* 91 */         double offsetY = WyHelper.randomDouble();
/* 92 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 94 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
/* 95 */         data.setLife(8);
/* 96 */         data.setSize(2.0F);
/* 97 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\IceBlockPheasantProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */