package co.example.beverage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.example.beverage.database.DBHelper;
import co.example.beverage.models.Item;

/**
 * Created by Ussama Iftikhar on 28-Jan-2021.
 * Email iusama46@gmail.com
 * Email iusama466@gmail.com
 * Github https://github.com/iusama46
 */
public class AddItemActivity extends AppCompatActivity {

    Button addBtn;
    EditText name, price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        addBtn = findViewById(R.id.add);
        price = findViewById(R.id.price);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);

        DBHelper dbHelper = new DBHelper(this);

        int id = getIntent().getIntExtra("id", -1);
        if (id == 1) {
            price.setText(MainActivity2.price);
            description.setText(MainActivity2.description);
            name.setText(MainActivity2.name);
            addBtn.setText("Update");
        }


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id == 1) {
                    if (dbHelper.updateItem(new Item(MainActivity2.id, name.getText().toString(), description.getText().toString(), price.getText().toString(), 0))) {
                        Toast.makeText(AddItemActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddItemActivity.this, "Updating Failed", Toast.LENGTH_SHORT).show();
                    }

                    return;
                }
                if (!name.getText().toString().isEmpty()) {

                    if (!price.getText().toString().isEmpty()) {
                        if (!description.getText().toString().isEmpty()) {
                            if (dbHelper.insertItem(new Item(name.getText().toString(), description.getText().toString(), price.getText().toString()))) {
                                Toast.makeText(AddItemActivity.this, "Added to db", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(AddItemActivity.this, "Failed To add into db", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AddItemActivity.this, "Enter description", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddItemActivity.this, "Enter Price", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(AddItemActivity.this, "Enter item name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}