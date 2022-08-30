/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertSpadaParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DesertSpadaProjectile extends AbilityProjectileEntity {
/* 19 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new DesertSpadaParticleEffect();
/* 20 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public DesertSpadaProjectile(World world) {
/* 24 */     super(SunaProjectiles.DESERT_SPADA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DesertSpadaProjectile(World world, double x, double y, double z) {
/* 29 */     super(SunaProjectiles.DESERT_SPADA, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public DesertSpadaProjectile(World world, LivingEntity player) {
/* 34 */     super(SunaProjectiles.DESERT_SPADA, world, player);
/*    */     
/* 36 */     setDamage(30.0F);
/* 37 */     setPhysical(false);
/* 38 */     setAffectedByImbuing();
/* 39 */     setMaxLife(45);
/* 40 */     setPassThroughEntities();
/*    */     
/* 42 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.HUNGER, 200, 1) });
/*    */ 
/*    */ 
/*    */     
/* 46 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 51 */     BlockPos pos = null;
/* 52 */     int j = 1;
/*    */     
/* 54 */     while (pos == null) {
/*    */       
/* 56 */       BlockState state = this.world.getBlockState(getPosition().down(j));
/*    */       
/* 58 */       if (state.isSolid()) {
/*    */         
/* 60 */         pos = getPosition().down(j);
/*    */         
/*    */         break;
/*    */       } 
/* 64 */       if (j > 5) {
/*    */         break;
/*    */       }
/* 67 */       j++;
/*    */     } 
/*    */     
/* 70 */     if (pos == null) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 75 */     AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), 2, Blocks.AIR, GRIEF_RULE);
/* 76 */     PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), (getMotion()).x, (getMotion()).y, (getMotion()).z);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\DesertSpadaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */