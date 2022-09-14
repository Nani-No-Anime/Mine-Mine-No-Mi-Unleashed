# Mineminenomi Unleashed
Decompled deobfuscated version of The [Mine Mine No Mi](https://www.curseforge.com/minecraft/mc-mods/mine-mine-no-mi) Mod created by (WyndFTWz) and (FGpisces) for version 1.15.2-0.8.1
(compadabiltiy with other mod apis and modifications on the way)

Decompled with [Java Decompiler](http://java-decompiler.github.io/)
then alot of the code broken by Java Decompiler and fixed with[FernFlower](https://github.com/fesh0r/fernflower) to compare bronken functions.

## WARNING
* Do not report any issues with this version of the mod back to the creators of Mine Mine No Mi. The modifications that have been made could be causeing an issues that are un-forceen.

## Modification Plans
* ### Ability Use Log

    have a toggle in the config so when a player uses an ability it will be logged. Allow for a blacklist to exclude non-destructive abilites like gepo

* ### Doriki Cap

    Have the Max Integer Limit be the max range on the "Doriki Limit" config option.  Will cause issue with health vaules, but there may be a way around that.
    
    In same vane as have a config option for setting specific doriki requirements for abilities example "mineminenomi:soru:600,mineminenomi:tekkai:1100,mineminenomi:geppo:1350" would set Soru to 600 Doriki requirement, Tekkai to 1100 and Geppo to 1350

* ### Ability Zones 

    Add commands to add/remove Abilities to a per zone whitelist

* ### Marines/Pirates custom spawn conditions

    Allow Marines and Priates to spawn on more blocks. Also adding config option to add more blocks.

* ### Global Trader
    Trader that trades with everyone.

## Completed Modifications
* Marines, Pirates, and Bandits with guns/snipers that are kiroseki enchant will fire kiroseki bullets
* Improved doriki system so that the doriki nbt values on vinila mobs could be set and used instead of ( MaxHP + MaxAttack )/4
* Change formual for Doriki gain so that higher amounts of doriki can still gain doriki just at a slower rate