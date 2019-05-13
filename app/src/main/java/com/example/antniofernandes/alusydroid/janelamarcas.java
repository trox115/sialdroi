package com.example.antniofernandes.alusydroid;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

public class janelamarcas extends AppCompatDialogFragment {
   private Spinner marca1;
   private Spinner marca2;
    private Spinner marca3;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.marcas, null);
        builder.setView(view)
                .setTitle("Selecione 3 Marcas")

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    String username = marca1.getSelectedItem().toString();
                    String password = marca2.getSelectedItem().toString();
                        String spinner = marca3.getSelectedItem().toString();
                        listener.applyTexts(username, password, spinner);
                    }
                });
        marca1 = view.findViewById(R.id.spinner6);
        marca2 = view.findViewById(R.id.spinner7);
        marca3 = view.findViewById(R.id.spinner8);
        return builder.create();
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    public interface ExampleDialogListener {
        void applyTexts(String username,String password, String spinner);
    }
}
