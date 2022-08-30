/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.IParticleFactory;
/*     */ import net.minecraft.client.particle.ParticleManager;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModParticleTypes {
/*     */   @Deprecated
/*  19 */   public static final ParticleType<GenericParticleData> GENERIC = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  21 */   public static final ParticleType<GenericParticleData> YUKI = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  22 */   public static final ParticleType<GenericParticleData> YUKI2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  23 */   public static final ParticleType<GenericParticleData> YUKI3 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  25 */   public static final ParticleType<GenericParticleData> PIKA = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  27 */   public static final ParticleType<GenericParticleData> MERA = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  28 */   public static final ParticleType<GenericParticleData> MERA2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  30 */   public static final ParticleType<GenericParticleData> MOKU = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  31 */   public static final ParticleType<GenericParticleData> MOKU2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  33 */   public static final ParticleType<GenericParticleData> SUNA = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  34 */   public static final ParticleType<GenericParticleData> SUNA2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  36 */   public static final ParticleType<GenericParticleData> GASU = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  37 */   public static final ParticleType<GenericParticleData> GASU2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  39 */   public static final ParticleType<GenericParticleData> BLUE_FLAME = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  41 */   public static final ParticleType<GenericParticleData> DARKNESS = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  42 */   public static final ParticleType<GenericParticleData> KUROUZU = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  44 */   public static final ParticleType<GenericParticleData> GORO = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  45 */   public static final ParticleType<GenericParticleData> GORO2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  46 */   public static final ParticleType<GenericParticleData> GORO3 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  48 */   public static final ParticleType<GenericParticleData> MAGU = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  50 */   public static final ParticleType<GenericParticleData> DOKU = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  51 */   public static final ParticleType<GenericParticleData> DOKU_PINK = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  53 */   public static final ParticleType<GenericParticleData> ITO = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  55 */   public static final ParticleType<GenericParticleData> GURA = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  56 */   public static final ParticleType<GenericParticleData> GURA2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  58 */   public static final ParticleType<GenericParticleData> HIE = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  60 */   public static final ParticleType<GenericParticleData> MERO = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  61 */   public static final ParticleType<GenericParticleData> HORU = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  63 */   public static final ParticleType<GenericParticleData> CHIYU = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  65 */   public static final ParticleType<GenericParticleData> RUST = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  67 */   public static final ParticleType<GenericParticleData> AWA = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  68 */   public static final ParticleType<GenericParticleData> AWA2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  69 */   public static final ParticleType<GenericParticleData> AWA3 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  70 */   public static final ParticleType<GenericParticleData> AWA_FOAM = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  72 */   public static final ParticleType<GenericParticleData> BETA = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  74 */   public static final ParticleType<GenericParticleData> NETSU = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  75 */   public static final ParticleType<GenericParticleData> NETSU2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  77 */   public static final ParticleType<GenericParticleData> GRILL = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  79 */   public static final ParticleType<GenericParticleData> MEDIC = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  81 */   public static final ParticleType<GenericParticleData> GORO_YELLOW = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  82 */   public static final ParticleType<GenericParticleData> GORO2_YELLOW = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*  83 */   public static final ParticleType<GenericParticleData> GORO3_YELLOW = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  85 */   public static final ParticleType<GenericParticleData> HANA = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */   
/*  87 */   public static final ParticleType<GenericParticleData> MAGNET = new ParticleType(true, GenericParticleData.DESERIALIZER);
/*     */ 
/*     */   
/*     */   static {
/*  91 */     WyRegistry.registerParticleType(YUKI, "Yuki Particle");
/*  92 */     WyRegistry.registerParticleType(YUKI2, "Yuki2 Particle");
/*  93 */     WyRegistry.registerParticleType(YUKI3, "Yuki3 Particle");
/*     */     
/*  95 */     WyRegistry.registerParticleType(PIKA, "Pika Particle");
/*     */     
/*  97 */     WyRegistry.registerParticleType(MERA, "Mera Particle");
/*  98 */     WyRegistry.registerParticleType(MERA2, "Mera2 Particle");
/*     */     
/* 100 */     WyRegistry.registerParticleType(MOKU, "Moku Particle");
/* 101 */     WyRegistry.registerParticleType(MOKU2, "Moku2 Particle");
/*     */     
/* 103 */     WyRegistry.registerParticleType(SUNA, "Suna Particle");
/* 104 */     WyRegistry.registerParticleType(SUNA2, "Suna2 Particle");
/*     */     
/* 106 */     WyRegistry.registerParticleType(GASU, "Gasu Particle");
/* 107 */     WyRegistry.registerParticleType(GASU2, "Gasu2 Particle");
/*     */     
/* 109 */     WyRegistry.registerParticleType(BLUE_FLAME, "Blue Flame Particle");
/*     */     
/* 111 */     WyRegistry.registerParticleType(DARKNESS, "Darkness Particle");
/* 112 */     WyRegistry.registerParticleType(KUROUZU, "Kurouzu Particle");
/*     */     
/* 114 */     WyRegistry.registerParticleType(GORO, "Goro Particle");
/* 115 */     WyRegistry.registerParticleType(GORO2, "Goro2 Particle");
/* 116 */     WyRegistry.registerParticleType(GORO3, "Goro3 Particle");
/*     */     
/* 118 */     WyRegistry.registerParticleType(MAGU, "Magu Particle");
/*     */     
/* 120 */     WyRegistry.registerParticleType(DOKU, "Doku Particle");
/* 121 */     WyRegistry.registerParticleType(DOKU_PINK, "Doku Pink Particle");
/*     */     
/* 123 */     WyRegistry.registerParticleType(ITO, "Ito Particle");
/*     */     
/* 125 */     WyRegistry.registerParticleType(GURA, "Gura Particle");
/* 126 */     WyRegistry.registerParticleType(GURA2, "Gura2 Particle");
/*     */     
/* 128 */     WyRegistry.registerParticleType(HIE, "Hie Particle");
/*     */     
/* 130 */     WyRegistry.registerParticleType(MERO, "Mero Particle");
/* 131 */     WyRegistry.registerParticleType(HORU, "Horu Particle");
/*     */     
/* 133 */     WyRegistry.registerParticleType(CHIYU, "Chiyu Particle");
/*     */     
/* 135 */     WyRegistry.registerParticleType(RUST, "Rust Particle");
/*     */     
/* 137 */     WyRegistry.registerParticleType(AWA, "Awa Particle");
/* 138 */     WyRegistry.registerParticleType(AWA2, "Awa2 Particle");
/* 139 */     WyRegistry.registerParticleType(AWA3, "Awa3 Particle");
/* 140 */     WyRegistry.registerParticleType(AWA_FOAM, "Awa Foam Particle");
/*     */     
/* 142 */     WyRegistry.registerParticleType(BETA, "Beta Particle");
/*     */     
/* 144 */     WyRegistry.registerParticleType(NETSU, "Netsu Particle");
/* 145 */     WyRegistry.registerParticleType(NETSU2, "Netsu2 Particle");
/*     */     
/* 147 */     WyRegistry.registerParticleType(GRILL, "Grill Particle");
/*     */     
/* 149 */     WyRegistry.registerParticleType(MEDIC, "Medic Particle");
/*     */     
/* 151 */     WyRegistry.registerParticleType(GORO_YELLOW, "Goro Yellow Particle");
/* 152 */     WyRegistry.registerParticleType(GORO2_YELLOW, "Goro2 Yellow Particle");
/* 153 */     WyRegistry.registerParticleType(GORO3_YELLOW, "Goro3 Yellow Particle");
/*     */     
/* 155 */     WyRegistry.registerParticleType(HANA, "Hana Particle");
/*     */     
/* 157 */     WyRegistry.registerParticleType(MAGNET, "Magnet Particle");
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
/* 164 */     ParticleManager manager = (Minecraft.getInstance()).particles;
/*     */     
/* 166 */     manager.registerFactory(YUKI, (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI));
/* 167 */     manager.registerFactory(YUKI2, (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI2));
/* 168 */     manager.registerFactory(YUKI3, (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI3));
/*     */     
/* 170 */     manager.registerFactory(PIKA, (IParticleFactory)new SimpleParticle.Factory(ModResources.PIKA));
/*     */     
/* 172 */     manager.registerFactory(MERA, (IParticleFactory)new SimpleParticle.Factory(ModResources.MERA));
/* 173 */     manager.registerFactory(MERA2, (IParticleFactory)new SimpleParticle.Factory(ModResources.MERA2));
/*     */     
/* 175 */     manager.registerFactory(MOKU, (IParticleFactory)new SimpleParticle.Factory(ModResources.MOKU));
/* 176 */     manager.registerFactory(MOKU2, (IParticleFactory)new SimpleParticle.Factory(ModResources.MOKU2));
/*     */     
/* 178 */     manager.registerFactory(SUNA, (IParticleFactory)new SimpleParticle.Factory(ModResources.SUNA));
/* 179 */     manager.registerFactory(SUNA2, (IParticleFactory)new SimpleParticle.Factory(ModResources.SUNA2));
/*     */     
/* 181 */     manager.registerFactory(GASU, (IParticleFactory)new SimpleParticle.Factory(ModResources.GASU));
/* 182 */     manager.registerFactory(GASU2, (IParticleFactory)new SimpleParticle.Factory(ModResources.GASU2));
/*     */     
/* 184 */     manager.registerFactory(BLUE_FLAME, (IParticleFactory)new SimpleParticle.Factory(ModResources.BLUE_FLAME));
/*     */     
/* 186 */     manager.registerFactory(DARKNESS, (IParticleFactory)new SimpleParticle.Factory(ModResources.DARKNESS));
/* 187 */     manager.registerFactory(KUROUZU, (IParticleFactory)new SimpleParticle.Factory(ModResources.KUROUZU));
/*     */     
/* 189 */     manager.registerFactory(GORO, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO));
/* 190 */     manager.registerFactory(GORO2, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO2));
/* 191 */     manager.registerFactory(GORO3, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO3));
/*     */     
/* 193 */     manager.registerFactory(MAGU, (IParticleFactory)new SimpleParticle.Factory(ModResources.MAGU));
/*     */     
/* 195 */     manager.registerFactory(DOKU, (IParticleFactory)new SimpleParticle.Factory(ModResources.DOKU));
/* 196 */     manager.registerFactory(DOKU_PINK, (IParticleFactory)new SimpleParticle.Factory(ModResources.DOKU_PINK));
/*     */     
/* 198 */     manager.registerFactory(ITO, (IParticleFactory)new SimpleParticle.Factory(ModResources.ITO));
/*     */     
/* 200 */     manager.registerFactory(GURA, (IParticleFactory)new SimpleParticle.Factory(ModResources.GURA));
/* 201 */     manager.registerFactory(GURA2, (IParticleFactory)new SimpleParticle.Factory(ModResources.GURA2));
/*     */     
/* 203 */     manager.registerFactory(HIE, (IParticleFactory)new SimpleParticle.Factory(ModResources.HIE));
/*     */     
/* 205 */     manager.registerFactory(MERO, (IParticleFactory)new SimpleParticle.Factory(ModResources.MERO));
/* 206 */     manager.registerFactory(HORU, (IParticleFactory)new SimpleParticle.Factory(ModResources.HORU));
/*     */     
/* 208 */     manager.registerFactory(CHIYU, (IParticleFactory)new SimpleParticle.Factory(ModResources.CHIYU));
/*     */     
/* 210 */     manager.registerFactory(RUST, (IParticleFactory)new SimpleParticle.Factory(ModResources.RUST));
/*     */     
/* 212 */     manager.registerFactory(AWA, (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA));
/* 213 */     manager.registerFactory(AWA2, (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA2));
/* 214 */     manager.registerFactory(AWA3, (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA3));
/* 215 */     manager.registerFactory(AWA_FOAM, (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA_FOAM));
/*     */     
/* 217 */     manager.registerFactory(BETA, (IParticleFactory)new SimpleParticle.Factory(ModResources.BETA));
/*     */     
/* 219 */     manager.registerFactory(NETSU, (IParticleFactory)new SimpleParticle.Factory(ModResources.NETSU));
/* 220 */     manager.registerFactory(NETSU2, (IParticleFactory)new SimpleParticle.Factory(ModResources.NETSU2));
/*     */     
/* 222 */     manager.registerFactory(GRILL, (IParticleFactory)new SimpleParticle.Factory(ModResources.GRILL));
/*     */     
/* 224 */     manager.registerFactory(MEDIC, (IParticleFactory)new SimpleParticle.Factory(ModResources.MEDIC));
/*     */     
/* 226 */     manager.registerFactory(GORO_YELLOW, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO_YELLOW));
/* 227 */     manager.registerFactory(GORO2_YELLOW, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO2_YELLOW));
/* 228 */     manager.registerFactory(GORO3_YELLOW, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO3_YELLOW));
/*     */     
/* 230 */     manager.registerFactory(HANA, (IParticleFactory)new SimpleParticle.Factory(ModResources.HANA));
/*     */     
/* 232 */     manager.registerFactory(MAGNET, (IParticleFactory)new SimpleParticle.Factory(ModResources.MAGNET));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModParticleTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */