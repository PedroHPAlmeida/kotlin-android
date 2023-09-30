package br.com.alura.orgs.extensions

import android.content.Context
import android.os.Build
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load

fun ImageView.tentaCarregarImagem(context: Context, url: String? = null) {
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    load(url, imageLoader = imageLoader) {
        fallback(br.com.alura.orgs.R.drawable.imagem_padrao)
        error(br.com.alura.orgs.R.drawable.erro)
        placeholder(br.com.alura.orgs.R.drawable.carregando)
    }
}