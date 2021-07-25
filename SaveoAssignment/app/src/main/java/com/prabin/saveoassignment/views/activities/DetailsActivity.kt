package com.prabin.saveoassignment.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.prabin.saveoassignment.databinding.ActivityDetailsBinding
import com.prabin.saveoassignment.model.ShowModel

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (intent != null && intent.extras != null) {
            val data: ShowModel = intent.getSerializableExtra("showModel") as ShowModel
            Log.d("prabin", data.image?.original.toString())
            Glide.with(this).load(data.image?.original).into(binding.ivShowDesc)
            binding.apply {
                tvDesc.text = data.summary
                tvShowName.text = data.name
                tvDuration.text = "${data.premiered}  |  ${data.schedule?.time}"
                if (data.rating?.average != null) {
                    tvRating.visibility = View.VISIBLE
                    starRatingBar.visibility = View.VISIBLE
                    tvRating.text = data.rating.average.toString()
                    starRatingBar.rating = data.rating.average.toString().toFloat() / 2
                }
            }

            if (data.genres!!.isNotEmpty()) {
                when (data.genres.size) {
                    1 -> {
                        binding.apply {
                            genre1.visibility = View.VISIBLE
                            genre1.text = data.genres[0]
                        }
                    }
                    2 -> {
                        binding.apply {
                            genre1.visibility = View.VISIBLE
                            genre1.text = data.genres[0]
                            genre2.visibility = View.VISIBLE
                            genre2.text = data.genres[1]
                        }
                    }
                    3 -> {
                        binding.apply {
                            genre1.visibility = View.VISIBLE
                            genre1.text = data.genres[0]
                            genre2.visibility = View.VISIBLE
                            genre2.text = data.genres[1]
                            genre3.visibility = View.VISIBLE
                            genre3.text = data.genres[2]
                        }
                    }
                    else -> {
                        binding.apply {
                            genre1.visibility = View.VISIBLE
                            genre1.text = data.genres[0]
                            genre2.visibility = View.VISIBLE
                            genre2.text = data.genres[1]
                            genre3.visibility = View.VISIBLE
                            genre3.text = data.genres[2]
                            genre4.visibility = View.VISIBLE
                            genre4.text = data.genres[3]
                        }
                    }
                }
            }
        }

        binding.ivBackDetails.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }
}