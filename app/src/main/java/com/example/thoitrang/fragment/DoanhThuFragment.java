package com.example.thoitrang.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.thoitrang.R;
import com.example.thoitrang.dao.ThongKeDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhThuFragment extends Fragment {

 Button btnDoanhThu;
 EditText edTuNgay, edDenNgay;
 TextView tvDoanhThu;
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 int mYear,mMonth,mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_doanhthu, container, false);
        edTuNgay = v.findViewById(R.id.edtuNgay_DT);
        edDenNgay = v.findViewById(R.id.eddenNgay_DT);
        tvDoanhThu = v.findViewById(R.id.tvdoanhThu);
        btnDoanhThu = v.findViewById(R.id.btndoanhThu);
        edTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        edDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0,mDateDenNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
                tvDoanhThu.setText(thongKeDAO.getDanhThu(tuNgay,denNgay)+" VNĐ");
            }
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth,mDay);
            edTuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth,mDay);
            edDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}