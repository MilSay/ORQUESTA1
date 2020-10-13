package com.android.app.orquesta.userRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.Util.CustomSharedPrefs;
import com.android.app.orquesta.model.Persona;
import com.android.app.orquesta.model.RegistrarPersona;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.NetworkHelper;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.main.MainActivity;
import com.android.app.orquesta.model.User;
import com.android.app.orquesta.userLogin.LoginActivity;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegisterLogin;
    Toolbar toolbar;
    Button btnUserRegistration;
    TextView tvTermsWriting;
    EditText etNombre, etApellidos,etDni, etCelular,etEmail, etUserName, etPassword, etConfirmar;
    TextView tvValidationNombre,tvValidationApellidos,tvValidationDni,tvValidationCelular,tvValidationUserName,getTvValidationPassword;

    EditText etFirstName, etLastname,  etRepetir, etDepartment,etDistrict, etProvince,etMobile,etUseraddress;
    TextView tvValidationEmail, tvValidationFirstName, tvValidationLastName, tvValidationPassword, tvRegMsg,tvValidationDepartment,tvValidationDistrict,tvValidationProvince,tvValidationMobile,tvValidationUseraddress;
    boolean fromCart = false;

    private boolean networkOK;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView toobarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        toobarTitle.setText(this.getString(R.string.title_register));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (CustomSharedPrefs.getLoggedInUser(RegisterActivity.this) == null) {

            btnUserRegistration = findViewById(R.id.btn_user_registration);
            btnUserRegistration.setOnClickListener(this);
            tvTermsWriting = findViewById(R.id.tv_terms_writing);

            etNombre = findViewById(R.id.et_register_nombre);
            etApellidos = findViewById(R.id.et_register_apellidos);
            etDni = findViewById(R.id.et_register_dni);
            etCelular = findViewById(R.id.et_register_celular);
            etEmail = findViewById(R.id.et_register_email);
            etUserName = findViewById(R.id.et_register_usuario);
            etPassword = findViewById(R.id.et_register_passwords);
            etConfirmar = findViewById(R.id.et_register_confirmar);
            /*
            etFirstName = findViewById(R.id.et_register_first_name);
            etLastname = findViewById(R.id.et_register_last_name);
            etEmail = findViewById(R.id.et_register_email);
            etPassword = findViewById(R.id.et_register_password);
            etRepetir= findViewById(R.id.et_repetir_password);
            /*add*/

            tvValidationNombre = findViewById(R.id.tv_validation_msg_nombre);
            tvValidationApellidos = findViewById(R.id.tv_validation_msg_apellidos);
            tvValidationDni = findViewById(R.id.tv_validation_msg_dni);
            tvValidationCelular = findViewById(R.id.tv_validation_msg_celular);
            tvValidationEmail = findViewById(R.id.tv_validation_msg_email);
            tvValidationUserName = findViewById(R.id.tv_validation_msg_usuario);
            tvValidationPassword = findViewById(R.id.tv_validation_msg_passwords);

            tvRegMsg = findViewById(R.id.tv_reg_msg);
            /*add*/



          //  UtilityClass.textViewScaleIconLeft(this, findViewById(R.id.et_register_email), R.drawable.ic_email_fill_black, .5);
         //   UtilityClass.textViewScaleIconLeft(this, findViewById(R.id.et_register_first_name), R.drawable.ic_user_fill_black, .5);
         //   UtilityClass.textViewScaleIconLeft(this, findViewById(R.id.et_register_last_name), R.drawable.ic_user_fill_black, .5);
            /*add*/
            /*
            UtilityClass.textViewScaleIconLeft(this, findViewById(R.id.et_register_department), R.drawable.ic_user_fill_black, .5);
            UtilityClass.textViewScaleIconLeft(this, findViewById(R.id.et_register_district), R.drawable.ic_user_fill_black, .5);
            UtilityClass.textViewScaleIconLeft(this, findViewById(R.id.et_register_province), R.drawable.ic_user_fill_black, .5);
            UtilityClass.textViewScaleIconLeft(this, findViewById(R.id.et_register_mobile), R.drawable.ic_user_fill_black, .5);
            UtilityClass.textViewScaleIconLeft(this, findViewById(R.id.et_register_useraddress), R.drawable.ic_user_fill_black, .5);
            */
            /* START OF CLICKABLE LINK AREA */
            SpannableString tearmsAndCondition = new SpannableString(getResources().getString(R.string.register_terms_writing));
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                }
            };

            int stringCount = tearmsAndCondition.length();

            tearmsAndCondition.setSpan(clickableSpan, stringCount - 20, stringCount, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            final TextView textView = findViewById(R.id.tv_terms_writing);
            textView.setText(tearmsAndCondition);
            textView.setMovementMethod(LinkMovementMethod.getInstance());

            btnRegisterLogin = findViewById(R.id.btn_register_login);
            btnRegisterLogin.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_user_registration:
                RegistrarPersona users = validateUser();

                if (users != null) {
                    networkOK = NetworkHelper.hasNetworkAccess(RegisterActivity.this);
                    if (networkOK) userResistration(users);
                    else
                        Toast.makeText(RegisterActivity.this, "No se pudo conectar a internet.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register_login:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void userResistration(final RegistrarPersona users) {
        String JSON_URL = Constants.REGISTAR_PERSONA;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.trim();
                if (isInteger(response)) {
                    if (Integer.parseInt(response) > 0) {
//                        users.setApellidos(response);
                        // CustomSharedPrefs.setLoggedInUser(RegisterActivity.this, new Persona());
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        clearInputs();
                    }
                } else Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responseV", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("Nombres", users.getNombres());
                params.put("Apellidos", users.getApellidos());
                params.put("Dni", users.getDni());
                params.put("Celular", users.getCelular());
                params.put("Email", users.getEmail());
                params.put("UserName", users.getUserName());
                params.put("Password", users.getPassword());

                return params;
            }
        };

        queue.getCache().clear();
        queue.add(stringRequest);
    }


    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }


    private RegistrarPersona validateUser() {

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String nombre = etNombre.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String dni = etDni.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String nueva = etConfirmar.getText().toString().trim();


        boolean isFieldEmpty = false;

        if (isEmpty(nombre)) {
            isFieldEmpty = true;
            tvValidationNombre.setVisibility(View.VISIBLE);
            tvValidationNombre.setText("El nombre no puede estar vacío.");
        } else {
            tvValidationNombre.setVisibility(View.GONE);
            tvValidationNombre.setText("");
        }


        if (isEmpty(apellidos)) {
            isFieldEmpty = true;
            tvValidationApellidos.setVisibility(View.VISIBLE);
            tvValidationApellidos.setText("El apellido no puede estar vacío.");
        } else {
            tvValidationApellidos.setVisibility(View.GONE);
            tvValidationApellidos.setText("");
        }

        if (isEmpty(dni)) {
            isFieldEmpty = true;
            tvValidationDni.setVisibility(View.VISIBLE);
            tvValidationDni.setText("El DNI no puede estar vacío.");
        }else if(dni.length() <8){
            isFieldEmpty = true;
            tvValidationDni.setVisibility(View.VISIBLE);
            tvValidationDni.setText("Digitar 8 digitos.");
        }else {
            tvValidationDni.setVisibility(View.GONE);
            tvValidationDni.setText("");
        }


        if (isEmpty(email)) {
            isFieldEmpty = true;
            tvValidationEmail.setVisibility(View.VISIBLE);
            tvValidationEmail.setText("El correo electrónico no puede estar vacío.");
        } else if (!email.matches(emailPattern) && !isEmpty(email)) {
            isFieldEmpty = true;
            tvValidationEmail.setVisibility(View.VISIBLE);
            tvValidationEmail.setText("Email inválido.");
        } else {
            tvValidationEmail.setVisibility(View.GONE);
            tvValidationEmail.setText("");
        }

        if (isEmpty(password)) {
            isFieldEmpty = true;
            tvValidationPassword.setVisibility(View.VISIBLE);
            tvValidationPassword.setText("La contraseña no puede estar vacía.");
        } else if (password.length() < 4 && !isEmpty(email)) {
            isFieldEmpty = true;
            tvValidationPassword.setVisibility(View.VISIBLE);
            tvValidationPassword.setText("La contraseña debe contener 4 caracteres como mínimo.");

        }  else {
            tvValidationPassword.setVisibility(View.GONE);
            tvValidationPassword.setText("");
        }
        if (!(password.equalsIgnoreCase(nueva))){
            isFieldEmpty = true;
            tvValidationPassword.setVisibility(View.VISIBLE);
            tvValidationPassword.setText("La contraseña no son iguales.");
        }
        if (isEmpty(celular)) {
            isFieldEmpty = true;
            tvValidationCelular.setVisibility(View.VISIBLE);
            tvValidationCelular.setText("El n° celular no puede estar vacío.");
        }else if(celular.length() <9){
            isFieldEmpty = true;
            tvValidationCelular.setVisibility(View.VISIBLE);
            tvValidationCelular.setText("Digitar 9 digitos.");
        }else {
            tvValidationCelular.setVisibility(View.GONE);
            tvValidationCelular.setText("");
        }



        if (!isFieldEmpty) {
            RegistrarPersona user = new RegistrarPersona();

            user.setNombres(nombre);
            user.setApellidos(apellidos);
            user.setDni(dni);
            user.setCelular(celular);
            user.setEmail(email);
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        } else {
            return null; //Error
        }

    }

    private void clearInputs() {
        etNombre.setText("");
        etApellidos.setText("");
        etDni.setText("");
        etCelular.setText("");
        etEmail.setText("");
        etUserName.setText("");
        etPassword.setText("");
    }

    private boolean isEmpty(String value) {
        return (value.length() < 1) ? true : false;
    }


}
