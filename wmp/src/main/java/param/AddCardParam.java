package param;

import beans.NameCard;

public class AddCardParam {

	private String token;
	private String id;
	private int uid;
	private String name;
	private String photolink;
	private String company;
	private String title;
	private String email;
	private String phone;
	private String fax;
	private int selected;
	private String mobile;
	private String address;
	private String website;
	private int seriesNumber;
	private int language;
	private int themeType;

	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhotolink() {
		return photolink;
	}


	public void setPhotolink(String photolink) {
		this.photolink = photolink;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public int getSelected() {
		return selected;
	}


	public void setSelected(int selected) {
		this.selected = selected;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public int getSeriesNumber() {
		return seriesNumber;
	}


	public void setSeriesNumber(int seriesNumber) {
		this.seriesNumber = seriesNumber;
	}


	public int getLanguage() {
		return language;
	}


	public void setLanguage(int language) {
		this.language = language;
	}


	public int getThemeType() {
		return themeType;
	}


	public void setThemeType(int themeType) {
		this.themeType = themeType;
	}


	public NameCard toNameCard(){		
		NameCard nc = new NameCard();
		nc.setAddress(this.getAddress());
		nc.setCompany(this.getCompany());
		nc.setEmail(this.getEmail());
		nc.setFax(this.getFax());
		nc.setId(this.getId());
		nc.setLanguage(this.getLanguage());
		nc.setMobile(this.getMobile());
		nc.setName(this.getName());
		nc.setPhone(this.getPhone());
		nc.setPhotolink(this.getPhotolink());
		nc.setSelected(this.getSelected());
		nc.setSeriesNumber(this.getSeriesNumber());
		nc.setThemeType(this.getThemeType());
		nc.setTitle(this.getTitle());
		nc.setUid(this.getUid());
		nc.setWebsite(this.getWebsite());
		return  nc;
	}
	
}
