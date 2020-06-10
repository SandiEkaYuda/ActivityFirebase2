public class LihatBarang {

package com.example.testbarang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
    public class LihatBarang extends AppCompatActivity {
        /**
         * Mendefinisikan variable yang akan dipakai
         */
        private DatabaseReference database;
        private RecyclerView rvView;
        private RecyclerView.Adapter adapter;
        private RecyclerView.LayoutManager layoutManager;
        private ArrayList<Barang> daftarBarang;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lihat_barang);
            /* Inisialisasi RecyclerView & komponennya
             */
            rvView = (RecyclerView) findViewById(R.id.rv_main);
            rvView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            rvView.setLayoutManager(layoutManager);
            /**
             * Inisialisasi dan mengambil Firebase Database Reference
             */
            database = FirebaseDatabase.getInstance().getReference();
            /**
             * Mengambil data barang dari Firebase Realtime DB
             */
            database.child("Barang").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              daftarBarang = new ArrayList<>();
               for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

               Barang barang = noteDataSnapshot.getValue(Barang.class);
               barang.setKode(noteDataSnapshot.getKey());

               daftarBarang.add(barang);
               }

               adapter = new AdapterLihatBarang(daftarBarang, LihatBarang.this);
               rvView.setAdapter(adapter);

               }
               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

                 System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
             }
            });
        }
        public static Intent getActIntent(Activity activity){
            return new Intent(activity, LihatBarang.class);
        }
    }
Edit MainActivity.java, tambahkan baris kode berikut ini :
            bLihat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(LihatBarang.getActIntent(MainActivity.this));
        }
    });

}
