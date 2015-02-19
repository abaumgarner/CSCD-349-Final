package Game_Session;

import java.util.ArrayList;
import Character.Character;

public class Party
{
   private ArrayList<Character> partyMembers;
   
   public Party()
   {
      partyMembers = new ArrayList<Character>();
   }//end constructor
   
   public void addMember(Character newMember)
   {
      partyMembers.add(newMember);
   }//end addPartyMember
   
   public void removeMember(Character recentlyDeceased)
   {
      partyMembers.remove(recentlyDeceased);
   }//end removePartyMember
   
   public boolean isDefeated() 
   {
      if(partyMembers.size() == 0)
         return true;
      
      return false;
   }//end partyDefeated
   
   public void partyStats()
   {
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("\nPARTY STATS:\n");
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
     
      for(Character character: partyMembers)
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