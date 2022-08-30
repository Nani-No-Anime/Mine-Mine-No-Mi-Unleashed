/*     */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WantedPosterTileEntity
/*     */   extends TileEntity
/*     */ {
/*  18 */   private String uuid = "";
/*  19 */   private String entityName = "";
/*  20 */   private String bounty = "";
/*  21 */   private String faction = "";
/*  22 */   private String date = "";
/*  23 */   private String background = "";
/*     */   
/*     */   private GameProfile gameProfile;
/*     */   
/*     */   public WantedPosterTileEntity() {
/*  28 */     super(ModTileEntities.WANTED_POSTER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGameProfile(GameProfile gp) {
/*  33 */     this.gameProfile = gp;
/*     */   }
/*     */ 
/*     */   
/*     */   public GameProfile getGameProfile() {
/*  38 */     return this.gameProfile;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUUID() {
/*  43 */     return this.uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUUID(String uuid) {
/*  48 */     this.uuid = uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEntityName() {
/*  53 */     return this.entityName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityName(String name) {
/*  58 */     this.entityName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBackground() {
/*  63 */     return this.background;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(String background) {
/*  68 */     this.background = background;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPosterBounty(String bounty) {
/*  73 */     this.bounty = bounty;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPosterBounty() {
/*  78 */     return this.bounty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFaction(String faction) {
/*  83 */     this.faction = faction;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFaction() {
/*  88 */     return this.faction;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPirate() {
/*  93 */     return this.faction.equalsIgnoreCase("pirate");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRevolutionary() {
/*  98 */     return this.faction.equalsIgnoreCase("revolutionary");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIssuedDate(String date) {
/* 103 */     this.date = date;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIssuedDate() {
/* 108 */     return this.date;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT write(CompoundNBT nbt) {
/* 114 */     super.write(nbt);
/* 115 */     if (this.entityName == null || this.bounty == null || this.date == null || this.background == null) {
/* 116 */       return nbt;
/*     */     }
/* 118 */     nbt.putString("UUID", this.uuid);
/* 119 */     nbt.putString("Name", this.entityName);
/* 120 */     nbt.putString("Bounty", this.bounty);
/* 121 */     nbt.putString("Faction", this.faction);
/* 122 */     nbt.putString("Date", this.date);
/* 123 */     nbt.putString("Background", this.background);
/* 124 */     if (this.gameProfile != null) {
/*     */       
/* 126 */       CompoundNBT compoundnbt = new CompoundNBT();
/* 127 */       NBTUtil.writeGameProfile(compoundnbt, this.gameProfile);
/* 128 */       nbt.put("Owner", (INBT)compoundnbt);
/*     */     } 
/*     */     
/* 131 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/* 137 */     super.read(nbt);
/* 138 */     this.uuid = nbt.getString("UUID");
/* 139 */     this.entityName = nbt.getString("Name");
/* 140 */     this.bounty = nbt.getString("Bounty");
/* 141 */     this.faction = nbt.getString("Faction");
/* 142 */     this.date = nbt.getString("Date");
/* 143 */     this.background = nbt.getString("Background");
/* 144 */     if (nbt.contains("Owner", 10)) {
/* 145 */       setGameProfile(NBTUtil.readGameProfile(nbt.getCompound("Owner")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT getUpdateTag() {
/* 151 */     return write(new CompoundNBT());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SUpdateTileEntityPacket getUpdatePacket() {
/* 158 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 159 */     write(nbttagcompound);
/* 160 */     return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
/* 166 */     read(pkt.getNbtCompound());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\WantedPosterTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */