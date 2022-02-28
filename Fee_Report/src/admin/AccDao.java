package admin;

public interface AccDao{
	boolean adminLogin(String username, String pass);
	void verifyAdmin();
	void adminSection();
    void addAccountant();
	void viewAccountant();
	

}
