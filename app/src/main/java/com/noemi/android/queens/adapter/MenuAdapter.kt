package com.noemi.android.queens.adapter

import android.content.Context
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.noemi.android.queens.R
import com.noemi.android.queens.model.Menu
import java.util.*
import android.view.View
import com.noemi.android.queens.room.Order
import com.noemi.android.queens.room.OrderDataBase
import com.noemi.android.queens.room.OrderExecutor
import java.text.SimpleDateFormat


class MenuAdapter (private var context: Context, private var menus: MutableList<Menu>) : PagerAdapter(){

    private var click: Boolean = true
    private var orderSoup: String? = null
    private var orderMain: String? = null
    private var orderSide: String? = null

    override fun getCount(): Int {
        return menus.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.card_item, container, false)
        container.addView(view)

        val myDatabase = OrderDataBase.getOrderDataBase(context)

        val textSoup1 = view.findViewById<TextView>(R.id.soup_1)
        val textSoup2 = view.findViewById<TextView>(R.id.soup_2)
        val textSoup3 = view.findViewById<TextView>(R.id.soup_3)
        val main1 = view.findViewById<TextView>(R.id.main_1)
        val main2 = view.findViewById<TextView>(R.id.main_2)
        val main3 = view.findViewById<TextView>(R.id.main_3)
        val decor1 = view.findViewById<TextView>(R.id.decor_1)
        val decor2 = view.findViewById<TextView>(R.id.decor_2)

        val image1 = view.findViewById<ImageView>(R.id.image_1)
        val image2 = view.findViewById<ImageView>(R.id.image_2)
        val image3 = view.findViewById<ImageView>(R.id.image_3)
        val image4 = view.findViewById<ImageView>(R.id.image_4)
        val image5 = view.findViewById<ImageView>(R.id.image_5)
        val image6 = view.findViewById<ImageView>(R.id.image_6)
        val image7 = view.findViewById<ImageView>(R.id.image_7)
        val image8 = view.findViewById<ImageView>(R.id.image_8)

        val fb = view.findViewById<FloatingActionButton>(R.id.fb)


        val menuItem = menus.get(position)
        val courseList = menuItem.sort
        val orderDate = menuItem.data

        val course1 = courseList!![0].meal!!.name
        textSoup1.setText(course1)

        val course2 = courseList[1].meal!!.name
        textSoup2.setText(course2)

        val course3 = courseList[2].meal!!.name
        textSoup3.setText(course3)

        val course4 = courseList[3].meal!!.name
        main1.setText(course4)

        val course5 = courseList[4].meal!!.name
        main2.setText(course5)

        val course6 = courseList[5].meal!!.name
        main3.setText(course6)

        val course7 = courseList[6].meal!!.name
        decor1.setText(course7)

        val course8 = courseList[7].meal!!.name
        decor2.setText(course8)

        val layout1 = view.findViewById<RelativeLayout>(R.id.layout_1)
        layout1.setOnClickListener{

            if (click){
                textSoup1.setTextColor(context.resources.getColor(R.color.grey))
                image1.setImageResource(R.drawable.checkbox_selected)
                orderSoup = course1
                click = false
            }else {
                textSoup1.setTextColor(context.resources.getColor(R.color.faded_grey))
                image1.setImageResource(R.drawable.checkbox)
                click = true
            }

        }

        val layout2 = view.findViewById<RelativeLayout>(R.id.layout_2)
        layout2.setOnClickListener {
            if (click){
                textSoup2.setTextColor(context.resources.getColor(R.color.grey))
                image2.setImageResource(R.drawable.checkbox_selected)
                orderSoup = course2
                click = false
            } else {
                textSoup2.setTextColor(context.resources.getColor(R.color.faded_grey))
                image2.setImageResource(R.drawable.checkbox)
                click = true
            }

        }

        val layout3 = view.findViewById<RelativeLayout>(R.id.layout_3)
        layout3.setOnClickListener {
            if (click){
                textSoup3.setTextColor(context.resources.getColor(R.color.grey))
                image3.setImageResource(R.drawable.checkbox_selected)
                orderSoup = course3
                click = false
            }else{
                textSoup3.setTextColor(context.resources.getColor(R.color.faded_grey))
                image3.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        val layout4 = view.findViewById<RelativeLayout>(R.id.layout_4)
        layout4.setOnClickListener {
            if (click){
                main1.setTextColor(context.resources.getColor(R.color.grey))
                image4.setImageResource(R.drawable.checkbox_selected)
                orderMain = course4
                click = false
            }else {
                main1.setTextColor(context.resources.getColor(R.color.faded_grey))
                image4.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        val layout5 = view.findViewById<RelativeLayout>(R.id.layout_5)
        layout5.setOnClickListener {
            if (click){
                main2.setTextColor(context.resources.getColor(R.color.grey))
                image5.setImageResource(R.drawable.checkbox_selected)
                orderMain = course5
                click = false
            } else{
                main2.setTextColor(context.resources.getColor(R.color.faded_grey))
                image5.setImageResource(R.drawable.checkbox)
                click = true
            }

        }

        val layout6 = view.findViewById<RelativeLayout>(R.id.layout_6)
        layout6.setOnClickListener {
            if (click){
                main3.setTextColor(context.resources.getColor(R.color.grey))
                image6.setImageResource(R.drawable.checkbox_selected)
                orderMain = course6
                click = false
            } else {
                main3.setTextColor(context.resources.getColor(R.color.faded_grey))
                image6.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        val layout7 = view.findViewById<RelativeLayout>(R.id.layout_7)
        layout7.setOnClickListener {
            if (click){
                decor1.setTextColor(context.resources.getColor(R.color.grey))
                image7.setImageResource(R.drawable.checkbox_selected)
                orderSide = course7
                click = false
            } else {
                decor1.setTextColor(context.resources.getColor(R.color.faded_grey))
                image7.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        val layout8 = view.findViewById<RelativeLayout>(R.id.layout_8)
        layout8.setOnClickListener {
            if (click){
                decor2.setTextColor(context.resources.getColor(R.color.grey))
                image8.setImageResource(R.drawable.checkbox_selected)
                orderSide = course8
                click = false
            }else{
                decor2.setTextColor(context.resources.getColor(R.color.faded_grey))
                image8.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        fb.setOnClickListener {

            if ( orderSoup!=null && orderMain!=null && orderSide!=null){

                val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
                val ziuaComenzii = dateFormat.format(orderDate)
                val ziua = ziuaComenzii.substring(0, ziuaComenzii.length-1).toUpperCase()
                val valueToShare = "Ati comandat in data de: $ziua \nSupa: $orderSoup \nFelul 2: $orderMain \nGarnitura: $orderSide"

                val formatForDB = SimpleDateFormat("EEEE, d LLLL yyyy", Locale.forLanguageTag("ro-RO"))
                val dataComenzii = formatForDB.format(orderDate)
                val newDate = dataComenzii.substring(0, 1).toUpperCase() + dataComenzii.substring(1)

                OrderExecutor.getOrderExecutor().diskIO.execute{
                    val order = Order(newDate, orderSoup!!, orderMain!!, orderSide!!)
                    myDatabase.orderDao().insertOrder(order)
                }

                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Queens's order")
                intent.putExtra(Intent.EXTRA_TEXT, valueToShare)
                context.startActivity(Intent.createChooser(intent, "Share via...."))


            }else{

                Toast.makeText(context, "You need to select at least one from each meal type", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val dataLong = menus.get(position).data
        val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
        val time = dateFormat.format(dataLong)
        val title = time.substring(0, time.length-1).toUpperCase()
        return title

    }
}
