/*    */ package xyz.pixelatedw.mineminenomi.abilities.chiyu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.ChiyupopoParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class KenpopoAbility extends PunchAbility {
/* 17 */   public static final Ability INSTANCE = (Ability)new KenpopoAbility();
/*    */   
/* 19 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new ChiyupopoParticleEffect();
/*    */ 
/*    */   
/*    */   public KenpopoAbility() {
/* 23 */     super("Kenpopo", AbilityHelper.getDevilFruitCategory());
/* 24 */     setMaxCooldown(50.0D);
/* 25 */     setDescription("Takes the target's life force, transforming it into a Dandelion. Dandelions can be eaten for healing");
/*    */     
/* 27 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 32 */     if (target.getActivePotionEffect(Effects.WEAKNESS) != null) {
/* 33 */       return 0.0F;
/*    */     }
/* 35 */     target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 2000, 0));
/* 36 */     player.addItemStackToInventory(new ItemStack((IItemProvider)ModItems.DANDELION));
/*    */     
/* 38 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 40 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\chiyu\KenpopoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */