package com.example.antniofernandes.sialdroi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

public class janelaacessorios extends AppCompatDialogFragment {
    Spinner acessorio1;
    Spinner acessorio2;
    private ExampleDialogListener1 listener2;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.acessorios, null);
        builder.setView(view)
                .setTitle("Selecione 2")

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username1 = acessorio1.getSelectedItem().toString();
                        String password1 = acessorio2.getSelectedItem().toString();
                        listener2.applyTexts2(username1,password1);
                    }
                });
        acessorio1 = view.findViewById(R.id.spinner);
        acessorio2 = view.findViewById(R.id.spinner4);
        return builder.create();
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener2 = (ExampleDialogListener1) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    public interface ExampleDialogListener1 {
        void applyTexts2 (String username1,String password1);
    }
}
