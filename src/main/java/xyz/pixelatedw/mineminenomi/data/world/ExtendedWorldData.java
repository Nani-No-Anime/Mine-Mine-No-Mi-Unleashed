package xyz.pixelatedw.mineminenomi.data.world;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;
import java.util.*;




public class ExtendedWorldData
  extends WorldSavedData
{
  private static final String IDENTIFIER = "mineminenomi";
  private HashMap<String, Long> issuedBounties = new HashMap<>();
  private List<String> devilFruitsInWorld = new ArrayList<>();
  private List<int[][]> abilityProtections = (List)new ArrayList<>();
  private HashMap<String, Crew> pirateCrews = new HashMap<>();
  private HashMap<UUID, String> ateDevilFruits = new HashMap<>();
  private HashMap<UUID, List<String>> devilFruitsInInventories = new HashMap<>();
  private HashMap<UUID, Pair<Date, List<String>>> loggedoutFruits = new HashMap<>();
  private List<String> generatedArenas = new ArrayList<>();
  
  private static final ExtendedWorldData CLIENT_DATA = new ExtendedWorldData();

  
  public ExtendedWorldData() {
    super("mineminenomi");
  }

  
  public ExtendedWorldData(String identifier) {
    super(identifier);
  }

  
  public static ExtendedWorldData get(World world) {
    if (world == null) {
      return null;
    }
    ExtendedWorldData worldExt = null;
    
    if (world instanceof ServerWorld) {
      
      ServerWorld serverWorld = world.getServer().getWorld(DimensionType.OVERWORLD);
      worldExt = (ExtendedWorldData)serverWorld.getSavedData().getOrCreate(ExtendedWorldData::new, "mineminenomi");
    } else {
      
      worldExt = CLIENT_DATA;
    } 
    return worldExt;
  }





































  
  public void read(CompoundNBT nbt) {
    CompoundNBT bounties = nbt.getCompound("issuedBounties");
    this.issuedBounties.clear();
    bounties.keySet().stream().forEach(x -> this.issuedBounties.put(x, Long.valueOf(bounties.getLong(x))));



    
    CompoundNBT devilFruits = nbt.getCompound("devilFruits");
    this.devilFruitsInWorld.clear();
    devilFruits.keySet().stream().forEach(x -> this.devilFruitsInWorld.add(x));



    
    CompoundNBT protectedAreas = nbt.getCompound("protectedAreas");
    this.abilityProtections.clear();
    for (int i = 0; i <= protectedAreas.keySet().size(); i++) {
      
      int[] minPos = protectedAreas.getIntArray("minPos_" + i);
      int[] maxPos = protectedAreas.getIntArray("maxPos_" + i);
      
      if (minPos.length == 3 && maxPos.length == 3)
      {
        this.abilityProtections.add(new int[][] { minPos, maxPos });
      }
    } 



    
    this.pirateCrews.clear();
    ListNBT crews = nbt.getList("crews", 10);
    for (int j = 0; j < crews.size(); j++) {
      
      CompoundNBT crewNBT = crews.getCompound(j);
      Crew crew = new Crew();
      crew.read(crewNBT);
      this.pirateCrews.put(WyHelper.getResourceName(crew.getName()), crew);
    } 
    
    CompoundNBT ateDevilFruits = nbt.getCompound("ateDevilFruits");
    this.ateDevilFruits.clear();
    ateDevilFruits.keySet().stream().forEach(key -> this.ateDevilFruits.put(UUID.fromString(key), ateDevilFruits.getString(key)));



    
    this.devilFruitsInInventories.clear();
    ListNBT dfInInventories = nbt.getList("devilFruitsInInventories", 10);
    for (int k = 0; k < dfInInventories.size(); k++) {
      
      CompoundNBT invNbt = dfInInventories.getCompound(k);
      List<String> fruits = new ArrayList<>();
      int noFruits = invNbt.getInt("fruits");
      for (int n = 0; n < noFruits; n++)
      {
        fruits.add(invNbt.getString("fruit-" + n));
      }
      this.devilFruitsInInventories.put(invNbt.getUniqueId("uuid"), fruits);
    } 
    
    this.loggedoutFruits.clear();
    ListNBT loggedoutFruits = nbt.getList("loggedoutFruits", 10);
    for (int m = 0; m < loggedoutFruits.size(); m++) {
      
      CompoundNBT nbtData = loggedoutFruits.getCompound(m);
      UUID id = nbtData.getUniqueId("uuid");
      Date date = new Date(nbtData.getLong("date"));
      List<String> fruits = new ArrayList<>();
      int noFruits = nbtData.getInt("fruits");
      for (int n = 0; n < noFruits; n++)
      {
        fruits.add(nbtData.getString("fruit-" + n));
      }
      this.loggedoutFruits.put(id, ImmutablePair.of(date, fruits));
    } 
    
    CompoundNBT generatedArenas = nbt.getCompound("generatedArenas");
    this.generatedArenas.clear();
    generatedArenas.keySet().stream().forEach(key -> this.generatedArenas.add(key));
  }





  
  public CompoundNBT write(CompoundNBT nbt) {
    CompoundNBT bounties = new CompoundNBT();
    if (this.issuedBounties.size() > 0)
    {
      this.issuedBounties.entrySet().stream().forEach(x -> bounties.putLong((String)x.getKey(), ((Long)x.getValue()).longValue()));
    }


    
    nbt.put("issuedBounties", (INBT)bounties);
    
    CompoundNBT devilFruits = new CompoundNBT();
    if (this.devilFruitsInWorld.size() > 0)
    {
      this.devilFruitsInWorld.stream().forEach(x -> devilFruits.putBoolean(x, true));
    }


    
    nbt.put("devilFruits", (INBT)devilFruits);
    
    CompoundNBT protectedAreas = new CompoundNBT();
    if (this.abilityProtections.size() > 0) {
      
      int i = 0;
      for (int[][] area : this.abilityProtections) {
        
        protectedAreas.putIntArray("minPos_" + i, area[0]);
        protectedAreas.putIntArray("maxPos_" + i, area[1]);
        i++;
      } 
    } 
    nbt.put("protectedAreas", (INBT)protectedAreas);
    
    ListNBT crews = new ListNBT();
    for (Crew crew : this.pirateCrews.values())
    {
      crews.add(crew.write());
    }
    nbt.put("crews", (INBT)crews);
    
    CompoundNBT ateDevilFruits = new CompoundNBT();
    if (this.ateDevilFruits.size() > 0)
    {
      for (Map.Entry<UUID, String> entry : this.ateDevilFruits.entrySet())
      {
        ateDevilFruits.putString(((UUID)entry.getKey()).toString(), entry.getValue());
      }
    }
    nbt.put("ateDevilFruits", (INBT)ateDevilFruits);
    
    ListNBT dfsInInventory = new ListNBT();
    for (Map.Entry<UUID, List<String>> entry : this.devilFruitsInInventories.entrySet()) {
      
      CompoundNBT invNbt = new CompoundNBT();
      invNbt.putUniqueId("uuid", entry.getKey());
      int i = 0;
      for (String fruit : entry.getValue()) {
        
        invNbt.putString("fruit-" + i, fruit);
        i++;
      } 
      invNbt.putInt("fruits", i);
      dfsInInventory.add(invNbt);
    } 
    nbt.put("devilFruitsInInventories", (INBT)dfsInInventory);
    
    ListNBT loggedoutFruits = new ListNBT();
    for (Map.Entry<UUID, Pair<Date, List<String>>> entry : this.loggedoutFruits.entrySet()) {
      
      CompoundNBT nbtData = new CompoundNBT();
      nbtData.putUniqueId("uuid", entry.getKey());
      nbtData.putLong("date", ((Date)(entry.getValue()).getKey()).getTime());
      int i = 0;
      for (String fruit : (entry.getValue()).getValue()) {
        
        nbtData.putString("fruit-" + i, fruit);
        i++;
      } 
      nbtData.putInt("fruits", i);
      loggedoutFruits.add(nbtData);
    } 
    nbt.put("loggedoutFruits", (INBT)loggedoutFruits);
    
    CompoundNBT generatedArenas = new CompoundNBT();
    if (this.generatedArenas.size() > 0)
    {
      for (String key : this.generatedArenas)
      {
        generatedArenas.putBoolean(key, true);
      }
    }
    nbt.put("generatedArenas", (INBT)generatedArenas);
    
    return nbt;
  }

  
  public boolean isArenaGenerated(String key) {
    return this.generatedArenas.contains(key);
  }

  
  public List<String> getGeneratedArenas() {
    return this.generatedArenas;
  }

  
  public void removeGeneratedArena(String key) {
    this.generatedArenas.remove(key);
    markDirty();
  }

  
  public void addGeneratedArena(String key) {
    this.generatedArenas.add(key);
    markDirty();
  }

  
  public HashMap<UUID, String> getAteFruits() {
    return this.ateDevilFruits;
  }

  
  public void addAteDevilFruit(PlayerEntity player, AkumaNoMiItem df) {
    String key = DevilFruitHelper.getDevilFruitKey(df);
    addAteDevilFruit(player, key);
  }

  
  public void addAteDevilFruit(PlayerEntity player, String key) {
    if (this.ateDevilFruits.containsKey(player.getUniqueID()))
      return; 
    this.ateDevilFruits.put(player.getUniqueID(), key);
    markDirty();
  }

  
  public void removeAteDevilFruit(UUID uuid) {
    this.ateDevilFruits.remove(uuid);
    markDirty();
  }

  
  public void removeAteDevilFruit(PlayerEntity player) {
    this.ateDevilFruits.remove(player.getUniqueID());
    markDirty();
  }

  
  public List<Crew> getCrews() {
    return new ArrayList<>(this.pirateCrews.values());
  }

  
  @Nullable
  public Crew getCrewWithMember(UUID memId) {
    for (Crew crew : this.pirateCrews.values()) {
      
      for (Crew.Member member : crew.getMembers()) {
        
        if (member.getUUID().equals(memId)) {
          return crew;
        }
      } 
    } 
    return null;
  }

  
  @Nullable
  public Crew getCrewWithCaptain(UUID capId) {
    return this.pirateCrews.values().stream().filter(crew -> (crew.getCaptain() != null && crew.getCaptain().getUUID().equals(capId))).findFirst().orElse(null);
  }

  
  public void removeCrew(Crew crew) {
    String key = WyHelper.getResourceName(crew.getName());
    if (this.pirateCrews.containsKey(key))
      this.pirateCrews.remove(key); 
    markDirty();
  }

  
  public void addCrew(Crew crew) {
    String key = WyHelper.getResourceName(crew.getName());
    if (!this.pirateCrews.containsKey(key))
      this.pirateCrews.put(key, crew); 
    markDirty();
  }

  
  public void removeCrewMember(Crew crew, UUID uuid) {
    crew.removeMember(uuid);
    markDirty();
  }

  
  public void addCrewMember(Crew crew, LivingEntity entity) {
    crew.addMember(entity);
    markDirty();
  }

  
  public void updateCrewJollyRoger(Crew crew, JollyRoger jollyRoger) {
    crew.setJollyRoger(jollyRoger);
    markDirty();
  }

  
  public boolean isInsideRestrictedArea(int posX, int posY, int posZ) {
    if (this.abilityProtections.size() <= 0) {
      return false;
    }
    for (int[][] area : this.abilityProtections) {
      
      int[] minPos = area[0];
      int[] maxPos = area[1];
      
      if (minPos.length <= 0 || maxPos.length <= 0) {
        continue;
      }
      if (posX > minPos[0] && posX < maxPos[0] && posY > minPos[1] && posY < maxPos[1] && posZ > minPos[2] && posZ < maxPos[2])
      {
        return true;
      }
    } 
    
    return false;
  }

  
  public void addRestrictedArea(int[] minPos, int[] maxPos) {
    this.abilityProtections.add(new int[][] { minPos, maxPos });
    markDirty();
  }

  
  public void removeRestrictedArea(int id) {
    this.abilityProtections.remove(id);
    markDirty();
  }

  
  public void resizeRestrictedArea(int id, int[] minPos, int[] maxPos) {
    this.abilityProtections.set(id, new int[][] { minPos, maxPos });
    markDirty();
  }

  
  public void removeRestrictedArea(int midX, int midY, int midZ) {
    Iterator<int[][]> iterator = this.abilityProtections.iterator();
    while (iterator.hasNext()) {
      
      int[][] area = (int[][])iterator.next();
      int[] minPos = area[0];
      int[] maxPos = area[1];
      
      if (minPos.length <= 0 || maxPos.length <= 0) {
        continue;
      }
      int possibleMidX = (minPos[0] + maxPos[0]) / 2;
      int possibleMidY = (minPos[1] + maxPos[1]) / 2;
      int possibleMidZ = (minPos[2] + maxPos[2]) / 2;
      
      if (midX == possibleMidX && midY == possibleMidY && midZ == possibleMidZ) {
        iterator.remove();
      }
    } 
    markDirty();
  }

  
  public List<int[][]> getAllRestrictions() {
    return this.abilityProtections;
  }

  
  public HashMap<String, Long> getAllBounties() {
    return this.issuedBounties;
  }

  
  public Object[] getRandomBounty() {
    int count = getAllBounties().size();
    
    if (count <= 0) {
      return null;
    }
    Object[] keys = getAllBounties().keySet().toArray();
    Object key = keys[(new Random()).nextInt(count)];
    
    long bounty = ((Long)getAllBounties().get(key)).longValue();
    
    return new Object[] { key, Long.valueOf(bounty) };
  }

  
  public long getBounty(String uuid) {
    if (this.issuedBounties.containsKey(uuid.toLowerCase())) {
      return ((Long)this.issuedBounties.get(uuid.toLowerCase())).longValue();
    }
    return 0L;
  }

  
  public void issueBounty(String uuid, long bounty) {
    if (this.issuedBounties.containsKey(uuid.toLowerCase())) {
      
      this.issuedBounties.remove(uuid.toLowerCase());
      this.issuedBounties.put(uuid.toLowerCase(), Long.valueOf(bounty));
    } else {
      
      this.issuedBounties.put(uuid.toLowerCase(), Long.valueOf(bounty));
    } 
    markDirty();
  }

  
  public List<String> getDevilFruitsInWorld() {
    return this.devilFruitsInWorld;
  }

  
  public void removeDevilFruitInWorld(AkumaNoMiItem fruit) {
    String name = DevilFruitHelper.getDevilFruitKey(fruit);
    removeDevilFruitInWorld(name);
  }

  
  public void removeDevilFruitInWorld(String name) {
    if (this.devilFruitsInWorld.contains(name)) {
      
      this.devilFruitsInWorld.remove(name);
      markDirty();
    } 
  }

  
  public void removeDevilFruitsInWorld(List<String> names) {
    this.devilFruitsInWorld.removeAll(names);
    markDirty();
  }

  
  public void addDevilFruitInWorld(AkumaNoMiItem fruit) {
    String name = DevilFruitHelper.getDevilFruitKey(fruit);
    addDevilFruitInWorld(name);
  }

  
  public void addDevilFruitInWorld(String name) {
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      return;
    }
    if (!this.devilFruitsInWorld.contains(name)) {
      
      this.devilFruitsInWorld.add(name);
      markDirty();
    } 
  }

  
  public boolean isDevilFruitInWorld(AkumaNoMiItem fruit) {
    String key = DevilFruitHelper.getDevilFruitKey(fruit);
    return this.devilFruitsInWorld.contains(key);
  }

  
  public boolean isDevilFruitInWorld(String name) {
    return this.devilFruitsInWorld.contains(name);
  }

  
  public void addDevilFruitInInventory(UUID playerId, String fruit) {
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      return;
    }
    if (this.devilFruitsInInventories.containsKey(playerId)) {
      
      if (!((List)this.devilFruitsInInventories.get(playerId)).contains(fruit)) {
        ((List<String>)this.devilFruitsInInventories.get(playerId)).add(fruit);
      }
    } else {
      
      List<String> fruits = new ArrayList<>();
      fruits.add(fruit);
      this.devilFruitsInInventories.put(playerId, fruits);
    } 
    markDirty();
  }

  
  public void removeDevilFruitInInventory(UUID playerId, String fruit) {
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      return;
    }
    if (this.devilFruitsInInventories.containsKey(playerId)) {
      
      ((List)this.devilFruitsInInventories.get(playerId)).remove(fruit);
      markDirty();
    } 
  }

  
  public void removeDevilFruitsInInventory(UUID playerId, List<String> fruits) {
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      return;
    }
    ((List)this.devilFruitsInInventories.get(playerId)).removeAll(fruits);
    markDirty();
  }

  
  public List<String> getFruitsInPlayerInventory(UUID playerId) {
    return this.devilFruitsInInventories.get(playerId);
  }

  
  public HashMap<UUID, List<String>> getFruitsInInventory() {
    return this.devilFruitsInInventories;
  }

  
  public HashMap<UUID, Pair<Date, List<String>>> getLoggedOutDevilFruits() {
    return this.loggedoutFruits;
  }

  
  @Nullable
  public Pair<Date, List<String>> getLoggedOutPlayer(UUID uuid) {
    if (this.loggedoutFruits.containsKey(uuid)) {
      return this.loggedoutFruits.get(uuid);
    }
    return null;
  }

  
  public void addLoggedOutFruit(UUID playerId, List<String> fruits) {
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      return;
    }

    
    this.loggedoutFruits.put(playerId, ImmutablePair.of(new Date(), fruits));
    
    markDirty();
  }

  
  public void removeLoggedOutFruit(UUID playerId) {
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      return;
    }
    this.loggedoutFruits.remove(playerId);
    
    markDirty();
  }
}


