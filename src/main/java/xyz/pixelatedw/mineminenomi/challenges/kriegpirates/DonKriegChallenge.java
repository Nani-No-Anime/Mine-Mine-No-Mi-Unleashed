/*    */ package xyz.pixelatedw.mineminenomi.challenges.kriegpirates;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArenas;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class DonKriegChallenge extends Challenge {
/*    */   public DonKriegChallenge() {
/* 26 */     super("Don Krieg");
/* 27 */     setCategory("Krieg Pirates");
/* 28 */     setArena(ModArenas.BARATIE);
/* 29 */     addRequirements(new Challenge[] { ModChallenges.GIN });
/* 30 */     setObjective("Defeat Don Krieg");
/* 31 */     setTimeLimit(20);
/*    */     
/* 33 */     this.reward.setDoriki(200);
/* 34 */     this.reward.setBounty(5000);
/* 35 */     this.reward.addItem(new ItemStack((IItemProvider)ModArmors.WOOTZ_STEEL_ARMOR));
/*    */     
/* 37 */     this.onStartEvent = this::onStartEvent;
/* 38 */     this.onCompleteEvent = this::onCompleteEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onCompleteEvent(ServerPlayerEntity player) {
/* 43 */     for (ItemStack stack : player.inventory.mainInventory) {
/*    */       
/* 45 */       if (stack.getItem() == ModArmors.MH5_GAS_MASK) {
/* 46 */         player.inventory.deleteStack(stack);
/*    */       }
/*    */     } 
/* 49 */     for (ItemStack stack : player.inventory.armorInventory) {
/*    */       
/* 51 */       if (stack.getItem() == ModArmors.MH5_GAS_MASK) {
/* 52 */         player.inventory.deleteStack(stack);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean onStartEvent(ServerPlayerEntity player, World world, ArenaData data) {
/* 58 */     data.build(world);
/*    */     
/* 60 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
/* 61 */     player.teleport((ServerWorld)world, data.getPlayerSpawn().getX(), data.getPlayerSpawn().getY(), data.getPlayerSpawn().getZ(), 270.0F, 0.0F);
/*    */     
/* 63 */     DonKriegEntity boss = new DonKriegEntity(player.world);
/* 64 */     boss.setLocationAndAngles(data.getBossSpawn().getX(), data.getBossSpawn().getY(), data.getBossSpawn().getZ(), 0.0F, 0.0F);
/* 65 */     boss.onInitialSpawn((IWorld)player.world, player.world.getDifficultyForLocation(player.getPosition()), SpawnReason.EVENT, (ILivingEntityData)null, (CompoundNBT)null);
/* 66 */     if (!player.isCreative() && !player.isSpectator())
/* 67 */       boss.setAttackTarget((LivingEntity)player); 
/* 68 */     world.addEntity((Entity)boss);
/* 69 */     boss.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
/*    */     
/* 71 */     data.getArenaTileEntity().setChallenge(this);
/* 72 */     data.getArenaTileEntity().addPlayer((PlayerEntity)player);
/* 73 */     data.getArenaTileEntity().addTarget((LivingEntity)boss);
/*    */     
/* 75 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityType getTarget() {
/* 81 */     return ModEntities.DON_KRIEG;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\kriegpirates\DonKriegChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */