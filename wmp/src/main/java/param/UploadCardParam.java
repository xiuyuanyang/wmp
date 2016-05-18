package param;

import java.util.List;


public class UploadCardParam {

	private String token;
	private List<NameCardParam> cards;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<NameCardParam> getCards() {
		return cards;
	}
	public void setCards(List<NameCardParam> cards) {
		this.cards = cards;
	}
	
}
