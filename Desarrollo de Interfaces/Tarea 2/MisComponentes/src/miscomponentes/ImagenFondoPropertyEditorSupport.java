/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miscomponentes;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

public class ImagenFondoPropertyEditorSupport extends PropertyEditorSupport {

    private ImagenFondoPanel imagenFondoPanel = new ImagenFondoPanel();

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return imagenFondoPanel;
    }
    
        @Override
    public String getJavaInitializationString() {
        ImagenFondo img = (ImagenFondo) getValue();
        String r1 = img.getRutaInicial().getAbsolutePath().replace("\\", "/");
        String r2 = img.getRutaFinal().getAbsolutePath().replace("\\", "/");
        return "new miscomponentes.ImagenFondo(new java.io.File(\"" + r1 + "\"), new java.io.File(\"" + r2 + "\"), " + img.getOpacidadInicial() + "f, " + img.getOpacidadFinal() + "f)";
    }

    @Override
    public Object getValue() {
        return imagenFondoPanel.getSelectedValue();
    }


}
