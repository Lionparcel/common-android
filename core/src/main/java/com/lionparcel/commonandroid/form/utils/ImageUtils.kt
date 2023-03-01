package com.lionparcel.commonandroid.form.utils

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.lionparcel.commonandroid.R
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

object ImageUtils {

    private const val PROVIDER = ".provider"
    private const val FOLDER_NAME = "LionParcel"

    fun getOutputMediaFileUri(activity: Activity, name: String): Uri? {
        return getOutputMediaFile(activity, name)?.let {
            FileProvider.getUriForFile(
                activity, "common$PROVIDER",
                it
            )
        }
    }

    private fun getOutputMediaFile(activity: Activity, name: String): File? {
        // External sdcard location
        val mediaStorageDir =
            File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), FOLDER_NAME)

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }

        // Create a media file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val mediaFile: File
        mediaFile =
            File(mediaStorageDir.path + File.separator + "IMG_" + name + "_" + timeStamp + ".jpg")
        return mediaFile
    }

    fun createTempFile(activity: Activity, bitmap: Bitmap): File {
        val file = File(
            activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            , "${System.currentTimeMillis()}_image" + ".jpg"
        )
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos)
        val bitmapData = bos.toByteArray()
        //write the bytes in file
        try {
            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    fun setToImageView(imageView: ImageView, bitmap_size: Int, bmp: Bitmap) {
        //compress image
        val bytes = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes)
        val decoded = BitmapFactory.decodeStream(ByteArrayInputStream(bytes.toByteArray()))
        imageView.setImageBitmap(decoded)
    }

    fun saveBitmapToGallery(context: Context, view: View, fileName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            val pictureFile = uri?.let { context.contentResolver.openOutputStream(it) }!!
            val boardingPass: Bitmap = getBitmapFromView(context, view)
            boardingPass.compress(Bitmap.CompressFormat.PNG, 100, pictureFile)
        } else {
            saveOldWays(context, view, fileName)
        }
    }

    private fun saveOldWays(context: Context, view: View, fileName: String) {
        val pictureFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName)
        val boardingPass: Bitmap = getBitmapFromView(context, view)
        try {
            pictureFile.createNewFile()
            val oStream = FileOutputStream(pictureFile)
            boardingPass.compress(Bitmap.CompressFormat.PNG, 100, oStream)
            oStream.flush()
            oStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getBitmapFromView(context: Context, view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        canvas.drawColor(ContextCompat.getColor(context, R.color.interpack7))
        view.draw(canvas)
        return returnedBitmap
    }
}
