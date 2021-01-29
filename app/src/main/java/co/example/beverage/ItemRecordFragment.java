package co.example.beverage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import co.example.beverage.database.DBHelper;


public class ItemRecordFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DBHelper dbHelper = new DBHelper(getContext());
        View view= inflater.inflate(R.layout.fragment_item_record, container, false);
        TextView price = view.findViewById(R.id.price);
        TextView name = view.findViewById(R.id.name);
        TextView description = view.findViewById(R.id.description);
        Button updateBtn= view.findViewById(R.id.update);
        Button delBtn= view.findViewById(R.id.del);

        price.setText("Price: "+MainActivity2.price);
        name.setText(MainActivity2.name);
        description.setText(MainActivity2.description);

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.deleteItem(MainActivity2.id)){
                    Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else{
                    Toast.makeText(getContext(), "Deletion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddItemActivity.class).putExtra("id",1));
                getActivity().finish();
            }
        });
        return view;
    }
}