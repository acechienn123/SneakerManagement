package com.example.thoitrang.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.thoitrang.R;
import com.example.thoitrang.adapter.NhanVienAdapter;
import com.example.thoitrang.dao.NhanVienDAO;
import com.example.thoitrang.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class NhanVienFragment extends Fragment {

    ListView lv;
    ArrayList<NhanVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaNV;
    TextInputEditText edtenDN, edhoTenNV, edPass, edRePass, edsdtNV, ed_search;
    Button btnSaveNV, btnCancelNV;
    ImageView img_avata, img_search;
    final int REQUEST_CODE_FOLDER = 456;
    static NhanVienDAO dao;
    NhanVienAdapter adapter;
    NhanVien item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        lv = v.findViewById(R.id.lvNhanVien);
        fab = v.findViewById(R.id.fab_NV);
        ed_search = v.findViewById(R.id.ed_search_nhanVien);
        img_search = v.findViewById(R.id.img_search_nhanVien);
        dao = new NhanVienDAO(getActivity());
        capNhatLv();

//        tim kiem Nhan Vien theo ten dang nhap
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_search.getText().length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin trước khi Search", Toast.LENGTH_SHORT).show();
                }
                capNhatTen();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });

        return v;
    }

    protected void openDialog(final Context context, final int type){

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.nhan_vien_dialog);
        edMaNV = dialog.findViewById(R.id.edmaNV_dialog);
        edtenDN = dialog.findViewById(R.id.edtenDN_dialog);
        edhoTenNV = dialog.findViewById(R.id.edTenNV_dialog);
        edsdtNV = dialog.findViewById(R.id.edSdtNV_dialog);
        edPass = dialog.findViewById(R.id.edPassword_nV_dialog);
        edRePass = dialog.findViewById(R.id.edRePassword_nV_dialog);
        btnCancelNV = dialog.findViewById(R.id.btnCancel_NV_dialog);
        btnSaveNV = dialog.findViewById(R.id.btnSave_NV_dialog);
        img_avata = dialog.findViewById(R.id.img_avataNV_dialog);
        edMaNV.setEnabled(false);

        if(type != 0){
            edMaNV.setText(String.valueOf(item.maNV));
            edtenDN.setText(item.tenDN);
            edhoTenNV.setText(item.hoTenNV);
            edsdtNV.setText(item.sdtNV);
            edPass.setText(item.matKhau);
            edRePass.setText(item.matKhau);
        }

        btnCancelNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img_avata.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh_NV = byteArray.toByteArray();
//===========

                item = new NhanVien();
                item.tenDN = edtenDN.getText().toString();
                item.hoTenNV = edhoTenNV.getText().toString();
                item.matKhau = edPass.getText().toString();
                item.sdtNV = edsdtNV.getText().toString();
                item.hinhNV = hinhAnh_NV;

                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{

                        item.maNV = Integer.parseInt(edMaNV.getText().toString());
                        if(dao.updatePass(item)>0){
                            Toast.makeText(context,"Cap nhat Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Cap nhatThất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });

        img_avata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_avata.setImageBitmap (bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muon xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                Snackbar.make(getView(),"Bạn đã xóa thành công",Snackbar.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLv(){
        list = (ArrayList<NhanVien>)dao.getAll();
        adapter = new NhanVienAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    void capNhatTen(){
        list = (ArrayList<NhanVien>)dao.getSearch_nhanVien(ed_search.getText().toString());
        adapter = new NhanVienAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    public int validate() {
        int check = 1;

        // Kiểm tra xem các trường có bị bỏ trống không
        if (edhoTenNV.getText().length() == 0 || edtenDN.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            // Lấy giá trị từ các trường nhập liệu
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            String tenDangNhap = edtenDN.getText().toString();
            String hotenNV = edhoTenNV.getText().toString();

            // Kiểm tra tên đăng nhập
            if (!tenDangNhap.matches(".*[a-zA-Z0-9]{3,40}.*")) {
                Toast.makeText(getContext(), "Tên Đăng Nhập chỉ được nhập 3 đến 40 ký tự", Toast.LENGTH_LONG).show();
                check = -1;
            }
            // Kiểm tra mật khẩu và mật khẩu nhập lại có trùng khớp không
            else if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật Khẩu Nhập lại không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            // Kiểm tra tên người dùng có hợp lệ không
            else if (!hotenNV.matches(".*[a-zA-Z]{3,40}.*")) {
                Toast.makeText(getContext(), "Tên Người Dùng chỉ được nhập 3 đến 40 ký tự", Toast.LENGTH_LONG).show();
                check = -1;
            }
            // Kiểm tra mật khẩu có hợp lệ không
            else if (!pass.matches(".*[a-zA-Z0-9]{5,40}.*")) {
                Toast.makeText(getContext(), "Mật Khẩu chỉ được nhập 5 đến 40 ký tự", Toast.LENGTH_LONG).show();
                check = -1;
            }
        }
        return check;
    }

}