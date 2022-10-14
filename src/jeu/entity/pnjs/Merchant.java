package jeu.entity.pnjs;

import java.util.*;
import jeu.entity.*;
import jeu.entity.items.*;
import jeu.exceptions.*;
import jeu.util.*;

public class Merchant extends PNJ{

  // The list of item in the market
  protected List<Item> market;
  // The clue for the quest
  protected String clue;

  /*
  * Constructor to build a Merchant
  * @param pStamina Stamina / number of actions
  * @param pName The name of the merchant
  * @param pMarket The Market
  */
  public Merchant(int pStamina, String pName, List<Item> pMarket){
    super(pStamina,pName,"\uD83D\uDCB0");
    this.immortal = true;
    this.market = pMarket;
    List<String> buy = new ArrayList<String>();
    List<String> sell = new ArrayList<String>();
    List<String> salut = new ArrayList<String>();
    List<String> ok = new ArrayList<String>();
    List<String> goodbye = new ArrayList<String>();
    buy.add("Merci pour votre achat!");
    buy.add("Merci de votre soutien!");
    sell.add("Merci pour cette superbe oeuvre d'art");
    sell.add("Merci pour votre confiance");
    salut.add("Bien le bonjour étranger!");
    salut.add("Salutations Voyageur.");
    goodbye.add("Merci, Au revoir!");
    goodbye.add("Merci de votre venu!");
    ok.add("D'accord Monsieur.");
    this.listDialogues.put(Action.SALUTATION,salut);
    this.listDialogues.put(Action.OKAY,ok);
    this.listDialogues.put(Action.GOODBYE,goodbye);
    this.listDialogues.put(Action.SELL,sell);
    this.listDialogues.put(Action.BUY,buy);

  }

  /*
  * @return List<Item> Return the market
  */
  public List<Item> getMarket() {
    return this.market;
  }

  public void setClue(String str){
    this.clue=str;
  }

  public String getClue(){
    return this.clue;
  }

  /*
  * Method to add an object in the market
  * @param i an item
  */
  public void addObjectInMarket(Item i) {
    this.market.add(i);
  }

  
  public String talkTo(Action act){
    if(act==Action.SALUTATION){
      return this.getRandomTalk(Action.SALUTATION);
    }
    if(act==Action.OKAY){
      return this.getRandomTalk(Action.OKAY);
    }
    if(act==Action.GOODBYE){
      return this.getRandomTalk(Action.GOODBYE);
    }
    if(act==Action.SELL){
      return this.getRandomTalk(Action.SELL);
    }
    if(act==Action.BUY){
      return this.getRandomTalk(Action.BUY);
    }
    
    return "";
  }

  /*
  * The Merchant will sell an item
  * @param hero , the player
  * @param item , an item to add to the hero inventory
  */
  public void sellItem(Hero hero,Item item)throws UnknownItemException{
    if(this.market.contains(item)){
      this.market.remove(item);
      hero.substractMoney(item.getMarketValue());
      item.setMarketValue(item.getMarketValue()/2);
      hero.addItem(item);
    }
    else{
      throw new UnknownItemException("I didn't hear correctly, i have a bad hearing, can you please repeat the thing you want in my Shop?");
    }
  }

  /*
  * The merchant will buyback an item
  * @param hero, the player
  * @param item , the item to be buyback
  */
  public void buyBackItem(Hero hero,Item item)throws UnknownItemException{
    if(hero.isInInventory(item)){
      this.market.add(item);
      hero.addMoney(item.getMarketValue());
      item.setMarketValue(item.getMarketValue()*2);
      hero.removeItem(item);
    }
    else{
      throw new UnknownItemException("You coward, want to scam an old man!!");
    }
  }

  //Not done bonus content
  public void scam(Item item)throws UnknownItemException{
    if(this.market.contains(item)){
      
      item.setMarketValue(item.getMarketValue()*2);
      this.market.remove(item);
    }
    else{
      throw new UnknownItemException("I didn't hear correctly, i have a bad hearing, can you please repeat the thing you want in my Shop?");
    }
  }

}
