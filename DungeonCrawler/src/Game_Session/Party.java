package Game_Session;

import java.util.ArrayList;
import Character.GameCharacter;

public class Party
{
   private ArrayList<GameCharacter> partyMembers;
   
   public Party()
   {
      partyMembers = new ArrayList<GameCharacter>();
   }//end constructor
   
   public void addMember(GameCharacter newMember)
   {
      partyMembers.add(newMember);
   }//end addPartyMember
   
   public void removeMember(GameCharacter recentlyDeceased)
   {
      partyMembers.remove(recentlyDeceased);
   }//end removePartyMember
   
   public boolean isDefeated() 
   {
      if(partyMembers.size() == 0)
         return true;
      
      return false;
   }//end partyDefeated
   
   protected ArrayList<GameCharacter> getPartyMembers()
   {
	   return partyMembers;
   }//end getPartyMembers
   
   public void partyStats()
   {
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("\nPARTY STATS:\n");
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
     
      for(GameCharacter character: partyMembers)
      {
         System.out.println("\n============================");
         System.out.println("NAME: " + character.getName());
			System.out.println("RACE: " + character.getRace());
         System.out.println("CLASS: " + character.getProfession());
         System.out.println("LVL: " + character.getLevel());
         System.out.println("============================");
         System.out.println("EXP: " + character.getExp());
         System.out.println("INITIATIVE: " + character.getInitiative());
         System.out.println("HP: " + character.getCurrentHP() + "/" + character.getMaxHP());
         System.out.println("STR: " + character.getStr());
         System.out.println("DEX: " + character.getDex());
         System.out.println("WIS: " + character.getWis());
         System.out.println("VIT: " + character.getVit());
         System.out.println("-----------------------------\n");
         
      }//end for
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
   }//end partyStats
}//end class