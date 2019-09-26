package br.com.victorchabelman.movierankings.util

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.victorchabelman.movierankings.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vansuita.gaussianblur.GaussianBlur


@BindingAdapter("bind:imageBg")
fun loadBg(view: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.generic_movie_bg)
        .noFade()
        .resize(500, 300)
        .into(view, object : Callback {
            override fun onSuccess() {
                val blurredBitmap = GaussianBlur.with(view.context).render((view.drawable as BitmapDrawable).bitmap)
                view.setImageBitmap(blurredBitmap)
            }

            override fun onError(e: Exception?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
}

@BindingAdapter("bind:imagePoster")
fun loadPoster(view: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.generic_poster)
//        .resize(218, 312)
        .into(view)
}

@BindingAdapter("bind:imagePosterHQ")
fun loadPosterHighQuality(view: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return
    Picasso.get()
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.generic_poster)
        .resize(436, 652)
        .into(view)
}