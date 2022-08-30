/*     */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*     */ import net.minecraft.tileentity.ITickableTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.ChallengeFailMessageThread;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ChallengeArenaTileEntity
/*     */   extends TileEntity
/*     */   implements ITickableTileEntity
/*     */ {
/*  29 */   private int protectedSize = 10;
/*     */   private Challenge challenge;
/*  31 */   private String arenaName = "";
/*  32 */   private List<UUID> players = new ArrayList<>();
/*  33 */   private List<LivingEntity> targets = new ArrayList<>();
/*  34 */   private int timer = 36000;
/*     */   
/*     */   private boolean isComplete = false;
/*     */   
/*     */   public ChallengeArenaTileEntity() {
/*  39 */     super(ModTileEntities.CHALLENGE_ARENA);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPlayer(PlayerEntity player) {
/*  44 */     if (!this.players.contains(player.getUniqueID())) {
/*  45 */       this.players.add(player.getUniqueID());
/*     */     }
/*     */   }
/*     */   
/*     */   public void removePlayer(UUID uuid, @Nullable ServerPlayerEntity player) {
/*  50 */     if (player != null) {
/*     */       
/*  52 */       ServerWorld overworld = player.getServer().getWorld(DimensionType.OVERWORLD);
/*  53 */       player.teleport(overworld, overworld.getSpawnPoint().getX(), overworld.getSpawnPoint().getY(), overworld.getSpawnPoint().getZ(), 270.0F, 0.0F);
/*  54 */       if (!this.isComplete) {
/*  55 */         (new ChallengeFailMessageThread(player)).start();
/*     */       }
/*     */     } 
/*  58 */     this.players.remove(uuid);
/*     */     
/*  60 */     (this.challenge.getArenaData()).isInUse = false;
/*  61 */     (this.challenge.getArenaData()).owner = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeTargets() {
/*  66 */     Iterator<LivingEntity> targets = this.targets.iterator();
/*  67 */     if (targets.hasNext()) {
/*     */       
/*  69 */       LivingEntity entity = targets.next();
/*  70 */       entity.remove();
/*  71 */       this.targets.remove(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void removePlayers() {
/*  77 */     Iterator<UUID> playersIterator = this.players.iterator();
/*  78 */     if (playersIterator.hasNext()) {
/*     */       
/*  80 */       UUID uuid = playersIterator.next();
/*  81 */       PlayerEntity player = this.world.getPlayerByUuid(uuid);
/*  82 */       removePlayer(uuid, (ServerPlayerEntity)player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeExtra() {
/*  88 */     List<Entity> extras = WyHelper.getEntitiesNear(this.pos, this.world, 100.0D, new Class[] { Entity.class });
/*  89 */     for (Entity extra : extras) {
/*     */       
/*  91 */       if (extra instanceof ServerPlayerEntity) {
/*     */         
/*  93 */         ServerWorld overworld = extra.getServer().getWorld(DimensionType.OVERWORLD);
/*  94 */         ((ServerPlayerEntity)extra).teleport(overworld, overworld.getSpawnPoint().getX(), overworld.getSpawnPoint().getY(), overworld.getSpawnPoint().getZ(), 270.0F, 0.0F);
/*     */         
/*     */         continue;
/*     */       } 
/*  98 */       extra.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTarget(LivingEntity target) {
/* 105 */     this.targets.add(target);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupArena(int size) {
/* 110 */     this.protectedSize = size;
/* 111 */     removeExtra();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChallenge(Challenge challenge) {
/* 116 */     this.challenge = challenge;
/* 117 */     this.arenaName = challenge.getArenaName();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 122 */     return this.protectedSize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 128 */     if (!this.world.isRemote) {
/*     */       
/* 130 */       if (this.challenge == null || !this.challenge.hasStarted()) {
/*     */         return;
/*     */       }
/* 133 */       if (this.timer <= 0) {
/*     */         
/* 135 */         removePlayers();
/* 136 */         removeTargets();
/* 137 */         removeExtra();
/* 138 */         this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
/*     */       } 
/*     */       
/* 141 */       if (this.players.isEmpty()) {
/*     */         
/* 143 */         removeTargets();
/* 144 */         removeExtra();
/* 145 */         this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
/*     */         
/*     */         return;
/*     */       } 
/* 149 */       if (!this.players.isEmpty()) {
/*     */         
/* 151 */         Iterator<UUID> playersIterator = this.players.iterator();
/* 152 */         if (playersIterator.hasNext()) {
/*     */           
/* 154 */           UUID uuid = playersIterator.next();
/* 155 */           PlayerEntity player = this.world.getPlayerByUuid(uuid);
/* 156 */           if (player == null || !player.isAlive() || player.getHealth() <= 0.0F) {
/*     */             
/* 158 */             removePlayer(uuid, (ServerPlayerEntity)player);
/*     */             
/*     */             return;
/*     */           } 
/* 162 */           double distance = player.getDistanceSq(new Vec3d(this.pos.getX(), this.pos.getY(), this.pos.getZ()));
/* 163 */           if (distance > this.protectedSize) {
/* 164 */             removePlayer(uuid, (ServerPlayerEntity)player);
/*     */           }
/* 166 */           if (this.targets.isEmpty()) {
/*     */             
/* 168 */             removeExtra();
/* 169 */             if (this.challenge != null) {
/*     */               
/* 171 */               this.challenge.complete(player);
/* 172 */               this.isComplete = true;
/* 173 */               removePlayer(uuid, (ServerPlayerEntity)player);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 178 */         if (this.players.isEmpty()) {
/*     */           return;
/*     */         }
/* 181 */         Iterator<LivingEntity> targets = this.targets.iterator();
/* 182 */         if (targets.hasNext()) {
/*     */           
/* 184 */           LivingEntity target = targets.next();
/* 185 */           if (target == null || !target.isAlive() || target.getHealth() <= 0.0F) {
/* 186 */             this.targets.remove(target);
/*     */           }
/*     */         } 
/*     */       } 
/* 190 */       this.timer--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbtTag) {
/* 197 */     super.read(nbtTag);
/*     */     
/* 199 */     this.protectedSize = nbtTag.getInt("Size");
/* 200 */     this.timer = nbtTag.getInt("Timer");
/* 201 */     this.arenaName = nbtTag.getString("ArenaName");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT write(CompoundNBT nbtTag) {
/* 207 */     super.write(nbtTag);
/*     */     
/* 209 */     nbtTag.putInt("Size", this.protectedSize);
/* 210 */     nbtTag.putInt("Timer", this.timer);
/* 211 */     nbtTag.putString("ArenaName", this.arenaName);
/*     */     
/* 213 */     return nbtTag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getUpdateTag() {
/* 219 */     return write(new CompoundNBT());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SUpdateTileEntityPacket getUpdatePacket() {
/* 226 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 227 */     write(nbttagcompound);
/* 228 */     return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\ChallengeArenaTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */