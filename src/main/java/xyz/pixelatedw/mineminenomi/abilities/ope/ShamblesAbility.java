/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class ShamblesAbility
/*     */   extends Ability {
/*  29 */   public static final Ability INSTANCE = new ShamblesAbility();
/*     */   
/*  31 */   private Mode currentMode = Mode.SINGLE;
/*     */ 
/*     */   
/*     */   public ShamblesAbility() {
/*  35 */     super("Shambles", AbilityHelper.getDevilFruitCategory());
/*  36 */     setMaxCooldown(5.0D);
/*  37 */     setDescription("The user swaps place with the closest entity or block within the ROOM\n\n§2SHIFT-USE§r: Switches between GROUP and SINGLE modes");
/*     */     
/*  39 */     this.onUseEvent = this::onUseEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  44 */     if (!OpeHelper.hasRoomActive(player, this)) {
/*  45 */       return false;
/*     */     }
/*  47 */     RoomAbility ability = (RoomAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)RoomAbility.INSTANCE);
/*  48 */     boolean hadTarget = false;
/*     */     
/*  50 */     if (player.isSneaking()) {
/*     */       
/*  52 */       if (this.currentMode == Mode.SINGLE) {
/*  53 */         this.currentMode = Mode.GROUP;
/*     */       } else {
/*  55 */         this.currentMode = Mode.SINGLE;
/*     */       } 
/*  57 */       player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.currentMode, new Object[0]));
/*  58 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  62 */     if (this.currentMode == Mode.SINGLE) {
/*     */       
/*  64 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
/*     */       
/*  66 */       if (mop instanceof EntityRayTraceResult) {
/*     */         
/*  68 */         EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
/*     */         
/*  70 */         if (entityRayTraceResult.getEntity() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity || (entityRayTraceResult.getEntity() instanceof AbilityProjectileEntity && !((AbilityProjectileEntity)entityRayTraceResult.getEntity()).isPhysical())) {
/*  71 */           return true;
/*     */         }
/*  73 */         Entity target = ((EntityRayTraceResult)mop).getEntity();
/*     */         
/*  75 */         if (!ability.isEntityInThisRoom(target)) {
/*  76 */           return true;
/*     */         }
/*  78 */         float[] beforeCoords = { (float)player.getPosX(), (float)player.getPosY(), (float)player.getPosZ(), player.rotationYaw, player.rotationPitch };
/*  79 */         player.setPositionAndRotation(target.getPosX(), target.getPosY(), target.getPosZ(), target.rotationYaw, target.rotationPitch);
/*  80 */         player.setPositionAndUpdate(target.getPosX(), target.getPosY(), target.getPosZ());
/*  81 */         target.setPositionAndRotation(beforeCoords[0], beforeCoords[1], beforeCoords[2], beforeCoords[3], beforeCoords[4]);
/*     */         
/*  83 */         player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*  84 */         player.world.playSound(null, target.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */         
/*  86 */         hadTarget = true;
/*     */       }
/*  88 */       else if (mop instanceof BlockRayTraceResult) {
/*     */         
/*  90 */         BlockRayTraceResult result = (BlockRayTraceResult)mop;
/*  91 */         BlockPos pos = result.getPos();
/*  92 */         BlockState state = player.world.getBlockState(pos);
/*  93 */         BlockPos playerPos = player.getPosition();
/*  94 */         BlockState playerPosState = player.world.getBlockState(playerPos);
/*     */         
/*  96 */         boolean isInsideRoom = ability.isInsideROOM(player.world, pos);
/*  97 */         boolean isDestinationBanned = RestrictedBlockProtectionRule.INSTANCE.isBanned(state.getBlock());
/*  98 */         boolean isOriginBanned = RestrictedBlockProtectionRule.INSTANCE.isBanned(playerPosState.getBlock());
/*     */         
/* 100 */         if (isInsideRoom && !isDestinationBanned && !isOriginBanned)
/*     */         {
/* 102 */           BlockPos beforePos = player.getPosition();
/* 103 */           player.setPositionAndRotation(pos.getX(), (pos.getY() + 1), pos.getZ(), player.rotationYaw, player.rotationPitch);
/* 104 */           player.world.setBlockState(beforePos, state);
/* 105 */           player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
/*     */           
/* 107 */           player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */           
/* 109 */           hadTarget = true;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 115 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(ability.getCenterBlock(), player.world, ability.getROOMSize(), FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 116 */       targets.remove(player);
/* 117 */       Collections.shuffle(targets);
/*     */       
/* 119 */       for (int i = 0; i < targets.size(); i += 2) {
/*     */         
/* 121 */         if (i >= targets.size() || i + 1 >= targets.size()) {
/*     */           break;
/*     */         }
/* 124 */         LivingEntity target1 = targets.get(i);
/* 125 */         LivingEntity target2 = targets.get(i + 1);
/*     */         
/* 127 */         if (ability.isEntityInThisRoom((Entity)target1) && ability.isEntityInThisRoom((Entity)target2)) {
/*     */ 
/*     */           
/* 130 */           float[] beforeCoords = { (float)target2.getPosX(), (float)target2.getPosY(), (float)target2.getPosZ(), target2.rotationYaw, target2.rotationPitch };
/* 131 */           target2.setPositionAndRotation(target1.getPosX(), target1.getPosY(), target1.getPosZ(), target1.rotationYaw, target1.rotationPitch);
/* 132 */           target2.setPositionAndUpdate(target1.getPosX(), target1.getPosY(), target1.getPosZ());
/* 133 */           target1.setPositionAndRotation(beforeCoords[0], beforeCoords[1], beforeCoords[2], beforeCoords[3], beforeCoords[4]);
/*     */           
/* 135 */           player.world.playSound(null, target2.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 136 */           player.world.playSound(null, target1.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */         } 
/*     */       } 
/* 139 */       if (targets.size() >= 2) {
/* 140 */         hadTarget = true;
/*     */       }
/*     */     } 
/*     */     
/* 144 */     return hadTarget;
/*     */   }
/*     */   
/*     */   public enum Mode
/*     */   {
/* 149 */     SINGLE, GROUP;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\ShamblesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */