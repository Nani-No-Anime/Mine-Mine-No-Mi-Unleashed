/*     */ package xyz.pixelatedw.mineminenomi.api.crew;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Crew
/*     */ {
/*     */   private String name;
/*     */   private boolean isTemporary;
/*  20 */   private List<Member> members = new ArrayList<>();
/*  21 */   private JollyRoger jollyRoger = new JollyRoger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Crew(String name, LivingEntity entity) {
/*  29 */     this(name, entity.getUniqueID(), entity.getDisplayName().getFormattedText());
/*     */   }
/*     */ 
/*     */   
/*     */   public Crew(String name, UUID capId, String username) {
/*  34 */     this.name = name;
/*  35 */     this.isTemporary = true;
/*  36 */     addMember(capId, username).setIsCaptain(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  41 */     this.name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  46 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTemporary() {
/*  51 */     return this.isTemporary;
/*     */   }
/*     */ 
/*     */   
/*     */   public Member addMember(LivingEntity entity) {
/*  56 */     return addMember(entity.getUniqueID(), entity.getDisplayName().getFormattedText());
/*     */   }
/*     */ 
/*     */   
/*     */   public Member addMember(UUID uuid, String username) {
/*  61 */     Member member = new Member(uuid, username);
/*  62 */     this.members.add(member);
/*  63 */     return member;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMember(UUID id) {
/*  68 */     Member member = getMember(id);
/*  69 */     if (member == null)
/*     */       return; 
/*  71 */     if (member.isCaptain()) {
/*     */       
/*  73 */       Optional<Member> nextCaptain = getMembers().stream().filter(mem -> !mem.isCaptain()).findFirst();
/*  74 */       if (nextCaptain.isPresent()) {
/*     */         
/*  76 */         member.setIsCaptain(false);
/*  77 */         ((Member)nextCaptain.get()).setIsCaptain(true);
/*  78 */         this.members.remove(member);
/*     */       }
/*     */       else {
/*     */         
/*  82 */         this.members.removeAll(this.members);
/*     */       } 
/*     */     } else {
/*     */       
/*  86 */       this.members.remove(member);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Member getMember(UUID id) {
/*  91 */     return this.members.stream().filter(member -> member.getUUID().equals(id)).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMember(UUID id) {
/*  96 */     return (getMember(id) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void create(World world) {
/* 101 */     this.isTemporary = false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Member getCaptain() {
/* 107 */     return this.members.stream().filter(member -> member.isCaptain()).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Member> getMembers() {
/* 112 */     return this.members;
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRoger getJollyRoger() {
/* 117 */     return this.jollyRoger;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJollyRoger(JollyRoger jr) {
/* 122 */     this.jollyRoger = jr;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT write() {
/* 127 */     CompoundNBT crewNBT = new CompoundNBT();
/* 128 */     crewNBT.putString("name", getName());
/*     */     
/* 130 */     ListNBT members = new ListNBT();
/* 131 */     for (Member member : getMembers()) {
/*     */       
/* 133 */       CompoundNBT memberNBT = new CompoundNBT();
/* 134 */       memberNBT.putUniqueId("id", member.getUUID());
/* 135 */       memberNBT.putString("username", member.getUsername());
/* 136 */       memberNBT.putBoolean("isCaptain", member.isCaptain());
/* 137 */       members.add(memberNBT);
/*     */     } 
/* 139 */     crewNBT.put("members", (INBT)members);
/*     */     
/* 141 */     CompoundNBT jollyRogerData = this.jollyRoger.write();
/* 142 */     crewNBT.put("jollyRoger", (INBT)jollyRogerData);
/*     */     
/* 144 */     return crewNBT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/* 149 */     String name = nbt.getString("name");
/* 150 */     setName(name);
/*     */     
/* 152 */     ListNBT members = nbt.getList("members", 10);
/* 153 */     for (int j = 0; j < members.size(); j++) {
/*     */       
/* 155 */       CompoundNBT memberNBT = members.getCompound(j);
/* 156 */       Member member = addMember(memberNBT.getUniqueId("id"), memberNBT.getString("username"));
/* 157 */       member.setIsCaptain(memberNBT.getBoolean("isCaptain"));
/*     */     } 
/*     */     
/* 160 */     CompoundNBT jollyRogerData = nbt.getCompound("jollyRoger");
/* 161 */     this.jollyRoger.read(jollyRogerData);
/*     */   }
/*     */   
/*     */   public Crew() {}
/*     */   
/*     */   public static class Member {
/*     */     private UUID uuid;
/*     */     private String username;
/*     */     private boolean isCaptain;
/*     */     
/*     */     public Member(LivingEntity entity) {
/* 172 */       this(entity.getUniqueID(), entity.getDisplayName().getFormattedText());
/*     */     }
/*     */ 
/*     */     
/*     */     public Member(UUID uuid, String username) {
/* 177 */       this.uuid = uuid;
/* 178 */       this.username = username;
/*     */     }
/*     */ 
/*     */     
/*     */     public Member setIsCaptain(boolean flag) {
/* 183 */       this.isCaptain = flag;
/* 184 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isCaptain() {
/* 189 */       return this.isCaptain;
/*     */     }
/*     */ 
/*     */     
/*     */     public UUID getUUID() {
/* 194 */       return this.uuid;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getUsername() {
/* 199 */       return this.username;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\crew\Crew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */