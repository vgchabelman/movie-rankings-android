package br.com.victorchabelman.movierankings.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.victorchabelman.movierankings.R
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageBg")
fun loadBg(view: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.generic_movie_bg)
        .noFade()
        .resize(500, 300)
        .into(view)
}

@BindingAdapter("bind:imagePoster")
fun loadPoster(view: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.generic_poster)
        .noFade()
        .resize(109, 163)
        .into(view)
}