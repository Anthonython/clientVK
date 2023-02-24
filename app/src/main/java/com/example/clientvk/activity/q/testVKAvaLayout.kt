package com.example.clientvk.activity.q

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.*
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.clientvk.R
import com.example.clientvk.adapters.*
import com.example.clientvk.models.AvaModel
import com.example.clientvk.models.FModel
import com.example.clientvk.presenter.AvaPresenter
import com.example.clientvk.views.AvaView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class testVKAvaLayout : MvpAppCompatActivity(), AvaView, Animation.AnimationListener {
   private lateinit var loaderava: CircularProgressView
   private lateinit var RecycleAva: RecyclerView
   private lateinit var searchAvaText: EditText
   private lateinit var AvaMine: ViewPager2
   private lateinit var AvaOne: ViewPager2
   private lateinit var AvaThree: ViewPager2
   private lateinit var likes: View
   private lateinit var mFrameL: FrameLayout
            private val FModel: FModel by viewModels()
                    var RecAvaAdapter = AvaAdapter(this)
            private var pos0 = 0
                    var pos1 = 0
            private var vibrat = true

    private lateinit var openAvaAnim: Animation
    private lateinit var likeanim1: Animation
    private lateinit var likeanim2: Animation
    private lateinit var openlike: Animation
    private lateinit var AvaModelP: AvaModel

    @InjectPresenter
    lateinit var AvaPresenter: AvaPresenter

    @SuppressLint("WrongConstant", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_vkava_layout)

        AvaMine = findViewById(R.id.fullAvaPag)
        AvaOne = findViewById(R.id.fullAvaPag1)
        AvaThree = findViewById(R.id.fullAvaPag2)
        loaderava = findViewById(R.id.avaloader)
        RecycleAva = findViewById(R.id.recyclerAva)
        searchAvaText = findViewById(R.id.hintsearchava)
        mFrameL = findViewById(R.id.mFrameLayout)
        likes = findViewById(R.id.ser)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        AvaOne.isUserInputEnabled = false
        AvaThree.isUserInputEnabled = false

        loadanim()

        AvaPresenter.loadAva()
        RecycleAva.adapter = RecAvaAdapter
        RecycleAva.layoutManager = LinearLayoutManager(this, OrientationHelper.VERTICAL, false)
        RecycleAva.setHasFixedSize(true)

        searchAvaText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                RecAvaAdapter.filter(s.toString())
            }
        })

        AvaMine.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
               pos1 = position
            }
        })

        AvaMine.setPageTransformer { page, position ->
             page.setOnClickListener {
               AvaPresenter.loadfullpic(AvaModelP.id)
             }
             if (pos0 != pos1) {
                 vibrator.vibrate(7)
                 vibrat = false
                 if (pos0 > pos1) {  //Перемещение влево
                     if (pos1 == 2) FModel.piclink0.value = AvaModelP.fullAva
                     AvaOne.setCurrentItem(pos0-1, true)
                     AvaThree.setCurrentItem(pos0-1, true)
                 } else {      //Перемещение вправо
                     AvaOne.setCurrentItem(pos0+1, true)
                     AvaThree.setCurrentItem(pos0+1, true)
                 }
                 pos0 = pos1
             }
        }
        AvaOne.setPageTransformer { page, position ->
             page.setOnClickListener {
                 AvaMine.setCurrentItem(pos0-1, true)
                 AvaThree.setCurrentItem(pos0, true)
                 vibrat = true
             }
             if (vibrat){
                 vibrator.vibrate(7)
                 vibrat = false
             }
         }
        AvaThree.setPageTransformer { page, position ->
            page.setOnClickListener {
                AvaMine.setCurrentItem(pos0+1, true)
                AvaOne.setCurrentItem(pos0, true)

                vibrat = true
            }
             if (vibrat){
                 vibrator.vibrate(7)
                 vibrat = false
             }
        }

        likes.setOnClickListener {
            likeanim1.setAnimationListener(this)
            likes.startAnimation(likeanim1)
            likes.setBackgroundResource(R.drawable.ser1)
            Handler().postDelayed({AvaPresenter.addlike()}, 360)
        }
    }

    override fun ShowError(id: Int) {
        Toast.makeText(this, R.string.failed, Toast.LENGTH_SHORT).show()
    }
    override fun SetupEmprty() {
        RecycleAva.visibility = View.INVISIBLE
        loaderava.visibility = View.INVISIBLE
        ShowError(0)
    }
    override fun SetupAva(avaList: ArrayList<AvaModel>) {
        RecycleAva.visibility = View.VISIBLE
        loaderava.visibility = View.INVISIBLE
        RecAvaAdapter.loadUsers(avaList)
    }

    override fun StartLoad() {
        searchAvaText.visibility = View.INVISIBLE
        AvaOne.visibility = View.INVISIBLE
        AvaMine.visibility = View.INVISIBLE
        AvaThree.visibility = View.INVISIBLE
        RecycleAva.visibility = View.INVISIBLE
        likes.visibility = View.INVISIBLE
        loaderava.visibility = View.VISIBLE
        loaderava.startAnimation()
    }

    override fun EndLoad() {
        searchAvaText.visibility = View.VISIBLE
        loaderava.visibility = View.INVISIBLE
        RecycleAva.visibility = View.VISIBLE
        AvaOne.visibility = View.VISIBLE
        AvaMine.visibility = View.VISIBLE
        AvaThree.visibility = View.VISIBLE
    }

    override fun ClckFava(AvaModel: AvaModel) {
        AvaModelP = AvaModel
        AvaPresenter.loadphoto(AvaModelP.id)
        Handler().postDelayed({AvaPresenter.isLiked(AvaModelP.id)
            likes.startAnimation(openlike)}, 240)
        AvaMine.setCurrentItem(0, true)
        AvaPresenter.loadAphotos(AvaModelP.id)
        openAvaAnim.setAnimationListener(this)
        FModel.piclink0.value = AvaModelP.fullAva
        AvaMine.visibility = View.INVISIBLE
        AvaOne.visibility = View.INVISIBLE
        AvaThree.visibility = View.INVISIBLE
        likes.visibility = View.INVISIBLE
        Handler().postDelayed({
            AvaMine.visibility = View.VISIBLE
            AvaOne.visibility = View.VISIBLE
            AvaThree.visibility = View.VISIBLE
            likes.visibility = View.VISIBLE
            AvaMine.startAnimation(openAvaAnim)
            AvaOne.startAnimation(openAvaAnim)
            AvaThree.startAnimation(openAvaAnim)
             }, 450)
    }

   private fun loadanim(){
       openAvaAnim = AnimationUtils.loadAnimation(this, R.anim.open1)
       likeanim1 = AnimationUtils.loadAnimation(this, R.anim.likes1)
       likeanim2 = AnimationUtils.loadAnimation(this, R.anim.likes2)
       openlike = AnimationUtils.loadAnimation(this, R.anim.openlikes)
   }

    override fun onAnimationStart(animation: Animation?) {}

    override fun onAnimationEnd(animation: Animation?) {
        when (animation){
           likeanim1 -> likes.startAnimation(likeanim2)
       }
    }

    override fun onAnimationRepeat(animation: Animation?) {}

    override fun isLiked(value: Int) {
        if (value == 1) likes.setBackgroundResource(R.drawable.ser1)
        else likes.setBackgroundResource(R.drawable.ser2)
    }

    override fun sendphoto(array: ArrayList<String>) {
        Log.e("TAG", "RR: $array")
        AvaMine.adapter = slideadapter(array)
        AvaOne.adapter = slideadapter1(array)
        AvaThree.adapter = slideadapter2(array)
    }

    override fun loadfullpic(link: ArrayList<String>) {
        val intent = Intent(this, fullpic::class.java)
        intent.putExtra(R.string.linkpic.toString(), link)
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(7)
        startActivity(intent)
    }
}