package co.example.beverage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView emailTV, passwordTV;
    Button loginBtn, registerBtn;
    CShowProgress cShowProgress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordTV = findViewById(R.id.password);
        emailTV = findViewById(R.id.email);
        loginBtn = findViewById(R.id.login);
        registerBtn = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();
        cShowProgress = CShowProgress.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailTV.getText().toString().isEmpty()) {
                    if (!passwordTV.getText().toString().isEmpty()) {
                        loginUser(emailTV.getText().toString(), passwordTV.getText().toString());
                    } else {
                        passwordTV.setText("Required");
                    }
                } else {
                    emailTV.setText("Required");
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });
    }

    private void loginUser(String email, String password) {
        cShowProgress.showProgress(LoginActivity.this);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            cShowProgress.hideProgress();
                            Toast.makeText(LoginActivity.this, "LoginWithEmail:success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            cShowProgress.hideProgress();
                            Toast.makeText(LoginActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}