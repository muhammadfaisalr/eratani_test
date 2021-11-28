package id.muhammadfaisal.eratanitestinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.muhammadfaisal.eratanitestinterview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetailBinding.inflate(this.layoutInflater)
        this.setContentView(this.binding.root)
    }
}