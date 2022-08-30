/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaFeetEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class CienFleurStompAbility extends RepeaterAbility implements IAnimatedAbility {
/* 26 */   public static final CienFleurStompAbility INSTANCE = new CienFleurStompAbility();
/*    */   
/* 28 */   private static final GreatStompParticleEffect PARTICLES = new GreatStompParticleEffect();
/*    */   
/*    */   private static final int RADIUS = 10;
/*    */   
/*    */   private Iterator<BlockPos> targetedBlocks;
/*    */   
/*    */   public CienFleurStompAbility() {
/* 35 */     super("Cien Fleur: Stomp", AbilityHelper.getDevilFruitCategory());
/* 36 */     setDescription("§2Range:§r 10 blocks\nStomps the ground in front of the user using giant feet");
/*    */     
/* 38 */     setMaxCooldown(10.0D);
/* 39 */     setMaxRepeaterCount(7, 4);
/*    */     
/* 41 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 42 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 47 */     Predicate<BlockPos> predicate = pos -> (player.world.getBlockState(pos.up()).isAir() && pos.getY() > player.getPosY() - 3.0D);
/* 48 */     Vec3d look = player.getPositionVec().add(player.getLookVec().mul(7.0D, 1.0D, 7.0D));
/* 49 */     BlockPos ogPos = new BlockPos(look.getX(), player.getPosY(), look.getZ());
/* 50 */     List<BlockPos> poses = WyHelper.getNearbyBlocks(ogPos, (IWorld)player.world, 10, predicate, (List)ImmutableList.of(Blocks.AIR));
/* 51 */     this.targetedBlocks = WyHelper.shuffle(poses).stream().limit(7L).iterator();
/* 52 */     MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
/* 53 */     player.world.playSound(null, player.getPosition(), ModSounds.HANA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 59 */     if (this.targetedBlocks == null || !this.targetedBlocks.hasNext()) {
/* 60 */       return false;
/*    */     }
/* 62 */     BlockPos pos = this.targetedBlocks.next();
/*    */     
/* 64 */     HanaFeetEntity stompFeet = new HanaFeetEntity(player.world, (LivingEntity)player);
/* 65 */     stompFeet.setPositionAndUpdate(pos.getX(), (pos.getY() + 15), pos.getZ());
/* 66 */     stompFeet.setMotion(0.0D, -0.9D, 0.0D);
/* 67 */     player.world.addEntity((Entity)stompFeet);
/*    */     
/* 69 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 75 */     return (IAnimation)CrossedArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 81 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\CienFleurStompAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */