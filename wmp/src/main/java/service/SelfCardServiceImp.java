package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beans.NameCard;
import dao.SelfCardDao;
@Transactional
@Service("selfCardService")
public class SelfCardServiceImp implements SelfCardService{

	@Autowired
	private SelfCardDao selfCardDao;
	
	public List<NameCard> getAllCards(int uid) {
		
		return selfCardDao.getAllNameCards(uid);
	}

	public boolean addOneCard(NameCard jsonCard) {
		int r=0;
		try{
			r = selfCardDao.addNameCard(jsonCard);
		}
		catch(org.springframework.dao.DuplicateKeyException e){
			e.printStackTrace();
		}
		System.out.println(r);
		return r>0?true:false;
	}

	public List<Integer> countCards(int uid) {
		
		return selfCardDao.countCards(uid);
	}

	public boolean deleteAllCard(int uid) {
		int r = selfCardDao.deleteAllCard(uid);
		System.out.println(r);		
		return r>0?true:false;
	}

	public boolean deleteOneCard(String cardid) {
		int r = selfCardDao.deleteOneCard(cardid);
		System.out.println(r);		
		return r>0?true:false;
	}

	public boolean deleteCards(List<String> cardids) {
		int r=0,t;
		for(String id : cardids) {
			t = selfCardDao.deleteOneCard(id);
			r += t;
		}
		System.out.println(r);
		return r>0?true:false;
	}

	public boolean modifyOneCard(NameCard jsonCard) {
		int r = selfCardDao.updateNameCard(jsonCard);
		System.out.println(r);	
		return r>0?true:false;
	}

	public boolean uploadCards(List<NameCard> jsonCards) {
		int r=0;
		for(NameCard nc : jsonCards) {
			if( modifyOneCard(nc) | addOneCard(nc) ){
				r++;
			}
		}
		System.out.println(r);
		return r>0?true:false;
	}

}
