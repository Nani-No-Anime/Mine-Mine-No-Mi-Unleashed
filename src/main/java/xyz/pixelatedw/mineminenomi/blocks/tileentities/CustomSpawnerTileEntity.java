/*     */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.tileentity.ITickableTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ 
/*     */ public class CustomSpawnerTileEntity
/*     */   extends TileEntity
/*     */   implements ITickableTileEntity {
/*  25 */   private EntityType entityToSpawn = EntityType.PIG;
/*  26 */   private int spawnLimit = 5;
/*  27 */   private int playerDistance = 100;
/*  28 */   private Class<? extends Entity> spawnClass = null;
/*  29 */   private ArrayList<UUID> spawnedEntities = new ArrayList<>();
/*  30 */   private ArrayList<LivingEntity> nearbyEntities = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public CustomSpawnerTileEntity() {
/*  34 */     super(ModTileEntities.CUSTOM_SPAWNER);
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomSpawnerTileEntity setSpawnerMob(EntityType toSpawn) {
/*  39 */     this.entityToSpawn = toSpawn;
/*  40 */     markDirty();
/*  41 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomSpawnerTileEntity setSpawnerLimit(int limit) {
/*  46 */     this.spawnLimit = limit;
/*  47 */     markDirty();
/*  48 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomSpawnerTileEntity setPlayerDistance(int distance) {
/*  53 */     this.playerDistance = distance;
/*  54 */     markDirty();
/*  55 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isActivated() {
/*  60 */     BlockPos blockpos = getPos();
/*  61 */     return getWorld().isPlayerWithin(blockpos.getX() + 0.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D, this.playerDistance);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  67 */     if (this.world == null) {
/*     */       return;
/*     */     }
/*  70 */     if (this.spawnClass == null) {
/*  71 */       this.spawnClass = (Class)this.entityToSpawn.create(this.world).getClass();
/*     */     }
/*  73 */     if (!this.world.isRemote && this.world.getGameTime() % 5L == 0L)
/*     */     {
/*  75 */       if (isActivated()) {
/*     */         
/*  77 */         if (this.world.getBlockState(this.pos.down()).getBlock() == Blocks.AIR) {
/*  78 */           this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
/*     */         }
/*  80 */         if (CommonConfig.INSTANCE.getDestroySpawner() && this.spawnedEntities.size() > 0) {
/*     */           
/*  82 */           int alive = 0;
/*  83 */           for (UUID spawnUUID : this.spawnedEntities) {
/*     */             
/*  85 */             Entity target = ((ServerWorld)this.world).getEntityByUuid(spawnUUID);
/*  86 */             if (target != null && target.isAlive()) {
/*  87 */               alive++;
/*     */             }
/*     */           } 
/*  90 */           if (alive == 0) {
/*  91 */             this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
/*     */           }
/*     */         } 
/*  94 */         if (this.entityToSpawn != null)
/*     */         {
/*  96 */           if (this.spawnedEntities.size() < this.spawnLimit)
/*     */           {
/*  98 */             LivingEntity newSpawn = (LivingEntity)this.entityToSpawn.spawn(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.pos.up(), SpawnReason.STRUCTURE, false, false);
/*  99 */             if (newSpawn != null)
/*     */             {
/* 101 */               double r1 = (this.world.rand.nextDouble() - this.world.rand.nextDouble()) * 2.0D + 0.5D;
/* 102 */               double r2 = (this.world.rand.nextDouble() - this.world.rand.nextDouble()) * 2.0D + 0.5D;
/* 103 */               BlockPos newPos = this.pos.add(r1, 0.0D, r2);
/* 104 */               newSpawn.setLocationAndAngles(newPos.getX(), newPos.getY(), newPos.getZ(), 0.0F, 0.0F);
/* 105 */               this.spawnedEntities.add(newSpawn.getUniqueID());
/* 106 */               markDirty();
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       }
/* 113 */       else if (this.spawnedEntities.size() > 0) {
/*     */         
/* 115 */         for (UUID spawnUUID : this.spawnedEntities) {
/*     */           
/* 117 */           Entity target = ((ServerWorld)this.world).getEntityByUuid(spawnUUID);
/* 118 */           if (target != null && target.isAlive())
/* 119 */             target.remove(); 
/*     */         } 
/* 121 */         this.spawnedEntities.clear();
/* 122 */         markDirty();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/* 131 */     super.read(nbt);
/* 132 */     this.spawnLimit = nbt.getInt("spawnLimit");
/* 133 */     this.playerDistance = nbt.getInt("playerDistance");
/* 134 */     if (this.playerDistance <= 0)
/* 135 */       this.playerDistance = 30; 
/* 136 */     this.entityToSpawn = EntityType.byKey(nbt.getString("entityToSpawn")).orElse(EntityType.PIG);
/*     */     
/* 138 */     this.spawnClass = (Class)this.entityToSpawn.create(this.world).getClass();
/*     */     
/* 140 */     ListNBT spawnedEntities = nbt.getList("spawns", 10);
/* 141 */     for (int i = 0; i < spawnedEntities.size(); i++) {
/*     */       
/* 143 */       CompoundNBT nbtEntity = spawnedEntities.getCompound(i);
/* 144 */       UUID nbtUUID = nbtEntity.getUniqueId("uuid");
/* 145 */       this.spawnedEntities.add(nbtUUID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT write(CompoundNBT nbt) {
/* 152 */     super.write(nbt);
/* 153 */     nbt.putInt("spawnLimit", this.spawnLimit);
/* 154 */     nbt.putInt("playerDistance", this.playerDistance);
/* 155 */     nbt.putString("entityToSpawn", EntityType.getKey(this.entityToSpawn).toString());
/*     */     
/* 157 */     ListNBT spawnedEntities = new ListNBT();
/* 158 */     for (UUID uuid : this.spawnedEntities) {
/*     */       
/* 160 */       CompoundNBT nbtEntity = new CompoundNBT();
/* 161 */       nbtEntity.putUniqueId("uuid", uuid);
/* 162 */       spawnedEntities.add(nbtEntity);
/*     */     } 
/* 164 */     nbt.put("spawns", (INBT)spawnedEntities);
/*     */     
/* 166 */     return nbt;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\CustomSpawnerTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */