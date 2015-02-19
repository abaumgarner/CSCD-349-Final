package Character;

public class Warrior extends Hero{

   public Warrior(){
  
      this.name = "NULL The Conqueror";
      this.profession = "Warrior";
      this.race = "Null'thraki";
      
      this.level = 1;
      this.exp = 0;
   
      this.str = 12;
      this.dex = 8;
      this.wis = 8;
      this.vit = 12;
      this.maxHP = calculateMaxHP();
      this.currentHP = maxHP;
      
      
   }
   
   public double calculateMaxHP(){
      
      return 10 + this.vit + (5 * this.level);
   
   }
   
   public Cripple cripple(Character target){
   
      Cripple cripple = new Cripple();
      cripple.apply(target);
   
      return cripple;
   }





}