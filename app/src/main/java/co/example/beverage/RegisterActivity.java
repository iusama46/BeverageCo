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

public class RegisterActivity extends AppCompatActivity {

    TextView nameTV, emailTV, passwordTV;
    Button registerBtn, loginBtn;
    CShowProgress cShowProgress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameTV = findViewById(R.id.name);
        passwordTV = findViewById(R.id.password);
        emailTV = findViewById(R.id.email);
        registerBtn = findViewById(R.id.register);
        loginBtn = findViewById(R.id.login);

        mAuth = FirebaseAuth.getInstance();

        cShowProgress = CShowProgress.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailTV.getText().toString().isEmpty()) {
                    if (!passwordTV.getText().toString().isEmpty()) {
                        registerUser(emailTV.getText().toString(), passwordTV.getText().toString());
                    } else {
                        passwordTV.setText("Required");
                    }
                } else {
                    emailTV.setText("Required");
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    private void registerUser(String email, String password) {
        cShowProgress.showProgress(RegisterActivity.this);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            cShowProgress.hideProgress();
                            Toast.makeText(RegisterActivity.this, "createUserWithEmail:success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            cShowProgress.hideProgress();
                            Toast.makeText(RegisterActivity.this, "Registration failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}