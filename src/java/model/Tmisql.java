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
public class Tmisql {

    /*character varying(1) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-тип*/
    private String osdch_t;
    /*character varying(4) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-індекс*/
    private String osdch_c;
    /*character varying(20) NOT NULL, -- Одиниця складова(деталь).Позначення "Що": ОСД-залишок*/
    private String osdch_r;
    /*Конкатенация osdch_c||osdch_r*/
    private String osdch;
    /*character varying[], --Технологічній маршрут вигитовлення. Масив */
    private String nc;
    /*date NOT NULL, -- Строк впровадження повідомлення*/
    private Date svi;
    /*character varying(10), --Номер повідомлення*/
    private String nizv;
    /* naim character varying(80), -- Найменування*/
    private String naim;
    /*character varying(30) DEFAULT "current_user"(), -- Користувач*/
    private String user;
    /*date DEFAULT ('now'::text)::date, -- Дата завантаження*/
    private Date datez;
    /*character varying(19) DEFAULT inet_client_addr(), -- Мережева адреса користувача*/
    private String user_ip;

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
     * @return the ncm
     */
    public String getNc() {
        return nc;
    }

    /**
     * @param nc the ncm to set
     */
    public void setNc(String nc) {
        this.nc = nc;
    }

    @Override
    public String toString() {
        return "Tmisql{" + "osdch_t=" + osdch_t + ", osdch_c=" + osdch_c + ", osdch_r=" + osdch_r + ", osdch=" + osdch + ", nc=" + nc + ", svi=" + svi + ", nizv=" + nizv + ", naim=" + naim + ", user=" + user + ", datez=" + datez + ", user_ip=" + user_ip + '}';
    }

}
