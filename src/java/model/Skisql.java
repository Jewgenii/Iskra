/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author u27brvz17
 */
public class Skisql {

    private String nzak;
    private String osd_t;
    private String osd_c;
    private String osd_r;
    private String kiz;
    private int pri;
    private String nvp;
    private String naim;
    private String nizv;
    private String user;
    private Date datez;
    //DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    //String text = df.format(datez);
    private String user_ip;

    /**
     * @return the nzak
     */
    public String getNzak() {
        return nzak;
    }

    /**
     * @param nzak the nzak to set
     */
    public void setNzak(String nzak) {
        this.nzak = nzak;
    }

    /**
     * @return the osd_t
     */
    public String getOsd_t() {
        return osd_t;
    }

    /**
     * @param osd_t the osd_t to set
     */
    public void setOsd_t(String osd_t) {
        this.osd_t = osd_t;
    }

    /**
     * @return the osd_c
     */
    public String getOsd_c() {
        return osd_c;
    }

    /**
     * @param osd_c the osd_c to set
     */
    public void setOsd_c(String osd_c) {
        this.osd_c = osd_c;
    }

    /**
     * @return the osd_r
     */
    public String getOsd_r() {
        return osd_r;
    }

    /**
     * @param osd_r the osd_r to set
     */
    public void setOsd_r(String osd_r) {
        this.osd_r = osd_r;
    }

    /**
     * @return the kiz
     */
    public String getKiz() {
        return kiz;
    }

    /**
     * @param kiz the kiz to set
     */
    public void setKiz(String kiz) {
        this.kiz = kiz;
    }

    /**
     * @return the pri
     */
    public int getPri() {
        return pri;
    }

    /**
     * @param pri the pri to set
     */
    public void setPri(int pri) {
        this.pri = pri;
    }

    /**
     * @return the nvp
     */
    public String getNvp() {
        return nvp;
    }

    /**
     * @param nvp the nvp to set
     */
    public void setNvp(String nvp) {
        this.nvp = nvp;
    }

    /**
     * @return the naim
     */
    public String getNaim() {
        return naim;
    }

    /**
     * @param naim the naim to set
     */
    public void setNaim(String naim) {
        this.naim = naim;
    }

    /**
     * @return the nizv
     */
    public String getNizv() {
        return nizv;
    }

    /**
     * @param nizv the nizv to set
     */
    public void setNizv(String nizv) {
        this.nizv = nizv;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the datez
     */
    public Date getDatez() {
        return datez;
    }

    /**
     * @param datez the datez to set
     */
    public void setDatez(Date datez) {
        this.datez = datez;
    }

    /**
     * @return the user_ip
     */
    public String getUser_ip() {
        return user_ip;
    }

    /**
     * @param user_ip the user_ip to set
     */
    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    @Override
    public String toString() {
        return "Skisql{" + "nzak=" + nzak
                + ", osd_t=" + osd_t + ", osd_c=" + osd_c
                + ", osd_r=" + osd_r + ", kiz=" + kiz
                + ", pri=" + pri + ", nvp=" + nvp
                + ", naim=" + naim + ", nizv=" + nizv
                + ", user=" + user + ", datez=" + datez
                + ", user_ip=" + user_ip + '}';
    }

}
