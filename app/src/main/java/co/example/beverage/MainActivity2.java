package co.example.beverage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by Ussama Iftikhar on 28-Jan-2021.
 * Email iusama46@gmail.com
 * Email iusama466@gmail.com
 * Github https://github.com/iusama46
 */
public class MainActivity2 extends AppCompatActivity {

    public static int id;
    public static String name;
    public static String price;
    public static String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        description = getIntent().getStringExtra("description");
        setFragment(new ItemRecordFragment());

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.lay, fragment);
        transaction.commit();
    }
}