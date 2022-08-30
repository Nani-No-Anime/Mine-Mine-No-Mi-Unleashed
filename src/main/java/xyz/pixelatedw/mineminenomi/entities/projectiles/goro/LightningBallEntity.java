/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.LightningBallEntityRenderer;
/*     */ 
/*     */ public class LightningBallEntity
/*     */   extends Entity
/*     */ {
/*  22 */   public List<LightningBallEntityRenderer.LightningRendererPropieties> entities = new ArrayList<>();
/*  23 */   protected static final DataParameter<Float> SIZE = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
/*  24 */   protected static final DataParameter<Integer> ALIVE_TICKS = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
/*  25 */   protected static final DataParameter<Float> LIGHTNING_LENGTH = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
/*     */   
/*     */   public LightningBallEntity(World worldIn) {
/*  28 */     super(GoroProjectiles.LIGHTNING_BALL, worldIn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public LightningBallEntity(EntityType<LightningBallEntity> entityType, World world) {
/*  34 */     super(entityType, world);
/*  35 */     this.ignoreFrustumCheck = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public LightningBallEntity(Entity entity, double posX, double posY, double PosZ, float rotationYaw, float rotationPitch) {
/*  40 */     this(GoroProjectiles.LIGHTNING_BALL, entity.world);
/*     */     
/*  42 */     setLocationAndAngles(posX, posY, PosZ, rotationYaw, rotationPitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  47 */     return (new Random()).nextLong();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  52 */     super.tick();
/*     */     
/*  54 */     if (this.ticksExisted >= getAliveTicks()) {
/*  55 */       remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isInRangeToRenderDist(double distance) {
/*  62 */     double d0 = 64.0D * getRenderDistanceWeight();
/*  63 */     return (distance < d0 * d0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  68 */     this.dataManager.register(SIZE, Float.valueOf(0.2F));
/*  69 */     this.dataManager.register(ALIVE_TICKS, Integer.valueOf(200));
/*  70 */     this.dataManager.register(LIGHTNING_LENGTH, Float.valueOf(1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditional(CompoundNBT compound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeAdditional(CompoundNBT compound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/*  86 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */   
/*     */   public void setSize(float size) {
/*  90 */     this.dataManager.set(SIZE, Float.valueOf(size));
/*     */   }
/*     */   
/*     */   public void setLightningLength(float size) {
/*  94 */     this.dataManager.set(LIGHTNING_LENGTH, Float.valueOf(size));
/*     */   }
/*     */   
/*     */   public float getSize() {
/*  98 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*     */   }
/*     */   
/*     */   public float getLightningLength() {
/* 102 */     return ((Float)this.dataManager.get(LIGHTNING_LENGTH)).floatValue();
/*     */   }
/*     */   
/*     */   public int getAliveTicks() {
/* 106 */     return ((Integer)this.dataManager.get(ALIVE_TICKS)).intValue();
/*     */   }
/*     */   
/*     */   public void setAliveTicks(int ticks) {
/* 110 */     this.dataManager.set(ALIVE_TICKS, Integer.valueOf(ticks));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\LightningBallEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */