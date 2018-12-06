package com.example.zuoyelog.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zuoyelog.LogActivity;
import com.example.zuoyelog.MainActivity;
import com.example.zuoyelog.R;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class FragmentName extends Fragment {
    private ImageView imageView;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentname,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.back);
        imageView = view.findViewById(R.id.imageview);




        shengcheng();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();



            }
        });

    }
    private void shengcheng() {
        String qr = ((LogActivity) getActivity()).QR();
        QRTask qrTask = new QRTask(getActivity(), imageView, qr);
        qrTask.execute(qr);

    }
    static class QRTask extends AsyncTask<String,Void,Bitmap> {
        private WeakReference<Context> mcontext;
        private WeakReference<ImageView> mimageview;

        public QRTask(Context context, ImageView imageView, String name) {
            mcontext=new WeakReference<>(context);
            mimageview=new WeakReference<>(imageView);
        }

        @Override protected Bitmap doInBackground(String... params) {
            String str = params[0];
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            int size = mcontext.get().getResources().getDimensionPixelOffset(R.dimen.qr);
            return QRCodeEncoder.syncEncodeQRCode(str, size);
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap!=null){
                mimageview.get().setImageBitmap(bitmap);
            }else{
                Toast.makeText(mcontext.get(), "失败", Toast.LENGTH_SHORT).show();

            }

        }
    }
    }

