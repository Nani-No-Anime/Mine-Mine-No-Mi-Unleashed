/*    */ package xyz.pixelatedw.mineminenomi.api.entities;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestChooseScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public abstract class TrainerEntity
/*    */   extends OPEntity
/*    */ {
/*    */   public TrainerEntity(EntityType type, World world) {
/* 27 */     this(type, world, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public TrainerEntity(EntityType type, World world, String[] textures) {
/* 32 */     super(type, world, textures);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerGoals() {
/* 38 */     super.registerGoals();
/*    */     
/* 40 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 41 */     props.setFaction("civilian");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canSpawn(IWorld world, SpawnReason reason) {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {}
/*    */ 
/*    */   
/*    */   public boolean canDespawn(double distance) {
/* 56 */     if (super.canDespawn(distance) && distance > 1024.0D) {
/* 57 */       return true;
/*    */     }
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract Quest[] getAvailableQuests(PlayerEntity paramPlayerEntity);
/*    */ 
/*    */   
/*    */   protected boolean processInteract(PlayerEntity player, Hand hand) {
/* 67 */     if (hand != Hand.MAIN_HAND) {
/* 68 */       return false;
/*    */     }
/* 70 */     ItemStack stack = player.getHeldItem(hand);
/* 71 */     if (!stack.isEmpty() && stack.getItem() == Items.NAME_TAG) {
/* 72 */       return false;
/*    */     }
/* 74 */     if (!player.world.isRemote) {
/*    */       
/* 76 */       WyNetwork.sendTo(new SOpenQuestChooseScreenPacket(getEntityId()), player);
/* 77 */       if (this instanceof xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IHakiTrainer)
/*    */       {
/* 79 */         WyNetwork.sendTo(new SSyncHakiDataPacket(player.getEntityId(), HakiDataCapability.get((LivingEntity)player)), player);
/*    */       }
/* 81 */       return true;
/*    */     } 
/* 83 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\entities\TrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */