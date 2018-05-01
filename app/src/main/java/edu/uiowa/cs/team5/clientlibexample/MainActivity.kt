package edu.uiowa.cs.team5.clientlibexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import edu.uiowa.cs.team5.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = ClientController("10.0.2.2:8080")


        /*
        //Admin create survey
        var response1 = client.login("admin","admin")
        text1.text = response1.text

        val patron1 = response1.patron!!
        val survey1 = Survey("This is a survey title")
        survey1.questionList.add(Question("this is a question"))
        patron1.surveyList.add(survey1)

        client.submit(patron1)//every survey submit by admin will update everyone's survey
        */

        var response2 = client.login("user","user")
        val patron = response2.patron!!
        patron.surveyList[0].questionList[0].answer = "nonono"
        client.submit(patron)

    }
}
