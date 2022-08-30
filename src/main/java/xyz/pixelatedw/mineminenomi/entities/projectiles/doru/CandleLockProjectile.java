/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.CandleChampionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.doru.CandleLockParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class CandleLockProjectile extends AbilityProjectileEntity {
/*  22 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new CandleLockParticleEffect();
/*     */ 
/*     */   
/*     */   public CandleLockProjectile(World world) {
/*  26 */     super(DoruProjectiles.CANDLE_LOCK, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public CandleLockProjectile(EntityType type, World world) {
/*  31 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public CandleLockProjectile(World world, double x, double y, double z) {
/*  36 */     super(DoruProjectiles.CANDLE_LOCK, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public CandleLockProjectile(World world, LivingEntity player) {
/*  41 */     super(DoruProjectiles.CANDLE_LOCK, world, player);
/*     */     
/*  43 */     setDamage(8.0F);
/*  44 */     setMaxLife(20);
/*  45 */     setPassThroughEntities();
/*  46 */     setPhysical(false);
/*  47 */     setAffectedByImbuing();
/*     */     
/*  49 */     this.withEffects = this::withEffects;
/*     */     
/*  51 */     Ability ability = AbilityDataCapability.get(this.owner).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
/*  52 */     if (ability != null && ability.isContinuous()) {
/*  53 */       setCollisionSize(3.0D);
/*     */     }
/*  55 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  60 */     BlockPos pos = null;
/*  61 */     int j = 1;
/*     */     
/*  63 */     while (pos == null) {
/*     */       
/*  65 */       BlockState state = this.world.getBlockState(getPosition().down(j));
/*     */       
/*  67 */       if (state.isSolid()) {
/*     */         
/*  69 */         pos = getPosition().down(j);
/*     */         
/*     */         break;
/*     */       } 
/*  73 */       if (j > 5) {
/*     */         break;
/*     */       }
/*  76 */       j++;
/*     */     } 
/*     */     
/*  79 */     if (pos == null) {
/*     */       return;
/*     */     }
/*  82 */     PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   private EffectInstance[] withEffects() {
/*  87 */     if (this.owner == null)
/*  88 */       remove(); 
/*  89 */     if (this.owner instanceof PlayerEntity) {
/*     */       
/*  91 */       int time = 200;
/*  92 */       int modifier = 1;
/*     */       
/*  94 */       if (((PlayerEntity)this.owner).inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE))) {
/*  95 */         modifier = 2;
/*     */       }
/*  97 */       Ability ability = AbilityDataCapability.get(this.owner).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
/*  98 */       if (ability != null && ability.isContinuous()) {
/*  99 */         time = 300;
/*     */       }
/* 101 */       return new EffectInstance[] { new EffectInstance(ModEffects.CANDLE_LOCK, time, modifier) };
/*     */     } 
/* 103 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doru\CandleLockProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */