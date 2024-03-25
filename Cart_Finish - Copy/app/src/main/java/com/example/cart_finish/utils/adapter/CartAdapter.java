package com.example.cart_finish.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cart_finish.R;
import com.example.cart_finish.utils.model.ProdukCart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private CartClickedListeners cartClickedListeners;
    private List<ProdukCart> produkCartList;

    public CartAdapter(CartClickedListeners cartClickedListeners){
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setProdukCartList(List<ProdukCart> produkCartList){
        this.produkCartList = produkCartList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        ProdukCart produkCart = produkCartList.get(position);
        holder.produkImageView.setImageResource(produkCart.getProdukImage());
        holder.produkNameTv.setText(produkCart.getProdukName());
        holder.produkBrandNameTv.setText(produkCart.getProdukBrandName());
        holder.produkQuantity.setText(produkCart.getQuantity() + "");
        holder.produkPriceTv.setText(produkCart.getTotalItemPrice() + "");

        holder.deleteProdukBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(produkCart);
            }
        });

        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(produkCart);
            }
        });

        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(produkCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (produkCartList == null){
            return 0;
        }else {
            return produkCartList.size();
        }
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        private TextView produkNameTv, produkBrandNameTv, produkPriceTv, produkQuantity;
        private ImageView deleteProdukBtn;
        private ImageView produkImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            produkNameTv = itemView.findViewById(R.id.eachCartItemName);
            produkBrandNameTv = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            produkPriceTv = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteProdukBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            produkImageView = itemView.findViewById(R.id.eachCartItemIV);
            produkQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
        }
    }

    public interface CartClickedListeners{
        void onDeleteClicked(ProdukCart produkCart);
        void onPlusClicked(ProdukCart produkCart);
        void onMinusClicked(ProdukCart produkCart);

    }
}
