/*    */ package xyz.pixelatedw.mineminenomi.challenges.shellstown;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArenas;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class MorganChallenge extends Challenge {
/*    */   public MorganChallenge() {
/* 22 */     super("Morgan");
/* 23 */     setCategory("Marine 153rd Branch");
/* 24 */     setArena(ModArenas.MARINE_153rd_BRANCH);
/* 25 */     setObjective("Defeat \"Axe-Hand\" Morgan");
/* 26 */     setTimeLimit(10);
/*    */     
/* 28 */     this.reward.setBounty(2000);
/*    */     
/* 30 */     this.onStartEvent = this::onStartEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartEvent(ServerPlayerEntity player, World world, ArenaData data) {
/* 35 */     data.build(world);
/*    */     
/* 37 */     player.teleport((ServerWorld)world, data.getPlayerSpawn().getX(), data.getPlayerSpawn().getY(), data.getPlayerSpawn().getZ(), 270.0F, 0.0F);
/* 38 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
/*    */     
/* 40 */     MorganEntity boss = new MorganEntity(world);
/* 41 */     boss.setLocationAndAngles(data.getBossSpawn().getX(), data.getBossSpawn().getY(), data.getBossSpawn().getZ(), 0.0F, 0.0F);
/* 42 */     boss.onInitialSpawn((IWorld)world, world.getDifficultyForLocation(player.getPosition()), SpawnReason.EVENT, (ILivingEntityData)null, (CompoundNBT)null);
/* 43 */     if (!player.isCreative() && !player.isSpectator())
/* 44 */       boss.setAttackTarget((LivingEntity)player); 
/* 45 */     world.addEntity((Entity)boss);
/* 46 */     boss.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
/*    */     
/* 48 */     data.getArenaTileEntity().setChallenge(this);
/* 49 */     data.getArenaTileEntity().addPlayer((PlayerEntity)player);
/* 50 */     data.getArenaTileEntity().addTarget((LivingEntity)boss);
/*    */     
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityType getTarget() {
/* 58 */     return ModEntities.MORGAN;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\shellstown\MorganChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */