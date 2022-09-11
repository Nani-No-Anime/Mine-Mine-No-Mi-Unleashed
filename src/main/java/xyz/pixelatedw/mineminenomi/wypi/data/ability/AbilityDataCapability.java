package xyz.pixelatedw.mineminenomi.wypi.data.ability;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;




public class AbilityDataCapability
{
  @CapabilityInject(IAbilityData.class)
  public static final Capability<IAbilityData> INSTANCE = null;

  
  public static void register() {
    CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>()
        {
          
          public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side)
          {
            CompoundNBT props = new CompoundNBT();

            
            try {
              ListNBT unlockedAbilities = new ListNBT();
              for (int i = 0; i < instance.getUnlockedAbilities(APIConfig.AbilityCategory.ALL).size(); i++) {
                
                Ability ability = instance.<Ability>getUnlockedAbilities(APIConfig.AbilityCategory.ALL).get(i);
                String name = WyHelper.getResourceName(ability.getName());
                CompoundNBT nbtAbility = new CompoundNBT();
                nbtAbility.putString("name", name);
                nbtAbility.putString("displayName", WyHelper.isNullOrEmpty(ability.getDisplayName()) ? "" : ability.getDisplayName());
                nbtAbility.putIntArray("pools", ability.getPools());
                nbtAbility.putString("unlock", ability.getUnlockType().name());
                if (ability instanceof PassiveAbility)
                {
                  nbtAbility.putBoolean("isPaused", ((PassiveAbility)ability).isPaused());
                }
                if (ability instanceof IExtraUpdateData) {
                  
                  CompoundNBT nbt = ((IExtraUpdateData)ability).getExtraData();
                  nbtAbility.put("extraData", (INBT)nbt);
                } 
                unlockedAbilities.add(nbtAbility);
              } 
              props.put("unlocked_abilities", (INBT)unlockedAbilities);
              
              ListNBT equippedAbilities = new ListNBT();
              for (int j = 0; j < (instance.getEquippedAbilities()).length; j++) {
                
                Ability t = instance.getEquippedAbilities()[j];
                if (t != null) {
                  
                  String name = WyHelper.getResourceName(t.getName());
                  CompoundNBT nbtAbility = new CompoundNBT();
                  nbtAbility.putString("name", name);
                  nbtAbility.putString("displayName", (t.getDisplayName() == null) ? "" : t.getDisplayName());
                  nbtAbility.putInt("pos", j);
                  nbtAbility.putString("state", t.getState().toString());
                  nbtAbility.putIntArray("pools", t.getPools());
                  nbtAbility.putDouble("cooldown", t.getCooldown());
                  nbtAbility.putDouble("maxCooldown", t.getMaxCooldown());
                  nbtAbility.putBoolean("isForced", t.isStateForced());
                  if (t instanceof ContinuousAbility) {
                    
                    nbtAbility.putDouble("continueTimer", ((ContinuousAbility)t).getContinueTime());
                    nbtAbility.putDouble("threshold", ((ContinuousAbility)t).getThreshold());
                  } 
                  if (t instanceof ChargeableAbility)
                    nbtAbility.putDouble("chargeTimer", ((ChargeableAbility)t).getChargeTime()); 
                  if (t instanceof IExtraUpdateData) {
                    
                    CompoundNBT nbt = ((IExtraUpdateData)t).getExtraData();
                    nbtAbility.put("extraData", (INBT)nbt);
                  } 
                  equippedAbilities.add(nbtAbility);
                }
                else {
                  
                  CompoundNBT nbtAbility = new CompoundNBT();
                  nbtAbility.putInt("pos", j);
                  equippedAbilities.add(nbtAbility);
                } 
              } 
              props.put("equipped_abilities", (INBT)equippedAbilities);
              
              props.putInt("combat_bar_set", instance.getCombatBarSet());
            }
            catch (Exception ex) {
              
              ex.printStackTrace();
            } 
            
            return (INBT)props;
          }


          
          public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt) {
            CompoundNBT props = (CompoundNBT)nbt;

            
            try {
              instance.clearEquippedAbilities(APIConfig.AbilityCategory.ALL);
              instance.clearUnlockedAbilities(APIConfig.AbilityCategory.ALL);
              
              ListNBT unlockedAbilities = props.getList("unlocked_abilities", 10);
              for (int i = 0; i < unlockedAbilities.size(); i++) {
                
                CompoundNBT nbtAbility = unlockedAbilities.getCompound(i);
                
                try {
                  Ability ability = ((Ability)GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation("mineminenomi", nbtAbility.getString("name")))).create();
                  if (ability != null) {
                    
                    int[] pools = nbtAbility.getIntArray("pools");
                    ability.addInPool(pools);
                    AbilityUnlock unlockType = AbilityUnlock.valueOf(nbtAbility.getString("unlock"));
                    ability.setUnlockType(unlockType);
                    String displayName = nbtAbility.getString("displayName");
                    ability.setDisplayName(displayName);
                    if (ability instanceof PassiveAbility)
                    {
                      ((PassiveAbility)ability).setPause(nbtAbility.getBoolean("isPaused"));
                    }
                    if (ability instanceof IExtraUpdateData) {
                      
                      CompoundNBT extraData = nbtAbility.getCompound("extraData");
                      ((IExtraUpdateData)ability).setExtraData(extraData);
                    } 
                    instance.loadUnlockedAbility(ability);
                  } 
                } catch (Exception e) {
                  
                  WyDebug.debug("Unregistered ability: " + nbtAbility.getString("name"));
                } 
              } 
              
              ListNBT equippedAbilities = props.getList("equipped_abilities", 10);
              List<Ability> activeAbilitiesUnlocked = (List<Ability>)instance.<Ability>getUnlockedAbilities(APIConfig.AbilityCategory.ALL).parallelStream().filter(ability -> !(ability instanceof PassiveAbility)).collect(Collectors.toList());
              for (int j = 0; j < equippedAbilities.size(); j++) {
                
                CompoundNBT nbtAbility = equippedAbilities.getCompound(j);
                Ability ability = (Ability)GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(APIConfig.projectId, nbtAbility.getString("name")));
                if (ability != null) {
                  
                  for (Ability abl : activeAbilitiesUnlocked) {
                    
                    if (abl.equals(ability))
                    {
                      Ability.State state = Ability.State.valueOf(nbtAbility.getString("state"));
                      int cooldown = (int)(nbtAbility.getDouble("cooldown") / 20.0D);
                      int maxCooldown = (int)(nbtAbility.getDouble("maxCooldown") / 20.0D);
                      int pos = nbtAbility.getInt("pos");
                      int[] pools = nbtAbility.getIntArray("pools");
                      String displayName = nbtAbility.getString("displayName");
                      boolean isForced = nbtAbility.getBoolean("isForced");
                      if (state == null)
                        state = Ability.State.STANDBY; 
                      abl.setState(state);
                      abl.setMaxCooldown(maxCooldown);
                      abl.setCooldown(cooldown);
                      abl.addInPool(pools);
                      abl.setDisplayName(displayName);
                      abl.setForcedState(isForced);
                      if (abl instanceof ContinuousAbility) {
                        
                        int continueTime = (int)(nbtAbility.getDouble("continueTime") / 20.0D);
                        ((ContinuousAbility)abl).setContinueTime(continueTime);
                        int threshold = (int)(nbtAbility.getDouble("threshold") / 20.0D);
                        ((ContinuousAbility)abl).setThreshold(threshold);
                      } 
                      if (abl instanceof ChargeableAbility) {
                        
                        int chargeTime = (int)(nbtAbility.getDouble("chargeTime") / 20.0D);
                        ((ChargeableAbility)abl).setChargeTime(chargeTime);
                      } 
                      if (abl instanceof IExtraUpdateData) {
                        
                        CompoundNBT extraData = nbtAbility.getCompound("extraData");
                        ((IExtraUpdateData)abl).setExtraData(extraData);
                      } 
                      
                      instance.setEquippedAbility(pos, abl);
                    }
                  
                  } 
                } else if (ability == null) {
                  
                  int pos = nbtAbility.getInt("pos");
                  instance.setEquippedAbility(pos, null);
                } 
              } 
              
              instance.setCombatBarSet(props.getInt("combat_bar_set"));
            }
            catch (Exception ex) {
              
              ex.printStackTrace();
            } 
          }
        }, AbilityDataBase::new );
  }


  
  public static IAbilityData get(LivingEntity entity) {
    return (IAbilityData)entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
  }

  
  public static LazyOptional<IAbilityData> getLazy(LivingEntity entity) {
    return entity.getCapability(INSTANCE, null);
  }
}


