package com.kevu.kevalassign

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class MainActivity : AppCompatActivity(), IProductsRVAdapter , PermissionListener {

    lateinit var viewModel: ProductViewModel


    val permissions = listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE ,
        Manifest.permission.MANAGE_EXTERNAL_STORAGE

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Product")
        Dexter.withActivity(this)
            .withPermission(permissions.toString())
            .withListener(this)
            .check()


        productRecycler.layoutManager=LinearLayoutManager(this)
        val adapter = ProductsRVAdapter(this,this)
        productRecycler.adapter = adapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            ProductViewModel::class.java)
        viewModel.allProducts.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)

            }
        })


    }






    fun addproductpage(view: View) {
        val intent =    Intent(this,AddProductActivity::class.java)
//        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }


    override fun onItemClicked(product: Product) {
        viewModel.deleteProduct(product)
    }

     fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>, token: PermissionToken) {
        // This method will be called when the user rejects a permission request
        // You must display a dialog box that explains to the user why the application needs this permission
        Toast.makeText(this, "Permission requried", Toast.LENGTH_SHORT).show()
    }

    fun onPermissionsChecked(report: MultiplePermissionsReport) {
        // Here you have to check granted permissions
        Toast.makeText(this, "Checked Permissions", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
//        Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        TODO("Not yet implemented")
    }
}