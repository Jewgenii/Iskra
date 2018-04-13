/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Sergey Nikonenko
 */
public class Vp44150sql {

    private String osdch_t;
    /*character varying(1) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-тип*/
    private String osdch_c;
    /*character varying(4) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-індекс*/
    private String osdch_r;
    /*character varying(20) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-залишок*/
    private String osdch;
    private String osdk_t;
    /*character varying(1) NOT NULL, -- Одиниця складова(деталь).Позначення "Куди": ОСД-тип*/
    private String osdk_c;
    /*character varying(4) NOT NULL, -- Одиниця складова(деталь).Позначення "Куди": ОСД-індекс*/
    private String osdk_r;
    /*character varying(20) NOT NULL, -- Одиниця складова(деталь).Позначення "Куди": ОСД-залишок*/
    private String osdk;
    private String kiz;
    /*character varying(4) NOT NULL, -- Код виробу*/
    private Date svi;
    /*date NOT NULL, -- Строк впровадження повідомлення*/
    private String kol;
    /*numeric(19,8), -- Кількість*/
    private String koliz;
    /*numeric(19,8), -- Кількість у виробі*/
    private String user;
    /*character varying(30) DEFAULT "current_user"(), -- Користувач*/
    private Date datez;
    /*date DEFAULT ('now'::text)::date, -- Дата завантаження*/
    private String user_ip;
    /*character varying(19) DEFAULT inet_client_addr(), -- Мережева адреса користувача*/
    private String naim;
    /* naim character varying(80), -- Найменування*/
    private String naimk;
    
    private String nc;
    /*nc character varying(42), -- Технологічний маршрут виготовлення*/
    
    private String ncK;
    
    private String cp;
    /*cp character varying(42), -- Технологічний маршрут споживання*/
    
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
     * @return the kol
     */
    public String getKol() {
        return kol;
    }

    /**
     * @param kol the kol to set
     */
    public void setKol(String kol) {
        this.kol = kol;
    }

    /**
     * @return the koliz
     */
    public String getKoliz() {
        return koliz;
    }

    /**
     * @param koliz the koliz to set
     */
    public void setKoliz(String koliz) {
        this.koliz = koliz;
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
     * @return the nc
     */
    public String getNc() {
        return nc;
    }

    /**
     * @param nc the nc to set
     */
    public void setNc(String nc) {
        this.nc = nc;
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
     * @return the naimk
     */
    public String getNaimk() {
        return naimk;
    }

    /**
     * @param naimk the naimk to set
     */
    public void setNaimk(String naimk) {
        this.naimk = naimk;
    }

    /**
     * @return the ncK
     */
    public String getNcK() {
        return ncK;
    }

    /**
     * @param ncK the ncK to set
     */
    public void setNcK(String ncK) {
        this.ncK = ncK;
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
