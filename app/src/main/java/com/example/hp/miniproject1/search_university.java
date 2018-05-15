package com.example.hp.miniproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class search_university extends AppCompatActivity {

    public ListView lvUniversity;
    public ArrayList<University>  arrayUniversity;
    public ArrayList<String> arrayMonHoc;
    public UniversityAdapter adapter;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_university);

        AnhXa();
    //    arrayMonHoc = new ArrayList<>();
      //  arrayMonHoc.add("Android");
     //   arrayMonHoc.add("IOS");
      //  arrayMonHoc.add("PHP");
      //  arrayMonHoc.add("Unity");

//        ArrayAdapter adapter1 = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1,
//                arrayMonHoc);
      //  lvUniversity.setAdapter(adapter1);
        adapter = new UniversityAdapter(this,R.layout.dong_university,arrayUniversity);
        lvUniversity.setAdapter(adapter);

        lvUniversity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Call dialog to display details
                //Create Dialog Activity
                Intent intent = new Intent(getApplicationContext(), dialog_listview.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("university", arrayUniversity.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                setResult(2,data);
                finish();
            }
        });
       // finish();
    }

    private void AnhXa() {
        lvUniversity = (ListView) findViewById(R.id.lvUniversity);
        arrayUniversity = new ArrayList<>();
        btnBack = (Button) findViewById(R.id.btnBack);

        arrayUniversity.add(new University("Học viện Cán bộ Thành phố Hồ Chí Minh", "02822437830", "www.hocviencanbo.hochiminhcity.gov.vn", R.drawable.hoc_vien_can_bo, "324 Chu Văn An, Phường 12, Bình Thạnh, Hồ Chí Minh", "123", 10.812669, 106.703453));
    //    arrayUniversity.add(new University("Học viện Công nghệ Bưu chính Viễn thông cơ sở 2", "02838295258", "hcm.ptit.edu.vn", R.drawable.hoc_vien_buu_chinh_vien_thong, "97 Man Thiện, Hiệp Phú, Quận 9, Hồ Chí Minh", "123", 10.848796, 106.787227));
        arrayUniversity.add(new University("Học Viện Hành Chính Quốc Gia", "01682256413", "www1.napa.vn", R.drawable.hoc_vien_hanh_chinh, "10 Ba Tháng Hai, Phường 12, Quận 10, Hồ Chí Minh", "", 10.774727, 106.676104));
        arrayUniversity.add(new University("Học Viện Kỹ Thuật Mật Mã", "02862903434", "actvn.edu.vn", R.drawable.hoc_vien_ky_thuat_mat_ma, "17A Cộng Hòa, Phường 4, Tân Bình, Hồ Chí Minh", "", 10.802187, 106.657065));
        arrayUniversity.add(new University("Nhạc viện Thành phố Hồ Chí Minh", "02838225841", "www.hcmcons.vn", R.drawable.nhac_vien_tp, "112 Nguyễn Du, Phường Bến Thành, Quận 1, Hồ Chí Minh", "", 10.775970, 106.694839));
        arrayUniversity.add(new University("Đại học An ninh nhân dân", "", "", R.drawable.dh_an_ninh_nhan_dan, "112/47 Xa lộ Hà Nội, Phường Linh Trung, Thủ Đức, Hồ Chí Minh", "", 10.874720, 10.874720));
        arrayUniversity.add(new University("Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh", "02838647256", "hcmut.edu.vn", R.drawable.dh_bach_khoa, "268 Lý Thường Kiệt, Phường 14, Quận 10, Hồ Chí Minh", "", 10.774489, 10.659296));
        arrayUniversity.add(new University("Trường Đại học Công nghiệp Thực phẩm Thành phố Hồ Chí Minh", "02838161673", "hufi.edu.vn", R.drawable.dh_cong_nghiep_thuc_pham, "140 Lê Trọng Tấn, Phường 15, Tân Phú, Hồ Chí Minh", "", 10.808014, 10.628483));
        arrayUniversity.add(new University("Trường Đại học Công nghiệp Thành phố Hồ Chí Minh", "02838940390", "www.hui.edu.vn", R.drawable.dh_cong_nghiep, "12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Hồ Chí Minh", "", 10.823177, 10.687452));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            String destination = data.getStringExtra("destination");
            sendBack(destination);
        }
    }

    private void sendBack(String destination) {
        Intent data1 = new Intent();
        data1.putExtra("destination",destination);
        setResult(2,data1);
        finish();
    }
}
