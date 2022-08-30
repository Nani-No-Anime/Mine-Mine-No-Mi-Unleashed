/*     */ package xyz.pixelatedw.mineminenomi.wypi.data.ability;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ public class AbilityDataBase
/*     */   implements IAbilityData
/*     */ {
/*  18 */   private List<Ability> unlockedAbilities = new ArrayList<>();
/*  19 */   private Ability[] equippedAbilities = new Ability[128];
/*     */   
/*     */   private Ability previouslyUsedAbility;
/*  22 */   private int currentCombatBarSet = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addUnlockedAbility(Ability abl) {
/*  31 */     Ability ogAbl = getUnlockedAbility(abl);
/*  32 */     if (ogAbl == null) {
/*     */       
/*  34 */       Ability newAbl = Ability.deepClone(abl);
/*  35 */       this.unlockedAbilities.add(newAbl);
/*  36 */       return true;
/*     */     } 
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean loadUnlockedAbility(Ability abl) {
/*  44 */     Ability ogAbl = getUnlockedAbility(abl);
/*  45 */     if (ogAbl == null) {
/*     */       
/*  47 */       this.unlockedAbilities.add(abl);
/*  48 */       return true;
/*     */     } 
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setUnlockedAbility(int slot, Ability abl) {
/*  56 */     Ability ogAbl = getUnlockedAbility(abl);
/*  57 */     if (ogAbl == null) {
/*     */       
/*  59 */       if (this.unlockedAbilities.size() > slot) {
/*  60 */         this.unlockedAbilities.set(slot, abl);
/*     */       } else {
/*  62 */         this.unlockedAbilities.add(slot, abl);
/*  63 */       }  return true;
/*     */     } 
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeUnlockedAbility(Ability abl) {
/*  71 */     Ability ogAbl = getUnlockedAbility(abl);
/*  72 */     if (ogAbl != null) {
/*     */       
/*  74 */       this.unlockedAbilities.remove(ogAbl);
/*  75 */       return true;
/*     */     } 
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasUnlockedAbility(Ability abl) {
/*  83 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/*  84 */     return this.unlockedAbilities.parallelStream().anyMatch(ability -> ability.equals(abl));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Ability> T getUnlockedAbility(T abl) {
/*  90 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/*  91 */     return (T)this.unlockedAbilities.parallelStream().filter(ability -> ability.equals(abl)).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Ability> T getUnlockedAbility(int slot) {
/*  97 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/*  98 */     return (this.unlockedAbilities.size() > slot) ? (T)this.unlockedAbilities.get(slot) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Ability> List<T> getUnlockedAbilities(Predicate<Ability> check) {
/* 104 */     Stream<Ability> stream = this.unlockedAbilities.stream();
/* 105 */     stream = stream.filter(check);
/* 106 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/* 107 */     return (List<T>)stream.collect((Collector)Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Ability> List<T> getUnlockedAbilities(APIConfig.AbilityCategory category) {
/* 113 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/* 114 */     return (List<T>)this.unlockedAbilities.parallelStream().filter(ability -> (ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL)).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearUnlockedAbilities(APIConfig.AbilityCategory category) {
/* 120 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/* 121 */     this.unlockedAbilities.removeIf(ability -> (ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearUnlockedAbilities(Predicate<Ability> check) {
/* 127 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/* 128 */     this.unlockedAbilities.removeIf(check);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearUnlockedAbilityFromList(APIConfig.AbilityCategory category, List<Ability> list) {
/* 134 */     this.unlockedAbilities.removeIf(ability -> ((ability == null || ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL) && list.contains(ability)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int countUnlockedAbilities(APIConfig.AbilityCategory category) {
/* 140 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/* 141 */     return ((List)this.unlockedAbilities
/* 142 */       .parallelStream()
/* 143 */       .filter(ability -> !(ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility))
/* 144 */       .filter(ability -> (ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL))
/* 145 */       .collect(Collectors.toList())).size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addEquippedAbility(Ability abl) {
/* 155 */     for (int i = 0; i < this.equippedAbilities.length; i++) {
/*     */       
/* 157 */       Ability ability = this.equippedAbilities[i];
/* 158 */       if (ability == null) {
/*     */         
/* 160 */         this.equippedAbilities[i] = abl;
/* 161 */         return true;
/*     */       } 
/*     */     } 
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setEquippedAbility(int slot, Ability abl) {
/* 170 */     Ability ogAbl = getEquippedAbility(abl);
/* 171 */     if (ogAbl == null) {
/*     */       
/* 173 */       this.equippedAbilities[slot] = abl;
/* 174 */       return true;
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeEquippedAbility(Ability abl) {
/* 182 */     Ability ogAbl = getUnlockedAbility(abl);
/* 183 */     if (ogAbl != null)
/*     */     {
/* 185 */       for (int i = 0; i < this.equippedAbilities.length; i++) {
/*     */         
/* 187 */         Ability ability = this.equippedAbilities[i];
/* 188 */         if (ability != null) {
/*     */           
/* 190 */           this.equippedAbilities[i] = null;
/* 191 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasEquippedAbility(Ability abl) {
/* 201 */     return Arrays.<Ability>stream(this.equippedAbilities)
/* 202 */       .parallel()
/* 203 */       .filter(ability -> (ability != null))
/* 204 */       .anyMatch(ability -> ability.equals(abl));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEquippedAbilitySlot(Ability abl) {
/* 210 */     for (int i = 0; i < this.equippedAbilities.length; i++) {
/*     */       
/* 212 */       Ability ability = this.equippedAbilities[i];
/* 213 */       if (ability != null && ability.equals(abl)) {
/* 214 */         return i;
/*     */       }
/*     */     } 
/* 217 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends Ability> T getEquippedAbility(T abl) {
/* 224 */     return (T)Arrays.<Ability>stream(this.equippedAbilities)
/* 225 */       .parallel()
/* 226 */       .filter(ability -> (ability != null))
/* 227 */       .filter(ability -> ability.equals(abl))
/* 228 */       .findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends Ability> T getEquippedAbility(int slot) {
/* 235 */     return (T)this.equippedAbilities[slot];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Ability> T[] getEquippedAbilities() {
/* 241 */     Stream<Ability> stream = Arrays.stream(this.equippedAbilities);
/* 242 */     List<Ability> list1 = (List<Ability>) stream.collect((Collector)Collectors.toList());
/* 243 */     Ability[] list2 = new Ability[list1.size()];
/* 244 */     return (T[])list1.<Ability>toArray(list2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Ability> T[] getEquippedAbilities(Predicate<Ability> check) {
/* 250 */     Stream<Ability> stream = Arrays.stream(this.equippedAbilities);
/* 251 */     stream = stream.filter(check);
/* 252 */     List<Ability> list1 = (List<Ability>) stream.collect((Collector)Collectors.toList());
/* 253 */     Ability[] list2 = new Ability[list1.size()];
/* 254 */     return (T[])list1.<Ability>toArray(list2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Ability> T[] getEquippedAbilities(APIConfig.AbilityCategory category) {
/* 260 */     List<Ability> list1 = (List<Ability>)Arrays.<Ability>stream(this.equippedAbilities).filter(ability -> ((ability != null && ability.getCategory() == category) || category == APIConfig.AbilityCategory.ALL)).collect(Collectors.toList());
/* 261 */     Ability[] list2 = new Ability[list1.size()];
/* 262 */     return (T[])list1.<Ability>toArray(list2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearEquippedAbilities(Predicate<Ability> predicate) {
/* 268 */     for (int i = 0; i < this.equippedAbilities.length; i++) {
/*     */       
/* 270 */       Ability ability = this.equippedAbilities[i];
/* 271 */       if (ability != null && predicate.test(ability))
/*     */       {
/* 273 */         this.equippedAbilities[i] = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearEquippedAbilities(APIConfig.AbilityCategory category) {
/* 281 */     for (int i = 0; i < this.equippedAbilities.length; i++) {
/*     */       
/* 283 */       Ability ability = this.equippedAbilities[i];
/* 284 */       if ((ability != null && ability.getCategory() == category) || category == APIConfig.AbilityCategory.ALL)
/*     */       {
/* 286 */         this.equippedAbilities[i] = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearEquippedAbilityFromList(APIConfig.AbilityCategory category, List<Ability> list) {
/* 294 */     for (int i = 0; i < this.equippedAbilities.length; i++) {
/*     */       
/* 296 */       Ability ability = this.equippedAbilities[i];
/* 297 */       if ((ability != null && ability.getCategory() == category && list.contains(ability)) || category != APIConfig.AbilityCategory.ALL)
/*     */       {
/* 299 */         this.equippedAbilities[i] = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int countEquippedAbilities(APIConfig.AbilityCategory category) {
/* 307 */     return ((List)Arrays.<Ability>stream(this.equippedAbilities)
/* 308 */       .parallel()
/* 309 */       .filter(ability -> (ability != null))
/* 310 */       .filter(ability -> (ability.getCategory() == category || category == APIConfig.AbilityCategory.ALL))
/* 311 */       .filter(ability -> !(ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility))
/* 312 */       .collect(Collectors.toList()))
/* 313 */       .size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Ability> T getPreviouslyUsedAbility() {
/* 323 */     return (T)this.previouslyUsedAbility;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPreviouslyUsedAbility(Ability abl) {
/* 329 */     this.previouslyUsedAbility = abl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCombatBarSet() {
/* 336 */     return this.currentCombatBarSet;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextCombatBarSet() {
/* 342 */     this.currentCombatBarSet++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void prevCombatBarSet() {
/* 348 */     this.currentCombatBarSet--;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCombatBarSet(int set) {
/* 354 */     this.currentCombatBarSet = set;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\data\ability\AbilityDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */