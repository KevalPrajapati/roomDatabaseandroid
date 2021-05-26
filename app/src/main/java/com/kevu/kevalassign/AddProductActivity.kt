package com.kevu.kevalassign

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class AddProductActivity : AppCompatActivity() ,IProductsRVAdapter{
    private lateinit var viewModel: ProductViewModel
    lateinit var imageView: ImageView
    private val pickImage = 100
    lateinit var photoButton: Button
    private var imageUri: Uri? = null
    val adapter = ProductsRVAdapter(this,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        imageView = findViewById(R.id.imageView)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(com.kevu.kevalassign.ProductViewModel::class.java)
        viewModel.allProducts.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }


        })
        photoButton = findViewById(R.id.photobutton)
        photoButton.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }
    fun addProduct(view: View) {
        val name = findViewById(R.id.productnameinputeditor) as EditText
        val desc = findViewById(R.id.descinputeditor) as EditText


        viewModel.insertProduct(Product(name.text.toString(),desc.text.toString(),imageUri.toString()))
                Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show()

        finish()
//


    }

    override fun onItemClicked(product: Product) {
        viewModel.deleteProduct(product)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
    fun pickimage(){
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)

    }
}