package com.example.cyberthon;

public class sosmodal {
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public sosmodal(String remark, String no, String reason,String email,String cor) {
        this.remark = remark;
        this.no = no;
        Reason = reason;
        this.email=email;
        this.cor=cor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String remark,no,Reason,email,cor;

    public String getCor() {
        return cor;
    }

    public sosmodal(String remark, String email, String cor) {
        this.remark = remark;
        this.email = email;
        this.cor = cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public sosmodal() {
    }
}
