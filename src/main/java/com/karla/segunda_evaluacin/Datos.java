package com.karla.segunda_evaluacin;

import android.graphics.Bitmap;

/**
 * Created by Karla on 06/11/2017.
 */

public class Datos {

    private Bitmap Imagen;
    private String Dirección;

    public Datos(String dirección, Bitmap imagen ) {
        Imagen = imagen;
        Dirección = dirección;
    }
    public Datos (){

    }

    public Bitmap getImagen() {
        return Imagen;
    }

    public void getImagen(Bitmap imagen) {
        Imagen = imagen;
    }

    public String getDic() {
        return Dirección;
    }

    public void getDic(String dirección) {
        Dirección = dirección;
    }
}
