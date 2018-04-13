/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author u27brvz14
 */
public class Naimesql {
    private String osd_t;
    private String osd_c;
    private String osd_r;
    private String naim;
    private String nizv;

    /**
     * @return the osd_t
     */
    public String getOsd_t() {
        return osd_t;
    }

    /**
     * @return the osd_c
     */
    public String getOsd_c() {
        return osd_c;
    }

    /**
     * @return the osd_r
     */
    public String getOsd_r() {
        return osd_r;
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
    public void setOsd_t(String osd_t) {
        this.osd_t = osd_t;
    }

    /**
     * @param osd_c the osd_c to set
     */
    public void setOsd_c(String osd_c) {
        this.osd_c = osd_c;
    }

    /**
     * @param osd_r the osd_r to set
     */
    public void setOsd_r(String osd_r) {
        this.osd_r = osd_r;
    }

    /**
     * @param naim the naim to set
     */
    public void setNaim(String naim) {
        this.naim = naim;
    }

    /**
     * @param nizv the nizv to set
     */
    public void setNizv(String nizv) {
        this.nizv = nizv;
    }
    
}





class Test{
    public MenuResolve menu;
    public String selectMenuPath(String userName){
        
        if(true)
            menu = new KizMenu();
        else
            menu = new MainMenu();
        
         return menu.getMenuPath();
    }
}
  //  private  selectotr
abstract class MenuResolve{
     public abstract String getMenuPath();
}
class KizMenu extends MenuResolve{
    public String getMenuPath()
    {
        return "./Kizmenu.jsp";
    }
}
class MainMenu extends MenuResolve{
    public String getMenuPath()
    {
        return "./menu.jsp";
    }
}