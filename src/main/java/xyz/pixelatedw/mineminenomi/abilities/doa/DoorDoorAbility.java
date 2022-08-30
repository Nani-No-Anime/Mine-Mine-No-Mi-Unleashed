/*    */ package xyz.pixelatedw.mineminenomi.abilities.doa;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.math.Vec3i;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class DoorDoorAbility extends Ability {
/* 17 */   public static final DoorDoorAbility INSTANCE = new DoorDoorAbility();
/*    */ 
/*    */   
/*    */   public DoorDoorAbility() {
/* 21 */     super("Door Door", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("By making a door, the user transports to the other side of any surface");
/* 23 */     setMaxCooldown(5.0D);
/*    */     
/* 25 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 30 */     BlockRayTraceResult hitBlock = WyHelper.rayTraceBlocks((Entity)player, 16.0D);
/* 31 */     if (Math.sqrt(player.getDistanceSq(hitBlock.getHitVec())) > 2.5D)
/*    */     {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     Vec3d dir = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
/* 37 */     int x = (int)Math.floor(dir.x), z = (int)Math.floor(dir.z);
/* 38 */     if ((dir.x > -0.3D && dir.x < 0.0D) || (dir.x > 0.0D && dir.x < 0.3D))
/* 39 */       x = 0; 
/* 40 */     if ((dir.z > -0.3D && dir.z < 0.0D) || (dir.z > 0.0D && dir.z < 0.3D))
/* 41 */       z = 0; 
/* 42 */     Vec3i iDir = new Vec3i(x, dir.y, z);
/* 43 */     BlockPos pos = player.getPosition().add(iDir);
/* 44 */     boolean firstSolid = false;
/* 45 */     int airBlocks = 0;
/* 46 */     for (int i = 0; i < 40; i++) {
/*    */       
/* 48 */       if (player.world.getBlockState(pos) == Blocks.AIR.getDefaultState() && (firstSolid || airBlocks > 1)) {
/*    */         
/* 50 */         player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
/*    */         
/*    */         break;
/*    */       } 
/* 54 */       dir = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
/* 55 */       if ((dir.x > -0.3D && dir.x < 0.0D) || (dir.x > 0.0D && dir.x < 0.3D))
/* 56 */         x = 0; 
/* 57 */       if ((dir.z > -0.3D && dir.z < 0.0D) || (dir.z > 0.0D && dir.z < 0.3D))
/* 58 */         z = 0; 
/* 59 */       iDir = new Vec3i(x, dir.y, z);
/* 60 */       pos = pos.add(iDir);
/*    */       
/* 62 */       if (player.world.getBlockState(pos).isSolid()) {
/* 63 */         firstSolid = true;
/*    */       }
/* 65 */       if (player.world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
/* 66 */         airBlocks++;
/*    */       }
/*    */     } 
/* 69 */     player.world.playSound(null, player.getPosition(), ModSounds.DOA_IN_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doa\DoorDoorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */