/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesGASS.jsf;

/**
 *
 * @author root
 */
public class paginaWorker {

    private String pagina;
    private boolean checkBox;

    public paginaWorker(String pagina, boolean checkBox) {
        this.pagina = pagina;
        this.checkBox = checkBox;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public boolean getCheckBox() {
        return this.checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
