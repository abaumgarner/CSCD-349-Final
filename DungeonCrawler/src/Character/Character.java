package Character;

import java.util.ArrayList;
import java.util.Random;

public abstract class Character implements Comparable<Character>{

   protected String name;
   protected String race;
   protected Boolean isAlive;
   
   protected double str, dex, wis, vit, initiative;
   protected double maxHP, currentHP;
   protected int level, exp;
   protected ArrayList<Effect> effectsList = new ArrayList<Effect>();
   
   public void addEffect(Effect newEffect){
	   
	   this.effectsList.add(newEffect);
   }
   
   public void removeEffect(Effect expiredEffect){
	   
	   this.effectsList.remove(expiredEffect);
   }
   
   public String getName(){
   
      return this.name;
   
   }
   
   public double getCurrentHP(){
   
      return this.currentHP;
   }
   
   public double getMaxHP(){
   
      return this.maxHP;
   }
   
   public double getStr(){
   
      return this.str;
   }
   
   public double getDex(){
   
      return this.dex;
   }
   
   public double getWis(){
   
      return this.wis;
   }
   
   public double getVit(){
   
      return this.vit;
   }
   
   public int getLevel(){
   
      return this.level;
   }
   
   public int getExp(){
   
      return this.exp;
   }
   
   public double getInitiative()
   {
	   return this.initiative;
   }
   public void setName(String name){
   
      this.name = name;
   }
   
   public void setCurrentHP(double hp){
   
      this.currentHP = hp;
   }
   
   public void setStr(double stat){
   
      this.str = stat;
   }
   
   public void setDex(double stat){
   
      this.dex = stat;
   }
   
   public void setWis(double stat){
      
      this.wis = stat;
   }
   
   public void setVit(double stat){
   
      this.vit = stat;
   }
   
   public void setLevel(int lvl){
   
      this.level = lvl;
      
   }

   public void setExp(int exp){
   
      this.exp = exp;
   }
   
   public void setInitiative(double init){
	   
	   this.initiative = init;
   }
   
   public void calculateInitiative(){
   
      Random roll = new Random();
      
      double base = (double)roll.nextInt(5);
      
      double bonus = (double)roll.nextInt(this.level);
      
      double dexBonus = ((this.dex - (10 + ((double)this.level - 1))));
      
      if(dexBonus > 0){
      
         this.setInitiative(base + bonus + dexBonus);
      }
      
      else{
      
         this.setInitiative(base + bonus);
      }
   
   }
   public boolean calculateHitChance(Character target){
   
      //get hit chance modifiers based on attacker's stats, get miss chance based on level differences and
      //enemy stats, then calculate a hit chance (normal chance is 80%).
      
      double enemyMissFactor = getMissFactor(target);
      double attackerHitFactor = this.getHitFactor();      
      double hitChance = attackerHitFactor + enemyMissFactor;
      
      //roll to see if the attack hits.
      
      Random roll = new Random();
      double val = 0;
      val = roll.nextDouble();
      
      if(val < hitChance ){
      
         return true;
      }
      else{
      
         return false;
      }
      
   }
   
   public void basicAttack(Character target){
      
      if(this.calculateHitChance(target)){
      
         double damage = this.calculateDamage();
         System.out.println(this.getName()+" strikes the enemy "+target.getName()+" for "+damage+" damage!");
         target.setCurrentHP(target.getCurrentHP()-damage);
      
      }
   
      else{
      
         System.out.println(this.getName()+" tries to strike the enemy "+target.getName()+" but misses!");
      }
   
   }
   
   public double calculateDamage(){
      
      Random roll = new Random();
      
      double baseDamage = (double)roll.nextInt(4+this.level);
      
      double extraDamage = ((this.str - (10 + ((double)this.level - 1))))*.5;
      
      return baseDamage + extraDamage;
   
   }

   public double getHitFactor(){
   
      //base hit chance is 80%
      double normalChance = .8;
      
      //dex affects hit chance by 1.5% per stat above or below normal
      double dexFactor = ((this.dex - (10 + (this.level - 1))))*.015;
      
      return normalChance + dexFactor;
 
   }
   
   public double getMissFactor(Character target){
   
      double missFactor = 0, lvlFactor = 0;
      double lvlDifference = this.level - target.level;
      
      //dex affects dodge chance by .75% per dex above or below normal
      double dexFactor = (target.dex - (10 + (target.level - 1)))* .0075;
      
      if( (lvlDifference <= 5 && lvlDifference >= 0) || (lvlDifference >= -5 && lvlDifference <= 0) ){
      
         //level differences affect dodges/miss by 5% per level
         lvlFactor = (lvlDifference * .05);
      
      }
      
      //level difference modifiers are capped at 25%
      else if(lvlDifference > 5){
      
         lvlFactor = .25;
      }
      
      else if(lvlDifference < -5){
      
         lvlFactor = -.25;
      }
      
      missFactor = lvlFactor + dexFactor;
     
      return missFactor;
   }

   public void dies(){
	   
	   System.out.println(this.name+"has been slain!");
	   this.isAlive = false;
   }

   @Override
   public int compareTo(Character other){
	   
	   if(this.initiative < other.initiative){
		   return -1;
	   }
	   else if(this.initiative > other.initiative){
		   return 1;
	   }
	   else{
		   return 0;
	   }
   }

}