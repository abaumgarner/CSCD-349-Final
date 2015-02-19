class Monster extends Character{

   public String getName(){
   
      return this.name;
   
   }
   
   public double getHP(){
   
      return this.currentHP;
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
   
   public void setName(String name){
   
      this.name = name;
   }
   
   public void setHP(double hp){
   
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








}