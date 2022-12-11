package vincentSenjayaJSleepDN.jsleep_android.model;

public class Account extends Serializable{
    public String name;
    public String password;
    public Renter renter;
    public String email;
    public double balance;

    public String toString(){
        return "Account{"+
                "balance="+ balance+", email='" + email+'\'' + ", name='"+ name + '\'' + ", password='"
                + password +'\'' + ", renter=" + renter + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
