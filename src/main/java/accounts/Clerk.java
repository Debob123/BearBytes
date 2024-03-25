package accounts;

public class Clerk extends Account {
    private String profileInfo;

    public Clerk(String username, String password) {
        super(username, password);
    }

    public void setProfileInfo(String info){
        profileInfo = info;
    }

    public String getProfileInfo(){
        return profileInfo;
    }
}
