package control;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gomes.faunars_1.R;

import dao.EspecieDAO;

public class IdentifyFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    public static final String TAG = "IdentifyFragment";

    private ImageButton btnimg;
    private ImageButton btnhelp;
    private Button btnidentify;
    private ImageView img;
    private Spinner spnrclasse;
    private Spinner spnrc2;
    private Spinner spnrc3;
    private Spinner spnrc4;
    private Spinner spnrc5;
    private TextView txtc2;
    private TextView txtc3;
    private TextView txtc4;
    private TextView txtc5;
    private EspecieDAO especieDAO;
    private ResultFragment resultFragment;
    private String[] listac;


    /**
     * Id to identify a camera permission request.
     */
    private static final int REQUEST_CAMERA = 0;

    private View layout;

    /**
     * Requests the Camera permission.
     * If the permission has been denied previously, a SnackBar will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestCameraPermission() {
        Log.i(TAG, "CAMERA permission has NOT been granted. Requesting permission.");
        // BEGIN_INCLUDE(camera_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Log.i(TAG,
                    "Displaying camera permission rationale to provide additional context.");
            Snackbar.make(layout, R.string.permission_camera_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CAMERA);
                        }
                    })
                    .show();
        } else {
            // Camera permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        }
        // END_INCLUDE(camera_permission_request)
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            // BEGIN_INCLUDE(permission_result)
            // Received permission result for camera permission.
            Log.i(TAG, "Received response for Camera permission request.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.i(TAG, "CAMERA permission has now been granted. Showing camera.");
                Snackbar.make(layout, R.string.permision_available_camera,
                        Snackbar.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "CAMERA permission was NOT granted.");
                Snackbar.make(layout, R.string.permissions_not_granted,
                        Snackbar.LENGTH_SHORT).show();
            }
            // END_INCLUDE(permission_result)
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultFragment = new ResultFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_identify, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_identify_fragment);  //um título para a janela

        layout = view.findViewById(R.id.layout_identify);

        btnimg = (ImageButton) view.findViewById(R.id.btnfoto_identify);
        btnhelp = (ImageButton) view.findViewById(R.id.btnhelp_identify);
        btnidentify = (Button) view.findViewById(R.id.btn_identify);
        img = (ImageView) view.findViewById(R.id.img_identify);

        txtc2 = (TextView) view.findViewById(R.id.txt_identify_c2);
        txtc3 = (TextView) view.findViewById(R.id.txt_identify_c3);
        txtc4 = (TextView) view.findViewById(R.id.txt_identify_c4);
        txtc5 = (TextView) view.findViewById(R.id.txt_identify_c5);


        //spinner da classe
        spnrclasse = (Spinner) view.findViewById(R.id.spinner_classe_identify);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.caract1_classes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnrclasse.setAdapter(adapter);
        spnrclasse.setOnItemSelectedListener(this);

        //spinner 2
        spnrc2 = (Spinner) view.findViewById(R.id.spinner_caract2_identify);
        spnrc2.setOnItemSelectedListener(this);
        //spinner 3
        spnrc3 = (Spinner) view.findViewById(R.id.spinner_caract3_idenfity);
        spnrc3.setOnItemSelectedListener(this);
        //spinner 4
        spnrc4 = (Spinner) view.findViewById(R.id.spinner_caract4_identify);
        spnrc4.setOnItemSelectedListener(this);
        //spinner 5
        spnrc5 = (Spinner) view.findViewById(R.id.spinner_caract5_identify);
        spnrc5.setOnItemSelectedListener(this);


        btnidentify.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resultFragment.classe = spnrclasse.getSelectedItem().toString();
                        switch (resultFragment.classe) {
                            case ("Peixes"):
                                resultFragment.c2 = spnrc2.getSelectedItem().toString();
                                resultFragment.c3 = spnrc3.getSelectedItem().toString();
                                resultFragment.c4 = spnrc4.getSelectedItem().toString();
                                resultFragment.c5 = spnrc5.getSelectedItem().toString();
                                replaceFragment();
                                break;
                            case ("Anfíbios"):
                                resultFragment.c2 = spnrc2.getSelectedItem().toString();
                                resultFragment.c3 = spnrc3.getSelectedItem().toString();
                                resultFragment.c4 = spnrc4.getSelectedItem().toString();
                                resultFragment.c5 = spnrc5.getSelectedItem().toString();
                                replaceFragment();
                                break;
                            case ("Répteis"):
                                resultFragment.c2 = spnrc2.getSelectedItem().toString();
                                resultFragment.c3 = spnrc3.getSelectedItem().toString();
                                resultFragment.c4 = spnrc4.getSelectedItem().toString();
                                resultFragment.c5 = spnrc5.getSelectedItem().toString();
                                replaceFragment();
                                break;
                            case ("Aves"):
                                resultFragment.c2 = spnrc2.getSelectedItem().toString();
                                resultFragment.c3 = spnrc3.getSelectedItem().toString();
                                resultFragment.c4 = spnrc4.getSelectedItem().toString();
                                resultFragment.c5 = spnrc5.getSelectedItem().toString();
                                replaceFragment();
                                break;
                            case ("Mamíferos"):
                                resultFragment.c2 = spnrc2.getSelectedItem().toString();
                                resultFragment.c3 = spnrc3.getSelectedItem().toString();
                                resultFragment.c4 = spnrc4.getSelectedItem().toString();
                                resultFragment.c5 = spnrc5.getSelectedItem().toString();
                                replaceFragment();
                                break;
                        }
                    }
                })
        );
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Show camera button pressed. Checking permission.");
                // BEGIN_INCLUDE(camera_permission)
                // Check if the Camera permission is already available.
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has not been granted.
                    requestCameraPermission();
                } else {
                    // Camera permissions is already available, show the camera.
                    Log.i(TAG,
                            "CAMERA permission has already been granted. Displaying camera.");
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, 0);
                }
                // END_INCLUDE(camera_permission)
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = (Bitmap) bundle.get("data");
                img.setImageBitmap(bitmap);
                btnimg.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner_classe_identify:
                if (spnrclasse.getSelectedItem().equals("Selecione")) {
                    btnidentify.setEnabled(false);
                } else if (spnrclasse.getSelectedItem().equals("Peixes")) {
                    btnidentify.setEnabled(true);
                    //spinner 2
                    txtc2.setText(R.string.txtc2_peixes_identify);
                    ArrayAdapter adapter2_peixes = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract2_peixes_tamanho, android.R.layout.simple_spinner_item);
                    adapter2_peixes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc2.setAdapter(adapter2_peixes);
                    //spinner 3
                    txtc3.setText(R.string.txtc3_peixes_identify);
                    ArrayAdapter adapter3_peixes = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract3_peixes_agua, android.R.layout.simple_spinner_item);
                    adapter3_peixes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc3.setAdapter(adapter3_peixes);
                    //spinner 4
                    txtc4.setText(R.string.txtc4_peixes_identify);
                    ArrayAdapter adapter4_peixes = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract4_peixes_comida, android.R.layout.simple_spinner_item);
                    adapter4_peixes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc4.setAdapter(adapter4_peixes);
                    //spinner 5
                    txtc5.setText(R.string.txtc5_peixes_identify);
                    ArrayAdapter adapter5_peixes = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract5_peixes_coloração, android.R.layout.simple_spinner_item);
                    adapter5_peixes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc5.setAdapter(adapter5_peixes);
                } else if (spnrclasse.getSelectedItem().equals("Anfíbios")) {
                    btnidentify.setEnabled(true);
                    //spinner 2
                    txtc2.setText(R.string.txtc2_anfi_identify);
                    ArrayAdapter adapter2_anfi = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract2_anfíbios_ordem, android.R.layout.simple_spinner_item);
                    adapter2_anfi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc2.setAdapter(adapter2_anfi);
                    //spinner 3
                    txtc3.setText(R.string.txtc3_anfi_identify);
                    ArrayAdapter adapter3_anfi = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract3_anfibios_local, android.R.layout.simple_spinner_item);
                    adapter3_anfi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc3.setAdapter(adapter3_anfi);
                    //spinner 4
                    txtc4.setText(R.string.txtc4_anfi_identify);
                    ArrayAdapter adapter4_anfi = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract4_anfibios_tamanho, android.R.layout.simple_spinner_item);
                    adapter4_anfi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc4.setAdapter(adapter4_anfi);
                    //spinner 5
                    txtc5.setText(R.string.txtc5_anfi_identify);
                    ArrayAdapter adapter5_anfi = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract5_anfibios_cor, android.R.layout.simple_spinner_item);
                    adapter5_anfi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc5.setAdapter(adapter5_anfi);
                } else if (spnrclasse.getSelectedItem().equals("Répteis")) {
                    btnidentify.setEnabled(true);
                    //spinner 2
                    txtc2.setText(R.string.txtc2_rept_identify);
                    ArrayAdapter adapter2_rep = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract2_repteis_tipo, android.R.layout.simple_spinner_item);
                    adapter2_rep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc2.setAdapter(adapter2_rep);
                    //spinner 3
                    txtc3.setText(R.string.txtc3_rept_identify);
                    ArrayAdapter adapter3_rep = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract3_repteis_local, android.R.layout.simple_spinner_item);
                    adapter3_rep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc3.setAdapter(adapter3_rep);
                    //spinner 4
                    txtc4.setText(R.string.txtc4_rept_identify);
                    ArrayAdapter adapter4_rep = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract4_repteis_comida, android.R.layout.simple_spinner_item);
                    adapter4_rep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc4.setAdapter(adapter4_rep);
                    //spinner 5
                    txtc5.setText(R.string.txtc5_rept_identify);
                    ArrayAdapter adapter5_rep = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract5_repteis_tamanho, android.R.layout.simple_spinner_item);
                    adapter5_rep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc5.setAdapter(adapter5_rep);
                } else if (spnrclasse.getSelectedItem().equals("Aves")) {
                    btnidentify.setEnabled(true);
                    //spinner 2
                    txtc2.setText(R.string.txtc2_aves_identify);
                    ArrayAdapter adapter2_aves = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract2_aves_porte, android.R.layout.simple_spinner_item);
                    adapter2_aves.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc2.setAdapter(adapter2_aves);
                    //spinner 3
                    txtc3.setText(R.string.txtc3_aves_identify);
                    ArrayAdapter adapter3_aves = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract3_aves_bico, android.R.layout.simple_spinner_item);
                    adapter3_aves.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc3.setAdapter(adapter3_aves);
                    //spinner 4
                    txtc4.setText(R.string.txtc4_aves_identify);
                    ArrayAdapter adapter4_aves = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract4_aves_cor, android.R.layout.simple_spinner_item);
                    adapter4_aves.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc4.setAdapter(adapter4_aves);
                    //spinner 5
                    txtc5.setText(R.string.txtc5_aves_identify);
                    ArrayAdapter adapter5_aves = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract5_aves_local, android.R.layout.simple_spinner_item);
                    adapter5_aves.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc5.setAdapter(adapter5_aves);
                } else if (spnrclasse.getSelectedItem().equals("Mamíferos")) {
                    btnidentify.setEnabled(true);
                    //spinner 2
                    txtc2.setText(R.string.txtc2_mamif_identify);
                    ArrayAdapter adapter2_mam = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract2_mamiferos_ordem, android.R.layout.simple_spinner_item);
                    adapter2_mam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc2.setAdapter(adapter2_mam);
                    //spinner 3
                    txtc3.setText(R.string.txtc3_mamif_identify);
                    ArrayAdapter adapter3_mam = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract3_mamiferos_local, android.R.layout.simple_spinner_item);
                    adapter3_mam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc3.setAdapter(adapter3_mam);
                    //spinner 4
                    txtc4.setText(R.string.txtc4_mamif_identify);
                    ArrayAdapter adapter4_mam = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract4_mamiferos_comida, android.R.layout.simple_spinner_item);
                    adapter4_mam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc4.setAdapter(adapter4_mam);
                    //spinner 5
                    txtc5.setText(R.string.txtc5_mamif_identify);
                    ArrayAdapter adapter5_mam = ArrayAdapter.createFromResource(getContext(),
                            R.array.caract5_mamiferos_porte, android.R.layout.simple_spinner_item);
                    adapter5_mam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnrc5.setAdapter(adapter5_mam);
                }
                break;
            case R.id.spinner_caract2_identify:
                if (spnrc2.getSelectedItem().equals("Não sei")) {
                    resultFragment.aux1 = true;
                }
                Toast.makeText(getContext(), "selecionado:" + spnrc2.getSelectedItem(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner_caract3_idenfity:
                if (spnrc3.getSelectedItem().equals("Não sei")) {
                    resultFragment.aux2 = true;
                }
                Toast.makeText(getContext(), "selecionado:" + spnrc3.getSelectedItem(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner_caract4_identify:
                if (spnrc4.getSelectedItem().equals("Não sei")) {
                    resultFragment.aux3 = true;
                }
                Toast.makeText(getContext(), "selecionado:" + spnrc4.getSelectedItem(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinner_caract5_identify:
                if (spnrc5.getSelectedItem().equals("Não sei")) {
                    resultFragment.aux4 = true;
                }
                Toast.makeText(getContext(), "selecionado:" + spnrc5.getSelectedItem(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //nothing
    }

    private void replaceFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //Prepara o fragment que será inflado
        transaction.replace(R.id.fragment_container, resultFragment).addToBackStack(null).commit();
    }

    /**
     * Ele é chamado pelo Android ao destruir a Activity.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}

