
package Retrofit;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private long id;
    @SerializedName("Busid")
    @Expose
    private String busid;
    @SerializedName("LAT")
    @Expose
    private String lAT;
    @SerializedName("LONG")
    @Expose
    private String lONG;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("speed")
    @Expose
    private String speed;
    @SerializedName("IGN")
    @Expose
    private String iGN;
    @SerializedName("Rfidno")
    @Expose
    private Object rfidno;
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3227810076164206251L;

    protected Result(Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.busid = ((String) in.readValue((String.class.getClassLoader())));
        this.lAT = ((String) in.readValue((String.class.getClassLoader())));
        this.lONG = ((String) in.readValue((String.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        this.speed = ((String) in.readValue((String.class.getClassLoader())));
        this.iGN = ((String) in.readValue((String.class.getClassLoader())));
        this.rfidno = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Result() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBusid() {
        return busid;
    }

    public void setBusid(String busid) {
        this.busid = busid;
    }

    public String getLAT() {
        return lAT;
    }

    public void setLAT(String lAT) {
        this.lAT = lAT;
    }

    public String getLONG() {
        return lONG;
    }

    public void setLONG(String lONG) {
        this.lONG = lONG;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getIGN() {
        return iGN;
    }

    public void setIGN(String iGN) {
        this.iGN = iGN;
    }

    public Object getRfidno() {
        return rfidno;
    }

    public void setRfidno(Object rfidno) {
        this.rfidno = rfidno;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(busid);
        dest.writeValue(lAT);
        dest.writeValue(lONG);
        dest.writeValue(time);
        dest.writeValue(speed);
        dest.writeValue(iGN);
        dest.writeValue(rfidno);
    }

    public int describeContents() {
        return  0;
    }

}
