package id.muhammadfaisal.eratanitestinterview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.muhammadfaisal.eratanitestinterview.DetailActivity
import id.muhammadfaisal.eratanitestinterview.R
import id.muhammadfaisal.eratanitestinterview.databinding.ItemProductBinding
import id.muhammadfaisal.eratanitestinterview.model.ProductItem

class ProductAdapter(val context: Context, val products: ArrayList<ProductItem>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemProductBinding.bind(itemView)

        fun bind(context: Context, productItem: ProductItem) {
            binding.textProductName.text = "${productItem.brand} ${productItem.type}  ${productItem.year}"
            binding.textMachineNumber.text = productItem.car_vin

            this.itemView.setOnClickListener{
                context.startActivity(Intent(context, DetailActivity::class.java))
            }
         }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}