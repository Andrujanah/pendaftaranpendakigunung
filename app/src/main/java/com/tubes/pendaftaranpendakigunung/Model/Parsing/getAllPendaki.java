package com.tubes.pendaftaranpendakigunung.Model.Parsing;
import com.google.gson.annotations.SerializedName;
import com.tubes.pendaftaranpendakigunung.Model.Pendaki;
import java.util.List;

public class getAllPendaki {

    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("pendaki")
    private List<Pendaki> pendaki;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Pendaki> getPendaki() {
        return pendaki;
    }

    public void setPendaki(List<Pendaki> pendaki) {
        this.pendaki = pendaki;
    }
}
