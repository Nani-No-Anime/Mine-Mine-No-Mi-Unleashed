/*     */ package xyz.pixelatedw.mineminenomi.data.entity.entitystats;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class EntityStatsBase
/*     */   implements IEntityStats
/*     */ {
/*     */   private int doriki;
/*  14 */   private int cola = 100; private int maxCola = 100; private int ultraCola = 0; private int loyalty = 0;
/*     */   private long bounty;
/*  16 */   private String faction = ""; private long belly; private long extol; private String race = ""; private String subRace = ""; private String fightingStyle = ""; private boolean hasShadow = true;
/*     */   private boolean hasHeart = true;
/*  18 */   private double damageMultiplier = 1.0D; private boolean inCombatMode = false;
/*     */   private boolean hasStrawDoll = true;
/*     */   private boolean isRogue = false;
/*     */   
/*     */   public int getDoriki() {
/*  23 */     return this.doriki;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void alterDoriki(int value) {
/*  29 */     this.doriki = MathHelper.clamp(this.doriki + value, 0, CommonConfig.INSTANCE.getDorikiLimit());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoriki(int value) {
/*  35 */     if (value <= CommonConfig.INSTANCE.getDorikiLimit()) {
/*  36 */       this.doriki = value;
/*     */     } else {
/*  38 */       this.doriki = CommonConfig.INSTANCE.getDorikiLimit();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public long getBelly() {
/*  44 */     return this.belly;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void alterBelly(long value) {
/*  50 */     this.belly = WyHelper.clamp(this.belly + value, 0L, 999999999L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBelly(long value) {
/*  56 */     this.belly = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getExtol() {
/*  62 */     return this.extol;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void alterExtol(long value) {
/*  68 */     this.extol = WyHelper.clamp(this.extol + value, 0L, 999999999L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtol(long value) {
/*  74 */     this.extol = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getBounty() {
/*  80 */     return this.bounty;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void alterBounty(long value) {
/*  86 */     this.bounty = WyHelper.clamp(this.bounty + value, 0L, 100000000000L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBounty(long value) {
/*  92 */     this.bounty = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCola() {
/*  98 */     return this.cola;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void alterCola(int value) {
/* 104 */     this.cola = MathHelper.clamp(this.cola + value, 0, getMaxCola());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCola(int value) {
/* 110 */     this.cola = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxCola() {
/* 116 */     return this.maxCola;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void alterMaxCola(int value) {
/* 122 */     this.maxCola = MathHelper.clamp(this.maxCola + value, 0, 1000);
/* 123 */     if (value != 0) {
/* 124 */       this.cola = MathHelper.clamp(this.cola, 0, this.maxCola);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxCola(int value) {
/* 130 */     this.maxCola = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUltraCola() {
/* 136 */     return this.ultraCola;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUltraCola(int value) {
/* 142 */     if (this.ultraCola + value < 0) {
/* 143 */       this.ultraCola = 0;
/*     */     } else {
/* 145 */       this.ultraCola += value;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addUltraCola(int value) {
/* 151 */     this.ultraCola += value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLoyalty() {
/* 157 */     return this.loyalty;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void alterLoyalty(int value) {
/* 163 */     this.loyalty = MathHelper.clamp(this.loyalty + value, 0, 100);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoyalty(int value) {
/* 169 */     this.loyalty = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public FactionHelper.MarineRank getMarineRank() {
/* 179 */     if (!isMarine()) {
/* 180 */       return null;
/*     */     }
/* 182 */     for (int i = 0; i < (FactionHelper.MarineRank.values()).length; i++) {
/*     */       
/* 184 */       FactionHelper.MarineRank rank = FactionHelper.MarineRank.values()[i];
/* 185 */       FactionHelper.MarineRank next = (i + 1 < (FactionHelper.MarineRank.values()).length) ? FactionHelper.MarineRank.values()[i + 1] : null;
/*     */       
/* 187 */       if (getLoyalty() >= rank.getRequiredLoyalty() && (next == null || getLoyalty() < next.getRequiredLoyalty()))
/*     */       {
/* 189 */         return rank;
/*     */       }
/*     */     } 
/*     */     
/* 193 */     return FactionHelper.MarineRank.CHORE_BOY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMarineRank(FactionHelper.MarineRank rank) {
/* 199 */     if (!isMarine()) {
/* 200 */       return false;
/*     */     }
/* 202 */     if (getMarineRank().ordinal() >= rank.ordinal()) {
/* 203 */       return true;
/*     */     }
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public FactionHelper.RevolutionaryRank getRevolutionaryRank() {
/* 212 */     if (!isRevolutionary()) {
/* 213 */       return null;
/*     */     }
/* 215 */     for (int i = 0; i < (FactionHelper.RevolutionaryRank.values()).length; i++) {
/*     */       
/* 217 */       FactionHelper.RevolutionaryRank rank = FactionHelper.RevolutionaryRank.values()[i];
/* 218 */       FactionHelper.RevolutionaryRank next = (i + 1 < (FactionHelper.RevolutionaryRank.values()).length) ? FactionHelper.RevolutionaryRank.values()[i + 1] : null;
/*     */       
/* 220 */       if (getLoyalty() >= rank.getRequiredLoyalty() && (next == null || getLoyalty() < next.getRequiredLoyalty()))
/*     */       {
/* 222 */         return rank;
/*     */       }
/*     */     } 
/*     */     
/* 226 */     return FactionHelper.RevolutionaryRank.MEMBER;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRevolutionaryRank(FactionHelper.RevolutionaryRank rank) {
/* 232 */     if (!isRevolutionary()) {
/* 233 */       return false;
/*     */     }
/* 235 */     if (getRevolutionaryRank().ordinal() >= rank.ordinal()) {
/* 236 */       return true;
/*     */     }
/* 238 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPirate() {
/* 244 */     if (WyHelper.isNullOrEmpty(this.faction)) {
/* 245 */       return false;
/*     */     }
/* 247 */     return this.faction.equalsIgnoreCase("pirate");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMarine() {
/* 253 */     if (WyHelper.isNullOrEmpty(this.faction)) {
/* 254 */       return false;
/*     */     }
/* 256 */     return this.faction.equalsIgnoreCase("marine");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBountyHunter() {
/* 262 */     if (WyHelper.isNullOrEmpty(this.faction)) {
/* 263 */       return false;
/*     */     }
/* 265 */     return this.faction.equalsIgnoreCase("bounty_hunter");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRevolutionary() {
/* 271 */     if (WyHelper.isNullOrEmpty(this.faction)) {
/* 272 */       return false;
/*     */     }
/* 274 */     return this.faction.equalsIgnoreCase("revolutionary");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBandit() {
/* 280 */     if (WyHelper.isNullOrEmpty(this.faction)) {
/* 281 */       return false;
/*     */     }
/* 283 */     return this.faction.equalsIgnoreCase("bandit");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCivilian() {
/* 289 */     if (WyHelper.isNullOrEmpty(this.faction)) {
/* 290 */       return false;
/*     */     }
/* 292 */     return this.faction.equalsIgnoreCase("civilian");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFaction() {
/* 298 */     if (WyHelper.isNullOrEmpty(this.faction)) {
/* 299 */       return false;
/*     */     }
/* 301 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFaction(String value) {
/* 307 */     this.faction = WyHelper.getResourceName(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFaction() {
/* 313 */     return this.faction;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHuman() {
/* 319 */     if (WyHelper.isNullOrEmpty(this.race)) {
/* 320 */       return false;
/*     */     }
/* 322 */     return this.race.equalsIgnoreCase("human");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFishman() {
/* 328 */     if (WyHelper.isNullOrEmpty(this.race)) {
/* 329 */       return false;
/*     */     }
/* 331 */     return this.race.equalsIgnoreCase("fishman");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCyborg() {
/* 337 */     if (WyHelper.isNullOrEmpty(this.race)) {
/* 338 */       return false;
/*     */     }
/* 340 */     return this.race.equalsIgnoreCase("cyborg");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMink() {
/* 346 */     if (WyHelper.isNullOrEmpty(this.race)) {
/* 347 */       return false;
/*     */     }
/* 349 */     return this.race.equalsIgnoreCase("mink");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRace() {
/* 355 */     if (WyHelper.isNullOrEmpty(this.race)) {
/* 356 */       return false;
/*     */     }
/* 358 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRace(String value) {
/* 364 */     this.race = WyHelper.getResourceName(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRace() {
/* 370 */     return this.race;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSwordsman() {
/* 376 */     if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
/* 377 */       return false;
/*     */     }
/* 379 */     return this.fightingStyle.equalsIgnoreCase("swordsman");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSniper() {
/* 385 */     if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
/* 386 */       return false;
/*     */     }
/* 388 */     return this.fightingStyle.equalsIgnoreCase("sniper");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoctor() {
/* 394 */     if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
/* 395 */       return false;
/*     */     }
/* 397 */     return this.fightingStyle.equalsIgnoreCase("doctor");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWeatherWizard() {
/* 403 */     if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
/* 404 */       return false;
/*     */     }
/* 406 */     return this.fightingStyle.equalsIgnoreCase("art_of_weather");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlackLeg() {
/* 412 */     if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
/* 413 */       return false;
/*     */     }
/* 415 */     return this.fightingStyle.equalsIgnoreCase("black_leg");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBrawler() {
/* 421 */     if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
/* 422 */       return false;
/*     */     }
/* 424 */     return this.fightingStyle.equalsIgnoreCase("brawler");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFightingStyle() {
/* 430 */     if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
/* 431 */       return false;
/*     */     }
/* 433 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFightingStyle(String value) {
/* 439 */     this.fightingStyle = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFightingStyle() {
/* 445 */     return this.fightingStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasShadow() {
/* 451 */     return this.hasShadow;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShadow(boolean value) {
/* 457 */     this.hasShadow = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasHeart() {
/* 463 */     return this.hasHeart;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeart(boolean value) {
/* 469 */     this.hasHeart = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInCombatMode() {
/* 475 */     return this.inCombatMode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCombatMode(boolean value) {
/* 481 */     this.inCombatMode = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasStrawDoll() {
/* 487 */     return this.hasStrawDoll;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrawDoll(boolean value) {
/* 493 */     this.hasStrawDoll = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDamageMultiplier() {
/* 499 */     return this.damageMultiplier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamageMultiplier(double multiplier) {
/* 505 */     this.damageMultiplier = multiplier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBunnyMink() {
/* 511 */     return this.subRace.equalsIgnoreCase("mink_bunny");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDogMink() {
/* 517 */     return this.subRace.equalsIgnoreCase("mink_dog");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLionMink() {
/* 523 */     return this.subRace.equalsIgnoreCase("mink_lion");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubRace(String value) {
/* 529 */     this.subRace = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubRace() {
/* 535 */     return this.subRace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRogue() {
/* 541 */     return this.isRogue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRogue(boolean value) {
/* 547 */     this.isRogue = value;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\entitystats\EntityStatsBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */