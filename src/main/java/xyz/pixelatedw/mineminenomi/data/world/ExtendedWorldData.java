/*     */ package xyz.pixelatedw.mineminenomi.data.world;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.WorldSavedData;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtendedWorldData
/*     */   extends WorldSavedData
/*     */ {
/*     */   private static final String IDENTIFIER = "mineminenomi";
/*  38 */   private HashMap<String, Long> issuedBounties = new HashMap<>();
/*  39 */   private List<String> devilFruitsInWorld = new ArrayList<>();
/*  40 */   private List<int[][]> abilityProtections = (List)new ArrayList<>();
/*  41 */   private HashMap<String, Crew> pirateCrews = new HashMap<>();
/*  42 */   private HashMap<UUID, String> ateDevilFruits = new HashMap<>();
/*  43 */   private HashMap<UUID, List<String>> devilFruitsInInventories = new HashMap<>();
/*  44 */   private HashMap<UUID, Pair<Date, List<String>>> loggedoutFruits = new HashMap<>();
/*  45 */   private List<String> generatedArenas = new ArrayList<>();
/*     */   
/*  47 */   private static final ExtendedWorldData CLIENT_DATA = new ExtendedWorldData();
/*     */ 
/*     */   
/*     */   public ExtendedWorldData() {
/*  51 */     super("mineminenomi");
/*     */   }
/*     */ 
/*     */   
/*     */   public ExtendedWorldData(String identifier) {
/*  56 */     super(identifier);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ExtendedWorldData get(World world) {
/*  61 */     if (world == null) {
/*  62 */       return null;
/*     */     }
/*  64 */     ExtendedWorldData worldExt = null;
/*     */     
/*  66 */     if (world instanceof ServerWorld) {
/*     */       
/*  68 */       ServerWorld serverWorld = world.getServer().getWorld(DimensionType.OVERWORLD);
/*  69 */       worldExt = (ExtendedWorldData)serverWorld.getSavedData().getOrCreate(ExtendedWorldData::new, "mineminenomi");
/*     */     } else {
/*     */       
/*  72 */       worldExt = CLIENT_DATA;
/*     */     } 
/*  74 */     return worldExt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/* 115 */     CompoundNBT bounties = nbt.getCompound("issuedBounties");
/* 116 */     this.issuedBounties.clear();
/* 117 */     bounties.keySet().stream().forEach(x -> this.issuedBounties.put(x, Long.valueOf(bounties.getLong(x))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     CompoundNBT devilFruits = nbt.getCompound("devilFruits");
/* 123 */     this.devilFruitsInWorld.clear();
/* 124 */     devilFruits.keySet().stream().forEach(x -> this.devilFruitsInWorld.add(x));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     CompoundNBT protectedAreas = nbt.getCompound("protectedAreas");
/* 130 */     this.abilityProtections.clear();
/* 131 */     for (int i = 0; i <= protectedAreas.keySet().size(); i++) {
/*     */       
/* 133 */       int[] minPos = protectedAreas.getIntArray("minPos_" + i);
/* 134 */       int[] maxPos = protectedAreas.getIntArray("maxPos_" + i);
/*     */       
/* 136 */       if (minPos.length == 3 && maxPos.length == 3)
/*     */       {
/* 138 */         this.abilityProtections.add(new int[][] { minPos, maxPos });
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     this.pirateCrews.clear();
/* 146 */     ListNBT crews = nbt.getList("crews", 10);
/* 147 */     for (int j = 0; j < crews.size(); j++) {
/*     */       
/* 149 */       CompoundNBT crewNBT = crews.getCompound(j);
/* 150 */       Crew crew = new Crew();
/* 151 */       crew.read(crewNBT);
/* 152 */       this.pirateCrews.put(WyHelper.getResourceName(crew.getName()), crew);
/*     */     } 
/*     */     
/* 155 */     CompoundNBT ateDevilFruits = nbt.getCompound("ateDevilFruits");
/* 156 */     this.ateDevilFruits.clear();
/* 157 */     ateDevilFruits.keySet().stream().forEach(key -> this.ateDevilFruits.put(UUID.fromString(key), ateDevilFruits.getString(key)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     this.devilFruitsInInventories.clear();
/* 163 */     ListNBT dfInInventories = nbt.getList("devilFruitsInInventories", 10);
/* 164 */     for (int k = 0; k < dfInInventories.size(); k++) {
/*     */       
/* 166 */       CompoundNBT invNbt = dfInInventories.getCompound(k);
/* 167 */       List<String> fruits = new ArrayList<>();
/* 168 */       int noFruits = invNbt.getInt("fruits");
/* 169 */       for (int n = 0; n < noFruits; n++)
/*     */       {
/* 171 */         fruits.add(invNbt.getString("fruit-" + n));
/*     */       }
/* 173 */       this.devilFruitsInInventories.put(invNbt.getUniqueId("uuid"), fruits);
/*     */     } 
/*     */     
/* 176 */     this.loggedoutFruits.clear();
/* 177 */     ListNBT loggedoutFruits = nbt.getList("loggedoutFruits", 10);
/* 178 */     for (int m = 0; m < loggedoutFruits.size(); m++) {
/*     */       
/* 180 */       CompoundNBT nbtData = loggedoutFruits.getCompound(m);
/* 181 */       UUID id = nbtData.getUniqueId("uuid");
/* 182 */       Date date = new Date(nbtData.getLong("date"));
/* 183 */       List<String> fruits = new ArrayList<>();
/* 184 */       int noFruits = nbtData.getInt("fruits");
/* 185 */       for (int n = 0; n < noFruits; n++)
/*     */       {
/* 187 */         fruits.add(nbtData.getString("fruit-" + n));
/*     */       }
/* 189 */       this.loggedoutFruits.put(id, ImmutablePair.of(date, fruits));
/*     */     } 
/*     */     
/* 192 */     CompoundNBT generatedArenas = nbt.getCompound("generatedArenas");
/* 193 */     this.generatedArenas.clear();
/* 194 */     generatedArenas.keySet().stream().forEach(key -> this.generatedArenas.add(key));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT write(CompoundNBT nbt) {
/* 203 */     CompoundNBT bounties = new CompoundNBT();
/* 204 */     if (this.issuedBounties.size() > 0)
/*     */     {
/* 206 */       this.issuedBounties.entrySet().stream().forEach(x -> bounties.putLong((String)x.getKey(), ((Long)x.getValue()).longValue()));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 211 */     nbt.put("issuedBounties", (INBT)bounties);
/*     */     
/* 213 */     CompoundNBT devilFruits = new CompoundNBT();
/* 214 */     if (this.devilFruitsInWorld.size() > 0)
/*     */     {
/* 216 */       this.devilFruitsInWorld.stream().forEach(x -> devilFruits.putBoolean(x, true));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 221 */     nbt.put("devilFruits", (INBT)devilFruits);
/*     */     
/* 223 */     CompoundNBT protectedAreas = new CompoundNBT();
/* 224 */     if (this.abilityProtections.size() > 0) {
/*     */       
/* 226 */       int i = 0;
/* 227 */       for (int[][] area : this.abilityProtections) {
/*     */         
/* 229 */         protectedAreas.putIntArray("minPos_" + i, area[0]);
/* 230 */         protectedAreas.putIntArray("maxPos_" + i, area[1]);
/* 231 */         i++;
/*     */       } 
/*     */     } 
/* 234 */     nbt.put("protectedAreas", (INBT)protectedAreas);
/*     */     
/* 236 */     ListNBT crews = new ListNBT();
/* 237 */     for (Crew crew : this.pirateCrews.values())
/*     */     {
/* 239 */       crews.add(crew.write());
/*     */     }
/* 241 */     nbt.put("crews", (INBT)crews);
/*     */     
/* 243 */     CompoundNBT ateDevilFruits = new CompoundNBT();
/* 244 */     if (this.ateDevilFruits.size() > 0)
/*     */     {
/* 246 */       for (Map.Entry<UUID, String> entry : this.ateDevilFruits.entrySet())
/*     */       {
/* 248 */         ateDevilFruits.putString(((UUID)entry.getKey()).toString(), entry.getValue());
/*     */       }
/*     */     }
/* 251 */     nbt.put("ateDevilFruits", (INBT)ateDevilFruits);
/*     */     
/* 253 */     ListNBT dfsInInventory = new ListNBT();
/* 254 */     for (Map.Entry<UUID, List<String>> entry : this.devilFruitsInInventories.entrySet()) {
/*     */       
/* 256 */       CompoundNBT invNbt = new CompoundNBT();
/* 257 */       invNbt.putUniqueId("uuid", entry.getKey());
/* 258 */       int i = 0;
/* 259 */       for (String fruit : entry.getValue()) {
/*     */         
/* 261 */         invNbt.putString("fruit-" + i, fruit);
/* 262 */         i++;
/*     */       } 
/* 264 */       invNbt.putInt("fruits", i);
/* 265 */       dfsInInventory.add(invNbt);
/*     */     } 
/* 267 */     nbt.put("devilFruitsInInventories", (INBT)dfsInInventory);
/*     */     
/* 269 */     ListNBT loggedoutFruits = new ListNBT();
/* 270 */     for (Map.Entry<UUID, Pair<Date, List<String>>> entry : this.loggedoutFruits.entrySet()) {
/*     */       
/* 272 */       CompoundNBT nbtData = new CompoundNBT();
/* 273 */       nbtData.putUniqueId("uuid", entry.getKey());
/* 274 */       nbtData.putLong("date", ((Date)(entry.getValue()).getKey()).getTime());
/* 275 */       int i = 0;
/* 276 */       for (String fruit : (entry.getValue()).getValue()) {
/*     */         
/* 278 */         nbtData.putString("fruit-" + i, fruit);
/* 279 */         i++;
/*     */       } 
/* 281 */       nbtData.putInt("fruits", i);
/* 282 */       loggedoutFruits.add(nbtData);
/*     */     } 
/* 284 */     nbt.put("loggedoutFruits", (INBT)loggedoutFruits);
/*     */     
/* 286 */     CompoundNBT generatedArenas = new CompoundNBT();
/* 287 */     if (this.generatedArenas.size() > 0)
/*     */     {
/* 289 */       for (String key : this.generatedArenas)
/*     */       {
/* 291 */         generatedArenas.putBoolean(key, true);
/*     */       }
/*     */     }
/* 294 */     nbt.put("generatedArenas", (INBT)generatedArenas);
/*     */     
/* 296 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArenaGenerated(String key) {
/* 301 */     return this.generatedArenas.contains(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getGeneratedArenas() {
/* 306 */     return this.generatedArenas;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeGeneratedArena(String key) {
/* 311 */     this.generatedArenas.remove(key);
/* 312 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addGeneratedArena(String key) {
/* 317 */     this.generatedArenas.add(key);
/* 318 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<UUID, String> getAteFruits() {
/* 323 */     return this.ateDevilFruits;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAteDevilFruit(PlayerEntity player, AkumaNoMiItem df) {
/* 328 */     String key = DevilFruitHelper.getDevilFruitKey(df);
/* 329 */     addAteDevilFruit(player, key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAteDevilFruit(PlayerEntity player, String key) {
/* 334 */     if (this.ateDevilFruits.containsKey(player.getUniqueID()))
/*     */       return; 
/* 336 */     this.ateDevilFruits.put(player.getUniqueID(), key);
/* 337 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAteDevilFruit(UUID uuid) {
/* 342 */     this.ateDevilFruits.remove(uuid);
/* 343 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAteDevilFruit(PlayerEntity player) {
/* 348 */     this.ateDevilFruits.remove(player.getUniqueID());
/* 349 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Crew> getCrews() {
/* 354 */     return new ArrayList<>(this.pirateCrews.values());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Crew getCrewWithMember(UUID memId) {
/* 360 */     for (Crew crew : this.pirateCrews.values()) {
/*     */       
/* 362 */       for (Crew.Member member : crew.getMembers()) {
/*     */         
/* 364 */         if (member.getUUID().equals(memId)) {
/* 365 */           return crew;
/*     */         }
/*     */       } 
/*     */     } 
/* 369 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Crew getCrewWithCaptain(UUID capId) {
/* 375 */     return this.pirateCrews.values().stream().filter(crew -> (crew.getCaptain() != null && crew.getCaptain().getUUID().equals(capId))).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeCrew(Crew crew) {
/* 380 */     String key = WyHelper.getResourceName(crew.getName());
/* 381 */     if (this.pirateCrews.containsKey(key))
/* 382 */       this.pirateCrews.remove(key); 
/* 383 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCrew(Crew crew) {
/* 388 */     String key = WyHelper.getResourceName(crew.getName());
/* 389 */     if (!this.pirateCrews.containsKey(key))
/* 390 */       this.pirateCrews.put(key, crew); 
/* 391 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeCrewMember(Crew crew, UUID uuid) {
/* 396 */     crew.removeMember(uuid);
/* 397 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCrewMember(Crew crew, LivingEntity entity) {
/* 402 */     crew.addMember(entity);
/* 403 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCrewJollyRoger(Crew crew, JollyRoger jollyRoger) {
/* 408 */     crew.setJollyRoger(jollyRoger);
/* 409 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInsideRestrictedArea(int posX, int posY, int posZ) {
/* 414 */     if (this.abilityProtections.size() <= 0) {
/* 415 */       return false;
/*     */     }
/* 417 */     for (int[][] area : this.abilityProtections) {
/*     */       
/* 419 */       int[] minPos = area[0];
/* 420 */       int[] maxPos = area[1];
/*     */       
/* 422 */       if (minPos.length <= 0 || maxPos.length <= 0) {
/*     */         continue;
/*     */       }
/* 425 */       if (posX > minPos[0] && posX < maxPos[0] && posY > minPos[1] && posY < maxPos[1] && posZ > minPos[2] && posZ < maxPos[2])
/*     */       {
/* 427 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 431 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRestrictedArea(int[] minPos, int[] maxPos) {
/* 436 */     this.abilityProtections.add(new int[][] { minPos, maxPos });
/* 437 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeRestrictedArea(int id) {
/* 442 */     this.abilityProtections.remove(id);
/* 443 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resizeRestrictedArea(int id, int[] minPos, int[] maxPos) {
/* 448 */     this.abilityProtections.set(id, new int[][] { minPos, maxPos });
/* 449 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeRestrictedArea(int midX, int midY, int midZ) {
/* 454 */     Iterator<int[][]> iterator = this.abilityProtections.iterator();
/* 455 */     while (iterator.hasNext()) {
/*     */       
/* 457 */       int[][] area = (int[][])iterator.next();
/* 458 */       int[] minPos = area[0];
/* 459 */       int[] maxPos = area[1];
/*     */       
/* 461 */       if (minPos.length <= 0 || maxPos.length <= 0) {
/*     */         continue;
/*     */       }
/* 464 */       int possibleMidX = (minPos[0] + maxPos[0]) / 2;
/* 465 */       int possibleMidY = (minPos[1] + maxPos[1]) / 2;
/* 466 */       int possibleMidZ = (minPos[2] + maxPos[2]) / 2;
/*     */       
/* 468 */       if (midX == possibleMidX && midY == possibleMidY && midZ == possibleMidZ) {
/* 469 */         iterator.remove();
/*     */       }
/*     */     } 
/* 472 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<int[][]> getAllRestrictions() {
/* 477 */     return this.abilityProtections;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<String, Long> getAllBounties() {
/* 482 */     return this.issuedBounties;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getRandomBounty() {
/* 487 */     int count = getAllBounties().size();
/*     */     
/* 489 */     if (count <= 0) {
/* 490 */       return null;
/*     */     }
/* 492 */     Object[] keys = getAllBounties().keySet().toArray();
/* 493 */     Object key = keys[(new Random()).nextInt(count)];
/*     */     
/* 495 */     long bounty = ((Long)getAllBounties().get(key)).longValue();
/*     */     
/* 497 */     return new Object[] { key, Long.valueOf(bounty) };
/*     */   }
/*     */ 
/*     */   
/*     */   public long getBounty(String uuid) {
/* 502 */     if (this.issuedBounties.containsKey(uuid.toLowerCase())) {
/* 503 */       return ((Long)this.issuedBounties.get(uuid.toLowerCase())).longValue();
/*     */     }
/* 505 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public void issueBounty(String uuid, long bounty) {
/* 510 */     if (this.issuedBounties.containsKey(uuid.toLowerCase())) {
/*     */       
/* 512 */       this.issuedBounties.remove(uuid.toLowerCase());
/* 513 */       this.issuedBounties.put(uuid.toLowerCase(), Long.valueOf(bounty));
/*     */     } else {
/*     */       
/* 516 */       this.issuedBounties.put(uuid.toLowerCase(), Long.valueOf(bounty));
/*     */     } 
/* 518 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getDevilFruitsInWorld() {
/* 523 */     return this.devilFruitsInWorld;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeDevilFruitInWorld(AkumaNoMiItem fruit) {
/* 528 */     String name = DevilFruitHelper.getDevilFruitKey(fruit);
/* 529 */     removeDevilFruitInWorld(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeDevilFruitInWorld(String name) {
/* 534 */     if (this.devilFruitsInWorld.contains(name)) {
/*     */       
/* 536 */       this.devilFruitsInWorld.remove(name);
/* 537 */       markDirty();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeDevilFruitsInWorld(List<String> names) {
/* 543 */     this.devilFruitsInWorld.removeAll(names);
/* 544 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDevilFruitInWorld(AkumaNoMiItem fruit) {
/* 549 */     String name = DevilFruitHelper.getDevilFruitKey(fruit);
/* 550 */     addDevilFruitInWorld(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDevilFruitInWorld(String name) {
/* 555 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/* 558 */     if (!this.devilFruitsInWorld.contains(name)) {
/*     */       
/* 560 */       this.devilFruitsInWorld.add(name);
/* 561 */       markDirty();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDevilFruitInWorld(AkumaNoMiItem fruit) {
/* 567 */     String key = DevilFruitHelper.getDevilFruitKey(fruit);
/* 568 */     return this.devilFruitsInWorld.contains(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDevilFruitInWorld(String name) {
/* 573 */     return this.devilFruitsInWorld.contains(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDevilFruitInInventory(UUID playerId, String fruit) {
/* 578 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/* 581 */     if (this.devilFruitsInInventories.containsKey(playerId)) {
/*     */       
/* 583 */       if (!((List)this.devilFruitsInInventories.get(playerId)).contains(fruit)) {
/* 584 */         ((List<String>)this.devilFruitsInInventories.get(playerId)).add(fruit);
/*     */       }
/*     */     } else {
/*     */       
/* 588 */       List<String> fruits = new ArrayList<>();
/* 589 */       fruits.add(fruit);
/* 590 */       this.devilFruitsInInventories.put(playerId, fruits);
/*     */     } 
/* 592 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeDevilFruitInInventory(UUID playerId, String fruit) {
/* 597 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/* 600 */     if (this.devilFruitsInInventories.containsKey(playerId)) {
/*     */       
/* 602 */       ((List)this.devilFruitsInInventories.get(playerId)).remove(fruit);
/* 603 */       markDirty();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeDevilFruitsInInventory(UUID playerId, List<String> fruits) {
/* 609 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/* 612 */     ((List)this.devilFruitsInInventories.get(playerId)).removeAll(fruits);
/* 613 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getFruitsInPlayerInventory(UUID playerId) {
/* 618 */     return this.devilFruitsInInventories.get(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<UUID, List<String>> getFruitsInInventory() {
/* 623 */     return this.devilFruitsInInventories;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<UUID, Pair<Date, List<String>>> getLoggedOutDevilFruits() {
/* 628 */     return this.loggedoutFruits;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Pair<Date, List<String>> getLoggedOutPlayer(UUID uuid) {
/* 634 */     if (this.loggedoutFruits.containsKey(uuid)) {
/* 635 */       return this.loggedoutFruits.get(uuid);
/*     */     }
/* 637 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLoggedOutFruit(UUID playerId, List<String> fruits) {
/* 642 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 647 */     this.loggedoutFruits.put(playerId, ImmutablePair.of(new Date(), fruits));
/*     */     
/* 649 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLoggedOutFruit(UUID playerId) {
/* 654 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/* 657 */     this.loggedoutFruits.remove(playerId);
/*     */     
/* 659 */     markDirty();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\world\ExtendedWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */