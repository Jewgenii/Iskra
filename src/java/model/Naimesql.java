/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;

/**
 *
 * @author u27brvz14
 */
public class Naimesql {
    private String osd;
    private String naim;
    private String nizv;

    /**
     * @return the osd_t
     */
    public String getOsd() {
        return osd;
    }

    /**
     * @return the naim
     */
    public String getNaim() {
        return naim;
    }

    /**
     * @return the nizv
     */
    public String getNizv() {
        return nizv;
    }

    /**
     * @param osd_t the osd_t to set
     */
    public void setOsd(String osd) {
        this.osd = osd;
    }

    public void setNaim(String naim) {
        this.naim = naim;
    }

    /**
     * @param nizv the nizv to set
     */
    public void setNizv(String nizv) {
        this.nizv = nizv;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
}