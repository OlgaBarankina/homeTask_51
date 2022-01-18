package com.example.hometask_51

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import com.mukesh.image_processing.ImageProcessor

class MainActivity : AppCompatActivity() {


    val GALLERY = 0

    var bitmap: Bitmap? = null

    var oneBitMap:   Bitmap? = null
    var twoBitMap:   Bitmap? = null
    var threeBitmap: Bitmap? = null
    var fourBitMap:  Bitmap? = null
    var fiveBitMap:  Bitmap? = null
    var sixBitMap:   Bitmap? = null
    var sevenBitMap: Bitmap? = null
    var eightBitMap: Bitmap? = null
    var nineBitMap:  Bitmap? = null
    var tenBitMap:   Bitmap? = null



    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /*add into settings.gradle
          maven { url "https://jitpack.io" }
         build.gradle
         implementation 'com.github.mukeshsolanki:photofilter:1.0.2'

         and into manifest file
         <uses-permission android:name="android.permission.CAMERA" />
         <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> */


        val toastEnter = Toast.makeText(
            applicationContext,
            "Please, enter Login and Password",
            Toast.LENGTH_LONG
        ) as Toast

        val toastGlobalMessage = Toast.makeText(
            applicationContext,
            "User, somrhting went wrong",
            Toast.LENGTH_LONG
        ) as Toast


        btSelectPhoto.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")               //take all images
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select new Avatar"), GALLERY)

        }


        HeavyProcess().start()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data ->
                    initializeOnCLickListerns()
                },
                {error ->
                    toastGlobalMessage.show()
                })




        val processor = ImageProcessor()
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar)

        oneBitMap = processor.tintImage(bitmap, 90)
        idIVOne?.setImageBitmap(oneBitMap)

        twoBitMap = processor.applyGaussianBlur(bitmap)
        idIVTwo?.setImageBitmap(twoBitMap)

        threeBitmap = processor.createSepiaToningEffect(bitmap, 1, 2.0, 1.0, 5.0)
        idIVThree?.setImageBitmap(threeBitmap)

        fourBitMap = processor.applySaturationFilter(bitmap, 3)
        idIVFour?.setImageBitmap(fourBitMap)

        fiveBitMap = processor.doGamma(bitmap, 1.0, 7.0, 8.0)
        idIVFive?.setImageBitmap(fiveBitMap)

        sixBitMap = processor.doGreyScale(bitmap)
        idIVSix?.setImageBitmap(sixBitMap)

        sevenBitMap = processor.engrave(bitmap)
        idIVSeven?.setImageBitmap(sevenBitMap)

        eightBitMap = processor.createContrast(bitmap, 1.5)
        idIVEight?.setImageBitmap(eightBitMap)

        nineBitMap = processor.applySnowEffect(bitmap)
        idIVNine?.setImageBitmap(nineBitMap)

        tenBitMap = processor.applyFleaEffect(bitmap)
        idIVTen?.setImageBitmap(tenBitMap)



        goToMainPage()
        toastEnter.show()
        //initializeOnCLickListerns()


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY && resultCode == RESULT_OK) {
            val imageUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            ivAvatar.setImageBitmap(bitmap)
        } else {
            (requestCode == GALLERY && resultCode == RESULT_OK)
        }
    }




    fun goToMainPage() {
        ivAvatar.setVisibility(View.INVISIBLE)
        btSelectPhoto.setVisibility(View.INVISIBLE)

        clLogin.setVisibility(View.VISIBLE)
        hcView.setVisibility(View.GONE)
        lyHorizontal.setVisibility(View.GONE)


        val toastError = Toast.makeText(
            applicationContext,
            "Wrong Password! Try again",
            Toast.LENGTH_LONG
        ) as Toast

        btLogin.setOnClickListener {
            if ((edLogin.getText().toString().equals("olgab")) && (edPassword.getText().toString()
                    .toInt().equals(12345))
            ) {
                clLogin.setVisibility(View.INVISIBLE)
                ivtitle.setVisibility(View.INVISIBLE)
                ivAvatar.setVisibility(View.VISIBLE)
                btSelectPhoto.setVisibility(View.VISIBLE)

                hcView.setVisibility(View.VISIBLE)
                lyHorizontal.setVisibility(View.VISIBLE)

            } else {
                toastError.show()
            }


        }
    }


    fun initializeOnCLickListerns() {
        idIVOne?.setOnClickListener {
            ivAvatar?.setImageBitmap(oneBitMap)
        }

        idIVTwo?.setOnClickListener {
            ivAvatar.setImageBitmap(twoBitMap)
        }

        idIVThree?.setOnClickListener {
            ivAvatar?.setImageBitmap(threeBitmap)
        }

        idIVFour?.setOnClickListener {
            ivAvatar?.setImageBitmap(fourBitMap)
        }

        idIVFive?.setOnClickListener {
            ivAvatar?.setImageBitmap(fiveBitMap)
        }

        idIVSix?.setOnClickListener {
            ivAvatar?.setImageBitmap(sixBitMap)
        }

        idIVSeven?.setOnClickListener {
            ivAvatar?.setImageBitmap(sevenBitMap)
        }

        idIVEight?.setOnClickListener {
            ivAvatar?.setImageBitmap(eightBitMap)
        }

        idIVNine?.setOnClickListener {
            ivAvatar?.setImageBitmap(nineBitMap)
        }

        idIVTen?.setOnClickListener {
            ivAvatar?.setImageBitmap(tenBitMap)
        }

    }


}
