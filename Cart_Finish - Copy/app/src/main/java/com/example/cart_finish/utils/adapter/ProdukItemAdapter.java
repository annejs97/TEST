package com.example.cart_finish.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cart_finish.R;
import com.example.cart_finish.utils.model.ProdukItem;
import com.example.cart_finish.views.MainActivity;

import java.util.List;

public class ProdukItemAdapter extends RecyclerView.Adapter<ProdukItemAdapter.ProdukItemViewHolder> {

    private List<ProdukItem> produkItemList;
    private MainActivity produkClickedListeners;
    public ProdukItemAdapter (MainActivity produkClickedListeners){
        this.produkClickedListeners = produkClickedListeners;
    }
    public void setProdukItemList(List<ProdukItem> produkItemList){
        this.produkItemList = produkItemList;
    }
    @NonNull
    @Override
    public ProdukItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_produk, parent, false);
        return new ProdukItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukItemViewHolder holder, int position) {
        ProdukItem produkItem = produkItemList.get(position);
        holder.produkNameTv.setText(produkItem.getProdukName());
        holder.produkBrandNameTv.setText(produkItem.getProdukBrandName());
        holder.produkPriceTv.setText(String.valueOf(produkItem.getProdukPrice()));
        holder.produkImageView.setImageResource(produkItem.getProdukImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produkClickedListeners.onCardClicked(produkItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produkClickedListeners.onAddToCartBtnClicked(produkItem);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (produkItemList == null){
            return 0;
        }else {
            return produkItemList.size();
        }
    }

    public class ProdukItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView produkImageView , addToCartBtn;
        private TextView produkNameTv, produkBrandNameTv, produkPriceTv;
        private CardView cardView;
        public ProdukItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.eachProdukCardView);
            addToCartBtn = itemView.findViewById(R.id.eachProdukAddToCartBtn);
            produkNameTv = itemView.findViewById(R.id.eachProdukName);
            produkImageView = itemView.findViewById(R.id.eachProdukIv);
            produkBrandNameTv = itemView.findViewById(R.id.eachProdukBrandNameTv);
            produkPriceTv = itemView.findViewById(R.id.eachProdukPriceTv);
        }
    }

    public interface ProdukClickedListeners{
        void onCardClicked(ProdukItem produk);
        void onAddToCartBtnClicked(ProdukItem produkItem);
    }


}
