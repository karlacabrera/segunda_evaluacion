package com.karla.segunda_evaluacin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Karla on 06/11/2017.
 */

public class Adaptador extends ArrayAdapter<Datos> {
    private Animation anim_in,anim_out;
    Boolean   es_zoomIN;
    ImageView img;
    public Adaptador(Context context, List<Datos> objects)  {
        super(context, 0, objects);}

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Datos datos = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_imagen, parent, false);
        }
        TextView txtId = (TextView) convertView.findViewById(R.id.id);
        TextView txtDic = (TextView) convertView.findViewById(R.id.textView);
        img = (ImageView) convertView.findViewById(R.id.imag);


        es_zoomIN = true;
        anim_in = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        anim_in.setDuration(1000);
        anim_in.setFillAfter(true);

        anim_out = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
        anim_out.setDuration(1000);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(es_zoomIN) {
                    view.startAnimation(anim_in);
                }else if (!es_zoomIN){
                    view.startAnimation(anim_out);
                }
                es_zoomIN=!es_zoomIN;
            }
        });


        txtId.setText((position+1)+"");
        txtDic.setText(datos.getDic());
        img.setImageBitmap(datos.getImagen());


        return convertView;

}
}
