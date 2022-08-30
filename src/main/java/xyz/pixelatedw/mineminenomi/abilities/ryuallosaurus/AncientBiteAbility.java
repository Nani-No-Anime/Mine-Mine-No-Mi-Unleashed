/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class AncientBiteAbility extends PunchAbility implements IFormRequiredAbility {
/* 20 */   public static final AncientBiteAbility INSTANCE = new AncientBiteAbility();
/*    */ 
/*    */   
/*    */   public AncientBiteAbility() {
/* 24 */     super("Ancient Bite", AbilityHelper.getDevilFruitCategory());
/* 25 */     setDescription("");
/* 26 */     setMaxCooldown(7.0D);
/*    */     
/* 28 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 29 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 34 */     double x = WyHelper.randomDouble() * 2.0D;
/* 35 */     double y = WyHelper.randomDouble() * 0.3D;
/* 36 */     double z = WyHelper.randomDouble() * 2.0D;
/* 37 */     player.world.addParticle((IParticleData)ParticleTypes.CRIT, target.getPosX(), target.getPosY() + 1.0D, target.getPosZ(), x, y, z);
/* 38 */     target.addPotionEffect(new EffectInstance(ModEffects.BLEEDING, 20, 0));
/*    */     
/* 40 */     for (int i = 0; i < 50; i++) {
/*    */       
/* 42 */       Vec3d vec3d = new Vec3d((player.getRNG().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 43 */       vec3d = vec3d.rotatePitch(-player.rotationPitch * 0.017453292F);
/* 44 */       vec3d = vec3d.rotateYaw(-player.rotationYaw * 0.017453292F);
/* 45 */       ((ServerWorld)player.world).spawnParticle((IParticleData)ParticleTypes.CRIT, target.getPosX(), target.getPosY() + 1.5D, target.getPosZ(), 1, vec3d.x, vec3d.y, vec3d.z, 0.8D);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 51 */     return 20.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 57 */     return new ZoanInfo[] { (ZoanInfo)AllosaurusHeavyZoanInfo.INSTANCE, (ZoanInfo)AllosaurusWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryuallosaurus\AncientBiteAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */