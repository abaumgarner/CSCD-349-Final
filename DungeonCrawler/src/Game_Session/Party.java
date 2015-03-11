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
   
   protected boolean findMember(String name)
   {
	   for(GameCharacter partyMember: partyMembers)
	   {
		  if(partyMember.getName().equalsIgnoreCase(name))
			  return true;
	   }//end for
	   System.out.println("'" + name +"' is not a party member.");
	   return false;
   }//end findMember
   
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
         System.out.println("============================");
         System.out.println("LVL: " + character.getStats().getLevel());
         System.out.println("EXP: " + character.getStats().getExp());
         System.out.println("INITIATIVE: " + character.getStats().getInitiative());
         System.out.println("HP: " + character.getStats().getCurrentHP() + "/" + character.getStats().getMaxHP());
         System.out.println("STR: " + character.getStats().getStr());
         System.out.println("DEX: " + character.getStats().getDex());
         System.out.println("WIS: " + character.getStats().getWis());
         System.out.println("VIT: " + character.getStats().getVit());
         System.out.println("-----------------------------\n");
         
      }//end for
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
   }//end partyStats
}//end class