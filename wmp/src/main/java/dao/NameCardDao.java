package dao;

import java.util.List;

import beans.NameCard;

public interface NameCardDao {

	int addNameCard(NameCard jsonCard);
	
	int deleteOneCard(String cardid);
	int deleteAllCard(int uid);
	
	NameCard getNameCard(String id);
	List<NameCard> getAllNameCards(int uid);
	
	int updateNameCard(NameCard jsonCard);

	List<Integer> countCards(int uid);

}
