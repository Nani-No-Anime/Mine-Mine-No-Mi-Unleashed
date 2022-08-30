/*    */ package xyz.pixelatedw.mineminenomi.challenges.kriegpirates;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.GinEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArenas;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class GinChallenge extends Challenge {
/*    */   public GinChallenge() {
/* 25 */     super("Gin");
/* 26 */     setCategory("Krieg Pirates");
/* 27 */     setArena(ModArenas.BARATIE);
/* 28 */     setObjective("Defeat Gin");
/* 29 */     setTimeLimit(10);
/*    */     
/* 31 */     this.reward.setDoriki(100);
/* 32 */     this.reward.setBounty(1000);
/* 33 */     this.reward.addItem(new ItemStack((IItemProvider)ModWeapons.TONFA));
/*    */     
/* 35 */     this.onStartEvent = this::onStartEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartEvent(ServerPlayerEntity player, World world, ArenaData data) {
/* 40 */     data.build(world);
/*    */     
/* 42 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
/* 43 */     player.teleport((ServerWorld)world, data.getPlayerSpawn().getX(), data.getPlayerSpawn().getY(), data.getPlayerSpawn().getZ(), 270.0F, 0.0F);
/*    */     
/* 45 */     GinEntity boss = new GinEntity(player.world);
/* 46 */     boss.setLocationAndAngles(data.getBossSpawn().getX(), data.getBossSpawn().getY(), data.getBossSpawn().getZ(), 0.0F, 0.0F);
/* 47 */     boss.onInitialSpawn((IWorld)player.world, player.world.getDifficultyForLocation(player.getPosition()), SpawnReason.EVENT, (ILivingEntityData)null, (CompoundNBT)null);
/* 48 */     if (!player.isCreative() && !player.isSpectator())
/* 49 */       boss.setAttackTarget((LivingEntity)player); 
/* 50 */     world.addEntity((Entity)boss);
/* 51 */     boss.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
/*    */     
/* 53 */     data.getArenaTileEntity().setChallenge(this);
/* 54 */     data.getArenaTileEntity().addPlayer((PlayerEntity)player);
/* 55 */     data.getArenaTileEntity().addTarget((LivingEntity)boss);
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityType getTarget() {
/* 63 */     return ModEntities.GIN;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\kriegpirates\GinChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */