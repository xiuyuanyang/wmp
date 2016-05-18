package service;

import java.util.List;

import beans.NameCard;

public interface SelfCardService {
	
	List<NameCard> getAllCards(int uid);
	
	boolean addOneCard(NameCard jsonCard);
	
	List<Integer> countCards(int uid);
	
	boolean deleteAllCard(int uid);
	
	boolean deleteOneCard(String cardid);
	
	boolean deleteCards(List<String> cardids);
	
	boolean modifyOneCard(NameCard jsonCard);
	
	boolean uploadCards(List<NameCard> jsonCards);
}
