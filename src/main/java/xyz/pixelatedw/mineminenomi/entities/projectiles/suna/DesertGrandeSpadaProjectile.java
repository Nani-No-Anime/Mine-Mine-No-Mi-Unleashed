/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertSpadaParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DesertGrandeSpadaProjectile
/*    */   extends AbilityProjectileEntity {
/* 13 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new DesertSpadaParticleEffect();
/* 14 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public DesertGrandeSpadaProjectile(World world) {
/* 18 */     super(SunaProjectiles.DESERT_GRANDE_SPADA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DesertGrandeSpadaProjectile(World world, double x, double y, double z) {
/* 23 */     super(SunaProjectiles.DESERT_GRANDE_SPADA, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public DesertGrandeSpadaProjectile(World world, LivingEntity player) {
/* 28 */     super(SunaProjectiles.DESERT_GRANDE_SPADA, world, player);
/*    */     
/* 30 */     setDamage(85.0F);
/* 31 */     setPhysical(false);
/* 32 */     setAffectedByImbuing();
/* 33 */     setMaxLife(5);
/* 34 */     setPassThroughBlocks();
/* 35 */     setPassThroughEntities();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\DesertGrandeSpadaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */