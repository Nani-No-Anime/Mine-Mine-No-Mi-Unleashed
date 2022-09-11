package xyz.pixelatedw.mineminenomi.wypi.data.ability;

import java.util.List;
import java.util.function.Predicate;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public interface IAbilityData {
  boolean addUnlockedAbility(Ability paramAbility);
  
  boolean loadUnlockedAbility(Ability paramAbility);
  
  @Deprecated
  boolean setUnlockedAbility(int paramInt, Ability paramAbility);
  
  boolean removeUnlockedAbility(Ability paramAbility);
  
  boolean hasUnlockedAbility(Ability paramAbility);
  
  <T extends Ability> T getUnlockedAbility(T paramT);
  
  <T extends Ability> T getUnlockedAbility(int paramInt);
  
  <T extends Ability> List<T> getUnlockedAbilities(Predicate<Ability> paramPredicate);
  
  <T extends Ability> List<T> getUnlockedAbilities(APIConfig.AbilityCategory paramAbilityCategory);
  
  void clearUnlockedAbilities(APIConfig.AbilityCategory paramAbilityCategory);
  
  void clearUnlockedAbilities(Predicate<Ability> paramPredicate);
  
  void clearUnlockedAbilityFromList(APIConfig.AbilityCategory paramAbilityCategory, List<Ability> paramList);
  
  int countUnlockedAbilities(APIConfig.AbilityCategory paramAbilityCategory);
  
  boolean addEquippedAbility(Ability paramAbility);
  
  boolean setEquippedAbility(int paramInt, Ability paramAbility);
  
  boolean removeEquippedAbility(Ability paramAbility);
  
  boolean hasEquippedAbility(Ability paramAbility);
  
  int getEquippedAbilitySlot(Ability paramAbility);
  
  <T extends Ability> T getEquippedAbility(T paramT);
  
  <T extends Ability> T getEquippedAbility(int paramInt);
  
  <T extends Ability> T[] getEquippedAbilities();
  
  <T extends Ability> T[] getEquippedAbilities(Predicate<Ability> paramPredicate);
  
  <T extends Ability> T[] getEquippedAbilities(APIConfig.AbilityCategory paramAbilityCategory);
  
  void clearEquippedAbilities(APIConfig.AbilityCategory paramAbilityCategory);
  
  void clearEquippedAbilities(Predicate<Ability> paramPredicate);
  
  void clearEquippedAbilityFromList(APIConfig.AbilityCategory paramAbilityCategory, List<Ability> paramList);
  
  int countEquippedAbilities(APIConfig.AbilityCategory paramAbilityCategory);
  
  <T extends Ability> T getPreviouslyUsedAbility();
  
  void setPreviouslyUsedAbility(Ability paramAbility);
  
  int getCombatBarSet();
  
  void nextCombatBarSet();
  
  void prevCombatBarSet();
  
  void setCombatBarSet(int paramInt);
}


