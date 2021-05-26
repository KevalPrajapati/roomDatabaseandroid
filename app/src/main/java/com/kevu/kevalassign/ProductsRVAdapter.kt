package com.kevu.kevalassign

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevu.kevalassign.Product
import kotlinx.android.synthetic.main.productitem.view.*

class ProductsRVAdapter(private val context:Context, val listener: IProductsRVAdapter): RecyclerView.Adapter<ProductsRVAdapter.ProductViewHolder>() {
    private val allProducts = ArrayList<Product>()
    inner class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById<TextView>(R.id.text)
        val descView: TextView = itemView.findViewById<TextView>(R.id.desc)
        val photoView = itemView.findViewById<ImageView>(R.id.productimage)
        val deleteButton =itemView.findViewById<android.widget.ImageView>(R.id.deletebutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       val viewHolder = ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.productitem,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allProducts[viewHolder.position])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = allProducts[position]
        holder.textView.text=currentProduct.productName
        holder.descView.text=currentProduct.desc
        val imageuri = Uri.parse(currentProduct.photostring)
        holder.photoView.setImageURI(imageuri)


    }

    override fun getItemCount(): Int {
        return allProducts.size
    }
    fun updateList(newList: List<Product>){
        allProducts.clear()
        allProducts.addAll(newList)
        notifyDataSetChanged()
    }

}


interface IProductsRVAdapter{
    fun onItemClicked(product: Product)
}