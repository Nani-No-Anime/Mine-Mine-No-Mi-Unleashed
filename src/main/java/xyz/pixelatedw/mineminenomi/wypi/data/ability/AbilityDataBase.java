package xyz.pixelatedw.mineminenomi.wypi.data.ability;

import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AbilityDataBase
  implements IAbilityData
{
  private List<Ability> unlockedAbilities = new ArrayList<>();
  private Ability[] equippedAbilities = new Ability[128];
  
  private Ability previouslyUsedAbility;
  private int currentCombatBarSet = 0;






  
  public boolean addUnlockedAbility(Ability abl) {
    Ability ogAbl = getUnlockedAbility(abl);
    if (ogAbl == null) {
      
      Ability newAbl = Ability.deepClone(abl);
      this.unlockedAbilities.add(newAbl);
      return true;
    } 
    return false;
  }


  
  public boolean loadUnlockedAbility(Ability abl) {
    Ability ogAbl = getUnlockedAbility(abl);
    if (ogAbl == null) {
      
      this.unlockedAbilities.add(abl);
      return true;
    } 
    return false;
  }


  
  public boolean setUnlockedAbility(int slot, Ability abl) {
    Ability ogAbl = getUnlockedAbility(abl);
    if (ogAbl == null) {
      
      if (this.unlockedAbilities.size() > slot) {
        this.unlockedAbilities.set(slot, abl);
      } else {
        this.unlockedAbilities.add(slot, abl);
      }  return true;
    } 
    return false;
  }


  
  public boolean removeUnlockedAbility(Ability abl) {
    Ability ogAbl = getUnlockedAbility(abl);
    if (ogAbl != null) {
      
      this.unlockedAbilities.remove(ogAbl);
      return true;
    } 
    return false;
  }


  
  public boolean hasUnlockedAbility(Ability abl) {
    this.unlockedAbilities.removeIf(ability -> (ability == null));
    return this.unlockedAbilities.parallelStream().anyMatch(ability -> ability.equals(abl));
  }


  
  public <T extends Ability> T getUnlockedAbility(T abl) {
    this.unlockedAbilities.removeIf(ability -> (ability == null));
    return (T)this.unlockedAbilities.parallelStream().filter(ability -> ability.equals(abl)).findFirst().orElse(null);
  }


  
  public <T extends Ability> T getUnlockedAbility(int slot) {
    this.unlockedAbilities.removeIf(ability -> (ability == null));
    return (this.unlockedAbilities.size() > slot) ? (T)this.unlockedAbilities.get(slot) : null;
  }


  
  public <T extends Ability> List<T> getUnlockedAbilities(Predicate<Ability> check) {
    Stream<Ability> stream = this.unlockedAbilities.stream();
    stream = stream.filter(check);
    this.unlockedAbilities.removeIf(ability -> (ability == null));
    return (List<T>)stream.collect((Collector)Collectors.toList());
  }


  
  public <T extends Ability> List<T> getUnlockedAbilities(APIConfig.AbilityCategory category) {
    this.unlockedAbilities.removeIf(ability -> (ability == null));
    return (List<T>)this.unlockedAbilities.parallelStream().filter(ability -> (ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL)).collect(Collectors.toList());
  }


  
  public void clearUnlockedAbilities(APIConfig.AbilityCategory category) {
    this.unlockedAbilities.removeIf(ability -> (ability == null));
    this.unlockedAbilities.removeIf(ability -> (ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL));
  }


  
  public void clearUnlockedAbilities(Predicate<Ability> check) {
    this.unlockedAbilities.removeIf(ability -> (ability == null));
    this.unlockedAbilities.removeIf(check);
  }


  
  public void clearUnlockedAbilityFromList(APIConfig.AbilityCategory category, List<Ability> list) {
    this.unlockedAbilities.removeIf(ability -> ((ability == null || ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL) && list.contains(ability)));
  }


  
  public int countUnlockedAbilities(APIConfig.AbilityCategory category) {
    this.unlockedAbilities.removeIf(ability -> (ability == null));
    return ((List)this.unlockedAbilities
      .parallelStream()
      .filter(ability -> !(ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility))
      .filter(ability -> (ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL))
      .collect(Collectors.toList())).size();
  }






  
  public boolean addEquippedAbility(Ability abl) {
    for (int i = 0; i < this.equippedAbilities.length; i++) {
      
      Ability ability = this.equippedAbilities[i];
      if (ability == null) {
        
        this.equippedAbilities[i] = abl;
        return true;
      } 
    } 
    return false;
  }


  
  public boolean setEquippedAbility(int slot, Ability abl) {
    Ability ogAbl = getEquippedAbility(abl);
    if (ogAbl == null) {
      
      this.equippedAbilities[slot] = abl;
      return true;
    } 
    return false;
  }


  
  public boolean removeEquippedAbility(Ability abl) {
    Ability ogAbl = getUnlockedAbility(abl);
    if (ogAbl != null)
    {
      for (int i = 0; i < this.equippedAbilities.length; i++) {
        
        Ability ability = this.equippedAbilities[i];
        if (ability != null) {
          
          this.equippedAbilities[i] = null;
          return true;
        } 
      } 
    }
    return false;
  }


  
  public boolean hasEquippedAbility(Ability abl) {
    return Arrays.<Ability>stream(this.equippedAbilities)
      .parallel()
      .filter(ability -> (ability != null))
      .anyMatch(ability -> ability.equals(abl));
  }


  
  public int getEquippedAbilitySlot(Ability abl) {
    for (int i = 0; i < this.equippedAbilities.length; i++) {
      
      Ability ability = this.equippedAbilities[i];
      if (ability != null && ability.equals(abl)) {
        return i;
      }
    } 
    return -1;
  }


  
  @Nullable
  public <T extends Ability> T getEquippedAbility(T abl) {
    return (T)Arrays.<Ability>stream(this.equippedAbilities)
      .parallel()
      .filter(ability -> (ability != null))
      .filter(ability -> ability.equals(abl))
      .findFirst().orElse(null);
  }


  
  @Nullable
  public <T extends Ability> T getEquippedAbility(int slot) {
    return (T)this.equippedAbilities[slot];
  }


  
  public <T extends Ability> T[] getEquippedAbilities() {
    Stream<Ability> stream = Arrays.stream(this.equippedAbilities);
    List<Ability> list1 = (List<Ability>) stream.collect((Collector)Collectors.toList());
    Ability[] list2 = new Ability[list1.size()];
    return (T[])list1.<Ability>toArray(list2);
  }


  
  public <T extends Ability> T[] getEquippedAbilities(Predicate<Ability> check) {
    Stream<Ability> stream = Arrays.stream(this.equippedAbilities);
    stream = stream.filter(check);
    List<Ability> list1 = (List<Ability>) stream.collect((Collector)Collectors.toList());
    Ability[] list2 = new Ability[list1.size()];
    return (T[])list1.<Ability>toArray(list2);
  }


  
  public <T extends Ability> T[] getEquippedAbilities(APIConfig.AbilityCategory category) {
    List<Ability> list1 = (List<Ability>)Arrays.<Ability>stream(this.equippedAbilities).filter(ability -> ((ability != null && ability.getCategory() == category) || category == APIConfig.AbilityCategory.ALL)).collect(Collectors.toList());
    Ability[] list2 = new Ability[list1.size()];
    return (T[])list1.<Ability>toArray(list2);
  }


  
  public void clearEquippedAbilities(Predicate<Ability> predicate) {
    for (int i = 0; i < this.equippedAbilities.length; i++) {
      
      Ability ability = this.equippedAbilities[i];
      if (ability != null && predicate.test(ability))
      {
        this.equippedAbilities[i] = null;
      }
    } 
  }


  
  public void clearEquippedAbilities(APIConfig.AbilityCategory category) {
    for (int i = 0; i < this.equippedAbilities.length; i++) {
      
      Ability ability = this.equippedAbilities[i];
      if ((ability != null && ability.getCategory() == category) || category == APIConfig.AbilityCategory.ALL)
      {
        this.equippedAbilities[i] = null;
      }
    } 
  }


  
  public void clearEquippedAbilityFromList(APIConfig.AbilityCategory category, List<Ability> list) {
    for (int i = 0; i < this.equippedAbilities.length; i++) {
      
      Ability ability = this.equippedAbilities[i];
      if ((ability != null && ability.getCategory() == category && list.contains(ability)) || category != APIConfig.AbilityCategory.ALL)
      {
        this.equippedAbilities[i] = null;
      }
    } 
  }


  
  public int countEquippedAbilities(APIConfig.AbilityCategory category) {
    return ((List)Arrays.<Ability>stream(this.equippedAbilities)
      .parallel()
      .filter(ability -> (ability != null))
      .filter(ability -> (ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL))
      .filter(ability -> !(ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility))
      .collect(Collectors.toList()))
      .size();
  }






  
  public <T extends Ability> T getPreviouslyUsedAbility() {
    return (T)this.previouslyUsedAbility;
  }


  
  public void setPreviouslyUsedAbility(Ability abl) {
    this.previouslyUsedAbility = abl;
  }



  
  public int getCombatBarSet() {
    return this.currentCombatBarSet;
  }


  
  public void nextCombatBarSet() {
    this.currentCombatBarSet++;
  }


  
  public void prevCombatBarSet() {
    this.currentCombatBarSet--;
  }


  
  public void setCombatBarSet(int set) {
    this.currentCombatBarSet = set;
  }
}


