package id.muhammadfaisal.eratanitestinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import id.muhammadfaisal.eratanitestinterview.adapter.ProductAdapter
import id.muhammadfaisal.eratanitestinterview.databinding.ActivityMainBinding
import id.muhammadfaisal.eratanitestinterview.helper.GeneralHelper
import id.muhammadfaisal.eratanitestinterview.model.Product
import id.muhammadfaisal.eratanitestinterview.model.ProductItem
import id.muhammadfaisal.eratanitestinterview.model.Query

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var product: Product

    private lateinit var queryTo: String
    private lateinit var queryValue: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(this.layoutInflater)
        super.setContentView(this.binding.root)

        this.data()
        this.prepare()
    }

    private fun prepare() {
        this.binding.apply {
            this.recyclerProduct.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            this.recyclerProduct.adapter = ProductAdapter(this@MainActivity, this@MainActivity.getProductItems(null))

            this.buttonSearch.setOnClickListener(this@MainActivity)
        }
    }

    private fun data() {
        val gson = GsonBuilder().create()
        this.product = gson.fromJson(GeneralHelper.loadJSONFromAsset(this)!!, Product::class.java)

        val types: ArrayList<String> = ArrayList()

        types.add(Query.BRAND)
        types.add(Query.TYPE)
        types.add(Query.YEAR)

        val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, types)
        this.binding.inputFilter.setAdapter(arrayAdapter)

    }

    private fun getProductItems(query: Query?): ArrayList<ProductItem> {
        val productItems: ArrayList<ProductItem> = ArrayList()
        for (i in 0 until product.size - 1) {
            val product = product[i]
            if (query == null) {
                //If Query is Null, Show All Product
                productItems.add(product)
                this.binding.recyclerProduct.adapter = ProductAdapter(this, productItems)
            } else {
                //If Query is Not Null, Filter by Query To
                if (query.queryTo == Query.BRAND) {
                    //Query to is Brand
                    if (product.brand == query.queryValue) {
                        productItems.add(product)
                    }
                } else if (query.queryTo == Query.TYPE) {
                    //Query to is Type
                    if (product.type == query.queryValue) {
                        productItems.add(product)
                    }
                } else if (query.queryTo == Query.YEAR) {
                    //Query to is Year
                    if (product.year.toString() == query.queryValue) {
                        productItems.add(product)
                    }
                }else{
                    val name = "${product.brand} ${product.type} ${product.year}"
                    if (name.contains( query.queryValue)){
                        productItems.add(product)
                    }
                }

                //Re-Set Adapter
                this.binding.recyclerProduct.adapter = ProductAdapter(this, productItems)
            }
        }


        if (productItems.size == 0){
            this.binding.textDataEmpty.visibility = View.VISIBLE
        }else{
            if (query != null){
                Toast.makeText(this, "Product ${query.queryTo} ${query.queryValue} Have ${productItems.size} Item(s)", Toast.LENGTH_SHORT).show()
            }
            this.binding.textDataEmpty.visibility = View.GONE
        }

        return productItems
    }

    override fun onClick(p0: View?) {
        if (p0 == this.binding.buttonSearch){
            this.search()
        }
    }

    private fun search() {
        val queryTo = this.binding.inputFilter.text.toString()
        val queryValue = this.binding.inputSearch.text.toString()

        if (queryTo.isEmpty()){
            Toast.makeText(this, "Anda Belum Memasukkan Pencarian", Toast.LENGTH_SHORT).show()
            return
        }

        if (queryValue.isEmpty()){
            Toast.makeText(this, "Anda Belum Memasukkan Pencarian", Toast.LENGTH_SHORT).show()
            return
        }

        this.getProductItems(Query.copyWith(queryTo, queryValue))
    }
}