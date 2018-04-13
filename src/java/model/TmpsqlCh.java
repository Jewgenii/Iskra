/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author u27brvz18
 */
public class TmpsqlCh {
   /*character varying(1) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-тип*/
    private String osdch_t;
    /*character varying(4) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-індекс*/
    private String osdch_c;
    /*character varying(20) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-залишок*/
    private String osdch_r;
    /*Конкатенация osdch_c||osdch_r*/
    private String osdch;
    
    /*character varying(1) NOT NULL, -- Одиниця складова(деталь).Позначення "Куда": ОСД-тип*/
    private String osdk_t;
    /*character varying(4) NOT NULL, -- Одиниця складова(деталь).Позначення "Куда": ОСД-індекс*/
    private String osdk_c;
    /*character varying(20) NOT NULL, -- Одиниця складова(деталь).Позначення "Куда": ОСД-залишок*/
    private String osdk_r;
    /*Конкатенация osdk_c||osdk_r*/
    private String osdk;
    
    /*character varying[], --Технологічний маршрут споживання */
    private String cp;
    private String nc_ch;
    private String nc_k;
    /*date NOT NULL, -- Строк впровадження повідомлення*/
    private Date svi;
    /*character varying(10), --Номер повідомлення*/
    private String nizv;
    /* naim character varying(80), -- Найменування*/
    private String naim_ch;
    private String naim_k;
    /*character varying(30) DEFAULT "current_user"(), -- Користувач*/
    private String user;
    /*date DEFAULT ('now'::text)::date, -- Дата завантаження*/
    private Date datez;
    /*character varying(19) DEFAULT inet_client_addr(), -- Мережева адреса користувача*/
    private String user_ip;  
    
    private String osdch_cnt;
    private String osdk_cnt; 

    /**
     * @return the osdch_t
     */
    public String getOsdch_t() {
        return osdch_t;
    }

    /**
     * @param osdch_t the osdch_t to set
     */
    public void setOsdch_t(String osdch_t) {
        this.osdch_t = osdch_t;
    }

    /**
     * @return the osdch_c
     */
    public String getOsdch_c() {
        return osdch_c;
    }

    /**
     * @param osdch_c the osdch_c to set
     */
    public void setOsdch_c(String osdch_c) {
        this.osdch_c = osdch_c;
    }

    /**
     * @return the osdch_r
     */
    public String getOsdch_r() {
        return osdch_r;
    }

    /**
     * @param osdch_r the osdch_r to set
     */
    public void setOsdch_r(String osdch_r) {
        this.osdch_r = osdch_r;
    }

    /**
     * @return the osdch
     */
    public String getOsdch() {
        return osdch;
    }

    /**
     * @param osdch the osdch to set
     */
    public void setOsdch(String osdch) {
        this.osdch = osdch;
    }

    /**
     * @return the osdk_t
     */
    public String getOsdk_t() {
        return osdk_t;
    }

    /**
     * @param osdk_t the osdk_t to set
     */
    public void setOsdk_t(String osdk_t) {
        this.osdk_t = osdk_t;
    }

    /**
     * @return the osdk_c
     */
    public String getOsdk_c() {
        return osdk_c;
    }

    /**
     * @param osdk_c the osdk_c to set
     */
    public void setOsdk_c(String osdk_c) {
        this.osdk_c = osdk_c;
    }

    /**
     * @return the osdk_r
     */
    public String getOsdk_r() {
        return osdk_r;
    }

    /**
     * @param osdk_r the osdk_r to set
     */
    public void setOsdk_r(String osdk_r) {
        this.osdk_r = osdk_r;
    }

    /**
     * @return the osdk
     */
    public String getOsdk() {
        return osdk;
    }

    /**
     * @param osdk the osdk to set
     */
    public void setOsdk(String osdk) {
        this.osdk = osdk;
    }

    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * @return the nc_ch
     */
    public String getNc_ch() {
        return nc_ch;
    }

    /**
     * @param nc_ch the nc_ch to set
     */
    public void setNc_ch(String nc_ch) {
        this.nc_ch = nc_ch;
    }

    /**
     * @return the nc_k
     */
    public String getNc_k() {
        return nc_k;
    }

    /**
     * @param nc_k the nc_k to set
     */
    public void setNc_k(String nc_k) {
        this.nc_k = nc_k;
    }

    /**
     * @return the svi
     */
    public Date getSvi() {
        return svi;
    }

    /**
     * @param svi the svi to set
     */
    public void setSvi(Date svi) {
        this.svi = svi;
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
     * @return the naim_ch
     */
    public String getNaim_ch() {
        return naim_ch;
    }

    /**
     * @param naim_ch the naim_ch to set
     */
    public void setNaim_ch(String naim_ch) {
        this.naim_ch = naim_ch;
    }

    /**
     * @return the naim_k
     */
    public String getNaim_k() {
        return naim_k;
    }

    /**
     * @param naim_k the naim_k to set
     */
    public void setNaim_k(String naim_k) {
        this.naim_k = naim_k;
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
        return "TmpsqlCh{" + "osdch_t=" + osdch_t + ", osdch_c=" + osdch_c + ", osdch_r=" + osdch_r + ", osdch=" + osdch + ", osdk_t=" + osdk_t + ", osdk_c=" + osdk_c + ", osdk_r=" + osdk_r + ", osdk=" + osdk + ", cp=" + cp + ", nc_ch=" + nc_ch + ", nc_k=" + nc_k + ", svi=" + svi + ", nizv=" + nizv + ", naim_ch=" + naim_ch + ", naim_k=" + naim_k + ", user=" + user + ", datez=" + datez + ", user_ip=" + user_ip + '}';
    }

    /**
     * @return the osdch_cnt
     */
    public String getOsdch_cnt() {
        return osdch_cnt;
    }

    /**
     * @param osdch_cnt the osdch_cnt to set
     */
    public void setOsdch_cnt(String osdch_cnt) {
        this.osdch_cnt = osdch_cnt;
    }

    /**
     * @return the osdk_cnt
     */
    public String getOsdk_cnt() {
        return osdk_cnt;
    }

    /**
     * @param osdk_cnt the osdk_cnt to set
     */
    public void setOsdk_cnt(String osdk_cnt) {
        this.osdk_cnt = osdk_cnt;
    }
    
    
}
