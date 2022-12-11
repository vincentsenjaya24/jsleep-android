package vincentSenjayaJSleepDN.jsleep_android.model;
/**
 *
 * @author Vincent Senjaya
 * @version 1.0
 * @since 11 Desember 2021
 */
import java.util.ArrayList;
import java.util.Date;

public class Room {
    public int accountId;
    public String name;
    public ArrayList<Date> booked;
    public String address;
    public Price price;
    public City city;
    public int size;
    public BedType bedType;
    public Facility facility;
}
