package com.example.xqlim.secondlife.RecyclablesFolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.xqlim.secondlife.R;

    public class RecyclableImageAdapter extends BaseAdapter {
        private Context mContext;

        public RecyclableImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return mThumbIds[position];
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(370, 370));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        public Integer[] mThumbIds = {
                R.drawable.small_cans, R.drawable.small_glass,
                R.drawable.small_metal_tin, R.drawable.small_paper,
                R.drawable.small_plastic, R.drawable.small_clothing,
                R.drawable.small_appliance, R.drawable.small_corrugated_cardboard,
                R.drawable.small_second_hand, R.drawable.small_ewaste
        };
    }
