
package Retrofit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusInfo implements Serializable, Parcelable
{

    @SerializedName("Result")
    @Expose
    private List<Result> result = new ArrayList<Result>();
    public final static Creator<BusInfo> CREATOR = new Creator<BusInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BusInfo createFromParcel(Parcel in) {
            return new BusInfo(in);
        }

        public BusInfo[] newArray(int size) {
            return (new BusInfo[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3309820479541128839L;

    protected BusInfo(Parcel in) {
        in.readList(this.result, (Retrofit.Result.class.getClassLoader()));
    }

    public BusInfo() {
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(result);
    }

    public int describeContents() {
        return  0;
    }

}
