/*     */ package xyz.pixelatedw.mineminenomi.abilities.nagi;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.sound.PlaySoundEvent;
/*     */ import net.minecraftforge.event.ServerChatEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class SilentAbility extends ContinuousAbility implements IParallelContinuousAbility {
/*  23 */   public static final SilentAbility INSTANCE = new SilentAbility();
/*     */ 
/*     */   
/*     */   public SilentAbility() {
/*  27 */     super("Silent", AbilityHelper.getDevilFruitCategory());
/*  28 */     setDescription("Cancels all noises caused by or around the user");
/*     */     
/*  30 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int time) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class SilentAbilityServerEvents
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onServerMessage(ServerChatEvent event) {
/*  47 */       ServerPlayerEntity serverPlayerEntity = event.getPlayer();
/*  48 */       IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*  49 */       SilentAbility ability = (SilentAbility)abilityData.getEquippedAbility((Ability)SilentAbility.INSTANCE);
/*     */       
/*  51 */       if (ability != null && ability.isContinuous()) {
/*     */         
/*  53 */         event.setCanceled(true);
/*  54 */         List<PlayerEntity> players = WyHelper.getEntitiesNear(serverPlayerEntity.getPosition(), ((PlayerEntity)serverPlayerEntity).world, 30.0D, new Class[] { PlayerEntity.class });
/*     */         
/*  56 */         if (players.size() <= 0) {
/*     */           return;
/*     */         }
/*  59 */         for (PlayerEntity target : players) {
/*  60 */           target.sendMessage(event.getComponent());
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class SilentAbilityClientEvents
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onSoundPlayed(PlaySoundEvent event) {
/*  77 */       Minecraft mc = Minecraft.getInstance();
/*     */       
/*  79 */       if (mc.world == null) {
/*     */         return;
/*     */       }
/*  82 */       BlockPos soundPos = new BlockPos(event.getSound().getX(), event.getSound().getY(), event.getSound().getZ());
/*     */       
/*  84 */       List<PlayerEntity> targets = WyHelper.getEntitiesNear(soundPos, (World)mc.world, 30.0D, new Class[] { PlayerEntity.class });
/*     */       
/*  86 */       if (targets.size() <= 0) {
/*     */         return;
/*     */       }
/*  89 */       PlayerEntity user = null;
/*     */       
/*  91 */       for (PlayerEntity target : targets) {
/*     */         
/*  93 */         IAbilityData targetData = AbilityDataCapability.get((LivingEntity)target);
/*  94 */         SilentAbility ability = (SilentAbility)targetData.getEquippedAbility((Ability)SilentAbility.INSTANCE);
/*  95 */         boolean isActive = (ability != null && ability.isContinuous());
/*  96 */         if (isActive) {
/*     */           
/*  98 */           user = target;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 103 */       if (user != null)
/* 104 */         event.setResultSound(null); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\nagi\SilentAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */