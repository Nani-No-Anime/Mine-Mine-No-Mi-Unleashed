/*    */ package xyz.pixelatedw.mineminenomi.abilities.marineloyalty;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class MusterAbility extends Ability {
/* 16 */   public static final MusterAbility INSTANCE = new MusterAbility();
/*    */ 
/*    */   
/*    */   public MusterAbility() {
/* 20 */     super("Muster", AbilityHelper.getFactionCategory());
/* 21 */     setMaxCooldown(60.0D);
/* 22 */     setDescription("The user musters some higher level reinforcements");
/* 23 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 28 */     EntityType grunt1Entity = ModEntities.MARINE_WITH_SWORD;
/* 29 */     EntityType grunt2Entity = ModEntities.MARINE_WITH_GUN;
/* 30 */     EntityType captain = ModEntities.MARINE_CAPTAIN;
/*    */     int i;
/* 32 */     for (i = 0; i < WyHelper.randomWithRange(3, 10); i++) {
/*    */       
/* 34 */       EntityType gruntEntity = (i % 2 == 0) ? grunt1Entity : grunt2Entity;
/*    */       
/* 36 */       BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(player.world, gruntEntity, player.getPosition(), 10);
/* 37 */       if (spawnPos != null) {
/* 38 */         gruntEntity.spawn(player.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*    */       }
/*    */     } 
/* 41 */     for (i = 0; i < WyHelper.randomWithRange(1, 3); i++) {
/*    */ 
/*    */       
/* 44 */       BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(player.world, captain, player.getPosition(), 10);
/* 45 */       captain.spawn(player.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*    */     } 
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\marineloyalty\MusterAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */