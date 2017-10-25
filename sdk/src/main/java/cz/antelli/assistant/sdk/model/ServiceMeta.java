package cz.antelli.assistant.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stepan on 19.10.2017.
 */

public class ServiceMeta implements Parcelable {

    private String serviceId;
    private String serviceName;
    private String description;
    private String author;
    private List<String> sampleQuestions;
    private boolean hasSettings;


    public String getServiceId() {
        return serviceId;
    }

    public ServiceMeta setServiceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public ServiceMeta setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceMeta setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ServiceMeta setAuthor(String author) {
        this.author = author;
        return this;
    }

    public List<String> getSampleQuestions() {
        return sampleQuestions;
    }

    public ServiceMeta setSampleQuestions(List<String> sampleQuestions) {
        this.sampleQuestions = sampleQuestions;
        return this;
    }

    public ServiceMeta setSampleQuestions(String[] sampleQuestions) {
        this.sampleQuestions = new ArrayList<>(Arrays.asList(sampleQuestions));
        return this;
    }

    public ServiceMeta addSampleQuestion(String sampleQuestion) {
        if (this.sampleQuestions == null){
            this.setSampleQuestions(new ArrayList());
        }
        this.sampleQuestions.add(sampleQuestion);
        return this;
    }

    public boolean hasSettings() {
        return hasSettings;
    }

    public ServiceMeta setHasSettings(boolean hasSettings) {
        this.hasSettings = hasSettings;
        return this;
    }

    public ServiceMeta() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.serviceId);
        dest.writeString(this.serviceName);
        dest.writeString(this.description);
        dest.writeString(this.author);
        dest.writeStringList(this.sampleQuestions);
        dest.writeByte(this.hasSettings ? (byte) 1 : (byte) 0);
    }

    protected ServiceMeta(Parcel in) {
        this.serviceId = in.readString();
        this.serviceName = in.readString();
        this.description = in.readString();
        this.author = in.readString();
        this.sampleQuestions = in.createStringArrayList();
        this.hasSettings = in.readByte() != 0;
    }

    public static final Creator<ServiceMeta> CREATOR = new Creator<ServiceMeta>() {
        @Override
        public ServiceMeta createFromParcel(Parcel source) {
            return new ServiceMeta(source);
        }

        @Override
        public ServiceMeta[] newArray(int size) {
            return new ServiceMeta[size];
        }
    };
}
