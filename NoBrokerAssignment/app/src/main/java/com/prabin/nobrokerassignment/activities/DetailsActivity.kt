package com.prabin.nobrokerassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.prabin.nobrokerassignment.R
import com.prabin.nobrokerassignment.roomDb.DataEntity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent != null && intent.extras != null) {
            val data: DataEntity = intent.getSerializableExtra("dataEntity") as DataEntity
            Log.d("prabin", data.title)
            Glide.with(this).load(data.image).into(ivDetails)
            tvTitleDetails.text = data.title
            tvDescDetails.text = data.subTitle
        }
    }
}