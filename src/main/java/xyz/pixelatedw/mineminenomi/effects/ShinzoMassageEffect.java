/*     */ package xyz.pixelatedw.mineminenomi.effects;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ShinzoMassageEffect extends GuardingEffect {
/*     */   int duration;
/*     */   
/*     */   public ShinzoMassageEffect() {
/*  27 */     super(false);
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.duration = 0;
/*     */     this.reduceSpeedAfterHit = true;
/*     */   }
/*     */   public boolean isReady(int duration, int amplifier) {
/*  35 */     this.duration = duration;
/*  36 */     return (duration % 20 == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void performEffect(LivingEntity entity, int amplifier) {
/*  42 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity && !entity.world.isRemote) {
/*     */       
/*  44 */       if (this.duration % 100 == 0)
/*  45 */         entity.world.playMovingSound(null, (Entity)entity, ModSounds.LONG_ELECTRIC_DISCHARGE_SFX, SoundCategory.PLAYERS, 0.5F, 0.5F); 
/*  46 */       entity.world.playMovingSound(null, (Entity)entity, ModSounds.HEART_BEAT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */       
/*  48 */       if (this.duration > 500) {
/*     */         
/*  50 */         List<LivingEntity> list = WyHelper.getEntitiesNear(entity.getPosition(), entity.world, 8.0D, FactionHelper.getOutsideGroupPredicate(entity), new Class[] { LivingEntity.class });
/*  51 */         list.remove(entity);
/*     */         
/*  53 */         list.stream().filter(e -> e.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)entity), 12.0F)).forEachOrdered(e -> {
/*     */               entity.removePotionEffect(ModEffects.PARALYSIS);
/*     */               
/*     */               Vec3d speed = e.getLook(1.0F).mul(-1.0D, -1.0D, -1.0D).mul(3.0D, 0.0D, 3.0D);
/*     */               
/*     */               e.setMotion(speed.x, 1.0D, speed.z);
/*     */               e.velocityChanged = true;
/*     */             });
/*  61 */         int range = 64;
/*  62 */         for (int j = 0; j < range; j++) {
/*     */           
/*  64 */           float boltSize = 8.0F;
/*     */ 
/*     */           
/*  67 */           LightningEntity bolt = new LightningEntity((Entity)entity, entity.getPosX(), entity.getPosY() + 0.5D, entity.getPosZ(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(-90, 90), boltSize, boltSize);
/*     */           
/*  69 */           bolt.setAngle(20);
/*  70 */           bolt.setAliveTicks(20);
/*  71 */           bolt.setDamage(0.0F);
/*  72 */           bolt.setExplosion(0, false);
/*  73 */           bolt.setSize(boltSize / 800.0F);
/*  74 */           bolt.setBranches(3);
/*  75 */           bolt.setSegments(10);
/*  76 */           bolt.disableLightningMimic();
/*  77 */           entity.world.addEntity((Entity)bolt);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getOverlayColor() {
/*  88 */     return new float[] { 0.0F, 0.8F, 1.0F, (float)(0.5D + WyHelper.randomWithRange(-1, 1) / 10.0D) };
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRender(EffectInstance effect) {
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRenderHUD(EffectInstance effect) {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBodyOverlayColor() {
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlockOverlay() {
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockingRotations() {
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getResourceLocation(int duration) {
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public RenderType getRenderType() {
/* 123 */     return ModRenderTypes.ENERGY;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\ShinzoMassageEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */