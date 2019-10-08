package com.cognition.buildovov3.view.adapters.otherAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cognition.buildovov3.R;
import com.cognition.buildovov3.values.Constants;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class ImageSliderProductAdapter extends SliderViewAdapter<ImageSliderProductAdapter.SliderAdapterVH> {

    private Context context;
    ArrayList<String> imageURLs;

    public ImageSliderProductAdapter(Context context, ArrayList<String> imageURLs) {
        this.context = context;
        this.imageURLs = imageURLs;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item_product, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {


                Glide.with(viewHolder.itemView)
                        .load(Constants.BASE_IMAGE_URL +imageURLs.get(position))
                        .into(viewHolder.imageViewBackground);


    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return imageURLs.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
